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
 * Other Files: HeaderNode.java, DoublyLinkedList.java, Question2.
 */
package assignment4Question2;

public class DNode {
	private Object info;
	private DNode prev;
	private DNode next;

	public DNode() {
		info = null;
		prev = null;
		next = null;
	}

	public DNode(Object info) {
		this.info = info;
		prev = null;
		next = null;
	}

	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}

	public DNode getPrev() {
		return prev;
	}

	public void setPrev(DNode prev) {
		this.prev = prev;
	}

	public DNode getNext() {
		return next;
	}

	public void setNext(DNode next) {
		this.next = next;
	}
}
