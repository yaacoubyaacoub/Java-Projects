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
 * Other Files: Node.java, DNode.java, DoublyLinkedList.java, Question1.
 */
package assignment4Question1;

public class LinkedList {
	private Node header;

	public LinkedList() {
		header = null;
	}

	public Node getHeader() {
		return header;
	}

	public void setHeader(Node header) {
		this.header = header;
	}

	public int size() {
		/* return number of Nodes in the list */
		if (header == null)
			return 0;
		else {
			Node current = header;
			int count = 1;
			while (current.getNext() != null) {
				count++;
				current = current.getNext();
			}
			return count;
		}
	}

	public void add(Object o) {
		/* adds o to the end to the end of the list */
		Node node = new Node(o);
		Node current = header;
		if (header == null) // empty list
			header = node;
		else {
			while (current.getNext() != null)
				current = current.getNext();
			current.setNext(node);
		}
	}

	public Node delete(int i) {
		/*deletes node at index i, return a pointer to the deleted node, null if i is an invalid index */
		if ((i < 0) || (i > (size() - 1)))
			return null;
		else {
			Node current = header;
			if (i == 0) {
				header = header.getNext();
				current.setNext(null);
				return current;
			} else {
				for (int j = 0; j < i - 1; j++)
					current = current.getNext();
				Node temp = current.getNext();
				current.setNext(current.getNext().getNext());
				temp.setNext(null);
				return temp;
			}
		}
	}

	public Object get(int i) {
		/* return Object at index i, null if i is an invalid index */
		if ((i < 0) || (i > size() - 1))
			return null;
		else {
			Node current = header;
			for (int j = 0; j < i; j++)
				current = current.getNext();
			return current.getInfo();
		}
	}

	public boolean exists(Object o) {
		/* checks if an object in present in the list and return true, else return false */
		if (header == null)
			return false;
		else {
			Node current = header;
			boolean exist = false;
			do {
				if (current.getInfo().equals(o))
					exist = true;
			} while ((current = current.getNext()) != null);
			return exist;
		}
	}

	public Object[] toArray() {
		/* transform the Linked List to an array */
		if (header == null) {
			Object[] arr = {};
			return arr;
		} else {
			Node current = header;
			Object[] arr = new Object[size()];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = current.getInfo();
				current = current.getNext();
			}
			return arr;
		}
	}

	public LinkedList clone() {
		/* copy a Linked List to another Linked List */
		LinkedList new_list = new LinkedList();
		if (header == null)
			return new_list;
		else {
			Node current = header;
			do {
				new_list.add(current.getInfo());
			} while ((current = current.getNext()) != null);
			return new_list;
		}
	}

	public String display() {
		/* Displays info, location and next */ 
		if (header == null) {
			return "Empty list";
		} else {
			Node current = header;
			String[] dis = new String[size()];
			int i;
			for (i = 0; i < size() - 1; i++) {
				dis[i] = "Info@(" + current.hashCode() + ") [" + current.getInfo() + "], Next["
						+ current.getNext().hashCode() + "]";

				current = current.getNext();
			}
			dis[i] = "Info@(" + current.hashCode() + ") [" + current.getInfo() + "], Next[null]";
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
			Node current = header;
			while (current.getNext() != null) {
				s = s + current.getInfo() + ", ";
				current = current.getNext();
			}
			s = s + current.getInfo();
			return "[" + s + "]";
		}
	}
}
