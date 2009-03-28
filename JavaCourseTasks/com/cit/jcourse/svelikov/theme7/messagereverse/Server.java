/**
 * 
 */
package com.cit.jcourse.svelikov.theme7.messagereverse;

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
 * This class represents a kind of echo server. Once it is initialized
 * the server can be started through the gui. If the creation of the 
 * server socket is successful one thread is started to deal with 
 * accepting the clients. Finally when a client is connected another
 * thread is started to serve it.
 * 
 * @author Svilen Velikov
 * @version 2007-7-23
 */
public class Server {
	/** Refference to gui object. */
	private ServerGUI gui;
	
	/** The ServerSocket object. */
	private ServerSocket serverSocket;

	/** The Socket object. */
	private Socket client;

	/** The PrintWriter used to send message to the client. */
	private PrintWriter writer;
	
	/** A thread that serves a client. */
	private MaintainClient mc;

	/** Holds a list of connected clients. */
	private List clients;
	
	/**
	 * Constructor..
	 * @param gui   The gui's reference.
	 */
	public Server(ServerGUI gui) {
		this.gui      = gui;
	}

	/**
	 * Starts the server and creates a thread to listen for clients.
	 */
	public void startServer() {
		try {
			serverSocket = new ServerSocket(PropertiesClass.portNumber,
											PropertiesClass.backlog,
											InetAddress.getByName(
											PropertiesClass.hostName));
		} catch (BindException e1) {
			gui.setWarnings("Can't use this port!", 1);
			System.exit(1);
		} catch (SocketException e1) {
			gui.setWarnings("Can't open socket!", 1);
		} catch (IOException e1) {
			gui.setWarnings("Can't use this hostname!", 1);
		}
		
		if(serverSocket.isBound()) {						//server is started
			gui.prtMessage("<<< SERVER IS STARTED >>>");	
			clients = Collections.synchronizedList(new ArrayList());
			Thread clientsListener = new Thread(cListener);	//start a thread to listen for clients
			clientsListener.start();			
		} else {
			gui.enableStartButton();						//start is failure
		}
	}

	/**
	 * A thread that loops waiting for clients to connect. When
	 * any client is accepted an output stream is created from
	 * the clients socket and a welcome message is sent. A thread
	 * to serve that client then is started.
	 */
	Runnable cListener = new Runnable() {
		
		public void run() {	
			PrintWriter writer;			//stream used to write to the client
			while (true) {
				try {
					client = serverSocket.accept();
					gui.prtMessage("Client#" + client.hashCode() + " accepted...");
					writer = new PrintWriter(new BufferedWriter(
								new OutputStreamWriter(
									client.getOutputStream())),true);
					writer.println("HELLO Client# " + client.hashCode());				
				} catch (SocketException e) {
					break;
				} catch (IOException e) {
					break;
				}
				if(client != null)
					mc = new MaintainClient(client, gui);	
					mc.start();
					clients.add(mc);
			}
		}
	};

	/**
	 * Disconnects any clients and closes the streams
	 * and sockets.
	 */
	public void stopServer() {
		if(clients.size() != 0) {
			disconnectClients();
		}
		
		if (writer != null)
			writer.close();	//close the stream
		try {
		} finally {			//close the sockets
			try {
				if (client != null)
					client.close();
				if (serverSocket != null)
					serverSocket.close();
				writer = null;
				client = null;
				serverSocket = null;
				gui.prtMessage("<<< SERVER IS STOPPED >>>");
			} catch (IOException e) {
				gui.setWarnings("Error in closing sockets!", 1);
			}
		}
	}
		
	/**
	 * Closes the streams and sockets of all maintain threads thus 
	 * the clients that have been maintained from these threads to 
	 * get null when tryes to read from them. This way the clients
	 * understands that the server is stopped.
	 */
	private void disconnectClients() {
		for (int i = clients.size(); i != 0 ; i--) {
			MaintainClient mc = (MaintainClient) clients.remove(i-1);
			if(mc.getWriter() != null) {
				mc.closeWriter();
			}
			if(mc.getReader() != null) {
				try {
					mc.closeReader();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(mc.getSocket() != null) {
				try {
					mc.closeSocket();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			mc.setStopped();
		}
		clients = null;
	}
}
