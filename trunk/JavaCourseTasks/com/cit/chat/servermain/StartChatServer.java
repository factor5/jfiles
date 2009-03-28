package com.cit.chat.servermain;

import com.cit.chat.server.ServerMainThread;

/**
 * Used to start the chat server.
 * @author Svilen Velikov
 * @version
 */
public class StartChatServer {
	
	/**
	 * Creates an instance for the ServerMainThread that is the 
	 * entry point for the server.
	 * @param args
	 */
	public static void main(String[] args) {
		ServerMainThread smt = new ServerMainThread();
	}
}
