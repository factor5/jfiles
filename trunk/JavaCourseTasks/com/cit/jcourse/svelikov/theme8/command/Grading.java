package com.cit.jcourse.svelikov.theme8.command;

import java.math.BigDecimal;

/**
 * Makes rise on power over a BigDecimal number.
 * 
 * @author Svilen Velikov
 * @version 07.08.07
 */
public class Grading implements Command {

	/**
	 * Initializes the number and the grade.
	 * @param fNum
	 * @param sNum
	 */
	public Grading(BigDecimal fNum, BigDecimal sNum) {
		firstNumber  = fNum;
		grade = sNum.intValue();
	}
	
	/** The firstNumber. */
	private BigDecimal firstNumber;
	/** The grade. */
	private int grade;
	
	/**
	 * Applies the grading.
	 */
	public void execute() {
		try {
			BigDecimal result = firstNumber.pow(grade);
			System.out.println("\n"+firstNumber+" ^ "+grade+" = "+result);	
		} catch (ArithmeticException e) {
			System.out.println("Grade is out of range!");
		}
	}
}
