/**
 * 
 */
package proj.mod;

import java.awt.Cursor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import proj.mod.logger.WindowLogger;

/**
 * SubsModifier is a simple class that provide methods for tracing the directory
 * where the class is placed and to search for subtitle files with *.srt
 * extension. If such kind of file or files are found then they are parsed and
 * all the found tags like '<i>, </i>, <b> and </b>' in those files are removed
 * and files are saved back.
 * 
 * @author Svilen Velikov
 * 
 * 20.01.2009
 */
public class FileModifier extends JFrame {

    private static final long serialVersionUID = -9056510793502618924L;

    /**
     * Line separator.
     */
    private static final String SEPARATOR = System
	    .getProperty("line.separator");

    /**
     * Path list.
     */
    List<String> pathList = null;

    /**
     * Array containing the strings to be removed from the file.
     */
    private String[] strings = null;

    /**
     * Reference to window logger.
     */
    private WindowLogger log;

    /**
     * Message constants
     */
    public static String FILE_EXT_TYPE;
    private static final String CANT_SEARCH_DIR_ERROR = "Current directory cannot be traced!";
    private static final String NO_FILES_FOUND_INFO = "There aren't subtitle files!";
    private static final String IO_ERROR = "IO error!";
    private static final String READY_MESSAGE = "End of scanning!";
    private static final String TITLE_ERROR = "Error";
    private static final String TITLE_RESULT = "Result";
    private static final String EMPTY_STRING = "";
    private static final byte MESS_TYPE_ERR = 0;
    private static final byte MESS_TYPE_INFO = 1;

    /**
     * Displays a pop up message that tells the result, status or error.
     * 
     * @param msg
     *                the message that should be displayed.
     * @param title
     *                the title for this pop up window
     * @param type
     *                the type of the option pane (error=0, warning=1)
     */
    public void displayMessage(String msg, String title, byte type) {
	JOptionPane.showMessageDialog(null, msg, title, type);
    }

    /**
     * Main entry method that dispatches the work about modifying the files.
     * 
     * @param strings
     *                configurations read from property file
     */
    public void startModifying(String[] strings) {
	this.strings = strings;
	String curDirectory = "";
	try {
	    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

	    log = new WindowLogger();
	    pathList = new ArrayList<String>();

	    curDirectory = findCurrentDirectory();
	    log.appendLine("Current directory:", true);
	    log.appendLine(curDirectory, false);

	    File current = new File(curDirectory);

	    log.appendLine("\nFound files:", true);
	    findFiles(current);

	    if (pathList.size() == 0) {
		displayMessage(NO_FILES_FOUND_INFO, TITLE_RESULT,
			MESS_TYPE_INFO);
		return;
	    }

	    log.appendLine("\nStart parsing files:", true);
	    parseFiles();

	    log.appendLine("\nJob done!", true);
	    // displayMessage(READY_MESSAGE, TITLE_RESULT, MESS_TYPE_INFO);
	} catch (IOException e) {
	    e.printStackTrace();
	    displayMessage(e.getMessage(), TITLE_ERROR, MESS_TYPE_ERR);
	} finally {
	    this.setCursor(Cursor.getDefaultCursor());
	}

    }

    /**
     * Reads the content of the file denoted by the path parameter to an buffer.
     * 
     * @param path
     *                the source file
     * @return the buffer where the content of the file is stored after reading
     * @throws IOException
     * @throws FileNotFoundException
     */
    private StringBuilder readFile(String path) throws IOException {
	BufferedReader br = null;
	try {
	    br = new BufferedReader(new FileReader(path));
	} catch (FileNotFoundException e1) {
	    e1.printStackTrace();
	}

	StringBuilder bufer = new StringBuilder();
	String line = "";
	try {
	    while ((line = br.readLine()) != null) {
		bufer.append(line);
		bufer.append(SEPARATOR);
	    }
	    return bufer;
	} catch (IOException e) {
	    throw new IOException(IO_ERROR);
	} finally {
	    try {
		br.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }

    /**
     * Parses the files stored in the pathList and if any of the following tags '<i>,
     * </i>, <b> and </b>' are found then they are removed.
     * 
     * @throws IOException
     */
    private void parseFiles() throws IOException {
	try {
	    for (String path : pathList) {
		log.appendLine("Scanning file: " + path, false);
		StringBuilder bufer = readFile(path);
		String buferAsString = "";
		if (bufer.length() > 0) {
		    buferAsString = bufer.toString();
		    for (int i = 0; i < strings.length; i++) {
			buferAsString = buferAsString.replaceAll(strings[i],
				EMPTY_STRING);
		    }
		}
		writeFile(buferAsString, path);
		log.appendLine("Done", false);
	    }
	} catch (Exception e) {
	    throw new IOException(IO_ERROR);
	}
    }

    /**
     * Writes the content of the string buffer back to the file.
     * 
     * @param buferAsString
     *                the modified content of the subtitle file that is to be
     *                saved back in file
     * @param path
     *                the path to the destination file
     * @throws IOException
     */
    private void writeFile(String buferAsString, String path)
	    throws IOException {
	BufferedWriter bw = new BufferedWriter(new FileWriter(path));

	String[] arrBuf = buferAsString.split(SEPARATOR);
	int arrLen = arrBuf.length;
	try {
	    for (int i = 0; i < arrLen; i++) {
		bw.write(arrBuf[i]);
		bw.newLine();
	    }
	} finally {
	    bw.close();
	}
    }

    /**
     * Searches recursively the directory where this class is situated to find
     * any subtitle files. In case that any files are found they are stored in a
     * list.
     * 
     * @param current
     *                the directory where to search for subtitle files
     * @throws IOException
     */
    void findFiles(File current) throws IOException {
	try {
	    if (current.isFile()) {
		if (current.getName().endsWith(FILE_EXT_TYPE)) {
		    pathList.add(current.getCanonicalPath());
		    log.appendLine(current.getCanonicalPath(), false);
		}
	    } else {
		File[] siblings = current.listFiles();
		for (int i = 0; i < siblings.length; i++) {
		    findFiles(siblings[i]);
		}
	    }
	} catch (Exception e) {
	    throw new IOException(CANT_SEARCH_DIR_ERROR);
	}
    }

    /**
     * Finds the directory where this class is situated in.
     * 
     * @return the path that denotes the current directory for this class
     * @throws IOException
     */
    public String findCurrentDirectory() throws IOException {
	File file = new File(".");
	try {
	    String currentDirectory = file.getCanonicalPath();
	    return currentDirectory;
	} catch (Exception e) {
	    throw new IOException(CANT_SEARCH_DIR_ERROR);
	}
    }
}
