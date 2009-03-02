/**
 * 
 */
package pe.ezici.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import pe.ezici.dto.Student;
import pe.ezici.util.FileReadWrite;
import pe.ezici.util.Validator;
import pr.ezici.ui.MainWindow;
import pr.ezici.ui.StudentWindow;

/**
 * Action class that provides response to the user actions over the student
 * window.
 */
public class StudentWindowActions extends WindowAdapter implements
	ActionListener {

    /**
     * A reference to the student window.
     */
    private StudentWindow studWin;

    /**
     * A reference to the main window.
     */
    private MainWindow mainWin;

    /**
     * The list with already saved students data.
     */
    private List<Student> studentsList;

    /**
     * Constructor.
     * 
     * @param swin
     * @param mwin
     */
    public StudentWindowActions(StudentWindow swin, MainWindow mwin) {
	studWin = swin;
	mainWin = mwin;
	// get the prepared students list from the file operator class
	studentsList = FileReadWrite.studentsList;
    }

    /**
     * Action method that provides response for user's activities.
     * 
     * @param ae
     *                event that this method catches
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
	if (ae.getActionCommand().equals("save")) {
	    Student stobj = collectAndCheckData();
	    if (stobj != null) {
		if (exists(stobj)) {
		    studWin.displayMessage("Съществуващ фак.номер!");
		    return;
		}
		studentsList.add(stobj);
		if (FileReadWrite.storeListInFile(1)) {
		    studWin.displayMessage("Студентът е записан!");
		    switchButtons(false);
		}
	    }
	} else if (ae.getActionCommand().equals("reset")) {
	    studWin.resetForm();
	    switchButtons(true);
	} else if (ae.getActionCommand().equals("cancel")) {
	    studWin.resetForm();
	    switchButtons(true);
	    hideWindow();
	}
    }

    /**
     * Checks if the data for the student already exists in the base.
     * 
     * @param student
     *                the data for the new student
     * @return true if there is record for this student in the base.
     */
    private boolean exists(Student student) {
	for (Student current : studentsList) {
	    if (current.getFacNumber().equals(student.getFacNumber())) {
		return true;
	    }
	}
	return false;
    }

    /**
     * Collects the data entered by the user in the form fields the invoke
     * validator to check if the data is valid.
     * 
     * @return the Student object prepared with the data provided by the user or
     *         null if validation of the data fails.
     */
    private Student collectAndCheckData() {
	Student stobj = new Student();
	String name = studWin.fldName.getText();
	String fnum = studWin.fldFNumber.getText();
	String result = studWin.fldResults.getText();
	if (!Validator.validateName(name)
		|| !Validator.validateEgnOrFacNumber(fnum, 2)
		|| !Validator.validateResult(result)) {
	    return null;
	}
	stobj.setStudentName(name);
	stobj.setFacNumber(fnum);
	stobj.setResult(Float.parseFloat(result));
	stobj.setSpeciality((String) studWin.cmbSpeciality.getSelectedItem());
	stobj.setCourse((Integer) studWin.cmbCourse.getSelectedItem());
	return stobj;
    }

    /**
     * Listens for the window closing event and cleans the form before hiding
     * this window.
     */
    @Override
    public void windowClosing(WindowEvent e) {
	studWin.resetForm();
	switchButtons(true);
	hideWindow();
    }

    /**
     * Switches this window with the main one.
     */
    private void hideWindow() {
	studWin.setVisible(false);
	mainWin.setVisible(true);
    }

    /**
     * Switches the buttons
     * 
     * @param turn
     */
    private void switchButtons(boolean turn) {
	studWin.btnSave.setEnabled(turn);
	studWin.btnCancel.setEnabled(turn);
    }
}
