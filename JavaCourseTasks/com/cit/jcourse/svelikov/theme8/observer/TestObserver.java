package com.cit.jcourse.svelikov.theme8.observer;

import java.util.Hashtable;
import java.util.Vector;

/**
 * Provides some tests for the Store class after it's initialized
 * with a list of goods.
 * 
 * @author Svilen Velikov
 * @version 08.08.07
 */
public class TestObserver {
	
	/**
	 * Creates the Store with initial list of goods. Then 
	 * a couple of sells and supplyings are realized to test
	 * the observer. 
	 * @param args
	 */
	public static void main(String[] args) {
		Hashtable goods = new Hashtable();
		Notebook  pc1, pc2, pc3;
			pc1 = new Notebook();
			Vector v1 = new Vector();
				v1.add(pc1);
			pc2 = new Notebook();
			Vector v2 = new Vector();
				v2.add(pc2);
			pc3 = new Notebook();
			Vector v3 = new Vector();
				v3.add(pc3);
			goods.put(""+pc1.hashCode(), v1);
			goods.put(""+pc2.hashCode(), v2);
			goods.put(""+pc3.hashCode(), v3);
			
		//creating the store
		Store store = new Store(goods);	
		
		//add another piece of 'pc1' and try to sell three of those
		store.addNewGoods(""+pc1.hashCode(), pc1);
		store.sellGoods(""+pc1.hashCode());
		store.sellGoods(""+pc1.hashCode());
		store.sellGoods(""+pc1.hashCode());
		
/*		store.sellGoods(""+pc3.hashCode());
		store.sellGoods(""+pc2.hashCode());
		store.sellGoods(""+pc1.hashCode());
		store.sellGoods(""+pc3.hashCode());*/
		
		//add a new model in the store
/*		Notebook pc4 = new Notebook();
		store.addNewGoods(""+pc4.hashCode(), pc4);	
		store.addNewGoods(""+pc4.hashCode(), pc4);
		store.sellGoods(""+pc4.hashCode());
		store.sellGoods(""+pc4.hashCode());
		store.sellGoods(""+pc4.hashCode());
		store.addNewGoods(""+pc4.hashCode(), pc4);*/
		
		//System.out.println("presence : "+store.getPresence());
		//store.showStock();
	}
}
