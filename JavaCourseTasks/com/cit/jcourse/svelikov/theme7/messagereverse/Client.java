package com.cit.jcourse.svelikov.theme7.messagereverse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.cit.jcourse.svelikov.theme7.clients.NoSocketException;

/**
 * This class realizes a client side of this client/server
 * aplication. It is constructed and started when the connect
 * button from the gui is pressed. The connectToServer method
 * is invoked to realize the connection with the server. If
 * the last operation is successful a while loop is started to
 * pass the input in console to server and to read its response.
 *  
 * @author Svilen Velikov
 * @version 2007-7-25
 */
public class Client extends Thread {

	/** The Socket object. */
	private Socket socket;

	/** The reader. */
	private BufferedReader reader;
	
	private PrintWriter writer;
	
	/** Refference to the gui object. */
	private ClientGUI gui;

	/** The text entered by the user in the console. */
	private String typed;
	
	/**
	 * Initializes the client.
	 * @param gui  The gui object.
	 */
	public Client(ClientGUI gui) {
		this.gui   = gui;
	}

	/**
	 * Creates a socket and tryes to connect to the server.
	 * Once connection is established an input stream is created
	 * to ensure a chanel for servers messages.
	 */
	public boolean connectToServer() {
		socket = new Socket();
		try {
			socket.connect(new InetSocketAddress(
					InetAddress.getByName(PropertiesClass.hostName),
					PropertiesClass.portNumber),
					PropertiesClass.timeOut);
			gui.prtMessage("CONNECTED TO SERVER...");
		} catch (UnknownHostException e1) {
			gui.setWarnings("Unknown host!", 1);
		} catch (ConnectException e1) {
			gui.setWarnings("   Can't find server!\n Maybe its not started!", 1);
			socket = null;
		} catch (NoRouteToHostException e1) {
			gui.setWarnings("Connection timeout. Try again later!", 1);
		} catch (IOException e1) {}
		
		if(socket != null) {
			try {
				reader = new BufferedReader(
							new InputStreamReader(socket.getInputStream()));
				writer  = new PrintWriter(
							new BufferedWriter(
								new OutputStreamWriter(socket.getOutputStream())), true);
				gui.prtMessage("Server said :: " + reader.readLine());//read welcome message
			} catch (IOException e) {
				e.printStackTrace();	//if any error occurs during the streams construction
			}
			gui.setConsole(true);
			return true;				//connected to server
		} else {
			gui.enableConnectButton();
			return false;				//fails to connect to server
		}
	}

	/**
	 * Trims the blanks before and after the text then replaces all
	 * \n with empty strings and saves the resulting string in this 
	 * variable.
	 * @param str The contents of the console.
	 */
	public void checkText(String str) {
		String checked = str.trim();
		checked = str.replaceAll("\n", "");
		typed = checked;
	}
	
	/**
	 * Wakes up this thread when there is any input in console.
	 */
	public synchronized void wakeUp() {
		notify();
	}
	
	/**
	 * Connects to server if the socket is created and starts a loop
	 * to get written in console and write it to output stream then
	 * waits for a servers response.
	 */
	public synchronized void run() {	
		if(connectToServer()) {
			String receivedMessage = null;						//holds the messages read from the stream
			try {
				while (true) {
					if((typed == null) || ("".equals(typed))) {	//if there is nothing written in console
						try {
							wait();								//untill there is any input in console
						} catch (InterruptedException e) { e.printStackTrace(); }
					} 
					if(".".equals(typed)) {						//should disconnect from server
						gui.setWarnings("You are about to disconnect from server!", 2);
						break;
					} else {
						writer.println(typed);					//pass the message to server
						receivedMessage = reader.readLine();	//read the servers response
					
						if(("disconnected".equals(receivedMessage)) || (receivedMessage == null)) {
							throw new NoSocketException("  Can't find server!\n Maybe it was stopped!");
						}
						gui.prtMessage("The reverse of [ "+typed+" ] is [ "+receivedMessage+" ]");
					}
					typed = null;
				}
			} catch (IOException e) {
				gui.setWarnings("The server have been disconnected!"+
						"\n   Try to restart the client!", 1);
			} finally {
				gui.setConsole(false);
				try {
					if(reader != null)
						reader.close();
					if(socket != null)
						socket.close();
					gui.setVisible(false);
					gui.dispose();
					gui = null;
				} catch (IOException e) {
					gui.setWarnings("Error in closing!", 1);
				}
			}
		}
	}
}
