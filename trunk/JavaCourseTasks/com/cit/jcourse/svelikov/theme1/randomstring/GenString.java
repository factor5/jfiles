package com.cit.jcourse.svelikov.theme1.randomstring;

/**
 * @author Svilen
 */
public class GenString {
	
	/** holds the generated random string */
	//private String rndStr;
	
	/**
	 * Generate random strings with lenght 'br'. 
	 * @param br The lenght of the string.
	 * @return The string generated.
	 */
	public static String randomString( int br ) {
		String rndStr = "";
		char[] arr = {'a','b','c','d','e','f','i','g','h','i','j','k','l','m','n',
				      'o','p','q','r','s','t','u','v','w','x','y','z'};
		/*generate random chars and concatenate them into a string*/
	    for( int i = 0; i < br; i++ ){
	    	rndStr = rndStr + (arr[(int) (Math.random() * 27)]); 	    	  
		}
	    return rndStr;
	}
	
	/**
	 * Prints the generated string.
	 */
	public void prtString() {
		//System.out.println( "Random string : " + rndStr ); //prints the strig 
	}
}