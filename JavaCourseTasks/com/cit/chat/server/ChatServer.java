package com.cit.chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Vector;
import org.apache.log4j.*;

import com.cit.chat.common.IClientMessages;
import com.cit.chat.common.IServerMessages;

/**
 * This class realizes the base logic for this chat server and 
 * the connection between the reader/writer threads and the gui.
 * 
 * @author Svilen Velikov
 * @version
 */
public class ChatServer {
	
	/**  */
	private ChatServer chatServer;
	
	/** Holds a list with the connected clients. */
	private Vector connectedClients;

	/** Holds a list with the connected clients nicknames. */
	private Vector nickNames;
	
	/** Backlog level. */
	private int backLog;
	
	/** The server's socket. */
	private ServerSocket serverSocket;
	
	/** The server's gui reference. */
	private ServerMainThread serverUI;
	
	/** An object that holds some data about a client. */
	private ConnectedClientData clientData;
	
	/** The client's socket. */
	private Socket clientSocket;
	
	/** A thread used to read messages from the client. */
	private ClientMessagesReadThread clientReaderThread;
	
	/** A thread used to send message to the client. */
	private SendMessageThread clientSendThread;
	
	/** The port number on which this server should run. */
	private int portNumber;
	
	/** The input stream. */
	private BufferedReader reader;
	
	/** The output stream. */
	private ObjectOutputStream writer;
	
	/** The messenger thread. */
	private Messenger messenger;
	
	/** The logger. */
	private Logger log = Logger.getLogger("ServerMainThread");
	
	
	/**
	 * Sets the port number and a reference to the gui then starts the server.
	 * @param gui  The reference to the servers gui.
	 * @param port The port number which is used to start the server.
	 */
	public ChatServer(ServerMainThread gui, int port) {
		serverUI   = gui;
		portNumber = port;
		chatServer = this;
		startServer();
	}

	/**
	 * Starts the server on the port taken from the gui. If starting is 
	 * succesfull a list for the clients is created. Then the messenger
	 * thread is created and started. Finally a client listener is started
	 * to accept clients.
	 */
	public void startServer() {
		try {
			serverSocket = new ServerSocket(
					portNumber, backLog, InetAddress.getByName("127.0.0.1"));
		} catch (BindException e1) {
			//serverSocket = null;
			serverUI.setWarnings("This port is in use. Try another one!", 1);
		} catch (SocketException e1) {
			serverUI.setWarnings("Can't open socket!", 1);
		} catch (IOException e1) {
			serverUI.setWarnings("Can't use this hostname!", 1);
		}
		
								
		if(serverSocket != null) {							//server is started
			connectedClients = new Vector();					
			nickNames        = new Vector();
			
			messenger = new Messenger(connectedClients);	
			messenger.start();								//start messenger
			
			Thread clientsListener = new Thread(cListener);	
			clientsListener.start();						//start a thread to listen for clients
			
			serverUI.prtMessage("SERVER IS STARTED\n"+" - PORT : "+portNumber);
			serverUI.switchCursor(2);						//default cursor
			serverUI.disableStartButton();
		} else {											//start is failure
			log.error("Error in starting server.");
			serverUI.enableStartButton();					
			serverUI.switchCursor(2);
		}
	}
	
	/**
	 * Stops this server.
	 */
	public void stopServer() {	
		try {	
			messenger.setMessage(null);
			messenger.stopMessenger();
			
			if (serverSocket   != null) {
				serverSocket.close();
			}
			if (clientSocket != null) {
				clientSocket.close();
			}
			serverSocket = null;
			clientSocket = null;
/*			connectedClients.clear();
			connectedClients = null;
			nickNames.clear();
			nickNames        = null;*/
			serverUI.prtMessage("SERVER IS STOPPED");
			log.info("The server was stopped.");
		} catch (IOException e) {
			serverUI.setWarnings("Error in closing sockets!", 1);
		}
	}
	
	/**
	 * Accepts clients while not interrupted. Once a client is accepted
	 * it has to follow the protocol so to get permission to enter in 
	 * chat.
	 */	
 	Runnable cListener = new Runnable() {		
		public void run() {	
			while (true) {
				try {
					clientSocket = serverSocket.accept();
				} catch (SocketException e) {
					break;
				} catch (IOException e) {
					break;
				}
				if(clientSocket != null) {
					checkConnectionRequest();					
				}
			}
			log.error("Client listener thread failed.");
		}
	};
	
	/**
	 * Checks the connection request and the nickname provided by user.
	 * The if there are more than one users online a clients list is sent
	 * to the user just connected. To the others is sent only the nickname
	 * of this last connected user.
	 */
	private void checkConnectionRequest() {			
		if(createStreams()) {										//streams are created
			try {												
				String connAttempt = reader.readLine();				//read connection request
				String command 	   = splitMessage(connAttempt, 1);	
				String nick	       = splitMessage(connAttempt, 2);
				
				if(IClientMessages.CONNECTION_ATTEMPT.equals(command)) {			//check command
					
					if(isValidNickname(nick)) {						//if nickname is valid and doesn't exists
						
						clientData = new ConnectedClientData();		//create connected client dataobject
						
						writer.writeObject(IServerMessages.NICK_OK+"|"+nick);		//nick is ok send that to client
						
						clientData.setNickname(nick);					//add the nickname in the data object
						clientData.setSocketToClient(clientSocket);	//add the client's socket to the data object
						
						//create the reader thread for this client
						clientReaderThread = new ClientMessagesReadThread(
								chatServer, serverUI, clientData, reader);
						clientData.setClientReadThread(clientReaderThread);					
						clientReaderThread.start();					//start the thread
						serverUI.prtMessage("ClientMessageReadThread is started for #"+nick);
						
						//create the writer thread for this client
						clientSendThread = new SendMessageThread(
								chatServer, serverUI, clientData, writer);
						clientData.setMessageSendThread(clientSendThread);		
						clientSendThread.start();					//start the thread		
						
						if(connectedClients.size() != 0) {			//there are more than one user online
							writer.writeObject(nickNames);
							writer.flush();
							populateMessage(IServerMessages.CLIENT_CONNECTED+"|"+nick);
							serverUI.prtMessage("Sent [CLIENT_ACCEPTED] to online users...");
						}
						
						serverUI.prtMessage("Client#" + nick + " accepted...");
						serverUI.prtMessage("#"+nick+" was added to the clients list...");
						
						addNewClient(clientData, nick);				//add the new client to the lists
					} else {
						writer.writeObject(IServerMessages.INVALID_NICK+"|"+nick);
						closeStreams();
					}
				} else {
					serverUI.prtMessage("Wrong connection request!");
					log.warn("An invalid connection request detected!");
					closeStreams();
				}
			} catch (IOException e) {
				serverUI.prtMessage("Error while check connection request!");
				closeStreams();
			}
		}			
	}

	/**
	 * Creates input and output streams to the client that has just connected.
	 * @return true if the streams are succesfully created.
	 */
	private boolean createStreams() {
		boolean haveStreams = true;
		try {
			reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			writer = new ObjectOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			serverUI.prtMessage("Can't create streams to client!");
			log.error("Can't create sterams to client!");
			haveStreams = false;
		}
		return haveStreams;
	}
	
	/**
	 * Tries to close the streams.
	 */
	private void closeStreams() {
		try {
			if(reader != null)
				reader.close();
			if(writer != null)
				writer.close();
		} catch(IOException e) {
			serverUI.prtMessage("Error in closing streams!");
		}
	}
	
	/**
	 * Splits the message and returns the requested part of it.
	 * @param message The message to be splited.
	 * @param i		  The index that shows which part to return
	 * @return The part from the message that is requested.
	 */
	private String splitMessage(String message, int i) {
		String[] separateMsg = message.split("\\|");
		String command = separateMsg[0];
		String text    = separateMsg[1];
		if(i == 1) {
			return command;
		} else {
			return text;
		}
	}
	
	/**
	 * Checks if the nickname read from the stream is a valid one.
	 * @param nickname The nickname to check.
	 * @return True if the nickname is valid.
	 */
	public boolean isValidNickname(String nickname) {
		boolean isValid = false;
		if(!(nickname.contains("[")) || (!nickname.contains("]")) 
				|| (!"".equals(nickname)) || (nickname.length() <= 15)) {
			if(!nickNames.contains(nickname)) {
				isValid = true;
			}
		}
		return isValid;
	}
	
	/**
	 * Adds the client just connected to the list of connected clients and its
	 * nickname to the nicknames list.
	 * @param newConnectedClient The client's data.
	 * @param nick				 The nickname for the client
	 */
	public void addNewClient(ConnectedClientData newConnectedClient, String nick) {
		synchronized (connectedClients) {
			connectedClients.add(newConnectedClient);
			nickNames.add(nick);			
		}
	}
	
	/**
	 * Removes a client from the list if it is in that list.
	 * @param aClient The client to be disconnected.
	 * @return True if this client exists and is succesfully removed.
	 */
	public boolean disconnectClient(ConnectedClientData aClientDisconnected) {
		synchronized (connectedClients) {
			boolean isRemoved = false;
			String nick = aClientDisconnected.getNickname();
			if(nickNames.contains(nick)) {
				nickNames.remove(nick);
				connectedClients.remove(aClientDisconnected);
				populateMessage(IServerMessages.CLIENT_DISCONNECTED+"|"+nick);
				serverUI.prtMessage("Sent [CLIENT_DISCONNECTED] to online users...");
				isRemoved = true;
			}
			return isRemoved;
		}
	}
	
	/**
	 * Asks the messenger to send the message to all connected clients.
	 */
	public void populateMessage(Object messageToPopulate) {
		messenger.setMessage(messageToPopulate);
	}
}
