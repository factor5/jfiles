package com.cit.jcourse.svelikov.theme2.hettree;

/**
 * A class that provide methods for 
 * process a binary tree of Objects
 * @author Svilen
 * @version 0.1  02.22.2007
 */
public class HeterogeneousTree {
	
	/**
	 * get a reference to Root
	 */
	Root root;                             /*The root of the tree*/
	
	/**
	 * Inserting an element in the tree
	 * @param key  element to be inserted
	 */
	public void insData( Object key ) {
		if ( root == null ) {                      /*if the tree not exist*/
			root = new Root( key );                /*create new tree*/
		} else {                                   /*tree exist*/
			if ( searchKey(key) != null ) {        /*search for the key to ecape duplication*/
				System.out.println("Числото : " + key + " се повтаря!" );
			} else { 							   /*no such key*/
				Root current = root;           
				Root parent;
				while( true ){
					parent = current;
					if ( key.hashCode() < current.keyCell.hashCode()  ) {   /*goes to the left*/ 
						current = current.lChild;
						if ( current == null ) {            				/*if no inheritor*/ 
							parent.lChild = new Root( key );				/*insert element*/
							return;
						}
					} else {                                				/*goes to the right*/
						current = current.rChild;    
						if ( current == null ) {           				    /*if no inheritor*/
							parent.rChild = new Root( key );				/*insert element*/
							return;
						}
					} //end else to right
				} //end while
			} //end else no such key
		} //end else no root
	} //end insData
	
	/**
	 * Provide the root of the tre
	 * for the prtTree method
	 */
	public void prtTree() {
	  Root forPrt = root;
	  prtTree( forPrt );
	}
	
	/**
	 * Trace the tree by method left/root/right
	 * 'in-order' and print all elements
	 * @param root  the root of the tree
	 */
	public void prtTree(Root root) {
		Root prt = root;
		if ( prt != null ){
			prtTree( prt.lChild );
			System.out.println("prtkey:" + prt.keyCell );
			prtTree( prt.rChild );
		}
	}
	
	/**
	 * Search the tree for element
	 * @param key element wich has to be found
	 * @return
	 */
	public Root searchKey( Object key ) {
		Root curr = root;
		while ( curr.keyCell.hashCode() != key.hashCode() ) {
			if ( key.hashCode() < curr.keyCell.hashCode() ) {  /*requested key is on the left*/
				curr = curr.lChild;
			} else {
				curr = curr.rChild;      /*requested key is on the right*/
			}
			if ( curr == null )          /*can't find*/
				return null;
		}
		return curr;                      
	}
}
