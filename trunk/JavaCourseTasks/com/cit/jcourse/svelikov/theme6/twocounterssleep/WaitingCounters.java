/**
 * 
 */
package com.cit.jcourse.svelikov.theme6.twocounterssleep;

/**
 * A class that increments and prints the content of the counter
 * until it founds that should stop and return. Besides that
 * it should waits for a while after avery counting.
 * 
 * @author Svilen Velikov
 * @version 10.04.07
 */
public class WaitingCounters implements Runnable{
	
	/** the thread's reference */
	private Thread tr;
	
	/** flag that says when the thread should dye */
	private static boolean toDie;
	
	/** counter */
	private int count;
	
	/** limit */
	private int end;
	
	/** Initializes the class variables and starts the thread */
	public WaitingCounters(int count, int end) {
		this.count = count;
		this.end = end;
		toDie = false;
		tr = new Thread(this);
		tr.start();
	}
	
	/**
	 * Prints and increments the content of the counter then
	 * it should wait for a while. After that checks whether
	 * the limit is riched. If it is then sets the flag toDie to
	 * be true. Finaly check the same flag and returns if it is
	 * set to true. Otherwise it continues with looping.
	 */
	synchronized public void run() {
		
		while ( true ) {
			System.out.println("counter = "+count);
			count++;
			try {
				wait(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if ( count == end ) {
				toDie = true;
			}
			if ( toDie ) return;
		}
	}
}