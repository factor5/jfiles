/**
 * 
 */
package com.cit.jcourse.svelikov.theme7.downloadagent;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.cit.jcourse.svelikov.theme4.transferobject.TransferObject;

/**
 * Transfers any file from destination directory local based
 * or via the internet to the local directory choosen by user.
 * 
 * @author Svilen Velikov
 * @version 2007-7-16
 */
public class FileTransporter extends TransferObject{

	/**
	 * Constructs this object and initializes the streams
	 * @param in	The input stream.
	 * @param out   The output stream.
	 * @param vamp	The reference to the GUI.
	 */
	public FileTransporter(InputStream in, OutputStream out, TheVampireUI vamp) {
		input   = in;
		output  = out;
		vampire = vamp;
	}
	
	/** input stream */
	private InputStream input;
	/** output stream */
	private OutputStream output;
	/** The GUI's handler. */
	private TheVampireUI vampire;
	/** It is set to true when the transfer has to be stopped. */
	public static boolean stopped = false;
	
	/**
	 * Transfers the any content from input to output stream.
	 * If the two parameters given are greater from zero
	 * then only a portion of the data is transfered in conformity
	 * of these parameters. 
	 *  
	 * @param numberOfBytes The number of bytes to be passed to OutputStream.
	 * @param offset 		The number of bytes to be skiped before to start writing.
	 * 
	 * @return The number of bytes actualy written to the OutputStream.
	 * @throws IOException
	 */
	public int transfer(int numberOfBytes, int offset) throws IOException {
		int ch;										/*temp var needed fo reading from stream*/
		int count = 0;								/*counter for read bytes*/
		int written = 0;							/*count bytes written to OutputStream*/
		int startWrite = offset;					/*the offset*/
		int stopWrite  = offset+numberOfBytes-1;	/*where to stop writing to a stream*/
		
		try {	
			while(( ch = input.read()) != -1) {		/*read bytes to the end of this stream*/
				if(stopped) {
					input.close();
					output.close();
					stopped = false;
					break;
				}
				/*determine where to start and stop writing to the output stream*/
				if((count >= startWrite)&&(count<=stopWrite)) {
					output.write(ch);				/*write a byte to output*/
					written++;
					vampire.updateBar(written);
				}
				count++;
			}
		}
		finally {
			if( input != null )
				input.close();						/*close the input stream if necessery*/
			if( output != null )
				output.close();						/*close the output stream if necessery*/
		}
		return written;								/*number of bytes written to the stream*/
	}
	
	/**
	 *
	 */
	public void setStopped() {
		stopped = true;
	}
}
