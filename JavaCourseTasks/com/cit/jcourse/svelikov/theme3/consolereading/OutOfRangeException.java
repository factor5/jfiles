package com.cit.jcourse.svelikov.theme3.consolereading;

/**
 * new class exception which is used 
 * to be thrown when out of range data is
 * inserted
 * @author Svilen
 */
public class OutOfRangeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Invokes constructor for Throwable with String msg parameter.
	 * @param msg it's detail mesage for the cause to be thrown this exception
	 */
	public OutOfRangeException(String msg) {
		super(msg);
	}
}
