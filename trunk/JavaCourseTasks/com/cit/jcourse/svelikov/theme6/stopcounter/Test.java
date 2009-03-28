/**
 * 
 */
package com.cit.jcourse.svelikov.theme6.stopcounter;

import com.cit.jcourse.svelikov.theme4.readers.ConsoleReader;

/**
 * Starts Counter class in new Thread and stops the
 * counter when a char is inserted in main Thread.
 * @author Svilen Velikov
 * @version 06.04.07
 */
public class Test {

	/**
	 * Creates a new Thread for runnable class Counter.
	 * When the count thread is started it needs to enter
	 * a char in main thread to stop the thread and to 
	 * print the counter's number.   
	 * @param args
	 */
	public static void main(String[] args) {
		
		Counter c = new Counter(900000000);
		
		ConsoleReader.readChar();			
		c.getThreadRef().interrupt();
		System.out.println("Counter is stopped to : "+c.getNumber());
	}
}
