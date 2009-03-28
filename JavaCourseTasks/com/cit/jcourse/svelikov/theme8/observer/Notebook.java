package com.cit.jcourse.svelikov.theme8.observer;

/**
 * This represents an object that is used as goods in the
 * store.
 * 
 * @author Svilen Velikov
 * @version 08.08.07
 */
public class Notebook {
	
	/** The price. */
	private int price;
	
	/**
	 * Sets the price.
	 * @param newPrice
	 */
	public void setPrice(int newPrice) {
		price = newPrice;
	}
	
	/**
	 * Returns the price for this one.
	 * @return
	 */
	public int getPrice() {
		return price;
	}
}
