package com.cit.jcourse.svelikov.theme8.proxy;

import java.math.BigInteger;

/**
 * Class Integer provides some methods for simple calculations
 * over BigInteger objects.
 * 
 * @author Svilen Velikov
 * @version 02.08.07
 */
public class Integer implements IntegerMethods {

	/** Holds the result from the operation. */
	private BigInteger result; 
	
	/**
	 * Add two BigIntegers.
	 */
	public BigInteger add(BigInteger firstOp, BigInteger secondOp) {
		result = firstOp.add(secondOp);
		return result;
	}

	/** 
	 * Subtracts two BigIntegers.
	 */
	public BigInteger subtract(BigInteger firstOp, BigInteger secondOp) {
		result = firstOp.subtract(secondOp);
		return result;
	}

	/**
	 * Multiplies two BigIntegers.
	 */
	public BigInteger multy(BigInteger firstOp, BigInteger secondOp) {
		result = firstOp.multiply(secondOp);
		return result;
	}
	
	/**
	 * Prints out the content of the vector.
	 */
	public void showResults() {
		System.out.println("Result : "+result);
	}
}
