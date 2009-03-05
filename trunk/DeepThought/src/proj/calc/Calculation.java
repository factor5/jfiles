package proj.calc;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Calculation implements ActionListener, KeyListener, ItemListener,
	Const {

    private Elka elka;
    private StringBuilder result = new StringBuilder();
    private boolean isFloating = false;

    public Calculation(Elka elka) {
	this.elka = elka;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	getCurrentResult();
	dispatchCommand(e.getActionCommand());

    }

    public void getCurrentResult() {
	result.setLength(0);
	String curr = elka.getDisplay().getText();
	if (!isFloating) {
	    result.append(curr.substring(0, curr.length() - 1)); // remove trailing dot
	}
    }

    private void dispatchCommand(String actionCommand) {
	if (actionCommand.length() == 1) { // digit
	    appendChar(actionCommand, Const.DIGIT_TYPE);
	} else if (actionCommand.equals(Const.DOT)) {
	    appendDot();
	}
    }

    private void appendDot() {
	if (hasDot()) {
	    return;
	}
	isFloating = true;
	appendChar(Const.DOT_CHAR, Const.DOT_TYPE);
    }

    private void appendChar(String ch, byte type) {
	switch (type) {
	case 0:
	    result.append(ch);
	    showResult(result);
	    //isFloating = false;
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

    private boolean hasDot() {
	if (result.indexOf(Const.DOT_CHAR) == -1) { // there isn't any dot's
	    return false;
	}
	return true;
    }

    private boolean isLenghtOK(StringBuilder res) {
	if (res.length() < Const.MAX_LENGHT) {
	    return true;
	}
	return false;
    }

    private void showResult(StringBuilder res) {
	elka.getDisplay().setText(Const.EMPTY_STRING);
	if (hasDot()) {
	    elka.getDisplay().setText(res.toString());
	} else {
	    elka.getDisplay().setText(res.append(Const.DOT_CHAR).toString());
	}

    }

    @Override
    public void keyPressed(KeyEvent e) {

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
