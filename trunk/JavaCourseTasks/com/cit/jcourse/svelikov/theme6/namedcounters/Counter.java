/**
 * 
 */
package com.cit.jcourse.svelikov.theme6.namedcounters;

/**
 * Class counter that has only one method who increases
 * and prints the content of a counter.
 * @author Svilen Velikov
 * @version 21.04.07
 */
public class Counter {
	
	/** The end number for the counter. */
	public int end;
	
	/**
	 * Initialize the end instance field.
	 * @param end The end for the counter.
	 */
	public Counter(int end) {
		this.end = end;
	}
	
	/**
	 * Prints the content of the counter then notify the other
	 * thread that it should count and release the counter's 
	 * lock. First thread left the while loop notifyes the other
	 * that it should weakens up for last time to check the condition.
	 */
	public synchronized void count(){
		int counter = 0;
		
		while (counter < end) {
			counter++;
			System.out.println(Thread.currentThread().getName()+" > "+counter);
			notify();
			try {
				wait();				
			} catch (InterruptedException e) {}
		}
		notifyAll();		
	}
}
