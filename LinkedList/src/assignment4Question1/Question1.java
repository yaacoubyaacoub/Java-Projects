/* 
 * Name: Yaacoub Yaacoub
 * Date Last Modified: 31/3/2020
 * Project Description: Creating linked lists with some methods to manipulate them such as size, add, 
 * 						insert at a specific index, delete, get, exists... 
 * 						A linked list is has nodes each node is composed of an info field and a next field
 * 						which will be pointing the next Node.
 * 						Doubly linked list is a linked list but each node is pointing to the previous one and 
 * 						the next one. Doubly linked lists have the same methods of the Linked list but the 
 * 						implementation changes to account for the prev field of each node.
 * 						In the main file, some behaviors were created such as:
 * 							Includes: check if elements of a linked list are present in another linked list.
 * 							Remove Duplicates: checks if a linked list have a same element more than once 
 * 												and removes them.
 * 							Reverse: Return a reversed linked list.
 * 							Return Odd: Returns elements at odd indices of a linked list.
 * 							Make Doubly Linked List: Create a doubly linked list having same elements of a 
 * 														given liked list.
 * Other Files: Node.java, LinkedList.java, DNode.java, DoublyLinkedList.java.
 */
package assignment4Question1;

import java.util.Arrays;
import java.util.Scanner;

public class Question1 {
	public static Scanner scan = new Scanner(System.in);

	public static int scanInt() {
		/* Scan an integer and return it */
		int integer = 0;
		boolean is_integer = true;
		do { // Stuck in this while loop until the user enters good input
			try {
				integer = Integer.parseInt(scan.nextLine());
				is_integer = false;
			} catch (NumberFormatException e) {
				System.out.print("Please enter an integer: ");
			}
		} while (is_integer);
		return integer;
	}

	public static LinkedList createList() {
		/* Create a Linked List and return it */
		System.out.print("Enter the size of the list: ");
		int size = 0;
		do {
			size = scanInt();
			if (size < 0) {
				System.out.println("The size should be a positive integer.");
				System.out.print("Enter the size of the list: ");
			}
		} while (size < 0);
		System.out.println();

		System.out.println("Now we want to fill this list.");
		LinkedList list = new LinkedList();
		for (int i = 0; i < size; i++) {
			System.out.print("Enter the element " + (i + 1) + " of the list: ");
			String to_add = scan.nextLine();
			try {
				list.add(Integer.parseInt(to_add));
			} catch (NumberFormatException e) {
				try {
					list.add(Double.parseDouble(to_add));
				} catch (NumberFormatException e2) {
					list.add(to_add);
				}
			}
		}
		System.out.println("Linked List elements: " + list);
		System.out.println();
		return list;
	}

	public static LinkedList initialList() {
		/* Create the initial Linked List */
		System.out.println("Hello! wlecome to the Linked List world.");
		System.out.println("We will first start by creating a linked list.");
		LinkedList initial_list = createList();
		return initial_list;
	}

	public static boolean includes(LinkedList initial_list, LinkedList list) {
		/*
		 * Checks if list is included in initial list and return true, else return false
		 */
		for (int i = 0; i < list.size(); i++) {
			if (!initial_list.exists(list.get(i)))
				return false;
		}
		return true;
	}

	public static LinkedList removeDuplicate(LinkedList list) {
		/*
		 * takes a linked list, checks if identical elements are present in this list
		 * and remove them
		 */
		LinkedList cloned_list = list.clone();
		Object o;
		String del = "";
		for (int i = 0; i < list.size(); i++) {
			o = list.get(i);
			for (int j = (i + 1); j < list.size(); j++) {
				if (o.equals(list.get(j)))
					del = del + j + " ";
			}
		}
		if (del.length() > 0) {
			String[] delete = del.split(" ");
			int[] to_del = new int[delete.length];
			for (int i = 0; i < delete.length; i++)
				to_del[i] = Integer.parseInt(delete[i]);
			Arrays.sort(to_del);

			del = "";
			for (int i = 0; i < to_del.length - 1; i++) {
				if (to_del[i] != to_del[i + 1])
					del = del + to_del[i] + " ";
			}
			del = del + to_del[to_del.length - 1];

			delete = del.split(" ");
			int[] to_delete = new int[delete.length];
			for (int i = 0; i < delete.length; i++)
				to_delete[i] = Integer.parseInt(delete[i]);

			for (int i = (to_delete.length - 1); i >= 0; i--) {
				cloned_list.delete(to_delete[i]);
			}
		}
		return cloned_list;
	}

	public static LinkedList reverse(LinkedList list) {
		/* takes a linked list and returns a linked list with elements reversed */
		Object[] arr = list.toArray();
		LinkedList new_list = new LinkedList();
		for (int i = (arr.length - 1); i >= 0; i--)
			new_list.add(arr[i]);
		return new_list;
	}

	public static LinkedList returnOdd(LinkedList list) {
		/*
		 * takes a linked list and return another linked list with the element at the
		 * off indices of the initial list
		 */
		LinkedList new_list = new LinkedList();
		for (int i = 0; i < list.size(); i++) {
			if (i % 2 != 0)
				new_list.add(list.get(i));
		}
		return new_list;
	}

	public static DoublyLinkedList toDoublyLinkedList(LinkedList list) {
		/*
		 * takes a linked list and return a doubly linked list with the same element of
		 * the initial list
		 */
		DoublyLinkedList new_list = new DoublyLinkedList();
		for (int i = 0; i < list.size(); i++)
			new_list.add(list.get(i));
		return new_list;
	}

	public static void menu() {
		LinkedList initial_list = initialList();
		boolean running = true;
		while (running) {// stuck in this while loop the program is running and the user did not exit
			System.out.println("1. Includes");
			System.out.println("2. Remove Duplicates");
			System.out.println("3. Reverse");
			System.out.println("4. Return Odd");
			System.out.println("5. Make Doubly Linked List");
			System.out.println("6. Exit");
			System.out.println("-------------------");
			System.out.print("Enter your choice: ");

			int choice = 0;
			try {
				choice = Integer.parseInt(scan.nextLine());
				if (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6) {
					System.out.print("Wrong input. \nThe program will terminate. \nSorry :(");
					running = false;
				}
			} catch (NumberFormatException e) {
				System.out.print("Wrong input. \nThe program will terminate. \nSorry :(");
				running = false;
			}

			switch (choice) {
			case 1:
				if (includes(initial_list, createList()))
					System.out.println("This list is included in the initial list.");
				else
					System.out.println("This list is not included in the initial list.");
				System.out.println();
				break;

			case 2:
				LinkedList l2 = removeDuplicate(createList());
				System.out.println("Linked List with duplicates removed: " + l2);
				System.out.println();
				break;

			case 3:
				LinkedList l3 = reverse(initial_list);
				System.out.println("Reverse list: " + l3);
				System.out.println();
				break;

			case 4:
				LinkedList l4 = returnOdd(initial_list);
				System.out.println("Elements of the odd indices of the Linked List: " + l4);
				System.out.println();
				break;

			case 5:
				DoublyLinkedList l5 = toDoublyLinkedList(initial_list);
				System.out.println("The Doubly Linked List: " + l5);
				System.out.println();
				break;

			case 6:
				System.out.println("Exiting the program...");
				running = false;
				break;
			}
		}
		scan.close();
	}

	public static void main(String[] args) {
		menu();
	}
}