package com.cit.chat.client;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.log4j.*;

/**
 * Realizes the gui for the login form.
 * 
 * @author Svilen Velikov
 * @version
 */
public class LoginForm extends javax.swing.JFrame implements ActionListener,
		DocumentListener {
	
	private static final long serialVersionUID = -3940870937163532413L;
	
//	private ClientMainFrame chatWindow;
	private ChatClient client;
	private JButton    bConnect;
	private JButton    bCancel;
	private JTextField taHostname;
	private JTextField taNick;

	/** The nickname used from this client. */
	private String nick;
	
	/** A flag... */
	private boolean isConnected;
	
	/** The logger. */
	private Logger log = Logger.getLogger("LoginForm");
	
	
	/**
	 * Creates a client object and initializes the gui for login form.
	 */
	public LoginForm() {
		super("Login");
		client = new ChatClient(this);
		
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		        initUI();
		        log.info("Login is displayed correctly.");
		    }
		});
	}
	
	/**
	 * Initializes the gui for the login form and displays it.
	 */
	private void initUI() {
		//register the close frame button to stop client first.
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
            	if(isConnected) {
					client.stopClient();
					System.exit(0);
            	} else {
            		dispose();
            	}
            }
        });
        
		try {
			GridBagLayout thisLayout = new GridBagLayout();
			thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1};
			thisLayout.rowHeights = new int[] {20, 7, 7};
			thisLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.1};
			thisLayout.columnWidths = new int[] {56, 89, 84, 7};
			getContentPane().setLayout(thisLayout);
			
			
			//connect button
			bConnect = new JButton("connect");
			bConnect.setActionCommand("connect");
			bConnect.setSelected(true);
			getContentPane().add(bConnect, new GridBagConstraints(
					0, 2, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH, 
					GridBagConstraints.HORIZONTAL, new Insets(0, 10,0,0),0,0));
			bConnect.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			bConnect.addActionListener(this);

			
			//cansel button used when want to stop/cansel login
			bCancel = new JButton("cancel");
			bCancel.setActionCommand("cancel");
			getContentPane().add(bCancel, new GridBagConstraints(
					2, 2, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH, 
					GridBagConstraints.HORIZONTAL, new Insets(0,10,0,10),0,0));
			bCancel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			bCancel.addActionListener(this);
			

			//text area to fill nickname
			taNick = new JTextField();
			getContentPane().add(taNick, new GridBagConstraints(
					0, 1, 4, 1, 0.0, 0.0, GridBagConstraints.EAST, 
					GridBagConstraints.HORIZONTAL, new Insets(10,10,0,10),0,0));
			taNick.setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createBevelBorder(BevelBorder.LOWERED),
					"Nickname", TitledBorder.LEADING, TitledBorder.TOP));
			taNick.getDocument().addDocumentListener(this);
			
			
			//text area to fill the host name
			taHostname = new JTextField();
			taHostname.setText("localhost");
			getContentPane().add(taHostname, new GridBagConstraints(
					0, 0, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER, 
					GridBagConstraints.HORIZONTAL, new Insets(0, 10,0,10),0,0));
			taHostname.setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createBevelBorder(BevelBorder.LOWERED), 
					"Hostname", TitledBorder.LEADING, TitledBorder.TOP));
			
			
			pack();
			setResizable(false);
			setLocation(20, 50);
			setSize(300, 200);
			setVisible(true);
		} catch (Exception e) {
			log.error("Error while showing loginform.", e);
		}
	}
		
	/**
	 * Realizes the actions fired from the buttons in the login form.
	 */
	public void actionPerformed(ActionEvent ae) {
		String nick = taNick.getText();
		String host = taHostname.getText();
		
		//cansel button is pressed
		if("cancel".equals(ae.getActionCommand())) {	
			//TODO this duplicates some code before
        	if(isConnected) {
				client.stopClient();
				System.exit(0);
        	} else {
        		showLoginForm(false);
        		this.dispose();
        		//System.exit(0);
        	}
        	
        //connect button is pressed
		} else if("connect".equals(ae.getActionCommand()) && (!"".equals(nick)) && (!"".equals(host))) {		
			enableLogin(false);
			if(client.connectToServer(host, nick)) {
				log.info("User enters in chat.");
				isConnected = true;				
			} else {
				enableLogin(true);
			}
		}
	}
	
	/**
	 * Checks out the nickname every time it is changed by user and
	 * removes all the illegal symbols. It also cuts the nickname to
	 * 15 symbols lenght.
	 */
	public void insertUpdate(DocumentEvent arg0) {
		nick = taNick.getText();
		if(nick.contains("[") || nick.contains("]")) {
			nick = nick.replace("[", "");
			nick = nick.replace("]", "");
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {	
					taNick.setText(nick);	
				}
			});
		} 
		if(nick.length() > 15) {
			nick = nick.substring(0, 15);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {	
					taNick.setText(nick);	
				}
			});
		}
	}
	
	/**
	 * Closes this frame.
	 */
	public void showLoginForm(boolean b) {
		this.setVisible(b);
	}
	
	/**
	 * Turns on/off this login form.
	 * @param b
	 */
	public void enableLogin(boolean b) {
		bConnect.setEnabled(b);
		taNick.setEnabled(b);
		if(b) {
			setCursor(Cursor.getDefaultCursor());			
			taHostname.setText("");
			taNick.setText("");
		} else {
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));			
		}
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
	
	public void removeUpdate(DocumentEvent arg0) {}
	public void changedUpdate(DocumentEvent arg0) {}
}
