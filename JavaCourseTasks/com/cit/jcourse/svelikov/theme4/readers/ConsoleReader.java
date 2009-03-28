/**
 * 
 */
package com.cit.jcourse.svelikov.theme4.readers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * A class that read any type of data 
 * from the console.
 * @author Svilen
 * @version 02 03.09.07
 */
public class ConsoleReader {
	/** creates an input stream from keyboard */
	private static InputStream in = System.in;
	/** reads text from inputstream in a buffer */
	private static BufferedReader br = new BufferedReader( new InputStreamReader(in));
	
	/**
	 * Read line of data from console
	 * and returns it as a string. 
	 * @return string read from console.
	 * @throws IOException if an IO error occurs during the reading.
	 */
	public static String readString() {
		String readS = null;
		try {
			readS = br.readLine();
		} catch (IOException e) {
			System.err.println("Error!");
		}
		return readS;
	}
	
	/**
	 * Read line from console and parses data
	 * to int if it's possible.
	 * @return an int number wich is parsed from read string.
	 * @throws IOException if an IO error occurs during the reading.
	 * @throws NumberFormatException if the string read doesn't contain an
	 *         integer number.
	 */
	public static int readInt() {
		String str = null;
		int readI = 0;
		try {
			str = br.readLine();
		} catch (IOException e) {
			System.err.println("Error!");
		}
		try {
			readI = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			System.err.println("Only numbers are allowed!");
		}
		return readI;
	}
	
	/**
	 * Read a char from console.
	 * @return a single char read from console.
	 * @throws IOException if an IO error occurs during the reading.
	 */
	public static char readChar() {
		char readC = 0;
		try {
			readC = (char) br.read();
		} catch (IOException e) {
			System.err.println("Error!");
		}
		return readC;
	}
	
	/**
	 * Read line from console and try to parse to float
	 * if It's possible.
	 * @return a float number wich is parsed read string.
	 * @throws IOException if an IO error occurs during the reading.
	 */
	public static float readFloat() {
		String str = null;
		float readF = 0;
		try {
			str = br.readLine();
		} catch (IOException e) {
			System.err.println("Error!");
		}
		try {
			readF = Float.parseFloat(str);
		} catch (NumberFormatException e) {
			System.err.println("Only numbers are allowed!");
		}
		return readF;
	}
}
