/**
 * 
 */
package ru.pe.graphix.figures;

import java.awt.Color;

import ru.pe.graphix.utill.Const;

/**
 * An abstract representation of the color as an object with its attributes:
 * three 8 bit numbers representing the base colors - red, green, blue.
 * 
 * @author Svilen Velikov
 * 
 * 26.10.2008
 */
public class GColor implements IBaseFigure {

    /**
     * red
     */
    public int red;

    /**
     * green
     */
    public int green;

    /**
     * blue
     */
    public int blue;

    @Override
    public void drawFigure() { }

    /**
     * Setter for the colors that accepts the new color as Color object and
     * splits it up to its three color components.
     * 
     * @param newColorValues
     *                the new color value
     */
    public void setColors(Color newColorValues) {
	red = newColorValues.getRed();
	green = newColorValues.getGreen();
	blue = newColorValues.getBlue();
    }

    /**
     * A getter method used to get the Color value for this object as Color
     * type.
     * 
     * @return the color stored in this object
     */
    public Color getDrawingColor() {
	return new Color(red, green, blue);
    }

    /**
     * Overrides the toString() method from java.lang.Object
     * 
     * @return the string representation of the GColor object.
     */
    public String toString() {
	StringBuffer gcolor = new StringBuffer();
	gcolor.append(Const.SPACE);
	gcolor.append("setcolor");
	gcolor.append(Const.SPACE);
	gcolor.append(red);
	gcolor.append(Const.SPACE);
	gcolor.append(green);
	gcolor.append(Const.SPACE);
	gcolor.append(blue);
	return gcolor.toString();
    }
}
