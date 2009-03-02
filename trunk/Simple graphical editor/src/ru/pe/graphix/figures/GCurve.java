/**
 * 
 */
package ru.pe.graphix.figures;

import ru.pe.graphix.utill.Const;

/**
 * @author iuyiyiuyi
 * 
 */
public class GCurve extends GPoint implements IBaseFigure {
    /**
     * Overrides the toString() method from java.lang.Object
     * 
     * @return the string representation of the GCurve object.
     */
    public String toString() {
	// FIXME
	StringBuffer gline = new StringBuffer();
	gline.append("line");
	gline.append(Const.SPACE);
	gline.append(co1.x);
	gline.append(Const.SPACE);
	gline.append(co1.y);
	return gline.toString();
    }
}
