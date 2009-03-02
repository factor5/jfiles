package ru.pe.graphix;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import ru.pe.graphix.actions.DrawingActions;
import ru.pe.graphix.actions.UInterfaceActions;
import ru.pe.graphix.utill.Const;
import ru.pe.graphix.utill.FileOperation;

/**
 * This class represents a graphic user interface for the application.
 * 
 * @author Svilen Velikov
 * 
 * 26.10.2008
 */
public class UserInterface extends JFrame {

    private static final long serialVersionUID = 1L;
    /** button */
    private JButton bDraw;
    /** button */
    private JButton bCircle;
    /** button */
    private JButton bLine;
    /** button */
    private JButton bColor;
    /** button */
    private JButton bNewSheet;
    /** button */
    private JButton bText;
    /** button */
    private JButton bSquare;
    /** button */
    private JButton bPolyline;
    /** menu bar */
    private JMenuBar menuBar;
    /** a menu component */
    private JMenu menu;
    /** a sub menu component */
    private JMenuItem menuItem;
    /** the screen's width */
    public int scrWidth;
    /** the screens height */
    public int scrHeight;
    /** an extended Canvas object with additional functions */
    private DrawingPanel dp;
    /** action class that provide methods for drawing */
    private DrawingActions drawAction;
    /** action class that deals with users activities over the user interface */
    private UInterfaceActions uiActions;

    /**
     * Constructs the UI for this client.
     */
    public UserInterface() {
	setTitle(Const.APP_TITLE);
	setIconImage(Toolkit.getDefaultToolkit().getImage(
		ClassLoader.getSystemResource("p.GIF")));
	try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception e) {
	    e.printStackTrace();
	}

	drawAction = new DrawingActions();
	uiActions = new UInterfaceActions();
	this.addWindowListener(uiActions);
	initGUI(this.getContentPane());
	drawAction.setUInterface(this);
	FileOperation fileOperation = new FileOperation(this, uiActions);
	uiActions.setUInterface(this, fileOperation);
    }

    /**
     * Creates the GUI.
     */
    public void initGUI(Container pane) {
	try {
	    // components are placed without layout
	    pane.setLayout(null);

	    // set up default used fonts
	    Font font12 = new Font(Const.FONT_FAMILY, 0, 12);
	    Font font10 = new Font(Const.FONT_FAMILY, 0, 10);

	    // creates a menu bar
	    menuBar = new JMenuBar();
	    menuBar.setBackground(new Color(173, 173, 173));

	    // File menu
	    menu = new JMenu(Const.MENU_FILE);
	    menu.setBackground(new Color(180, 180, 180));
	    menu.setFont(font12);
	    menu.setMnemonic(KeyEvent.VK_F);
	    menu.getAccessibleContext().setAccessibleDescription("");
	    menuBar.add(menu);

	    // Open file option
	    menuItem = new JMenuItem(Const.MI_OPEN_FILE, KeyEvent.VK_T);
	    menuItem.setActionCommand(Const.AC_OPEN);
	    menuItem.setFont(font12);
	    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,
		    ActionEvent.ALT_MASK));
	    menuItem.getAccessibleContext().setAccessibleDescription("");
	    menu.add(menuItem);
	    menuItem.addActionListener(uiActions);

	    // Save file option
	    menuItem = new JMenuItem(Const.MI_SAVE_FILE, KeyEvent.VK_B);
	    menuItem.setActionCommand(Const.AC_SAVE);
	    menuItem.setFont(font12);
	    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,
		    ActionEvent.ALT_MASK));
	    menuItem.getAccessibleContext().setAccessibleDescription("");
	    menu.add(menuItem);
	    menuItem.addActionListener(uiActions);

	    // Delete file option
	    menuItem = new JMenuItem(Const.MI_DEL_FILE, KeyEvent.VK_B);
	    menuItem.setActionCommand(Const.AC_DELETE);
	    menuItem.setFont(font12);
	    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3,
		    ActionEvent.ALT_MASK));
	    menuItem.getAccessibleContext().setAccessibleDescription("");
	    menu.add(menuItem);
	    menuItem.addActionListener(uiActions);

	    // Exit from application option
	    menu.addSeparator();
	    menuItem = new JMenuItem(Const.MI_EXIT, KeyEvent.VK_N);
	    menuItem.setActionCommand(Const.AC_EXIT);
	    menuItem.setFont(font12);
	    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4,
		    ActionEvent.ALT_MASK));
	    menuItem.getAccessibleContext().setAccessibleDescription("");
	    menu.add(menuItem);
	    menuItem.addActionListener(uiActions);

	    // Help menu
	    menu = new JMenu(Const.MENU_HELP);
	    menu.setBackground(new Color(180, 180, 180));
	    menu.setFont(font12);
	    menu.setMnemonic(KeyEvent.VK_H);
	    menu.getAccessibleContext().setAccessibleDescription("");

	    // Help topics option
	    menuItem = new JMenuItem(Const.MI_HELP_TOPICS, KeyEvent.VK_H);
	    menuItem.setActionCommand(Const.AC_HELP);
	    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5,
		    ActionEvent.ALT_MASK));
	    menuItem.getAccessibleContext().setAccessibleDescription("");
	    menu.add(menuItem);
	    menuItem.addActionListener(uiActions);

	    // About option
	    menu.addSeparator();
	    menuItem = new JMenuItem(Const.MI_ABOUT, KeyEvent.VK_A);
	    menuItem.setActionCommand(Const.AC_ABOUT);
	    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_6,
		    ActionEvent.ALT_MASK));
	    menuItem.getAccessibleContext().setAccessibleDescription("");
	    menu.add(menuItem);
	    menuItem.addActionListener(uiActions);

	    // set up the menu in the menu bar
	    menuBar.add(menu);
	    this.setJMenuBar(menuBar);

	    // creates the freeDraw button
	    ImageIcon imgDraw = new ImageIcon(Const.ICON_D);
	    bDraw = new JButton(imgDraw);
	    bDraw.setActionCommand(Const.AC_DRAW);
	    bDraw.setFocusable(true);
	    bDraw.setToolTipText(Const.TOOLTIP_BTN_DRAW);
	    bDraw.setFont(font12);
	    pane.add(bDraw);
	    bDraw.setVisible(true);
	    bDraw.setMargin(new Insets(2, 2, 2, 2));
	    bDraw.addActionListener(uiActions);

	    // creates the drawCircle button
	    ImageIcon imgCircle = new ImageIcon(Const.ICON_C);
	    bCircle = new JButton(imgCircle);
	    bCircle.setActionCommand(Const.AC_CIRCLE);
	    bCircle.setFocusable(true);
	    bCircle.setToolTipText(Const.TOOLTIP_BTN_CIRCLE);
	    bCircle.setFont(font12);
	    pane.add(bCircle);
	    bCircle.setVisible(true);
	    bCircle.setMargin(new Insets(2, 2, 2, 2));
	    bCircle.addActionListener(uiActions);

	    // creates the drawLine button
	    ImageIcon imgLine = new ImageIcon(Const.ICON_L);
	    bLine = new JButton(imgLine);
	    bLine.setActionCommand(Const.AC_LINE);
	    bLine.setFocusable(true);
	    bLine.setToolTipText(Const.TOOLTIP_BTN_LINE);
	    bLine.setFont(font12);
	    pane.add(bLine);
	    bLine.setVisible(true);
	    bLine.setMargin(new Insets(2, 2, 2, 2));
	    bLine.addActionListener(uiActions);

	    // creates the draw square button
	    ImageIcon imgSquare = new ImageIcon(Const.ICON_S);
	    bSquare = new JButton(imgSquare);
	    bSquare.setActionCommand(Const.AC_SQUARE);
	    bSquare.setFocusable(true);
	    bSquare.setToolTipText(Const.TOOLTIP_BTN_SQUARE);
	    bSquare.setFont(font10);
	    pane.add(bSquare);
	    bSquare.setVisible(true);
	    bSquare.setMargin(new Insets(2, 2, 2, 2));
	    bSquare.addActionListener(uiActions);

	    // creates the draw polyline button
	    ImageIcon imgPolyline = new ImageIcon(Const.ICON_M);
	    bPolyline = new JButton(imgPolyline);
	    bPolyline.setActionCommand(Const.AC_POLYLINE);
	    bPolyline.setFocusable(true);
	    bPolyline.setToolTipText(Const.TOOLTIP_BTN_POLYLINE);
	    bPolyline.setFont(font10);
	    pane.add(bPolyline);
	    bPolyline.setVisible(true);
	    bPolyline.setFocusable(true);
	    bPolyline.setMargin(new Insets(2, 2, 2, 2));
	    bPolyline.addActionListener(uiActions);

	    // creates the setColor button
	    ImageIcon imgColor = new ImageIcon(Const.ICON_P);
	    bColor = new JButton(imgColor);
	    bColor.setActionCommand(Const.AC_COLOR);
	    bColor.setFocusable(true);
	    bColor.setToolTipText(Const.TOOLTIP_BTN_COLOR);
	    bColor.setFont(font10);
	    pane.add(bColor);
	    bColor.setVisible(true);
	    bColor.setMargin(new Insets(2, 2, 2, 2));
	    bColor.addActionListener(uiActions);

	    // creates the setColor button
	    ImageIcon imgNewSheet = new ImageIcon(Const.ICON_N);
	    bNewSheet = new JButton(imgNewSheet);
	    bNewSheet.setActionCommand(Const.AC_NEW_SHEET);
	    bNewSheet.setFocusable(true);
	    bNewSheet.setToolTipText(Const.TOOLTIP_BTN_COLOR);
	    bNewSheet.setFont(font10);
	    pane.add(bNewSheet);
	    bNewSheet.setVisible(true);
	    bNewSheet.setMargin(new Insets(2, 2, 2, 2));
	    bNewSheet.addActionListener(uiActions);

	    // creates the writeText button
	    ImageIcon imgText = new ImageIcon(Const.ICON_T);
	    bText = new JButton(imgText);
	    bText.setActionCommand(Const.AC_TEXT);
	    bText.setFocusable(true);
	    bText.setToolTipText(Const.TOOLTIP_BTN_TEXT);
	    bText.setFont(font10);
	    pane.add(bText);
	    bText.setVisible(true);
	    bText.setMargin(new Insets(2, 2, 2, 2));
	    bText.addActionListener(uiActions);

	    // sets the dimensions of the components
	    Insets insets = pane.getInsets();
	    // Get the default toolkit
	    Toolkit toolkit = Toolkit.getDefaultToolkit();
	    // Get the current screen size
	    Dimension scrSize = toolkit.getScreenSize();
	    scrWidth = (int) scrSize.getWidth();
	    scrHeight = (int) scrSize.getHeight() - 50;

	    // set up the drawing panel
	    dp = new DrawingPanel();
	    dp.setSize(new Dimension());
	    dp.setBackground(Color.WHITE);
	    dp.addMouseListener(drawAction);
	    dp.addMouseMotionListener(drawAction);

	    Dimension size = dp.getPreferredSize();
	    dp.setBounds(10 + insets.left, 40 + insets.top, size.width
		    + (scrWidth - 25), size.height + (scrHeight - 120));
	    pane.add(dp);

	    size = bDraw.getPreferredSize();
	    bDraw.setBounds(10 + insets.left, 5 + insets.top, size.width,
		    size.height);
	    size = bCircle.getPreferredSize();
	    bCircle.setBounds(40 + insets.left, 5 + insets.top, size.width,
		    size.height);
	    size = bLine.getPreferredSize();
	    bLine.setBounds(70 + insets.left, 5 + insets.top, size.width,
		    size.height);
	    size = bSquare.getPreferredSize();
	    bSquare.setBounds(100 + insets.left, 5 + insets.top, size.width,
		    size.height);
	    size = bPolyline.getPreferredSize();
	    bPolyline.setBounds(130 + insets.left, 5 + insets.top, size.width,
		    size.height);
	    size = bColor.getPreferredSize();
	    bColor.setBounds(160 + insets.left, 5 + insets.top, size.width,
		    size.height);
	    size = bText.getPreferredSize();
	    bText.setBounds(190 + insets.left, 5 + insets.top, size.width,
		    size.height);
	    size = bNewSheet.getPreferredSize();
	    bNewSheet.setBounds(220 + insets.left, 5 + insets.top, size.width,
		    size.height);

	    setResizable(true);
	    setLocation(0, 0);
	    setVisible(true);
	    pack();
	    this.setSize(scrWidth + insets.left + insets.right, scrHeight
		    + insets.left + insets.right);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * Opens a color chooser component to allow the user to choose any color
     * from the pallete.
     * 
     * @return the new color or the current one if user cancels the operation
     */
    public Color chooseColor() {
	Color newColor = JColorChooser.showDialog(this, "Choose New Color", dp
		.getDrawingColor().getDrawingColor());
	if (newColor != null) {
	    return newColor;
	}
	return dp.getDrawingColor().getDrawingColor();
    }

    /**
     * Shows windows with messages according the parameters.
     * 
     * @param warningMessage
     *                The message that should be displayed.
     * @param msgType
     *                The type of the window to be shown.
     */
    public void setWarnings(String warningMessage, int msgType) {
	switch (msgType) {
	case 1:
	    JOptionPane.showMessageDialog(this, warningMessage, "Warning",
		    JOptionPane.WARNING_MESSAGE);
	    break;
	case 2:
	    JOptionPane.showMessageDialog(this, warningMessage, "Information",
		    JOptionPane.INFORMATION_MESSAGE);
	    break;
	}
    }

    /**
     * Displays an input dialog to allow the user to enter some text that is
     * going to be displayed on the screen.
     * 
     * @param dialogText
     *                the text to be displayed in this dialog window
     * @return the string entered by the user
     */
    public String stringCollector(String dialogText) {
	String string = JOptionPane.showInputDialog(this, dialogText);
	return string;
    }

    /**
     * Displays a window about.
     */
    public void showAbout() {
	JOptionPane.showMessageDialog(this,
		"Graphix \n Version 1.0 \n Created by Svilen Velikov",
		"About Graphix", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * @return the dp
     */
    public DrawingPanel getDp() {
	return dp;
    }

    /**
     * @param dp
     *                the dp to set
     */
    public void setDp(DrawingPanel dp) {
	this.dp = dp;
    }

    /**
     * @return the drawAction
     */
    public DrawingActions getDrawAction() {
	return drawAction;
    }
}
