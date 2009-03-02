package proj.pe.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import proj.pe.actions.StaffWindowActions;

/**
 * This is the window where is visualized the form for the staffs.
 */
public class StaffWindow extends JFrame {

    /**
     * Buttons
     */
    public JButton btnSave;
    public JButton btnCancel;
    private JButton btnReset;

    /**
     * Fields
     */
    public JTextField fldName;
    public JTextField fldEGN;
    public JTextField fldPhoneNumber;

    /**
     * Labels
     */
    private JLabel lblName;
    private JLabel lblEGN;
    private JLabel lblPosition;
    private JLabel lblPhoneNumber;
    private JLabel lblStaffData;
    public JLabel lblInfo;

    /**
     * Combo box
     */
    public JComboBox cmbPosition;

    /**
     * The possible staff's job positions
     */
    private String[] posList = { "счетоводител", "библиотекар", "касиер",
	    "администратор" };

    /**
     * The action listener class for this window
     */
    private StaffWindowActions stwa;

    /**
     * A reference to the main window class
     */
    private MainWindow mainWin;

    /**
     * Constructor to initialize the user interface and to prepare some objects
     * for use.
     */
    public StaffWindow(MainWindow mwin) {
	setTitle("Нов запис за служител");
	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	mainWin = mwin;
	stwa = new StaffWindowActions(this, mainWin);
	this.addWindowListener(stwa);
	makeMainWindow(this.getContentPane());
    }

    /**
     * Creates the GUI.
     */
    public void makeMainWindow(Container pane) {
	try {
	    pane.setLayout(null);

	    btnSave = new JButton("запис");
	    btnSave.setActionCommand("save");
	    pane.add(btnSave);
	    btnSave.addActionListener(stwa);

	    btnCancel = new JButton("отказ");
	    btnCancel.setActionCommand("cancel");
	    pane.add(btnCancel);
	    btnCancel.addActionListener(stwa);

	    btnReset = new JButton("изчистване");
	    btnReset.setActionCommand("reset");
	    pane.add(btnReset);
	    btnReset.addActionListener(stwa);

	    lblName = new JLabel("Име :");
	    pane.add(lblName);
	    fldName = new JTextField();
	    fldName.setActionCommand("name");
	    fldName.setName("name");
	    pane.add(fldName);
	    fldName.addActionListener(stwa);

	    lblEGN = new JLabel("ЕГН :");
	    pane.add(lblEGN);
	    fldEGN = new JTextField();
	    fldEGN.setActionCommand("egn");
	    fldEGN.setName("egn");
	    pane.add(fldEGN);
	    fldEGN.addActionListener(stwa);

	    lblPosition = new JLabel("Позиция :");
	    pane.add(lblPosition);
	    cmbPosition = new JComboBox(posList);
	    cmbPosition.setActionCommand("position");
	    cmbPosition.setName("results");
	    pane.add(cmbPosition);
	    cmbPosition.addActionListener(stwa);

	    lblPhoneNumber = new JLabel("Тел. номер :");
	    pane.add(lblPhoneNumber);
	    fldPhoneNumber = new JTextField();
	    fldPhoneNumber.setActionCommand("phone");
	    fldPhoneNumber.setName("phone");
	    pane.add(fldPhoneNumber);
	    fldPhoneNumber.addActionListener(stwa);

	    lblStaffData = new JLabel("Въведи данни за служител:");
	    pane.add(lblStaffData);
	    lblInfo = new JLabel();
	    lblInfo.setForeground(Color.BLUE);
	    lblInfo.setVisible(false);
	    pane.add(lblInfo);

	    Insets insets = pane.getInsets();
	    Dimension size = lblStaffData.getPreferredSize();
	    lblStaffData.setBounds(10 + insets.left, 5 + insets.top,
		    size.width, size.height);
	    lblInfo.setBounds(160 + insets.left, 5 + insets.top, size.width,
		    size.height);

	    lblName.setBounds(10 + insets.left, 30 + insets.top, 100, 20);
	    size = fldName.getPreferredSize();
	    fldName.setBounds(100 + insets.left, 30 + insets.top,
		    size.width + 170, size.height);

	    lblEGN.setBounds(10 + insets.left, 60 + insets.top, 100, 20);
	    size = fldEGN.getPreferredSize();
	    fldEGN.setBounds(100 + insets.left, 60 + insets.top,
		    size.width + 170, size.height);

	    lblPosition.setBounds(10 + insets.left, 90 + insets.top, 100, 20);
	    size = cmbPosition.getPreferredSize();
	    cmbPosition.setBounds(100 + insets.left, 90 + insets.top,
		    size.width + 73, size.height);

	    lblPhoneNumber.setBounds(10 + insets.left, 120 + insets.top, 100,
		    20);
	    size = fldPhoneNumber.getPreferredSize();
	    fldPhoneNumber.setBounds(100 + insets.left, 120 + insets.top,
		    size.width + 60, size.height);

	    size = btnSave.getPreferredSize();
	    btnSave.setBounds(10 + insets.left, 160 + insets.top,
		    size.width + 30, size.height);
	    size = btnCancel.getPreferredSize();
	    btnCancel.setBounds(105 + insets.left, 160 + insets.top,
		    size.width, size.height);
	    size = btnReset.getPreferredSize();
	    btnReset.setBounds(170 + insets.left, 160 + insets.top,
		    size.width + 25, size.height);

	    setResizable(false);
	    setSize(300 + insets.left + insets.right, 220 + insets.left
		    + insets.right);
	    setLocation(200, 200);
	    setVisible(false);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * Shows a label with a message to inform the user for any event.
     * 
     * @param msg
     *                the message to be shown
     */
    public void displayMessage(String msg) {
	lblInfo.setText(msg);
	lblInfo.setVisible(true);
    }

    /**
     * Resets the forms fields
     */
    public void resetForm() {
	fldName.setText("");
	fldEGN.setText("");
	fldPhoneNumber.setText("");
	lblInfo.setVisible(false);
	cmbPosition.setSelectedIndex(0);
    }
}
