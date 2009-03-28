package com.cit.jcourse.svelikov.theme3.stack;

import java.util.EmptyStackException;

/**
 * Class Stack that represent some methods
 * for process list of elements.
 * @author Svilen
 * @version 02 01.03.07
 */
public class Stack {
	
	/** get reference to top element of the list */
	private TypeStack top;
	
	/** get number of elements */
	private int br = TypeStack.count;    
	
	/** define size of the stack */
	static int SIZE  = 5;   
	
	/**
	 * Check whether the stack is empty
	 * @return null if it is empty
	 */
	private boolean isEmpty() {
		return top == null;
	}
	
	/**
	 * If stack is full?
	 * @return true if stack is empty or not full
	 */
	public boolean notFull() {
		if ( isEmpty() )
			return true;
		else if ( br < SIZE )
			return true;
		else return false;
	}
	
	/**
	 * Insert elements on the top of the stack.
	 * @param obj to be inserted.
	 * @throws StackIsFullException when attempt 
	 *         to add extra elements in filled stack.
	 */
	public void add( Object obj ) throws StackIsFullException {
		
		TypeStack newStack = new TypeStack();   

		if ( notFull() ) {                     //insert new element if not full
			br++;
			newStack.dataCell = obj;		   //insert the new data in info field
			newStack.down = top;               //insert reference to previous element
			top = newStack;                    //top points to the new element
			
		} else                                 //else throw exeption
			throw new StackIsFullException("You can't add more elements! The Stack is full!");
	}
	
	/**
	 * Remove elements from the top of the stack
	 * if not empty.
	 * @return removed element
	 */
	public Object remove() {
		
		if ( isEmpty() )                     //if true, throw EmptyStackExeption 
			throw new EmptyStackException();
		TypeStack newstack = top;            
		top = top.down;                      //change reference to point previous
		br--;
		return newstack.dataCell;            //removed element 
	}
	
	/**
	 * Provide the top element of the stack
	 * for printAllElements method
	 */
	public void printAllElements() {
		printAllElements( top );
	}
	
	/**
	 * Prints all element of the list
	 * @param obj reference to the top element
	 */
	private void printAllElements( TypeStack obj ) {
		TypeStack curr = obj;
		if( curr != null ) {                      
			System.out.println( curr.dataCell );
			printAllElements( curr.down );
		}
	}
}
