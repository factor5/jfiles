/**
 * 
 */
package ru.pe.graphix.utill;

import java.util.ArrayList;
import java.util.List;

import ru.pe.graphix.figures.GCircle;
import ru.pe.graphix.figures.GColor;
import ru.pe.graphix.figures.GLine;
import ru.pe.graphix.figures.GPoint;
import ru.pe.graphix.figures.GRectangle;
import ru.pe.graphix.figures.GText;
import ru.pe.graphix.figures.IBaseFigure;

/**
 * 
 * 
 * @author Svilen Velikov
 * 
 * 26.10.2008
 */
public class StringOperation {

    private List<IBaseFigure> figList;

    private StringBuffer sourceString;

    public List<IBaseFigure> makeList(StringBuffer source) {
	this.sourceString = source;
	String temp = sourceString.toString();
	temp = temp.replaceAll("\r\n", " ");
	String[] tokens = temp.split(Const.SPACE);
	if (makeObjects(tokens)) {
	    return figList;
	}
	return null;
    }

    public boolean makeObjects(String[] tokens) {
	figList = new ArrayList<IBaseFigure>();
	try {
	    for (int i = 0; i < tokens.length; i++) {
		if (tokens[i].equals("point")) {
		    GPoint point = new GPoint();
		    point.co1.x = Integer.parseInt(tokens[i + 1]);
		    point.co1.y = Integer.parseInt(tokens[i + 2]);
		    figList.add(point);
		    continue;
		} else if (tokens[i].equals("line")) {
		    GLine line = new GLine();
		    line.co1.x = Integer.parseInt(tokens[i + 1]);
		    line.co1.y = Integer.parseInt(tokens[i + 2]);
		    line.co2.x = Integer.parseInt(tokens[i + 3]);
		    line.co2.y = Integer.parseInt(tokens[i + 4]);
		    figList.add(line);
		    continue;
		} else if (tokens[i].equals("circle")) {
		    GCircle circle = new GCircle();
		    circle.co1.x = Integer.parseInt(tokens[i + 1]);
		    circle.co1.y = Integer.parseInt(tokens[i + 2]);
		    circle.radius = Integer.parseInt(tokens[i + 3]);
		    figList.add(circle);
		    continue;
		} else if (tokens[i].equals("rectangle")) {
		    GRectangle rectangle = new GRectangle();
		    rectangle.co1.x = Integer.parseInt(tokens[i + 1]);
		    rectangle.co1.y = Integer.parseInt(tokens[i + 2]);
		    rectangle.width = Integer.parseInt(tokens[i + 3]);
		    rectangle.height = Integer.parseInt(tokens[i + 4]);
		    figList.add(rectangle);
		    continue;
		} else if (tokens[i].equals("write")) {
		    GText text = new GText();
		    text.co1.x = Integer.parseInt(tokens[i + 1]);
		    text.co1.y = Integer.parseInt(tokens[i + 2]);
		    text.text = tokens[i + 3];
		    figList.add(text);
		    continue;
		} else if (tokens[i].equals("polyline")) {

		} else if (tokens[i].equals("setcolor")) {
		    GColor color = new GColor();
		    color.red = Integer.parseInt(tokens[i + 1]);
		    color.green = Integer.parseInt(tokens[i + 2]);
		    color.blue = Integer.parseInt(tokens[i + 3]);
		    figList.add(color);
		    continue;
		}
	    }
	} catch (NumberFormatException e) {
	    e.printStackTrace();
	    return false;
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
	return true;
    }

    /**
     * @return the sourceString
     */
    public StringBuffer getSourceString() {
	return sourceString;
    }

    /**
     * @param sourceString
     *                the sourceString to set
     */
    public void setSourceString(StringBuffer sourceString) {
	this.sourceString = sourceString;
    }
}
