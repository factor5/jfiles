package com.cit.jcourse.svelikov.theme8.command;

import java.math.BigDecimal;

/**
 * Multiplies two BigDecimal numbers.
 * @author Svilen Velikov
 * @version 07.08.07
 */
public class Multiply implements Command {

	/**
	 * Initializes the two numbers.
	 * @param fNum
	 * @param sNum
	 */
	public Multiply(BigDecimal fNum, BigDecimal sNum) {
		firstNumber  = fNum;
		secondNumber = sNum;
	}
	
	/** The first number. */
	private BigDecimal firstNumber;
	
	/** The second number. */
	private BigDecimal secondNumber;
	
	/**
	 * Applies the multiplication over two numbers.
	 */
	public void execute() {
		BigDecimal result = firstNumber.multiply(secondNumber);
		System.out.println("\n"+firstNumber+" * "+secondNumber+" = "+result);
	}
}
