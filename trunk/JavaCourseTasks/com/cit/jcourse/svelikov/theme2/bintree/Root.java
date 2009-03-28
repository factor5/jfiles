/**
 * 
 */
package com.cit.jcourse.svelikov.theme2.bintree;

/**
 *  Define àn object of type Root represents
 *  one node in a binary tree of integers.
 * @author Svilen
 *
 */
class Root {
	
	/**
	 * constructor for the Root object
	 * @param data
	 */
	Root (int data){  
	keyCell = data;
	lChild  = null;
	rChild  = null;
	}
	
	int keyCell;     //data cell
	Root lChild;     //left inheritor
	Root rChild;     //right inheritor
}
