package com.cit.jcourse.svelikov.theme1.nod;

public class Method {
	
	/**
	 * Calculates GCD of two numbers without division
	 * @param value1  the first number
	 * @param value2  the second number
	 * @return 
	 */
	public int NodA( int value1, int value2 )
	  {
		while ( value1 != value2 )
		{
			if ( value1 > value2 )
			{
				value1 = value1 - value2;
			}
			else
			{
				value2 = value2 - value1;
			}
		}
        return value1;
	  }
	
	/**
	 * Calculates GCD of two numbers using Euclidean algorithm
	 * @param value1  the first number
	 * @param value2  the second number
	 * @return 
	 * 
	 */
	public float NodE( float valueA, float valueB )
	  { 
		float a;
		while ( valueB != 0 )
		 {
			a = valueB;
			valueB = valueA % valueB;
			valueA = a;
		 }
		return valueA;
	  }
	
	/**
	 * Calculates LCM of two numbers
	 * @param value1  the first number
	 * @param value2  the second number
	 * @return nok
	 */
	public float Nok( int value1, int value2 )
	  {
		 float nok = ( value1 * value2 ) / NodE(value1,value2);

		 return nok;
	  }
}
