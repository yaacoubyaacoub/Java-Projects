package queues;

public class Node {
	private Object info;
	private Node next;

	public Node() {
		info = 0;
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
