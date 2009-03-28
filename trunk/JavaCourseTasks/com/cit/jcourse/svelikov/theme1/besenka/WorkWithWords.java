package com.cit.jcourse.svelikov.theme1.besenka;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Svilen
 *
 */
public class WorkWithWords {
	
	/**
	 * 
	 * @param countErrors
	 */
	public WorkWithWords(int countErrors ) {
		this.countErrors = countErrors;
	}

	/** Number of possible error. */
	private int countErrors;
	
	/**
	 *
	 */
	public void enter() {
		String n = null;
		//workWithWords words = new workWithWords();

		String realWord = randomize(n); //generate a word
		String hiddenWord = generHiddWord(realWord); //generate a primary hidden word
		String oldHidWord = hiddenWord; //need for error counter
		System.out.println("Намислих си една дума : " + hiddenWord);//print the hidden word
		String newHiddenWord = hiddenWord;

		while (realWord.compareTo(newHiddenWord) != 0 && (countErrors < 10)) {
			/*read from console*/
			InputStream in = System.in;
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line = null;

			System.out.println("Буква : ");
			try {
				line = br.readLine();
			} catch (Exception e) {
			}

			String fineData = clearData(line); //clear input data
			System.out.println("Избрахте буква : " + fineData);

			newHiddenWord = searchForChar(fineData, realWord, newHiddenWord);
			if (newHiddenWord.compareTo(oldHidWord) == 0) {
				countErrors++;
			} else {
				oldHidWord = newHiddenWord;
			}
			System.out.println("Досега имате " + countErrors + " грешки.");
			System.out.println(newHiddenWord);
		} //end while

		if (countErrors == 10) {
			System.out.println("Бяхте обесен!!! Направихте " + countErrors
					+ " грешки.");
		} else {
			System.out.println("БРАВО познахте думата!!! Направихте само "
					+ countErrors + " грешки.");
		}
	}

	/**
	 * Get randomized word 
	 * @param rnd
	 * @return rndWord 
	 */
	public String randomize(String rnd) {
		int numWords = 5; //number of words in the array
		String words[] = { "kook", "car", "monitor", "textbook", "kokakoloa" };
		int n = (int) (Math.random() * numWords);
		String rndWord = words[n];
		//System.out.println(rndWord);
		return rndWord;
	}

	/**
	 * Generate the hidden word
	 * @param realWord
	 * @return hiddenWord 
	 */
	public String generHiddWord(String realWord) {
		String Word = realWord;
		int len = Word.length();
		String fChar = Word.substring(0, 1); //get only the first char from the word
		String lChar = Word.substring((len - 1), len); //get the last one
		String hiddenWord = fChar;
		for (int i = 1; i < (len - 1); i++) {
			hiddenWord = hiddenWord + "."; //here it makes the hiddenWord
		}
		hiddenWord = hiddenWord + lChar;
		return hiddenWord;
	}

	/**
	 * Clear the data entered form user
	 * @param m holds the data enetred
	 * @return userInput cleared data
	 */
	public String clearData(String m) {
		String userInput = m.trim(); //remove whitespaces
		if (userInput.length() > 1) {
			userInput = userInput.substring(0, 1); //get only the first symbol
		}
		return userInput;
	}

	/**
	 * Searches for a char entered by the user in the word 
	 * toGuess.
	 * @param userTurn The users turn.
	 * @param toGuess  The word to search in.
	 * @return True if there is a match.
	 */
	private boolean contains(String userTurn, String toGuess) {
		boolean match = false;
		for (int i = 0; i < toGuess.length(); i++) {
			if(userTurn.equals(toGuess.substring(i, i+1))) {
				match = true;
				break;
			}
		}
		return match;
	}

	/**
	 * Search the char entered from the user in the word
	 * @param fineData it's the character to search for 
	 * @param realWord 
	 * @param hWord
	 * @return
	 */
	public String searchForChar(String fineData, String realWord, String hWord) {
		String rWord = realWord;
		String toGuess = rWord.substring(1, (rWord.length() - 1));
		char[] hidWrd = hWord.toCharArray();
		String userTurn = fineData;

		//if (toGuess.contains(userTurn)) {
		if (contains(userTurn, toGuess)) {
			int ind;
			int startIndex = 0;
			int lastIndex = toGuess.lastIndexOf(userTurn); //index of last occurrence to the char 

			/*if there are 2 equal chars ..*/
			if (lastIndex == (toGuess.indexOf(userTurn, startIndex) + 1)) {
				hidWrd[lastIndex] = userTurn.charAt(0);
				hidWrd[lastIndex + 1] = userTurn.charAt(0);
			}
			/*if there is only one char on the first position*/
			if (lastIndex == 0) {
				ind = toGuess.indexOf(userTurn, startIndex);//locate index to replace dot with char
				hidWrd[ind + 1] = userTurn.charAt(0); //replace dot with char
			}
			/*start a while loop to search the indexes where the chars stand*/
			else {
				while (startIndex != lastIndex) {
					ind = toGuess.indexOf(userTurn, startIndex);//locate index to replace dot with char
					hidWrd[ind + 1] = userTurn.charAt(0); //replace dot with char
					/*if there is another char in the word like the choosen one*/
					if (ind < lastIndex) {
						startIndex = ind + 1;
					} else {
						startIndex = ind;
					}
				} //end while
			}//end else
		}//end if
		String newHiddenWord = String.copyValueOf(hidWrd);
		return newHiddenWord;
	}
}