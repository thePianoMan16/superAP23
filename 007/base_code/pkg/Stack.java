package pkg;
import java.util.Scanner;
import java.util.Random;


public class Stack {
	/*  LAST IN FIRST OUT  */
	Node top;

	/* 
		Postcondition: The top will be null.
	*/
	public Stack() {
		top = null;
	}

	/*
		Insert a new Node on top of the stack
	*/
	public void push(char data){
		Node x = new Node(data);
		x.setNext(top);
		top = x;
	}

	/*
		Removes the top node of the stack
	*/
	public char pop(){
		Node temp = new Node(top.getData());
		top = top.getNext();
		return temp.getData();
	}

	/*
		Returns the top value of the stack. Doesn't pop. 
	*/
	public char peek(){
		return top.getData();
	}

	/*
		Checks if the stack is empty. 
	*/
	public boolean isEmpty(){
		if (top == null) {
			return true;
		}
		return false;
	}
}
