package com.cit.jcourse.svelikov.theme7.helloserver;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * This class represents a simple Hello server with GUI window.
 * It works on localhost using a fixed port number. It waits for
 * client to connect and then writes to the created socket a Hello
 * message plus system date. In the window are printed messages for
 * all the activityes of the server.
 *  
 * @author Svilen Velikov
 * @version 04.07.07
 */
public class HelloServer extends JFrame implements ActionListener {

	private static final long serialVersionUID = -2235677367820854132L;
	private JTextArea msgField;
	private JButton stopServer;
	
	/** The port on witch the server listens for client. */
	private int portNumber;
	/** New line constant used in msgField. */
	private static final String NEW_LINE = "\n";
	/** The ServerSocket object. */
	private ServerSocket serverSocket;
	/** The Socket object. */
	private Socket socket;
	/** The PrintWriter used to send message to the client. */
	private PrintWriter writer;
	
	/**
	 * Constructs the GUI.
	 * @param port The port on witch to listen for client.
	 */
	public HelloServer(int port) {
		portNumber = port;
		
		msgField = new JTextArea(10, 20);
		msgField.setEnabled(false);
		msgField.setDisabledTextColor(new Color(0,0,0));
		
		stopServer = new JButton("STOP SERVER");
		stopServer.addActionListener(this);
		
		setTitle("Hello Server");
		setSize(200, 100);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().add(msgField, BorderLayout.WEST);
		getContentPane().add(stopServer, BorderLayout.NORTH);
		
		pack();
		setVisible(true);
		setLocation(400, 100);
	}

	/**
	 * Starts the server and waits for client to connect.
	 * When it does, a Hello message is written to the stream.
	 */
	public void startServer() {
       
		try {
			serverSocket = new ServerSocket(portNumber, 0, InetAddress.getLocalHost());
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(this,
					"Can't open socket!",
					"Server error",
					JOptionPane.ERROR_MESSAGE);	
		}
		if(serverSocket != null) {
			try {
				prtMessage("Server started...");
				socket = serverSocket.accept();
				prtMessage("Client accepted...");
				writer = new PrintWriter(
						socket.getOutputStream(), true); 
				Calendar date = Calendar.getInstance();
				writer.println("Hello "+date.get(Calendar.DATE)+
								"/"+date.get(Calendar.MONTH)+
								"/"+date.get(Calendar.YEAR));
				prtMessage("Message sent...");
			} catch (IOException e) {} 
		}
	}

	/**
	 * Handles the event fired from the ActionListener when the
	 * stopServer button is pressed.
	 */
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == stopServer) {
			if(writer != null) 
				writer.close();		
			try {
				if(socket != null)
					socket.close();
				if(serverSocket != null)
					serverSocket.close();
				prtMessage("Server was stopped!");
				stopServer.setEnabled(false);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this,
						"Error in closing sockets!",
						"Server error",
						JOptionPane.ERROR_MESSAGE);
			}	
			//dispose();
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
