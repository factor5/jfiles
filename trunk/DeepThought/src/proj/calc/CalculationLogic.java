package proj.calc;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComponent;

/**
 * 
 * @author Svilen Velikov
 * 
 * 05.03.2009
 */
public class CalculationLogic implements ActionListener, KeyListener, ItemListener,
	Const {

    /**
     * Reference to gui.
     */
    private Elka elka;

    /**
     * The result (number taken from display) is stored in StringBuilder.
     */
    private StringBuilder result = new StringBuilder();

    // private StringBuilder buffer

    /**
     * Shows if the current displayed number is floating or not.
     */
    private boolean isFloating = false;

    /**
     * Shows if an error has occurred.
     */
    private boolean hasError = false;
    
    /**
     * Shows if any operation has been used already.
     */
    private boolean isOperationUsed = false;
    
    /**
     * Bundle with button labels.
     */
    private ResourceBundle bundle;
    
    /**
     * Contains button objects mapped to integer keys.
     */
    private Map<Integer, JButton> buttonMap;
    
    private StringBuilder operandOne;
    
    private String operator;

    /**
     * Constructor.
     * 
     * @param elka
     *                a reference to gui
     */
    public CalculationLogic(Elka elka) {
	this.elka = elka;
	bundle = ResourceBundle.getBundle("labels");
	buttonMap = elka.getButtonMap();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (hasError && !e.getActionCommand().equals(Const.CLEAR)) {
	    return;
	}
	// on every action event we take the number from the display and store
	// it in a variable for later procеssing
	getCurrentResult();
	// call dispatcher to decide what is next to do
	dispatchCommand(e.getActionCommand());

    }

    /**
     * Gets the currently displayed result on the display and stores it in a
     * global variable as first it is cleared. A check if the number is floating
     * is made so to remove the trailing dot if it is.
     */
    public void getCurrentResult() {
	result.setLength(0);
	String curr = elka.getDisplay().getText();
	if (!isFloating) {
	    // remove trailing dot
	    result.append(curr.substring(0, curr.length() - 1));
	} else {
	    result.append(curr);
	}
    }

    /**
     * Checks what is the actionCommand and takes decision what to do next.
     * 
     * @param actionCommand
     *                represents the key from the gui that is pressed.
     */
    private void dispatchCommand(String actionCommand) {
	if (actionCommand.length() == 1) { // is digit
	    appendChar(actionCommand, Const.DIGIT_TYPE);
	} else if (actionCommand.equals(Const.DOT)) {
	    appendDot();
	} else if (actionCommand.equals(Const.CLEAR)) {
	    clearAll();
	} else if (actionCommand.equals(Const.SIGN)) {
	    changeSign();
	} else if (actionCommand.equals(Const.FRAC)) {
	    calculateFraction();
	} else if (actionCommand.equals(Const.ADD)) {
	    applyOperation(Const.ADD);
	} else if (actionCommand.equals(Const.SUB)) {
	    applyOperation(Const.SUB);
	} else if (actionCommand.equals(Const.MUL)) {
	    applyOperation(Const.MUL);
	} else if (actionCommand.equals(Const.DIV)) {
	    applyOperation(Const.DIV);
	}
    }

    private void applyOperation(String operation) {
	if (isOperationUsed) {
	    // call calculate result
	    operator = operation;
	} else {
	    operandOne = result;
	}
    }
    
    private void invokeCalculation(String operator) {
	if (operandOne.length() != 0 && result.length() != 0) {
	    if (operator.equals(Const.ADD)) {
		
	    } else if (operator.equals(Const.SUB)) {

	    } else if (operator.equals(Const.MUL)) {
		
	    } else if (operator.equals(Const.ADD)) {
		
	    }
	}
    }
    
    /**
     * 
     */
    private void calculateFraction() {
	if (result.toString().equals(Const.RESULT_ZERO)) {
	    clearAll();
	    result.setLength(0);
	    result.append(bundle.getString("divByZero"));
	    showResult(result);
	    hasError = true;
	    return;
	} else {
	    boolean negative = isNegative();
	    if (negative) {
		result.deleteCharAt(0); // remove negative sign
	    }
	    BigDecimal decNum = new BigDecimal(result.toString());
	    BigDecimal one = new BigDecimal(1);
	    decNum = one.divide(decNum, MathContext.DECIMAL128);
	    if (hasDot(decNum.toString())) {
		isFloating = true;
	    }
	    if (negative) {
		updateResult(Const.NEGATIVE_SIGN + decNum);
	    } else {
		updateResult(decNum.toString());
	    }
	    showResult(result);
	}
    }

    private void updateResult(String newValue) {
	result.setLength(0);
	// TODO check for length and truncate
	result.append(newValue);
	if (isFloating) {
	    result = result.reverse();
	    while (result.charAt(0) == '0') {
		result.deleteCharAt(0);
	    }
	    result = result.reverse();
	}
	if (!isLenghtOK(result)) {
	    result = new StringBuilder(result.substring(0, 31));
	}
    }

    /**
     * Appends a dot sign to the result. A check is made for possible
     * duplication of dot sign.
     */
    private void appendDot() {
	if (hasDot(result.toString())) {
	    return;
	}
	// the number became a floating one
	isFloating = true;
	appendChar(Const.DOT_CHAR, Const.DOT_TYPE);
    }

    /**
     * Append a char to the end of the result buffer according to its type.
     * 
     * @param ch
     *                the character to be appended
     * @param type
     *                the type of the char (dot=0, digit=1)
     */
    private void appendChar(String ch, byte type) {
	switch (type) {
	case 0:
	    result.append(ch);
	    showResult(result);
	    // isFloating = false;
	    break;
	case 1:
	    if (result.toString().equals(Const.RESULT_ZERO)) {
		result.setLength(0);
	    }
	    if (!isLenghtOK(result)) {
		return;
	    }
	    result.append(ch);
	    showResult(result);
	    break;

	default:
	    break;
	}
    }

    /**
     * Checks if the result buffer contains any dot sign.
     * 
     * @return true if result contains dot sign and false otherwise
     */
    private boolean hasDot(String number) {
	if (number.indexOf(Const.DOT_CHAR) == -1) { // there isn't any dot's
	    return false;
	}
	return true;
    }

    /**
     * Checks if the length of the parameter is least than needed.
     * 
     * @param res
     * @return
     */
    private boolean isLenghtOK(StringBuilder res) {
	if (res.length() < Const.MAX_LENGHT) {
	    return true;
	}
	return false;
    }

    /**
     * 
     * @param res
     */
    private void showResult(StringBuilder res) {
	elka.getDisplay().setText(Const.EMPTY_STRING);
	if (hasDot(result.toString())) {
	    elka.getDisplay().setText(res.toString());
	} else {
	    elka.getDisplay().setText(res.append(Const.DOT_CHAR).toString());
	}

    }

    /**
     * Changes the numbers sign (+/-) if it is different from zero.
     */
    private void changeSign() {
	if (result.toString().equals(Const.RESULT_ZERO)) {
	    return;
	}
	if (isNegative()) {
	    result.deleteCharAt(0);
	} else {
	    result.insert(0, '-');
	}
	showResult(result);
    }

    /**
     * Checks if the result (number taken from the display) is negative one or
     * not.
     * 
     * @return true if is negative or false otherwise
     */
    private boolean isNegative() {
	char sign = result.charAt(0);
	if (sign == '-') {
	    return true;
	}
	return false;
    }

    /**
     * Resets the fields and clears the display.
     */
    private void clearAll() {
	result.setLength(0);
	result.append(Const.RESULT_ZERO);
	isFloating = false;
	hasError = false;
	showResult(result);
    }

    @Override
    public void keyPressed(KeyEvent e) {
	int keyCode = e.getKeyCode();
	JButton button;
	if (buttonMap.containsKey(keyCode)) {
	    button = buttonMap.get(keyCode);
	    button.doClick();
	}
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
	Object source = e.getItemSelectable();
	if (source == elka.getTextIsBold()) {
	    if (e.getStateChange() == ItemEvent.SELECTED) {
		elka.getDisplay().setFont(new Font("Arial", Font.BOLD, 12));
	    } else {
		elka.getDisplay().setFont(new Font("Arial", Font.PLAIN, 11));
	    }
	}
    }

}
