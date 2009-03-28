package com.cit.chat.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Vector;
import org.apache.log4j.*;

import com.cit.chat.common.IClientMessages;
import com.cit.chat.common.IServerMessages;

/**
 * A thread that reads from the stream server's messages and executes
 * the commands from that messages.
 * 
 * @author Svilen Velikov
 * @version
 */
public class ServerMessagesReadThread extends Thread {

	/** The chat client. */
	private ChatClient client;
	
	/** The chat gui. */
	private ClientMainFrame chatUI;
	
	/** The socket to server. */
	private Socket socketToServer;
	
	/** Reader */
	private ObjectInputStream reader;
	
	/** Message read from server. */
	private Object message;
	
	/** The connected users list. */
	private Vector usersList;
	
	/** A flag which says that this thread should stop. */
	private boolean isStopped;
	
	/** The logger. */
	private Logger log = Logger.getLogger("ServerMessagesReadThread");
	
	
	/**
	 * Initializes this thread and creates the input stream.
	 * @param cl		The chat client.
	 * @param loginUI	The login form.
	 * @throws IOException If fails to create stream.
	 */
	public ServerMessagesReadThread(ChatClient cl, ClientMainFrame cmf) throws IOException {
		client  = cl;
		chatUI = cmf;
		socketToServer = client.getServerSocket();
		reader = new ObjectInputStream(socketToServer.getInputStream());
	}
	
	/**
	 * Reads from the stream while not asked to stop or null is read.
	 */
	public void run() {
		try {
			while(!isStopped) {
				message = (Object) reader.readObject();
				if(message != null) {
					
					//a message is sent from the server
					if (message instanceof String) {
						String textMessage = (String) message;	
						executeCommand(textMessage);
						
					//a clients list is sent from server
					} else {	
						usersList = (Vector) message;
						client.setClientsList(usersList);
					}													
				} else {
					chatUI.setWarnings("  Server is down!\n Try to reconnect later!", 2);
					break;
				}
			}
		} catch (IOException e) {
			//chatUI.setWarnings("Disconnected from server!", 2);
			log.info("Disconnected from server!");
		} catch (ClassNotFoundException e) {
			chatUI.setWarnings("Error! Restart chat!", 1);
		} finally {
			try {
				if(reader != null) {
					reader.close();
				}
				if(socketToServer != null) {
					socketToServer.close();
				}
				client.displayLogin();
			} catch (IOException e) {
				log.error("Error in closing streams/sockets.", e);
			}		
		}		
	}
	
	/**
	 * Executes the commands that incomes from server.
	 * @param textMessage The message read from server.
	 */
	private void executeCommand(String textMessage) {
		String[] separateMsg = textMessage.split("\\|");
		String command = separateMsg[0];
		String text    = separateMsg[1];
		
		if(IServerMessages.NICK_OK.equals(command)) {					//it's allowed to enter in chat
			client.logInChat();
		} else if(IServerMessages.INVALID_NICK.equals(command)) {		//nickname is occupied
			client.backToLogin();
		} else if(IServerMessages.MESSAGE.equals(command)) {			//message is read and should be displayed
			chatUI.displayMessage(text);
		} else if(IServerMessages.CLIENT_CONNECTED.equals(command)) {	//new user is connected
			client.setClient(text);
		} else if(IServerMessages.CLIENT_DISCONNECTED.equals(command)) {//a user is disconnected
			client.removeClient(text);
		} 
	}
	
	/**
	 * Sets the interruption flag.
	 */
	public void stopThread() {
		isStopped = true;
	}
}
