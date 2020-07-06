/* 
 * Name: Yaacoub Yaacoub
 * Date Last Modified: 28/4/2020
 * Project Description: Simulating a ATM machine, in this machine a client can withdraw, deposit or transfer money.
 * 						These transaction will be stored in a queue and processed on a first in first out basis
 * 						Transactions are only processed if we are at the working time (from 8am to 6pm) all weekdays
 * 						plus Saturday but Sundays are off. So every transaction made outside the working hours will
 * 						wait in the queue and processed the next working day, in a first in first out basis.
 * 						In this code we will be using a timer interrupt, so every 10 seconds the queue will
 * 						be checked, if a transaction exist in the queue and this we are inside the working 
 * 						hours this transaction will be processed, else the transaction will be ignored.
 * Other Files: Queue.java, Node.java, Account.java, Person,java, Transaction.java, ATM.java.
 */
package assignment5;

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

	public void add(Account o) {
		/* adds o to the end of the list */
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
		/*
		 * deletes node at index i, return a pointer to the deleted node, null if i is
		 * an invalid index
		 */
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

	public boolean insert(Account o, int i) {
		/* inserts o at index i, return true if i is valid and false otherwise */
		Node node = new Node(o);
		if ((i < 0) || (i > size()))
			return false;
		else {
			Node current = header;
			if (i == 0) {
				node.setNext(header);
				header = node;
				return true;
			} else {
				for (int j = 0; j < i - 1; j++)
					current = current.getNext();
				node.setNext(current.getNext());
				current.setNext(node);
				return true;
			}
		}
	}

	public Account get(int i) {
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

	public boolean exists(Account o) {
		/*
		 * checks id an object in present in the list and return true, else return false
		 */
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

	public LinkedList clone() {
		/* Displays info, location and next */
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
