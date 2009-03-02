/**
 * 
 */
package pe.ezici.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import pe.ezici.dto.Staff;
import pe.ezici.dto.Student;
import pe.ezici.dto.Teacher;
import pe.ezici.util.FileReadWrite;
import pr.ezici.ui.MainWindow;
import pr.ezici.ui.StaffWindow;
import pr.ezici.ui.StudentWindow;
import pr.ezici.ui.TeacherWindow;

/**
 * Action class for the main window interface.
 */
public class MainWindowActions extends WindowAdapter implements ActionListener {

    /**
     * Reference to the student window
     */
    private StudentWindow studWin;

    /**
     * Reference to the teacher window
     */
    private TeacherWindow teachWin;

    /**
     * Reference to the staff window
     */
    private StaffWindow staffWin;

    /**
     * Reference to the main window
     */
    private MainWindow mainWin;

    /**
     * Constructor that initialize some fields.
     * 
     * @param mwin
     */
    public MainWindowActions(MainWindow mwin) {
	mainWin = mwin;
	studWin = new StudentWindow(mainWin);
	teachWin = new TeacherWindow(mainWin);
	staffWin = new StaffWindow(mainWin);
    }

    /**
     * Action method that catches event fired by the main window controls.
     * 
     * @param ae
     *                event that this method catches
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
	// button students is pressed
	if (ae.getActionCommand().equals("stud")) {
	    // hide main window
	    mainWin.setVisible(false);
	    // show student window
	    studWin.setVisible(true);
	    // button teachers is pressed
	} else if (ae.getActionCommand().equals("teach")) {
	    // hide main window
	    mainWin.setVisible(false);
	    // show teacher window
	    teachWin.setVisible(true);
	    // button staff is pressed
	} else if (ae.getActionCommand().equals("staff")) {
	    // hide main window
	    mainWin.setVisible(false);
	    // show staff window
	    staffWin.setVisible(true);
	    // button search is pressed
	} else if ("search".equals(ae.getActionCommand())) {
	    // before we start the searching first we check for what type we
	    // should do that and then we get the search string from the search
	    // field an pass it to the method that did the actual work
	    int type = 0;
	    if (mainWin.radioButtons.getSelection().getActionCommand().equals(
		    "searchStudent")) {
		type = 1;
	    } else if (mainWin.radioButtons.getSelection().getActionCommand()
		    .equals("searchTeacher")) {
		type = 2;
	    } else if (mainWin.radioButtons.getSelection().getActionCommand()
		    .equals("searchStaff")) {
		type = 3;
	    }
	    // get the string that we will search for
	    String searchString = mainWin.fldSearch.getText();
	    // call the search method
	    search(searchString, type);
	}
    }

    /**
     * Searches the string provided in the type fields and if match occurs it
     * returns true.
     * 
     * @param searchString
     *                the string to search for
     * @param type
     *                the type that is being searched
     */
    private void search(String searchString, int type) {
	switch (type) {
	case 1:
	    // get the list of students where we should search
	    List<Student> studentsList = FileReadWrite.studentsList;
	    // start a for loop on the list to search
	    for (Student student : studentsList) {
		// if we find the needed string we start to fill the window for
		// students with the data that we get from the current student
		// object
		if (student.getFacNumber().equals(searchString)) {
		    studWin.fldName.setText(student.getStudentName());
		    studWin.fldFNumber.setText(student.getFacNumber());
		    studWin.fldResults.setText(String.valueOf(student
			    .getResult()));
		    studWin.cmbCourse.setSelectedItem(student.getCourse());
		    studWin.cmbCourse.setSelectedItem(student.getSpeciality());
		    // hide the main and show the students window
		    mainWin.setVisible(false);
		    studWin.setVisible(true);
		    // leave the for loop
		    break;
		}
	    }
	    // leave the switch
	    break;
	case 2:
	    List<Teacher> teachersList = FileReadWrite.teacherList;
	    for (Teacher teacher : teachersList) {
		if (teacher.getEgn().equals(searchString)) {
		    teachWin.fldName.setText(teacher.getTeachName());
		    teachWin.fldEGN.setText(teacher.getEgn());
		    teachWin.fldRoomNumber.setText(teacher.getRoomNumber());
		    teachWin.cmbDepartment.setSelectedItem(teacher
			    .getDepartment());
		    teachWin.cmbFaculty.setSelectedItem(teacher.getFaculty());
		    mainWin.setVisible(false);
		    teachWin.setVisible(true);
		    break;
		}
	    }
	    break;
	case 3:
	    List<Staff> staffList = FileReadWrite.staffList;
	    for (Staff staff : staffList) {
		if (staff.getEgn().equals(searchString)) {
		    staffWin.fldName.setText(staff.getStaffName());
		    staffWin.fldEGN.setText(staff.getEgn());
		    staffWin.fldPhoneNumber.setText(staff.getPhoneNumber());
		    staffWin.cmbPosition.setSelectedItem(staff.getPosition());
		    mainWin.setVisible(false);
		    staffWin.setVisible(true);
		    break;
		}
	    }
	    break;

	default:
	    break;
	}
    }

    /**
     * Catches the WindowClosing event and correctly closes the application.
     */
    @Override
    public void windowClosing(WindowEvent e) {
	saveAndClose();
    }

    /**
     * Shows a confirmation dialog for saving current sheet and closes the
     * application.
     */
    private void saveAndClose() {
	System.exit(1);
    }
}
