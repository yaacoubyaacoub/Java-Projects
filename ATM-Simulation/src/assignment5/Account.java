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
 * Other Files: Queue.java, Node.java, LinkedList.java, Person,java, Transaction.java, ATM.java.
 */
package assignment5;

public class Account {

	private int accountNumber;
	private String accountType;
	private float accountBalance;

	public Account(int accountNumber, String accountType, float accountBalance) {
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public float getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(float accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", accountType=" + accountType + ", accountBalance="
				+ accountBalance + "]";
	}
}
