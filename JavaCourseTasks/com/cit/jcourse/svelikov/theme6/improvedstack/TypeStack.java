/**
 * 
 */
package com.cit.jcourse.svelikov.theme6.improvedstack;

/**
 * Define an object of type TypeStack
 * represent one element from the stack
 * @author Svilen Velikov
 * @version 12.04.07
 */
public class TypeStack {
	
	/** info field */
	public Object dataCell;      
	
	/** reference to the previous element */
	public TypeStack down;
	
	/** counter for the elements */
	static int count = 0; 
}
