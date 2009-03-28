package com.cit.jcourse.svelikov.theme8.pool;

/**
 * Interface implemented by the CalculatingThread.
 * @author Svilen Velikov
 * @version 03.08.07
 */
public interface Item {
	
	/** Needed to notify the waiting thread. */
	void wakeUp();
	
	/** To get the jobs status. */
	boolean getAcquired();
}
