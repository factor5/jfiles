package pr.ezici.ui;

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

import pe.ezici.actions.TeacherWindowActions;

/**
 * This is the window where is visualized the form for the teachers data.
 */
public class TeacherWindow extends JFrame {

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
    public JTextField fldRoomNumber;

    /**
     * Combo boxes
     */
    public JComboBox cmbDepartment;
    public JComboBox cmbFaculty;

    /**
     * Labels
     */
    private JLabel lblTeachData;
    private JLabel lblName;
    private JLabel lblEGN;
    private JLabel lblDep;
    private JLabel lblFac;
    private JLabel lblRoom;
    public JLabel lblInfo;

    /**
     * Options for the departments select menu
     */
    private String[] depList = { "ЕЕА", "ЗТТ" };

    /**
     * Options for the faculties list
     */
    private String[] facList = { "КСТ", "КТТ", "ЗТТ" };

    /**
     * The action listener for this window
     */
    private TeacherWindowActions twa;

    /**
     * A reference to the main window
     */
    private MainWindow mainWin;

    /**
     * Constructor to initialize the user interface and to prepare some objects
     * for use.
     */
    public TeacherWindow(MainWindow mwin) {
	setTitle("Нов запис за преподавател");
	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	mainWin = mwin;
	twa = new TeacherWindowActions(this, mainWin);
	this.addWindowListener(twa);
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
	    btnSave.addActionListener(twa);

	    btnCancel = new JButton("отказ");
	    btnCancel.setActionCommand("cancel");
	    pane.add(btnCancel);
	    btnCancel.addActionListener(twa);

	    btnReset = new JButton("изчистване");
	    btnReset.setActionCommand("reset");
	    pane.add(btnReset);
	    btnReset.addActionListener(twa);

	    lblName = new JLabel("Име :");
	    pane.add(lblName);
	    fldName = new JTextField();
	    pane.add(fldName);
	    fldName.addActionListener(twa);

	    lblEGN = new JLabel("ЕГН :");
	    pane.add(lblEGN);
	    fldEGN = new JTextField();
	    pane.add(fldEGN);
	    fldEGN.addActionListener(twa);

	    lblDep = new JLabel("Катедра :");
	    pane.add(lblDep);
	    cmbDepartment = new JComboBox(depList);
	    cmbDepartment.setSelectedIndex(0);
	    pane.add(cmbDepartment);
	    cmbDepartment.addActionListener(twa);

	    lblFac = new JLabel("Факултет :");
	    pane.add(lblFac);
	    cmbFaculty = new JComboBox(facList);
	    cmbFaculty.setSelectedIndex(0);
	    pane.add(cmbFaculty);
	    cmbFaculty.addActionListener(twa);

	    lblRoom = new JLabel("Кабинет :");
	    pane.add(lblRoom);
	    fldRoomNumber = new JTextField();
	    pane.add(fldRoomNumber);
	    fldRoomNumber.addActionListener(twa);

	    lblTeachData = new JLabel("Въведи данни за преподавател:");
	    pane.add(lblTeachData);
	    lblInfo = new JLabel();
	    lblInfo.setForeground(Color.BLUE);
	    lblInfo.setVisible(false);
	    pane.add(lblInfo);

	    Insets insets = pane.getInsets();
	    Dimension size = lblTeachData.getPreferredSize();
	    lblTeachData.setBounds(10 + insets.left, 5 + insets.top,
		    size.width, size.height);
	    lblInfo.setBounds(180 + insets.left, 5 + insets.top, size.width,
		    size.height);

	    lblName.setBounds(10 + insets.left, 30 + insets.top, 100, 20);
	    size = fldName.getPreferredSize();
	    fldName.setBounds(100 + insets.left, 30 + insets.top,
		    size.width + 170, size.height);

	    lblEGN.setBounds(10 + insets.left, 60 + insets.top, 100, 20);
	    size = fldEGN.getPreferredSize();
	    fldEGN.setBounds(100 + insets.left, 60 + insets.top,
		    size.width + 170, size.height);

	    lblDep.setBounds(10 + insets.left, 90 + insets.top, 100, 20);
	    size = cmbDepartment.getPreferredSize();
	    cmbDepartment.setBounds(100 + insets.left, 90 + insets.top,
		    size.width + 30, size.height);

	    lblFac.setBounds(10 + insets.left, 120 + insets.top, 100, 20);
	    size = cmbFaculty.getPreferredSize();
	    cmbFaculty.setBounds(100 + insets.left, 120 + insets.top,
		    size.width + 30, size.height);

	    lblRoom.setBounds(10 + insets.left, 150 + insets.top, 100, 20);
	    size = fldRoomNumber.getPreferredSize();
	    fldRoomNumber.setBounds(100 + insets.left, 150 + insets.top,
		    size.width + 30, size.height);

	    size = btnSave.getPreferredSize();
	    btnSave.setBounds(10 + insets.left, 190 + insets.top,
		    size.width + 30, size.height);
	    size = btnCancel.getPreferredSize();
	    btnCancel.setBounds(105 + insets.left, 190 + insets.top,
		    size.width, size.height);
	    size = btnReset.getPreferredSize();
	    btnReset.setBounds(170 + insets.left, 190 + insets.top,
		    size.width + 25, size.height);

	    setResizable(false);
	    setSize(300 + insets.left + insets.right, 250 + insets.left
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
     * Resets the form fields.
     */
    public void resetForm() {
	fldName.setText("");
	fldEGN.setText("");
	fldRoomNumber.setText("");
	lblInfo.setVisible(false);
	cmbDepartment.setSelectedIndex(0);
	cmbFaculty.setSelectedIndex(0);
    }
}
