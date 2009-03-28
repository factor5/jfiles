/**
 * 
 */
package com.cit.jcourse.svelikov.theme6.twocountersonethread;

/**
 * A class that increments and prints a counter
 * until it founds that should stop and return.
 * @author Svilen Velikov
 * @version 09.04.07
 */
public class Counters extends Thread{
	
	/** flag that says when the thread should return from it's run */
	private static boolean toDie;
	
	/** counter */
	private int count;
	
	/** limit */
	private int end;
	
	/** Initializes the class variables */
	public Counters(int count, int end) {
		this.count = count;
		this.end = end;
		toDie = false;
	}
	
	/**
	 * Prints and increments the content of the counter then
	 * check whether the limit is riched. If it is sets flag
	 * toDie to be true. Finaly check the same flag and returns
	 * if it is set to true. Otherwise it continues with looping.
	 */
	public void run() {
		while ( true ) {
			System.out.println("counter = "+count);
			count++;

			if ( count == end ) {	//if limit is riched 
				toDie = true;		//set flag to be true
			}
			if ( toDie ) return;	//if flag shows true then must die
		}
	}
}
