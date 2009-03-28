package com.cit.jcourse.svelikov.theme3.exceptions1;

import java.io.IOException;

/**
 * @author Svilen Velikov
 * @version 03  02.28.07
 */
public class TestExceptions {

	/**
	 * Creates an instance of ExceptionsInSumString 
     * class and invoke its inputData method for test.
     * It also catches two type of exceptions which
     * may occur.
	 */
	public static void main(String[] args) {
		try {
		ExceptionsInSumString except = new ExceptionsInSumString();
		except.inputData();
		}
		catch ( NumberFormatException e ) {
			System.err.println( "It is allowed only numbers!" + e.getMessage());
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
