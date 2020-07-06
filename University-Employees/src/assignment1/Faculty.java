package assignment1;

public class Faculty extends Employee {
	/*
	 * a Faculty is represented by 4 instance data: name, recruitment date, salary and teaching load
	 * child of the class Employee 
	 */
	protected int teaching_load;
	public final static int bonus = 2;

	public Faculty(String name, String rec_date, float salary, int teaching_load) {
		/*
		 * Create a new Staff object
		 * 
		 * name: String representing the name of the Employee. 
		 * rec_date: String representing the recruitment date of the Employee. 
		 * salary: Float representing the salary of the Employee. 
		 * teaching_load: int representing the teaching load of the Employee.
		 */
		super(name, rec_date, salary);
		this.teaching_load = teaching_load;
	}

	public int getTeaching_load() {
		/*
		 * Returns: int teaching load
		 */
		return teaching_load;
	}

	public void setTeaching_load(int teaching_load) {
		/*
		 * Set teaching_load to a new int value
		 */
		this.teaching_load = teaching_load;
	}

	public String toString() {
		/*
		 * Print Faculty member data (overrides toString method of the parent class)
		 */
		return "Faculty member( name: " + name + ", rec date: " + rec_date + ", Salary: " + salary + ", teaching load: "
				+ teaching_load + ")";
	}

}
