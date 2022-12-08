import pkg.*;
import java.util.*;
import java.time.*;
import java.lang.*;
import java.util.ArrayList;

class main {
	public static void main(String args[]) {
		/*
			Create an ArrayList of 100 Nodes
			Store random integers in each of them
			Print out all of the values
		*/
		SinglyLinkedList list = new SinglyLinkedList();
		for (int i=0; i<20; i++) {
			int num = (int) (Math.random*100);
			list.insert(0, num);
		}


	}
}
