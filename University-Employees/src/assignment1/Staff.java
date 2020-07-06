package assignment1;

public class Staff extends Employee {
	/*
	 * a Faculty is represented by 4 instance data: name, recruitment date and salary
	 * child of the class Employee 
	 */
	public Staff(String name, String rec_date, float salary) {
		/*
		 * Create a new Staff object
		 * 
		 * name: String representing the name of the Employee. 
		 * rec_date: String representing the recruitment date of the Employee. 
		 * salary: Float representing the salary of the Employee.
		 */
		super(name, rec_date, salary);
	}

	public String toString() {
		/*
		 * Print Staff member data (overrides toString method of the parent class)
		 */
		return "Staff member( name: " + name + ", rec date: " + rec_date + ", Salary: " + salary + ")";
	}
}
