package com.cit.jcourse.svelikov.theme8.singleton;

/**
 * A class that can have only one instance. That instance can be get
 * trough the static getInstance() method. If there is created one this
 * method just returns null.
 * 
 * @author Svilen Velikov
 * @version 31.07.07
 */
class Singleton {
	
	/**
	 * The private constructor is to prevent creating the
	 * default one from the compiler.
	 */
	private Singleton() {}
	
	/** True if we allready have one instance. */
	static boolean hasOne = false;
	
	/** Holds a simple string data. */
	private String name = "Svilen";
	
	/** Holds another simple string data. */
	private String surname = "Velikov";
	
	/**
	 * The only way to get an instance of this class.
	 * @return The Singleton's instance or null if there is
	 * 		   one created allready.
	 */
	public synchronized static Singleton getSingleton() {
		if(!hasOne) {
			hasOne = true;
			return new Singleton();			
		} else {
			return null;
		}
	}
	
	/**
	 * @return The name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return The surname.
	 */
	public String getSurname() {
		return surname;
	}
}

