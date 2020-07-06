/* 
 * Name: Yaacoub Yaacoub
 * Date Last Modified: 15/2/2020
 * Project Description: Creating space and adding shapes such as Circle, Square, Triangle or Equilateral Triangle
 * 						to the space. we can manipulate these shapes by deleting them or calculate their area and 
 * 						perimeter by giving the x and y coordinates. It is also possible to read shapes from an 
 * 						external text file and save its content if it is a useful shape to the created space.
 * 						Finally save all the existing shapes in a file called output.txt. 
 * Other Files: Shape.java, Space.java, Square.java, Triangle.java, EquilateralTriangle.java
 */
package assignment2;

public class Circle extends Shape {
	/*
	 * A Circle class is a child class for Shape and it is defined by the radius.
	 * We can calculate the area and the perimeter of a Circle using the perimeter() and area() methods
	 * that overrides the abstract methods of the parent abstract class Shape.
	 */

	protected float radius;

	public Circle(String color, float x_coordinate, float y_coordinate, float radius) {
		super(color, x_coordinate, y_coordinate);
		this.radius = radius;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public float perimeter() {
		/*
		 * Return a float perimeter of the circle
		 */
		return (float) (2 * Math.PI * radius);
	}

	public float area() {
		/*
		 * Return a float area of the circle
		 */
		return (float) Math.pow((Math.PI * radius), 2);
	}

	public boolean equals(Object o) {
		/*
		 * Inherits the equals method of the superclass Shape:
		 * if it is false than return false,
		 * if it is true than check if both circles have equal radius, if true than return true
		 * if they have different radius than return false
		 */
		if (!super.equals(o))
			return false;
		else if (this.getRadius() == ((Circle) o).getRadius())
			return true;
		else
			return false;
	}

	public String toString() {
		return "Circle [radius=" + radius + ", " + super.toString() + "]";
	}

}
