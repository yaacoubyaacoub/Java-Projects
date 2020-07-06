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
 * Other Files: Queue.java, Node.java, LinkedList.java, Account,java, Person.java, ATM.java.
 */
package assignment5;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
	private Person person;
	private String date;
	private String time;
	private String type;
	private float amountOfMoney;
	private int accountNumber_from;
	private int accountNumber_to;

	public static String[] DateAndTime() {
		Date d = new Date();
		SimpleDateFormat dateFromat = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
		String date = dateFromat.format(d);
		String time = timeFormat.format(d);
		String[] DateTime = { date, time };
		return DateTime;
	}

	public Transaction(Person person, String type, float amountOfMoney) {
		this.person = person;
		String[] DateTime = DateAndTime();
		this.date = DateTime[0];
		this.time = DateTime[1];
		this.type = type;
		this.amountOfMoney = amountOfMoney;
		this.accountNumber_from = 0;
		this.accountNumber_to = -1;
	}

	public Transaction(Person person, String type, float amountOfMoney, int accountNumber_from) {
		this.person = person;
		String[] DateTime = DateAndTime();
		this.date = DateTime[0];
		this.time = DateTime[1];
		this.type = type;
		this.amountOfMoney = amountOfMoney;
		this.accountNumber_from = accountNumber_from;
		this.accountNumber_to = -1;
	}

	public Transaction(Person person, String type, float amountOfMoney, int accountNumber_from, int accountNumber_to) {
		this.person = person;
		String[] DateTime = DateAndTime();
		this.date = DateTime[0];
		this.time = DateTime[1];
		this.type = type;
		this.amountOfMoney = amountOfMoney;
		this.accountNumber_from = accountNumber_from;
		this.accountNumber_to = accountNumber_to;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getAmountOfMoney() {
		return amountOfMoney;
	}

	public void setAmountOfMoney(float amountOfMoney) {
		this.amountOfMoney = amountOfMoney;
	}

	public int getAccountNumber_from() {
		return accountNumber_from;
	}

	public void setAccountNumber_from(int accountNumber_from) {
		this.accountNumber_from = accountNumber_from;
	}

	public int getAccountNumber_to() {
		return accountNumber_to;
	}

	public void setAccountNumber_to(int accountNumber_to) {
		this.accountNumber_to = accountNumber_to;
	}

	public String toString() {
		if (accountNumber_to == -1)
			return "Transaction [person=" + person.getName() + ", date=" + date + ", time=" + time + ", type=" + type
					+ ", amount of money=" + amountOfMoney + ", From account Number=" + (accountNumber_from + 1) + "]";
		else
			return "Transaction [person=" + person.getName() + ", date=" + date + ", time=" + time + ", type=" + type
					+ ", amount of money=" + amountOfMoney + ", From account number=" + (accountNumber_from + 1)
					+ ", To account number=" + (accountNumber_to + 1) + "]";
	}

}
