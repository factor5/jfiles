/**
 * 
 */
package ru.pe.graphix.actions;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import ru.pe.graphix.DrawingPanel;
import ru.pe.graphix.UserInterface;
import ru.pe.graphix.figures.GCircle;
import ru.pe.graphix.figures.GLine;
import ru.pe.graphix.figures.GPoint;
import ru.pe.graphix.figures.GRectangle;
import ru.pe.graphix.figures.GText;
import ru.pe.graphix.utill.Const;

/**
 * A listener class that is in charge of the events risen when the mouse is used
 * for drawing in the drawing panel.
 * 
 * @author Svilen Velikov
 * 
 * 26.10.2008
 */
public class DrawingActions extends MouseAdapter implements MouseMotionListener {

    /**
     * Reference to the user interface.
     */
    private UserInterface ui;

    /**
     * Reference to the drawing panel
     */
    private DrawingPanel dp;

    /**
     * Flag to show if the left mouse button was pressed or not.
     */
    private boolean pressed = false;

    /**
     * Flag to show if the mouse button has been pressed once before.
     */
    private boolean firstPointIsSet;

    /**
     * A GLine object.
     */
    private GLine line;

    /**
     * A GRectangle object.
     */
    private GRectangle rectangle;

    /**
     * A GCircle object.
     */
    private GCircle circle;

    /**
     * A GText object.
     */
    private GText text;

    /**
     * A pointer to show which action is in turn.
     */
    private int action = 0;

    /**
     * Provides response when the left mouse button was pressed.
     * 
     * @param me
     *                the event that this method is listen to
     */
    @Override
    public void mousePressed(MouseEvent me) {
	pressed = true;
	if (action == 1) {
	    // create a dot
	    dp.getFigList().add(new GPoint(me.getPoint().x, me.getPoint().y));
	    dp.repaint();
	} else if (action == 2) {
	    // create a line
	    if (!firstPointIsSet) {
		line = new GLine();
		line.co1.x = me.getX();
		line.co1.y = me.getY();
		firstPointIsSet = true;
	    } else {
		line.co2.x = me.getX();
		line.co2.y = me.getY();
		firstPointIsSet = false;
		dp.getFigList().add(line);
		line = null;
		dp.repaint();
	    }
	} else if (action == 3) {
	    // create a circle
	    if (!firstPointIsSet) {
		circle = new GCircle();
		circle.co1.x = me.getX();
		circle.co1.y = me.getY();
		firstPointIsSet = true;
	    } else {
		circle.co2.x = me.getX();
		circle.co2.y = me.getY();
		// calculating the radius
		int radius = (int) ((Math.round(Math.sqrt(Math.pow(
			(circle.co1.x - circle.co2.x), 2)
			+ Math.pow((circle.co1.y - circle.co2.y), 2)))) * 2);
		circle.radius = radius;
		// translating the center
		circle.co1.x = (circle.co1.x <= (circle.co1.x + (radius / 2))) ? (circle.co1.x - (radius / 2))
			: 0;
		circle.co1.y = (circle.co1.y <= (circle.co1.y + (radius / 2))) ? (circle.co1.y - (radius / 2))
			: 0;
		firstPointIsSet = false;
		dp.getFigList().add(circle);
		circle = null;
		dp.repaint();
	    }
	} else if (action == 4) {
	    // draw a text
	    text = new GText();
	    text.co1.x = me.getX();
	    text.co1.y = me.getY();
	    text.text = ui.stringCollector(Const.DIALOG_ENTER_TEXT);
	    if (text.text != null) {
		dp.getFigList().add(text);
		dp.repaint();
	    }
	    text = null;
	} else if (action == 5) {
	    // draw rectangle
	    if (!firstPointIsSet) {
		rectangle = new GRectangle();
		rectangle.co1.x = me.getX();
		rectangle.co1.y = me.getY();
		firstPointIsSet = true;
	    } else {
		rectangle.co2.x = me.getX();
		rectangle.co2.y = me.getY();
		// calculating the width
		int width = (Math.max(rectangle.co1.x, rectangle.co2.x))
			- (Math.min(rectangle.co1.x, rectangle.co2.x));
		// calculating the height
		int height = (Math.max(rectangle.co1.y, rectangle.co2.y))
			- (Math.min(rectangle.co1.y, rectangle.co2.y));
		// set up the height and width
		rectangle.width = width;
		rectangle.height = height;
		// check direction and switch the start point if needs to
		if (rectangle.co2.x < rectangle.co1.x
			&& rectangle.co2.y < rectangle.co1.y) {
		    Point temp = new Point(rectangle.co1.x, rectangle.co1.y);
		    rectangle.co1 = rectangle.co2;
		    rectangle.co2 = temp;
		} else if (rectangle.co2.x < rectangle.co1.x
			&& rectangle.co2.y > rectangle.co1.y) {
		    rectangle.co1.x = rectangle.co1.x
			    - (rectangle.co1.x - rectangle.co2.x);
		} else if (rectangle.co2.x > rectangle.co1.x
			&& rectangle.co2.y < rectangle.co1.y) {
		    rectangle.co1.y = rectangle.co1.y
			    - (rectangle.co1.y - rectangle.co2.y);
		}
		firstPointIsSet = false;
		dp.getFigList().add(rectangle);
		rectangle = null;
		dp.repaint();
	    }
	} else if (action == 6) {
	    // draw polyline
	}
    }

    /**
     * Provides response when the mouse button was released.
     * 
     * @param me
     *                the event that this method is listen to
     */
    @Override
    public void mouseReleased(MouseEvent me) {
	pressed = false;
    }

    /**
     * 
     * @param me
     *                the event that this method is listen to
     */
    @Override
    public void mouseDragged(MouseEvent me) {
	if (pressed && action == 1) {
	    dp.getFigList().add(new GPoint(me.getPoint().x, me.getPoint().y));
	    dp.repaint();
	}
    }

    /**
     * Sets up the class fields.
     * 
     * @param ui
     *                reference to the user interface
     */
    public void setUInterface(UserInterface ui) {
	this.ui = ui;
	this.dp = ui.getDp();
    }

    /**
     * @return the firstPointIsSet
     */
    public boolean isFirstPointIsSet() {
	return firstPointIsSet;
    }

    /**
     * @param firstPointIsSet
     *                the firstPointIsSet to set
     */
    public void setFirstPointIsSet(boolean firstPointIsSet) {
	this.firstPointIsSet = firstPointIsSet;
    }

    /**
     * @return the action
     */
    public int getAction() {
	return action;
    }

    /**
     * @param action
     *                the action to set
     */
    public void setAction(int action) {
	this.action = action;
    }
}
