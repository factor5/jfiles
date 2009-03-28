package com.cit.jcourse.svelikov.theme8.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

/**
 * The Calculator class represents a console based calculator
 * that realizes the Command pattern.
 * 
 * @author Svilen Velikov
 * @version 07.08.07
 */
public class Calculator {
	
	/**
	 * Creates the reader to get the users input from the console.
	 * Also gets the CalculatorCommands object thus we may pass to
	 * it real command object to execute.
	 */
	public Calculator() {
		in = new BufferedReader(
				new InputStreamReader(System.in));
		cExecutor = new CommandsExecutor();
	}
	
	/** The commands executor. */
	private CommandsExecutor cExecutor;
	
	/** The console reader. */
	private BufferedReader in;
	
	/** The first number. */
	private BigDecimal firstNumber;
	
	/** The second number. */
	private BigDecimal secondNumber;
	
	/** The operator. */
	private char operator;
	
	/**
	 * Reads from the console and invoke the callCommand method
	 * to execute the requested operation.
	 */
	public void console() {
		String ch = null;
		System.out.println("Enter the expression to be calculated :: ");
		try {
			while(!"y".equals(ch)) {
				System.out.println("-----------------------------------------------");
				System.out.println("First number : ");
					firstNumber = new BigDecimal(in.readLine());
				System.out.println("Operation [+ - * / ^] : ");
					operator = (in.readLine()).charAt(0);
				System.out.println("Second number : ");
					secondNumber = new BigDecimal(in.readLine());
				callCommand();
				System.out.println("\nEXIT  [y/n] ::");
					ch = in.readLine();
			}
		} catch (NumberFormatException e) {
			System.out.println("Wrong data format!\n\n");
			console();
		} catch (IOException e) {
			System.out.println("Error in reading!");
			console();
		}
	}
	
	/**
	 * Using operator argument this method passes the appropriate
	 * command object to the CommandsExecutor object.
	 */
	private void callCommand() {
		switch (operator) {
		case '+' : 
			cExecutor.makeCalculation(new Add(firstNumber, secondNumber));
			break;
		case '-' :
			cExecutor.makeCalculation(new Subtract(firstNumber, secondNumber));
			break;
		case '*' :
			cExecutor.makeCalculation(new Multiply(firstNumber, secondNumber));
			break;
		case '/' :
			cExecutor.makeCalculation(new Division(firstNumber, secondNumber));
			break;
		case '^' :
			cExecutor.makeCalculation(new Grading(firstNumber, secondNumber));
			break;
		default : System.out.println("Wrong operator!");
		}
	}
}
