/* 
 * Name: Yaacoub Yaacoub
 * Date Last Modified: 15/2/2020
 * Project Description: Creating space and adding shapes such as Circle, Square, Triangle or Equilateral Triangle
 * 						to the space. we can manipulate these shapes by deleting them or calculate their area and 
 * 						perimeter by giving the x and y coordinates. It is also possible to read shapes from an 
 * 						external text file and save its content if it is a useful shape to the created space.
 * 						Finally save all the existing shapes in a file called output.txt. 
 * Other Files: Shape.java, Circle.java, Space.java, Triangle.java, EquilateralTriangle.java
 */
package assignment2;

public class Square extends Shape {
	/*
	 * A Square class is a child class for Shape and it is defined by the side_length.
	 * We can calculate the area and the perimeter of a Square using the perimeter() and area() methods
	 * that overrides the abstract methods of the parent abstract class Shape.
	 */

	protected float side_length;

	public Square(String color, float x_coordinate, float y_coordinate, float side_length) {
		super(color, x_coordinate, y_coordinate);
		this.side_length = side_length;
	}

	public float getSide() {
		return side_length;
	}

	public void setSide(float side_length) {
		this.side_length = side_length;
	}

	public float perimeter() {
		/*
		 * Return a float perimeter of the square
		 */
		return side_length * 4;
	}

	public float area() {
		/*
		 * Return a float area of the square
		 */
		return (float) Math.pow(side_length, 2);
	}

	public boolean equals(Object o) {
		/*
		 * Inherits the equals method of the superclass Shape:
		 * if it is false than return false,
		 * if it is true than check if both squares have equal side length, if true than return true
		 * if they have different side lengths than return false
		 */
		if (!super.equals(o))
			return false;
		else if (this.getSide() == ((Square) o).getSide())
			return true;
		else
			return false;
	}

	public String toString() {
		return "Square [side length=" + side_length + ", " + super.toString() + "]";
	}
}
