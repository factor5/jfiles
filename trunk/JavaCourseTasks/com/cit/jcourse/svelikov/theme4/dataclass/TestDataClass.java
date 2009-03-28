/**
 * 
 */
package com.cit.jcourse.svelikov.theme4.dataclass;

import java.io.IOException;

import com.cit.jcourse.svelikov.theme4.dirbrowser.Test;

/**
 * Provide arguments for the DataClasses methods
 * such a filename and an object to be written and
 * after that to be read from the file.
 * @author Svilen Velikov
 * @version 02 03.27.07
 */
public class TestDataClass {

	/**
	 * Create an instance of DataClass and pass it as
	 * an parameter to saveObject() method to be saved to 
	 * a file with a name also provided from this class.
	 * Then appropriate returned object from getObject() method
	 * to a theClass variable of type DataClass.
	 * Last prints out the data fields of the returned object.
	 * @param args
	 */
	public static void main(String[] args) {
		String path = "rock";							 /*filename*/
		DataClass dc = new DataClass();					 /*create DataClass instance*/
		
		Test tto = new Test();
		
		try {
			dc.saveObject(path, tto);					 /*the object should be save in file*/
			DataClass returnedClass = dc.getObject(path);/*get DataClass returned with the Object in it*/
			System.out.println(returnedClass);
			Test te = (Test) returnedClass.getSerializedObj();
			te.main(args);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}