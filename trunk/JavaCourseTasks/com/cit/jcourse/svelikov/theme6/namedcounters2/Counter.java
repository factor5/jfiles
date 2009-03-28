/**
 * 
 */
package com.cit.jcourse.svelikov.theme6.namedcounters2;

/**
 * Class counter that has only one method who increases
 * and prints the content of a counter.
 * @author Svilen Velikov
 * @version 21.04.07
 */
public class Counter {

	private String threadsName = "Thread1";
	
	/**
	 * Prints the content of the counter then notify the other
	 * thread that it should count and release the counter's 
	 * lock.
	 */
	public synchronized void printCounter(int counter, String currentThread) {
		while(!currentThread.equals(threadsName)){
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		System.out.println(threadsName+" > "+counter);
		if(threadsName.equals("Thread1"))
			threadsName = "Thread2";
		else
			threadsName = "Thread1";
		notifyAll();
	}
}
