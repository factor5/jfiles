package com.cit.jcourse.svelikov.theme1.stupidaddition;

import java.lang.Integer;

/**
 * @author Svilen Velikov
 *
 */
public class Maths {
	
	/**
	 * Makes addition on two numbers as the people do it
	 * @param A number one
	 * @param B number two
	 */
	public String addition( String A, String B ){
		A = "0" + A;
		B = "0" + B;
		int num1  = A.length();  //gets length of the string/number of digits
		int num2  = B.length();  //gets length of the string/number of digits
		int ost   = 0;           //holds the difference between num1 and num2
		int start = 0;           //start position for substring method
		int end   = 0;           //end position for substring method
		
		/*if the two numbers(A&B)have different number of digits
		 * add a zeroes in the beginning of the shorter:) to make 
		 * them flatten */
		if( num1 != num2 )            //the numbers have difference in length
		{
			if( num1 > num2 )         
			{
				ost   = ( num1 - num2 );  //calculates how many zeroes to add
				start = num1 - 1;     
				end   = num1;
				for( int i = 0; i < ost; i++ ) 
				{
					B = "0" + B;      //add zeroes at the beginning of B
				}
			}
			else
			{
				ost   = ( num2 - num1 );  //calculates how many zeroes to add
				start = num2 - 1;
				end   = num2;
				for( int i = 0; i < ost; i++ ) 
				{
					A = "0" + A;      //add zeroes at the beginning of B
				}
			}
		}
		else                          //the numbers have equal lengths=>no need of zeroes
		{
			start = num1 - 1;
			end   = num1;
		}
		
		int sum;
		int prenos = 0;
	    int n1;
		int n2;
		String chastA;
		String chastB;
		String strSum;
		String sbor = "0";
		String znak;
		
		/*Get substrings from endings of A and B
		 *parseInt them into integers n1 and n2
		 *and summarize n1,n2,prenos */
		while( end > 0 )
		{
			chastA = A.substring( start, end );
			chastB = B.substring( start, end );
			n1     = Integer.parseInt(chastA);
			n2     = Integer.parseInt(chastB);
			sum    = n1 + n2 + prenos;
			
			if( sum > 9 )                      //if sum has two digits
			{
				prenos = 1;                    //make prenos = 1
				strSum = Integer.toString(sum);//change sum into string 
				znak   = strSum.substring(1);  //get the second digit of strSum and put it in znak			
			}
			else                               //sum has one digit
			{
				prenos = 0;                    //keep prenos = 0
				strSum = Integer.toString(sum);//change sum into string
				znak   = strSum;               //strSum has one digit so and znak has one
			}
			sbor = znak + sbor;                //make concatenation to get the result number
			start--;
			end--;
		}
		
		//String zero = "0";
		if( sbor.substring(0,1).compareTo("0") == 0 )
		{
			return sbor.substring( 1, sbor.length()-1 ) ; //return the result
		}
		else
		{
			return sbor.substring( 0, sbor.length()-1 ) ; //return the result
		}		
	}
}