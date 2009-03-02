package ru.pe.graphix.figures;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import ru.pe.graphix.utill.Const;

/**
 * An abstract representation of a polyline with its attributes: a list of
 * vertices, the vertice's coordinates stored in two arrays of integers and its
 * total number.
 * 
 * @author Svilen Velikov
 * 
 * 26.10.2008
 */
public class GPolyline extends GPoint implements IBaseFigure {

    /**
     * A list of vertices for this polyline.
     */
    public List<Point> vertices;

    /**
     * The x coordinates for the vertices
     */
    public int[] verticesX;

    /**
     * The y coordinates for the vertices.
     */
    public int[] verticesY;

    /**
     * The total number of the vertices.
     */
    public int verticesNumber;

    /**
     * Initializing constructor.
     */
    public GPolyline() {
	co1 = new Point();
	vertices = new ArrayList<Point>();
	verticesX = new int[] {};
	verticesY = new int[] {};
    }

    /**
     * Overrides the toString() method from java.lang.Object
     * 
     * @return the string representation of the GPolyline object.
     */
    public String toString() {
	StringBuffer gPolyline = new StringBuffer();
	gPolyline.append(Const.SPACE);
	gPolyline.append("polyline");
	for (Point point : vertices) {
	    gPolyline.append(Const.SPACE);
	    gPolyline.append(point.x);
	    gPolyline.append(Const.SPACE);
	    gPolyline.append(point.y);
	}
	gPolyline.append(Const.SPACE);
	gPolyline.append(verticesNumber);
	return gPolyline.toString();
    }
}
