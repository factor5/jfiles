package com.cit.jcourse.svelikov.theme8.proxy;

import java.math.BigInteger;

/**
 * Test for the IntegerProxy. The client works only with the proxy clas.
 * 
 * @author Svilen Velikov
 * @version 02.08.07
 */
public class TestProxy {
	
	/**
	 * Gets an instance of the IntegerProxy from the factory
	 * and uses that proxy to get access to the real Integer
	 * class methods.
	 * @param args
	 */
	public static void main(String[] args) {
		//create the factory
		IntegerFactory factory = new IntegerFactory();
		//get the proxy object from the factory
		IntegerProxy proxy = (IntegerProxy)factory.createInstance();
		
		//do some job with the Integer class via proxy
		for (int j = 0; j < 20; j++) {
			proxy.add(new BigInteger(""+(j*12)), new BigInteger(""+(j+10)));
			proxy.showResults();
		}
		
		for (int j = 0; j < 20; j++) {
			proxy.multy(new BigInteger(""+(j*12)), new BigInteger(""+(j+10)));
			proxy.showResults();
		}

		proxy.showResults();
	}
}
