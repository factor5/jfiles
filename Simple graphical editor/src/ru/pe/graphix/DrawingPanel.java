/**
 * 
 */
package ru.pe.graphix;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;

import ru.pe.graphix.figures.GCircle;
import ru.pe.graphix.figures.GColor;
import ru.pe.graphix.figures.GLine;
import ru.pe.graphix.figures.GPoint;
import ru.pe.graphix.figures.GPolyline;
import ru.pe.graphix.figures.GRectangle;
import ru.pe.graphix.figures.GText;
import ru.pe.graphix.figures.IBaseFigure;

/**
 * A wrapper class for the Canvas that provides some additional functions and
 * fields that are needed for the application.
 * 
 * @author Svilen Velikov
 * 
 * 26.10.2008
 */
public class DrawingPanel extends Canvas {

    private static final long serialVersionUID = -458422531020753825L;

    /**
     * The default background color for the drawing area.
     */
    private static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;

    /**
     * The default drawing color.
     */
    private static final Color DEFAULT_DRAWING_COLOR = Color.BLACK;

    /**
     * The list that holds the figures drawn in the drawing area.
     */
    private List<IBaseFigure> figList;

    /**
     * A GPoint object.
     */
    private GPoint point;

    /**
     * A GLine object.
     */
    private GLine line;

    /**
     * A GCircle object.
     */
    private GCircle circle;

    /**
     * A GText object.
     */
    private GText text;

    /**
     * A GColor object to represent the current drawing color.
     */
    private GColor drawingColor;

    /**
     * A Stroke object.
     */
    private Stroke stroke;

    /**
     * Initializing constructor.
     */
    public DrawingPanel() {
	figList = new ArrayList<IBaseFigure>();
	drawingColor = new GColor();
	drawingColor.setColors(DEFAULT_DRAWING_COLOR);
	figList.add(drawingColor);
    }

    /**
     * Overrides the parents paint method to provide the needed drawing
     * functionality.
     */
    @Override
    public void paint(Graphics graphics) {
	Graphics2D g = (Graphics2D) graphics;

	g.setStroke(new BasicStroke(1));

	if (figList != null) {
	    for (IBaseFigure fig : figList) {
		if (fig.getClass().getSimpleName().equals("GPoint")) {
		    GPoint gp = (GPoint) fig;
		    g.drawLine(gp.co1.x, gp.co1.y, gp.co1.x, gp.co1.y);
		} else if (fig.getClass().getSimpleName().equals("GLine")) {
		    GLine gl = (GLine) fig;
		    g.drawLine(gl.co1.x, gl.co1.y, gl.co2.x, gl.co2.y);
		} else if (fig.getClass().getSimpleName().equals("GCircle")) {
		    GCircle go = (GCircle) fig;
		    g.drawOval(go.co1.x, go.co1.y, go.radius, go.radius);
		} else if (fig.getClass().getSimpleName().equals("GText")) {
		    GText go = (GText) fig;
		    g.drawString(go.text, go.co1.x, go.co1.y);
		} else if (fig.getClass().getSimpleName().equals("GRectangle")) {
		    GRectangle gr = (GRectangle) fig;
		    g.drawRect(gr.co1.x, gr.co1.y, gr.width, gr.height);
		} else if (fig.getClass().getSimpleName().equals("GPolyline")) {
		    GPolyline gpo = (GPolyline) fig;
		    g.drawPolyline(gpo.verticesX, gpo.verticesY,
			    gpo.verticesNumber);
		} else if (fig.getClass().getSimpleName().equals("GColor")) {
		    GColor gcol = (GColor) fig;
		    g.setColor(gcol.getDrawingColor());
		}
	    }
	}
    }

    /**
     * Clears the drawing panel by removing all the objects from the figList and
     * repainting the panel.
     */
    public void clearSheet() {
	figList.clear();
	this.getGraphics().setColor(DEFAULT_BACKGROUND_COLOR);
	this.getGraphics().fillRect(0, 0, this.WIDTH, this.HEIGHT);
	this.getGraphics().clearRect(0, 0, this.WIDTH, this.HEIGHT);
	repaint();
    }

    /**
     * @return the figList
     */
    public List<IBaseFigure> getFigList() {
	return figList;
    }

    /**
     * @param figList
     *                the figList to set
     */
    public void setFigList(List<IBaseFigure> figList) {
	this.figList = figList;
    }

    /**
     * @return the line
     */
    public GLine getLine() {
	return line;
    }

    /**
     * @return the stroke
     */
    public Stroke getStroke() {
	return stroke;
    }

    /**
     * @param stroke
     *                the stroke to set
     */
    public void setStroke(Stroke stroke) {
	this.stroke = stroke;
    }

    /**
     * @return the point
     */
    public GPoint getPoint() {
	return point;
    }

    /**
     * @param point
     *                the point to set
     */
    public void setPoint(GPoint point) {
	this.point = point;
    }

    /**
     * @return the circle
     */
    public GCircle getCircle() {
	return circle;
    }

    /**
     * @param circle
     *                the circle to set
     */
    public void setCircle(GCircle circle) {
	this.circle = circle;
    }

    /**
     * @return the text
     */
    public GText getText() {
	return text;
    }

    /**
     * @param text
     *                the text to set
     */
    public void setText(GText text) {
	this.text = text;
    }

    /**
     * @param line
     *                the line to set
     */
    public void setLine(GLine line) {
	this.line = line;
    }

    /**
     * @return the drawingColor
     */
    public GColor getDrawingColor() {
	return drawingColor;
    }

    /**
     * @param drawingColor
     *                the drawingColor to set
     */
    public void setDrawingColor(GColor drawingColor) {
	this.drawingColor = drawingColor;
    }
}
