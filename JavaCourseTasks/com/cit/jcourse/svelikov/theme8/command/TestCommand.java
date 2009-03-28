/**
 * 
 */
package com.cit.jcourse.svelikov.theme8.command;

/**
 * Starts the console from the Calculator class.
 * 
 * @author Svilen Velikov
 * @version 07.08.07
 */
public class TestCommand {
	
	/**
     * Creates an instance of the Calculator and starts 
 	 * the console.
	 * @param args
	 */
	public static void main(String[] args) {
		Calculator c = new Calculator();
		c.console();
	}
}
