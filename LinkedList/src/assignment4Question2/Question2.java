/* 
 * Name: Yaacoub Yaacoub
 * Date Last Modified: 31/3/2020
 * Project Description: Creating Doubly linked lists with some methods to manipulate them such as size, add, 
 * 						insert at a specific index, delete, get, exists... 
 * 						A Doubly linked list is has nodes each node is composed of an info field and a next field
 * 						which will be pointing the the next Node, a prev field which will be pointing the next Node.
 * 						In the main file, some behaviors were created such as:
 * 							Insert: Inserts an element at a specific index to  the doubly linked list.
 * 							Delete: Delete an element of the doubly linked list
 * 							Reverse: Return a reversed linked list.
 * 							Add: sum the elements of a doubly linked list of integers.
 * 							Split: Splits a doubly linked list into 2 doubly linked lists at a specific index.
 * Other Files: DNode.java, HeaderNode.java, DoublyLinkedList.java.
 */
package assignment4Question2;

import java.util.Scanner;

public class Question2 {
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

	public static DoublyLinkedList createList() {
		/* Create a Doubly Linked List and return it */
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
		DoublyLinkedList list = new DoublyLinkedList();
		for (int i = 0; i < size; i++) {
			System.out.print("Enter the element " + (i + 1) + " of the list: ");
			list.add(new Integer(scanInt()));
		}
		System.out.println("Linked List elements: " + list);
		System.out.println();
		return list;
	}

	public static void reverse(DoublyLinkedList list) {
		/* takes a Doubly linked list and reverse its elements */
		Object[] arr = list.toArray();
		list.setHeader(new HeaderNode());
		for (int i = (arr.length - 1); i >= 0; i--)
			list.add(arr[i]);
	}

	public static int sum(DoublyLinkedList list) {
		/*
		 * takes a Doubly Linked List of integers and return the sum of all the elements
		 */
		int sum = 0;
		Object o;
		Integer a;
		for (int i = 0; i < list.size(); i++) {
			o = list.get(i);
			a = (Integer) o;
			sum = sum + a.intValue();
		}
		return sum;
	}

	public static DoublyLinkedList subList(DoublyLinkedList list, int index_start, int index_end) {
		/*
		 * takes a Doubly Linked List and return a sublist Doubly Linked List from
		 * index_start to index_end - 1
		 */
		DoublyLinkedList l = new DoublyLinkedList();
		for (int i = index_start; i < index_end; i++) {
			l.add(list.get(i));
		}
		return l;
	}

	public static void menu() {
		DoublyLinkedList list = new DoublyLinkedList();
		boolean is_list = false;
		boolean running = true;
		while (running) {// stuck in this while loop the program is running and the user did not exit
			System.out.println("1. Make List");
			System.out.println("2. Insert");
			System.out.println("3. Delete");
			System.out.println("4. Reverse");
			System.out.println("5. Add");
			System.out.println("6. Split");
			System.out.println("7. Exit");
			System.out.println("-------------------");
			System.out.print("Enter your choice: ");

			int choice = 0;
			try {
				choice = Integer.parseInt(scan.nextLine());
				if (!is_list && choice != 1 && choice != 7) {
					System.out.println("Initially you should press 1 to create a list.");
					System.out.println("You could have exited if you pressed 7.");
					System.out.println("For now you should create the list:");
					System.out.println();
					choice = 1;
				}
				if (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6
						&& choice != 7) {
					System.out.println("Wrong input. Please try again.");
					System.out.println();
				}
			} catch (NumberFormatException e) {
				System.out.println("Wrong input. Please try again.");
				System.out.println();
			}

			switch (choice) {
			case 1:
				is_list = true;
				list = createList();
				System.out.println("List: " + list);
				System.out.println();
				break;

			case 2:
				System.out.print("You want to insert an object to the list, here is you current list: ");
				System.out.println(list);
				System.out.print("Enter the object you want to add (it should be an integer): ");
				Integer add = new Integer(scanInt());
				System.out.println();
				System.out.print("At what index you want to enter this integer: ");
				int index = scanInt();
				System.out.println();
				boolean is_added = list.insert(add, index);
				if (is_added) {
					System.out.println("Your object is added successfully.");
					System.out.println("New list: " + list);
				} else {
					System.out.println("Your object could not be added. (Possible problems: index out of range)");
					System.out.println("List: " + list);
				}
				System.out.println();
				break;

			case 3:
				System.out.print("You want to delete an object from the list, here is you current list: ");
				System.out.println(list);
				System.out.print("At what index of the object you want to delete: ");
				int index_to_delete = scanInt();
				System.out.println();
				if (list.delete(index_to_delete) != null) {
					System.out.println("Item deleted successfully.");
					System.out.println("New list: " + list);
				} else {
					System.out.println("Your object could not be deleted. (Possible problems: index out of range)");
					System.out.println("List: " + list);
				}
				System.out.println();
				break;

			case 4:
				System.out.print("You want to reverse the list: ");
				System.out.println(list);
				reverse(list);
				System.out.println("Here is your reversed list:" + list);
				System.out.println();
				break;

			case 5:
				System.out.print("You want to add all the elements in your linked list, here is you current list: ");
				System.out.println(list);
				System.out.println();
				System.out.println("Sum of the elements of the list: sum=" + sum(list));
				System.out.println();
				break;

			case 6:
				System.out.println("You want to split the Linked list into 2 linked lists, here is you current list: ");
				System.out.println(list);
				System.out.print("Please enter at what index you want to split it: ");
				int split_index = scanInt();
				if (split_index < 0 || split_index > list.size()) {
					System.out.println(
							"Your cannot split the list at this index. (Possible problems: index out of range)");
					System.out.println("List: " + list);
				} else {
					DoublyLinkedList l1 = subList(list, 0, split_index);
					DoublyLinkedList l2 = subList(list, split_index, list.size());
					System.out.println();
					System.out.println("Here is you two lists:");
					System.out.println("List 1: " + l1);
					System.out.println("List 2: " + l2);
					String[] pointers = { (l1.getHeader()).toString(), (l2.getHeader()).toString() };
					System.out.println("Pointer to linked list l1: " + pointers[0]);
					System.out.println("Pointer to linked list l2: " + pointers[1]);
				}
				System.out.println();
				break;
			case 7:
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