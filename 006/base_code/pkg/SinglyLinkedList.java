package pkg;

import java.util.Scanner;
import java.util.Random;

public class SinglyLinkedList {
	public Node head;

	/*
	 * Postcondition: The head will be null
	 */
	public SinglyLinkedList() {
		head = null;
	}

	/*
	 * Receives an integer position, searches through the SinglyLinkedList for the
	 * position and returns the data at that positon
	 * If the position doesn't exist, it returns -1
	 */
	public int get(int pos) {
		Node temp;
		temp = head;
		int count = 0;
		while(count < pos) {
			if (temp.getNext() == null) {
				return -1;
			}
			temp = temp.getNext();
			count++;
		}
		return temp.getData();
	}

	/*
	 * Insert a new Node at the given position with the data given
	 */
	public void insert(int pos, int data){
		System.out.println(pos + " " + data);
        Node temp;
        Node insert = new Node(data);
		if (pos == 0) {
			insert.setNext(head);
		}
        if(head == null){
            head = insert;
            return;
        }
        temp = head;
        int count = 0;
        while(count<pos-3){
            // if(data == 100){
            //     System.out.println(count + "   " + temp.getNext().getData());
            // }
            if(temp.getNext() == null){
                temp.setNext(insert);
                return;
            }
            temp = temp.getNext();
            count++;
        }

        Node getNext = temp.getNext();
        temp.setNext(insert);
        insert.setNext(getNext);
    }
	/*public void insert(int pos, int data){
		
		/*Node temp;
        Node insert = new Node(data);
        if(head == null){
            head = insert;
            return;
        }
        temp = head;
        int count = 0;
			while(count < pos-5) {
				if (temp.getNext() == null) {
					System.out.println("unreasonable position. Check insert function calls.");
					break;
				}
				temp = temp.getNext();
				count++;
			}
			
			Node newNode = temp.getNext();
			//Node insertNode = new Node(data);
			temp.setNext(insert);
			insert.setNext(newNode);
	}*/

	/*
	 * Remove the node at the given position
	 * If no position exists, don't change the list
	 */
	public void remove(int pos) {
		Node temp;
		temp = head.getNext();
		int count = 0;
		while(count < pos) {
			if (temp.getNext() == null) {
				return;
			}
			temp = temp.getNext();
			count++;
		}
		temp.setNext(temp.getNext().getNext());
	}

	/*
	 * Swap two Nodes with the two positions given
	 */
	public void swap(int pos1, int pos2) {
		Node temp1;
		Node temp5;
		temp1 = head;
		temp5 = temp1;
		int count1 = 0;
		while(count1 < pos1) {
			System.out.println(temp1.getData());
			if (temp1.getNext() == null) {
				return;
			}
			temp1 = temp1.getNext();
			count1++;
			if (count1 <pos1){
				temp5 = temp1;
			}
		}
		Node temp6;
		System.out.println();
		Node temp2; temp2=head; int count2=0; 
		temp6 = temp2;
		while(count2<pos2){
			System.out.println(temp2.getData());
			if (temp2.getNext() == null) {
				return;
			}
			temp2 = temp2.getNext();
			count2++;
		}

		Node temp3 = temp1.getNext();
		Node temp4 = temp2.getNext();
		//temp5.setNext(temp1);
		//temp6.setNext(temp2);
		System.out.println(temp1.getData()+" t1");
		System.out.println(temp2.getData()+" t2");
		System.out.println(temp3.getData()+" t3");
		System.out.println(temp4.getData()+" t4");
		temp1.setNext(temp4);
		temp2.setNext(temp3);
	}

	/*
	 * Print all data values in the LinkedList
	 */
	public void printList() {
		System.out.println("-------------------------------------------------");
		Node temp = head;
		while(temp.getNext() != null) {
			System.out.print(temp.getData() + " ");
			temp = temp.getNext();
		}
	}

}
