package com.cit.jcourse.svelikov.theme3.stack;

/**
 * new class exception which is thrown
 * when there is no empty space in the stack
 * @author Svilen
 */
public class StackIsFullException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Invokes constructor for Throwable with String msg parameter.
	 * @param msg it's detail mesage for the cause to be thrown this exception
	 */
	public StackIsFullException(String msg) {
		super(msg);
	}
}
