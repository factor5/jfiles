package com.cit.jcourse.svelikov.theme8.pool;

/**
 * This class has a method to calculate the PI number with
 * given precise. It runs in a thread so many of it can be
 * started if need. The limit is given in the pool that holds
 * a couple of those classes ready to be acquired.
 * 
 * @author Svilen Velikov
 * @version 03.08.07
 */
public class CalculatingThread extends Thread implements Item {
	
	/**
	 * Initialize the field that shows what precise to have
	 * for the calculated PI number.
	 * @param i
	 */
	public CalculatingThread(long i) {
		precise = i;
	}
	
	/** The precise for the calculation. */
	private long precise;

	/** The result from the calculation. */
	private double result;
	
	/** The flag to tell this thread that it was acquired
	 *  and it may do its job. */
	private boolean acquired;
	
	/**
	 * Calculates the PI number when is acquired and then goes in
	 * waiting status for another request. 
	 */
	public synchronized void run() {
		while(true) {
			if(acquired) {
				double total = 0.0;
		        for (long j = 1; j <= precise; j += 4) {
		            total += 1.0 / j - 1.0 / (j+2);  
		        }
		        result = 4 * total;
		        System.out.println("PI :: \n"+result);
		        acquired = false;
			} else {
				try {
					wait();
					acquired = true;
				} catch (InterruptedException e) {}	//to be acquired				
			}
		}
	}
	
	/**
	 * Notifies the thread that it has a job to do.
	 */
	public synchronized void wakeUp() {
		notify();
	}
	
	/**
	 * Returns the acquired flag status.
	 * @return
	 */
	public boolean getAcquired() {
		return acquired;
	}
}
