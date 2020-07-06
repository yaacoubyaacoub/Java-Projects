package assignment3_3;

public class Employee {
	/*
	 * an Employee is represented by 3 instance data: name, recruitment date and
	 * salary
	 */
	protected String name;
	protected String rec_date;
	protected float salary;

	public Employee(String name, String rec_date, float salary) {
		/*
		 * Create a new Staff object
		 * 
		 * name: String representing the name of the Employee. rec_date: String
		 * representing the recruitment date of the Employee. salary: Float representing
		 * the salary of the Employee.
		 */
		this.name = name;
		this.rec_date = rec_date;
		this.salary = salary;
	}

	public String getName() {
		/*
		 * Returns: String name
		 */
		return name;
	}

	public void setName(String name) {
		/*
		 * Set name to a new String value
		 */
		this.name = name;
	}

	public String getRec_date() {
		/*
		 * Returns: String of recruitment date
		 */
		return rec_date;
	}

	public void setRec_date(String rec_date) {
		/*
		 * Set rec_date to a new String value
		 */
		this.rec_date = rec_date;
	}

	public float getSalary() {
		/*
		 * Returns: float Salary
		 */
		return salary;
	}

	public void setSalary(float salary) {
		/*
		 * Set salary to a new float value
		 */
		this.salary = salary;
	}

	public boolean equals(Object o) {
		/*
		 * Returns: true if 2 employees are equals and false otherwise
		 */
		if (((this.getClass().getName()).equals(o.getClass().getName()))
				&& ((this.name).equals(((Employee) o).getName())))
			return true;
		else
			return false;
	}

	public String toString() {
		/*
		 * Print employee data
		 */
		return "Employee( name: " + name + ", rec date: " + rec_date + ", Salary: " + salary + ")";
	}
}
