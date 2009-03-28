/**
 * 
 */
package com.cit.jcourse.svelikov.theme7.downloadagent;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 * 
 * @author Svilen Velikov
 * @version 2007-7-16
 */
public class TheVampireUI extends JFrame implements MouseListener, KeyListener {

    private static final long serialVersionUID = 1L;
    /** Reference to the DownloadAgent class. */
    private DownloadAgent dAgent;
    /** The progressbar. */
    private JProgressBar downloadPBar;
    /** The filechooser window. */
    private JFileChooser fChooser;
    /** The stop button. */
    private JButton bStop;
    /** The textfield for the path to be entered. */
    private JTextField pathField;
    /** The start button for the download task. */
    private JButton bDownload;

    /**
     * Constructs the UI for this client.
     */
    public TheVampireUI() {
	setTitle("TheVampire");
	try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	fChooser = new JFileChooser();
    }

    /**
     * Creates the GUI.
     */
    public void initGUI() {
	try {
	    GridBagLayout thisLayout = new GridBagLayout();
	    thisLayout.rowWeights = new double[] { 0.1, 0.1 };
	    thisLayout.rowHeights = new int[] { 52, 29 };
	    thisLayout.columnWeights = new double[] { 0.1, 0.1, 0.1, 0.1 };
	    thisLayout.columnWidths = new int[] { 70, 137, 98, 7 };
	    getContentPane().setLayout(thisLayout);
	    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

	    bDownload = new JButton("Download");
	    bDownload.setName("download");
	    getContentPane().add(
		    bDownload,
		    new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER,
			    GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0,
				    5), 0, 0));
	    bDownload.setFocusable(true);
	    bDownload.addMouseListener(this);
	    bDownload.addKeyListener(this);

	    pathField = new JTextField("Paste or type source path here.");
	    pathField.setName("path");
	    getContentPane().add(
		    pathField,
		    new GridBagConstraints(0, 0, 3, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER,
			    GridBagConstraints.HORIZONTAL, new Insets(0, 5, 0,
				    5), 0, 0));
	    pathField.addMouseListener(this);
	    pathField.addKeyListener(this);

	    downloadPBar = new JProgressBar();
	    Border border2 = BorderFactory
		    .createTitledBorder("Download progress...");
	    downloadPBar.setBorder(border2);
	    getContentPane().add(
		    downloadPBar,
		    new GridBagConstraints(0, 1, 3, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER,
			    GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5,
				    5), 0, 0));
	    downloadPBar.setVisible(false);

	    bStop = new JButton();
	    bStop.setName("stop");
	    getContentPane().add(
		    bStop,
		    new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER,
			    GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0,
				    5), 0, 0));
	    bStop.setText("Stop");
	    bStop.setVisible(false);
	    bStop.addMouseListener(this);

	    setResizable(false);
	    this.setSize(400, 130);
	    setLocation(350, 200);
	    pack();
	    setVisible(true);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * Handler for the mousePressed event fired from the start and stop buttons
     * and from textfield.
     */
    public void mousePressed(MouseEvent me) {
	doAction(me);
    }

    /**
     * Handler for the keyPressed event fired from the start button if it has
     * focus and enter is pressed.
     */
    public void keyPressed(KeyEvent ke) {
	if (ke.getKeyCode() == 10) { // [enter]
	    doAction(ke);
	}
    }

    /**
     * Does any action according to the users activity.
     * 
     * @param ce
     *                The event passed by the the handlers.
     */
    private void doAction(ComponentEvent ce) {
	dAgent = new DownloadAgent(this);
	if (ce.getComponent().getName() == "download") {
	    String sourcePath = pathField.getText();
	    dAgent.checkURL(sourcePath);
	} else if (ce.getComponent().getName() == "stop") {
	    FileTransporter.stopped = true;
	} else if (ce.getComponent().getName() == "path") {
	    pathField.selectAll();
	}
    }

    /**
     * Shows the fileChooser so the user can choose the destination directory.
     * 
     * @param sourceFileName
     *                The source file name to be set in the file chooser.
     */
    public void chooseDirectoryToSave(File sourceFileName,
	    String validSourcePath) {
	fChooser.setSelectedFile(sourceFileName); // set the name in the file
						    // chooser
	int returnVal = fChooser.showSaveDialog(TheVampireUI.this);
	if ((returnVal == JFileChooser.APPROVE_OPTION)) {
	    String destination = fChooser.getCurrentDirectory() + "\\"
		    + sourceFileName.toString();
	    File outputPath = new File(destination);
	    dAgent.downloadFile(validSourcePath, outputPath);
	} else {
	}
    }

    /**
     * Shows windows with messages according the parameters.
     * 
     * @param warningMessage
     *                The message that should be displayed.
     * @param msgType
     *                The type of the window to be shown.
     */
    public void setWarnings(String warningMessage, int msgType) {
	switch (msgType) {
	case 1:
	    JOptionPane.showMessageDialog(this, warningMessage, "Warning",
		    JOptionPane.WARNING_MESSAGE);
	    break;
	case 2:
	    JOptionPane.showMessageDialog(this, warningMessage, "Information",
		    JOptionPane.INFORMATION_MESSAGE);
	    break;
	}

    }

    /**
     * Initializes and shows the progressBar when the download task is started.
     * It also disables the start button and the text field until the task
     * finishes.
     * 
     * @param start
     *                The start value for the progressbar.
     * @param end
     *                The end value for the progressbar.
     */
    public void showPBar(int start, int end) {
	bDownload.removeMouseListener(this);
	bDownload.setEnabled(false);
	pathField.setEnabled(false);
	downloadPBar.setMinimum(start);
	downloadPBar.setMaximum(end);
	downloadPBar.setValue(start);
	downloadPBar.setVisible(true);
	bStop.setVisible(true);
    }

    /**
     * Hides the progressBar and the stop button when the download task is
     * finished. And also reactivates the text field and the start button.
     */
    public void hidePBar() {
	bDownload.addMouseListener(this);
	bDownload.setEnabled(true);
	pathField.setEnabled(true);
	downloadPBar.setVisible(false);
	bStop.setVisible(false);
	pathField.setText("Paste or type source path here.");
    }

    /**
     * Updates the progressBar.
     * 
     * @param newValue
     */
    public void updateBar(int newValue) {
	// downloadPBar.setString("downloaded : "+newValue+" bytes");
	downloadPBar.setValue(newValue);
	downloadPBar.setStringPainted(true);
    }

    public void mouseClicked(MouseEvent arg0) {
    }

    public void mouseEntered(MouseEvent arg0) {
    }

    public void mouseExited(MouseEvent arg0) {
    }

    public void mouseReleased(MouseEvent arg0) {
    }

    public void keyReleased(KeyEvent arg0) {
    }

    public void keyTyped(KeyEvent arg0) {
    }
}
