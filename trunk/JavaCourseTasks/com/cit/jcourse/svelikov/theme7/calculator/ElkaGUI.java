package com.cit.jcourse.svelikov.theme7.calculator;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class ElkaGUI extends javax.swing.JFrame implements MouseListener {

    /**
     * 
     */
    private static final long serialVersionUID = -3476878757637452788L;

    private Logic logic;
    private String output = "";
    private JTextField display;
    private JButton b0;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JButton b7;
    private JButton b8;
    private JButton b9;
    private JButton bEqual;
    private JButton bAdd;
    private JButton bSubtr;
    private JButton bMulty;
    private JButton bDiv;
    private JButton bDot;
    private JButton bC;
    private JButton bCE;

    /**
     * Initialize the UI and gets a refererence of the Logic class that actualy
     * provides all functions for this application.
     */
    public ElkaGUI() {
	super("DeepThought");
	this.logic = new Logic();
	initGUI();
    }

    /**
     * Creates the GUI for this application.
     * 
     */
    private void initGUI() {
	try {
	    GridBagLayout thisLayout = new GridBagLayout();
	    thisLayout.rowWeights = new double[] { 0.1, 0.0, 0.1, 0.0, 0.1,
		    0.0, 0.1, 0.0, 0.1, 0.0, 0.1, 0.0 };
	    thisLayout.rowHeights = new int[] { 7, 7, 20, 7, 20, 7, 7, 7, 7, 7,
		    7, 7 };
	    thisLayout.columnWeights = new double[] { 0.0, 0.1, 0.0, 0.1, 0.0,
		    0.1, 0.0, 0.1, 0.0 };
	    thisLayout.columnWidths = new int[] { 7, 7, 7, 7, 7, 7, 7, 7, 7 };
	    getContentPane().setLayout(thisLayout);
	    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	    this.setResizable(false);
	    this.setLocation(400, 200);

	    display = new JTextField();
	    display.setFont(new Font("DicotMedium", 0, 18));
	    display.setEnabled(false);
	    display.setDisabledTextColor(new Color(0));
	    display.setBackground(new Color(255, 255, 255));
	    getContentPane().add(
		    display,
		    new GridBagConstraints(1, 0, 7, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			    new Insets(0, 0, 0, 0), 0, 0));
	    display.setHorizontalAlignment(JTextField.RIGHT);
	    display.setText("0");

	    b0 = new JButton();
	    b0.setFocusable(false);
	    getContentPane().add(
		    b0,
		    new GridBagConstraints(1, 10, 1, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			    new Insets(0, 0, 0, 0), 0, 0));
	    b0.setText("0");
	    b0.setName("0");
	    b0.addMouseListener(this);

	    b1 = new JButton();
	    b1.setFocusable(false);
	    getContentPane().add(
		    b1,
		    new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			    new Insets(0, 0, 0, 0), 0, 0));
	    b1.setText("1");
	    b1.setName("1");
	    b1.addMouseListener(this);

	    b2 = new JButton();
	    b2.setFocusable(false);
	    getContentPane().add(
		    b2,
		    new GridBagConstraints(3, 8, 1, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			    new Insets(0, 0, 0, 0), 0, 0));
	    b2.setText("2");
	    b2.setName("2");
	    b2.addMouseListener(this);

	    b3 = new JButton();
	    b3.setFocusable(false);
	    getContentPane().add(
		    b3,
		    new GridBagConstraints(5, 8, 1, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			    new Insets(0, 0, 0, 0), 0, 0));
	    b3.setText("3");
	    b3.setName("3");
	    b3.addMouseListener(this);

	    b4 = new JButton();
	    b4.setFocusable(false);
	    getContentPane().add(
		    b4,
		    new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			    new Insets(0, 0, 0, 0), 0, 0));
	    b4.setText("4");
	    b4.setName("4");
	    b4.addMouseListener(this);

	    b5 = new JButton();
	    b5.setFocusable(false);
	    getContentPane().add(
		    b5,
		    new GridBagConstraints(3, 6, 1, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			    new Insets(0, 0, 0, 0), 0, 0));
	    b5.setText("5");
	    b5.setName("5");
	    b5.addMouseListener(this);

	    b6 = new JButton();
	    b6.setFocusable(false);
	    getContentPane().add(
		    b6,
		    new GridBagConstraints(5, 6, 1, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			    new Insets(0, 0, 0, 0), 0, 0));
	    b6.setText("6");
	    b6.setName("6");
	    b6.addMouseListener(this);

	    b7 = new JButton();
	    b7.setFocusable(false);
	    getContentPane().add(
		    b7,
		    new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			    new Insets(0, 0, 0, 0), 0, 0));
	    b7.setText("7");
	    b7.setName("7");
	    b7.addMouseListener(this);

	    b8 = new JButton();
	    b8.setFocusable(false);
	    getContentPane().add(
		    b8,
		    new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			    new Insets(0, 0, 0, 0), 0, 0));
	    b8.setText("8");
	    b8.setName("8");
	    b8.addMouseListener(this);

	    b9 = new JButton();
	    b9.setFocusable(false);
	    getContentPane().add(
		    b9,
		    new GridBagConstraints(5, 4, 1, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			    new Insets(0, 0, 0, 0), 0, 0));
	    b9.setText("9");
	    b9.setName("9");
	    b9.addMouseListener(this);

	    bEqual = new JButton();
	    bEqual.setFocusable(false);
	    getContentPane().add(
		    bEqual,
		    new GridBagConstraints(7, 2, 1, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			    new Insets(0, 0, 0, 0), 0, 0));
	    bEqual.setText("=");
	    bEqual.setName("=");
	    bEqual.addMouseListener(this);

	    bAdd = new JButton();
	    bAdd.setFocusable(false);
	    getContentPane().add(
		    bAdd,
		    new GridBagConstraints(7, 10, 1, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			    new Insets(0, 0, 0, 0), 0, 0));
	    bAdd.setText("+");
	    bAdd.setName("+");
	    bAdd.addMouseListener(this);

	    bSubtr = new JButton();
	    bSubtr.setFocusable(false);
	    getContentPane().add(
		    bSubtr,
		    new GridBagConstraints(7, 8, 1, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			    new Insets(0, 0, 0, 0), 0, 0));
	    bSubtr.setText("-");
	    bSubtr.setName("-");
	    bSubtr.addMouseListener(this);

	    bDiv = new JButton();
	    bDiv.setFocusable(false);
	    getContentPane().add(
		    bDiv,
		    new GridBagConstraints(7, 4, 1, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			    new Insets(0, 0, 0, 0), 0, 0));
	    bDiv.setText("/");
	    bDiv.setName("/");
	    bDiv.addMouseListener(this);

	    bMulty = new JButton();
	    bMulty.setFocusable(false);
	    getContentPane().add(
		    bMulty,
		    new GridBagConstraints(7, 6, 1, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			    new Insets(0, 0, 0, 0), 0, 0));
	    bMulty.setText("*");
	    bMulty.setName("*");
	    bMulty.addMouseListener(this);

	    bDot = new JButton();
	    bDot.setFocusable(false);
	    getContentPane().add(
		    bDot,
		    new GridBagConstraints(5, 10, 1, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			    new Insets(0, 0, 0, 0), 0, 0));
	    bDot.setText(".");
	    bDot.setName(".");
	    bDot.addMouseListener(this);

	    bCE = new JButton();
	    bCE.setFocusable(false);
	    getContentPane().add(
		    bCE,
		    new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			    new Insets(0, 0, 0, 0), 0, 0));
	    bCE.setText("CE");
	    bCE.setName("l");
	    bCE.addMouseListener(this);

	    bC = new JButton();
	    bC.setFocusable(false);
	    getContentPane().add(
		    bC,
		    new GridBagConstraints(5, 2, 1, 1, 0.0, 0.0,
			    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			    new Insets(0, 0, 0, 0), 0, 0));
	    bC.setText("C");
	    bC.setName("c");
	    bC.addMouseListener(this);

	    pack();
	    setSize(300, 250);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * Handles a mousePressed event and invokes functions from Logic class
     * according to users action over the GUI.
     */
    public void mousePressed(MouseEvent me) {
	String name = me.getComponent().getName().toString();
	char buttonName = name.charAt(0);

	switch (buttonName) {
	case '0':
	case '1':
	case '2':
	case '3':
	case '4':
	case '5':
	case '6':
	case '7':
	case '8':
	case '9':
	    output = logic.setNumber(name); // process the event fired from
	    display.setText(output);
	    break; // the number buttons
	case '+':
	case '-':
	case '*':
	case '/':
	    output = logic.setOperator(name);// process the event fired from
	    display.setText(output);
	    break; // the operator buttons
	case '=':
	    output = logic.setEqual(); // process the event fired from
	    display.setText(output);
	    break; // the equal button
	case 'c':
	    logic.clear(); // process the event fired from
	    display.setText("0");
	    break; // the clear button
	case 'l':
	    logic.clearLastEntered(); // process the event fired from
	    display.setText("0");
	    break; // the clear last enetered button
	case '.':
	    output = logic.setDot(); // process the event fired from
	    display.setText(output);
	    break; // the dot button
	}

    }

    /**
     * Sets the system dependent LookAndFeel and shows this UI.
     * 
     * @param args
     */
    public static void main(String[] args) {
	try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception e) {
	}
	ElkaGUI elkaUI = new ElkaGUI();
	elkaUI.setVisible(true);
    }

    public void mouseReleased(MouseEvent arg0) {
    }

    public void mouseClicked(MouseEvent arg0) {
    }

    public void mouseEntered(MouseEvent arg0) {
    }

    public void mouseExited(MouseEvent arg0) {
    }
}