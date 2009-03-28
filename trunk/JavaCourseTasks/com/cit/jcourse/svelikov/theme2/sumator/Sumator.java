package com.cit.jcourse.svelikov.theme2.sumator;
import java.math.BigDecimal;
import java.math.BigInteger;
import com.cit.jcourse.svelikov.theme1.stupidaddition.Maths;

/**
 * class Sumator that holds methods for summarize
 * on two numbers given in different types.
 * All the methods have one and the 
 * same name sum().
 * 
 * @author SVelikov
 *
 * @version 0.2  19.02.2007
 */
public class Sumator {
	
	/**
	 * Calculates the sum of two integers
	 * @param a
	 * @param b
	 * @return a+b
	 */
	public int sum( int a, int b ){
		return ( a + b );
	}
	
	/**
	 * Calculates the sum of two floats
	 * @param a
	 * @param b
	 * @return a+b
	 */
	public float sum( float a, float b ){
		return ( a + b );
	}
	
	/**
	 * Calculates the sum of two BigDecimal
	 * @param a
	 * @param b
	 * @return a+b
	 */
	public BigDecimal sum( BigDecimal a, BigDecimal b ){
		
		return a.add(b);		
	}
	
	/**
	 * Calculates the sum of two BigIntegers
	 * @param a
	 * @param b
	 * @return a+b
	 */
	public BigInteger sum( BigInteger a, BigInteger b ){
		
		return a.add(b);		
	}
	
	
	/**
	 * Calculates the sum of two numbers given as a string
	 * @param A number one
	 * @param B number two
	 * @return 
	 * @return A+B
	 */
	public String sum( String A, String B )
	{
		Maths add = new Maths();
		return add.addition( A, B );

	}
}
