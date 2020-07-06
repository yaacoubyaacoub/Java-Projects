/* 
 * Name: Yaacoub Yaacoub
 * Date Last Modified: 9/3/2020
 * Project Description: Four methods are present in this file:
 * 						First method: equalSum will return true if a given array could be cut into 2 arrays with 
 * 						equal element sum. This method uses 2 recursive methods elementsSum and isEqualSum.
 *  					Second method: removeDuplicate takes a String and return the same string but one of 2 
 *  					consecutive duplicate letters will be removed.
 *  					Third method: fibonacci takes an int n and return the nth fibonacci sequence element,
 *  					fibonacci sequence is at an element n is the sum of the previous 2 elements.
 *  					Another version of this method is also available called fibonacciSequence which will return 
 *  					the sequence until element n given for the function as parameter.
 *  					Forth method: sumsNotAdjacent takes an array of int and an integer and return true if we can 
 *  					find a number of elements inside the array with a sum equals to the given int, but the elements 
 *  					should not be adjacent. This method uses 2 recursive methods sum and checkSum.
 * Other Files: none
 */
package assignment3_2;

import java.util.Arrays;
import java.util.Scanner;

public class Assignment3_2 {

	private static int elementsSum(int[] intArray, int index_start, int index_stop) {
		/*
		 * return the sum of the elements of intArray from index index_start to index
		 * index_stop
		 */
		if (index_start == index_stop)
			return intArray[index_start];
		else
			return intArray[index_start] + elementsSum(intArray, index_start + 1, index_stop);
	}

	private static boolean isEqualSum(int[] intArray, int index_start, int index_stop) {
		/*
		 * checks if 2 sums are equals return true it 2 equal sums are found return
		 * false if not
		 */
		if (elementsSum(intArray, index_start, index_stop) == elementsSum(intArray, index_stop + 1,
				intArray.length - 1))
			return true;
		else {
			if (index_stop + 1 != intArray.length - 1)
				return isEqualSum(intArray, index_start, index_stop + 1);
			else
				return false;
		}
	}

	public static boolean equalSum(int[] intArray) {
		/*
		 * To initialize the equalSum method return true it 2 equal sums are found
		 * return false if not
		 */
		if (intArray.length <= 1)
			return false;
		else
			return isEqualSum(intArray, 0, 0);
	}

	public static String removeDuplicate(String s) {
		/*
		 * takes a String and return the same string but one of 2 consecutive duplicate
		 * letters will be removed.
		 */
		if (s.length() > 1) {
			if (s.charAt(0) == s.charAt(1))
				return removeDuplicate(s.substring(1));
			else
				return s.substring(0, 1) + removeDuplicate(s.substring(1));
		} else
			return s;
	}

	public static long fibonacci(int n) {
		/*
		 * takes an int n and return the nth fibonacci sequence element
		 */
		if (n == 0)
			return 0;
		else if (n == 1)
			return 1;
		else
			return fibonacci(n - 1) + fibonacci(n - 2);
	}

	public static void fibonacciSequence(int numberOfTerms) {
		/*
		 * takes the number of terms of the fibonacci sequence and return the sequence
		 * until element numberOfTerms's term
		 */
		if (numberOfTerms >= 0) {
			long[] fibonacci_sequence = new long[numberOfTerms + 1];
			System.out.print("[");
			for (int i = 0; i <= numberOfTerms; i++) {
				fibonacci_sequence[i] = fibonacci(i);
				if (i == numberOfTerms)
					System.out.print(fibonacci_sequence[i]);
				else {
					if ((i != 0) && (i % 10 == 0))
						System.out.print(fibonacci_sequence[i] + ",\n ");
					else
						System.out.print(fibonacci_sequence[i] + ", ");
				}
			}
			System.out.println("]");
		} else
			System.out.println("Number of terms should be a positive integer.");
	}

	private static boolean sum(int[] intArray, int[] index, int result) {
		/*
		 * sums non adjacent elements of the intArray until one of the is equal to result
		 * return true if a sum equal to result is found
		 * return false if not
		 */
		int this_sum = 0;
		for (int i = 0; i < index.length; i++) {
			if ((i < (index.length - 1))
					&& ((((int) Math.abs(index[i] - index[i + 1])) == 1) || (index[i] == index[i + 1]))) {
				this_sum = result - 1;
				break;
			} else
				this_sum = this_sum + intArray[index[i]];
		}
		if (this_sum == result)
			return true;
		else if ((index[0] == (intArray.length - 1 - ((index.length - 1) * 2)))
				&& (index[index.length - 1] == (intArray.length - 1)))
			return false;
		else {
			for (int i = (index.length - 1); i >= 0; i--) {
				if (index[i] < (intArray.length - 1 - (((index.length - 1) - i) * 2))) {
					index[i]++;
					break;
				} else
					index[i] = (i * 2) + 1;
			}
			return sum(intArray, index, result);
		}
	}

	private static boolean checkSum(int[] intArray, int result, int terms) {
		/*
		 * checks if a sum of some non adjacent terms of the intArray array is equal to result
		 * return true if a sum equal to result is found
		 * return false if not
		 * 
		 */
		int[] index = new int[terms];
		for (int i = 0; i < index.length; i++)
			index[i] = i * 2;
		if (sum(intArray, index, result))
			return true;
		else if (terms > 1)
			return checkSum(intArray, result, terms - 1);
		else
			return false;
	}

	public static boolean sumsNotAdjacent(int[] intArray, int result) {
		/*
		 * initialize the sum of non adjacent methods
		 * return true if a sum equal to result is found
		 * return false if not 
		 */
		int terms = (intArray.length + 1) / 2;
		return checkSum(intArray, result, terms);
	}

	public static void menu() {
		Scanner scan = new Scanner(System.in);
		boolean running = true;
		while (running) {// stuck in this while loop the program is running and the user did not exit
			System.out.println("1. Equal Sum");
			System.out.println("2. Remove Duplicates");
			System.out.println("3. Fibonacci");
			System.out.println("4. Sums but not adjacent");
			System.out.println("5. Exit");
			System.out.println("-------------------");
			System.out.print("Enter your choice: ");

			int choice = 0;
			do {
				try {
					choice = Integer.parseInt(scan.nextLine());
					if (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5) {
						System.out.print("Enter a number between 1 and 5 only: ");
					}
				} catch (NumberFormatException e) {
					System.out.print("You should input a number between 1 and 5: ");
				}
			} while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5);

			System.out.println();
			switch (choice) {

			case 1:
				int length = 0;
				boolean isLength = false;
				do {
					try {
						System.out.print("Enter the length of the array of int you want to check its equal sum: ");
						length = Integer.parseInt(scan.nextLine());
						if (length <= 0) {
							System.out.println("The number cannot be negative please try again.");
						} else
							isLength = true;
					} catch (NumberFormatException e) {
						System.out.println("This is an unvalid number. Please try again.");
					}
				} while (!isLength);

				int[] intArray = new int[length];
				for (int i = 0; i < length; i++) {
					int element = 0;
					boolean isElement = false;
					do {
						try {
							System.out.print("Enter the element of the array: ");
							element = Integer.parseInt(scan.nextLine());
							isElement = true;
						} catch (NumberFormatException e) {
							System.out.println("This is an unvalid number. Please try again.");
						}
					} while (!isElement);
					intArray[i] = element;
				}
				System.out.println("Array values: " + Arrays.toString(intArray));
				System.out.println();
				System.out.print("An equal sum of the array " + Arrays.toString(intArray) + " ");
				if (equalSum(intArray))
					System.out.println("exists.");
				else
					System.out.println("does not exist.");
				System.out.println();
				break;

			case 2:
				System.out.print("Enter the string that you want to delete duplicates letters: ");
				String duplicate = scan.nextLine();
				System.out.println("Stirng without duplicate letters: " + removeDuplicate(duplicate));
				System.out.println();
				break;

			case 3:
				int number = 0;
				boolean isNumber = false;
				do {
					try {
						System.out.print("Enter Fibonacci nth number: ");
						number = Integer.parseInt(scan.nextLine());
						System.out.println();
						if (number < 0) {
							System.out.println("The number cannot be negative please try again.");
						} else
							isNumber = true;
					} catch (NumberFormatException e) {
						System.out.println("This is an unvalid number. Please try again.");
					}
				} while (!isNumber);

				String fibChoice = "";
				do {
					System.out.println("To view the whole series until the " + number + "th term, press \"1\".");
					System.out.println("To view only the " + number + "th term press \"2\".");
					System.out.print("Enter your choice \"1\" or \"2\": ");
					fibChoice = scan.nextLine();
					System.out.println();
					if (!fibChoice.equals("1") && !fibChoice.equals("2"))
						System.out.println("Wrong input. Please try again.");
				} while (!fibChoice.equals("1") && !fibChoice.equals("2"));

				if (fibChoice.equals("1")) {
					System.out.print("Fibonacci series until the " + number + "th term is: ");
					fibonacciSequence(number);
				} else if (fibChoice.equals("2"))
					System.out.println("the " + number + "th fibonacci term is: " + fibonacci(number));
				System.out.println();
				break;

			case 4:

				int len = 0;
				boolean isLen = false;
				do {
					try {
						System.out.print("Enter the length of the array of int you want to check its equal sum: ");
						len = Integer.parseInt(scan.nextLine());
						if (len <= 0) {
							System.out.println("The number cannot be negative please try again.");
						} else
							isLen = true;
					} catch (NumberFormatException e) {
						System.out.println("This is an unvalid number. Please try again.");
					}
				} while (!isLen);

				int[] intArr = new int[len];
				for (int i = 0; i < len; i++) {
					int element = 0;
					boolean isElement = false;
					do {
						try {
							System.out.print("Enter the element of the array: ");
							element = Integer.parseInt(scan.nextLine());
							isElement = true;
						} catch (NumberFormatException e) {
							System.out.println("This is an unvalid number. Please try again.");
						}
					} while (!isElement);
					intArr[i] = element;
				}
				System.out.println("Array values: " + Arrays.toString(intArr));

				int result = 0;
				boolean isResult = false;
				do {
					try {
						System.out.print("Enter the result your checking there exist a sum equals to this number: ");
						result = Integer.parseInt(scan.nextLine());
						isResult = true;
					} catch (NumberFormatException e) {
						System.out.println("This is an unvalid number. Please try again.");
					}
				} while (!isResult);

				System.out.println();
				System.out.print("A sum equals to " + result + " ");
				if (sumsNotAdjacent(intArr, result))
					System.out.print("exists ");
				else
					System.out.print("does not exist ");
				System.out.println("in " + Arrays.toString(intArr) + ".");
				System.out.println();
				break;

			case 5:
				String exit = "";
				System.out.println("Do you want you really to exit?");
				do {
					exit = "";
					System.out.print("Yes (y)/No (n): ");
					exit = scan.nextLine();
				} while (!exit.equalsIgnoreCase("y") && !exit.equalsIgnoreCase("n"));
				System.out.println();
				if (exit.equalsIgnoreCase("y"))
					running = false;
				break;
			}
		}
	}

	public static void main(String[] args) {
		menu();
	}
}