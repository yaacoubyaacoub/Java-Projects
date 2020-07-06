/* 
 * Name: Yaacoub Yaacoub
 * Date Last Modified: 9/3/2020
 * Project Description: An interface for the University class.
 * Other Files: Employee.java, Faculty.java, Staff.java, University.java
 */
package assignment3_3;

public interface UniversityInterface {
	/*
	 * An interface will guarantee that a certain class will have some methods;
	 * these methods will be declared as abstract methods in the interface and any
	 * class that implements the interface have to implement all these abstract
	 * method. 
	 * Unlike inheritance, a class can implements multiple interfaces, so
	 * using interfaces we can solve the multiple inheritance problem that java
	 * have.
	 * 
	 * So in this problem, we are asked to implement some functions such as add
	 * Employees (faculty or staff), delete employee, raise salary for faculty or
	 * staff and print all employees. So to make sure that all these methods will be
	 * implemented in the University class, we defined an interface called
	 * UniversityInterface and we declared all the previous required methods as
	 * abstract methods. the University class will implements this interface and then
	 * we will be sure that all needed methods which are present in the
	 * UniversityInterface interface will be implemented.
	 */

	public abstract void add_faculty(String name, String rec_date, float salary, int teaching_load);

	public abstract void add_staff(String name, String rec_date, float salary);

	public abstract void delete_employee(int index);

	public abstract void printFacultyStaff();

	public abstract void staff_raise(int index, int r);

	public abstract void faculty_raise(int index, int r);
}
