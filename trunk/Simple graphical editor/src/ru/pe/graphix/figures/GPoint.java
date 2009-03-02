package ru.pe.graphix.figures;

import java.awt.Point;

import ru.pe.graphix.utill.Const;

/**
 * An abstract representation of a point with its attribute: a point
 * representing the x/y coordinates.
 * 
 * @author Svilen Velikov
 * 
 * 26.10.2008
 */
public class GPoint implements IBaseFigure {

    /**
     * A point holding coordinates for this object to be placed.
     */
    public Point co1;

    /**
     * Default constructor.
     */
    public GPoint() {
	co1 = new Point();
    }

    /**
     * Initializing constructor.
     * 
     * @param x
     *                coordinate
     * @param y
     *                coordinate
     */
    public GPoint(int x, int y) {
	co1 = new Point(x, y);
    }

    @Override
    public void drawFigure() {}

    /**
     * Overrides the toString() method from java.lang.Object
     * 
     * @return the string representation of the GPoint object.
     */
    public String toString() {
	StringBuffer gpoint = new StringBuffer();
	gpoint.append(Const.SPACE);
	gpoint.append("point");
	gpoint.append(Const.SPACE);
	gpoint.append(co1.x);
	gpoint.append(Const.SPACE);
	gpoint.append(co1.y);
	return gpoint.toString();
    }
}
