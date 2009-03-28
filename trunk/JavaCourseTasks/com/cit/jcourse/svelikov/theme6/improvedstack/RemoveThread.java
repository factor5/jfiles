/**
 * 
 */
package com.cit.jcourse.svelikov.theme6.improvedstack;

/**
 * Class thread that removes elements from provided stack.
 * @author Svilen Velikov
 * @version 14.04.07
 */
public class RemoveThread implements Runnable{
	
	/** The stack*/
	private Stack myStack;
	
	/**
	 * Sets stack referense variable and start this thread.
	 * @param stack The stack.
	 */
	public RemoveThread(Stack stack) {
		this.myStack = stack; 
		new Thread(this).start();
	}
	
	/**
	 * Removes last entered (Top) element from the stack if
	 * it isn't empty. If it's empty the thread should wait
	 * until any element is added.
	 */
	public void run() {
		myStack.removing();
	}
}
