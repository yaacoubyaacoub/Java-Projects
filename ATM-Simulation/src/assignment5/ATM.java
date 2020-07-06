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
 * Other Files: Queue.java, Node.java, LinkedList.java, Account,java, Person.java, Transaction.java.
 */
package assignment5;

import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class ATM {
	public static Scanner scan = new Scanner(System.in);
	public static String[] ids;
	public static String[] passwords;
	public static Person[] persons;
	public static Queue transactionQueue = new Queue(5);

	public static void TimerSetup() {
		/*
		 * Timer interrupt handler, every 10 second this peace of code (the run() method
		 * will be checked if specific conditions are met a transaction waiting in the
		 * transaction queue will be processed, else a transaction if it exists, it will
		 * be ignored.
		 */
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				Date d = new Date();
				SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
				String day = dayFormat.format(d);
				SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
				int hour = Integer.parseInt(hourFormat.format(d));
				if ((!day.equals("Sunday")) && ((hour > 18) && (hour >= 8))) {
					if (transactionQueue.peek() != null) {
						processTransaction((Transaction) transactionQueue.dequeue());
						savePersonsAndInfo("AccountsInformations.txt");
					}
				}
			}
		};
		timer.scheduleAtFixedRate(task, 10000, 10000);
	}

	public static int scanInt() {
		/* Scan an integer and return it */
		int integer = 0;
		boolean is_integer = true;
		do { // Stuck in this while loop until the user enters good input
			try {
				integer = Integer.parseInt(scan.nextLine());
				if (integer > 0) {
					is_integer = false;
				} else
					System.out.print("please enter a positive integer: ");
			} catch (NumberFormatException e) {
				System.out.print("Please enter an integer: ");
			}
		} while (is_integer);
		return integer;
	}

	public static String scanType() {
		String type = "";
		do {
			type = scan.nextLine();
			if (!(type.equalsIgnoreCase("savings") || type.equalsIgnoreCase("checking")))
				System.out.println("You must enter a valid type (savings/checking).");
		} while (!(type.equalsIgnoreCase("savings") || type.equalsIgnoreCase("checking")));
		return type;
	}

	public static boolean getIDsAndPasswords(String fileName) throws IOException {
		/*
		 * format of the file:
		 * ID, Password
		 * note: on each line a new Id and password, the line number matters
		 */
		boolean toReturn = true;
		FileReader fr = new FileReader(new File(fileName));
		BufferedReader br = new BufferedReader(fr);
		String id = "";
		String pass = "";
		String line = null;
		String[] ln;
		int lineNb = 1;
		while ((line = br.readLine()) != null) {
			ln = line.split(", ");
			if (ln.length == 2) {
				id = id + ln[0] + " ";
				pass = pass + ln[1] + " ";
			} else {
				System.out.println("An error occur in the IDsAndPasswords file at line " + lineNb
						+ " Please check it, fix it and restart the system.");
				id = id + 0 + " ";
				pass = pass + 0 + " ";
				toReturn = false;
			}
			lineNb++;
		}
		br.close();
		ids = id.split(" ");
		passwords = pass.split(" ");
		return toReturn;
	}

	public static boolean getPersonsAndInfos(String fileName) throws IOException {
		/*
		 * Format of the file:
		 * name/clientNumber/accountNumber accountType accountBalance,accountNumber accountType accountBalance ... 
		 * line number matters, each line will correspond to the same person of the same line in IDsAndPassword file
		 */
		boolean toReturn = true;
		FileReader fr = new FileReader(new File(fileName));
		BufferedReader br = new BufferedReader(fr);
		persons = new Person[ids.length];
		int personIndex = 0;
		String line = null;
		String[] ln;
		while ((line = br.readLine()) != null) {
			if (personIndex >= persons.length) {
				System.out.println(
						"An error occur, the PersonsAndInfos file and IDsAndPasswords file don't have the same length , Please check it, fix it and restart the system.");
				toReturn = false;
			} else {
				ln = line.split("/");
				int accInfo_length = 0;
				LinkedList accounts = new LinkedList();
				if (ln.length == 3) {
					String[] accs = ln[2].split(",");
					for (int i = 0; i < accs.length; i++) {
						String[] accInfo = accs[i].split(" ");
						accInfo_length = accInfo.length;
						if (accInfo_length == 3)
							accounts.add(new Account(Integer.parseInt(accInfo[0]), accInfo[1],
									Float.parseFloat(accInfo[2])));
						else
							break;
					}
				}
				if ((ln.length == 3) && (accInfo_length == 3))
					persons[personIndex] = new Person(ln[0], Integer.parseInt(ln[1]), accounts);
				else {
					persons[personIndex] = null;
					System.out.println("An error occur  in the PersonsAndInfos file at line " + (personIndex + 1)
							+ " Please check it, fix it than restart the system.");
					toReturn = false;
				}
				personIndex++;
			}
		}
		br.close();
		return toReturn;
	}

	public static void addNewBankAccount() {
		String password = null;
		System.out.print("Enter your new ID: ");
		String id = scan.nextLine();
		System.out.println();
		int idIndex = searchArray(ids, id);
		if (idIndex != -1) {
			System.out.println("This ID already exists");
		} else {
			boolean passwordMatched = false;
			do {
				System.out.print("Enter your new Password: ");
				password = scan.nextLine();
				System.out.println();
				System.out.print("Confirm Password: ");
				String passwordConfirmation = scan.nextLine();
				System.out.println();
				if (!(password.equals(passwordConfirmation))) {
					System.out.println("Password does not match.");
					System.out.println("Please try again.");
				} else {
					System.out.println("This ID has been created.");
					passwordMatched = true;
				}
			} while (!passwordMatched);

			System.out.println("Creating your account.");
			System.out.print("Your name: ");
			String name = scan.nextLine();
			System.out.println();
			System.out.print("Client Number: ");
			int clientNumber = scanInt();
			System.out.println();

			System.out.print("How many accounts? ");
			int nbOfAccounts = scanInt();
			LinkedList acc = new LinkedList();
			for (int i = 0; i < nbOfAccounts; i++) {
				System.out.print("Account Number: ");
				int accountNumber = scanInt();
				System.out.println();
				System.out.print("Account type: ");
				String accountType = scanType();
				System.out.println();
				System.out.print("Account balance: ");
				int accountbalance = scanInt();
				System.out.println();
				acc.add(new Account(accountNumber, accountType, (float) accountbalance));
			}
			Person p = new Person(name, clientNumber, acc);
			String[] tempID = new String[ids.length + 1];
			String[] tempPasswords = new String[passwords.length + 1];
			Person[] tempPersons = new Person[persons.length + 1];
			for (int i = 0; i < ids.length; i++) {
				tempID[i] = ids[i];
				tempPasswords[i] = passwords[i];
				tempPersons[i] = persons[i];
			}
			tempID[tempID.length - 1] = id;
			tempPasswords[tempPasswords.length - 1] = password;
			tempPersons[tempPersons.length - 1] = p;
			ids = tempID;
			passwords = tempPasswords;
			persons = tempPersons;
		}
	}

	public static void saveIDandPass(String fileName_IDandPass) {
		/*
		 * Save the updated IDs and password in the IDsAndPasswords file
		 */
		try {
			FileWriter fw = new FileWriter(new File(fileName_IDandPass));
			BufferedWriter bw = new BufferedWriter(fw);
			String line = "";
			for (int i = 0; i < ids.length; i++) {
				line = ids[i] + ", " + passwords[i];
				bw.write(line + "\n");
			}
			bw.close();
		} catch (IOException e) {
			System.out.println("Files not found");
		}
	}

	public static void savePersonsAndInfo(String fileName_PersonsAndInfos) {
		/*
		 * Save the updated IDs and password in the AccountsInformations file
		 */
		try {
			FileWriter fw = new FileWriter(new File(fileName_PersonsAndInfos));
			BufferedWriter bw = new BufferedWriter(fw);
			String line = "";
			for (int i = 0; i < ids.length; i++) {
				line = persons[i].getName() + "/" + persons[i].getClientNumber() + "/";
				LinkedList acc = persons[i].getAccounts();
				for (int j = 0; j < acc.size(); j++) {
					line = line + (acc.get(j)).getAccountNumber() + " " + (acc.get(j)).getAccountType() + " "
							+ (acc.get(j)).getAccountBalance() + ",";
				}
				bw.write(line + "\n");
			}
			bw.close();
		} catch (IOException e) {
			System.out.println("Files not found");
		}
	}

	public static int searchArray(Object[] arr, String toFind) {
		if (arr[0] != null) {
			for (int i = 0; i < arr.length; i++) {
				if (((String) arr[i]).equals(toFind))
					return i;
			}
		}
		return -1;
	}

	public static Object[] getTransaction(int idIndex) {
		Person p = persons[idIndex];
		int nbOfAcc = (p.getAccounts()).size();
		Transaction t = null;
		String flag = null;
		System.out.println("Please enter you choice:");
		System.out.println("1. Withdraw money");
		System.out.println("2. Deposit money");
		System.out.println("3. Transfer of Money");
		System.out.println("4. Display account info");
		System.out.println("5. Exit");
		System.out.println("-------------------");
		System.out.print("Enter your choice: ");

		int choice = 0;
		do {
			try {
				choice = Integer.parseInt(scan.nextLine());
				if (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5) {
					System.out.println("Wrong input. Please try again.");
					System.out.println();
				}
			} catch (NumberFormatException e) {
				System.out.println("Wrong input. Please try again.");
				System.out.println();
			}
		} while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5);

		switch (choice) {
		case 1:
			System.out.println("You want to withdraw money from your account.");
			int accNb = 0;
			if (nbOfAcc > 1) {
				do {
					System.out.print("From which of your " + nbOfAcc + " accounts you want to withdraw money? ");
					accNb = scanInt();
					if (accNb > nbOfAcc) {
						System.out.println("You only have " + nbOfAcc + " accounts in our bank.");
						System.out.println("You can create more accounts if you want.");
					}
				} while (accNb > nbOfAcc);
				accNb = accNb - 1;
			}
			float accBalance = ((p.getAccounts()).get(accNb)).getAccountBalance();
			System.out.println("In this account ou have " + accBalance + "$");
			float money = 0;
			do {
				System.out.print("Enter the amount of money you want to withdraw: ");
				money = scanInt();
				System.out.println();
				if (money > accBalance) {
					System.out.println("Warning! This amount of money is not available in your account.");
					System.out.println("Please try again.");
				}
			} while (money > accBalance);

			t = new Transaction(p, "debit", money, accNb);
			System.out.println();
			break;

		case 2:
			System.out.println("You want to deposit money from your account.");
			int accNb1 = 0;
			if (nbOfAcc > 1) {
				do {
					System.out.print("Which of your " + nbOfAcc + " accounts you want to deposit money to? ");
					accNb1 = scanInt();
					if (accNb1 > nbOfAcc) {
						System.out.println("You only have " + nbOfAcc + " accounts in our bank.");
						System.out.println("You can create more accounts if you want.");
					}
				} while (accNb1 > nbOfAcc);
				accNb1 = accNb1 - 1;
			}
			float accBalance1 = ((p.getAccounts()).get(accNb1)).getAccountBalance();
			System.out.println("In this account ou have " + accBalance1 + "$");
			System.out.print("Enter the amount of money you want to deposit: ");
			float money1 = scanInt();

			t = new Transaction(p, "credit", money1, accNb1);
			System.out.println();
			break;

		case 3:
			System.out.println("You want to transfer money from your account.");
			int accNb2 = 0;
			int accNb3 = 0;
			if (nbOfAcc == 1) {
				System.out.println("But you only have one account you cannot transfer money.");
				System.out.println();
			} else {
				do {
					System.out.print("From which of your " + nbOfAcc + " accounts you want to transfer the money? ");
					accNb2 = scanInt();
					if (accNb2 > nbOfAcc) {
						System.out.println("You only have " + nbOfAcc + " accounts in our bank.");
						System.out.println("You can create more accounts if you want.");
					}
				} while (accNb2 > nbOfAcc);
				accNb2 = accNb2 - 1;

				if (nbOfAcc == 2) {
					if (accNb2 == 0)
						accNb3 = 1;
					else
						accNb3 = 0;
				} else {
					do {
						System.out.print("to which of your " + nbOfAcc + " accounts you want to transfer the money? ");
						accNb3 = scanInt();
						if (accNb3 > nbOfAcc) {
							System.out.println("You only have " + nbOfAcc + " accounts in our bank.");
							System.out.println("You can create more accounts if you want.");
						} else if ((accNb3 - 1) == accNb2) {
							System.out.println("You have the money in the same account, no need to transfer");
							System.out.println("please try again.");
						}
					} while ((accNb3 > nbOfAcc) || ((accNb3 - 1) == accNb2));
					accNb3 = accNb3 - 1;
				}

				float accBalance2 = ((p.getAccounts()).get(accNb2)).getAccountBalance();
				System.out.println("In this account ou have " + accBalance2 + "$");
				float money2 = 0;
				do {
					System.out.print("Enter the amount of money you want to transfer: ");
					money2 = scanInt();
					if (money2 > accBalance2) {
						System.out.println("Warning! This amount of money is not available in your account.");
						System.out.println("Please try again.");
					}
				} while (money2 > accBalance2);
				t = new Transaction(p, "transfer", money2, accNb2, accNb3);
			}
			System.out.println();
			break;

		case 4:
			System.out.println("The information about all your accounts will be shown.");
			for (int i = 0; i < nbOfAcc; i++) {
				float accBal = ((p.getAccounts()).get(i)).getAccountBalance();
				int accNbr = ((p.getAccounts()).get(i)).getAccountNumber();
				System.out.println(
						"Account " + (i + 1) + " --> Account number: " + accNbr + ", Balance: " + accBal + "$.");
			}
			System.out.println();
			break;

		case 5:
			t = null;
			flag = "exit";
			System.out.println("You turn on the machine has ended.");
			System.out.println();
			break;
		}
		Object[] toReturn = { t, flag };
		return toReturn;
	}

	public static void processTransaction(Transaction t) {
		/*
		 * to process the transactions
		 */
		String type = t.getType();
		Person p = t.getPerson();
		Account fromAccount = (p.getAccounts()).get(t.getAccountNumber_from());
		float amountOfmoney = t.getAmountOfMoney();
		if (type.equalsIgnoreCase("debit")) {
			fromAccount.setAccountBalance(fromAccount.getAccountBalance() - amountOfmoney);
		} else if (type.equalsIgnoreCase("credit")) {
			fromAccount.setAccountBalance(fromAccount.getAccountBalance() + amountOfmoney);
		} else if (type.equalsIgnoreCase("transfer")) {
			Account toAccount = (p.getAccounts()).get(t.getAccountNumber_to());
			fromAccount.setAccountBalance(fromAccount.getAccountBalance() - amountOfmoney);
			toAccount.setAccountBalance(toAccount.getAccountBalance() + amountOfmoney);
		}
	}

	public static void atm() {
		boolean running = true;
		while (running) {// stuck in this while loop the program is running and the user did not exit
			System.out.println(
					"Welcome to our virtual bank, here you can deposit and withdraw as much as you want, no limit on your transactions.");
			System.out.println("You can also use any currency you want. :-)");
			System.out.println();
			System.out.print("Enter your ID: ");
			String id = scan.nextLine();
			int index_id = searchArray(ids, id);
			if (index_id == -1) {
				System.out.println("This ID does not exist.");
				System.out.println();
				continue;
			} else {
				System.out.print("Enter your Password: ");
				String pass = scan.nextLine();
				System.out.println();
				if (!(passwords[index_id].equals(pass))) {
					System.out.println("Wrong password, does not match your ID.");
					System.out.println();
					continue;
				} else {
					String name = persons[index_id].getName();
					System.out.println("Welcome " + name + " !");
					System.out.println();
					Queue tempTransactions = new Queue(2);
					Object[] obj;
					Transaction t;
					String flag;
					do {
						obj = getTransaction(index_id);
						t = (Transaction) obj[0];
						flag = (String) obj[1];
						if ((t == null) && (flag == null))
							continue;
						else if ((t != null) && (flag == null)) {
							transactionQueue.enqueue(t);
							tempTransactions.enqueue(t);
						} else if ((t == null) && (flag.equals("exit"))) {
							Date d = new Date();
							SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
							String day = dayFormat.format(d);
							SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
							int hour = Integer.parseInt(hourFormat.format(d));
							if ((day.equals("Sunday")) || ((hour >= 18) || (hour < 8))) {
								if (!tempTransactions.isEmpty()) {
									System.out.println("Here are your transactions.");
									while (!tempTransactions.isEmpty()) {
										System.out.println((Transaction) tempTransactions.dequeue());
									}
									System.out.println("They will be processed the next working day.");
									System.out.println("Thank you for using our bank.");
								} else {
									System.out.println("You did not make any transaction.");
									System.out.println("Thank you for using our bank.");
								}
							} else {
								if (tempTransactions.isEmpty()) {
									System.out.println("You did not make any transaction.");
									System.out.println("Thank you for using our bank.");
								} else {
									System.out.println("Your transactions will be processed Immediately.");
									System.out.println("Please be patient since some people came before you.");
									System.out.println("Thank you for your patience.");
								}
							}
							System.out.println();
						}
					} while ((t != null) || (flag == null));
				}
			}
		}
	}

	public static void mainCode() {
		try {
			boolean checkFile1 = getIDsAndPasswords("IDsAndPasswords.txt");
			boolean checkFile2 = getPersonsAndInfos("AccountsInformations.txt");
			if (checkFile1 && checkFile2) {
				TimerSetup();
				atm();
			} else {
				System.out.println("System crashed!");
				System.out.println("Some errors occurs in your database files, Please fix it and restart the system.");
			}
		} catch (IOException e) {
			System.out.println("System crashed");
			System.out.println("Your data files were not found, please check your database files.");
		}
	}

	public static void main(String[] args) {
		mainCode();
	}
}
