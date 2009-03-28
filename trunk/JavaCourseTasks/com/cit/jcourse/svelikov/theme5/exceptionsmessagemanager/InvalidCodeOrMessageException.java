/**
 * 
 */
package com.cit.jcourse.svelikov.theme5.exceptionsmessagemanager;

/**
 * New Exception class that is thrown when is inserted a key
 * or message that is not in the table.
 * @author Svilen Velikov
 */
public class InvalidCodeOrMessageException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3429961631037171545L;

	/**
	 * Invokes constructor for Throwable with String msg parameter.
	 * @param msg it's detail mesage for the cause to be thrown this exception
	 */
	public InvalidCodeOrMessageException(String msg) {
		super(msg);
	}
}
