package com.cit.jcourse.svelikov.theme1.arrays;

public class Arrays {
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	  {
		   //Random rand = new Random();
	       
	       int[] realArray;
	       realArray = new int[10];
	       
	       for ( int i = 0; i < realArray.length; i++ )
	         {
	    	   realArray[i] = (int) (Math.random() * 10); 
	    	   //realArray[i] = rand.nextInt()%10;
		     }
	       
	       ArrayStats arrSt = new ArrayStats();

	       arrSt.PrintArray( realArray );

		   System.out.println( "��� ������� ������� � ������ � : " + arrSt.GetMinElement(realArray));		   
		   System.out.println( "������ �� ���������� �� ������ � : " + arrSt.GetSum(realArray));		   		   

		   arrSt.CentWeight( realArray );
		   
		   arrSt.ReversArr( realArray );
		   
		   int left=0;
		   int right=realArray.length-1;
		   arrSt.QuickSort( realArray, left, right );
		   System.out.println("�������� ����� :");
	       arrSt.PrintArray( realArray );
	  }
}
