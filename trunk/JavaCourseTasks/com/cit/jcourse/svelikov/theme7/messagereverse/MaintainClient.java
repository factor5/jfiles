/**
 * 
 */
package com.cit.jcourse.svelikov.theme7.messagereverse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Thread that serves a client. Both input and output streams
 * to the client are created in the constructor. Once the thread
 * is started it loops until client or server disconnects.
 * 
 * @author Svilen Velikov
 * @version 2007-7-24
 */
public class MaintainClient extends Thread {
	
	/**
	 * Initializes this thread.
	 * @param cs The clients socket.
	 * @param sg The gui object.
	 */
	public MaintainClient(Socket cs, ServerGUI sg) {
		gui = sg;
		client = cs;
		createStreams();
	}
	
	/** The clients socket. */
	private Socket client;
	
	/** The output stream. */
	private PrintWriter writer;
	
	/** The input stream. */
	private BufferedReader reader;
	
	/** The buffer to store the message from the client. */
	private StringBuffer initialMessage;
	
	/** The gui object. */
	private ServerGUI gui;
	
	/** Shows whether this thread should stop. */
	private boolean stopped;
	
	/**
	 * Asks the thread to stop. 
	 */
	public void setStopped() {
		stopped = true;
	}
	
	/**
	 * Creates the streams used to comunicate with the client.
	 */
	private void createStreams() {
		try {
			writer = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(
						client.getOutputStream())),true);
			reader = new BufferedReader(new InputStreamReader(
						client.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads a line from the client.
	 * @return The string read from the stream.
	 * @throws IOException If clients socket is disconnected.
	 */
	private String readClientMessage() throws IOException {
		String str = reader.readLine();
		return str;
	}
	
	/**
	 * Creates a StringBuffer object from the clients
	 * message thus it can use reverse() method. Then
	 * pass the reversed message back to client. 
	 * @param s The clients message.
	 */
	private void writeReversedMessage(String s) {
		initialMessage = new StringBuffer(s);
		initialMessage.reverse();
		writer.println(initialMessage.toString());
	}
	
	/**
	 * Invokes read and write methods in the loop untill
	 * client choose to disconnect from server.
	 */
	public void run() {
		String clientMessage;
		try {
			while(true) {
				clientMessage = readClientMessage();
				if(!stopped) {
					if(".".equals(clientMessage)) {
						gui.prtMessage("Client# "+client.hashCode()+" DISCONNECTED!");
						break;
					} else if(clientMessage == null) {
						gui.prtMessage("Can't read from client# "+client.hashCode()+
								". Maybe it is disconnected!");
						break;
					} else {
						gui.prtMessage("Client send : [ "+clientMessage+" ]");
						writeReversedMessage(clientMessage);
					}															
				} else {
					writer.println("disconnected");
					break;
				}
			}
		} catch (IOException e) {
			//gui.setWarnings("IO error!", 1);
		}
	}
	
	/**
	 * @return The output stream
	 */
	public PrintWriter getWriter() {
		return writer;
	}
	
	/**
	 * @return The input stream.
	 */
	public BufferedReader getReader() {
		return reader;
	}
	
	/**
	 * @return The client socket.
	 */
	public Socket getSocket() {
		return client;
	}
	
	/**
	 * Closes the output stream.
	 */
	public void closeWriter() {
		writer.close();
	}
	
	/**
	 * Closes the input stream.
	 * @throws IOException If any error occurs during 
	 * 					   the close operation.
	 */
	public void closeReader() throws IOException {
		reader.close();
	}
	
	/**
	 * Closes the client socket.
	 * @throws IOException If an error occurs during
	 * 					   the close operation.
	 */
	public void closeSocket() throws IOException {
		client.close();
	}
}
