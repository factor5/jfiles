/**
 * 
 */
package com.cit.jcourse.svelikov.theme6.namedcounters;

/**
 * A class that should create, name and start two threads for
 * Counters class.  
 * @author Svilen Velikov
 * @version 21.04.07
 */
public class Test {

	/**
	 * Creates two instances of PoliteThread class that implements
	 * Runnable interface providing counter object and string name
	 * for the constructor.
	 * @param args
	 */
	public static void main(String[] args) {
		
		Counter count = new Counter(5);
		
		PoliteThread nt1;
		PoliteThread nt2;
		
		nt1 = new PoliteThread(count, "Thread1");
		nt2 = new PoliteThread(count, "Thread2");
	}
}
