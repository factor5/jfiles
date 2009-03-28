/**
 * 
 */
package com.cit.jcourse.svelikov.theme4.textfilereverse;

import java.io.IOException;

/**
 * Tests TextFileReverse by invokation of 
 * its methods.
 * @author Svilen
 * @version 02 03.10.07
 */
public class Test {

	/**
	 * Gets an instance of TextFileReverse class
	 * and invokes its proccesFile method that ruls
	 * the whole procces. It also catches exception
	 * that may be thrown by any methods of the class.
	 * @param args
	 */
	public static void main(String[] args) {
		
		TextFileReverse tfr = new TextFileReverse();
		try {
			tfr.proccesFile();
		} catch (IOException e) {
			System.err.println("File procces failed! Exit!");
			e.printStackTrace();
			System.exit(0);
		}
	}

}
