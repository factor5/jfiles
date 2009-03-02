package proj.pe.ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import proj.pe.actions.MainWindowActions;
import proj.pe.util.FileReadWrite;

/**
 * This is the main interface window for this application.
 */
public class MainWindow extends JFrame {

    /**
     * The action listener for this window.
     */
    private MainWindowActions mwa;

    /**
     * Buttons
     */
    private JButton btnStud;
    private JButton btnTeach;
    private JButton btnStaff;
    private JButton btnSearch;

    /**
     * The search field.
     */
    public JTextField fldSearch;

    /**
     * The radio buttons
     */
    private JRadioButton rdoStud;
    private JRadioButton rdoTeach;
    private JRadioButton rdoStaff;

    /**
     * The button group where the radio button are stored.
     */
    public ButtonGroup radioButtons;

    /**
     * Labels
     */
    private JLabel lblSearch;
    private JLabel lblCreate;

    /**
     * Constructor to initialize the user interface and to prepare some objects
     * for use.
     */
    public MainWindow(String fileStud, String fileTeach, String fileStaff) {
	// check whether the paths to the working files are set correctly
	// because without them this application is useless
	if (fileStud == null && fileTeach == null && fileStaff == null
		&& fileStud.length() == 0 && fileTeach.length() == 0
		&& fileStaff.length() == 0) {
	    setWarnings("Не е указан пътя до ресурсните файлове!", 1);
	    return;
	}
	setTitle("Програма справки");
	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	try {
	    // create the file operator class - if its constructor fails the
	    // application will be terminated
	    FileReadWrite frw = new FileReadWrite(fileStud, fileTeach,
		    fileStaff);
	    // create the action listener for this window
	    mwa = new MainWindowActions(this);
	    // call a method to create the window
	    makeMainWindow(this.getContentPane());
	} catch (IOException e) {
	    setWarnings("Грешка при достъп до ресурсните файлове!"
		    + "\nПриложението не може да бъде стартирано.", 1);
	    e.printStackTrace();
	}
    }

    /**
     * Creates the GUI.
     */
    public void makeMainWindow(Container pane) {
	try {
	    // we doesn't use a layout
	    pane.setLayout(null);

	    // set up the buttons
	    btnStud = new JButton("студент");
	    btnStud.setActionCommand("stud");
	    pane.add(btnStud);
	    btnStud.addActionListener(mwa);

	    btnTeach = new JButton("преподавател");
	    btnTeach.setActionCommand("teach");
	    pane.add(btnTeach);
	    btnTeach.addActionListener(mwa);

	    btnStaff = new JButton("служител");
	    btnStaff.setActionCommand("staff");
	    pane.add(btnStaff);
	    btnStaff.addActionListener(mwa);

	    btnSearch = new JButton("търси");
	    btnSearch.setActionCommand("search");
	    pane.add(btnSearch);
	    btnSearch.addActionListener(mwa);

	    // set up the search field
	    fldSearch = new JTextField();
	    pane.add(fldSearch);
	    fldSearch.addActionListener(mwa);

	    // set up the radio buttons
	    rdoStud = new JRadioButton("студент");
	    rdoStud.setActionCommand("searchStudent");
	    rdoStud.setSelected(true);
	    pane.add(rdoStud);
	    rdoStud.addActionListener(mwa);

	    rdoTeach = new JRadioButton("преподавател");
	    rdoTeach.setActionCommand("searchTeacher");
	    pane.add(rdoTeach);
	    rdoTeach.addActionListener(mwa);

	    rdoStaff = new JRadioButton("служител");
	    rdoStaff.setActionCommand("searchStaff");
	    pane.add(rdoStaff);
	    rdoStaff.addActionListener(mwa);

	    // set up the button group and add to it the radio buttons
	    radioButtons = new ButtonGroup();
	    radioButtons.add(rdoStud);
	    radioButtons.add(rdoTeach);
	    radioButtons.add(rdoStaff);

	    // set up the labels
	    lblCreate = new JLabel("Създай запис:");
	    pane.add(lblCreate);
	    lblSearch = new JLabel("Направи справка:");
	    pane.add(lblSearch);

	    // set the position of the components and their dimensions
	    Insets insets = pane.getInsets();
	    Dimension size = lblCreate.getPreferredSize();
	    lblCreate.setBounds(10 + insets.left, 5 + insets.top, size.width,
		    size.height);

	    size = btnStud.getPreferredSize();
	    btnStud.setBounds(10 + insets.left, 30 + insets.top,
		    size.width + 30, size.height);
	    size = btnTeach.getPreferredSize();
	    btnTeach.setBounds(10 + insets.left, 60 + insets.top, size.width,
		    size.height);
	    size = btnStaff.getPreferredSize();
	    btnStaff.setBounds(10 + insets.left, 90 + insets.top,
		    size.width + 25, size.height);

	    JSeparator verticalSeparator = new JSeparator(1);
	    pane.add(verticalSeparator);
	    size = verticalSeparator.getPreferredSize();
	    verticalSeparator.setBounds(140 + insets.left, 5 + insets.top,
		    size.width, size.height + 155);

	    size = lblSearch.getPreferredSize();
	    lblSearch.setBounds(150 + insets.left, 5 + insets.top, size.width,
		    size.height);

	    size = rdoStud.getPreferredSize();
	    rdoStud.setBounds(150 + insets.left, 30 + insets.top, size.width,
		    size.height);

	    size = rdoTeach.getPreferredSize();
	    rdoTeach.setBounds(150 + insets.left, 60 + insets.top, size.width,
		    size.height);

	    size = rdoStaff.getPreferredSize();
	    rdoStaff.setBounds(150 + insets.left, 90 + insets.top, size.width,
		    size.height);

	    size = fldSearch.getPreferredSize();
	    fldSearch.setBounds(150 + insets.left, 120 + insets.top,
		    size.width + 80, size.height);

	    size = btnSearch.getPreferredSize();
	    btnSearch.setBounds(240 + insets.left, 120 + insets.top,
		    size.width - 20, size.height);

	    // set the window to be not resizable
	    setResizable(false);
	    // set the size for the window
	    setSize(300 + insets.left + insets.right, 200 + insets.left
		    + insets.right);
	    // set the position where this window will appear by default
	    setLocation(200, 200);
	    // show the window
	    setVisible(true);
	} catch (Exception e) {
	    e.printStackTrace();
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
}
