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
 * Other Files: Node.java, LinkedList.java, DNode.java, Question1.
 */
package assignment4Question1;

public class DoublyLinkedList {
	private DNode header;

	public DoublyLinkedList() {
		header = null;
	}

	public DNode getHeader() {
		return header;
	}

	public void setHeader(DNode header) {
		this.header = header;
	}

	public int size() {
		/* return the number of DNodes in the list */
		if (header == null)
			return 0;
		else {
			int count = 1;
			DNode current = header;
			while (current.getNext() != null) {
				count++;
				current = current.getNext();
			}
			return count;
		}
	}

	public void add(Object o) {
		/* add i to the end of the list */
		DNode node = new DNode(o);
		if (header == null)
			header = node;
		else {
			DNode current = header;
			while (current.getNext() != null)
				current = current.getNext();
			current.setNext(node);
			node.setPrev(current);
		}
	}

	public DNode delete(int i) {
		/*
		 * deletes node at index i, returns a pointer to the deleted node, null if i is
		 * an invalid index
		 */
		if ((header == null) || (i < 0) || (i > size() - 1))
			return null;
		else {
			DNode current = header;
			if (i == 0) {
				if (size() == 1) {
					header = null;
					return current;
				} else {
					header = header.getNext();
					current.setNext(null);
					header.setPrev(null);
					return current;
				}
			} else {
				for (int j = 0; j < i; j++)
					current = current.getNext();
				DNode pointer = current;
				current.getPrev().setNext(current.getNext());
				if (current.getNext() != null) {
					current.getNext().setPrev(current.getPrev());
					pointer.setNext(null);
				}
				pointer.setPrev(null);
				return pointer;
			}
		}
	}

	public String display() {
		/* Displays info, location, previous and next */
		if (header == null) {
			return "Empty list";
		} else {
			DNode current = header;
			String[] dis = new String[size()];
			if (size() == 0) {
				dis[0] = "Info@(" + current.hashCode() + ") [" + current.getInfo() + "], Next[null], Prev[null]";
			} else {
				dis[0] = "Info@(" + current.hashCode() + ") [" + current.getInfo() + "], Next["
						+ current.getNext().hashCode() + "], Prev[null]";
				current = current.getNext();
				int i;
				for (i = 1; i < size() - 1; i++) {
					dis[i] = "Info@(" + current.hashCode() + ") [" + current.getInfo() + "], Next["
							+ current.getNext().hashCode() + "], Prev[" + current.getPrev().hashCode() + "]";

					current = current.getNext();
				}
				dis[i] = "Info@(" + current.hashCode() + ") [" + current.getInfo() + "], Next[null], Prev["
						+ current.getPrev().hashCode() + "]";
			}
			String display = "";
			for (int j = 0; j < dis.length; j++) {
				display = display + "Node " + j + ": " + dis[j] + "\n";
			}
			return display;
		}
	}

	public String toString() {
		if (header == null)
			return "Empty List";
		else {
			String s = "";
			DNode current = header;
			while (current.getNext() != null) {
				s = s + current.getInfo() + ", ";
				current = current.getNext();
			}
			s = s + current.getInfo();
			return "[" + s + "]";
		}
	}
}
