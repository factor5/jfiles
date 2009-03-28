package com.cit.jcourse.svelikov.theme8.abstractfactory;

/**
 * This class provides a method to switch from
 * small letters to caps.
 * 
 * @author Svilen Velikov
 * @version 31.07.07
 */
public class BigLetters implements DataWorker {

	/**
	 * Stores the data that should be proccessed and
	 * calls the method to deal with it.
	 * @param n The data to proccess.
	 */
	public BigLetters(String d) {
		data = d;
		switchToBig();
	}

	/**
	 * A default constructor.
	 */
	public BigLetters() {}
	
	/** Holds the data that should be proccessed. */
	private String data;
	
	/**
	 *	Turns the letters into Upper.
	 */
	public void switchToBig() {
		data = data.toUpperCase();
	}

	/**
	 * Prints out the proccessed data.
	 */
	public void prt() {
		System.out.println(data);	
	}

	/**
	 * Sets the data and calls the method
	 * to process it.
	 */
	public void setAndChangeData(String aData) {
		data = aData;
		switchToBig();
	}
}