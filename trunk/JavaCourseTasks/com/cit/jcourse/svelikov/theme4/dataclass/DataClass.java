/**
 * 
 */
package com.cit.jcourse.svelikov.theme4.dataclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Class that should be serialized. Contains some
 * objects storing data and two methods for saving and
 * getting the class itself.
 * @author Svilen Velikov
 * @version 02 03.27.07
 */
public class DataClass implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public DataClass(){}
	
	private void writeObject(java.io.ObjectOutputStream out)
		throws IOException{}
    private void readObject(java.io.ObjectInputStream in)
     	throws IOException, ClassNotFoundException{}
    
	
	/** the Object that should be stored */
	private Object obj;
	
	/**
	 * @return object that was stored in this class
	 */
	public Object getSerializedObj() {
		return obj;
	}
	
	/**
	 * Stores an Object given as parameter in this class 
	 * and then saves the class in file.
	 * @param path provides a name for the file where the object will be save.
	 * @param o the object that should be stored.
	 * @throws IOException if error ocurs during the file operation.
	 */
	public void saveObject( String path, Object o ) throws IOException {
		File filePath = new File(path);					/*File object with the given name*/
		ObjectOutputStream outStream = null;
		obj = o;										
		
		try {
			if( !filePath.exists() ) {					/*if the file not exist create new*/
				System.out.println("The file doesn't exist! ->");
				filePath.createNewFile();
				System.out.println("The file was created! ->");
			} else {									/*the file exists so delete and create new*/
				System.out.println("The file allready exists! ->");
				filePath.delete();
				filePath.createNewFile();
				System.out.println("The file was overwriten! ->");
			}
			outStream = new ObjectOutputStream( new FileOutputStream(filePath)); /*create stream*/
			outStream.writeObject(this);				/*writes the DataClass*/
		} finally {
			if( outStream != null )						/*close the stream if necessary*/
				outStream.close();
		}
	}

	
	/**
	 * Reads DataClass which was written in file and return an
	 * instance to it.
	 * @param path the name of the file where the DataClass is saved.
	 * @return an instance of DataClass that is read from the file.
	 * @throws FileNotFoundException if no such file as given.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public DataClass getObject( String path ) throws FileNotFoundException, IOException, ClassNotFoundException {
		File filePath = new File(path);						/*create a File object*/
		ObjectInputStream inStream = null;

		try {
			if( filePath.exists() && filePath.canRead() ) {	/*file exists and can be read*/
				inStream = new ObjectInputStream( new FileInputStream(filePath));/*create input stream*/
				/*will return an istance of DataClass read from file*/
				return (DataClass) inStream.readObject();
			} else {
				throw new IOException("The file doesn't exists!");/*the file doesn't exist*/
			}
		} finally {
			if( inStream != null ) 							/*close the stream*/
				inStream.close();
		}		
	}
}
