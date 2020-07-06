package stacks;

public class StacksMain {

	public static void main(String[] args) {
		int x = Stack.computeFullyParenthesized_WithNeg("(((-110+5)*-4)-((9*2)+(-1)))");
		System.out.println(x);
		int y = Stack.computeFullyParenthesized("((110+5)*(4))");
		System.out.println(y);
	}
}