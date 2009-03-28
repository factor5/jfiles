/**
 * 
 */
package com.cit.jcourse.svelikov.theme4.dirbrowser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Provide data for test DirectoryBrowser
 * class.
 * @author Svilen
 * @version 01 03.11.07
 */
public class Test {

	/**
	 * Prompts user to enter a pathname in console and
	 * if it is a real directory or file pathname
	 * invokes listContent method with string pathname
	 * as an argument.
	 * @param args
	 */
	public static void main(String[] args) {
		String path = "";
		DirectoryBrowser db = new DirectoryBrowser();	
		InputStream in = System.in;
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		try {
			boolean pathReady = false;
			/*loops until a real pathname is inserted*/
			while( !pathReady ) {                    
				System.out.println( "Enter a pathname : ");				
				path = br.readLine();
				/*check whether inserted string is a real pathname*/
				if(( !(db.makePath(path)).isDirectory()) && ( !(db.makePath(path)).isFile())) {   
					System.out.println( path + " --> This Isn't a directory!");
				} else {
					pathReady = true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
		db.listContent(path);    /*invokes listContent method to procces the pathname*/
	}
}
