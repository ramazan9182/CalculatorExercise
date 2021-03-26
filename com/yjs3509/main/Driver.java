package com.yjs3509.main;


import com.yjs3509.core.Calculator;
import com.yjs3509.core.exceptions.IllegalExpressionException;

/**
 * 
 * 
 * #(#4#+#5#)#*#67#/#89#-#90#
 *
 */
public class Driver {

	
	public static void main(String[] args) {
		
			Calculator calculator = new Calculator();
		
			try {
				double result = calculator.calculateExpression("(a+b)");
				System.out.println(result);
			} catch (IllegalExpressionException e) {
				e.printStackTrace();
				
			}
		
	}
	
}
