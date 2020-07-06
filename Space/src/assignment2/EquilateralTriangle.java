/* 
 * Name: Yaacoub Yaacoub
 * Date Last Modified: 15/2/2020
 * Project Description: Creating space and adding shapes such as Circle, Square, Triangle or Equilateral Triangle
 * 						to the space. we can manipulate these shapes by deleting them or calculate their area and 
 * 						perimeter by giving the x and y coordinates. It is also possible to read shapes from an 
 * 						external text file and save its content if it is a useful shape to the created space.
 * 						Finally save all the existing shapes in a file called output.txt. 
 * Other Files: Shape.java, Circle.java, Square.java, Triangle.java, Space.java
 */
package assignment2;

public class EquilateralTriangle extends Triangle {
	/*
	 * An EquilateralTriangle class is a child class for Triangle which is a child of the abstract class Shape 
	 * and it is defined by the side length.
	 * We can calculate the area and the perimeter of an EquilateralTriangle using the perimeter() and area() methods
	 * that overrides the methods of the parent class Triangle.
	 */
	
	protected float side;

	public EquilateralTriangle(String color, float x_coordinate, float y_coordinate, float side) {
		super(color, x_coordinate, y_coordinate, side, side, side);
		this.side = side;
	}

	public float getSide() {
		return side;
	}

	public void setSide(float side) {
		this.side = side;
	}

	public String toString() {
		return "Equilateral Triangle [side=" + side + ", color=" + color + ", x_coordinate=" + x_coordinate
				+ ", y_coordinate=" + y_coordinate + "]";
	}

}
