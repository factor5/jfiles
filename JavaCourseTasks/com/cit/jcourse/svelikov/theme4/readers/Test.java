/**
 * 
 */
package com.cit.jcourse.svelikov.theme4.readers;

/**
 * Test class that prompts the user to make 
 * a choice from four different possibilityes
 * for testing the ConsoleReader class.
 * @author Svilen
 * @version 02 03.09.07
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int choise = 0;
		String ch = "y";
		
		while ( ! "".equals(ch) ) {
			System.out.println("[1]->String..[2]->int..[3]->char..[4]->float..[5]->exit :");
			choise = ConsoleReader.readInt();
			
			switch (choise) {
				/*test readString method*/
				case 1: String prtS = ConsoleReader.readString();
			       	System.out.println("String: "+prtS); break;
		        /*test readInt method*/
				case 2: int prtI = ConsoleReader.readInt();
					System.out.println("Int: "+prtI);    break;
				/*test readChar method*/	
				case 3: char prtC = ConsoleReader.readChar();  
					System.out.println("Char: "+prtC);   break;
				/*test readFloat method*/
				case 4: float prtF = ConsoleReader.readFloat(); 
					System.out.println("Float: "+prtF);  break;
				/*exit if reach an empty string*/
				case 5: System.exit(0);
				default: System.out.println("Invalid choise!");
			}
			System.out.println("Press any key or [enter] for exit : ");
			ch = ConsoleReader.readString();
		}
	}
}

