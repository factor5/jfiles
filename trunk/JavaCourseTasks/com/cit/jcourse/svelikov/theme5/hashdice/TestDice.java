/**
 * 
 */
package com.cit.jcourse.svelikov.theme5.hashdice;

import java.util.List;
import java.util.Vector;

import com.cit.jcourse.svelikov.theme4.readers.ConsoleReader;

/**
 * Invokes Dice to fill the statistics and then check combinations
 * whether they were thrown.
 * 
 * @author  Svilen Velikov
 * @version 01 28.03.07
 */
public class TestDice {

	/**
	 * Prints statistics for choosen combination if there is entries
	 * for that combination in the Hashtable. Combinations are entered
	 * by the user.
	 * @param args
	 */
	public static void main(String[] args) {
		
		int firstDice = 0;						/*the first number for the combination*/
		int secondDice = 0;						/*the second number for the combination*/
		String ch = null;									
		
		List checkPos = new Vector();	
		Dice d = new Dice();
		d.throwDice();
		
		/*loops until user choose to exit*/
		do {
			System.out.println("Enter combination to check. Only numbers between 1-6 are allowed!");
			/*loops until not entered valid numbers for combination*/
			do {
				System.out.println("First  dice : ");
				firstDice = ConsoleReader.readInt();				
				System.out.println("Second dice : ");
				secondDice = ConsoleReader.readInt();	
			} while( firstDice < 1 || firstDice  > 6 ||
					secondDice < 1 || secondDice > 6 );
			
			/*forms the String representations of thrown combination*/
			String combToCheck    = String.valueOf(firstDice) + String.valueOf(secondDice);
			String revCombToCheck = String.valueOf(secondDice) + String.valueOf(firstDice);
			
			/*if there is such combination in the Hashtable prints statistics*/
			if((( checkPos = (List) d.getCombination(combToCheck)) !=null) ||
					( checkPos = (List) d.getCombination(revCombToCheck)) !=null) {
				System.out.println("combination ("+firstDice+","+secondDice+
								    ") thrown at positions "+checkPos);
				System.out.println("number of rolls : "+checkPos.size());
			/*table doesn't contain such combination as a key*/
			} else {
				System.out.println("There is no such combination in statistics!");
			}
			System.out.println("For another combination press any key! " +
					"[n] to exit!");
		
			ch = ConsoleReader.readString();
			
		} while( ! "n".equals(ch) );
	}
}
