package com.cit.jcourse.svelikov.theme8.command;

import java.math.BigDecimal;

/**
 * Add two BigDecimal numbers.
 * 
 * @author Svilen Velikov
 * @version 07.08.07
 */
public class Add implements Command {

	/**
	 * Initializes the two number
	 * @param fNum
	 * @param sNum
	 */
	public Add(BigDecimal fNum, BigDecimal sNum) {
		firstNumber  = fNum;
		secondNumber = sNum;
	}
	
	/** The first number. */
	private BigDecimal firstNumber;
	
	/** The second number. */
	private BigDecimal secondNumber;
	
	/**
	 * Applies addition over two numbers.
	 */
	public void execute() {
		BigDecimal result = firstNumber.add(secondNumber);
		System.out.println("\n"+firstNumber+" + "+secondNumber+" = "+result);
	}
}
