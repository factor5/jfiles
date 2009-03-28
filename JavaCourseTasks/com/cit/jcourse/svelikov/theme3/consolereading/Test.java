package com.cit.jcourse.svelikov.theme3.consolereading;

import java.io.IOException;

/**
 * @author Svilen Velikov
 * @version 02 02.28.07
 */
public class Test {

	/**
	 * Create an instance of ConsoleReading 
     * class and invoke its readNumbers method
	 */
	public static void main(String[] args) {
		ConsoleReading cr = new ConsoleReading();
		try {
			cr.readNumbers();
		}
		catch ( OutOfRangeException e ) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
