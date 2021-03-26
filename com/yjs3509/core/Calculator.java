package com.yjs3509.core;

import com.yjs3509.core.exceptions.IllegalExpressionException;

public class Calculator {

	public double calculateExpression(String expression) throws IllegalExpressionException {
		return new CalculatorExpression(expression).execute();
	}
	
	// TODO ...
	
	
}
