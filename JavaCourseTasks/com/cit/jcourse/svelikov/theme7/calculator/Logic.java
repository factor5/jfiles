package com.cit.jcourse.svelikov.theme7.calculator;

/**
 * Logic class provides the whole functionality for this application.
 * 
 * @author Svilen Velikov
 * @version 11.06.07
 * 
 */
public class Logic {

    /** The numbers length that can be displayed. */
    private static final int RAZR = 20;
    /** To temporary storing the current number. */
    private String tempCache = "";
    /** The first operand. */
    private String cache1 = "";
    /** The second operand. */
    private String cache2 = "";
    /** The operator. */
    private String operator = "";
    /** To store the result. */
    private String result = "";
    /** Shows which is the current operand. */
    private int currentOperand = 0;
    /** Shows if the equal button was pressed. */
    private boolean equalIsEntered = false;
    /** Last entered number used if the last operation is repeated. */
    private String lastEnteredNum = "";

    /**
     * Gets for the processing the current operand.
     */
    private void getCurrentOperand() {
	if ("".equals(operator)) {
	    tempCache = cache1;
	    currentOperand = 1;
	} else {
	    tempCache = cache2;
	    currentOperand = 2;
	}
    }

    /**
     * Stores the processed operand in the appropriate cache.
     */
    private void setCurrentOperand() {
	switch (currentOperand) {
	case 1:
	    cache1 = tempCache;
	    break;
	case 2:
	    cache2 = tempCache;
	    break;
	}
    }

    /**
     * Clears the display and some of the fields.
     */
    public void clear() {
	tempCache = "";
	cache1 = "";
	cache2 = "";
	operator = "";
	result = "";
	currentOperand = 0;
	equalIsEntered = false;
    }

    /**
     * Clears the last entered number.
     */
    public void clearLastEntered() {
	getCurrentOperand();
	switch (currentOperand) {
	case 1:
	    cache1 = "";
	    break;
	case 2:
	    cache2 = "";
	    break;
	}
    }

    /**
     * Concatenates the digit entered to the current operand.
     * 
     * @param digit
     *                The digit entered.
     * @return The resulting number to be displayed.
     */
    public String setNumber(String digit) {
	getCurrentOperand();
	if ("0".equals(digit)) { // zero button is pressed
	    if ("0".equals(tempCache)) {// it's the first digit entered
		tempCache = "0";
	    } else {
		if (tempCache.length() < RAZR) {
		    tempCache += digit;
		}
	    }
	} else { // none zero button is pressed
	    if ("0".equals(tempCache)) {// it's the first digit enetered
		tempCache = digit;
	    } else {
		if (tempCache.length() < RAZR) {
		    tempCache += digit;
		}
	    }
	}
	if (currentOperand != 0) { // save the current number for use if last-
	    lastEnteredNum = tempCache;// operation was repeated
	}
	setCurrentOperand();
	return tempCache;
    }

    /**
     * Sets a decimal dot.
     * 
     * @return The resulting number to be displayed.
     */
    public String setDot() {
	getCurrentOperand();
	if (tempCache.indexOf(".") == -1) { // there isn't a dot in the number
	    if ("".equals(tempCache)) {
		tempCache += "0.";
	    } else if (tempCache.length() < RAZR) {
		tempCache += ".";
	    }
	}
	if (currentOperand != 0) { // save the current number for use if last-
	    lastEnteredNum = tempCache;// operation was repeated
	}
	setCurrentOperand();
	return tempCache;
    }

    /**
     * Stores the operator that is entered and invokes the calculate method if
     * it has to.
     * 
     * @param op
     *                The operator entered.
     * @return The result to be displayed.
     */
    public String setOperator(String op) {
	if (equalIsEntered && (!"".equals(cache2))) {
	    cache1 = cache2;
	    result = cache1;
	    cache2 = "";
	} else {
	    if ("".equals(operator)) { // there isn't operator entered
		if ("".equals(cache1)) {// any operator button is first pressed
		    cache1 = "0";
		    result = "0";
		} else {
		    result = cache1;
		}
	    } else if ((!"".equals(cache1)) && (!"".equals(cache2))) {
		lastEnteredNum = cache2;
		calculate();
	    } else if ("".equals(cache2)) {
		lastEnteredNum = cache1;
	    }
	}
	equalIsEntered = false;
	operator = op;
	return result;
    }

    /**
     * Checks wheter there are both the operator and the operands are entered
     * and calls the calculate method.
     * 
     * @return The result to be displayed.
     */
    public String setEqual() {
	if ((!"".equals(cache2)) && (!"".equals(operator))) {
	    calculate();
	} else if (("".equals(cache2)) && (!"".equals(operator))) {// repeat
								    // last
								    // operation
	    cache2 = lastEnteredNum;
	    calculate();
	} else if ("".equals(cache1)) {
	    result = "0";
	} else {
	    result = cache1;
	}
	equalIsEntered = true;
	return result;
    }

    /**
     * Does calculations according to the operator entered. It also checks for
     * division by zero operation first.
     * 
     * @return The result from the calculation.
     */
    private void calculate() {
	if (("/".equals(operator)) && ("0".equals(cache2))) {
	    clear();
	    result = "Division by zero error";

	} else {
	    try {
		if ("+".equals(operator)) {
		    result = Double.toString(Double.parseDouble(cache1)
			    + Double.parseDouble(cache2));
		} else if ("-".equals(operator)) {
		    result = Double.toString(Double.parseDouble(cache1)
			    - Double.parseDouble(cache2));
		} else if ("*".equals(operator)) {
		    result = Double.toString(Double.parseDouble(cache1)
			    * Double.parseDouble(cache2));
		} else {
		    result = Double.toString(Double.parseDouble(cache1)
			    / Double.parseDouble(cache2));
		}
	    } catch (NumberFormatException e) {
	    }
	}
	removeTrailingZeroes();
	cache1 = result;
	cache2 = "";
    }

    /**
     * Removes the trailing zeroes from the floating point result that should be
     * displayed. That is needed because of the type Double used for the
     * calculations.
     */
    private void removeTrailingZeroes() {
	if (result.indexOf(".") != -1) {
	    while (result.endsWith("0")) {
		result = result.substring(0, result.length() - 1);
		if (result.endsWith(".")) {
		    result = result.substring(0, result.length() - 1);
		    break;
		}
	    }
	}
    }
}
