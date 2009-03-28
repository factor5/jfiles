package com.cit.chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

import org.apache.log4j.*;

import com.cit.chat.common.IClientMessages;
import com.cit.chat.common.IServerMessages;

/**
 * A thread that is started for every separate client and deals with
 * the incoming messages.
 * 
 * @author Svilen Velikov
 * @version
 */
public class ClientMessagesReadThread extends Thread {

	/** The server's reference. */
	private ChatServer server;

	/** Socket to client. */
	private Socket clientSocket;

	/** The client's data holder. */
	private ConnectedClientData aClient;

	/** The input stream. */
	private BufferedReader reader;

	/** The server's gui. */
	private ServerMainThread serverUI;

	/** The message read from the user. */
	private String message;

	/** The nick for this client. */
	private String nick;

	/** The logger. */
	private Logger log = Logger.getLogger("ServerMainThread");

	
	/**
	 * Initializes some fields and gets the input stream from the data object.
	 * @param server The server's reference.
	 * @param gui	 The gui's reference.
	 * @param client The client dataobject reference.
	 * @throws IOException If can't create the input stream.
	 */
	public ClientMessagesReadThread(ChatServer server, ServerMainThread gui,
			ConnectedClientData client, BufferedReader reader)
			throws IOException {
		this.server = server;
		this.reader = reader;
		aClient = client;
		serverUI = gui;
		nick = client.getNickname();
	}

	/**
	 * Reads from the stream and executes the commends in the loop untill
	 * client choose to disconnect from server.
	 */
	public void run() {
		try {
			while (!isInterrupted()) {
				message = reader.readLine();
				if (message != null) {
					executeCommand();
				}
			}
		} catch (IOException e) {
			log.error("Error while reading from client.");
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (clientSocket != null) {
					clientSocket.close();
				}
				server.populateMessage(IServerMessages.CLIENT_DISCONNECTED+"|"
						+ aClient.getNickname());
				server.disconnectClient(aClient);
				aClient.getMessageSendThread().stopThread();
			} catch (IOException e) {
				log.error("Error in closing socket/stream.");
			}
		}
	}

	/**
	 * Executes the commands read from the client.
	 */
	private void executeCommand() {
		String[] separateMsg = message.split("\\|");
		String command = separateMsg[0];
		String text = separateMsg[1];

		if (IClientMessages.DISCONNECT_CLIENT.equals(command)) {
			//ask server to remove client from lists and to notice the others
			if (server.disconnectClient(aClient)) {
				aClient.getMessageSendThread().stopThread(); 	//stop writer thread
				serverUI.prtMessage("Client#" + text + " disconnected...");
				stopThread(); 								//ask this thread to stop
			} else {
				serverUI.prtMessage("An attempt to remove an unexisting client is detected!");
				log.warn("An attempt to remove an unexisting client is detected!");
			}
		}
		
		if (IClientMessages.CLIENT_MESSAGE.equals(command)) {
			//received message from user that should be validated first
			if (checkMessage(text)) {
				server.populateMessage(IServerMessages.MESSAGE+"|[" + nick + "]:" + text);//populate the message				
			} else {
				serverUI.prtMessage("Invalid message is received!");
				log.warn("Invalid message is received!");
			}
		}
	}

	/**
	 * Checks whether the message received from user is valid.  
	 * @param receivedMessage The message.
	 * @return true if message is ok and message can be populated.
	 */
	private boolean checkMessage(String receivedMessage) {
		boolean isValid = true;
		if ("".equals(receivedMessage) || (receivedMessage.length() > 200)) {
			isValid = false;
		}
		return isValid;
	}

	/**
	 * Sets the flag that says this thread should stop.
	 */
	public void stopThread() {
		this.interrupt();
	}
}
