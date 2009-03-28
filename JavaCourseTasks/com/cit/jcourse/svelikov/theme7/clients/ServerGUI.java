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
 * The gui that stands over the server. It has just a message field
 * to show the activity of the server and a button used to stop the
 * server.
 * 
 * @author SvilenVelikov
 * @version 2007-7-20
 */
public class ServerGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = -249675395974648432L;
	private JTextArea msgField;
	private JButton actionButton;
	private JScrollPane scrollPane;

	/** New line constant used in msgField. */
	private static final String NEW_LINE = "\n";
	
	/** The server object. */
	private Server server;

	/**
	 * Constructs and shows the gui and starts the server.
	 */
	public ServerGUI() {	
		
		msgField = new JTextArea(10, 20);
		msgField.setEnabled(false);
		msgField.setDisabledTextColor(new Color(0, 0, 0));

		actionButton = new JButton("STOP SERVER");
		actionButton.setActionCommand("stop");
		actionButton.addActionListener(this);

		scrollPane = new JScrollPane(msgField);
		
		setTitle("Server");
		setSize(200, 100);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().add(scrollPane, BorderLayout.WEST);
		getContentPane().add(actionButton, BorderLayout.NORTH);

		pack();
		setVisible(true);
		setLocation(400, 100);
		
		server = new Server(this);
		server.startServer();
	}
	
	/**
	 * Handles the event fired from the ActionListener when the
	 * stopServer button is pressed.
	 */
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand() == "stop") {
			server.stopServer();
			actionButton.setEnabled(false);
		}
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
	 * @param msgType		 The type of the window to be shown.
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
