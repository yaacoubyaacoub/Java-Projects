package queues;

public class MainQueue {

	public static void main(String[] args) {
		QueueSE q = new QueueSE();
		
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		System.out.println(q);
		System.out.println(q.dequeue());
		System.out.println(q);

	}

}
