package com.yjs3509.core;

public enum Operators {

	ADDITION('+'),SUBTRACTION('-'),MULTIPLICATION('*'),DIVISION('/'),OPEN_BRACKET('('),CLOSE_BRACKET(')');
	
	private char operatorSign; 
	
	private Operators(char operatorSign) {
		this.operatorSign = operatorSign;
	}
	
	public char getOperatorSign() {
		return operatorSign;
	}
}
