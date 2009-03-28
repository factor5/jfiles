/**
 * 
 */
package com.cit.jcourse.svelikov.theme4.filewriter;

import java.io.IOException;

/**
 * Test class that gets instance of the 
 * WriteToFile class and invokes its methods
 * for creating a new file and for reading data
 * from console which has to be writen into the
 * file created.
 * @author Svilen
 * @version 02 03.08.07
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WriteToFile wtf = new WriteToFile();
		try {
			wtf.handle();
		} catch (IOException e) {
			System.err.println("File procces failed! Exit!");
			e.printStackTrace();
			System.exit(0);
		}
	}

}
