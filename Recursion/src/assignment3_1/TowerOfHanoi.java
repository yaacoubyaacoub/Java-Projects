/* 
 * Name: Yaacoub Yaacoub
 * Date Last Modified: 9/3/2020
 * Project Description: Tower of Hanoi, Move all disks from the leftmost rod to the rightmost rode. Only one disk may
 * 						be moved at a time and it is not possible to place a bigger disk on the top of a smaller disk.
 * 						Two version of the problem present in this file; the iterative version and the recursive version. 
 * Other Files: none.
 */
package assignment3_1;

import java.util.Scanner;

public class TowerOfHanoi {
	public static long moves;

	// Recursive Tower of Hanoi version
	private static void moveOneDisk(int from, int to) {
		System.out.println(++moves + " ==> Move Disk From \"tower " + from + "\" To \"tower " + to + "\".");
	}

	private static void moveTower(int n, int s, int m, int e) {
		if (n == 1) {
			moveOneDisk(s, e);
		} else {
			moveTower((n - 1), s, e, m);
			moveOneDisk(s, e);
			moveTower((n - 1), m, s, e);
		}
	}

	public static double recursiveTowerOfHanoi(int n) {
		moves = 0;
		long start = System.nanoTime();
		moveTower(n, 1, 2, 3);
		long end = System.nanoTime();
		return (end - start) / 1000000.0;
	}

	// Iterative Tower of Hanoi version
	private static int diskNumber(int current_move) {
		int disk_number = 0;
		int x = current_move + 1;
		while (x % 2 == 0) {
			x = x / 2;
			disk_number++;
		}
		return disk_number + 1; // 1=>Disk 1; 2=> Disk 2; 3=> Disk 3; ...
	}

	private static int stepsToDestination(long total_disks, int disk_number) {
		/*
		 * 1: move a disk 1 tower the the right. 
		 * 2: move a disk 2 towers the the right.
		 * always move to the right, if no towers left to the right continue counting
		 * from the first tower
		 */
		if (total_disks % 2 == 0 && disk_number % 2 == 0) // total_disks even and disk_number even
			return 2;
		else if (total_disks % 2 == 0 && disk_number % 2 != 0) // total_disks even and disk_number odd
			return 1;
		else if (total_disks % 2 != 0 && disk_number % 2 == 0) // total_disks odd and disk_number even
			return 1;
		else // total_disks % 2 != 0 && disk_number % 2 != 0   //total_disks odd and disk_number odd
			return 2;
	}

	private static int diskNumberOfMoves(int current_move, int disk_number) {
		/*
		 * returns the number of moves made by a specific disk until the current move.
		 */
		current_move = (current_move / ((int) Math.pow(2, (disk_number - 1))));
		return (current_move + 1) / 2;

	}

	private static void moveDisk(int move, int disk, int source, int destination) {
		System.out.println(
				move + " ==> Move Disk " + disk + " From \"tower " + source + "\" To \"tower " + destination + "\".");
	}

	public static double iterativeTowerOfHanoi(int n) {
		long total_moves = (long) (Math.pow(2, n) - 1);
		long start = System.nanoTime();
		for (int i = 0; i < total_moves; i++) {
			int disk_number = diskNumber(i); // 1=>Disk 1; 2=> Disk 2; 3=> Disk 3; ...
			int source = (diskNumberOfMoves(i, disk_number) * stepsToDestination(n, disk_number)) % 3; // 0=>Tower 1;
																										// 1=> Tower 2;
																										// 2=> Tower 3.
			int next_destination = (source + stepsToDestination(n, disk_number)) % 3; // 0=>Tower 1; 1=> Tower 2; 2=>
																						// Tower 3.
			moveDisk(i + 1, disk_number, source + 1, next_destination + 1);
		}
		long end = System.nanoTime();
		return (end - start) / 1000000.0;
	}

	public static void menu() {
		Scanner scan = new Scanner(System.in);
		boolean running = true;
		while (running) {// stuck in this while loop the program is running and the user did not exit
			String iterative_recursive = "";
			System.out.println("Do you want to use the iterative or the recursive version of \"Tower of Hanoi\"?");
			do {
				iterative_recursive = "";
				System.out.print("Iterative (I)/Recursive (R): ");
				iterative_recursive = scan.nextLine();
			} while (!iterative_recursive.equalsIgnoreCase("I") && !iterative_recursive.equalsIgnoreCase("R"));
			System.out.println();

			int numberOfDisks = 0;
			boolean isNumberOfDisks = false;
			do {
				try {
					System.out.print("Enter the number of disks: ");
					numberOfDisks = Integer.parseInt(scan.nextLine());
					if (numberOfDisks <= 0) {
						System.out.println("The number of disks cannot be negative nor zero. Please try again.");
					} else
						isNumberOfDisks = true;
				} catch (NumberFormatException e) {
					System.out.println("This is an unvalid number. Please try again.");
				}
			} while (!isNumberOfDisks);

			if (iterative_recursive.equalsIgnoreCase("I")) {
				double time = iterativeTowerOfHanoi(numberOfDisks);
				System.out.println("For n=" + numberOfDisks + " iteratively, done in: " + time + " milliseconds");
			} else if (iterative_recursive.equalsIgnoreCase("R")) {
				double time = recursiveTowerOfHanoi(numberOfDisks);
				System.out.println("For n=" + numberOfDisks + " iteratively, done in: " + time + " milliseconds");
			}
			System.out.println();
			String exit = "";
			System.out.println("Do you want to try again or exit?");
			do {
				exit = "";
				System.out.print("Try again (A)/Exit (E): ");
				exit = scan.nextLine();
			} while (!exit.equalsIgnoreCase("A") && !exit.equalsIgnoreCase("E"));
			System.out.println();
			if (exit.equalsIgnoreCase("E"))
				running = false;
		}
	}

	public static void main(String[] args) {

		/*
		 * Recursive version:
		 * n=3 ==> 0.5339 milliseconds
		 * n=5 ==> 1.6335 milliseconds
		 * n=10 ==> 25.5308 milliseconds
		 * n=100 ==> forever
		 * n=500 ==> forever
		 * n=1000 ==> forever
		 */
		/*
		 * Iterative version:
		 * n=3 ==> 0.6353 milliseconds
		 * n=5 ==> 1.6835 milliseconds
		 * n=10 ==> 26.3899 milliseconds
		 * n=100 ==> forever
		 * n=500 ==> forever
		 * n=1000 ==> forever
		 */
		menu();
	}
}