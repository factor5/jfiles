package com.cit.jcourse.svelikov.theme8.singleton;

/**
 * Class to test the Singleton trying to create two
 * different instances.
 * 
 * @author Svilen Velikov
 * @version 30.07.07
 */
public class TestSingleton {

	/**
	 * Trying to get two different instances from the
	 * singleton class. If suceed just gets and prints 
	 * a field from the singleton class.
	 * @param args
	 */
	public static void main(String[] args) {
		
		Singleton s = Singleton.getSingleton();
		if(s != null)
			System.out.println(s.getName());
		
		Singleton s2 = Singleton.getSingleton();
		if(s2 != null)
			System.out.println(s2.getSurname());
	}

}

