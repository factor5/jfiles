package com.cit.jcourse.svelikov.theme8.pool;

import java.util.ArrayList;

/**
 * Provide some test for the pool...
 * @author Svilen Velikov
 * @version 03.08.07
 */
public class TestPool {

	static Item t1, t2, t3;
	static ArrayList arr = new ArrayList(3);
	
	
	/**
	 * Creates the pool with initial capacity.
	 * When the pool is filled up with threads they are acuired and
	 * started. After the threads have done their tasks they are 
	 * placed back to the pool for later use.
	 * @param args
	 */
	public static void main(String[] args) {
		//the pool is created with: capacity of 3 items
		ThreadPool pool = new ThreadPool(3, 10000);
		
		try {
			Thread.sleep(300);
		} catch (InterruptedException e1) {}
		
		try {
			//acquire all three items from the pool
			arr.add(t1 = pool.acquire());
			arr.add(t2 = pool.acquire());
			arr.add(t3 = pool.acquire());
			
			t1.wakeUp();		//do some work
			t1.wakeUp();
			t2.wakeUp();		//do some work
			
			t3.wakeUp();		//do some work
			
			
			//acquire another one is not possible because the pool is empty now
			/*Item t5 = pool.acquire();
			t5.wakeUp();*/
			
			//about to release the acquired threads
			int countReleased = 0;
			while(countReleased < 3) {
				for (int i = 0; i < arr.size(); i++) {
					if(!((Item) arr.get(i)).getAcquired()) {
						pool.release((Thread) arr.remove(i));					
						countReleased++;	
						System.out.println(":: released > "+countReleased);
					}					
				}
			}
			
			//acquire a thread after the others are released
			Item t6 = pool.acquire();
			System.out.println("acquired t6");
			
			t6.wakeUp();
			
			System.out.println("t6 working");
			//release the last one
			while(!t6.getAcquired()) {}
			pool.release((Thread) t6);
			System.out.println("t6 released");
			
		} catch (NoPoolItemsException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
}
