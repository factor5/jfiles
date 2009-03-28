/**
 * 
 */
package com.cit.jcourse.svelikov.theme6.timeouthashtable;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * A class that have methods for proccesing a hashtable. It allows to
 * put elements in the table for definited period of time. If any element
 * is updated or is checked from the get() method then the timer for that
 * element is going to be restarted automatically. If the time for any 
 * element is elapsed then it will be removed from the table.
 * 
 * @author Svilen Velikov
 * @version 18.04.07
 */
public class TimeoutHashtable {
	
	/** The table where the objects are stored. */
	private Hashtable workingTable;

	/** The table where the Timer threads are stored. */ 
	private Hashtable workingTimers;
	
	/** The timer reference. */
	private Timer timer;
	
	/** Number of seccond to keep keys in the table. */
	private int sec;
	
	/** Shows whether any object has update. */ 
	private boolean hasUpdate;
	
	/**
	 * Constructor that initialize the Hashtable field and sets the time
	 * for the timer.
	 * 
	 * @param table The hashtable to work with.
	 * @param sec   The time in secconds for the timer.
	 */
	public TimeoutHashtable(int sec) {
		workingTimers = new Hashtable(10);
		workingTable  = new Hashtable(10);
		hasUpdate     = false;
		this.sec      = sec;
	}
	
	/**
	 * Puts key/value pair in the hashtable. It trows an exception if any of
	 * the two or both parameters are null. If the key provided doesn't exists 
	 * in table then a new key/value pair is added and a timer is started.
	 * Otherwise the value is replaced with the new one and the timer is restarted.
	 * Every created Timer object is stored in workingTimers hashtable from where
	 * it can be taken later.
	 * 
	 * @param key   The key 
	 * @param value The value
	 */
	public synchronized void put(String key, Object value) {
	
		if((!"".equals(key)) && (!"".equals(value))) {  /*check whether key or value are null*/
			if(workingTable.containsKey(key)) {			/*if key exists then update*/
				workingTable.put(key, value);			/*update value*/
				hasUpdate = true;						
				Timer time = (Timer) workingTimers.get(key);/*get the timer for this key*/
				time.restart();							/*restart the timer*/
			} else {								
				workingTable.put(key, value);			/*put new key/value pair in the table*/
				hasUpdate = false;						/*it's false initialy*/
				timer = new Timer(key);					/*start new timer*/
				workingTimers.put(key, timer);			/*save newly created timer*/
			}		
		} else {										/*if key and/or value are null*/
			throw new NullPointerException("The key and the value can't be null!");
		}
	}
	
	/**
	 * Checks whether provided key maps any value in the table. If the
	 * key exists the value is returned and the timer for that key is 
	 * restarted. Otherwise null is returned.
	 * 
	 * @param key The key to check for.
	 * @return    The value for that key or null if no such key.
	 */
	public synchronized Object get(String key) {
		Object value;								/*the value to be return*/
		if(!"".equals(key)) {						/*key is not null so can get the value*/
			if(workingTable.containsKey(key)){		/*key exists in the table*/
				value = workingTable.get(key);
				hasUpdate = true;
				Timer time = (Timer) workingTimers.get(key);
				time.restart();						/*restart the timer*/
			} else {								/*key doesn't exist*/
				value = null;						/*should return null*/
			}			
		} else {									/*the key provided is null*/
			throw new NullPointerException("The key can't be null!");
		}
		return value;
	}
	
	/**
	 * Removes an element maped from the given key and stops the timer
	 * for that key.
	 * 
	 * @param key The that should be removed.
	 * @return 	  Null only if the key doesn't exist.
	 */
	public synchronized Object remove(String key) {
		Object removed;								/*holds removed value*/
		if(!"".equals(key)) {						/*key is not null*/
			if(workingTable.containsKey(key)){		/*key exists so can be removed*/
				removed = workingTable.remove(key);
				hasUpdate = false;					/*it's false so the timer will fall off*/
				Timer time = (Timer) workingTimers.get(key);
				time.restart();						
			} else {								/*key doesn't exist*/
				removed = null;						/*should return null*/
			}
		} else {									/*provided key is null*/
			throw new NullPointerException("The key can't be null!");
		}
		return removed;							
	}
	
	/**
	 * Prints out the content of the table.
	 */
	public void printTable() {
		if(!workingTable.isEmpty()) {
			System.out.println("The content of the table (key->value) :\n");
			for (Enumeration e = workingTable.elements(),k = workingTable.keys(); k.hasMoreElements();)
			       System.out.println(k.nextElement()+" -> "+e.nextElement());
		} else {
			System.out.println("The table is empty!\n");
		}
	}
	
	/**
	 * A thread class representing a timer.
	 * @author Svilen Velikov
	 */
	public class Timer implements Runnable{

		/** The key watched. */
		private String localKey;
		
		/**
		 * Initializes fields and starts the thread.
		 * @param key   The key that is watched.
		 */
		public Timer(String key) {
			localKey = key;
			new Thread(this, key).start();
		}
		
		/** Used to wake up the timer. */
		public synchronized void restart() {
			notify();
		}
		
		/**
		 * Counts the time for the given key. If the key has not been 
		 * accessed from get() method or updated for a given period of
		 * time then it is going to be removed after time expired. If 
		 * the key has been removed in the meantime then thread just
		 * returns from run.
		 */
		public synchronized void run() {
			while(!hasUpdate) {					
				try {
					wait(sec*1000);			/*waits defined period or until notify() is called*/		
				} catch (InterruptedException e) {}	
				if(!hasUpdate)  			/*if the key hasn't been updated*/
					break;					/*leave the loop*/
				else						/*the key is updated or visited*/
					hasUpdate = false;		/*should wait again*/	
			}
			if(workingTable.containsKey(localKey)) {
				workingTable.remove(localKey);/*removes the key */
				System.out.println("TIMEOUT for key -> "+localKey);				
			}
			return;							/*end for this thread*/
		}
	}
}
