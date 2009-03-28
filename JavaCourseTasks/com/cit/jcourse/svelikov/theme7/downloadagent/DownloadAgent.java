/**
 * 
 */
package com.cit.jcourse.svelikov.theme7.downloadagent;

import java.io.File;
import java.io.IOException;
import java.net.*;

/**
 * This class provides methods for creating url, urlConnection and realize in
 * inner class thread the actual download.
 * 
 * @author Svilen Velikov
 * @version 2007-7-16
 */
public class DownloadAgent {

    /**
     * Constructs with a handler for TheVampireUI.
     * 
     * @param vamp
     *                The reference for the GUI object.
     */
    public DownloadAgent(TheVampireUI vamp) {
	vampire = vamp;
    }

    /** The thread that realizes the download task. */
    private DownloadThread dt;
    /** The GUI's handler. */
    private TheVampireUI vampire;

    /**
     * Checks the sourcepath provided by user if it's a correct URL or filepath.
     * 
     * @param sourcePath
     *                The sourcepath provided from user.
     */
    public void checkURL(String sourcePath) {
	File sPath = new File(sourcePath);
	URL url = null;
	File sourceFileName = null;
	try {
	    if (sPath.isFile()) { // if it's a real file from the file system
		sourcePath = sourcePath.replaceAll("\\\\", "/");
		sourcePath = "file:/" + sourcePath;// add a file protokol
		url = new URL(sourcePath);
	    } else {
		url = new URL(sourcePath); // if fails then check if it's a
					    // filepath
	    }
	} catch (MalformedURLException e1) {
	    vampire.setWarnings("Must enter a valid URL or filepath!", 1);
	    url = null;
	}
	if (url != null) { // succes in creating the url
	    sourceFileName = getSourceFileName(url);
	    vampire.chooseDirectoryToSave(sourceFileName, sourcePath);
	}
    }

    /**
     * Extracts the filename from this url.
     * 
     * @param url
     *                The source url.
     * @return The source filename
     */
    private File getSourceFileName(URL url) {
	String theUrl = url.toString();
	String file = theUrl.substring(theUrl.lastIndexOf("/") + 1);// cut the
								    // file name
	file = file.replaceAll("%20", " ");
	File sourceFileName = new File(file);
	return sourceFileName;
    }

    /**
     * Creates an URLConnection to the source.
     * 
     * @param sourcePath
     *                The url to the source.
     * @param outputPath
     *                The destination directory.
     */
    public void downloadFile(String sourcePath, File outputPath) {
	URL url = null;
	URLConnection sourceConn = null;
	try {
	    url = new URL(sourcePath);
	    sourceConn = url.openConnection();
	} catch (MalformedURLException e1) {
	    e1.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	// costructs a new thread for the download task.
	dt = new DownloadThread(sourceConn, outputPath, vampire);
	dt.start();
    }
}
