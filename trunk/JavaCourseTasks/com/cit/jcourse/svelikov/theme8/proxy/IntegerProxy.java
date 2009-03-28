package com.cit.jcourse.svelikov.theme8.proxy;

import java.math.BigInteger;

/**
 * Class IntegerProxy provides access to the any methods from Integer
 * class.
 * 
 * @author Svilen Velikov
 * @version 02.08.07
 */
public class IntegerProxy implements IntegerMethods {

	/** Get instance for the Integer class. */
	public IntegerProxy () {
		integer = new Integer();
	}
	
	/** The Integer class reference. */
	private Integer integer;
	
	/** Holds the result. */
	private BigInteger result;
	
	/**
	 * Adds two BigIntegers using Integer class object.
	 */
	public BigInteger add(BigInteger firstOp, BigInteger secondOp) {
		result = integer.add(firstOp, secondOp);
		return result;
	}
	
	/**
	 * Subtracts two BigIntegers using Integer class object.
	 */
	public BigInteger subtract(BigInteger firstOp, BigInteger secondOp) {
		result = integer.subtract(firstOp, secondOp);
		return result;
	}

	/**
	 * Multiplys two BigIntegers using Integer class object.
	 */
	public BigInteger multy(BigInteger firstOp, BigInteger secondOp) {
		result = integer.multy(firstOp, secondOp);
		return result;
	}
	
	/**
	 * Invokes the showResults method from the original class
	 * Integer.
	 */
	public void showResults() {
		integer.showResults();
	}
}
