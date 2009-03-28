package com.cit.jcourse.svelikov.theme8.command;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Devide two BigDecimal numbers.
 * 
 * @author Svilen Velikov
 * @version 07.08.07
 */
public class Division implements Command {

	/**
	 * Initializes the two numbers.
	 * @param fNum
	 * @param sNum
	 */
	public Division (BigDecimal fNum, BigDecimal sNum) {
		firstNumber  = fNum;
		secondNumber = sNum;
	}
	
	/** The first number. */
	private BigDecimal firstNumber;
	
	/** The second number. */
	private BigDecimal secondNumber;
	
	/**
	 * Applies divide over the two numbers.
	 */
	public void execute() {
		try {
			BigDecimal result = firstNumber.divide(secondNumber, MathContext.DECIMAL128);
			System.out.println("\n"+firstNumber+" / "+secondNumber+" = "+result);			
		} catch (ArithmeticException e) {
			System.out.println("Error in division!");
		}
	}

}
