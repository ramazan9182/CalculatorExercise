package com.yjs3509.core;

import java.util.LinkedList;

public abstract class AbstractCalculationStack<T> {
	
	private LinkedList<T> calculationElement = new LinkedList<T>();
	
	public boolean isEmpty() {
		return calculationElement.isEmpty();
	}
	
	public void push(T item) {
		calculationElement.addFirst(item);
	}
	
	public T peek() {
		return calculationElement.getFirst();
	}
	
	public T pop() {
		return calculationElement.removeFirst();
	}
	
	public int size() {
		return calculationElement.size();
	}
	

}
