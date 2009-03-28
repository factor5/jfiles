/**
 * 
 */
package com.cit.jcourse.svelikov.theme6.improvedstack;

/**
 * Class Stack that represent some methods for process list of
 * elements. The methods add() and remove() are starting threads
 * providing the current object as parameter and an object that
 * should be added for the AddThread thread.
 * @author Svilen Velikov
 * @version 14.04.07
 */
public class Stack {
	
	/** The top element of the list. */
	private TypeStack top;
	
	/** Counter for the elements in the stack. */
	private int counter = TypeStack.count;    
	
	/** The size of the stack. */
	private static int SIZE;
	
	/**
	 * Sets the size of the stack.
	 * @param size How many objects can be stored in the stack.
	 */
	public Stack(int size){
		Stack.SIZE = size;
	}
	
	/**
	 * Checks whether the stack is empty.
	 * @return True if it's empty. Otherwise returns false.
	 */
	public boolean isEmpty() {
		return top == null;
	}
	
	/**
	 * Checks whether in the stack has empty slots.
	 * @return True if stack is empty or not full. Otherwise returns false.
	 */
	public boolean hasRoom() {
		if ( isEmpty() )
			return true;
		else if ( counter < SIZE )
			return true;
		else return false;
	}
	
	/**
	 * Creates new addition thread and provides the current object
	 * and the object that must be added to the stack.
	 * @param obj The object to be added.
	 */
	public void add(Object obj) {
		AddThread addElement = new AddThread(this, obj);
	}
	
	/**
	 * Creates new removing thread provides the current object.
	 */
	public void remove() {
		RemoveThread removeElement = new RemoveThread(this);
	}
	
	/**
	 * Adds Object element provided as parameter. If has no empty
	 * slots in the stack the thread is waiting until an element is 
	 * removed.
	 * @param obj The object that should be added to the stack.
	 */
	public synchronized void addition(Object obj){
		while ( !hasRoom() ) {        			//should wait if no empty slots
			try {
				System.out.println(Thread.currentThread().getName()+">> Waiting to remove!");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		TypeStack newElement = new TypeStack(); //creates new empty element
		newElement.dataCell = obj;				//inserts object in the info field
		newElement.down = top;		     		//inserts pointer to previous top
		top = newElement;           	  		//sets top to point the new element		
		counter++;								//sets counter
		System.out.println(Thread.currentThread().getName()+". add : "+obj);
		notifyAll();
	}
	
	/**
	 * Removes the top element from the stack. If the stack is
	 * empty the thread should wait for an element to be added.
	 */
	public synchronized void removing(){
		while ( isEmpty() ) {				//should wait if no elements to remove
			try {
				System.out.println(Thread.currentThread().getName()+">> Waiting to add!");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
		TypeStack removed = top;		    //gets referense to top
		top = top.down; 	  				//moves pointer one step down
		counter--;							//sets counter
		System.out.println(Thread.currentThread().getName()+".. remove : "+removed.dataCell);
		notifyAll();
	}
}