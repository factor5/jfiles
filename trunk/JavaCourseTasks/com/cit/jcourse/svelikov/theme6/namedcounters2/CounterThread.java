/**
 * 
 */
package com.cit.jcourse.svelikov.theme6.namedcounters2;

/**
 * Class thread that calls synchronized method from theCount
 * object thus only one thread at time can increment and print
 * the value of the counter.
 * @author Svilen Velikov
 * @version 21.04.07
 */
public class CounterThread implements Runnable {

	private Counter theCount;
	
	private String threadsName;
	
	/**
	 * Constructs and starts this thread. 
	 * @param count The Counter object.
	 * @param name  The name for the thread.
	 */
	public CounterThread(Counter count, String name) {
		theCount 	= count;
		threadsName = name;
		new Thread(this, name).start();
	}
	
	/**
	 * Start counting.
	 */
	public void run() {
		for (int counter = 0; counter < 10; counter++) {
			theCount.printCounter(counter, threadsName);
		}
	}
}

