package com.cit.jcourse.svelikov.theme1.randomstring;

/**
 * @author Svilen Velikov
 * 
 */
public class Test 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String str;
		for( int i = 2; i < 20; i+=2 ) {
    		str = GenString.randomString( i );  //param 'i'define the lenght of the string	
    		System.out.println(str);
        }
	}
}