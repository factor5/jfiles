package com.cit.jcourse.svelikov.theme7.clients;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class realizes the server side in this server/client program.
 * It takes five parameters in its constructor : port, host, backlog,
 * ip and a reference to its gui object. Once started the server enters
 * in an infinite loop to accept clients. When one is accepted then a 
 * thread is started to notify the other clients connected for this.
 * The server also can be stopped through the gui. Then the messanger
 * thread is started again to notifys the connected clients that the 
 * server will be stopped.
 * 
 * @author Svilen Velikov
 * @version 2007-7-22
 */
public class Server {
	
	/** Refference to gui object. */
	private ServerGUI gui;
	
	/** The ServerSocket object. */
	private ServerSocket serverSocket;

	/** The Socket object. */
	private Socket lastAcceptedClient;

	/** The PrintWriter used to send message to the client. */
	private PrintWriter writer;

	/** The accepted clients list. */
	private List clients;
	
	/** The message for the clients. */
	private String notice;

	/**
	 * Initializes the GUI for the server.
	 * @param gui   The gui's reference.
	 */
	public Server(ServerGUI gui) {
		this.gui      = gui;
	}

	/**
	 * Starts the server and invokes acceptClients method.
	 */
	public void startServer() {
		try {
			serverSocket = new ServerSocket(
					PropertiesClass.portNumber,
					PropertiesClass.backlog,
					InetAddress.getByName(PropertiesClass.hostName));
			gui.prtMessage("<<< SERVER IS STARTED >>>");
			acceptClients();
		} catch (BindException e1) {
			gui.setWarnings("The port " + PropertiesClass.portNumber +
							" is busy.\n      EXIT!!!", 1);
			System.exit(1);
		} catch (SocketException e1) {
			gui.setWarnings("Can't open socket!", 1);
		} catch (IOException e1) {
			gui.setWarnings("Can't use this hostname!", 1);
		}
	}

	/**
	 * Waits for clients to connect. When there is one it is notified
	 * for that. Then a Messanger thread is started to notify others 
	 * connected for the new one. Done this the client just connected
	 * is going to be added in the list.
	 */
	private void acceptClients() {	
		clients = Collections.synchronizedList(new ArrayList());//the list with the client connected
		PrintWriter writer;			//stream used to write to the client
		int clientsNumber = 1;		//counter for the connected clients

		while (true) {
			try {
				lastAcceptedClient = serverSocket.accept();
				gui.prtMessage("Client#" + clientsNumber + " accepted...");
				//get output stream to write to this client
				writer = new PrintWriter(new BufferedWriter(
							new OutputStreamWriter(
								lastAcceptedClient.getOutputStream())),true);
				writer.println("You are Client# " + clientsNumber);				
			} catch (SocketException e) {
				break;
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
			if (clientsNumber > 1) {
				notice = "Client# "+clientsNumber+" is On-Line...";
				new Messenger(clients, notice);
				clients.add(lastAcceptedClient);
			} else {	//this is the first client connected  messenger is not started
				clients.add(lastAcceptedClient);
			}
			clientsNumber++;					
		}
	}

	/**
	 * Notifies the connected clients and cleans after the
	 * server is stopped.
	 */
	public void stopServer() {
		notice = "disconnected";
		new Messenger(clients, notice);
		try {
			//sleeps for a while to give a 
			//chance the clients to be notified
			Thread.sleep(PropertiesClass.shutdownDelay);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		if (writer != null)
			writer.close();	//close the stream
		try {
		} finally {			//close the sockets
			try {
				if (lastAcceptedClient != null)
					lastAcceptedClient.close();
				if (serverSocket != null)
					serverSocket.close();
				writer = null;
				lastAcceptedClient = null;
				serverSocket       = null;
			} catch (IOException e) {
				gui.setWarnings("Error in closing sockets!", 1);
			}
		}
		gui.prtMessage("<<< SERVER IS STOPPED >>>");
	}
}
