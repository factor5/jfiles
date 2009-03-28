/**
 * 
 */
package com.cit.jcourse.svelikov.theme4.dirbrowser;

import java.io.File;

/**
 * Prints the content of a directory.
 * 
 * @author Svilen
 * @version 01 03.11.07
 */
public class DirectoryBrowser {

	/**
	 * Prints the content of a directory. If the
	 * parameter path points to a file should
	 * prints a mesage.
	 * @param path
	 */
	public void listContent(String path) {
		File p = makePath(path);                                  	/*constructs a pathname*/
		String[] dirList = p.list(); 						        /*creates an array with dir content*/
		
		if ( p.isFile() ) {                                         /*if p points to a file*/
			System.out.println( p.getName() + " --> Is a File!!!"); /*yes => get name*/		
		} else {                                                    /*p is a directory*/
			System.out.println("Directory -->");
			for (int i = 0; i < dirList.length; i++) {              /*prints the entire directory*/
				System.out.println(dirList[i]);
			}			
		}
	}
	
	/**
	 * Converts parameter path to an object of
	 * File type. Then makes a system 
	 * dependent pathname by inserting a default
	 * name-separator.
	 * @param path
	 * @return constructed pathname
	 */
	public File makePath(String path) {
		File p = new File(path);
		path = p.getPath();
		return p;
	}
}
