/* 
 * Name: Yaacoub Yaacoub
 * Date Last Modified: 8/2/2020
 * Project Description: Creating an Employee database for a university, this university has employees that can be
 * 						either faculty or staff; we can add, delete raise salary to each of these employees.
 * Other Files: Employee.java, Faculty.java, Staff.java
 */

package assignment1;

import java.util.ArrayList;
import java.util.Scanner;

public class University {
	/*
	 * A University is represented by an arraylist of Employee, these employees
	 * could be Faculty or staff
	 */
	public static int size = 0;
	private ArrayList<Employee> employees;

	public University() {
		/*
		 * Create a new University object
		 */
		employees = new ArrayList<Employee>();
	}

	public ArrayList<Employee> getEmployees() {
		/*
		 * Returns: ArrayList of Employees
		 */
		return employees;
	}

	public void add_faculty(String name, String rec_date, float salary, int teaching_load) {
		/*
		 * Add a faculty member to the employees arraylist if it does not exist, if it
		 * exists prompt the user and do not add him/her to the list.
		 * 
		 * name: String representing the name of the Employee. 
		 * rec_date: String representing the recruitment date of the Employee. 
		 * salary: Float representing the salary of the Employee. 
		 * teaching_load: int representing the teaching load of the Employee.
		 */

		Faculty f = new Faculty(name, rec_date, salary, teaching_load);
		boolean exist = false;
		for (int i = 0; i < size; i++) {
			if (employees.get(i).equals(f)) {
				exist = true;
				System.out.println("This employee already exists.");
				break;
			}
		}
		if (!exist) {
			employees.add(f);
			size++;
		}
	}

	public void add_staff(String name, String rec_date, float salary) {
		/*
		 * Add a staff member to the employees arraylist if it does not exist, if it
		 * exists prompt the user and do not add him/her to the list.
		 * 
		 * name: String representing the name of the Employee. rec_date: String
		 * representing the recruitment date of the Employee. salary: Float representing
		 * the salary of the Employee.
		 */

		Staff s = new Staff(name, rec_date, salary);
		boolean exist = false;
		for (int i = 0; i < size; i++) {
			if (employees.get(i).equals(s)) {
				exist = true;
				System.out.println("This employee already exists.");
				break;
			}
		}
		if (!exist) {
			employees.add(s);
			size++;
		}
	}

	public void delete_employee(int index) {
		/*
		 * Deletes an employee from the list.
		 * 
		 * index: int representing the index of the employee in the employees ArrayList.
		 */
		employees.remove(index);
		size--;
	}

	public void printFacultyStaff() {
		/*
		 * Prints the faculty then the staff members present in the ArrayList employees.
		 */
		String s = "";
		for (int i = 0; i < size; i++) {
			s = employees.get(i).getClass().getName();
			if (s.equals("assignment1.Faculty")) {
				System.out.println(employees.get(i));
			}
		}
		for (int i = 0; i < size; i++) {
			s = employees.get(i).getClass().getName();
			if (s.equals("assignment1.Staff")) {
				System.out.println(employees.get(i));
			}
		}
	}

	public void staff_raise(int index, int r) {
		/*
		 * Raise the salary of a specific staff member.
		 * 
		 * index: int representing the index of the employee in the employees ArrayList
		 * r: int representing the raise percentage for the employee
		 */
		((Employee) employees.get(index))
				.setSalary((float) ((((Employee) employees.get(index)).getSalary()) * (1 + (r / 100.0))));
	}

	public void faculty_raise(int index, int r) {
		/*
		 * Raise the salary of a specific faculty member.
		 * 
		 * index: int representing the index of the employee in the employees ArrayList
		 * r: int representing the raise percentage for the employee
		 */
		staff_raise(index, r);
		((Employee) employees.get(index))
				.setSalary((float) ((((Employee) employees.get(index)).getSalary()) * (1 + (Faculty.bonus / 100.0))));
	}

	public String toString() {
		/*
		 * Prints the employees present in the employees ArrayList.
		 */
		String s = "";
		for (int i = 0; i < employees.size(); i++) {
			s = s + (employees.get(i)).toString() + "\n";
		}
		return s;
	}

	public static void main(String[] args) {

		University u = new University();
		Scanner scan = new Scanner(System.in);

		while (true) { // loop while the user is still using the program

			System.out.println("1 .Add employee");
			System.out.println("2 .Delete empolyee");
			System.out.println("3 .List all");
			System.out.println("4 .Raise salary");
			System.out.println("5 .Exit");
			System.out.println("----------------------");
			System.out.print("Enter your choice: ");

			int trial = 0;
			int choice = 0;
			do { // Stuck in this loop while until the user enters good input
				try {
					choice = Integer.parseInt(scan.nextLine());
					if (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5) {
						System.out.print("Enter a number between 1 and 5 only: ");
						trial++;
					}
				} catch (Exception e) {
					System.out.print("You should input a number between 1 and 5: ");
					trial++;
				}
				if (trial == 5)
					break;
			} while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5);
			trial = 0;

			if (choice == 1) {
				System.out.print("Enter the name: ");
				String name = scan.nextLine();

				System.out.print("Enter the salary: ");
				float salary = 0.0f;
				boolean is_salary = true;
				do { // Stuck in this loop while until the user enters good input (salary)
					try {
						salary = (float) Integer.parseInt(scan.nextLine());
						is_salary = false;
					} catch (Exception e) {
						System.out.print("Please enter the salary: ");
					}
				} while (is_salary);

				System.out.print("Enter the recruitment date (format dd/mm/yyyy): ");
				String rec_date = "";
				boolean is_date = true;
				do { // Stuck in this loop while until the user enters good input (date)
					rec_date = scan.nextLine();
					try {
						String c = "" + rec_date.charAt(2) + rec_date.charAt(5);
						int day = Integer.parseInt((rec_date.substring(0, 2)));
						int month = Integer.parseInt((rec_date.substring(3, 5)));
						int year = Integer.parseInt((rec_date.substring(6)));

						if (c.equals("//") && month >= 1 && month <= 12) {
							if (month == 2) {
								if (year % 4 == 0) {
									if (!(day >= 1 && day <= 29))
										System.out.print("wrong date. Please try again (format dd/mm/yyyy): ");
									else
										is_date = false;
								} else {
									if (!(day >= 1 && day <= 28))
										System.out.print("wrong date. Please try again (format dd/mm/yyyy): ");
									else
										is_date = false;
								}
							} else if (month == 4 || month == 6 || month == 9 || month == 11) {
								if (!(day >= 1 && day <= 30))
									System.out.print("wrong date. Please try again (format dd/mm/yyyy): ");
								else
									is_date = false;
							} else {
								if (!(day >= 1 && day <= 31))
									System.out.print("wrong date. Please try again (format dd/mm/yyyy): ");
								else
									is_date = false;
							}
						} else
							System.out.print("wrong date. Please try again (format dd/mm/yyyy): ");
					} catch (Exception e) {
						System.out.print("wrong date. Please try again (format dd/mm/yyyy): ");
					}
				} while (is_date);

				System.out
						.print("Enter \"Faculty\" if the employee is faculty and \"Staff\" if the employee is staff: ");
				String emp = scan.nextLine();
				while ((!(emp.toLowerCase()).equals("faculty")) && (!(emp.toLowerCase()).equals("staff"))) { // Stuck in this loop while until the user enters good input (faculty/staff)
					System.out.print("Enter \"Faculty\" or \"Staff\" only: ");
					emp = scan.nextLine();
				}
				if (emp.equalsIgnoreCase("faculty")) {
					int teaching_load = 0;
					boolean is_tl = true;
					System.out.print("Enter the teaching load: ");
					while (is_tl) {	// Stuck in this loop while until the user enters good input (salary)
						try {
							teaching_load = Integer.parseInt(scan.nextLine());
							is_tl = false;
						} catch (Exception e) {
							System.out.print("Please enter the teaching load: ");
						}
					}

					u.add_faculty(name, rec_date, salary, teaching_load);
				} else if (emp.equalsIgnoreCase("staff")) {
					u.add_staff(name, rec_date, salary);
				}
			}

			else if (choice == 2) {
				if (University.size == 0) {
					System.out.println("No Employees. Please add Employees!");
					System.out.println();
					continue;
				}
				int index = 0;
				String indexes = "";
				System.out.print("Enter the name of the employee you want to delete: ");
				String delete = scan.nextLine();
				for (int i = 0; i < University.size; i++) {	// Search for the indexes of the employee having a specific name to delete
					if ((((u.getEmployees()).get(i)).getName()).equalsIgnoreCase(delete)) {
						indexes = indexes + i;
					}
				}
				if (!indexes.equals("")) {
					for (int i = 0; i < indexes.length(); i++) {	// Ask the user if he wants to delete the employee
																	// if 2 employees have the same name, the user will 
																	// be asked if he wants to delete a specific employee
																	// if this is not the employee in mind ask for the other
						index = Character.getNumericValue(indexes.charAt(i));
						System.out.print("Do you want to delete: ");
						System.out.println((u.getEmployees()).get(index));
						System.out.print("For Yes press \"y\", for No press \"n\": ");
						String del = scan.nextLine();
						while (!del.equalsIgnoreCase("y") && !del.equalsIgnoreCase("n")) {	// Stuck in this loop while until the user enters good input (salary)
							System.out.print("For Yes press \"y\", for No press \"n\": ");
							del = scan.nextLine();
						}
						if (del.equalsIgnoreCase("y")) {
							u.delete_employee(index);
							System.out.println("Employee has been deleted.");
							break;
						}
					}
				} else
					System.out.println("Employee not found");
			}

			else if (choice == 3) {
				if (University.size == 0) {
					System.out.println("No Employees. Please add Employees!");
					System.out.println();
					continue;
				} else
					u.printFacultyStaff();
			}

			else if (choice == 4) {
				if (University.size == 0) {
					System.out.println("No Employees. Please add Employees!");
					System.out.println();
					continue;
				}
				int index = 0;
				String indexes = "";
				System.out.print("Enter the name of the employee you want to give him a raise: ");
				String raise = scan.nextLine();
				for (int i = 0; i < University.size; i++) {	// Search for the indexes of the employee having a specific name to give a raise
					if ((((u.getEmployees()).get(i)).getName()).equalsIgnoreCase(raise)) {
						indexes = indexes + i;
					}
				}
				if (!indexes.equals("")) {
					int j = 0;
					for (j = 0; j < indexes.length(); j++) {	// Ask the user if he wants to delete the employee
																// if 2 employees have the same name, the user will 
																// be asked if he wants to delete a specific employee
																// if this is not the employee in mind ask for the other
						index = Character.getNumericValue(indexes.charAt(j));
						System.out.print("Raise for: ");
						System.out.println((u.getEmployees()).get(index));
						if (j == indexes.length() - 1)
							break;
						else {
							System.out.print("For Yes press \"y\", for No press \"n\": ");
							String del = scan.nextLine();
							while (!del.equalsIgnoreCase("y") && !del.equalsIgnoreCase("n")) {	// Stuck in this loop while until the user enters good input (salary)
								System.out.print("For Yes press \"y\", for No press \"n\": ");
								del = scan.nextLine();
							}
							if (del.equalsIgnoreCase("y"))
								break;
						}
					}
					System.out.print("Enter the raise percentage: ");
					int r = 0;
					boolean is_r = true;
					do {	// Stuck in this loop while until the user enters good input (salary)
						try {
							r = Integer.parseInt(scan.nextLine());
							is_r = false;
						} catch (Exception e) {
							System.out.print("Please enter the raise percentage: ");
						}
					} while (is_r);

					if (((u.getEmployees()).get(index).getClass().getName()).equals("assignment1.Staff")) {
						u.staff_raise(index, r);
					} else if (((u.getEmployees()).get(index).getClass().getName()).equals("assignment1.Faculty")) {
						u.faculty_raise(index, r);
					}
				} else
					System.out.println("Employee not found");
			} else if (choice == 5) {
				System.out.print("Are you sure you want to exit (y/n): ");
				String exit = scan.nextLine();
				while (!exit.equalsIgnoreCase("y") && !exit.equalsIgnoreCase("n")) {	// Stuck in this loop while until the user enters good input (salary)
					System.out.print("For Yes press \"y\", for No press \"n\": ");
					exit = scan.nextLine();
				}
				if (exit.equalsIgnoreCase("y")) {
					break;
				}
			} else {
				System.out.println("To many wrong inputs, the program is exiting ");
				System.out.println("Sorry :(");
				break;
			}
			System.out.println();
		}
	}
}