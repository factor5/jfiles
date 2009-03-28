package com.cit.jcourse.svelikov.theme8.abstractfactory;

/**
 * This class provides a method to switch from
 * big letters to small.
 * 
 * @author Svilen Velikov
 * @version 31.07.07
 */
public class SmallLetters implements DataWorker {
		
	/**
	 * Stores the data that should be proccessed and
	 * calls the method to deal with it.
	 * @param n The data to proccess.
	 */
	public SmallLetters(String d) {
		data = d;
		switchToSmall();
	}
	
	/**
	 * A default constructor.
	 */
	public SmallLetters() {}
	
	/** Holds the data that should be proccessed. */
	private String data;
	
	/**
	 * Turns the letters into Lower.
	 */
	public void switchToSmall() {
		data = data.toLowerCase();
	}

	/**
	 * Prints out the proccessed data.
	 */
	public void prt() {
		System.out.println(data);
	}
	
	/**
	 * Sets the data and calls the method to process it.
	 */
	public void setAndChangeData(String aData) {
		data = aData;
		switchToSmall();
	}
}
