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
 * Other Files: Queue.java, Node.java, LinkedList.java, Account,java, Transaction.java, ATM.java.
 */
package assignment5;

public class Person {
	
	private String name;
	private int clientNumber;
	private LinkedList accounts;
	
	public Person(String name, int clientNumber, LinkedList accounts) {
		this.name = name;
		this.clientNumber = clientNumber;
		this.accounts = accounts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(int clientNumber) {
		this.clientNumber = clientNumber;
	}

	public LinkedList getAccounts() {
		return accounts;
	}

	public void setAccounts(LinkedList accounts) {
		this.accounts = accounts;
	}
}
