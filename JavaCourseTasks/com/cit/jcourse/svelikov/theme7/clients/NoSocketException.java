package com.cit.jcourse.svelikov.theme7.clients;

/**
 * NoSocketException exception is thrown in clients body
 * when the server is stopped.
 * @author Svilen Velikov
 * @version 2007-7-25
 */
public class NoSocketException extends RuntimeException {

	private static final long serialVersionUID = 951549232582000303L;

	/**
	 * Invokes constructor for Throwable with String msg parameter.
	 * @param msg it's detail mesage for the cause to be thrown this exception
	 */
	public NoSocketException (String msg) {
		super(msg);
	}
}
