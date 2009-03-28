/**
 * 
 */
package com.cit.jcourse.svelikov.theme2.bintree;

/**
 * Class Test for OrderedBinTree class
 * Provide tests for insData,
 * prtTree and searchKey methods.
 * @author Svilen
 * @version 0.5   02.22.2007
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OrderedBinTree newTree = new OrderedBinTree();
		
		newTree.insData(30);
		newTree.insData(10);
		newTree.insData(10);
		newTree.insData(50);
		newTree.insData(15);
		newTree.insData(60);
		newTree.insData(60);
		newTree.insData(75);
		
		newTree.prtTree();                   /*invoke prtTree method*/
		
		Root data = newTree.searchKey(6);    /*invoke searchKey method with an argument*/
		if ( data != null ){
			System.out.println( "Елементът : " + data.keyCell + " е намерен!" );
		} else {
			System.out.println( "Няма такъв елемент!" );

		}
		
	}

}
