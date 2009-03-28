package com.cit.jcourse.svelikov.theme8.observer;

import java.util.Observable;

/**
 * The supplier class is responsible for the supplying of 
 * new goods in the store. It is noticed when in the store
 * have sold any goods.
 * 
 * @author Svilen Velikov
 * @version 08.08.07
 */
public class Supplier extends Observable {
	
	/**
	 * Gets an order when in the store have sold any goods.
	 * @param order The order.
	 */
	public void setOrder(String order) {
		System.out.println("Model#"+order+" will be supplied!");
		//supplyGoods(order);
	}
	
	/**
	 * Executes the order.
	 */
	private void supplyGoods(String order) {
		System.out.println("");
	}

}
