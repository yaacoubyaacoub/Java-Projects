/* 
 * Name: Yaacoub Yaacoub
 * Date Last Modified: 6/5/2020
 * Project Description: Creating a dictionary implemented using a Binary search trees, each node of the tree
 * 						will have a word and its description, we can add words and its description to the dictionary
 * 						remove words and its description from the dictionary, display the definitions in present
 * 						in the dictionary alphabetically and search for a definition. You can display the tree as a
 * 						tree. 
 * Other Files: BTNode.java, BinarySearchTree.java.
 */
package assignment6;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Dictionary {
	public static Scanner scan = new Scanner(System.in);
	private BinarySearchTree bst;

	public static int scanInt() {
		/* Scan an integer and return it */
		int integer = 0;
		boolean is_integer = true;
		do { // Stuck in this while loop until the user enters good input
			try {
				integer = Integer.parseInt(scan.nextLine());
				if (integer >= 0) {
					is_integer = false;
				} else
					System.out.println("please enter a positive integer.");
			} catch (NumberFormatException e) {
				System.out.print("Please enter an integer: ");
			}
		} while (is_integer);
		return integer;
	}

	public Dictionary() {
		bst = new BinarySearchTree();
	}

	public BinarySearchTree getBst() {
		return bst;
	}

	public void setBst(BinarySearchTree bst) {
		this.bst = bst;
	}

	private void readFile(BufferedReader br, int line_number) throws IOException {
		String line = br.readLine();
		if (line == null)
			return;
		else {
			String[] def = line.split(": ");
			if (def.length == 2) {
				BTNode node = new BTNode(def[0], def[1]);
				bst.add(bst.getRoot(), node);
			} else
				System.out.println("Error in line " + line_number + ", it will be skipped");
			line_number++;
			readFile(br, line_number);
		}
	}

	public void readFromFile(String fileName) throws IOException {
		FileReader fr = new FileReader(new File(fileName));
		BufferedReader br = new BufferedReader(fr);
		readFile(br, 0);
		br.close();
	}

	public void remove(String word, String description) {
		BTNode[] tempNodes = bst.getPreOrder(bst.getRoot());
		if (description == null) {
			for (int i = 0; i < tempNodes.length; i++) {
				tempNodes[i].setLeft(null);
				tempNodes[i].setRight(null);
				if (tempNodes[i].getWord().equalsIgnoreCase(word))
					tempNodes[i] = null;
			}
		} else {
			for (int i = 0; i < tempNodes.length; i++) {
				tempNodes[i].setLeft(null);
				tempNodes[i].setRight(null);
				if ((tempNodes[i].getWord().equalsIgnoreCase(word))
						&& (tempNodes[i].getDescription().equalsIgnoreCase(description)))
					tempNodes[i] = null;
			}
		}
		bst.setRoot(null);
		for (int i = 0; i < tempNodes.length; i++) {
			if (tempNodes[i] != null) {
				bst.add(bst.getRoot(), tempNodes[i]);
			}
		}
	}

	private void printFindings(String[] defs, int index) {
		if (index == defs.length)
			return;
		else {
			System.out.println(defs[index]);
			index++;
			printFindings(defs, index);
		}

	}

	public void findWordDescription(String word) {
		String s = bst.findDescription(bst.getRoot(), word);
		if (s.equals(""))
			System.out.println("No Descriptions found (Word does not exist).");
		else {
			String[] defs = s.split("\t");
			System.out.println("Descriptions found for the word " + word + ":");
			printFindings(defs, 0);

		}
	}

	private void print(String[] s, int index) {
		if (index == s.length)
			return;
		else {
			System.out.println(s[index]);
			index++;
			print(s, index);
		}
	}

	public void printInOrder() {
		String[] s = bst.getinOrder(bst.getRoot());
		print(s, 0);
	}

	private void writeFile(BufferedWriter bw, String[] s, int index) throws IOException {
		if (index == s.length)
			return;
		else {
			bw.write(s[index] + "\n");
			index++;
			writeFile(bw, s, index);
		}
	}

	public void writeToFile(String fileName) {
		try {
			FileWriter fw = new FileWriter(new File(fileName));
			BufferedWriter bw = new BufferedWriter(fw);
			String[] s = bst.getinOrder(bst.getRoot());
			writeFile(bw, s, 0);
			bw.close();
		} catch (IOException e) {
			System.out.println("File not found");
		}

	}

	public static int[] indentation(int levels) {
		int[] indentations = new int[(int) Math.pow(2, levels) - 1];
		int x = 1;
		int index = 0;
		int checking = 0;
		int check = 1;
		int toSubtract = (indentations.length + 1) / 2;
		for (int i = 0; i < indentations.length; i++) {
			if (checking == check) {
				check = check * 2;
				checking = 1;
				toSubtract = (int) toSubtract / 2;
			} else
				checking++;
			if (i == 0)
				indentations[i] = (int) (Math.pow(2, levels) / 2);
			else if (x == 1) {
				indentations[i] = indentations[index] - toSubtract;
				x = 2;
			} else {
				indentations[i] = indentations[index] + toSubtract;
				x = 1;
				index++;
			}
		}
		return indentations;
	}

	public String indent(int indentation, int sub) {
		String s = "";
		for (int i = 0; i < (indentation - sub); i++)
			s = s + "-\t";
		return s;
	}

	public void displayTree() {
		int totalNbOfLevels = bst.getTotalNumberOfLevels();
		int[] indentions = indentation(totalNbOfLevels);
		BinarySearchTree b = (bst.clone()).toFullBinarySearchTree();
		BTNode[] nodes = b.getPreOrder(b.getRoot());
		int[] nodeLevel = new int[nodes.length];
		for (int i = 0; i < nodes.length; i++) {
			nodeLevel[i] = b.anyNodeLevel(b.getRoot(), nodes[i], 1);
		}

		int indentationIndex = 0;
		int sub = 0;
		String ind = "";
		String line = "";
		for (int i = 1; i <= totalNbOfLevels; i++) {
			for (int j = 0; j < nodeLevel.length; j++) {
				if (nodeLevel[j] == i) {
					ind = indent(indentions[indentationIndex], sub);
					sub = indentions[indentationIndex];
					if (nodes[j].getWord().charAt(0) == 'e')
						line = line + ind;
					else
						line = line + ind + nodes[j].getWord();
					indentationIndex++;
				}
			}
			System.out.println(line);
			line = "";
			sub = 0;
		}
	}

	public static void menu() {

		Dictionary d = new Dictionary();
		String fileName = "";
		boolean running = true;
		while (running) {// stuck in this while loop the program is running and the user did not exit
			System.out.println("1. Create the dictionary");
			System.out.println("2. Add a definition");
			System.out.println("3. Remove a definition");
			System.out.println("4. Search for a definition");
			System.out.println("5. Print dictionary");
			System.out.println("6. Display tree");
			System.out.println("7. Exit");
			System.out.println("-------------------");
			System.out.print("Enter your choice: ");

			int choice = 0;
			do {
				try {
					choice = Integer.parseInt(scan.nextLine());
					if (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6
							&& choice != 7) {
						System.out.print("Enter a number between 1 and 5 only: ");
					}
				} catch (NumberFormatException e) {
					System.out.print("You should input a number between 1 and 5: ");
				}
			} while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6
					&& choice != 7);

			System.out.println();
			switch (choice) {

			case 1:
				int count = 0;

				do {
					System.out.print("Enter the name of the text file with the extension (ie: file.txt): ");
					try {
						fileName = scan.nextLine();
						d.readFromFile(fileName);
						count = 4;
					} catch (IOException e) {
						System.out.println("this file does not exist, please try again.");
						count++;
					}
				} while (count < 3);

				if (count == 3) {
					System.out.println("To many wrong inputs.");
				}
				System.out.println();
				break;

			case 2:
				System.out.print("Enter the word that you want to add to the dictionary: ");
				String word = scan.nextLine();
				System.out.print("Enter its description: ");
				String description = scan.nextLine();
				BTNode definition = new BTNode(word, description);
				if (d.getBst().add(d.getBst().getRoot(), definition))
					System.out.println("Definition was added successfully.");
				else
					System.out.println("This definition already exists in the dictionary.");

				System.out.println();
				break;

			case 3:
				if (d.getBst().getRoot() == null) {
					System.out.println("No dictionary. Please create a dictionary first");
				} else {
					System.out.println("Enter the word you want to delete: ");
					String wordToDelete = scan.nextLine();
					String descToDelete = d.getBst().findDescription(d.getBst().getRoot(), wordToDelete);
					if (descToDelete == "")
						System.out.println("No such word in the dictionary.");
					else {
						String[] descToDeleteArr = descToDelete.split("\t");
						if (descToDeleteArr.length == 1) {
							System.out.println("The word and its description will be removed " + wordToDelete + ": "
									+ descToDeleteArr[0]);
							d.remove(wordToDelete, descToDeleteArr[0]);
						} else {
							System.out.println("Multiple description for this word, which one you want to delete?");
							for (int i = 0; i < descToDeleteArr.length; i++) {
								System.out.println(
										"description " + (i + 1) + "--> " + wordToDelete + ": " + descToDeleteArr[i]);
							}
							boolean goodIndex = false;
							int del = -1;
							do {
								System.out.println(
										"Please choose which definition you want to delete, a number from 1 to "
												+ descToDeleteArr.length);
								System.out.println("If you want to delete all, please press 0.");
								System.out.print("Waiting for your input: ");
								del = scanInt();
								if (del <= descToDeleteArr.length)
									goodIndex = true;
								else
									System.out.println("Wrong input please try again.");
							} while (!goodIndex);
							if (del == 0)
								d.remove(wordToDelete, null);
							else
								d.remove(wordToDelete, descToDeleteArr[del - 1]);
						}
					}
				}
				System.out.println();
				break;

			case 4:
				if (d.getBst().getRoot() == null) {
					System.out.println("No dictionary. Please create a dictionary first");
				} else {
					System.out.print("Enter the word that you want to get its descriptions: ");
					String wordToFind = scan.nextLine();
					d.findWordDescription(wordToFind);
				}
				System.out.println();
				break;

			case 5:
				if (d.getBst().getRoot() == null) {
					System.out.println("No dictionary. Please create a dictionary first");
				} else {
					d.printInOrder();
				}
				System.out.println();
				break;

			case 6:
				if (d.getBst().getRoot() == null) {
					System.out.println("No dictionary. Please create a dictionary first");
				} else {
					d.displayTree();
				}
				System.out.println();
				break;

			case 7:
				String exit = "";
				System.out.println("Do you want you really to exit?");
				do {
					exit = "";
					System.out.print("Yes (y)/No (n): ");
					exit = scan.nextLine();
				} while (!exit.equalsIgnoreCase("y") && !exit.equalsIgnoreCase("n"));
				System.out.println();
				if (exit.equalsIgnoreCase("y")) {
					if (d.getBst().getRoot() == null) {
						System.out.println("No dictionary. Nothing will be saved in the file.");
					} else {
						if (!fileName.equals("")) {
							d.writeToFile(fileName);
							System.out.println("Your dictionary is saved in your file.");
						} else {
							boolean isFileName = false;
							do {
								System.out.print(
										"Enter the name of the text file to save your dictionary with its extension (ie: file.txt): ");
								fileName = scan.nextLine();
								String[] f = fileName.split(".");
								if ((f.length == 2) && (f[1].equals("txt"))) {
									d.writeToFile(fileName);
									isFileName = true;
								} else
									System.out.println("Wrong file name, please try again.");
							} while (!isFileName);
						}
					}
					running = false;
				}
				break;
			}
		}
		scan.close();
	}

	public static void main(String[] args) {
		menu();
	}
}
