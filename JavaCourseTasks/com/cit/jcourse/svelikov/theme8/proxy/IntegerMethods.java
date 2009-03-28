package com.cit.jcourse.svelikov.theme8.proxy;

import java.math.BigInteger;

/**
 * Interface implemented by the Integer and IntegerProxy.
 * 
 * @author Svilen Velikov
 * @version 02.08.07
 */
public interface IntegerMethods {
	
	/** Should add two BigIntegers. */
	BigInteger add(BigInteger firstOp, BigInteger secondOp);
	
	/** Should subtract two BigIntegers. */
	BigInteger subtract(BigInteger firstOp, BigInteger secondOp);
	
	/** Should multiply two BigIntegers. */
	BigInteger multy(BigInteger firstOp, BigInteger secondOp);
    
	/** Should print out the results stored in a list. */
	void showResults();
}
