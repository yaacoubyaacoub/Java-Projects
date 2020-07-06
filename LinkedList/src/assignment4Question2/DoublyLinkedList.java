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
 * Other Files: DNode.java, HeaderNode.java, Question2.
 */
package assignment4Question2;

public class DoublyLinkedList {
	private HeaderNode header;

	public DoublyLinkedList() {
		header = new HeaderNode();
	}

	public HeaderNode getHeader() {
		return header;
	}

	public void setHeader(HeaderNode header) {
		this.header = header;
	}

	public int size() {
		/* return the number of DNodes in the list */
		return header.getCount();
	}

	public void add(Object o) {
		/* add Object o to the end of the list */
		DNode node = new DNode(o);
		if (header.getFirst() == null) {
			header.setFirst(node);
			header.setLast(node);
			header.setCount(header.getCount() + 1);
		} else {
			DNode current = header.getLast();
			current.setNext(node);
			node.setPrev(current);
			header.setLast(node);
			header.setCount(header.getCount() + 1);
		}
	}

	public boolean insert(Object o, int i) {
		/* inserts o at index i, return true if i is valid and false otherwise */
		DNode node = new DNode(o);
		if ((i < 0) || (i > size()))
			return false;
		else if (size() == 0) {
			header.setFirst(node);
			header.setLast(node);
			header.setCount(header.getCount() + 1);
			return true;
		} else if (i == 0) {
			header.getFirst().setPrev(node);
			node.setNext(header.getFirst());
			header.setFirst(node);
			header.setCount(header.getCount() + 1);
			return true;
		} else if (i == size()) {
			header.getLast().setNext(node);
			node.setPrev(header.getLast());
			header.setLast(node);
			header.setCount(header.getCount() + 1);
			return true;
		} else {
			DNode current = header.getFirst();
			for (int j = 0; j < i - 1; j++)
				current = current.getNext();
			node.setNext(current.getNext());
			node.setPrev(current);
			current.setNext(node);
			current.getNext().getNext().setPrev(node);
			header.setCount(header.getCount() + 1);
			return true;
		}
	}

	public DNode delete(int i) {
		/*
		 * deletes node at index i, returns a pointer to the deleted node, null if i is
		 * an invalid index 
		 */
		if ((header.getFirst() == null) || (i < 0) || (i > size() - 1))
			return null;
		else {
			DNode current = header.getFirst();
			if (i == 0) {
				if (size() == 1) {
					header.setFirst(null);
					header.setLast(null);
					header.setCount(header.getCount() - 1);
					return current;
				} else {
					header.setFirst(current.getNext());
					current.setNext(null);
					header.setCount(header.getCount() - 1);
					return current;
				}
			} else {
				for (int j = 0; j < i; j++)
					current = current.getNext();
				current.getPrev().setNext(current.getNext());
				if (current.getNext() != null) {
					current.getNext().setPrev(current.getPrev());
					current.setNext(null);
				} else if (current.getNext() == null)
					header.setLast(current.getPrev());
				current.setPrev(null);
				header.setCount(header.getCount() - 1);
				return current;
			}
		}
	}

	public Object get(int i) {
		/* return Object at index i, null if i is an invalid index */
		if ((i < 0) || (i > size() - 1))
			return null;
		else {
			DNode current = header.getFirst();
			for (int j = 0; j < i; j++)
				current = current.getNext();
			return current.getInfo();
		}
	}
	
	public Object[] toArray() {
		/* transform the Linked List to an array */
		if (header.getFirst() == null) {
			Object[] arr = {};
			return arr;
		} else {
			DNode current = header.getFirst();
			Object[] arr = new Object[size()];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = current.getInfo();
				current = current.getNext();
			}
			return arr;
		}
	}

	public String display() {
		/* Displays info, location, previous and next */
		if (header.getFirst() == null) {
			return "Empty list";
		} else {
			DNode current = header.getFirst();
			String[] dis = new String[size()];
			if (size() == 1) {
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
			String display = "Length = " + header.getCount() + ", First(" + header.getFirst().hashCode() + ") last("
					+ header.getLast().hashCode() + "):\n";
			for (int j = 0; j < dis.length; j++) {
				display = display + "Node " + j + ": " + dis[j] + "\n";
			}
			return display;
		}
	}

	public String toString() {
		if (header.getFirst() == null)
			return "Empty List";
		else {
			String s = "";
			DNode current = header.getFirst();
			while (current.getNext() != null) {
				s = s + current.getInfo() + ", ";
				current = current.getNext();
			}
			s = s + current.getInfo();
			return "[" + s + "]";
		}
	}
}
