/**
 * 
 */
package ru.pe.graphix.figures;

import java.awt.Point;

import ru.pe.graphix.utill.Const;

/**
 * An abstract representation of a line with its attributes: two points as the
 * first of them is inherited from the GPoint class.
 * 
 * @author Svilen Velikov
 * 
 * 26.10.2008
 */
public class GLine extends GPoint implements IBaseFigure {

    /**
     * The second point.
     */
    public Point co2;

    /**
     * Initializing constructor.
     */
    public GLine() {
	co2 = new Point();
	co1 = new Point();
    }

    /**
     * Overrides the toString() method from java.lang.Object
     * 
     * @return the string representation of the GLine object.
     */
    public String toString() {
	StringBuffer gline = new StringBuffer();
	gline.append(Const.SPACE);
	gline.append("line");
	gline.append(Const.SPACE);
	gline.append(co1.x);
	gline.append(Const.SPACE);
	gline.append(co1.y);
	gline.append(Const.SPACE);
	gline.append(co2.x);
	gline.append(Const.SPACE);
	gline.append(co2.y);
	return gline.toString();
    }
}
