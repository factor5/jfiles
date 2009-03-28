package com.cit.jcourse.svelikov.theme2.hettree;

/**
 *  Define àn object of type Root represents
 *  one node in a binary tree of Objects.
 * @author Svilen
 *
 */	
class Root {

	/**
	 * constructor for the Root object
	 * @param data
	 */
	public Root(Object key) {
		keyCell = key;			//constructor
	}
	public Object keyCell;		//data cell
	public Root lChild;         //left inheritor
	public Root rChild;			//right inheritor
}
