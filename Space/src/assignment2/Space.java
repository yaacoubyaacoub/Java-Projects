/* 
 * Name: Yaacoub Yaacoub
 * Date Last Modified: 15/2/2020
 * Project Description: Creating space and adding shapes such as Circle, Square, Triangle or Equilateral Triangle
 * 						to the space. we can manipulate these shapes by deleting them or calculate their area and 
 * 						perimeter by giving the x and y coordinates. It is also possible to read shapes from an 
 * 						external text file and save its content if it is a useful shape to the created space.
 * 						Finally save all the existing shapes in a file called output.txt. 
 * Other Files: Shape.java, Circle.java, Square.java, Triangle.java, EquilateralTriangle.java
 */
package assignment2;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Space {
	/*
	 * A Space class is a class that will hold the created Shapes. It is defined by
	 * an array of Shape to hold theses shapes. So in the Space class we can
	 * addCircle, addSquare, addTriangle, deleteShape and compute the area and the
	 * perimeter.
	 */

	private Shape[] shapes;
	private int index;

	public Space() {
		shapes = new Shape[0];
		index = 0;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Shape[] getShapes() {
		return shapes;
	}

	public void setShapes(Shape[] shapes) {
		this.shapes = shapes;
	}

	private void increaseArraySize() {
		/*
		 * Creates a new array object of the same name shapes every time the user adds a
		 * new shape to the space. The length of the new array is: length = (length of
		 * the old array shapes)+1
		 */
		Shape[] temp_shapes = new Shape[shapes.length + 1];
		for (int i = 0; i < shapes.length; i++) {
			temp_shapes[i] = shapes[i];
		}
		this.shapes = temp_shapes;
	}

	public void addCircle(String color, float x_coordinate, float y_coordinate, float radius) {
		/*
		 * Add a circle to the array. it takes the color of the circle, x and y
		 * coordinates and the radius.
		 */
		increaseArraySize();
		shapes[index] = new Circle(color, x_coordinate, y_coordinate, radius);
		index++;
	}

	public void addSquare(String color, float x_coordinate, float y_coordinate, float side_length) {
		/*
		 * Add a square to the array. it takes the color of the square, x and y
		 * coordinates and the side length.
		 */
		increaseArraySize();
		shapes[index] = new Square(color, x_coordinate, y_coordinate, side_length);
		index++;
	}

	public void addTriangle(String color, float x_coordinate, float y_coordinate, float side1, float side2,
			float side3) {
		/*
		 * Add a triangle to the array if the sides are not equals. Add an equilateral
		 * triangle to the array if all three side are equal. it takes the color of the
		 * triangle, x and y coordinates and the three sides lengths.
		 */
		if ((side1 + side2) > side3 && (side2 + side3) > side1 && (side1 + side3) > side2) {
			increaseArraySize();
			if (side1 == side2 && side1 == side3) {
				shapes[index] = new EquilateralTriangle(color, x_coordinate, y_coordinate, side1);
				index++;
			} else {
				shapes[index] = new Triangle(color, x_coordinate, y_coordinate, side1, side2, side3);
				index++;
			}
		} else
			System.out.println("This triangle does not exists");
	}

	public void deleteShape(ArrayList<Shape> delete) {
		/*
		 * create a new temporary shapes array and save in it the shape that are not
		 * deleted. make the shape array points to the new temporary array holding only
		 * the wanted shapes. it takes an arraylist that holds all the wanted shapes.
		 */
		Shape[] temp_shapes = new Shape[delete.size()];
		for (int i = 0; i < temp_shapes.length; i++) {
			temp_shapes[i] = delete.get(i);
		}
		this.shapes = temp_shapes;
		index = delete.size();
	}

	public void computePerimeter_Area(ArrayList<Shape> compute) {
		/*
		 * computes the perimeter and area of a number of shapes and print it on the
		 * screen. it takes an arraylist containing all shapes that we need to compute
		 * their area and perimeter.
		 */
		for (int i = 0; i < compute.size(); i++) {
			System.out.print(compute.get(i));
			System.out.println(" Perimeter=" + compute.get(i).perimeter() + ", Area=" + compute.get(i).area() + ".");
		}
	}

	public String chooseColor(String c) {
		/*
		 * choose the corresponding color to an input character. it takes a String c
		 * composed of only one character given by the user to choose a color. return a
		 * String with the actual color.
		 */
		String color = null;
		switch (c.toUpperCase()) {
		case "B":
			color = "Black";
			break;
		case "G":
			color = "Green";
			break;
		case "L":
			color = "Blue";
			break;
		case "O":
			color = "Orange";
			break;
		case "P":
			color = "Pink";
			break;
		case "R":
			color = "Red";
			break;
		case "U":
			color = "Purple";
			break;
		case "W":
			color = "White";
			break;
		case "Y":
			color = "Yellow";
			break;
		}
		return color;
	}

	public static void main(String[] args) throws IOException {

		Space space1 = new Space();

		Scanner scan = new Scanner(System.in);
		boolean running = true;
		while (running) {// stuck in this while loop the program is running and the user did not exit
			System.out.println("1. Add a Shape");
			System.out.println("2. Delete a shape");
			System.out.println("3. Compute Area and Perimeter");
			System.out.println("4. Display all");
			System.out.println("5. Read from File");
			System.out.println("6. Exit");
			System.out.println("-------------------------------");
			System.out.print("Enter your choice: ");

			int choice = 0;
			do { // Stuck in this while loop until the user enters good input
				try {
					choice = Integer.parseInt(scan.nextLine());
					if (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6) {
						System.out.print("Enter a number between 1 and 6 only: ");
					}
				} catch (NumberFormatException e) {
					System.out.print("You should input a number between 1 and 6: ");
				}
			} while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6);

			switch (choice) {
			case 1:
				boolean case1 = true;
				while (case1) { // stuck in this loop while the user is adding shapes and break out of it only
								// when the user inputs the character "D"
					System.out.println();
					System.out.println("A. Add a Cricle");
					System.out.println("B. Add a Square");
					System.out.println("C. Add a triangle");
					System.out.println("D. Return to main menu");
					System.out.println("-------------------------------");
					System.out.print("Enter shape: ");

					String choice1 = "";
					do { // Stuck in this While loop until the user enters good input
						choice1 = scan.nextLine();
						if (!choice1.equalsIgnoreCase("A") && !choice1.equalsIgnoreCase("B")
								&& !choice1.equalsIgnoreCase("C") && !choice1.equalsIgnoreCase("D")) {
							System.out.print("Enter a letter between A and D only: ");
						}
					} while (!choice1.equalsIgnoreCase("A") && !choice1.equalsIgnoreCase("B")
							&& !choice1.equalsIgnoreCase("C") && !choice1.equalsIgnoreCase("D"));

					if (choice1.equalsIgnoreCase("A")) {
						System.out.print("Enter the circle radius: ");
						float radius = 0.0f;
						boolean is_radius = true;
						do { // Stuck in this while loop until the user enters good input
							try {
								radius = Float.parseFloat(scan.nextLine());
								if (radius <= 0) {
									System.out.print("Radius can't be negative. Please enter a valid radius: ");
								} else
									is_radius = false;
							} catch (NumberFormatException e) {
								System.out.print("Please enter a valid radius: ");
							}
						} while (is_radius);

						System.out.print("Enter the x coordinate of the center of the circle: ");
						float x_coordinate = 0.0f;
						boolean is_x_coordinate = true;
						do { // Stuck in this while loop until the user enters good input
							try {
								x_coordinate = Float.parseFloat(scan.nextLine());
								is_x_coordinate = false;
							} catch (NumberFormatException e) {
								System.out.print("Please enter a valid coordinate: ");
							}
						} while (is_x_coordinate);

						System.out.print("Enter the y coordinate of the center of the circle: ");
						float y_coordinate = 0.0f;
						boolean is_y_coordinate = true;
						do { // Stuck in this while loop until the user enters good input
							try {
								y_coordinate = Float.parseFloat(scan.nextLine());
								is_y_coordinate = false;
							} catch (NumberFormatException e) {
								System.out.print("Please enter a valid coordinate: ");
							}
						} while (is_y_coordinate);

						System.out.print("Enter the color:\n"
								+ "Black(B), Green(G), Blue(L), Orange(O), Pink(P), Red(R), Purpule(U), White(W), Yellow(Y): ");
						String color = "";
						do { // Stuck in this while loop until the user enters good input
							color = scan.nextLine();
							if (!color.equalsIgnoreCase("B") && !color.equalsIgnoreCase("G")
									&& !color.equalsIgnoreCase("L") && !color.equalsIgnoreCase("O")
									&& !color.equalsIgnoreCase("P") && !color.equalsIgnoreCase("R")
									&& !color.equalsIgnoreCase("U") && !color.equalsIgnoreCase("W")
									&& !color.equalsIgnoreCase("Y")) {
								System.out.print("Enter one of the following colors only:\n"
										+ "Black(B), Green(G), Blue(L), Orange(O), Pink(P), Red(R), Purpule(U), White(W), Yellow(Y): ");
							}
						} while (!color.equalsIgnoreCase("B") && !color.equalsIgnoreCase("G")
								&& !color.equalsIgnoreCase("L") && !color.equalsIgnoreCase("O")
								&& !color.equalsIgnoreCase("P") && !color.equalsIgnoreCase("R")
								&& !color.equalsIgnoreCase("U") && !color.equalsIgnoreCase("W")
								&& !color.equalsIgnoreCase("Y"));
						String the_color = space1.chooseColor(color);
						space1.addCircle(the_color, x_coordinate, y_coordinate, radius);

					} else if (choice1.equalsIgnoreCase("B")) {
						System.out.print("Enter the square side length: ");
						float side_length = 0.0f;
						boolean is_side_length = true;
						do { // Stuck in this while loop until the user enters good input
							try {
								side_length = Float.parseFloat(scan.nextLine());
								if (side_length <= 0) {
									System.out.print("Side can't be negative. Please enter a valid radius: ");
								} else
									is_side_length = false;
							} catch (NumberFormatException e) {
								System.out.print("Please enter a valid length: ");
							}
						} while (is_side_length);

						System.out.print("Enter the x coordinate of the center of the square: ");
						float x_coordinate = 0.0f;
						boolean is_x_coordinate = true;
						do { // Stuck in this while loop until the user enters good input
							try {
								x_coordinate = Float.parseFloat(scan.nextLine());
								is_x_coordinate = false;
							} catch (NumberFormatException e) {
								System.out.print("Please enter a valid coordinate: ");
							}
						} while (is_x_coordinate);

						System.out.print("Enter the y coordinate of the center of the square: ");
						float y_coordinate = 0.0f;
						boolean is_y_coordinate = true;
						do { // Stuck in this while loop until the user enters good input
							try {
								y_coordinate = Float.parseFloat(scan.nextLine());
								is_y_coordinate = false;
							} catch (NumberFormatException e) {
								System.out.print("Please enter a valid coordinate: ");
							}
						} while (is_y_coordinate);

						System.out.print("Enter the color:\n"
								+ "Black(B), Green(G), Blue(L), Orange(O), Pink(P), Red(R), Purpule(U), White(W), Yellow(Y): ");
						String color = "";
						do { // Stuck in this while loop until the user enters good input
							color = scan.nextLine();
							if (!color.equalsIgnoreCase("B") && !color.equalsIgnoreCase("G")
									&& !color.equalsIgnoreCase("L") && !color.equalsIgnoreCase("O")
									&& !color.equalsIgnoreCase("P") && !color.equalsIgnoreCase("R")
									&& !color.equalsIgnoreCase("U") && !color.equalsIgnoreCase("W")
									&& !color.equalsIgnoreCase("Y")) {
								System.out.print("Enter one of the following colors only:\n"
										+ "Black(B), Green(G), Blue(L), Orange(O), Pink(P), Red(R), Purpule(U), White(W), Yellow(Y): ");
							}
						} while (!color.equalsIgnoreCase("B") && !color.equalsIgnoreCase("G")
								&& !color.equalsIgnoreCase("L") && !color.equalsIgnoreCase("O")
								&& !color.equalsIgnoreCase("P") && !color.equalsIgnoreCase("R")
								&& !color.equalsIgnoreCase("U") && !color.equalsIgnoreCase("W")
								&& !color.equalsIgnoreCase("Y"));
						String the_color = space1.chooseColor(color);

						space1.addSquare(the_color, x_coordinate, y_coordinate, side_length);

					} else if (choice1.equalsIgnoreCase("C")) {
						System.out.print("Enter the triangle side 1 length: ");
						float side1 = 0.0f;
						boolean is_side1 = true;
						do { // Stuck in this while loop until the user enters good input
							try {
								side1 = Float.parseFloat(scan.nextLine());
								if (side1 <= 0) {
									System.out.print("Side can't be negative. Please enter a valid radius: ");
								} else
									is_side1 = false;
							} catch (NumberFormatException e) {
								System.out.print("Please enter a valid length: ");
							}
						} while (is_side1);

						System.out.print("Enter the triangle side 2 length: ");
						float side2 = 0.0f;
						boolean is_side2 = true;
						do { // Stuck in this while loop until the user enters good input
							try {
								side2 = Float.parseFloat(scan.nextLine());
								if (side2 <= 0) {
									System.out.print("Side can't be negative. Please enter a valid radius: ");
								} else
									is_side2 = false;
							} catch (NumberFormatException e) {
								System.out.print("Please enter a valid length: ");
							}
						} while (is_side2);

						System.out.print("Enter the triangle side 3 length: ");
						float side3 = 0.0f;
						boolean is_side3 = true;
						do { // Stuck in this while loop until the user enters good input
							try {
								side3 = Float.parseFloat(scan.nextLine());
								if (side3 <= 0) {
									System.out.print("Side can't be negative. Please enter a valid radius: ");
								} else
									is_side3 = false;
							} catch (NumberFormatException e) {
								System.out.print("Please enter a valid length: ");
							}
						} while (is_side3);

						System.out.print("Enter the x coordinate of the center of the triangle: ");
						float x_coordinate = 0.0f;
						boolean is_x_coordinate = true;
						do { // Stuck in this while loop until the user enters good input
							try {
								x_coordinate = Float.parseFloat(scan.nextLine());
								is_x_coordinate = false;
							} catch (NumberFormatException e) {
								System.out.print("Please enter a valid coordinate: ");
							}
						} while (is_x_coordinate);

						System.out.print("Enter the y coordinate of the center of the triangle: ");
						float y_coordinate = 0.0f;
						boolean is_y_coordinate = true;
						do { // Stuck in this while loop until the user enters good input
							try {
								y_coordinate = Float.parseFloat(scan.nextLine());
								is_y_coordinate = false;
							} catch (NumberFormatException e) {
								System.out.print("Please enter a valid coordinate: ");
							}
						} while (is_y_coordinate);

						System.out.print("Enter the color:\n"
								+ "Black(B), Green(G), Blue(L), Orange(O), Pink(P), Red(R), Purpule(U), White(W), Yellow(Y): ");
						String color = "";
						do { // Stuck in this while loop until the user enters good input
							color = scan.nextLine();
							if (!color.equalsIgnoreCase("B") && !color.equalsIgnoreCase("G")
									&& !color.equalsIgnoreCase("L") && !color.equalsIgnoreCase("O")
									&& !color.equalsIgnoreCase("P") && !color.equalsIgnoreCase("R")
									&& !color.equalsIgnoreCase("U") && !color.equalsIgnoreCase("W")
									&& !color.equalsIgnoreCase("Y")) {
								System.out.print("Enter one of the following colors only:\n"
										+ "Black(B), Green(G), Blue(L), Orange(O), Pink(P), Red(R), Purpule(U), White(W), Yellow(Y): ");
							}
						} while (!color.equalsIgnoreCase("B") && !color.equalsIgnoreCase("G")
								&& !color.equalsIgnoreCase("L") && !color.equalsIgnoreCase("O")
								&& !color.equalsIgnoreCase("P") && !color.equalsIgnoreCase("R")
								&& !color.equalsIgnoreCase("U") && !color.equalsIgnoreCase("W")
								&& !color.equalsIgnoreCase("Y"));
						String the_color = space1.chooseColor(color);

						space1.addTriangle(the_color, x_coordinate, y_coordinate, side1, side2, side3);

					} else if (choice1.equalsIgnoreCase("D")) {
						System.out.println();
						break;
					}
				}
				break;

			case 2:
				if (space1.getShapes().length == 0) {
					System.out.println("No saved shapes");
				} else {
					System.out.print("Enter the x coordinate of the shape you want to delete: ");
					float x_coordinate = 0.0f;
					boolean is_x_coordinate = true;
					do { // Stuck in this while loop until the user enters good input
						try {
							x_coordinate = Float.parseFloat(scan.nextLine());
							is_x_coordinate = false;
						} catch (NumberFormatException e) {
							System.out.print("Please enter a valid coordinate: ");
						}
					} while (is_x_coordinate);

					System.out.print("Enter the y coordinate of the shape you want to delete: ");
					float y_coordinate = 0.0f;
					boolean is_y_coordinate = true;
					do { // Stuck in this while loop until the user enters good input
						try {
							y_coordinate = Float.parseFloat(scan.nextLine());
							is_y_coordinate = false;
						} catch (NumberFormatException e) {
							System.out.print("Please enter a valid coordinate: ");
						}
					} while (is_y_coordinate);

					ArrayList<Shape> needed_shapes = new ArrayList<Shape>();
					for (int i = 0; i < space1.getShapes().length; i++) {// search for the element we want to keep
																			// and the elements we want to delete
						if (!(space1.getShapes()[i].getX_coordinate() == x_coordinate
								&& space1.getShapes()[i].getY_coordinate() == y_coordinate)) {
							needed_shapes.add(space1.getShapes()[i]);
						}
					}
					if (space1.getIndex() == needed_shapes.size())
						System.out.println("No shapes having these coordinates are found");
					else
						System.out.println(
								"Shapes having x=" + x_coordinate + " and y=" + y_coordinate + " will be deleted");
					space1.deleteShape(needed_shapes);
				}
				System.out.println();
				break;

			case 3:
				if (space1.getShapes().length == 0) {
					System.out.println("No saved shapes");
				} else {
					System.out.print("Enter the x coordinate of the shape you want to calculate area and perimeter: ");
					float x_coordinate = 0.0f;
					boolean is_x_coordinate = true;
					do { // Stuck in this while loop until the user enters good input
						try {
							x_coordinate = Float.parseFloat(scan.nextLine());
							is_x_coordinate = false;
						} catch (NumberFormatException e) {
							System.out.print("Please enter a valid coordinate: ");
						}
					} while (is_x_coordinate);

					System.out.print("Enter the y coordinate of the shape you want to calculate area and perimeter: ");
					float y_coordinate = 0.0f;
					boolean is_y_coordinate = true;
					do { // Stuck in this while loop until the user enters good input
						try {
							y_coordinate = Float.parseFloat(scan.nextLine());
							is_y_coordinate = false;
						} catch (NumberFormatException e) {
							System.out.print("Please enter a valid coordinate: ");
						}
					} while (is_y_coordinate);

					ArrayList<Shape> needed_shapes = new ArrayList<Shape>();
					for (int i = 0; i < space1.getShapes().length; i++) {// search for the element we want to compute
																			// their area and perimeter
						if (space1.getShapes()[i].getX_coordinate() == x_coordinate
								&& space1.getShapes()[i].getY_coordinate() == y_coordinate) {
							needed_shapes.add(space1.getShapes()[i]);
						}
					}
					if (needed_shapes.size() != 0)
						System.out.println("Area and Perimeter of the shapes having x=" + x_coordinate + " and y="
								+ y_coordinate + " will be calculated.");
					else
						System.out.println("No shapes having these coordinates are found");

					space1.computePerimeter_Area(needed_shapes);
				}
				System.out.println();
				break;

			case 4:
				if (space1.getShapes().length == 0) {
					System.out.println("No saved shapes to display");
				} else {
					for (int i = 0; i < space1.getShapes().length; i++) { // prints each element of the array on a
																			// new line
						System.out.println(space1.getShapes()[i]);
					}
				}
				System.out.println();
				break;

			case 5:
				try {
					FileReader fr = new FileReader(new File("input.txt"));
					BufferedReader br = new BufferedReader(fr);
					String line = null;
					String[] ln;
					int line_number = 0;
					while ((line = br.readLine()) != null) {// stuck in this loop while the file still have data
															// to read
						ln = line.split(", ");
						line_number++;
						try {
							if (ln.length == 5) {
								if (ln[0].equalsIgnoreCase("Circle")) {
									space1.addCircle(ln[1], Float.parseFloat(ln[2]), Float.parseFloat(ln[3]),
											Float.parseFloat(ln[4]));
								} else if (ln[0].equalsIgnoreCase("Square")) {
									space1.addSquare(ln[1], Float.parseFloat(ln[2]), Float.parseFloat(ln[3]),
											Float.parseFloat(ln[4]));
								} else if (ln[0].equalsIgnoreCase("Equilateral Triangle")) {
									space1.addTriangle(ln[1], Float.parseFloat(ln[2]), Float.parseFloat(ln[3]),
											Float.parseFloat(ln[4]), Float.parseFloat(ln[4]), Float.parseFloat(ln[4]));
								} else
									System.out.println("Line number " + line_number
											+ " has been skipped since it was a useless line.");

							} else if (ln.length == 7) {
								if (ln[0].equalsIgnoreCase("triangle")) {
									space1.addTriangle(ln[1], Float.parseFloat(ln[2]), Float.parseFloat(ln[3]),
											Float.parseFloat(ln[4]), Float.parseFloat(ln[5]), Float.parseFloat(ln[6]));
								} else
									System.out.println("Line number " + line_number
											+ " has been skipped since it was a useless line.");
							} else
								System.out.println("Line number " + line_number
										+ " has been skipped since it was a useless line.");
						} catch (NumberFormatException e) {
							System.out.println(
									"Line number " + line_number + " has been skipped since it was a useless line.");
						}
					}
					br.close();
				} catch (FileNotFoundException e) {
					System.out.println("The file you are trying to read from was not found.");
					System.out.println("Please create the file and try again.");
				}
				System.out.println();
				break;

			case 6:
				System.out.println("Exiting the program...");
				if (space1.getShapes().length == 0) {
					System.out.println("No saved shapes, the file won't be created");
				} else {
					System.out.println("Created shapes will be saved in the output.txt file");
					FileWriter fw = new FileWriter(new File("output.txt"));
					BufferedWriter bw = new BufferedWriter(fw);
					for (int i = 0; i < space1.getIndex(); i++) {// write each element of the array on a new line of
																	// a file called output.txt
						bw.write(space1.getShapes()[i].toString() + "\n");
					}
					bw.close();
					System.out.println("File saved. You can check it in your directory.");
				}
				running = false;
				break;
			}
		}
		scan.close();
	}
}