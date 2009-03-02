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

import pe.ezici.actions.StudentWindowActions;

/**
 * This is the window where is visualized the form for the students.
 */
public class StudentWindow extends JFrame {

    /**
     * Buttons
     */
    public JButton btnSave;
    public JButton btnCancel;
    public JButton btnReset;

    /**
     * Input fields
     */
    public JTextField fldName;
    public JTextField fldFNumber;
    public JTextField fldResults;

    /**
     * Labels
     */
    private JLabel lblStudData;
    public JLabel lblInfo;

    /**
     * Select boxes
     */
    public JComboBox cmbSpeciality;
    public JComboBox cmbCourse;

    /**
     * Options for the speciality select menu
     */
    private String[] specList = { "КСТ", "КТТ", "ЗМТ", "ЕЕО" };

    /**
     * Options for the courses select menu
     */
    private Integer[] courseList = { 1, 2, 3, 4, 5 };

    /**
     * The action listener for this window
     */
    private StudentWindowActions swa;

    /**
     * A reference to the main window
     */
    private MainWindow mainWin;

    /**
     * Constructor to initialize the user interface and to prepare some objects
     * for use.
     */
    public StudentWindow(MainWindow mwin) {
	setTitle("Нов запис за студент");
	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	mainWin = mwin;
	swa = new StudentWindowActions(this, mainWin);
	this.addWindowListener(swa);
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
	    btnSave.addActionListener(swa);

	    btnCancel = new JButton("отказ");
	    btnCancel.setActionCommand("cancel");
	    pane.add(btnCancel);
	    btnCancel.addActionListener(swa);

	    btnReset = new JButton("изчистване");
	    btnReset.setActionCommand("reset");
	    pane.add(btnReset);
	    btnReset.addActionListener(swa);

	    JLabel lblName = new JLabel("Име :");
	    pane.add(lblName);
	    fldName = new JTextField();
	    fldName.setActionCommand("name");
	    fldName.setName("name");
	    pane.add(fldName);
	    fldName.addActionListener(swa);

	    JLabel lblFNumber = new JLabel("Фак. номер :");
	    pane.add(lblFNumber);
	    fldFNumber = new JTextField();
	    fldFNumber.setActionCommand("fnum");
	    fldFNumber.setName("fNumber");
	    pane.add(fldFNumber);
	    fldFNumber.addActionListener(swa);

	    JLabel lblResults = new JLabel("Успех :");
	    pane.add(lblResults);
	    fldResults = new JTextField();
	    fldResults.setActionCommand("results");
	    fldResults.setName("results");
	    pane.add(fldResults);
	    fldResults.addActionListener(swa);

	    JLabel lblSpeciality = new JLabel("Специалност :");
	    pane.add(lblSpeciality);
	    cmbSpeciality = new JComboBox(specList);
	    cmbSpeciality.setSelectedIndex(0);
	    pane.add(cmbSpeciality);
	    cmbSpeciality.addActionListener(swa);

	    JLabel lblCourse = new JLabel("Курс :");
	    pane.add(lblCourse);
	    cmbCourse = new JComboBox(courseList);
	    cmbCourse.setSelectedIndex(0);
	    pane.add(cmbCourse);
	    cmbCourse.addActionListener(swa);

	    lblStudData = new JLabel("Въведи данни за студент:");
	    pane.add(lblStudData);
	    lblInfo = new JLabel();
	    lblInfo.setForeground(Color.BLUE);
	    lblInfo.setVisible(false);
	    pane.add(lblInfo);

	    Insets insets = pane.getInsets();
	    Dimension size = lblStudData.getPreferredSize();
	    lblStudData.setBounds(10 + insets.left, 5 + insets.top, size.width,
		    size.height);
	    lblInfo.setBounds(150 + insets.left, 5 + insets.top, size.width,
		    size.height);

	    lblName.setBounds(10 + insets.left, 30 + insets.top, 100, 20);
	    size = fldName.getPreferredSize();
	    fldName.setBounds(100 + insets.left, 30 + insets.top,
		    size.width + 170, size.height);

	    lblFNumber.setBounds(10 + insets.left, 60 + insets.top, 100, 20);
	    size = fldFNumber.getPreferredSize();
	    fldFNumber.setBounds(100 + insets.left, 60 + insets.top,
		    size.width + 170, size.height);

	    lblResults.setBounds(10 + insets.left, 90 + insets.top, 100, 20);
	    size = fldResults.getPreferredSize();
	    fldResults.setBounds(100 + insets.left, 90 + insets.top,
		    size.width + 170, size.height);

	    lblSpeciality
		    .setBounds(10 + insets.left, 120 + insets.top, 100, 20);
	    size = cmbSpeciality.getPreferredSize();
	    cmbSpeciality.setBounds(100 + insets.left, 120 + insets.top,
		    size.width + 40, size.height);

	    lblCourse.setBounds(10 + insets.left, 150 + insets.top, 100, 20);
	    size = cmbCourse.getPreferredSize();
	    cmbCourse.setBounds(100 + insets.left, 150 + insets.top,
		    size.width + 20, size.height);

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
     * Resets the form fields to its default values.
     */
    public void resetForm() {
	fldName.setText("");
	fldFNumber.setText("");
	fldResults.setText("");
	lblInfo.setVisible(false);
	cmbCourse.setSelectedIndex(0);
	cmbSpeciality.setSelectedIndex(0);
    }
}
