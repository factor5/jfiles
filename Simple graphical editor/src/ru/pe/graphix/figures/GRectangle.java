/**
 * 
 */
package ru.pe.graphix.figures;

import java.awt.Point;

import ru.pe.graphix.utill.Const;

/**
 * An abstract representation of a rectangle with its attributes: two points
 * inherited from the GLine class the represents rectangle's opposite corners
 * and also width and height given as integer numbers.
 * 
 * @author Svilen Velikov
 * 
 * 26.10.2008
 */
public class GRectangle extends GLine implements IBaseFigure {

    /**
     * The width of this rectangle.
     */
    public int width;

    /**
     * The height of this rectangle.
     */
    public int height;

    /**
     * Initializing constructor.
     */
    public GRectangle() {
	co1 = new Point();
	co2 = new Point();
    }

    /**
     * Overrides the toString() method from java.lang.Object
     * 
     * @return the string representation of the GRectangle object.
     */
    public String toString() {
	StringBuffer gRectangle = new StringBuffer();
	gRectangle.append(Const.SPACE);
	gRectangle.append("rectangle");
	gRectangle.append(Const.SPACE);
	gRectangle.append(co1.x);
	gRectangle.append(Const.SPACE);
	gRectangle.append(co1.y);
	gRectangle.append(Const.SPACE);
	gRectangle.append(width);
	gRectangle.append(Const.SPACE);
	gRectangle.append(height);
	return gRectangle.toString();
    }

}
