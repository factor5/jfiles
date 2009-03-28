package com.cit.jcourse.svelikov.theme8.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * This class is intrested in changes that occurs in
 * the availability in the store.
 * 
 * @author Svilen Velikov
 * @version 08.08.07
 */
public class GoodsObserver implements Observer {

	/**
	 * Gets the Supplier and SoldGoods which are responsible
	 * for supplying new goods and for keeping track of sold
	 * goods.
	 */
	public GoodsObserver() {
		supplier = new Supplier();
		sold     = new SoldGoods();
	}

	/** The supplier. */
	private Supplier  supplier;
	
	/** The tracker for the sold goods. */
	private SoldGoods sold;
	
	/**
	 * Called when any of the observable objects have been changed. 
	 */
	public void update(Observable obs, Object arg) {
		if (obs instanceof Store) {
			if(arg instanceof Notebook) {	//anything were sold
				supplier.setOrder(""+arg.hashCode());
				sold.addToList((Notebook) arg);
			} else {						//anything were supplied in store
				sold.removeFromList((String) arg);
			}	
		}
	}
}
