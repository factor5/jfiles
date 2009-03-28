package com.cit.jcourse.svelikov.theme3.stack;

import java.util.EmptyStackException;

/**
 * Class used for test of the Stack class
 * @author Svilen
 */
public class Test {

	/**
	 * Insert elements in stack till max
	 * Remove elements till empty then insert again
	 * Catch exeptions that are thrown from
	 * add() and remove() method in Stack class
	 * @param args
	 */
	public static void main(String[] args) {
		
		Stack newStack = new Stack();
		
		/*attempt to insert more elements of max possible */
		try {
			newStack.add("1");
			newStack.add("10");
			newStack.add("20");
			newStack.add("30");
			newStack.add("40");
			newStack.add("50");
		} catch (StackIsFullException e) {
			e.getMessage();
			e.printStackTrace();
		}
		/*print all elements from the stack*/
		newStack.printAllElements();

		
		/*attempt to add another two elements in filled up stack*/
		try {
			newStack.add("011");
			newStack.add("022");
		} catch (StackIsFullException e1) {
			e1.getMessage();
			e1.printStackTrace();
		}
		/*print alements of the stack*/
		System.out.println("\n");
		newStack.printAllElements();

		
		/*attempt to remove more elements than it's possible*/
		try {
			newStack.remove();
			newStack.remove();
			newStack.remove();
			newStack.remove();
			newStack.remove();
			newStack.remove();			
		}
		catch (EmptyStackException e) {
			e.printStackTrace();
		}
		/*check whether It's empty?*/
		System.out.println("\n Празен стек:");
		newStack.printAllElements();
		
		/*attempt to add elements again*/
		try {
			newStack.add("1000");
			newStack.add("200");
			newStack.add("60");
			newStack.add("1");
			newStack.add("4000");
		} catch (StackIsFullException e) {
			e.getMessage();
			e.printStackTrace();
		}
		newStack.printAllElements();
	}

}
