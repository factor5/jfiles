/**
 * 
 */
package ru.pe.graphix.figures;

/**
 * This is the the base interface for all the figures used in the application.
 * It's implemented by all of them so to be possible to use polymorphism.
 * 
 * @author Svilen Velikov
 * 
 * 26.10.2008
 */
public interface IBaseFigure {

    /**
     * Method to draw the figure.
     */
    void drawFigure();

    /**
     * Overridden java.lang.Object.toString method
     * 
     * @return
     */
    String toString();
}
