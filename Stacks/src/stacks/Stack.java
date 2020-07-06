package stacks;

public class Stack {
	private Node top;

	public Node getTop() {
		return top;
	}

	public void setTop(Node top) {
		this.top = top;
	}

	public Stack() {
		top = null;
	}

	public boolean isEmpty() {
		if (top == null)
			return true;
		else
			return false;
	}

	public void push(Object o) {
		/* push info to the top of the stack */
		Node node = new Node(o);
		if (top == null)
			top = node;
		else {
			node.setNext(top);
			top = node;
		}
	}

	public Object pop() {
		/*
		 * pop the top element of the stack and return the value, return null if stack
		 * is empty
		 */
		if (top == null)
			return null;
		else {
			Node current = top;
			top = top.getNext();
			return current.getInfo();
		}
	}

	public Object peek() {
		/* return the element on the top of the stack, return null if stack is empty */
		if (top == null)
			return null;
		else
			return top.getInfo();
	}

	public static boolean isBalanced(String line) {
		Stack stack = new Stack();
		if (line == null)
			return true;
		else {
			for (int i = 0; i < line.length(); i++) {
				if ((line.charAt(i) == '{') || (line.charAt(i) == '(') || (line.charAt(i) == '['))
					stack.push(line.charAt(i));
				else if (line.charAt(i) == ']') {
					if ((stack.isEmpty()) || ((((char) stack.pop()) != ']')))
						return false;
				} else if (line.charAt(i) == ')') {
					if ((stack.isEmpty()) || ((((char) stack.pop()) != ')')))
						return false;
				} else if (line.charAt(i) == '}') {
					if ((stack.isEmpty()) || ((((char) stack.pop()) != '}')))
						return false;
				}
			}
			return stack.isEmpty();
		}
	}

	public static boolean isFullyParenthesized(String line) {
		if (!isBalanced(line))
			return false;
		else {
			for (int i = 0; i < line.length(); i++) {
				try {
					Integer.parseInt(line.substring(i, i + 1));
				} catch (NumberFormatException e) {
					continue;
				}
				if (i == 0)
					return false;
				else if (i == line.length() - 1)
					return false;
				else if ((line.charAt(i - 1) != '(') && (line.charAt(i + 1) != ')'))
					return false;
			}
			return true;
		}
	}

	public static int computeFullyParenthesized(String expression) {
		/* compute a fully parenthesized expression and return the result only int */
		Stack operants = new Stack();
		Stack operators = new Stack();
		for (int i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) == '(') {
				if (expression.charAt(i + 1) == '(')
					continue;
				else {
					boolean isInt = true;
					int integer = 0;
					int x = 2;
					do {
						try {
							integer = Integer.parseInt(expression.substring((i + 1), i + x));
							x++;
						} catch (NumberFormatException e) {
							i = i + x - 2;
							isInt = false;
						}
					} while (isInt);
					operants.push(integer);
				}
			} else if ((expression.charAt(i) == '+') || (expression.charAt(i) == '-') || (expression.charAt(i) == '*')
					|| (expression.charAt(i) == '/')) {
				operators.push(expression.charAt(i));
				if (expression.charAt(i + 1) != '(') {
					boolean isInt = false;
					int integer = 0;
					int x = 2;
					do {
						try {
							integer = Integer.parseInt(expression.substring((i + 1), i + x));
							x++;
						} catch (NumberFormatException e) {
							i = i + x - 2;
							isInt = true;
						}
					} while (!isInt);
					operants.push(integer);
				}
			} else if (expression.charAt(i) == ')') {
				if (!operators.isEmpty()) {
					int op1 = (int) operants.pop();
					int op2 = (int) operants.pop();
					char opr = (char) operators.pop();
					if (opr == '+')
						operants.push(op2 + op1);
					else if (opr == '-')
						operants.push(op2 - op1);
					else if (opr == '*')
						operants.push(op2 * op1);
					else if (opr == '/')
						operants.push(op2 / op1);
				}
			}
		}
		return (int) operants.pop();
	}

	public static int computeFullyParenthesized_WithNeg(String expression) {
		/* compute a fully parenthesized expression and return the result only int */
		Stack operants = new Stack();
		Stack operators = new Stack();
		for (int i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) == '(') {
				if (expression.charAt(i + 1) == '(')
					continue;
				else if (expression.charAt(i + 1) == '-') {
					boolean isInt = true;
					int integer = 0;
					int x = 3;
					do {
						try {
							integer = Integer.parseInt(expression.substring((i + 2), i + x));
							x++;
						} catch (NumberFormatException e) {
							i = i + x - 2;
							isInt = false;
						}
					} while (isInt);
					operants.push(-1 * integer);
				} else {
					boolean isInt = true;
					int integer = 0;
					int x = 2;
					do {
						try {
							integer = Integer.parseInt(expression.substring((i + 1), i + x));
							x++;
						} catch (NumberFormatException e) {
							i = i + x - 2;
							isInt = false;
						}
					} while (isInt);
					operants.push(integer);
				}
			} else if ((expression.charAt(i) == '+') || (expression.charAt(i) == '-') || (expression.charAt(i) == '*')
					|| (expression.charAt(i) == '/')) {
				operators.push(expression.charAt(i));
				if (expression.charAt(i + 1) != '(') {
					if (expression.charAt(i + 1) == '-') {
						boolean isInt = true;
						int integer = 0;
						int x = 3;
						do {
							try {
								integer = Integer.parseInt(expression.substring((i + 2), i + x));
								x++;
							} catch (NumberFormatException e) {
								i = i + x - 2;
								isInt = false;
							}
						} while (isInt);
						operants.push(-1 * integer);
					} else {
						boolean isInt = false;
						int integer = 0;
						int x = 2;
						do {
							try {
								integer = Integer.parseInt(expression.substring((i + 1), i + x));
								x++;
							} catch (NumberFormatException e) {
								i = i + x - 2;
								isInt = true;
							}
						} while (!isInt);
						operants.push(integer);
					}
				}
			} else if (expression.charAt(i) == ')') {
				if (!operators.isEmpty()) {
					int op1 = (int) operants.pop();
					int op2 = (int) operants.pop();
					char opr = (char) operators.pop();
					if (opr == '+')
						operants.push(op2 + op1);
					else if (opr == '-')
						operants.push(op2 - op1);
					else if (opr == '*')
						operants.push(op2 * op1);
					else if (opr == '/')
						operants.push(op2 / op1);
				}
			}
		}
		return (int) operants.pop();
	}
}
