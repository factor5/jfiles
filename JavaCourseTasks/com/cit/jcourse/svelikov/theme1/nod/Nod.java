package com.cit.jcourse.svelikov.theme1.nod;

public class Nod {
	/**
	 * Calculates the Greatest Common Divisor(GCD) of two numbers
	 * @param args
	 */
	public static void main(String[] args) 
	 {
	   int val1 = (int) (Math.random() * 100);
	   int val2 = (int) (Math.random() * 100);
	   System.out.println( "����� 1: " + val1 + "  ����� 2: " + val2 );

	   Method nod1 = new Method(); 
	   
	   int value1 = nod1.NodA( val1, val2 );
       System.out.println( "��� �� ����� ��� ������� :" + value1 );

	   float valueA = nod1.NodE( val1, val2 );
	   System.out.println( "��� �� ������ �� ������ :" + valueA );
	   
	   float valueNok = nod1.Nok( val1, val2 );
	   System.out.println( "��� �� ����� ����� :" + valueNok );
	   
     }
}
