package proj.calc;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import info.clearthought.layout.*;

/**
 * @author 
 */
public class Elka extends JFrame implements Const {

    private JCheckBox textIsBold;
    private JTextField display;
    private JButton btnBACK;
    private JButton btnCE;
    private JButton btnC;
    private JButton btnMC;
    private JButton btn7;
    private JButton btn8;
    private JButton btn9;
    private JButton btnDIV;
    private JButton btnSQRT;
    private JButton btnMR;
    private JButton btn4;
    private JButton btn5;
    private JButton btn6;
    private JButton btnMUL;
    private JButton btnPERC;
    private JButton btnMS;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btnSUB;
    private JButton btnFRAC;
    private JButton btnMPLUS;
    private JButton btn0;
    private JButton btnSIGN;
    private JButton btnDOT;
    private JButton btnPLUS;
    private JButton btnRESULT;

    private Dimension btnPrefSize = new Dimension(30, 30);
    private Dimension btnMinSize = new Dimension(20, 20);
    private Dimension btnMaxSize = new Dimension(35, 35);
    private Insets btnMargin = new Insets(0, 0, 0, 0);
    private Font btnFont = new Font("Arial", Font.PLAIN, 11);
    private LineBorder btnBorder = new LineBorder(Color.black);

    public Elka() {
	initComponents();
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setSize(300, 250);
	setLocation(400, 200);
	setVisible(true);
    }

    public static void main(String[] args) {
	javax.swing.SwingUtilities.invokeLater(new Runnable() {
	    public void run() {
		Elka elka = new Elka();
	    }
	});
    }

    private void initComponents() {
	ResourceBundle bundle = ResourceBundle.getBundle("labels");
	textIsBold = new JCheckBox();
	display = new JTextField();
	btnBACK = new JButton();
	btnCE = new JButton();
	btnC = new JButton();
	btnMC = new JButton();
	btn7 = new JButton();
	btn8 = new JButton();
	btn9 = new JButton();
	btnDIV = new JButton();
	btnSQRT = new JButton();
	btnMR = new JButton();
	btn4 = new JButton();
	btn5 = new JButton();
	btn6 = new JButton();
	btnMUL = new JButton();
	btnPERC = new JButton();
	btnMS = new JButton();
	btn1 = new JButton();
	btn2 = new JButton();
	btn3 = new JButton();
	btnSUB = new JButton();
	btnFRAC = new JButton();
	btnMPLUS = new JButton();
	btn0 = new JButton();
	btnSIGN = new JButton();
	btnDOT = new JButton();
	btnPLUS = new JButton();
	btnRESULT = new JButton();

	Calculation calc = new Calculation(this);

	// ======== this ========
	Container contentPane = getContentPane();
	contentPane.setLayout(new TableLayout(new double[][] {
		{ TableLayout.FILL, TableLayout.FILL, TableLayout.FILL,
			TableLayout.FILL, TableLayout.FILL, TableLayout.FILL,
			TableLayout.FILL, 0.02 },
		{ TableLayout.FILL, 0.1, TableLayout.FILL, TableLayout.FILL,
			TableLayout.FILL, TableLayout.FILL, TableLayout.FILL,
			0.02 } }));
	((TableLayout) contentPane.getLayout()).setHGap(5);
	((TableLayout) contentPane.getLayout()).setVGap(5);

	// ---- textIsBold ----
	textIsBold.setText(bundle.getString("textIsBold.text"));
	textIsBold.setBorder(null);
	textIsBold.setMargin(new Insets(2, 2, 2, 2));
	textIsBold.addItemListener(calc);
	contentPane.add(textIsBold, new TableLayoutConstraints(0, 1, 0, 1,
		TableLayoutConstraints.CENTER, TableLayoutConstraints.FULL));

	// ---- display ----
	display.setEditable(false);
	display.setFont(btnFont);
	display.setBackground(Color.WHITE);
	display.setHorizontalAlignment(SwingConstants.RIGHT);
	display.setBorder(new BevelBorder(BevelBorder.LOWERED));
	display.setText("0.");
	contentPane.add(display, new TableLayoutConstraints(1, 1, 6, 1,
		TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

	// ---- btnBACK ----
	btnBACK.setText(bundle.getString("btnBACK.text"));
	btnBACK.setActionCommand(Const.BACKSPACE);
	btnBACK.setFont(btnFont);
	btnBACK.setMargin(btnMargin);
	btnBACK.setBorder(btnBorder);
	btnBACK.addActionListener(calc);
	btnBACK.addKeyListener(calc);
	contentPane.add(btnBACK, new TableLayoutConstraints(1, 2, 2, 2,
		TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

	// ---- btnCE ----
	btnCE.setText(bundle.getString("btnCE.text"));
	btnCE.setActionCommand(Const.CE);
	btnCE.setFont(btnFont);
	btnCE.setMargin(btnMargin);
	btnCE.setBorder(btnBorder);
	btnCE.addActionListener(calc);
	btnCE.addKeyListener(calc);
	contentPane.add(btnCE, new TableLayoutConstraints(3, 2, 4, 2,
		TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

	// ---- btnC ----
	btnC.setText(bundle.getString("btnC.text"));
	btnC.setActionCommand(Const.CLEAR);
	btnC.setFont(btnFont);
	btnC.setMargin(btnMargin);
	btnC.setBorder(btnBorder);
	btnC.addActionListener(calc);
	btnC.addKeyListener(calc);
	contentPane.add(btnC, new TableLayoutConstraints(5, 2, 6, 2,
		TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

	// ---- btnMC ----
	btnMC.setText(bundle.getString("btnMC.text"));
	btnMC.setActionCommand(Const.MC);
	btnMC.setFont(btnFont);
	btnMC.setMargin(btnMargin);
	btnMC.setPreferredSize(btnPrefSize);
	btnMC.setMinimumSize(btnMinSize);
	btnMC.setMaximumSize(btnMaxSize);
	btnMC.setFocusable(false);
	btnMC.setBorder(btnBorder);
	btnMC.setForeground(Color.red);
	btnMC.addActionListener(calc);
	btnMC.addKeyListener(calc);
	contentPane.add(btnMC, new TableLayoutConstraints(1, 3, 1, 3,
		TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

	// ---- btn7 ----
	btn7.setText(bundle.getString("btn7.text"));
	btn7.setActionCommand(Const.D7);
	btn7.setFont(btnFont);
	btn7.setMargin(btnMargin);
	btn7.setPreferredSize(btnPrefSize);
	btn7.setMinimumSize(btnMinSize);
	btn7.setMaximumSize(btnMaxSize);
	btn7.setFocusable(false);
	btn7.setBorder(btnBorder);
	btn7.addActionListener(calc);
	btn7.addKeyListener(calc);
	contentPane.add(btn7, new TableLayoutConstraints(2, 3, 2, 3,
		TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

	// ---- btn8 ----
	btn8.setText(bundle.getString("btn8.text"));
	btn8.setActionCommand(Const.D8);
	btn8.setFont(btnFont);
	btn8.setMargin(btnMargin);
	btn8.setPreferredSize(btnPrefSize);
	btn8.setMinimumSize(btnMinSize);
	btn8.setMaximumSize(btnMaxSize);
	btn8.setFocusable(false);
	btn8.setBorder(btnBorder);
	btn8.addActionListener(calc);
	btn8.addKeyListener(calc);
	contentPane.add(btn8, new TableLayoutConstraints(3, 3, 3, 3,
		TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

	// ---- btn9 ----
	btn9.setText(bundle.getString("btn9.text"));
	btn9.setActionCommand(Const.D9);
	btn9.setFont(btnFont);
	btn9.setMargin(btnMargin);
	btn9.setPreferredSize(btnPrefSize);
	btn9.setMinimumSize(btnMinSize);
	btn9.setMaximumSize(btnMaxSize);
	btn9.setFocusable(false);
	btn9.setBorder(btnBorder);
	btn9.addActionListener(calc);
	btn9.addKeyListener(calc);
	contentPane.add(btn9, new TableLayoutConstraints(4, 3, 4, 3,
		TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

	// ---- btnDIV ----
	btnDIV.setText(bundle.getString("btnDIV.text"));
	btnDIV.setActionCommand(Const.DIV);
	btnDIV.setFont(btnFont);
	btnDIV.setMargin(btnMargin);
	btnDIV.setPreferredSize(btnPrefSize);
	btnDIV.setMinimumSize(btnMinSize);
	btnDIV.setMaximumSize(btnMaxSize);
	btnDIV.setFocusable(false);
	btnDIV.setBorder(btnBorder);
	btnDIV.addActionListener(calc);
	btnDIV.addKeyListener(calc);
	contentPane.add(btnDIV, new TableLayoutConstraints(5, 3, 5, 3,
		TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

	// ---- btnSQRT ----
	btnSQRT.setText(bundle.getString("btnSQRT.text"));
	btnSQRT.setActionCommand(Const.SQRT);
	btnSQRT.setFont(btnFont);
	btnSQRT.setMargin(btnMargin);
	btnSQRT.setPreferredSize(btnPrefSize);
	btnSQRT.setMinimumSize(btnMinSize);
	btnSQRT.setMaximumSize(btnMaxSize);
	btnSQRT.setFocusable(false);
	btnSQRT.setBorder(btnBorder);
	btnSQRT.addActionListener(calc);
	btnSQRT.addKeyListener(calc);
	contentPane.add(btnSQRT, new TableLayoutConstraints(6, 3, 6, 3,
		TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

	// ---- btnMR ----
	btnMR.setText(bundle.getString("btnMR.text"));
	btnMR.setActionCommand(Const.MR);
	btnMR.setFont(btnFont);
	btnMR.setMargin(btnMargin);
	btnMR.setPreferredSize(btnPrefSize);
	btnMR.setMinimumSize(btnMinSize);
	btnMR.setMaximumSize(btnMaxSize);
	btnMR.setFocusable(false);
	btnMR.setBorder(btnBorder);
	btnMR.addActionListener(calc);
	btnMR.addKeyListener(calc);
	contentPane.add(btnMR, new TableLayoutConstraints(1, 4, 1, 4,
		TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

	// ---- btn4 ----
	btn4.setText(bundle.getString("btn4.text"));
	btn4.setActionCommand(Const.D4);
	btn4.setFont(btnFont);
	btn4.setMargin(btnMargin);
	btn4.setPreferredSize(btnPrefSize);
	btn4.setMinimumSize(btnMinSize);
	btn4.setMaximumSize(btnMaxSize);
	btn4.setFocusable(false);
	btn4.setBorder(btnBorder);
	btn4.addActionListener(calc);
	btn4.addKeyListener(calc);
	contentPane.add(btn4, new TableLayoutConstraints(2, 4, 2, 4,
		TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

	// ---- btn5 ----
	btn5.setText(bundle.getString("btn5.text"));
	btn5.setActionCommand(Const.D5);
	btn5.setFont(btnFont);
	btn5.setMargin(btnMargin);
	btn5.setPreferredSize(btnPrefSize);
	btn5.setMinimumSize(btnMinSize);
	btn5.setMaximumSize(btnMaxSize);
	btn5.setFocusable(false);
	btn5.setBorder(btnBorder);
	btn5.addActionListener(calc);
	btn5.addKeyListener(calc);
	contentPane.add(btn5, new TableLayoutConstraints(3, 4, 3, 4,
		TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

	// ---- btn6 ----
	btn6.setText(bundle.getString("btn6.text"));
	btn6.setActionCommand(Const.D6);
	btn6.setFont(btnFont);
	btn6.setMargin(btnMargin);
	btn6.setPreferredSize(btnPrefSize);
	btn6.setMinimumSize(btnMinSize);
	btn6.setMaximumSize(btnMaxSize);
	btn6.setFocusable(false);
	btn6.setBorder(btnBorder);
	btn6.addActionListener(calc);
	btn6.addKeyListener(calc);
	contentPane.add(btn6, new TableLayoutConstraints(4, 4, 4, 4,
		TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

	// ---- btnMUL ----
	btnMUL.setText(bundle.getString("btnMUL.text"));
	btnMUL.setActionCommand(Const.MUL);
	btnMUL.setFont(btnFont);
	btnMUL.setMargin(btnMargin);
	btnMUL.setPreferredSize(btnPrefSize);
	btnMUL.setMinimumSize(btnMinSize);
	btnMUL.setMaximumSize(btnMaxSize);
	btnMUL.setFocusable(false);
	btnMUL.setBorder(btnBorder);
	btnMUL.addActionListener(calc);
	btnMUL.addKeyListener(calc);
	contentPane.add(btnMUL, new TableLayoutConstraints(5, 4, 5, 4,
		TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

	// ---- btnPERC ----
	btnPERC.setText(bundle.getString("btnPERC.text"));
	btnPERC.setActionCommand(Const.PERC);
	btnPERC.setFont(btnFont);
	btnPERC.setMargin(btnMargin);
	btnPERC.setPreferredSize(btnPrefSize);
	btnPERC.setMinimumSize(btnMinSize);
	btnPERC.setMaximumSize(btnMaxSize);
	btnPERC.setFocusable(false);
	btnPERC.setBorder(btnBorder);
	btnPERC.addActionListener(calc);
	btnPERC.addKeyListener(calc);
	contentPane.add(btnPERC, new TableLayoutConstraints(6, 4, 6, 4,
		TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

	// ---- btnMS ----
	btnMS.setText(bundle.getString("btnMS.text"));
	btnMS.setActionCommand(Const.MS);
	btnMS.setFont(btnFont);
	btnMS.setMargin(btnMargin);
	btnMS.setPreferredSize(btnPrefSize);
	btnMS.setMinimumSize(btnMinSize);
	btnMS.setMaximumSize(btnMaxSize);
	btnMS.setFocusable(false);
	btnMS.setBorder(btnBorder);
	btnMS.addActionListener(calc);
	btnMS.addKeyListener(calc);
	contentPane.add(btnMS, new TableLayoutConstraints(1, 5, 1, 5,
		TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

	// ---- btn1 ----
	btn1.setText(bundle.getString("btn1.text"));
	btn1.setActionCommand(Const.D1);
	btn1.setFont(btnFont);
	btn1.setMargin(btnMargin);
	btn1.setPreferredSize(btnPrefSize);
	btn1.setMinimumSize(btnMinSize);
	btn1.setMaximumSize(btnMaxSize);
	btn1.setFocusable(false);
	btn1.setBorder(btnBorder);
	btn1.addActionListener(calc);
	btn1.addKeyListener(calc);
	contentPane.add(btn1, new TableLayoutConstraints(2, 5, 2, 5,
		TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

	// ---- btn2 ----
	btn2.setText(bundle.getString("btn2.text"));
	btn2.setActionCommand(Const.D2);
	btn2.setFont(btnFont);
	btn2.setMargin(btnMargin);
	btn2.setPreferredSize(btnPrefSize);
	btn2.setMinimumSize(btnMinSize);
	btn2.setMaximumSize(btnMaxSize);
	btn2.setFocusable(false);
	btn2.setBorder(btnBorder);
	btn2.addActionListener(calc);
	btn2.addKeyListener(calc);
	contentPane.add(btn2, new TableLayoutConstraints(3, 5, 3, 5,
		TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

	// ---- btn3 ----
	btn3.setText(bundle.getString("btn3.text"));
	btn3.setActionCommand(Const.D3);
	btn3.setFont(btnFont);
	btn3.setMargin(btnMargin);
	btn3.setPreferredSize(btnPrefSize);
	btn3.setMinimumSize(btnMinSize);
	btn3.setMaximumSize(btnMaxSize);
	btn3.setFocusable(false);
	btn3.setBorder(btnBorder);
	btn3.addActionListener(calc);
	btn3.addKeyListener(calc);
	contentPane.add(btn3, new TableLayoutConstraints(4, 5, 4, 5,
		TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

	// ---- btnSUB ----
	btnSUB.setText(bundle.getString("btnSUB.text"));
	btnSUB.setActionCommand(Const.SUB);
	btnSUB.setFont(btnFont);
	btnSUB.setMargin(btnMargin);
	btnSUB.setPreferredSize(btnPrefSize);
	btnSUB.setMinimumSize(btnMinSize);
	btnSUB.setMaximumSize(btnMaxSize);
	btnSUB.setFocusable(false);
	btnSUB.setBorder(btnBorder);
	btnSUB.addActionListener(calc);
	btnSUB.addKeyListener(calc);
	contentPane.add(btnSUB, new TableLayoutConstraints(5, 5, 5, 5,
		TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

	// ---- btnFRAC ----
	btnFRAC.setText(bundle.getString("btnFRAC.text"));
	btnFRAC.setActionCommand(Const.FRAC);
	btnFRAC.setFont(btnFont);
	btnFRAC.setMargin(btnMargin);
	btnFRAC.setPreferredSize(btnPrefSize);
	btnFRAC.setMinimumSize(btnMinSize);
	btnFRAC.setMaximumSize(btnMaxSize);
	btnFRAC.setFocusable(false);
	btnFRAC.setBorder(btnBorder);
	btnFRAC.addActionListener(calc);
	btnFRAC.addKeyListener(calc);
	contentPane.add(btnFRAC, new TableLayoutConstraints(6, 5, 6, 5,
		TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

	// ---- btnMPLUS ----
	btnMPLUS.setText(bundle.getString("btnMPLUS.text"));
	btnMPLUS.setActionCommand(Const.MPLUS);
	btnMPLUS.setFont(btnFont);
	btnMPLUS.setMargin(btnMargin);
	btnMPLUS.setPreferredSize(btnPrefSize);
	btnMPLUS.setMinimumSize(btnMinSize);
	btnMPLUS.setMaximumSize(btnMaxSize);
	btnMPLUS.setFocusable(false);
	btnMPLUS.setBorder(btnBorder);
	btnMPLUS.addActionListener(calc);
	btnMPLUS.addKeyListener(calc);
	contentPane.add(btnMPLUS, new TableLayoutConstraints(1, 6, 1, 6,
		TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

	// ---- btn0 ----
	btn0.setText(bundle.getString("btn0.text"));
	btn0.setActionCommand(Const.D0);
	btn0.setFont(btnFont);
	btn0.setMargin(btnMargin);
	btn0.setPreferredSize(btnPrefSize);
	btn0.setMinimumSize(btnMinSize);
	btn0.setMaximumSize(btnMaxSize);
	btn0.setFocusable(false);
	btn0.setBorder(btnBorder);
	btn0.addActionListener(calc);
	btn0.addKeyListener(calc);
	contentPane.add(btn0, new TableLayoutConstraints(2, 6, 2, 6,
		TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

	// ---- btnSIGN ----
	btnSIGN.setText(bundle.getString("btnSIGN.text"));
	btnSIGN.setActionCommand(Const.SIGN);
	btnSIGN.setFont(btnFont);
	btnSIGN.setMargin(btnMargin);
	btnSIGN.setPreferredSize(btnPrefSize);
	btnSIGN.setMinimumSize(btnMinSize);
	btnSIGN.setMaximumSize(btnMaxSize);
	btnSIGN.setFocusable(false);
	btnSIGN.setBorder(btnBorder);
	btnSIGN.addActionListener(calc);
	btnSIGN.addKeyListener(calc);
	contentPane.add(btnSIGN, new TableLayoutConstraints(3, 6, 3, 6,
		TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

	// ---- btnDOT ----
	btnDOT.setText(bundle.getString("btnDOT.text"));
	btnDOT.setActionCommand(Const.DOT);
	btnDOT.setFont(btnFont);
	btnDOT.setMargin(btnMargin);
	btnDOT.setPreferredSize(btnPrefSize);
	btnDOT.setMinimumSize(btnMinSize);
	btnDOT.setMaximumSize(btnMaxSize);
	btnDOT.setFocusable(false);
	btnDOT.setBorder(btnBorder);
	btnDOT.addActionListener(calc);
	btnDOT.addKeyListener(calc);
	contentPane.add(btnDOT, new TableLayoutConstraints(4, 6, 4, 6,
		TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

	// ---- btnPLUS ----
	btnPLUS.setText(bundle.getString("btnPLUS.text"));
	btnPLUS.setActionCommand(Const.ADD);
	btnPLUS.setFont(btnFont);
	btnPLUS.setMargin(btnMargin);
	btnPLUS.setPreferredSize(btnPrefSize);
	btnPLUS.setMinimumSize(btnMinSize);
	btnPLUS.setMaximumSize(btnMaxSize);
	btnPLUS.setFocusable(false);
	btnPLUS.setBorder(btnBorder);
	btnPLUS.addActionListener(calc);
	btnPLUS.addKeyListener(calc);
	contentPane.add(btnPLUS, new TableLayoutConstraints(5, 6, 5, 6,
		TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

	// ---- btnRESULT ----
	btnRESULT.setText(bundle.getString("btnRESULT.text"));
	btnRESULT.setActionCommand(Const.RES);
	btnRESULT.setFont(btnFont);
	btnRESULT.setMargin(btnMargin);
	btnRESULT.setPreferredSize(btnPrefSize);
	btnRESULT.setMinimumSize(btnMinSize);
	btnRESULT.setMaximumSize(btnMaxSize);
	btnRESULT.setFocusable(false);
	btnRESULT.setBorder(btnBorder);
	btnRESULT.setForeground(Color.red);
	btnRESULT.addActionListener(calc);
	btnRESULT.addKeyListener(calc);
	contentPane.add(btnRESULT, new TableLayoutConstraints(6, 6, 6, 6,
		TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
    }

    /**
     * @return the textIsBold
     */
    public JCheckBox getTextIsBold() {
	return textIsBold;
    }

    /**
     * @param textIsBold
     *                the textIsBold to set
     */
    public void setTextIsBold(JCheckBox textIsBold) {
	this.textIsBold = textIsBold;
    }

    /**
     * @return the display
     */
    public JTextField getDisplay() {
	return display;
    }

    /**
     * @param display
     *                the display to set
     */
    public void setDisplay(JTextField display) {
	this.display = display;
    }
}
