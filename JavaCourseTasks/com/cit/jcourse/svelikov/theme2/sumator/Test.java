/**
 * 
 */
package com.cit.jcourse.svelikov.theme2.sumator;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * class Test verification of class Sumator
 * 
 * @author Svilen
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int numI1 = 25363;
		int numI2 = 2342;
		float numF1 = (float) 6.467;
		float numF2 = (float) 2.34;
        String numS1 = "754324";
        String numS2 = "345092384";
        BigDecimal numBigDec1 = new BigDecimal(958634.3242);
        BigDecimal numBigDec2 = new BigDecimal(9.32);
        BigInteger numBigInt1 = new BigInteger("34278578786996734232324636");
        BigInteger numBigInt2 = new BigInteger("239842853965676567");
        
        Sumator getSum = new Sumator();
        
        System.out.println( "int : "+numI1+" + "+numI2+" = "+getSum.sum( numI1, numI2 ));
        System.out.println( "real : "+numF1+" + "+numF2+" = "+getSum.sum( numF1, numF2 ));
        System.out.println( "string : "+numS1+" + "+numS2+" = "+getSum.sum( numS1, numS2 ));   
		System.out.println( "BigDecimal : "+numBigDec1+" + "+numBigDec2+" = "+getSum.sum( numBigDec1, numBigDec2 ));
        System.out.println( "BigInteger : "+numBigInt1+" + "+numBigInt2+" = "+getSum.sum( numBigInt1, numBigInt2 ));
	}
}


