/**
 * 
 */
package proj.pe.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import proj.pe.dto.Teacher;
import proj.pe.ui.MainWindow;
import proj.pe.ui.TeacherWindow;
import proj.pe.util.FileReadWrite;
import proj.pe.util.Validator;

public class TeacherWindowActions extends WindowAdapter implements
	ActionListener {

    private TeacherWindow teachWin;

    private MainWindow mainWin;

    private List<Teacher> teachersList;

    public TeacherWindowActions(TeacherWindow twin, MainWindow mwin) {
	teachWin = twin;
	mainWin = mwin;
	teachersList = FileReadWrite.teacherList;
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
	    Teacher teacher = collectAndCheckData();
	    if (teacher != null) {
		if (exists(teacher)) {
		    teachWin.displayMessage("Съществуващо ЕГН!");
		    return;
		}
		teachersList.add(teacher);
		if (FileReadWrite.storeListInFile(2)) {
		    teachWin.displayMessage("Данните са записани!");
		    switchButtons(false);
		}
	    }
	} else if (ae.getActionCommand().equals("reset")) {
	    teachWin.resetForm();
	    switchButtons(true);
	} else if (ae.getActionCommand().equals("cancel")) {
	    teachWin.resetForm();
	    switchButtons(true);
	    hideWindow();
	}
    }

    private boolean exists(Teacher teacher) {
	for (Teacher current : teachersList) {
	    if (current.getEgn().equals(teacher.getEgn())) {
		return true;
	    }
	}
	return false;
    }

    private Teacher collectAndCheckData() {
	Teacher teacher = new Teacher();
	String name = teachWin.fldName.getText();
	String egn = teachWin.fldEGN.getText();
	String roomNum = teachWin.fldRoomNumber.getText();
	if (!Validator.validateName(name)
		|| !Validator.validateEgnOrFacNumber(egn, 1)
		|| !Validator.validateRoomNumber(roomNum)) {
	    return null;
	}
	teacher.setTeachName(name);
	teacher.setEgn(egn);
	teacher
		.setDepartment((String) teachWin.cmbDepartment
			.getSelectedItem());
	teacher.setFaculty((String) teachWin.cmbFaculty.getSelectedItem());
	teacher.setRoomNumber(roomNum);
	return teacher;
    }

    /**
     * Catches the WindowClosing event and correctly closes the application.
     */
    @Override
    public void windowClosing(WindowEvent e) {
	teachWin.resetForm();
	switchButtons(true);
	hideWindow();
    }

    private void hideWindow() {
	teachWin.setVisible(false);
	mainWin.setVisible(true);
    }

    private void switchButtons(boolean turn) {
	teachWin.btnSave.setEnabled(turn);
	teachWin.btnCancel.setEnabled(turn);
    }
}
