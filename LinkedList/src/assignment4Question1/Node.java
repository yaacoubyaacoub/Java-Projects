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
 * Other Files: LinkedList.java, DNode.java, DoublyLinkedList.java, Question1.
 */
package assignment4Question1;

public class Node {
	private Object info;
	private Node next;

	public Node() {
		info = null;
		next = null;
	}

	public Node(Object info) {
		this.info = info;
		next = null;
	}

	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}
}
