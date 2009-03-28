/**
 * 
 */
package com.cit.jcourse.svelikov.theme6.timeouthashtable;

import com.cit.jcourse.svelikov.theme4.readers.ConsoleReader;

/**
 * Thest class that creates a simple menu with five choices.
 * Every choice invokes a method from tested TimeoutHashtable
 * class.
 * 
 * @author Svilen Velikov
 * @version 18.04.07
 */
public class Test {

	/**
	 * Creates TimeoutHashtable instance providing an int number as 
	 * parameter that is a "time" in seconds for the period for elements
	 * to be kept in the table. Then the menu is created.
	 * @param args
	 */
	public static void main(String[] args) {
		
		TimeoutHashtable toh = new TimeoutHashtable(10);
		String value;
		String key;
		int ch;
		
		do {
			System.out.println("\n[1]..Put element, [2]..Get element, [3]..Remove element, " +
					"[4]..Show table, [5]..EXIT");
			ch = ConsoleReader.readInt();
			switch (ch) {
			/*reads key and value from console and pass them to put method*/
			case 1: System.out.println("key : ");
					key = ConsoleReader.readString();
					System.out.println("value : ");
					value = ConsoleReader.readString();
					toh.put(key, value);
					break;
			/*reads key and pass it to be checked for existence*/
			case 2: System.out.println("get key as value : ");
					key = ConsoleReader.readString();
					Object rtnValue = toh.get(key);
					if (rtnValue != null)
						System.out.println("Key->Value : "+key+" -> "+rtnValue);
					else
						System.out.println("No such key!");
					break;
			/*reads key that should be removed from the table*/
			case 3: System.out.println("to remove : ");
					key = ConsoleReader.readString();
					Object removed = toh.remove(key);
					if (removed == null)
						System.out.println("No such key!");
					break;
			/*calls a method to print the content of the table*/
			case 4: toh.printTable();
					break;
			case 5: break;//System.exit(0);
			default : System.out.println("Invalid choise!");
			}
		} while ( ch != 5 );
	}
}
