/**
 * 
 */
package com.cit.jcourse.svelikov.theme4.textfilereverse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.cit.jcourse.svelikov.theme4.filewriter.WriteToFile;

/**
 * Reverses content of a text file. The content
 * is stored in StringBuffer. For reversing is used
 * reverse() method from StringBuffer class. 
 * @author Svilen Velikov
 * @version 02  03.10.07
 */
public class TextFileReverse extends WriteToFile {
	
	/** holds reversed content of the text file */
	private StringBuffer reversedText;
	
	/** holds readed content of the text file */
	private StringBuffer textData;
	
	/** the name of the file */
	private File fileName;
	
	/**
	 * Makes things to work.
	 * First invokes readTextFile method to get the 
	 * content of the file. Then reverseText is in turn.
	 * Finally the content of StringBuffer reversedText
	 * should be written in the same text file.
	 * @throws IOException 
	 */
	public void proccesFile() throws IOException {
		this.readTxtFile();
		this.writeRevText( reversedText, fileName );
	}
	
	/**
	 * Reads the content of the text file with name
	 * given by user and store the data in StringBuffer.
	 * @throws IOException 
	 */
	private void readTxtFile() throws IOException {
		String line = null;								/*store a line of text*/		
		fileName = inputFileName();                     /*user should input the name*/
		
		while( ! fileName.exists() ) {
			System.out.println("File not exist! Try again!");
			fileName = inputFileName();						
		}
		
		BufferedReader fromFile = new BufferedReader( new FileReader( fileName ) );
		textData = new StringBuffer();
		
		while ( ( line = fromFile.readLine()) != null ) {       
			textData.append(line+"\n");					/*append read lines to StringBufer*/
		}
		fromFile.close();
		System.out.println("The content of the file is read!");
		reversedText = textData.reverse();              /*reverses the content*/
	}
	
	/**
	 * Writes the reversed content back to the same file.
	 * @param reversedText reversed content ready to be written.
	 * @param fileName where to write.
	 * @throws IOException  if an IO error during writing.
	 */
	private void writeRevText( StringBuffer reversedText, File fileName ) throws IOException {		
		BufferedWriter toFile = new BufferedWriter(new FileWriter(fileName));
		int len = reversedText.length();			/*Calculates the length reversedText.*/
		
		/*write single chars to file until
		 *length of the sequence is reached
		 */ 
		int ch = 0;
		for (int i = 0; i < len; i++) {
			ch = reversedText.charAt(i);
			if( ch == '\n') {                 		/*adds new line in file*/     
				toFile.newLine();
				continue;                           /*and skip next line of code*/
			}
			toFile.write(ch);
		}	
		toFile.close();
		System.out.println("Reversing is completed!");
	}
}
