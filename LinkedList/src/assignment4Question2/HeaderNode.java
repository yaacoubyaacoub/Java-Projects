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
 * Other Files: DNode.java, DoublyLinkedList.java, Question2.
 */
package assignment4Question2;

public class HeaderNode {
	private int count;
	private DNode first;
	private DNode last;

	public HeaderNode() {
		count = 0;
		first = null;
		last = null;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public DNode getFirst() {
		return first;
	}

	public void setFirst(DNode first) {
		this.first = first;
	}

	public DNode getLast() {
		return last;
	}

	public void setLast(DNode last) {
		this.last = last;
	}

	public String toString() {
		if (count == 0)
			return "HeaderNode [count=" + 0 + ", first->DNode info(null), last->DNode info(null)]";
		else
			return "HeaderNode [count=" + count + ", first->DNode info(" + first.getInfo() + "), last->DNode info("
					+ last.getInfo() + ")]";
	}

}
