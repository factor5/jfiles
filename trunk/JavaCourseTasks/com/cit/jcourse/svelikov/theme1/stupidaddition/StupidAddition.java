package com.cit.jcourse.svelikov.theme1.stupidaddition;

/**
 * @author Svilen Velikov
 *
 */
public class StupidAddition
{
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String numberA = "12";       //get number 1 as a string
		String numberB = "28";       //get number 2 as a string
		Maths add = new Maths();
        System.out.println("Сборът е = " + add.addition( numberA, numberB ) );
	}
}