/**
 * 
 */
package com.cit.jcourse.svelikov.theme6.twocounterssleep;

/**
 * A class that should create and start two threads for
 * SleepingCounters class and two for WaitingCounters.
 * @author Svilen Velikov
 * @version 09.04.07
 */
public class Test {

	/**
	 * Creates four instances of Counters class that extends
	 * Thread providing two int numbers for the constructor
	 * to initialize the counter. Then threads are started.
	 * @param args
	 */
	public static void main(String[] args) {
		
	    SleepingCounters sc1;
		SleepingCounters sc2;
		sc1 = new SleepingCounters(0,10);
		sc2 = new SleepingCounters(0,20);
		sc1.start();
		sc2.start();
		
		/*
	    WaitingCounters wc1;
		WaitingCounters wc2;
		wc1 = new WaitingCounters(0,10);
		wc2 = new WaitingCounters(0,20);*/
	}
}
