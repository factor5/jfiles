/**
 * 
 */
package com.cit.jcourse.svelikov.theme5.hashdice;

import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

/**
 * Contains a throwDice method that simulates thrown of two dices
 * and also fills up a Hashtable with combinations as keys and Vectors
 * that holds positions as values.
 * 
 * @author  Svilen Velikov
 * @version 01 28.03.07
 */
public class Dice {
	
	/** Number of tryes */
	private static final int NUM_POS = 1000000;
	
	/** will store positions number */
	private List position = new Vector();
	
	/** will store combinations that are thrown */
	private Hashtable combinations = new Hashtable(100);
	
	/**
	 * Gets a key from combinations table if exsist.
	 * @param combToCheck The key that should be checked and returned.
	 * @return The key if exsist or null if it doesn't.	
	 */
	public Object getCombination(String combToCheck) {
		return combinations.get(combToCheck);
	}
	
	/**
	 * Throws a pair of dices explicit number times. After every thrown
	 * fills up the statistics with the combination and at wich number 
	 * try it was thrown.
	 */
	public void throwDice() {
		
		int firstThrown;							/*result from 1st thrown*/
		int secondThrown;							/*result from 2nd thrown*/
		String  combThrown;							/*combination that is thrown*/
		String  revCombThrown;						/*reversed combination*/
		
		for (int i = 0; i < NUM_POS; i++) {
			firstThrown  = 1 + (int)(Math.random() * 6);
			secondThrown = 1 + (int)(Math.random() * 6);
			combThrown    = String.valueOf(firstThrown) + String.valueOf(secondThrown);
			revCombThrown = String.valueOf(secondThrown) + String.valueOf(firstThrown);
			
			/* If thrown combination is not already in the table
			 * then create new Vector for positions and put them 
			 * mapped together in the table. */
			if (!combinations.containsKey(combThrown) && !combinations.containsKey(revCombThrown)) {		
				List npos = new Vector();
				npos.add(String.valueOf(i));
				combinations.put(combThrown, npos);
			/* If there is such combination as key in Hashtable
			 * then add only the position in the value field. */
			} else if (combinations.containsKey(combThrown)) {
				position = (List) combinations.get(combThrown);
				position.add(String.valueOf(i));
				combinations.put(combThrown, position);				
			} else if (combinations.containsKey(revCombThrown)) {
				position = (List) combinations.get(revCombThrown);
				(position).add(String.valueOf(i));
				combinations.put(revCombThrown, position);				
			}
		}
	}
}

