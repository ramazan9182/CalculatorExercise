package com.yjs3509.core;


import com.yjs3509.core.exceptions.IllegalExpressionException;

class CalculatorExpression {
	
	private static final String DEFAULT_EXPRESSION_DELIMITER = "#";
	private String[] expressionTokens;
	private OperandStack<Double> operandStack;
	private OperatorStack<Character> operatorStack;
	
	CalculatorExpression(String rawExpression) throws IllegalExpressionException{
		this.operandStack = new OperandStack<Double>();
		this.operatorStack = new OperatorStack<Character>();
		expressionTokens = convertToExpression(rawExpression).split(DEFAULT_EXPRESSION_DELIMITER);
		
		// TODO if numeric return the numeric result
		if(expressionTokens.length == 1) throw new IllegalExpressionException("No expression item found !");
	}
	
	
	public double execute() {
		for(String token : expressionTokens) {
			if(token.length() == 0) {
				// TODO : return result
				continue;
			
			}else if(isOperatorSubtractionOrAdditon(token.charAt(0))) {
				while(!operatorStack.isEmpty() && (isOperatorSubtractionOrAdditon(operatorStack.peek()) 
						|| isOperatorMultiplicationOrDivision(operatorStack.peek())) ) {
					executeOperator();
				}
				operatorStack.push(token.charAt(0));
			}else if(isOperatorMultiplicationOrDivision(token.charAt(0))) {
				while(!operatorStack.isEmpty() && isOperatorMultiplicationOrDivision(operatorStack.peek())) {
					executeOperator();
				}
				operatorStack.push(token.charAt(0));
			}else if(token.trim().charAt(0) == Operators.OPEN_BRACKET.getOperatorSign()){
				operatorStack.push(token.charAt(0));
			}else if(token.trim().charAt(0) == Operators.CLOSE_BRACKET.getOperatorSign()){
					while(operatorStack.peek() != Operators.OPEN_BRACKET.getOperatorSign()) {
						executeOperator();
					}
				operatorStack.pop(); // clear the open bracket from stack
			}else {
				operandStack.push(new Double(token));

			}
		} // END OF FOR-EACH
		
		// clear the all stack !
		while (!operatorStack.isEmpty()) {
			executeOperator();
		}
		
		return operandStack.pop();
	}
	
	
	private void executeOperator() {
		char operator = operatorStack.pop();
		double operand1 = operandStack.pop();
		double operand2 = operandStack.pop();
		if(operator == Operators.ADDITION.getOperatorSign()) {
			operandStack.push(add(operand1, operand2));
		}else if(operator == Operators.SUBTRACTION.getOperatorSign()) {
			operandStack.push(subtract(operand2, operand1));
		}else if(operator == Operators.MULTIPLICATION.getOperatorSign()) {
			operandStack.push(multiply(operand1, operand2));
		}else if(operator == Operators.DIVISION.getOperatorSign()) {
			operandStack.push(divide(operand2, operand1));
		}
	}
	
	private boolean isOperatorSubtractionOrAdditon(char operator) {
		return operator == Operators.ADDITION.getOperatorSign() || operator == Operators.SUBTRACTION.getOperatorSign();
		
	}
	
	private boolean isOperatorMultiplicationOrDivision(char operator) {
		return operator == Operators.MULTIPLICATION.getOperatorSign() || operator == Operators.DIVISION.getOperatorSign();
	}
	
	private String convertToExpression(String rawExpression) {
		StringBuilder expressionBuilder = new StringBuilder("");
		for(int i = 0; i < rawExpression.length(); i++) {
			if(isLegalOperator(rawExpression.charAt(i))) {
				expressionBuilder.append(DEFAULT_EXPRESSION_DELIMITER).append(rawExpression.charAt(i)).append(DEFAULT_EXPRESSION_DELIMITER);
			}else {
				// TODO check if legal number
				expressionBuilder.append(rawExpression.charAt(i));
			}
		}
		return expressionBuilder.toString();
	}
	
	
	private boolean isLegalOperator(char operatorItem) {
		return operatorItem == Operators.ADDITION.getOperatorSign() 
				|| operatorItem == Operators.SUBTRACTION.getOperatorSign() 
				|| operatorItem == Operators.MULTIPLICATION.getOperatorSign()
				|| operatorItem == Operators.DIVISION.getOperatorSign() 
				|| operatorItem == Operators.OPEN_BRACKET.getOperatorSign() 
				|| operatorItem == Operators.CLOSE_BRACKET.getOperatorSign();
	}

	
	public double subtract(double operand1, double operand2) {
		return operand1 - operand2;
	}
	
	public double add(double operand1, double operand2) {
		return operand1 + operand2;
	}
	
	public double multiply(double operand1, double operand2) {
		return operand1 * operand2;
	}
	
	public double divide(double operand1, double operand2) {
		// TODO check runtime exceptions
		return operand1 / operand2;
	}
	
}
