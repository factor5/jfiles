package com.cit.chat.client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;
import org.apache.log4j.*;

/**
 * This thread holds a list for the messages that are written by this 
 * user. The output stream is created in the constructor.
 * 
 * @author Svilen Velikov
 * @version
 */
public class ClientMessageSendThread extends Thread {

	/** The chat client. */
	private ChatClient client;
	
	/** The servers socket. */
	private Socket socketToServer;
	
	/** The output stream. */
	private PrintWriter writer;
	
	/** A message list. */
	private Vector messageList;
	
	/** */
	public Logger log = Logger.getLogger("ClientMessageSendThread");
	
	
	/**
	 * Initializes this thread and creates the output stream.
	 * @param cl  The chat client.
	 * @param cmf The chat window.
	 * @throws IOException If fails to create stream
	 */
	public ClientMessageSendThread(ChatClient cl) throws IOException {
		client = cl;
		messageList = new Vector();
		socketToServer  = client.getServerSocket();
		writer  = new PrintWriter(new BufferedWriter(
				  new OutputStreamWriter(socketToServer.getOutputStream())), true);
	}

	/**
	 * Writes a message through the stream if there is any 
	 * waiting in the queue.
	 */
	public void run() {
		try {
			while(!isInterrupted()) {
				String nextMessage = getNextMessage();
				writer.println(nextMessage);
			}
		} finally {
			try {
				if(writer != null) {
					writer.close();
				}
				if(socketToServer != null) {
					socketToServer.close();
				}
			} catch (IOException e) {
				log.error("Error in closing sockets/streams.", e);
			}
		}
	}
	
	/**
	 * Gets the next message from the list if there is any or waits othrerwise.
	 * @return The next message to be sent.
	 */
	private synchronized String getNextMessage() {
		String nextMessage = null;
		try {
			if(messageList.size() == 0) {
				wait();
			} 
			nextMessage = (String) messageList.remove(0);			
		} catch (InterruptedException e) {
			log.warn("Send thread interrupted. Client shut down.");
		}
		return nextMessage;
	}
	
	/**
	 * Sets new message to the list of waiting messages and then notifyes
	 * the waiting thread.
	 * @param message The new
	 */
	public synchronized void setMessage(String message) {
		messageList.add(message);
		this.notify();
	}
	
	/**
	 * Sets the interruption flag.
	 */
	public void stopThread() {
		this.interrupt();
	}
}
