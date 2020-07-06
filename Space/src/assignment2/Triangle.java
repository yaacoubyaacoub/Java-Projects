/* 
 * Name: Yaacoub Yaacoub
 * Date Last Modified: 15/2/2020
 * Project Description: Creating space and adding shapes such as Circle, Square, Triangle or Equilateral Triangle
 * 						to the space. we can manipulate these shapes by deleting them or calculate their area and 
 * 						perimeter by giving the x and y coordinates. It is also possible to read shapes from an 
 * 						external text file and save its content if it is a useful shape to the created space.
 * 						Finally save all the existing shapes in a file called output.txt. 
 * Other Files: Shape.java, Circle.java, Square.java, Space.java, EquilateralTriangle.java
 */
package assignment2;

public class Triangle extends Shape {
	/*
	 * A triangle class is a child class for Shape and it is defined by the length of the three sides.
	 * We can calculate the area and the perimeter of a Triangle using the perimeter() and area() methods
	 * that overrides the abstract methods of the parent abstract class Shape.
	 */
	
	protected float side1;
	protected float side2;
	protected float side3;

	public Triangle(String color, float x_coordinate, float y_coordinate, float side1, float side2, float side3) {
		super(color, x_coordinate, y_coordinate);
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
	}

	public float getSide1() {
		return side1;
	}

	public void setSide1(float side1) {
		this.side1 = side1;
	}

	public float getSide2() {
		return side2;
	}

	public void setSide2(float side2) {
		this.side2 = side2;
	}

	public float getSide3() {
		return side3;
	}

	public void setSide3(float side3) {
		this.side3 = side3;
	}

	public float perimeter() {
		/*
		 * Return a float perimeter of the triangle
		 */
		return side1 + side2 + side3;
	}

	public float area() {
		/*
		 * Return a float area of the triangle
		 */
		float p = (side1 + side2 + side3) / 2;
		return (float) Math.sqrt(p * (p - side1) * (p - side2) * (p - side3));
	}

	public boolean equals(Object o) {
		/*
		 * Inherits the equals method of the superclass Shape:
		 *  if it is false than return false,
		 * if it is true than check if both triangles have equal sides, if true than return true
		 * if they have different sides than return false
		 */
		if (!super.equals(o))
			return false;
		else if (this.getSide1() == ((Triangle) o).getSide1() && this.getSide2() == ((Triangle) o).getSide2()
				&& this.getSide3() == ((Triangle) o).getSide3())
			return true;
		else if (this.getSide1() == ((Triangle) o).getSide2() && this.getSide2() == ((Triangle) o).getSide3()
				&& this.getSide3() == ((Triangle) o).getSide1())
			return true;
		else if (this.getSide1() == ((Triangle) o).getSide3() && this.getSide2() == ((Triangle) o).getSide1()
				&& this.getSide3() == ((Triangle) o).getSide2())
			return true;
		else
			return false;
	}

	public String toString() {
		return "Triangle [side1=" + side1 + ", side2=" + side2 + ", side3=" + side3 + ", " + super.toString() + "]";
	}

}
