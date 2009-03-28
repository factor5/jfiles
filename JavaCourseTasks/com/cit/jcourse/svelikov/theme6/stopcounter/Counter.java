/**
 * 
 */
package com.cit.jcourse.svelikov.theme6.stopcounter;

/**
 * Counter class have run() method that increments
 * count instance variable until it is interrupted from
 * main thread.
 * @author Svilen Velikov
 */
public class Counter implements Runnable {
	
	/** reference for the thread  */
	private Thread counterThread;
	
	/** counter */
	private long count;
	
	/** limit for counter */
	private long end;
	
	/**
	 * Sets limit for counter and starts the thread.
	 */
	public Counter(long end) {
		this.end = end;
		counterThread = new Thread(this);
		counterThread.setPriority(1);
		counterThread.start();
	}
	
	/**
	 * Returns a reference for this thread.
	 * @return The thread's reference.
	 */
	public Thread getThreadRef() {
		return counterThread;
	}
	
	/**
	 * Returns the value of the counter.
	 * @return The counter.
	 */
	public long getNumber() {
		return count;
	}
	
	/**
	 * Increments counter until this thread is 
	 * interrupted from main or end is riched.
	 * @return 	if interupted
	 */
	public void run() {
		for (count = 0; count < end; count++) {
			if ( counterThread.isInterrupted()) {
				return;
			}
		}
	}
}
