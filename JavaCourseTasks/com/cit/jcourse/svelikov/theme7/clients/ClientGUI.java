/**
 * 
 */
package com.cit.jcourse.svelikov.theme7.clients;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * The gui that stands over the client program. It contains a
 * message field to show messages from the server and a button
 * that is used to connect to the server.
 * 
 * @author Svilen Velikov
 * @version 2007-7-20
 */
public class ClientGUI  extends JFrame implements ActionListener {

	private static final long serialVersionUID = -6588131873294178420L;
	private JTextArea msgField;
	private JButton bConnect;
	private JScrollPane scrollPane;

	/** New line constant used in msgField. */
	private static final String NEW_LINE = "\n";
	
	/** The client. */
	private Client client;
	
	/**
	 * Constructor invokes the method to create and
	 * show the gui.
	 */
	public ClientGUI() {
		createGUI();
	}
	
	/**
	 * Creates the gui for this client.
	 */
	public void createGUI() {
		msgField = new JTextArea(10, 20);
		msgField.setEnabled(false);
		msgField.setDisabledTextColor(new Color(0, 0, 0));

		scrollPane = new JScrollPane(msgField);

		bConnect = new JButton("CONNECT");
		bConnect.setActionCommand("connect");
		bConnect.addActionListener(this);

		setTitle("Client Window");
		setSize(200, 100);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().add(scrollPane, BorderLayout.WEST);
		getContentPane().add(bConnect, BorderLayout.NORTH);

		pack();
		setVisible(true);
		setLocation(400, 350);
	}
	
	/**
	 * Handles the event fired from the ActionListener when the connect
	 * button is pressed. The button is disabled and a client thread is
	 * started.
	 */
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand() == "connect") {
			bConnect.setEnabled(false);
			client = new Client(this);
			client.start();
		}	
	}
	
	/**
	 * Enables the connect button if needed.
	 */
	public void enableConnectButton() {
		bConnect.setEnabled(true);
	}
	
	/**
	 * Prints messages in the text field.
	 * @param msg The message that should be printed.
	 */
	public void prtMessage(String msg) {
		msgField.append(msg + NEW_LINE);
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
}
