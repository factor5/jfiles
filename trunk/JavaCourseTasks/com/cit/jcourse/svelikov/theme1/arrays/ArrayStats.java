package com.cit.jcourse.svelikov.theme1.arrays;

public class ArrayStats {
	
	/**
	 * Task 2.2
	 * Finds least small element of an array
	 * @param arr
	 * @return  
	 */
	public int GetMinElement( int arr[] )
	{   
		int el = arr[0];
		for ( int i = 0; i < arr.length; i++ ) 
		{
			if ( arr[i] < el )
			{
				el = arr[i];
			}
		}
		return el;
	}
	
	/**
	 * Task 2.2
	 * Calculates sum of all elements of an array
	 * @param arr
	 * @return
	 */
	public int GetSum( int arr[] )
	{   
		int sum=0;
		for ( int i = 0; i < arr.length; i++ )
		{
			sum += arr[i];
		}
		return sum;
	}
	
	/**
	 * Task 2.2
	 * Displays all elements of the array 
	 * @param arr
	 */
	public void PrintArray( int arr[] )
	{
		for ( int i = 0; i < arr.length; i++ ) 
		{
			System.out.println( "Element " + i + ": " + arr[i] );
		}
	}
	
	/**
	 * Task 2.3
	 * Calculates center of weight of an array 
	 * @param arr
	 */
	public void CentWeight( int arr[] )
	{
		int i=0;
	    int sumEl = GetSum( arr );
		int halfSum = sumEl / 2;
		int sumBefore = arr[0];
		int sumAfter = 0;

	    while (( sumBefore < halfSum )&&( i < arr.length ))
	    {
			i++;
			sumBefore += arr[i];
		}
	    
	    System.out.println( "Центъра на тежестта на масива е " + i + "-ят елемент" );
	    System.out.println( "Сумата на елементите преди ц.на тежестта е:" + sumBefore );
	    
	    for ( int j = i+1; j < arr.length; j++ )
	    {
 			sumAfter += arr[j];
		}
	    System.out.println( "Сумата на елементите след ц.на тежестта е:" + sumAfter );
	}
	
	/**
	 * Reverses an array
	 * @param arr
	 */
	public void ReversArr( int arr[] )
	{   
		int decr;         //counts indexes from center to beginning    
		int incr;         //counts indexes from center to ending
		int temp;         //buffer variable

		if( arr.length % 2 != 1 )             /*Finds if arr.lenght is an even number */
		{
			/*If arr.lenght is an even number*/
			decr = ( arr.length / 2 ) - 1;
			incr = (arr.length / 2);
		}
		else
		{   /*If it is an odd number*/
			decr = ( arr.length / 2 ) - 1;
			incr = ( arr.length / 2 ) + 1;
		}
		
		for ( int i = decr; i >= 0; i-- )    /*Reversing from center to borders*/
		{
			temp      = arr[i];
			arr[i]    = arr[incr];
			arr[incr] = temp;
			incr++;
		}

		for ( int i = 0; i < arr.length; i++ ) /*Displays reversed array*/ 
		{
			System.out.println( "Element " + i + ": " + arr[i] );
		}
	}
	
	/**
	 * Quicksort
	 * @param arr is an populated array
	 * @param left
	 * @param right
	 */
	public void QuickSort(  int arr[], int left, int right )
	{      
		   int center;    //the middle of the array
		   int temp;      //temp var needed for exchange
		   int i;         //counter
		   int j;         //counter
		   i = left;
		   j = right;
		   center = arr[( left + right ) / 2];
		       
		   do    /*do-while loop : exchange the elements*/
		     {
		        while( arr[i] < center ) i++;   //smaller elements goes to the left
		        while( arr[j] > center ) j--;   //biger elements goes to the right
		        if( i <= j )
		           {
		        	   temp   = arr[i];
		               arr[i] = arr[j];
		               arr[j] = temp;
	                   i++;
	                   j--;  
                   }
	         }
		   while( i <= j );   //while meet in the middle
		       
		   if( left < j )  QuickSort ( arr, left, j );   //recurs evocation
           if( i < right ) QuickSort ( arr, i, right );  //recurs evocation
           
	}	
}
