package com.cit.jcourse.svelikov.theme8.command;

import java.math.BigDecimal;

/**
 * Subtracts the two BigDecimal numbers.
 * 
 * @author Svilen Velikov
 * @version 07.08.07
 */
public class Subtract implements Command {

	/**
	 * Initializes the two numbers.
	 * @param fNum
	 * @param sNum
	 */
	public Subtract(BigDecimal fNum, BigDecimal sNum) {
		firstNumber  = fNum;
		secondNumber = sNum;
	}
	
	/** The first number. */
	private BigDecimal firstNumber;
	
	/** The second number. */
	private BigDecimal secondNumber;
	
	/**
	 * Applies the subtraction over two numbers.
	 */
	public void execute() {
		BigDecimal result = firstNumber.subtract(secondNumber);
		System.out.println("\n"+firstNumber+" - "+secondNumber+" = "+result);
	}

}
