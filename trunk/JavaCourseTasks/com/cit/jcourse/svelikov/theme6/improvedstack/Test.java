/**
 * 
 */
package com.cit.jcourse.svelikov.theme6.improvedstack;

import com.cit.jcourse.svelikov.theme4.readers.ConsoleReader;

/**
 * Test class that creates a menu to choose actions from.
 * @author Svilen Velikov
 * @version 14.04.07
 */
public class Test {

	/**
	 * Creates a new stack with initial capacity of 2 elements.
	 * Then creates a menu with three possible choices : add,
	 * remove and exit from program. Add and remove choices both
	 * calls methods from Stack class.
	 * @param args
	 */
	public static void main(String[] args) {

		Stack stack = new Stack(2);		
		String line;
		int ch;
		
		do {
			System.out.println("[1]..Add element, [2]..Remove element, [3]..EXIT");
			ch = ConsoleReader.readInt();
			switch (ch) {
			case 1: System.out.println("to add : ");
					line = ConsoleReader.readString();
					stack.add(line);
					break;
			case 2: System.out.println("to remove : ");
					stack.remove();
					break;
			case 3: break;
			default : System.out.println("Invalid choise!");
			}
		} while ( ch != 3 );
	}
}
