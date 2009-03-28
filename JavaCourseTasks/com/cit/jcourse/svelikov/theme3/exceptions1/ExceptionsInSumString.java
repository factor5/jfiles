package com.cit.jcourse.svelikov.theme3.exceptions1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.cit.jcourse.svelikov.theme1.stupidaddition.Maths;


/**
 * Class that read two numbers as strings from console
 * and pass them to Addition method
 * @author Svilen Velikov
 * @version 02  02.28.07
 */
public class ExceptionsInSumString {
	
	/**
	 * Read two strings from console and 
	 * pass them to Addition method.
	 * @throws IOException 
	 */
	public void inputData() throws IOException {
		String ch      = "y";           
		String num1    = null;
		String num2    = null;
		InputStream in = System.in;
		Maths add = new Maths();
		BufferedReader br = new BufferedReader( new InputStreamReader(in) );
	
		while ( ! "n".equals(ch) ) {
			System.out.println( "First number : " );
			num1 = br.readLine();
				
			System.out.println( "Second number : " );
			num2 = br.readLine();

			System.out.println( "The sum is : " + add.addition( num1, num2 ));
			System.out.println( "Repeat (y/n)? " );
			ch = br.readLine();
		}
	}
}