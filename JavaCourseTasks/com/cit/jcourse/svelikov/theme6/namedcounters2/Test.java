/**
 * 
 */
package com.cit.jcourse.svelikov.theme6.namedcounters2;

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
	       Counter count = new Counter();
           
	       CounterThread ct1;
	       CounterThread ct2;
	                                                                               
	       ct1 = new CounterThread(count, "Thread1");
	       ct2 = new CounterThread(count, "Thread2");
	}
}
