/**
 * 
 */
package com.cit.jcourse.svelikov.theme2.hettree;

/**
 * Class Test for OrderedBinTree class
 * Provide tests for insData,
 * prtTree and searchKey methods.
 * @author Svilen
 * @version 0.1  02.22.2007
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HeterogeneousTree newTree = new HeterogeneousTree();
			
		newTree.insData("43");
		newTree.insData("3f");
		newTree.insData("8sdfgs");
		newTree.insData("8787");
		newTree.insData("0.53453");
		newTree.insData("0");

			
		newTree.prtTree();                      /*invoke prtTree method*/
		Root data = newTree.searchKey("31");    /*invoke searchKey method with an argument*/
		if ( data != null ){
			System.out.println( "Елементът : " + data.keyCell + " е намерен!" );
		} else {
			System.out.println( "Няма такъв елемент!" );

		}
	}
}

