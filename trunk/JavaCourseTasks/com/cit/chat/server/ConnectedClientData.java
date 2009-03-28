/**
 * 
 */
package com.cit.chat.server;

import java.net.Socket;

/**
 * This is an data holder class.
 * @author Svilen Velikov
 * @version
 */
public class ConnectedClientData {
	
	/** ClientMessagesReadThread reference. */
	private ClientMessagesReadThread clientReadThread;

	/** SendMessageThread reference. */
	private SendMessageThread 	   	messageSendThread;

	/** The client's socket. */
	private Socket 				    socketToClient;

	/** The client's nickname. */
	private String					nickname;

	public ClientMessagesReadThread getClientReadThread() {
		return clientReadThread;
	}

	public void setClientReadThread(ClientMessagesReadThread clientReadThread) {
		this.clientReadThread = clientReadThread;
	}

	public SendMessageThread getMessageSendThread() {
		return messageSendThread;
	}

	public void setMessageSendThread(SendMessageThread messageSendThread) {
		this.messageSendThread = messageSendThread;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Socket getSocketToClient() {
		return socketToClient;
	}

	public void setSocketToClient(Socket socketToClient) {
		this.socketToClient = socketToClient;
	}
}
