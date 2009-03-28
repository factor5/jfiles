package com.cit.chat.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import org.apache.log4j.*;

import com.cit.chat.common.IServerMessages;

/**
 * A thread that is started for every separate client and deals with
 * the output messages.
 * 
 * @author Svilen Velikov
 * @version 
 */
public class SendMessageThread extends Thread {

	/** The object that holds data for the client. */
	private ConnectedClientData aClient;
	
	/** The server's reference. */
	private ChatServer server;
	
	/** A list that holding messages that should be sent to this client. */
	private Object message;
	
	/** The output stream. */
	private ObjectOutputStream writer;
	
	/** The server's gui. */
	private ServerMainThread serverUI;
	
	/** The logger. */
	private Logger log = Logger.getLogger("ServerMainThread");
	
	
	/**
	 * Initializes this thread and gets the output stream from the data object.
	 * @param server The server's reference.
	 * @param gui    The gui's reference.
	 * @param client The client's object.
	 * @param writer The output stream.
	 */
	public SendMessageThread(ChatServer server, ServerMainThread gui, 
								ConnectedClientData client, 
								ObjectOutputStream writer) {
		this.writer = writer;
		this.server = server;
		aClient     = client;
		serverUI    = gui;
	}
	
	/**
	 * Gets a message from the list and sends it to the client.
	 * This thread may be stopped by setting the interruption
	 * flag to true.
	 */
	public void run() {
		try {
			while(!isInterrupted()) {
				//TODO should correct this synchorinzed block
				synchronized (this) {
					wait();
					writer.writeObject(message);
					writer.flush();
				}
			}
		} catch (IOException e) {
			log.error("IO Error.", e);
		} catch (InterruptedException e) {
			log.warn("Send message thread interrupted.");
		} finally {
			//serverUI.prtMessage("Client#"+aClient.nickname+" is disconnected!");
			server.populateMessage(IServerMessages.CLIENT_DISCONNECTED+"|"+aClient.getNickname());
			server.disconnectClient(aClient);
			aClient.getClientReadThread().stopThread();
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					log.error("Error in closing stream.");
				}
			}
		}			
	}
	
	/**
	 * Sets a message to be sent to this client and notifyies the waiting thread.
	 * @param messageToSend The message.
	 */
	public synchronized void setMessage(Object messageToSend) {
		message = messageToSend;
		notify();		
	}
	
	/**
	 * Sets interuption flag so this thread to stop and exits.
	 */
	public void stopThread() {
		this.interrupt();
	}
}
