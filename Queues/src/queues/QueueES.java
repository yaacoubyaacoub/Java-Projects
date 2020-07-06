package queues;

public class QueueES {
	/* add to the End remove from the Start */
	private Node header;

	public QueueES() {
		header = null;
	}

	public Node getHeader() {
		return header;
	}

	public void setHeader(Node header) {
		this.header = header;
	}

	public void enqueue(Object o) {
		/* adds o to the end of the queue */
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

	public Object dequeue() {
		/*
		 * dequeue element (from the start of the list) and return it, return null if the
		 * queue is empty
		 */
		if (header == null) // empty list
			return null;
		else {
			Node current = header;
			header = current.getNext();
			current.setNext(null);
			return current.getInfo();
		}
	}

	public boolean empty() {
		/* return true if queue is empty, false if queue is not empty */
		if (header == null)
			return true;
		else
			return false;
	}
}
