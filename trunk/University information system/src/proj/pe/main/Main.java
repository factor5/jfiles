/**
 * 
 */
package proj.pe.main;

import proj.pe.ui.MainWindow;

/**
 * This is the entry point for this application. Here is invoked the constructor
 * of the main window and are passed the paths to the three working files.
 */
public class Main {

    public static String fileStud = "stud.txt";
    public static String fileTeach = "teach.txt";
    public static String fileStaff = "staff.txt";

    /**
     * @param args
     */
    public static void main(String[] args) {
	javax.swing.SwingUtilities.invokeLater(new Runnable() {
	    public void run() {
		MainWindow main = new MainWindow(fileStud, fileTeach, fileStaff);
	    }
	});

    }

}
