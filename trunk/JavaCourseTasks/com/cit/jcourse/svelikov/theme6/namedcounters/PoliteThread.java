/**
 * 
 */
package com.cit.jcourse.svelikov.theme6.namedcounters;

/**
 * Class thread that calls synchronized method from theCount
 * object thus only one thread at time can increment and print
 * the value of the counter.
 * @author Svilen Velikov
 * @version 21.04.07
 */
public class PoliteThread implements Runnable {
	
	/** The Counter's reference */
	private Counter theCount;
	
	/**
	 * Constructs and starts this thread. 
	 * @param count The Counter object.
	 * @param name  The name for the thread.
	 */
	public PoliteThread(Counter count, String name) {
		theCount = count;
		new Thread(this, name).start();
	}	
	
	/**
	 * Start counting by invoking the synchronized
	 * count method from the counter class.
	 */
	public void run() {
		theCount.count();
	}
}

