package com.cit.jcourse.svelikov.theme8.pool;

/**
 * Exception that is thrown when the pool is empty.
 * @author Svilen Velikov
 * @version 03.08.07
 */
public class NoPoolItemsException extends Exception {
	
	private static final long serialVersionUID = -6700572761517411984L;

	/**
	 * Invokes constructor for Throwable with String msg parameter.
	 * @param msg it's detail mesage for the cause to be thrown this exception
	 */
	public NoPoolItemsException (String msg) {
		super(msg);
	}
}
