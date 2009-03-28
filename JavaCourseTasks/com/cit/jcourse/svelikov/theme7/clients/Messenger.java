/**
 * 
 */
package com.cit.jcourse.svelikov.theme7.clients;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * Messenger is a Thread that is started when there are
 * message to be sent to the all of connected clients.
 * 
 * @author Svilen Velikov
 * @version 2007-7-16
 */
public class Messenger implements Runnable {
	
	/**
	 * Initializes and starts this thread.
	 * @param cl The list with the connected clients.
	 * @param n  The message to be populated.
	 */
	public Messenger(List cl, String n) {
		clientsList      = cl;
		notice           = n;
		clientsToNotice  = cl.size();	//number of clients in the list
		new Thread(this).start();
	}
	
	/** The list with the accepted clients. */
	private List clientsList;
	
	/** Number of clients in the clientsList. */
	private int clientsToNotice;

	/** The message to populate. */
	private String notice;
	
	/**
	 * Notifies the connected clients from the list.
	 */
	public void run() {
		for (int i = 0; i < clientsToNotice; i++) {
			Socket thisClient = (Socket) clientsList.get(i);
			try {
				PrintWriter msgWriter = new PrintWriter(thisClient.getOutputStream(), true);
				msgWriter.println(notice);
				//msgWriter.println("Client# "+theLastConnected+" is On-Line...");				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
