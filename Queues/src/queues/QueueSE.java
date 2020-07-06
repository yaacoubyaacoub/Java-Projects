package queues;

public class QueueSE {
	/* add to the Start remove from the End */
	private Node header;

	public QueueSE() {
		header = null;
	}

	public Node getHeader() {
		return header;
	}

	public void setHeader(Node header) {
		this.header = header;
	}

	public void enqueue(Object o) {
		/* adds o to the start of the queue */
		Node node = new Node(o);
		if (header == null) // empty list
			header = node;
		else {
			node.setNext(header);
			header = node;
		}
	}

	public Object dequeue() {
		/*
		 * dequeue element (from the end of the list) and return it, return null if the
		 * queue is empty
		 */
		if (header == null) // empty list
			return null;
		else if (header.getNext() == null) { // queue with 1 element only
			Node current = header;
			header = header.getNext();
			return current.getInfo();
		} else {
			Node current = header;

			while ((current.getNext()).getNext() != null)
				current = current.getNext();
			Node temp = current.getNext();
			current.setNext(null);
			return temp.getInfo();
		}
	}

	public boolean empty() {
		/* return true if queue is empty, false if queue is not empty */
		if (header == null)
			return true;
		else
			return false;
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
			s = s + current.getInfo() + ".";
			return s;
		}
	}
}
