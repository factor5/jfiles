/**
 * 
 */
package com.cit.jcourse.svelikov.theme6.improvedstack;

/**
 * Insert elements on the top of the provided stack.
 * @author Svilen Velikov
 * @version 14.04.07
 */
public class AddThread implements Runnable {
	
	/** The stack*/
	private Stack myStack;
		
	/** Holds the object to add*/
	private Object obj;
	
	/**
	 * Sets stack referense variable and object that
	 * should be added. Then start this thread.
	 * @param stack The stack
	 * @param obj   The object that should be added.
	 */
	public AddThread(Stack stack, Object obj) {
		this.myStack = stack;
		this.obj = obj;
		new Thread(this).start();
	}

	/**
	 * Adds one element on the top of the stack if it is not
	 * full. If it is full the thread should wait until any
	 * element is removed.
	 */
	public void run() {
		myStack.addition(obj);
	}
}	

