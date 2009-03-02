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
import pe.ezici.util.FileReadWrite;
import pe.ezici.util.Validator;
import pr.ezici.ui.MainWindow;
import pr.ezici.ui.StaffWindow;

/**
 * Action class for the staff window interface.
 */
public class StaffWindowActions extends WindowAdapter implements ActionListener {

    /**
     * Reference to the staff window
     */
    private StaffWindow staffWin;

    /**
     * Reference to the main window
     */
    private MainWindow mainWin;

    /**
     * List with staff data
     */
    private List<Staff> staffList;

    /**
     * Constructor that initializes some fields.
     * 
     * @param twin
     * @param mwin
     */
    public StaffWindowActions(StaffWindow twin, MainWindow mwin) {
	staffWin = twin;
	mainWin = mwin;
	staffList = FileReadWrite.staffList;
    }

    /**
     * Action method that provides response for user's activities.
     * 
     * @param ae
     *                event that this method catches
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
	// button save is pressed
	if (ae.getActionCommand().equals("save")) {
	    // call method to collect data from the interface controls
	    Staff staff = collectAndCheckData();
	    if (staff != null) {
		// call method to check if provided from the user data already
		// exists in the list
		if (exists(staff)) {
		    // we leave the method
		    staffWin.displayMessage("Съществуващо ЕГН!");
		    return;
		}
		// add the record for the new staff in the list
		staffList.add(staff);
		// call the method that stores the list in a file
		if (FileReadWrite.storeListInFile(3)) {
		    // if data is successfully stored we show message
		    staffWin.displayMessage("Данните са въведени!");
		    // disable buttons
		    switchButtons(false);
		}
	    }
	    // button reset is pressed
	} else if (ae.getActionCommand().equals("reset")) {
	    // reset the form fields
	    staffWin.resetForm();
	    // enable the buttons
	    switchButtons(true);
	    // button cancel is pressed
	} else if (ae.getActionCommand().equals("cancel")) {
	    // reset the form fields
	    staffWin.resetForm();
	    // enable buttons
	    switchButtons(true);
	    // hide this window and show the main one
	    hideWindow();
	}
    }

    /**
     * Searches the list if the data provided from user exists.
     * 
     * @param staff
     *                the staff object to check if exists
     * @return true if exists or false if doesn't
     */
    private boolean exists(Staff staff) {
	// iterate the list
	for (Staff current : staffList) {
	    // check the data
	    if (current.getEgn().equals(staff.getEgn())) {
		// if match occurs we return
		return true;
	    }
	}
	return false;
    }

    /**
     * Collects the data from the form fields and store it in a new staff
     * object.
     * 
     * @return the staff object filled with new data
     */
    private Staff collectAndCheckData() {
	Staff staff = new Staff();
	String name = staffWin.fldName.getText();
	String egn = staffWin.fldEGN.getText();
	String phone = staffWin.fldPhoneNumber.getText();
	if (!Validator.validateName(name)
		|| !Validator.validateEgnOrFacNumber(egn, 1)
		|| !Validator.validatePhoneNumber(phone)) {
	    return null;
	}
	// set the collected data in the appropriate fields
	staff.setStaffName(name);
	staff.setEgn(egn);
	staff.setPosition((String) staffWin.cmbPosition.getSelectedItem());
	staff.setPhoneNumber(phone);
	// return the prepared staff object
	return staff;
    }

    /**
     * Catches the window closing event so to be able to do some cleaning work
     * before closing.
     */
    @Override
    public void windowClosing(WindowEvent e) {
	// reset the form fields
	staffWin.resetForm();
	// enable the buttons
	switchButtons(true);
	// hide this window and show the main one
	hideWindow();
    }

    /**
     * Switches the current window and the main one.
     */
    private void hideWindow() {
	staffWin.setVisible(false);
	mainWin.setVisible(true);
    }

    /**
     * Turns on/off the buttons in this window according the boolean parameter.
     * 
     * @param turn
     */
    private void switchButtons(boolean turn) {
	staffWin.btnSave.setEnabled(turn);
	staffWin.btnCancel.setEnabled(turn);
    }
}
