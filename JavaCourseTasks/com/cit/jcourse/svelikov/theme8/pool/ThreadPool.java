package com.cit.jcourse.svelikov.theme8.pool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The ThreadPool class creates an ArrayList that is filled
 * with initial number of new threads. That threads are ready
 * to be used for the task for which they are designed.
 * 
 * @author Svilen Velikov
 * @version 03.08.07
 */
public class ThreadPool {
	
	/**
	 * The constructor sets the size of the pool and calls
	 * a method to fill the pool with the objects to be ready
	 * for use.
	 * @param numThreads The number of threads that can be
	 * 					 stored in the pool.
	 */
	public ThreadPool(int numItems, int precise) {
		numberItemsInPool = numItems;
		piPrecise = precise;
		createPool();
	}
	
	/** An synchronized ArrayList to store the items. */
	private List items;
	/** The number of items tha can be stored in the pool. */
	private int  numberItemsInPool;
	/** ... */
	private int  piPrecise;
	
	/**
	 * Creates the list tha will hold the items and fills up
	 * this list with new items.
	 * 
	 * In this case the items are threads that are initialy 
	 * initialized in the time they are created.
	 */
	private void createPool() {	
		Thread t;
		items = Collections.synchronizedList(new ArrayList(numberItemsInPool));
		for (int i = 0; i < (numberItemsInPool); i++) {
			t = new CalculatingThread(piPrecise);
			t.start();
			items.add(t);
		}
	}
	
	/**
	 * Gets an item from the pool if there are some and returns
	 * it to the caller. Otherwise it throws exception to notice
	 * that the pool is empty.
	 * @return The item acquired from the pool. 
	 * @throws NoPoolItemsException If the pool is empty.
	 */
	public Item acquire() throws NoPoolItemsException {
		Item anItem;
		if(items.isEmpty()) {
			throw new NoPoolItemsException("Empty pool!!!");
		} else {
			anItem = (Item) items.remove(items.size()-1);
		}
		return anItem;	
	}
	
	/**
	 * Put the item released from the client in the pool.
	 * @param releasedItem The item that is no longer need to client.
	 */
	public void release(Thread releasedItem) {
		items.add(releasedItem);
	}
}
