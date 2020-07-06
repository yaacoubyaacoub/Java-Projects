/* 
 * Name: Yaacoub Yaacoub
 * Date Last Modified: 15/2/2020
 * Project Description: Creating space and adding shapes such as Circle, Square, Triangle or Equilateral Triangle
 * 						to the space. we can manipulate these shapes by deleting them or calculate their area and 
 * 						perimeter by giving the x and y coordinates. It is also possible to read shapes from an 
 * 						external text file and save its content if it is a useful shape to the created space.
 * 						Finally save all the existing shapes in a file called output.txt. 
 * Other Files: Space.java, Circle.java, Square.java, Triangle.java, EquilateralTriangle.java
 */
package assignment2;

public abstract class Shape {
	/*
	 * Shape class is an abstract class that represents a shape( circle, square, triangle or equilateral triangle)
	 * defined by color, x and y coordinates.
	 * We can move a shape, calculate its area and perimeter.
	 */
	
	protected String color;
	protected float x_coordinate;
	protected float y_coordinate;

	public Shape(String color, float x_coordinate, float y_coordinate) {
		this.color = color;
		this.x_coordinate = x_coordinate;
		this.y_coordinate = y_coordinate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public float getX_coordinate() {
		return x_coordinate;
	}

	public void setX_coordinate(float x_coordinate) {
		this.x_coordinate = x_coordinate;
	}

	public float getY_coordinate() {
		return y_coordinate;
	}

	public void setY_coordinate(float y_coordinate) {
		this.y_coordinate = y_coordinate;
	}

	public boolean equals(Object o) {
		/*
		 * takes a object o return true if both objects are of the same type and have
		 * the same x coordinate and y coordinate return false otherwise
		 */
		if (this.getClass().getName().equals(o.getClass().getName())) {
			if (this.getX_coordinate() == ((Shape) o).getX_coordinate()
					&& this.getY_coordinate() == ((Shape) o).getY_coordinate())
				return true;
			else
				return false;
		} else
			return false;
	}

	public boolean isLessThan(Shape m) {
		/*
		 * Returns true if the object is less than m Returns false otherwise the object
		 * is less than m if its x coordinate is less than the x coordinate of m if both
		 * objects have equal x coordinate than the object is less than m if its y
		 * coordinate is less than the y coordinate of m
		 */
		if (this.getX_coordinate() < m.getX_coordinate())
			return true;
		else if (this.getX_coordinate() == m.getX_coordinate() && this.getY_coordinate() < m.getY_coordinate())
			return true;
		else
			return false;
	}

	public void move(float dx, float dy) {
		/*
		 * move a shape dx in the x direction and dy in the y direction if the shape is
		 * a Circle than it cannot be moved, an appropriate message will be displayed on
		 * the screen
		 */
		if (this instanceof Circle)
			System.out.println("A circle cannot be moved.");
		else {
			this.setX_coordinate(this.getX_coordinate() + dx);
			this.setY_coordinate(this.getY_coordinate() + dy);
		}
	}

	public String toString() {
		return "color=" + color + ", x_coordinate=" + x_coordinate + ", y_coordinate=" + y_coordinate;
	}

	public abstract float perimeter();

	public abstract float area();
}
