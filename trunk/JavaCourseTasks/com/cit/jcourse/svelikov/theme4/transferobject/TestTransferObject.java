/**
 * 
 */
package com.cit.jcourse.svelikov.theme4.transferobject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Provides InputStream and OutputStream instances
 * for TransferObject class and invokes its only
 * method that transfers some data between these 
 * two instances.
 * 
 * @author Svilen Velikov
 * @version 04 27.03.07
 */
public class TestTransferObject {

	/**
	 * Create instances of InputStream and OutputStream and pass them
	 * to the constructor of TransferObject class. Then invoking the
	 * transfer method with two integers as a parameters representing
	 * an offset to start from and number of bytes to be written to the
	 * output stream drives it to read from the source and to write to
	 * target output.
	 * @param args
	 */
	public static void main(String[] args) {
		
		int transfered = 0;
		try {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream("E:\\Photo\\S7300345.AVI"));			
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("C:\\321.jpg"));
			
			TransferObject to = new TransferObject( in, out);
			
			transfered = to.transfer(999999999,0);		/*numberOfBytes and offset in this order*/
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Transfered bytes : " + transfered);
	}
}
