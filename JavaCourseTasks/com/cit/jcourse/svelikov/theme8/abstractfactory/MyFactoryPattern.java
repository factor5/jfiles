package com.cit.jcourse.svelikov.theme8.abstractfactory;

/**
 * This class represent an implementation of the abstract factory
 * pattern. It returns instance of any class depending of the argument
 * passed to the 
 * @author Svilen Velikov
 * @version 31.07.07
 */
public class MyFactoryPattern {
	
	/**
	 * The default constructor.
	 */
	public MyFactoryPattern() {}
	
	/**
	 * Decides for which class to return an instance depending
	 * of the result returned from isCapital method.
	 * @return Any of the two possible classes.
	 */
	public DataWorker createInstance(String aData) {
		DataWorker neededClass;
		if(isCapital(aData))
			neededClass = new SmallLetters(aData);
		else
			neededClass = new BigLetters(aData);
		return neededClass;
	}
	
	/**
	 * 	/**
	 * Decides for which class to return an instance depending
	 * of the result returned from isCapital method. In this 
	 * method the instance is created using reflection.
	 * @param aData Any data to be changed.
	 * @return The class instance.
	 */
	public DataWorker createInstanceByReflection(String aData) {		
		Class theClass = null;
		DataWorker neededClass = null;
		
		try {
			if(isCapital(aData)) {
				theClass = Class.forName("com.cit.jcourse.svelikov.theme8.abstractfactory.SmallLetters");
			} else {
				theClass = Class.forName("com.cit.jcourse.svelikov.theme8.abstractfactory.BigLetters");
			}
			neededClass = (DataWorker)theClass.newInstance();	//creates instance for the class needed	
			neededClass.setAndChangeData(aData);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return neededClass;
	}
	
	/**
	 * Checks if the first letter of the given string is
	 * a capitall or not.
	 * @return The flag that says that.
	 */
	private boolean isCapital(String data) {
		if(data.charAt(0) < 65 || data.charAt(0) > 90)	//small letter
			return false;	
		else											//big letter
			return true;	
	}
}
