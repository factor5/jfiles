package com.cit.jcourse.svelikov.theme8.observer;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Vector;

/**
 * The Store is initialy supplied with goods. As this
 * class is observable it notifies the Observer for every
 * changes that occurs in the list.
 * 
 * @author Svilen Velikov
 * @version 08.08.07
 */
public class Store extends Observable {
	
	/** The default constructor. */
	//public Store() {}
	
	/**
	 * Initializes the table with initial list of goods and
	 * registers the observer that is intrested in changes
	 * occured in the goods list. 
	 * @param goods The initial list with goods.
	 */
	public Store(Hashtable goods) {
		this.addObserver(new GoodsObserver());
		inStock = new Hashtable(goods);
	}

	/** The list with goods in stock. */
	private Hashtable inStock;
	
	/**
	 * Adds new goods in the store and notifies the observer.
	 * @param model		The id.
	 * @param notebook  The goods.
	 */
	public synchronized void addNewGoods(String model, Notebook notebook) {
		if(inStock.containsKey(model)) {
			Vector v = (Vector)inStock.get(model);
			v.addElement(notebook);
		} else {
			Vector pieces = new Vector();
			pieces.addElement(notebook);
			inStock.put(model, pieces);
		}
		setChanged();
		notifyObservers(model);
	}
	
	/**
	 * Sells any goods and notifies the observer for that.
	 * @param model The id.
	 */
	public synchronized void sellGoods(String model) {
		Notebook sold = null;
		if(inStock.containsKey(model)) {
			Vector pieces = (Vector) inStock.get(model);
			sold = (Notebook) pieces.remove(pieces.size()-1);
			System.out.println("Sold Notebook#"+sold.hashCode());
			if(pieces.isEmpty())		//all pieces from that model are sold
				inStock.remove(model);	//remove model from the goods table
			setChanged();
			notifyObservers(sold);
		} else {
			System.out.println("Acquired Model#"+model+" is out of order!");
		}
	}
	
	/**
	 * @return The number of goods.
	 */
	public int getPresence() {
		return inStock.size();
	}
	
	/**
	 * Prints out the availability of the store.
	 */
	public void showStock() {
		for (Enumeration e = inStock.keys(); e.hasMoreElements();) {
			String id = (String) e.nextElement();
			System.out.println("Model#"+id);
		}
	}
}
