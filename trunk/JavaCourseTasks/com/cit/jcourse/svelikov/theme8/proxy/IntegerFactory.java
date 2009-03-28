package com.cit.jcourse.svelikov.theme8.proxy;

/**
 * This factory creates instances of the proxy class.
 * @author Svilen Velikov
 * @version 02.08.07
 */
public class IntegerFactory {
	
	/** */
	public IntegerFactory() {}
	
	/**
	 * Creates instances of the proxy.
	 * @return The instance created.
	 */
	public IntegerMethods createInstance() {	 
		return new IntegerProxy();
	}
}
