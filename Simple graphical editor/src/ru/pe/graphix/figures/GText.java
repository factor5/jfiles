/**
 * 
 */
package ru.pe.graphix.figures;

import java.awt.Point;

import ru.pe.graphix.utill.Const;

/**
 * An abstract representation of a text as an object with its attributes: a
 * point inherited from the GPoint class as initial position of the text to be
 * placed and a string that is the actual text to be written.
 * 
 * @author Svilen Velikov
 * 
 * 26.10.2008
 */
public class GText extends GPoint implements IBaseFigure {

    /**
     * The text to be written
     */
    public String text = "";

    /**
     * Initializing constructor.
     */
    public GText() {
	co1 = new Point();
    }

    /**
     * Overrides the toString() method from java.lang.Object
     * 
     * @return the string representation of the GText object.
     */
    public String toString() {
	StringBuffer gtext = new StringBuffer();
	gtext.append(Const.SPACE);
	gtext.append("write");
	gtext.append(Const.SPACE);
	gtext.append(co1.x);
	gtext.append(Const.SPACE);
	gtext.append(co1.y);
	gtext.append(Const.SPACE);
	gtext.append(text);
	return gtext.toString();
    }
}
