package com.cit.jcourse.svelikov.theme7.downloadagent;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLConnection;

/**
 * The thread that realizes downloading the file.
 * 
 * @author Svilen Velikov
 * @version 2007-7-16 
 */
public class DownloadThread extends Thread {
	
	/** The input stream. */
	private BufferedInputStream in ;
	/** The output stream. */
	private BufferedOutputStream out;
	/** The created connection to the source. */
	private URLConnection connection;
	/** The destination directory. */
	private File output;
	/** The TransferObjects reference. */
	private FileTransporter fTransport;
	/** Refference to the GUI object. */
	private TheVampireUI vampire;
	
	/**       
	 * Constructs this thread.
	 * @param sourceConn The connection to the source.
	 * @param outputPath The destination directory.
	 */
	public DownloadThread(URLConnection sourceConn, File outputPath, TheVampireUI vamp) {	
		vampire    = vamp;
		connection = sourceConn;
		output     = outputPath;
	}
	
	/**
	 * Creates the input and the output stream and pass them to the
	 * constructor of the transfer object class that actualy performs
	 * the downloading and saving the file to the local mashine.
	 */
	public void run() {
		int bytesAvail = connection.getContentLength();
		int transferedBytes = 0;
		vampire.showPBar(0, bytesAvail);
		
		try {
			in  = new BufferedInputStream(connection.getInputStream());
			out = new BufferedOutputStream(new FileOutputStream(output));
			try {
				fTransport = new FileTransporter(in, out, vampire);
				transferedBytes = fTransport.transfer(bytesAvail, 0);
			} catch (IOException e) {
				e.printStackTrace();
				//vampire.setWarnings("Download failed!", 1);
			}
			if(bytesAvail == transferedBytes) {
				vampire.setWarnings("Download is completed!", 2);
				vampire.hidePBar();				
			} else {
				vampire.setWarnings("Download isn't completed!", 1);
				vampire.hidePBar();
			}
		} catch (IOException e) {
			vampire.setWarnings("Can't establish connection!", 1);
			vampire.hidePBar();
		} finally {
			try {
				if(in != null)
					in.close();
				if(out != null)	
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
