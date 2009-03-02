/**
 * 
 */
package ru.pe.graphix.figures;

import java.awt.Point;

import ru.pe.graphix.utill.Const;

/**
 * An abstract representation of a circle with its attributes: two points that
 * are inherited from the GLine class and a radius.
 * 
 * @author Svilen Velikov
 * 
 * 26.10.2008
 */
public class GCircle extends GLine implements IBaseFigure {

    /**
     * The radius for this circle.
     */
    public int radius;

    /**
     * Initializing constructor.
     */
    public GCircle() {
	co2 = new Point();
	co1 = new Point();
    }

    /**
     * Overrides the toString() method from java.lang.Object
     * 
     * @return the string representation of the GCircle object.
     */
    public String toString() {
	StringBuffer gcircle = new StringBuffer();
	gcircle.append(Const.SPACE);
	gcircle.append("circle");
	gcircle.append(Const.SPACE);
	gcircle.append(co1.x);
	gcircle.append(Const.SPACE);
	gcircle.append(co1.y);
	gcircle.append(Const.SPACE);
	gcircle.append(radius);
	return gcircle.toString();
    }
}
