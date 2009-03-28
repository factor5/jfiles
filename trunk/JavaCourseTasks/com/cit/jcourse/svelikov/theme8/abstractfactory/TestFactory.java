package com.cit.jcourse.svelikov.theme8.abstractfactory;

/**
 * Class used to test the factory. It first creates an factory and
 * then calls the two methods that returns instance of any class.
 * 
 * @author Svilen Velikov
 * @version 31.07.07
 */
public class TestFactory {

	/**
	 * Invokes createInstance method from the factory that returns
	 * a class depending of the argument passed to the factory 
	 * constructor.
	 * The second test is to 
	 * @param args
	 */
	public static void main(String[] args) {
		
		MyFactoryPattern factory = new MyFactoryPattern();
		
		DataWorker dw = (DataWorker)factory.createInstance("AAAAAA");
		dw.prt();
		
		DataWorker pd = (DataWorker)factory.createInstanceByReflection("bbbbbbbb");
		pd.prt();
	}
}
