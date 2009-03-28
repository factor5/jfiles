package com.cit.chat.client;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import org.apache.log4j.*;

import com.cit.chat.common.IClientMessages;

/**
 * ChatClient realizes the base logic for the client and the connection
 * between the read/write threads and the gui.
 * 
 * @author Svilen Velikov
 * @version
 */
public class ChatClient {
	
	/** The reader thread. */
	private ServerMessagesReadThread readThread;
	
	/** A flag to show whether this client is connected. */
	private boolean isConnected = false;
	
	/** A reference to the gui for the client. */
	private ClientMainFrame chatWindow;
	
	/** The sender thread. */
	private ClientMessageSendThread sendThread;
	
	/** A list with connected clients. */
	private Vector connectedClients;
	
	/** The servers socket. */
	private Socket socketToServer;	
	
	/** The login form gui. */
	private LoginForm login;
	
	/** The nickname for this client. */
	private String nickname;
	
	/** The logger. */
	private Logger log = Logger.getLogger("ChatClient");
	
	
	/**
	 * Constructor for this chat client. It takes only a reference
	 * to the login form.
	 * @param loginForm A reference to the login form. 
	 */
	public ChatClient(LoginForm loginForm) {
		login = loginForm;
	}

	/**
	 * Makes attempt to connect to server at given host. It iterates through
	 * the allowed ports to find if the server is started on different from
	 * default port 7000.
	 * @param host The host on which is the server.
	 * @param nick The nickname for this user.
	 * @return	Whether the connection attempt is succesfull.
	 */
	public boolean connectToServer(String host, String nick) {
		nickname = nick;
		int port = 7000;
		
		socketToServer = new Socket();							//create an unconnected socket to server
		while(!socketToServer.isConnected()) {
			try {	
				socketToServer.connect(new InetSocketAddress(	//try to connect to server
						InetAddress.getByName(host), port), 15000);
			} catch (UnknownHostException e1) {
				log.warn("Unknown host name provided by user.");
				login.setWarnings("Unknown host!", 1);
				socketToServer = null;
				break;
			} catch (ConnectException e1) {
					
			} catch (NoRouteToHostException e1) {
				log.warn("Connection timeout. NoRoot to host.");
				login.setWarnings("Connection timeout. Try again later!", 1);
				break;
			} catch (IOException e1) {
				socketToServer = new Socket();					//create new socket to try again...should change this
				port++;											//increment the port number
				if(port == 7005) {								//can't find server on all the allowed ports
					login.setWarnings(" Can't find chat server!\n Maybe its not started!", 1);
					socketToServer = null;
					break;
				}
			}			
		}
		
		isConnected = false;
		
		if(socketToServer != null) {
			log.info("User connected to server.");
			
			chatWindow       = new ClientMainFrame(this, nickname);	 //create chat window
			connectedClients = new Vector();						 //create list with connected users
			isConnected      = true;								 //?!?!?!?connected to server
			
			try {
				//create and start reader and writer thread
				sendThread = new ClientMessageSendThread(this);	
				readThread = new ServerMessagesReadThread(this, chatWindow);
				
				sendThread.start();		
				readThread.start();
		
				//sendThread.setMessage("CONNECTION_ATTEMPT|"+nick);	//send login request
				sendThread.setMessage(IClientMessages.CONNECTION_ATTEMPT+"|"+nick);	//send login request
			} catch (IOException e) {
				login.setWarnings("Can't create streams to server!", 1);
			}
		} else {
			log.info("Fails to connect to server.");
			isConnected = false;									//fails to connect to server
		}
		return isConnected;
	}

	/**
	 * Sends the message written from user to server. Message is first
	 * checked for line feeds and for blank fields. Then the first letter
	 * is turned out to capital.
	 * @param message The message written from user.
	 */
	public void sendMessage(String message) {
		String editedMessage = message.replaceAll("\n", " ");
		editedMessage = editedMessage.trim();
		if(!"".equals(editedMessage)) {
			//editedMessage = "CLIENT_MESSAGE|"+
					//(editedMessage.substring(0, 1).toUpperCase())+editedMessage.substring(1);
			editedMessage = IClientMessages.CLIENT_MESSAGE+"|"+
					(editedMessage.substring(0, 1).toUpperCase())+editedMessage.substring(1);			
			sendThread.setMessage(editedMessage);				
		}
	}
	
	/**
	 * Sends disconnection request to server and shutdown the client.
	 */
	public void sendDisconnectionRequest() {
		//sendThread.setMessage("DISCONNECT_CLIENT|"+nickname);
		sendThread.setMessage(IClientMessages.DISCONNECT_CLIENT+"|"+nickname);
		log.info("Client is disconnected from server.");
		try {
			Thread.sleep(1000);			//need some time to send message before shutdown
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		stopClient();
		displayLogin();
	}

	/**
	 * Saves the users online list and ask the gui to display this list.
	 * @param usersOnline The online users list.
	 */
	public void setClientsList(Vector usersOnline) {
		connectedClients = usersOnline;
		chatWindow.displayOnlineUsers(connectedClients);
	}
	
	/**
	 * Adds the new connected client to the list and ask the gui to 
	 * display its nick in the window.
	 * @param newConnectedClient The nickname of the new client.
	 */
	public synchronized void setClient(String newConnectedClient) {
		connectedClients.add(newConnectedClient);
		chatWindow.addClientToList(newConnectedClient);
	}
	
	/**
	 * Removes client from the list and reset chat window.
	 * @param disconnectedClient The client that is disconnected.
	 */
	public synchronized void removeClient(String disconnectedClient) {
		connectedClients.remove(disconnectedClient);
		chatWindow.resetClientsList();
		setClientsList(connectedClients);
	}
	
	/**
	 * Displays a message that the nickname is invalid and
	 * enables the login form.
	 */
	public void backToLogin() {
		login.setWarnings("Nickname is in use or invalid!", 2);
		login.enableLogin(true);
	}
	
	/**
	 * Closes the login form and shows the chat window.
	 */
	public void logInChat() {
		login.showLoginForm(false);
		login.dispose();
		chatWindow.showChatWindow(true);
	}
	
	/**
	 * Displays the login form and closes the chat window.
	 */
	public void displayLogin() {
		login.enableLogin(true);
		login.showLoginForm(true);
		chatWindow.showChatWindow(false);
	}
	
	/**
	 * Stops the reader and writer threads and closes the socket.
	 */
	public void stopClient() {
		readThread.stopThread();
		sendThread.stopThread();
		try {
			if(socketToServer != null) {
				socketToServer.close();
			}
		} catch (IOException e) {
			chatWindow.setWarnings("Error in closing!", 1);
		}
	}
	
	/**
	 * Returns the servers socket.
	 * @return
	 */
	public Socket getServerSocket() {
		return socketToServer;
	}
	
	/**
	 * Return the connected status for this client.
	 * @return
	 */
	public boolean getIsConnected() {
		return isConnected;
	}
}