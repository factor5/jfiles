package com.cit.jcourse.svelikov.theme8.abstractfactory;

/**
 * This interface is implemented by the classes for
 * which the factory returns instances.
 * 
 * @author Svilen Velikov
 * @version 31.07.07
 */
public interface DataWorker {
	
	/** This method should print any data. */
	public void prt();
	
	/** Method used to set data in the class. */
	public void setAndChangeData(String aData);
	
	//String data = null;
}