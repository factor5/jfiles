/**
 * 
 */
package com.cit.jcourse.svelikov.theme5.paging;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cit.jcourse.svelikov.theme4.readers.ConsoleReader;

/**
 * A test class that pass a text file to TextToArrList class
 * and gets back an ArrayList. Then passes this ArrayList to
 * the constructor of PageBean class. This test class also
 * provides a simple navigation menu for invokation of PageBean
 * class.
 * @author  Svilen Velikov
 * @version 02 30.03.07
 */
public class TestPageBean {

	/**
	 * Creates an ArrayList that will be filled with elements from
	 * TextToArrList class. PageBean class is instantiated later with 
	 * prepared ArrayList and an int number as arguments for the constructor. 
	 * Then the navigation menu is built and may be used to iterate over the list.
	 * @param args
	 */
	public static void main(String[] args) {
		
		List arList = new ArrayList();					/*will hold a list of rows */
		List currentPage = new ArrayList();				/*will hold current page as list of rows*/
		
		TextToArrList tta = new TextToArrList();	
		try {
			arList = tta.readTxtFile("123.txt");		/*pass a text file to be separated*/
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*pass created list and number of pages for one page*/
		PageBean pb = new PageBean((ArrayList) arList, 10); 	
		int ch = 0;	
		while( true ) {
			
			System.out.println("Choose: [1]first, [2]next , [3]previous, [4]last, [5]end\n\n");
			ch = ConsoleReader.readInt();
			switch (ch) {
			case 1 : currentPage = pb.firstPage(); break;
			case 2 : currentPage = pb.next();      break;
			case 3 : currentPage = pb.previous();  break;
			case 4 : currentPage = pb.lastPage();  break;
			case 5 : System.exit(0);
			default : System.out.println("Invalid choise!");
			}
			
			/*prints the returned currentPage*/
			System.out.println("----------------------------- page "+pb.getCurrentPageNumber()+
					" of "+pb.amountPages()+" -------------------------------------");
			for (Iterator iter = currentPage.iterator(); iter.hasNext();) {
				String element = (String) iter.next();
				System.out.println(element);
			}
			System.out.println("---------------------------------------------------" +
					"----------------------------------");
		}
	}
}
