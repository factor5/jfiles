package com.cit.chat.client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.apache.log4j.*;

/**
 * Realizes the gui for this chat client.
 * 
 * @author Svilen Velikov
 * @version
 */
public class ClientMainFrame extends javax.swing.JFrame implements ActionListener, DocumentListener {	
	private static final long serialVersionUID = -7700032460521039667L;
	
	private JTextArea   taClientsList;
	private JButton     bLogout;
	private JButton     bSend;
	private JTextArea   taInputMsg;
	private JTextArea   taReceivedMsg;
	private JScrollPane scrollClients;
	private JScrollPane scrollInput;
	private JScrollPane scrollReseiving;
	
	/** New line constant used in msgField. */
	private static final String NEW_LINE = "\n";
    
	/** A chat client reference. */
	private ChatClient client;
	
	/** The message ... */
	private String message;
	
	/** A flag says that the client is connected. */
	private boolean isConnected;
	
	/** The logger. */
	private Logger log = Logger.getLogger("ChatClient");
	
	
	/**
	 * Gets a reference to the chat and initializes this frame.
	 * @param cl   The chat client reference.
	 * @param nick The nickname for this client.
	 */
	public ClientMainFrame(ChatClient cl, String nick) {
		super(nick);
		client   = cl;
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		        initUI();
		        log.info("Chat window is displayed correctly.");
		    }
		});
	}
	
	/**
	 * Initializes this frame.
	 */
	private void initUI() {
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
				if(client.getIsConnected()) {
					client.sendDisconnectionRequest();
					System.exit(0);
				}
            }
        });
		try {
			GridBagLayout thisLayout = new GridBagLayout();
			thisLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.1};
			thisLayout.rowHeights = new int[] {40, 169, 28, 96, 20};
			thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
			thisLayout.columnWidths = new int[] {7, 7, 7, 7};
			getContentPane().setLayout(thisLayout);
			//setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			
			
			//logout button
			bLogout = new JButton();
			bLogout.setActionCommand("logout");
			getContentPane().add(bLogout, new GridBagConstraints(
					3, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, 
					GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0, 10), 0, 0));
			bLogout.setText("logout");
			bLogout.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			bLogout.addActionListener(this);
			
			
			//send message button that is disabled initially till user enters anything
			//in the input area
			bSend = new JButton();
			bSend.setActionCommand("send");
			getContentPane().add(bSend, new GridBagConstraints(
					2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, 
					GridBagConstraints.BOTH, new Insets(5, 0, 5, 0), 0, 0));
			bSend.setText("send");
			bSend.setBorder(BorderFactory.createCompoundBorder(
					null, BorderFactory.createBevelBorder(BevelBorder.RAISED)));
			bSend.addActionListener(this);
			bSend.setEnabled(false);
			
			
			//clients list area
			taClientsList = new JTextArea();
			scrollClients = new JScrollPane(taClientsList);
			getContentPane().add(scrollClients, new GridBagConstraints(
					3, 0, 1, 4, 0.0, 0.0, GridBagConstraints.CENTER, 
					GridBagConstraints.BOTH, new Insets(10, 10, 0, 10), 0, 0));
			taClientsList.setBorder(BorderFactory.createTitledBorder("Online users"));
			taClientsList.setEditable(false);
			
			
			//text area for resieving messages
			taReceivedMsg = new JTextArea();
			scrollReseiving = new JScrollPane(taReceivedMsg);
			getContentPane().add(scrollReseiving, new GridBagConstraints(
					0, 0, 3, 2, 0.0, 0.0, GridBagConstraints.CENTER, 
					GridBagConstraints.BOTH, new Insets(10, 10, 0, 0), 0, 0));
			taReceivedMsg.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			taReceivedMsg.setEditable(false);	
			
			
			//text area where the user enters messages
			taInputMsg = new JTextArea();
			scrollInput = new JScrollPane(taInputMsg);
			getContentPane().add(scrollInput, new GridBagConstraints(
					0, 3, 3, 2, 0.0, 0.0, GridBagConstraints.CENTER, 
					GridBagConstraints.BOTH, new Insets(0, 10, 10, 0), 0, 0));
			taInputMsg.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			taInputMsg.getDocument().addDocumentListener(this);
			
			
			pack();
			setResizable(false);
			setLocation(20, 50);
			setSize(500, 400);
		} catch (Exception e) {
			log.error("Error in displaying chat window.", e);
		}
	}
	
	/**
	 * Switches the chat window and login form.
	 */
	public void showChatWindow(boolean b) {
		this.setVisible(b);
		isConnected = b;
	}
	
	/**
	 * Handles the event fired from the ActionListener when send or 
	 * logout buttons is pressed.
	 */
	public void actionPerformed(ActionEvent ae) {
		//button logout is pressed
		if ("logout".equals(ae.getActionCommand())) {
			client.sendDisconnectionRequest();
		//button send is pressed
		} else if ("send".equals(ae.getActionCommand())) {
			client.sendMessage(taInputMsg.getText());
			taInputMsg.setText("");		//clear the console
			bSend.setEnabled(false);
		}
	}
	
	/**
	 * Processes the event fired when there are any change 
	 * in the input textarea.
	 */
	public void insertUpdate(DocumentEvent arg0) {
		message = taInputMsg.getText();
		if(!"".equals(message)) {
			message.substring(0, 1).toUpperCase();
			bSend.setEnabled(true);
		}
		if(message.length() > 200) {
			message = message.substring(0, 200);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {	
					taInputMsg.setText(message);	
				}
			});
		}
	}
	
	/**
	 * Displays the online users.
	 * @param usersOnline The list with connected users.
	 */
	public synchronized void displayOnlineUsers(Vector usersOnline) {
		for (Iterator it = usersOnline.iterator(); it.hasNext();) {
			String user = (String) it.next();
			addClientToList(user);
		}
	}
	
	/**
	 * Displays the message written from any user.
	 * @param msg The message to be displayed.
	 */
	public void displayMessage(String msg) {
		taReceivedMsg.append(msg + NEW_LINE);
	}
	
	/**
	 * Add a new client to the clients list in this window.
	 * @param aClient The client that is just connected.
	 */
	public void addClientToList(String aClient) {
		taClientsList.append(aClient+"\n");
	}
	
	/**
	 * Clears the clients list area.
	 */
	public void resetClientsList() {
		taClientsList.setText("");
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
					log.error(warningMessage);
					break;
			case 2:JOptionPane.showMessageDialog(
					this, warningMessage, "Information",
					JOptionPane.INFORMATION_MESSAGE);
					log.info(warningMessage);
					break;
		}
	}
	
	public void changedUpdate(DocumentEvent arg0) {}
	public void removeUpdate(DocumentEvent arg0) {}
}
