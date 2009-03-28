/**
 * 
 */
package com.cit.jcourse.svelikov.theme5.paging;

import java.util.ArrayList;

/**
 * Class that gets an ArrayList and provides some methods for
 * separating it to pages and iterating through these pages.
 *  
 * @author  Svilen Velikov
 * @version 02 30.03.07
 */
public class PageBean {
		
	/** Holds current page as list */
	private ArrayList currentList = new ArrayList();
	
	/** ArrayList to iterate over */
	private ArrayList linesList;
	
	/** Number of rows listed in one page */
	private int NUM_ROWS;
	
	/** Holds current page number */
	private int curPage = 0;
	
	/**
	 * Gets an ArrayList and an int as parameters and initialize 
	 * fields.
	 * @param linesList The ArrayList holding all rows.
	 * @param numRows   The number of rows that should have every page.
	 */
	public PageBean(ArrayList linesList, int numRows) {
		this.linesList = linesList;
		this.NUM_ROWS  = numRows;
	}
	
	/**
	 * Calculates how many pages we will have using number of
	 * lines in linesList as base.
	 * @return The iterable number of pages.
	 */
	public int amountPages() {
		int size = linesList.size();
		int totalPages;
		if ( (size % NUM_ROWS) == 0 )				/*it's equal to an int number*/
			totalPages = size / NUM_ROWS;			/*no need to round*/
		else										
			totalPages = (size / NUM_ROWS)+1;		/*must round up to next int*/
		return totalPages;
	}
	
	/**
	 * Calculates the offset that is start point for every page
	 * listed.
	 * @return The offset
	 */
	private int getOffset() {
		int offset = (curPage - 1)*NUM_ROWS;
		return offset;
	}
	
	/**
	 * Calculates the offset and stop index for next page that
	 * should be printed. Then return a list for that page.
	 * @return The list with rows for the next page.
	 */
	public ArrayList next() {
		int countRows;						/*will hold offset*/
		int endOfPage;						/*will hold stop index*/
		curPage++;							/*increment the current page first*/
		if (hasNext()) {					/*if current is not last page*/
			countRows = getOffset();			
			endOfPage = countRows + NUM_ROWS;	
		} else {							/*we are on the last page*/
			curPage = amountPages();	
			countRows = getOffset();
			endOfPage = linesList.size();	/*stop index will be at last line exactly*/
		}
		
		splitRows(countRows, endOfPage);
		return currentList;
	}
	
	/**
	 * Calculates the offset and stop index for the previous page that
	 * should be printed. Then return a list for that page.
	 * @return The list with rows for the previous page.
	 */
	public ArrayList previous() {
		int countRows;						/*will hold offset*/
		int endOfPage;						/*will hold stop index*/
		
		if (hasPrevious()) {				/*if we are not on the first page*/
			curPage--;						/*then decrement current page counter*/
			countRows = getOffset();
			endOfPage = countRows + NUM_ROWS;
		} else if ( linesList.size() < NUM_ROWS ) {
			curPage = 1;
			countRows = curPage;
			endOfPage = linesList.size();
			System.out.println("\nYou are curently on the first page!\n");
		} else {							/*we are on the first page, should print mesage*/
			curPage = 0;
			countRows = curPage;
			endOfPage = countRows + NUM_ROWS;
			System.out.println("\nYou are curently on the first page!\n");
			curPage++;
		}

		splitRows(countRows, endOfPage);
		return currentList; 
	}

	/**
	 * Calculates offset and stop index for the first page.
	 * @return The list with rows for the first page.
	 */
	public ArrayList firstPage() {
		curPage = 0;
		int countRows = curPage;
		int endOfPage;
		if ( hasNext() )
			endOfPage = countRows + NUM_ROWS;
		else
			endOfPage = linesList.size();
		
		curPage++;
		splitRows(countRows, endOfPage);
		return currentList;
	}
	
	/**
	 * Calculates offset and stop index for the last page.
	 * @return The list with rows for the last page.
	 */
	public ArrayList lastPage() {
		curPage = amountPages();
		int countRows = getOffset();
		int endOfPage = linesList.size();
		
		splitRows(countRows, endOfPage);
		return currentList;
	}
	
	/**
	 * Put the rows between countRows and endOfPage representing
	 * one page in different list.
	 * @param countRows The offset.
	 * @param endOfPage The end row.
	 * @return The list of rows for that page.
	 */
	private ArrayList splitRows(int countRows, int endOfPage) {
		currentList.clear();
		while ( countRows < endOfPage ) {
			currentList.add(linesList.get(countRows));
			countRows++;
		}
		return currentList;
	}
	
	/**
	 * Check whether have next page.
	 */
	private boolean hasNext() {	
		return curPage < amountPages();
	}
	
	/**
	 * Check whether have previous page.
	 */
	private boolean hasPrevious() {
		return curPage > 1;
	}
	
	/**
	 * @return The current page number
	 */
	public int getCurrentPageNumber() {
		return curPage;
	}

}
