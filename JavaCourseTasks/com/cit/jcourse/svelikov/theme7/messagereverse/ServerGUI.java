
package com.cit.jcourse.svelikov.theme7.messagereverse;

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
 * 
 */
public class ServerGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 7823874676215205540L;
	private JTextArea msgField;
	private JButton bStart;
	private JButton bStop;
	private JScrollPane scrollPane;

	/** New line constant used in msgField. */
	private static final String NEW_LINE = "\n";
	
	/** The server object. */
	private Server server;

	/**
	 * Invokes the method that creates the gui.
	 */
	public ServerGUI() {	
		createGUI();
	}
	
	/**
	 * Creates and shows the gui.
	 */
	private void createGUI() {
		msgField = new JTextArea(10, 30);
		msgField.setEnabled(false);
		msgField.setDisabledTextColor(new Color(0, 0, 0));
		
		bStart = new JButton("START SERVER");
		bStart.setActionCommand("start");
		bStart.addActionListener(this);

		bStop = new JButton("STOP SERVER");
		bStop.setActionCommand("stop");
		bStop.setEnabled(false);
		bStop.addActionListener(this);

		scrollPane = new JScrollPane(msgField);
		
		setTitle("Server");
		setSize(200, 100);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().add(scrollPane, BorderLayout.SOUTH);
		getContentPane().add(bStop, BorderLayout.AFTER_LINE_ENDS);
		getContentPane().add(bStart, BorderLayout.BEFORE_LINE_BEGINS);

		pack();
		setVisible(true);
		setLocation(300, 50);
	}
	
	/**
	 * Handles the event fired from the ActionListener when the
	 * stop/start buttons are pressed.
	 */
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand() == "stop") {
			server.stopServer();
			bStop.setEnabled(false);
			bStart.setEnabled(true);
		} else if (ae.getActionCommand() == "start") {
			server = new Server(this);
			server.startServer();		//start the server
			bStart.setEnabled(false);
			bStop.setEnabled(true);
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
	 * Enables the start button.
	 */
	public void enableStartButton() {
		bStop.setEnabled(false);
		bStart.setEnabled(true);
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
