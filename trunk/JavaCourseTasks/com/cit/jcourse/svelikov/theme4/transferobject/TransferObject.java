/**
 * 
 */
package com.cit.jcourse.svelikov.theme4.transferobject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Transfers the content from an input stream
 * to output stream.
 * 
 * @author  Svilen Velikov
 * @version 04 27.03.07
 */
public class TransferObject {
	
	public TransferObject() {}
	
	/**
	 * Constructor for BufferedInputStream and BufferedOutputStream.
	 * 
	 * @param in  The InputStream.
	 * @param out The OutputStream.
	 * @throws FileNotFoundException if FileInputStream can't find file provided.
	 */
	public TransferObject(InputStream in, OutputStream out){
		this.input=in;
		this.output=out;
	}
	
	/** input stream */
	private InputStream input;
	
	/** output stream */
	private OutputStream output;
	
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
			while(( ch = input.read()) != -1 ) {	/*read bytes to the end of this stream*/
				/*determine where to start and stop writing to the output stream*/
				if((count >= startWrite)&&(count<=stopWrite)) {
					output.write(ch);				/*write a byte to output*/
					written++;
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
}