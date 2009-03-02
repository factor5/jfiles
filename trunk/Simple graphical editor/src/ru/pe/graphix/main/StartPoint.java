/**
 * 
 */
package ru.pe.graphix.main;

import ru.pe.graphix.UserInterface;

/**
 * 
 * 
 * @author Svilen Velikov
 * 
 * 27.10.2008
 */
public class StartPoint {

    /**
     * @param args
     */
    public static void main(String[] args) {
	// String configPath = "src/config.properties";
	// Const c = new Const(configPath);

	javax.swing.SwingUtilities.invokeLater(new Runnable() {
	    public void run() {
		UserInterface ui = new UserInterface();
	    }
	});
    }
}
