/**
 * 
 */
package ru.pe.graphix.utill;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import ru.pe.graphix.UserInterface;
import ru.pe.graphix.actions.UInterfaceActions;
import ru.pe.graphix.figures.IBaseFigure;

/**
 * This class provides some convenient method for file operations.
 * 
 * @author Svilen Velikov
 * 
 * 26.10.2008
 */
public class FileOperation {

    /**
     * A reference to the user interface.
     */
    private UserInterface ui;

    /**
     * The resulting string that was read from a file.
     */
    private StringBuffer result;

    /**
     * Initializing constructor.
     * 
     * @param ui
     *                the reference to the user interface
     */
    public FileOperation(UserInterface ui, UInterfaceActions uia) {
	this.ui = ui;
    }

    /**
     * 
     * 
     * @param path
     * @param expressions
     * @return
     */
    public boolean saveFile(File path, List<IBaseFigure> figList) {
	int dotPosition = path.toString().lastIndexOf('.');
	/* get the extension from the path specified */
	String ext = path.toString().substring(dotPosition + 1);
	BufferedWriter bw = null;
	try {
	    if ("gfx".equals(ext)) {
		bw = new BufferedWriter(new FileWriter(path));
	    } else {
		/* add appropriate extension to the filename */
		bw = new BufferedWriter(new FileWriter(path + ".gfx"));
	    }
	    for (IBaseFigure baseFigure : figList) {
		bw.write(baseFigure.toString());
		bw.newLine();
	    }
	    return true;
	} catch (IOException e) {
	    ui.setWarnings(Const.WARNING_SAVE_ERROR, 1);
	} finally {
	    try {
		if (bw != null) {
		    bw.close();
		}
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	return false;
    }

    /**
     * 
     * 
     * @param path
     */
    public void readFile(File path) {
	if (isCorrectExtension(path)) {
	    BufferedReader br = null;
	    result = new StringBuffer();
	    String line = null;
	    try {
		br = new BufferedReader(new FileReader(path));
		while ((line = br.readLine()) != null) {
		    result.append(line);
		    result.append(Const.LINE_SEPARATOR);
		}
	    } catch (IOException e) {
		ui.setWarnings(Const.WARNING_READ_ERROR, 1);
	    } finally {
		try {
		    if (br != null) {
			br.close();
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	} else {
	    return;
	}
    }

    /**
     * Checks if the file extension is *.gfx that is allowed as work file
     * extension.
     * 
     * @param path
     *                The path to any file to be checked.
     * @return true if the extension is *.gfx of false otherwise.
     */
    private boolean isCorrectExtension(File path) {
	int dotPosition = path.toString().lastIndexOf('.');
	/* get the file extension */
	String ext = path.toString().substring(dotPosition + 1);
	/* if not appropriate display error message */
	if (!"gfx".equals(ext)) {
	    ui.setWarnings(Const.WARNING_INCORRECT_EXTENSION, 2);
	    return false;
	}
	return true;
    }

    /**
     * @return the result
     */
    public StringBuffer getResult() {
	return result;
    }
}
