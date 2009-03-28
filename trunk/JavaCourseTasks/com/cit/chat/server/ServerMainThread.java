package com.cit.chat.server;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import org.apache.log4j.*;

/**
 * This class realizes the gui for this chat server.
 * 
 * @author Svilen Velikov
 * @version
 */
public class ServerMainThread extends javax.swing.JFrame implements ActionListener {

	private static final long serialVersionUID = 6498641106033575585L;

	private JComboBox 	portsList;
	private JTabbedPane tabPane;
	private JTextArea 	taConsole;
	private JScrollPane scroll;
	private JPanel 		consoleTab;
	private JPanel 		configTab;
	private JButton		bStart;
	private JButton 	bStop;
	
	/** New line constant used in msgField. */
	private static final String NEW_LINE = "\n";
	
	/** The chat server reference. */
	private ChatServer server;
	
	/** A flag to say that the server is started. */
	private boolean isStarted;
	
	/** The logger. */
	private Logger log = Logger.getLogger("ServerMainThread");
	
	
	/**
	 * Displays this frame.
	 */
	public ServerMainThread() {
		super("Chat server");
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initUI();
				log.info("Server's console is displayed correctly.");
			}
		});
	}

	/**
	 * Initializes this frame.
	 */
	private void initUI() {
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				log.info("Server console is closed.");
				if (isStarted) {
					server.stopServer();
					dispose();
				} else {
					dispose();
				}
			}
		});
		
		try {
			//the tab pane
			tabPane = new JTabbedPane();
			getContentPane().add(tabPane, BorderLayout.CENTER);

			
			//the tab with the console and start/stop buttons
			consoleTab = new JPanel();
			GridBagLayout consoleTabLayout = new GridBagLayout();
			consoleTabLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.1};
			consoleTabLayout.rowHeights = new int[] {138, 135, 139, 7};
			consoleTabLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
			consoleTabLayout.columnWidths = new int[] {7, 7, 7, 7};
			consoleTab.setLayout(consoleTabLayout);
			tabPane.addTab("Console", null, consoleTab, null);
			
			
			//the tab containing options for configuration
			configTab = new JPanel();
			tabPane.addTab("Config", null, configTab, null);
			GridBagLayout configTabLayout = new GridBagLayout();
			configTabLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
			configTabLayout.rowHeights = new int[] {7, 7, 7, 7};
			configTabLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.1};
			configTabLayout.columnWidths = new int[] {105, 78, 118, 7};
			configTab.setLayout(configTabLayout);

			
			//start button
			bStart = new JButton("start server");
			bStart.setActionCommand("start");
			consoleTab.add(bStart, new GridBagConstraints(
					2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, 
					GridBagConstraints.HORIZONTAL, new Insets(0, 10, 0,5),0,0));
			bStart.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			bStart.addActionListener(this);
		
			
			//stop button
			bStop = new JButton("stop server");
			bStop.setActionCommand("stop");
			consoleTab.add(bStop, new GridBagConstraints(
					3, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, 
					GridBagConstraints.HORIZONTAL, new Insets(0, 5, 0,10),0,0));
			bStop.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			bStop.addActionListener(this);
			bStop.setEnabled(false);
		
			
			//text area that is used as a console
			taConsole = new JTextArea();
			scroll = new JScrollPane(taConsole);
			consoleTab.add(scroll, new GridBagConstraints(
					0, 0, 4, 3, 0.0, 0.0, GridBagConstraints.CENTER, 
					GridBagConstraints.BOTH, new Insets(5, 10, 0, 10), 0, 0));
			taConsole.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			taConsole.setEditable(false);
			taConsole.setFont(new java.awt.Font("Arial",0,10));

			
			//combo box to choose a port number
			ComboBoxModel portsListModel = new DefaultComboBoxModel(
					new String[] { "7000", "7001", "7002", "7003", "7004" });
			portsList = new JComboBox();
			configTab.add(portsList, new GridBagConstraints(0, 0, 2, 1, 0.0,0.0, 
					GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
					new Insets(0, 20, 0, 0), 0, 0));
			portsList.setModel(portsListModel);
			portsList.setBorder(BorderFactory.createTitledBorder("Ports list"));
				
			
			pack();
			setResizable(false);
			setLocation(550, 50);
			setSize(400, 500);
			setVisible(true);
		} catch (Exception e) {
			log.error("Error in displaying the console.");
		}
	}

	/**
	 * Realizes the actions fired when stop or start buttons are pressed.
	 */
	public void actionPerformed(ActionEvent ae) {
		
		//start button is pressed
		if("start".equals(ae.getActionCommand())) {
			enableStartButton();
			switchCursor(1);	//busy
			Integer portNumber = new Integer((String)portsList.getSelectedItem());
			server = new ChatServer(this, portNumber.intValue());
			isStarted = true;
			log.info("Server is started.");
			
		//stop button is pressed
		} else if("stop".equals(ae.getActionCommand())) {
			switchCursor(1);	//busy
			server.stopServer();
			isStarted = false;
			enableStartButton();
			switchCursor(2);
			log.info("Server is stopped.");
		}
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
					log.error(warningMessage);
					break;
			case 2:JOptionPane.showMessageDialog(
					this, warningMessage, "Information",
					JOptionPane.INFORMATION_MESSAGE);
					log.info(warningMessage);
					break;
		}
	}
	
	/**
	 * Enables the start button.
	 */
	public void enableStartButton() {
		bStop.setEnabled(false);
		bStart.setEnabled(true);
		tabPane.setEnabledAt(1, true);
	}
	
	/**
	 * Disables the start button.
	 */
	public void disableStartButton() {
		bStop.setEnabled(true);
		bStart.setEnabled(false);
		tabPane.setEnabledAt(1, false);
	}
	
	/**
	 * Switches cursor mode.
	 * @param mode The mode to be set.
	 */
	public void switchCursor(int mode) {
		switch(mode) {
			case 1: setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					break;	
			case 2: setCursor(Cursor.getDefaultCursor());
					break;
			default : 
					break;
		}	
	}
	
	/**
	 * Prints messages in the text field.
	 * @param msg The message that should be printed.
	 */
	public void prtMessage(String msg) {
		taConsole.append(msg + NEW_LINE);
	}
}
