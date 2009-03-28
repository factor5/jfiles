/**
 * 
 */
package com.cit.jcourse.svelikov.theme6.twocountersonethread;

/**
 * A class that should create and start two threads for
 * Counters class.  
 * @author Svilen Velikov
 * @version 09.04.07
 */
public class Test {
	
	/**
	 * Creates two instances of Counters class that extends
	 * Thread providing two int numbers for the constructor
	 * to initialize the counter. Then starts these instances
	 * in separate threads.
	 * @param args
	 */
	public static void main(String[] args) {

	    Counters c;
		Counters d;
		
		c = new Counters(0,1000);
		d = new Counters(0,1500);
		c.start();
		d.start();
	}

}
