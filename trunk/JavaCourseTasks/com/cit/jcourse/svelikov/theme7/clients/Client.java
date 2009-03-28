package com.cit.jcourse.svelikov.theme7.clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This realizes a client side in this server/client program.
 * It takes a reference to the gui object that stands over this
 * client. Once started this client tryes to connect to the 
 * remote server. If it succeed a thread is started to listen 
 * for a messages from the server until it won't be stopped.
 * 
 * @author Svilen Velikov
 * @version 2007-7-22
 */
public class Client extends Thread {
	
	/**
	 * Initializes the client.
	 * @param gui  The gui object.
	 */
	public Client(ClientGUI gui) {
		this.gui   = gui;
	}
	
	/** The Socket object. */
	private Socket socket;

	/** The reader. */
	private BufferedReader reader;
	
	/** Refference to the gui object. */
	private ClientGUI gui;

	/**
	 * Creates a socket and tryes to connect to the server.
	 * Once connection is established an input stream is created
	 * to ensure a chanel for servers messages.
	 */
	public boolean connectToServer() {
		try {
			socket = new Socket();
			//bind the socket to the server
			socket.connect(new InetSocketAddress(
							InetAddress.getByName(
							PropertiesClass.hostName),
							PropertiesClass.portNumber),
							PropertiesClass.timeOut);
			gui.prtMessage("Connected to server...");
			//create input stream to read from server
			reader = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
			gui.prtMessage("Server said :: " + reader.readLine());
		} catch (UnknownHostException e1) {
			gui.setWarnings("Unknown host!", 1);
		} catch (ConnectException e1) {
			gui.setWarnings("Connection refused!\nMaybe the server is not started.", 1);
			socket = null;
			gui.enableConnectButton();
		} catch (NoRouteToHostException e1) {
			gui.setWarnings("Connection timeout. Try again later!", 1);
		} catch (IOException e1) {
			gui.setWarnings("Can't create socket!", 1);
		}
		if(socket != null)
			return true;
		else
			return false;
	}

	/**
	 * Realizes the loop in which the client reads messages
	 * from the server and prints them in its window.
	 * @throws NoSocketException If the server is stopped.
	 */
	public void run() throws NoSocketException {	
		if(connectToServer()) {	//try to connect to server.
			String s = null;	//holds the messages read from the stream
			try {
				while (true) {
					s = reader.readLine();
					if (s == null) {						//happens when the server crashes or so...
						throw new NoSocketException("Server dissapeared!");
					} else if("disconnected".equals(s)) {	//happens when the server is going to be stopped
						gui.setWarnings("The server was stopped!\n This client will exit!", 2);
						break;
						//throw new NoSocketException("Server was stopped!");
					} else {
						gui.prtMessage("Server said :: "+s);
					}
				}
			} catch (IOException e) {
				gui.setWarnings("Error during reading!", 1);
			} finally {
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
