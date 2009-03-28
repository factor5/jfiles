package com.cit.jcourse.svelikov.theme3.consolereading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Class for reading of numbers in range of 0-100
 * @author Svilen
 * @version 02 28.02.07
 */
public class ConsoleReading {
	
	/**
	 * Read numbers from console. When an entry
	 * number is out of range a new OutOfRangeException 
	 * is thrown.
	 * @throws IOException 
	 * @throws OutOfRangeException 
	 */
	public void readNumbers() throws IOException, OutOfRangeException {
		InputStream in    = System.in;
		BufferedReader br = new BufferedReader( new InputStreamReader(in) );
		
		String ch = "y";
		int     num;
		String  strnum;
		
		while ( ! "n".equals(ch) ) {      /*read while ch!="n"*/
			System.out.println( "Number between [1,100] : " );
			strnum = br.readLine();             
			num    = Integer.parseInt(strnum);  /*convert from String to Integer*/
				
			if ( (num < 0) || (num > 100) )     /*check if num is out of range*/
				throw new OutOfRangeException("Inserted number is out of range!");
			System.out.println( "Repeat (y/n)? " );
			ch = br.readLine();
		}
	}	
} 

