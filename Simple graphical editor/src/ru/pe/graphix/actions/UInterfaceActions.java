/**
 * 
 */
package ru.pe.graphix.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import ru.pe.graphix.DrawingPanel;
import ru.pe.graphix.UserInterface;
import ru.pe.graphix.figures.GColor;
import ru.pe.graphix.figures.IBaseFigure;
import ru.pe.graphix.utill.Const;
import ru.pe.graphix.utill.FileOperation;
import ru.pe.graphix.utill.StringOperation;

/**
 * An listener class that provides response for the user's activities over the
 * user interface.
 * 
 * @author Svilen Velikov
 * 
 * 26.10.2008
 */
public class UInterfaceActions extends WindowAdapter implements ActionListener {

    /**
     * Reference to the user interface.
     */
    private UserInterface ui;

    /**
     * Reference to the object with the drawing actions.
     */
    private DrawingActions da;

    /**
     * Reference to the drawing panel object.
     */
    private DrawingPanel dp;

    /**
     * Reference to the file operations class.
     */
    private FileOperation fileOperations;

    /**
     * A file chooser component.
     */
    private JFileChooser fChooser;

    /**
     * Action method that provides response for user's activities.
     * 
     * @param ae
     *                event that this method catches
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
	if (Const.AC_DRAW.equals(ae.getActionCommand())) {
	    da.setAction(1);
	} else if (Const.AC_LINE.equals(ae.getActionCommand())) {
	    da.setFirstPointIsSet(false);
	    da.setAction(2);
	} else if (Const.AC_CIRCLE.equals(ae.getActionCommand())) {
	    da.setFirstPointIsSet(false);
	    da.setAction(3);
	} else if (Const.AC_TEXT.equals(ae.getActionCommand())) {
	    da.setAction(4);
	} else if (Const.AC_SQUARE.equals(ae.getActionCommand())) {
	    da.setAction(5);
	} else if (Const.AC_POLYLINE.equals(ae.getActionCommand())) {
	    da.setAction(6);
	} else if (Const.AC_COLOR.equals(ae.getActionCommand())) {
	    GColor gc = new GColor();
	    gc.setColors(ui.chooseColor());
	    dp.getFigList().add(gc);
	} else if (Const.AC_NEW_SHEET.equals(ae.getActionCommand())) {
	    showConfirmationDialog(Const.WARNING_NEW_SHEET,
		    Const.TITLE_CLOSE_SHEET_WARNING, 1);
	} else if (Const.AC_SAVE.equals(ae.getActionCommand())) {
	    chooseDirectoryToSave();
	} else if (Const.AC_OPEN.equals(ae.getActionCommand())) {
	    showConfirmationDialog(Const.DIALOG_READ_NEW_SHEET,
		    Const.TITLE_READ_FILE, 2);
	} else if (Const.AC_ABOUT.equals(ae.getActionCommand())) {
	    ui.showAbout();
	} else if (Const.AC_HELP.equals(ae.getActionCommand())) {
	    // TODO
	} else if (Const.AC_EXIT.equals(ae.getActionCommand())) {
	    saveAndClose();
	}
    }

    /**
     * Displays a confirmation dialog with yes/no/cancel options to choose.
     * 
     * @param confiramtionMessage
     *                the message to shown in the confirmation window
     * @param windowTitle
     *                the title for the window
     * @param type
     *                1-clear sheet, 2-read new sheet, 3-close application
     */
    public void showConfirmationDialog(String dialogMessage,
	    String windowTitle, int type) {
	int choise = JOptionPane.showConfirmDialog(ui, dialogMessage,
		windowTitle, 1);
	switch (choise) {
	case 0:
	    // yes
	    if (type == 1) {
		// save current sheet
		chooseDirectoryToSave();
		// clear the sheet
		dp.clearSheet();
	    } else if (type == 2) {
		// save current sheet
		chooseDirectoryToSave();
		// read from file
		chooseFileToRead();
		// apply new sheet
		StringOperation so = new StringOperation();
		List<IBaseFigure> figList = so.makeList(fileOperations
			.getResult());
		dp.setFigList(figList);
		dp.repaint();
	    } else if (type == 3) {
		chooseDirectoryToSave();
	    }
	    break;
	case 1:
	    // no
	    if (type == 1) {
		dp.clearSheet();
	    } else if (type == 2) {
		chooseFileToRead();
		StringOperation so = new StringOperation();
		List<IBaseFigure> figList = so.makeList(fileOperations
			.getResult());
		dp.setFigList(figList);
		dp.repaint();
	    } else {
		return;
	    }
	    break;
	case 2:
	    // cancel option
	    return;

	default:
	    break;
	}
    }

    /**
     * Shows the fileChooser so the user can choose the destination directory
     * where to save the current sheet.
     * 
     * @param sourceFileName
     *                The source file name to be set in the file chooser.
     */
    public void chooseDirectoryToSave() {
	if (fChooser == null) {
	    fChooser = new JFileChooser();
	}
	int returnVal = fChooser.showSaveDialog(ui);
	if (returnVal == JFileChooser.APPROVE_OPTION) {
	    File path = fChooser.getSelectedFile();
	    if (fileOperations.saveFile(path, dp.getFigList())) {
		ui.setWarnings(Const.WARNING_FILE_SAVED, 2);
	    }
	} else {
	}
    }

    /**
     * Choose a file to be read.
     */
    public void chooseFileToRead() {
	if (fChooser == null) {
	    fChooser = new JFileChooser();
	}
	fChooser.setCurrentDirectory(null);
	int returnVal = fChooser.showOpenDialog(ui);
	if (returnVal == JFileChooser.APPROVE_OPTION) {
	    File path = fChooser.getSelectedFile();
	    fileOperations.readFile(path);
	} else {
	}
    }

    /**
     * Setter method used to set up a class fields.
     * 
     * @param ui
     *                a reference to the user interface
     * @param fileOperation
     *                a reference to the utility class for file operations
     */
    public void setUInterface(UserInterface ui, FileOperation fileOperation) {
	this.ui = ui;
	this.dp = ui.getDp();
	this.da = ui.getDrawAction();
	this.fileOperations = fileOperation;
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
	showConfirmationDialog(Const.DIALOG_CLOSING_APPLICATION,
		Const.TITLE_APPLICATION_CLOSING, 3);
	System.exit(1);
    }

    /**
     * @return the dp
     */
    public DrawingPanel getDp() {
	return dp;
    }

    /**
     * @param dp
     *                the dp to set
     */
    public void setDp(DrawingPanel dp) {
	this.dp = dp;
    }
}
