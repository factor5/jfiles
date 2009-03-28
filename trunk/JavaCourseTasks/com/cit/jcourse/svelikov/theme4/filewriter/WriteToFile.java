/**
 * 
 */
package com.cit.jcourse.svelikov.theme4.filewriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.cit.jcourse.svelikov.theme4.readers.ConsoleReader;

/**
 * A class for reading from console and 
 * writing to file until only one dot is
 * inserted.
 * @author Svilen
 * @version 02 03.08.07
 */
public class WriteToFile {
	
	/** the name of the file */
	private File fName;                                    
	
	/**
	 * Invokes createFile method first to 
	 * ensure a place for data to be written.
	 * Then calls readConsole where the user
	 * can insert some text.
	 * @throws IOException 
	 */
	public void handle() throws IOException {
		this.createFile();
		this.readConsole();
	}
	
	/**
	 * Prompt the user to enter a name for the file.
	 * If an empty string is inserted the program exits. 
	 * @return string fName that hold the name for the file
	 * @throws IOException 
	 */
	protected File inputFileName() throws IOException {
		String name = "";                                /*will hold the name of the file in string representation*/
		System.out.println("Type file name without extension or empty string for exit : ");
	
		name = ConsoleReader.readString();	
		if ( "".equals(name) ) System.exit(0);            /*exit program because of empty string*/	
		name = name.trim();                               /*remove blanc fields*/
		
		while ( name.endsWith(".") ) {                    /*remove dots from the end of the string*/
			name = name.substring( 0, name.length()-1 );
		}
		
		name += ".txt";                                   /*append a file extension*/		
		return fName = new File(name);
	}
	
	/**
	 * Attempt to create a new file with the given name.
	 * @return true if succeed
	 * @throws IOException if IO error occurs during creation a file
	 */
	private boolean isCreated() throws IOException {
		boolean crf = false;
		crf = fName.createNewFile();          		/*attempt to create a new file*/
		return crf;                                 /*true if succeed*/
	}
	
	/**
	 * Gets the filename inserted from user and checks 
	 * whether the name already exists. If not, tryes
	 * to create new file with that name. If exists 
	 * prompt the user to choose whether to overwrite
	 * or not.
	 * @return if creation is successful
	 * @throws IOException if 
	 */
	private boolean createFile() throws IOException {
		String ch = null;
		fName = inputFileName();                    /*get the file name*/
		if ( fName.exists() ) {                     /*if entered name exists*/
		System.out.println("File exists! Overwrite (y/n)? : ");
		ch = ConsoleReader.readString();
			
			if ( ch.equals("n") ) {                 /*file exists cancel overwrite*/
				createFile();
			} else {                                /*file exists and must overwrite*/
				fName.delete();                     /*delete file first*/
				if ( isCreated() ) {                /*successfully created*/
					System.out.println("The File was overwriten!");
				} else {                            /*failed*/
					System.out.println("Can't create file!");
					createFile();                   /*recurrence*/
				}
			}
		} else {                                    /*file not exist*/
			if ( isCreated() ) {                    /*successfully created*/
				System.out.println("The File was created!");
			} else {                                /*failed*/
				System.out.println("Can't create file!");
				createFile();                       /*recurrence*/
			}			
		}
		return true;	                            /*the file was created*/
	}
	
	/**
	 * Create a FileWriter instance and 
	 * start reading lines from console and 
	 * writing them into the file until encounter
	 * only one dot.
	 * @throws IOException 
	 */
	private void readConsole() throws IOException {
		String line = "";                           /*temp string*/
		BufferedWriter toFile = new BufferedWriter(new FileWriter(fName)); /*FileWriter object*/
		System.out.println("Start writing!");
		
		while ( ! ".".equals(line) ) {        	    /*read lines until only one dot is inserted*/
				line = ConsoleReader.readString(); 
				if( ".".equals(line) ) continue;    /*skip the last dot inserted*/
				toFile.write( line );               /*write a line to the file*/
				toFile.newLine();
		}
		toFile.close();						        /*close file finally*/                  
	}
		
}


