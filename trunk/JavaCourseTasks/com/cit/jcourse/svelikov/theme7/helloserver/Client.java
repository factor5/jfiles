package com.cit.jcourse.svelikov.theme7.helloserver;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * This class represents a simple client that connects to the Hello
 * server working on localhost and reads the message sent from the 
 * server. The message and all other activityes are printed in GUI
 * window.
 * 
 * @author Svilen Velikov
 * @version 04.07.07
 */
public class Client extends JFrame {

	private static final long serialVersionUID = 787881622153773060L;

	private JTextArea msgField;
	
	/** The port on witch client tryes to connect. */
	private int portNumber;
	/** New line constant used in msgField. */
	private static final String NEW_LINE = "\n";
	/** The Socket object. */
	private Socket socket;
	/** The reader used to read message from the server. */
	private BufferedReader reader;
	
	/**
	 * Constructs the GUI.
	 * @param port The port on witch to connect to server.
	 */
	public Client(int port) {
		portNumber = port;
		
		msgField = new JTextArea(10, 20);
		msgField.setEnabled(false);
		msgField.setDisabledTextColor(new Color(0,0,0));
		
		setTitle("Client");
		setSize(200, 100);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		getContentPane().add(msgField, BorderLayout.WEST);
		
		pack();
		setVisible(true);
		setLocation(400, 350);
	}
	
	/**
	 * Creates a socket object on the same host and port as the server. 
	 * Then creates the reader and reads the message from the server. 
	 * At the end it closes the connection to the server. The message 
	 * and all activityes of this client are printed to the window.
	 */
	public void startClient() {        
        try {
			socket = new Socket(InetAddress.getLocalHost(), portNumber );
        } catch (UnknownHostException e1) {
			JOptionPane.showMessageDialog(this,
					"Can't find server!",
					"Client error",
					JOptionPane.ERROR_MESSAGE);
			
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(this,
					"Can't create socket!",
					"Client error",
					JOptionPane.ERROR_MESSAGE);
		}
		if(socket != null) {
		    try {
		    	prtMessage("Connected to server...");
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String msg = reader.readLine();
				prtMessage("Message read...");
				prtMessage(msg);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this,
						"Can't read from socket!",
						"Client error",
						JOptionPane.ERROR_MESSAGE);
				
			}
			try {
				reader.close();
				socket.close();
				prtMessage("Connection closed...");
			} catch (IOException e) {} 
		}
	} 
 
	
	/**
	 * Prints messages in the text field.
	 * @param msg The message that should be printed.
	 */
	private void prtMessage(String msg) {
		msgField.append(msg + NEW_LINE);
	}
}
