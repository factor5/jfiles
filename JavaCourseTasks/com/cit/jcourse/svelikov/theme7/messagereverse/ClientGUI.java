/**
 * 
 */
package com.cit.jcourse.svelikov.theme7.messagereverse;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * The gui that stands over the client program. It contains a
 * message field to show messages from the server and a button
 * that is used to connect to the server.
 * 
 * @author Svilen Velikov
 * @version 2007-7-25
 */
public class ClientGUI  extends JFrame implements ActionListener, DocumentListener {

	private static final long serialVersionUID = 4474359088145305115L;
	
	private JTextArea receivedMessages;
	private JTextArea typedMessages;
	private JButton bSendMessage;
	private JButton bConnect;	
	private JScrollPane scrollPaneReceived;
	private JScrollPane scrollPaneTyped;
	
	/** New line constant used in msgField. */
	private static final String NEW_LINE = "\n";
	
	/** The client object created trough this gui. */
	private Client client;
	
	/**
	 * Constructor that invokes method to create this gui.
	 */
	public ClientGUI() {
		createGUI();
	}
	
	/**
	 * Creates and shows the gui.
	 */
	public void createGUI() {
		receivedMessages = new JTextArea(10, 30);
		receivedMessages.setEditable(false);
		
		typedMessages = new JTextArea(10, 30);
		typedMessages.setEditable(true);
		typedMessages.setEnabled(false);
		typedMessages.getDocument().addDocumentListener(this);
		
		scrollPaneReceived = new JScrollPane(receivedMessages);
		scrollPaneTyped    = new JScrollPane(typedMessages);
		
		bSendMessage = new JButton("SEND");
		bSendMessage.setActionCommand("send");
		bSendMessage.setEnabled(false);
		bSendMessage.addActionListener(this);

		bConnect = new JButton("CONNECT TO SERVER");
		bConnect.setActionCommand("connect");
		bConnect.addActionListener(this);
		
		setTitle("Client");
		setSize(200, 200);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().add(scrollPaneReceived, BorderLayout.NORTH);
		getContentPane().add(scrollPaneTyped, BorderLayout.SOUTH);
		getContentPane().add(bSendMessage, BorderLayout.AFTER_LINE_ENDS);
		getContentPane().add(bConnect, BorderLayout.BEFORE_LINE_BEGINS);

		pack();
		setVisible(true);
		setLocation(300, 300);
	}
	
	/**
	 * Handles the event fired from the ActionListener when the stopServer
	 * button is pressed. If connect is pressed the client is created.
	 * When the send button is pressed the text typed in console is sent
	 * to client and it is woken up to deal with it. Then the console is 
	 * cleared.
	 */
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand() == "connect") {
			bConnect.setEnabled(false);
			client = new Client(this);
			client.start();
		} else if (ae.getActionCommand() == "send") {
			client.checkText(typedMessages.getText());
			typedMessages.setText("");
			bSendMessage.setEnabled(false);
			client.wakeUp();
		}
	}
	
	/**
	 * Enables the connect button if needed.
	 */
	public void enableConnectButton() {
		bConnect.setEnabled(true);
	}
	
	/**
	 * Enables console so the user can write in it.
	 */
	public void setConsole(boolean b) {
		typedMessages.setEnabled(b);
	}
	
	/**
	 * Prints messages in the text field.
	 * @param msg The message that should be printed.
	 */
	public void prtMessage(String msg) {
		receivedMessages.append(msg + NEW_LINE);
	}

	/**
	 * Shows warning windows with messages according the parameters.
	 * @param warningMessage The message that should be displayed.
	 * @param msgType        The type of the window to be shown.
	 */
	public void setWarnings(String warningMessage, int msgType) {
		switch (msgType) {
			case 1:JOptionPane.showMessageDialog(
					this, warningMessage, "Error",
					JOptionPane.ERROR_MESSAGE);
					break;
			case 2:JOptionPane.showMessageDialog(
					this, warningMessage, "Information",
					JOptionPane.INFORMATION_MESSAGE);
					break;
		}
	}
	
	/** 
	 * Enables send button if there is any input in the 
	 * console.
	 */
	public void insertUpdate(DocumentEvent arg0) {
		if(!"".equals(typedMessages.getText())) {
			bSendMessage.setEnabled(true);
		}
	}

	public void changedUpdate(DocumentEvent arg0) {}
	public void removeUpdate(DocumentEvent arg0) {}
}

