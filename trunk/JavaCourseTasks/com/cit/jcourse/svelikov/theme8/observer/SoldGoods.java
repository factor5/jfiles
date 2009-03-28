package com.cit.jcourse.svelikov.theme8.observer;

import java.util.Hashtable;

/**
 * This class keeps track for the sold in the store goods.
 * It has a table where the sold stuff are stored.
 * 
 * @author Svilen Velikov
 * @version 08.08.07
 */
public class SoldGoods {

	/**
	 * Creates the table where the unavailable goods are stored.
	 */
	public SoldGoods() {
		unavailable = new Hashtable();
	}
	
	/** The table with the sold goods. */
	private Hashtable unavailable;

	/**
	 * Adds the sold goods in the table.
	 * @param soldPC The sold one.
	 */
	public synchronized void addToList(Notebook soldPC) {
		String id = ""+soldPC.hashCode();
		unavailable.put(id, soldPC);
	}
	
	/**
	 * Removes any track from the table when it was supplied
	 * only if it had been in stock before. 
	 * @param suppliedPC The supplied doods.
	 */
	public synchronized void removeFromList(String suppliedPC) {		
		if(unavailable.containsKey(suppliedPC)) {
			unavailable.remove(suppliedPC);
			System.out.println("Supplied Model#"+suppliedPC+" - In Stock again!");			
		}
	}
}
