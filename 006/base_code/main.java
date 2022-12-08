import pkg.*;
import java.util.*;
import java.time.*;
import java.lang.*;

class main {
	public static void main(String args[]) {
		SinglyLinkedList taco = new SinglyLinkedList();
		for (int i=0; i<20; i++) {
			taco.insert(0, ((int)(Math.random()*10)));
		}
		taco.printList(); /* 
		for (int i=0; i<20; i++) {
			int rand = (int) (Math.random()*19);
			taco.insert(rand, -1);
		} */
		//taco.insert(0, 100);
		taco.printList();
		System.out.println();
		taco.swap(3, 4);
		/*int ind = 0;
		int ind2 = 40;
		while(true) {
			if (ind == ind2) {
				System.out.println("hi");
				break;
			} else {
				taco.swap(ind, ind2);
			}
			ind++;  ind2--;
		} */
		taco.printList();

		SinglyLinkedList reversed = new SinglyLinkedList();
		for (int i=20; i>0; i--) {
			reversed.insert(0, taco.get(i));
		}
		reversed.printList();
	}
}
