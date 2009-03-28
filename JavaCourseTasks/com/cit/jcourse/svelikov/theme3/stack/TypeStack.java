package com.cit.jcourse.svelikov.theme3.stack;

/**
 * Define an object of type TypeStack
 * represent one element from the stack
 * @author Svilen
 */
class TypeStack {
	
	/** info field */
	public Object dataCell;      
	
	/** reference to the previous element */
	public TypeStack down;
	
	/** counter for the elements */
	static int count = 0; 
}
