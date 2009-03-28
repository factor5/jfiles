package com.cit.chat.server;

import java.util.Iterator;
import java.util.Vector;
import org.apache.log4j.*;

/**
 * The messenger thread is a some kind of allocation unit that deals with
 * the messages stored in a queue. While there are messages in the queue
 * it gets them and sends them through the client's sender threads.
 *  
 * @author Svilen Velikov
 * @version
 */
public class Messenger extends Thread {
	
	/** A list with connected clients data. */
	private Vector connectedClientsData;
	
	/** A queue with messages that should be populated. */
	private Vector messageList;
	
	/** The logger. */
	private Logger log = Logger.getLogger("ServerMainThread");
	
	
	/**
	 * Initializes the messenger thread.
	 * @param ccd The ConnectedClientsData reference.
	 */
	public Messenger(Vector ccd) {
		connectedClientsData = ccd;
		messageList = new Vector();
	}
	
	/**
	 * Gets a message from the queue and pass it to the send method.
	 */
	public void run() {
		try {
			while(!isInterrupted()) {
				Object message = getNextMessage();
				allocateMessage(message);				
			}
		} catch (InterruptedException e) {
			log.warn("Messenger interrupted.");
		}
	}
	
	/**
	 * Sends a message to the writer threads to be sent.
	 * @param messageToSend The next message that should be sent.
	 */
	private synchronized void allocateMessage(Object messageToAllocate) {
		for (Iterator it = connectedClientsData.iterator(); it.hasNext();) {
			ConnectedClientData clientdata = (ConnectedClientData) it.next();	//get the data object
			SendMessageThread sender = clientdata.getMessageSendThread();			//get the writer thread
			sender.setMessage(messageToAllocate);								//set the message to be sent
		}
	}
	
	/**
	 * Gets the next message from the list if there is any or waits othrerwise.
	 * @return The next message to be sent.
	 * @throws InterruptedException
	 */
	private synchronized Object getNextMessage() throws InterruptedException {
		if(messageList.size() == 0) {
			wait();
		}
		Object nextMessage = messageList.remove(0);
		return nextMessage;
	}
	
	/**
	 * Adds a message to the queue and notifies this thread for that.
	 * @param newMessage The message that should be sent.
	 */
	public synchronized void setMessage(Object newMessage) {
		messageList.add(newMessage);
		notify();
	}
	
	/**
	 * Ask this thread to stop.
	 */
	public void stopMessenger() {
		this.interrupt();
	}
}
