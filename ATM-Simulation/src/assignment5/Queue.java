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
 * Other Files: Node.java, LinkedList.java, Account.java, Person,java, Transaction.java, ATM.java.
 */
package assignment5;

import java.util.Arrays;

public class Queue {
	Object[] queue;
	int enqueue_index = 0;
	int dequeue_index = 0;

	public Queue(int initial_size) {
		queue = new Object[initial_size];
	}

	public int getEnqueue_index() {
		return enqueue_index;
	}

	public void setEnqueue_index(int enqueue_index) {
		this.enqueue_index = enqueue_index;
	}

	public int getDequeue_index() {
		return dequeue_index;
	}

	public void setDequeue_index(int dequeue_index) {
		this.dequeue_index = dequeue_index;
	}

	public Object peek() {
		if (dequeue_index == queue.length)
			return queue[0];
		else
			return queue[dequeue_index];
	}

	public boolean isEmpty() {
		return peek() == null;
	}

	public String toString() {
		return Arrays.toString(queue);
	}

	public void enqueue(Object o) {
		if ((enqueue_index < queue.length)
				&& ((enqueue_index > dequeue_index) || ((enqueue_index == dequeue_index) && (peek() == null)))) {
			queue[enqueue_index] = o;
			enqueue_index++;
		} else if ((enqueue_index == queue.length) && (dequeue_index > 0)) {
			enqueue_index = 0;
			queue[enqueue_index] = o;
			enqueue_index++;
		} else if (dequeue_index > enqueue_index) {
			queue[enqueue_index] = o;
			enqueue_index++;
		} else {
			increaseSize((queue.length) * 2);
			queue[enqueue_index] = o;
			enqueue_index++;
		}
	}

	public Object dequeue() {
		if ((dequeue_index == queue.length) && (enqueue_index > 0)) {
			dequeue_index = 0;
			Object o = queue[dequeue_index];
			queue[dequeue_index] = null;
			dequeue_index++;
			return o;
		} else if (peek() != null) {
			Object o = queue[dequeue_index];
			queue[dequeue_index] = null;
			dequeue_index++;
			return o;
		} else
			return null;
	}

	public void increaseSize(int new_size) {
		Object[] temp_queue = new Object[new_size];
		int temp_index = 0;
		for (int i = enqueue_index; i < queue.length; i++) {
			temp_queue[temp_index] = queue[i];
			temp_index++;
		}
		for (int i = 0; i < enqueue_index; i++) {
			temp_queue[temp_index] = queue[i];
			temp_index++;
		}
		enqueue_index = temp_index;
		dequeue_index = 0;
		this.queue = temp_queue;
	}
}
