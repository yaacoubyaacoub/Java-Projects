/* 
 * Name: Yaacoub Yaacoub
 * Date Last Modified: 6/5/2020
 * Project Description: Creating a dictionary implemented using a Binary search trees, each node of the tree
 * 						will have a word and its description, we can add words and its description to the dictionary
 * 						remove words and its description from the dictionary, display the definitions in present
 * 						in the dictionary alphabetically and search for a definition. You can display the tree as a
 * 						tree. 
 * Other Files: BTNode.java, Dictionary.java.
 */
package assignment6;

public class BinarySearchTree {

	private BTNode root;

	private String tempString = "";
	private BTNode[] tempBTNode;
	private int tempIndex;
	private BinarySearchTree tempTree;

	public BinarySearchTree() {
		root = null;
	}

	public BTNode getRoot() {
		return root;
	}

	public void setRoot(BTNode root) {
		this.root = root;
	}

	public int size(BTNode root) {
		if (root == null)
			return 0;
		else
			return size(root.getLeft()) + size(root.getRight()) + 1;
	}

	public BTNode find(BTNode n, String word) {
		if (n == null)
			return null;
		else if (n.getWord().equalsIgnoreCase(word))
			return n;
		else if (n.getWord().compareToIgnoreCase(word) > 0)
			return find(n.getLeft(), word);
		else
			return find(n.getRight(), word);
	}

	public String findDescription(BTNode n, String word) {
		if (n == null)
			return "";
		else if (n.getWord().equalsIgnoreCase(word))
			return n.getDescription() + "\t" + findDescription(n.getLeft(), word);
		else if (n.getWord().compareToIgnoreCase(word) > 0)
			return findDescription(n.getLeft(), word);
		else
			return findDescription(n.getRight(), word);
	}

	public boolean add(BTNode root, BTNode definition) {
		if (root == null) {
			this.root = definition;
			return true;
		} else if (root.getWord().equals(definition.getWord())
				&& root.getDescription().equalsIgnoreCase(definition.getDescription()))
			return false;
		else if (root.getWord().compareToIgnoreCase(definition.getWord()) >= 0) {
			if (root.getLeft() == null) {
				root.setLeft(definition);
				return true;
			} else
				return add(root.getLeft(), definition);
		} else {
			if (root.getRight() == null) {
				root.setRight(definition);
				return true;
			} else
				return add(root.getRight(), definition);
		}
	}

	private void inOrder(BTNode root) {
		if (root.getLeft() != null)
			inOrder(root.getLeft());
		this.tempString = this.tempString + root.getWord() + ": " + root.getDescription() + "\t";
		if (root.getRight() != null)
			inOrder(root.getRight());
	}

	public String[] getinOrder(BTNode root) {
		this.tempString = "";
		inOrder(root);
		String[] tree = tempString.split("\t");
		return tree;
	}

	private void preOrder(BTNode root) {
		this.tempBTNode[this.tempIndex] = root;
		this.tempIndex++;
		if (root.getLeft() != null)
			preOrder(root.getLeft());
		if (root.getRight() != null)
			preOrder(root.getRight());
	}

	public BTNode[] getPreOrder(BTNode root) {
		this.tempBTNode = new BTNode[size(root)];
		this.tempIndex = 0;
		preOrder(root);
		return tempBTNode;
	}

	private void cloning(BTNode[] old) {
		if (this.tempIndex == old.length)
			return;
		else {
			this.tempTree.add(this.tempTree.getRoot(), old[this.tempIndex].clone());
			this.tempIndex++;
			cloning(old);
		}

	}

	public BinarySearchTree clone() {
		BTNode[] old = getPreOrder(this.getRoot());
		tempTree = new BinarySearchTree();
		this.tempIndex = 0;
		cloning(old);
		return tempTree;

	}

	public int nodeLevel(BTNode root, BTNode n) {
		if (root == null)
			return 0;
		else if (n.equals(root))
			return 1;
		else if (root.getWord().compareToIgnoreCase(n.getWord()) >= 0)
			return nodeLevel(root.getLeft(), n) + 1;
		else
			return nodeLevel(root.getRight(), n) + 1;
	}

	public int anyNodeLevel(BTNode root, BTNode n, int level) {
		if (root == null)
			return 0;
		else if (n.equals(root))
			return level;
		int theLevel = anyNodeLevel(root.getLeft(), n, (level + 1));
		if (theLevel != 0)
			return theLevel;
		theLevel = anyNodeLevel(root.getRight(), n, (level + 1));
		if (theLevel != 0)
			return theLevel;
		return 0;

	}

	public int getTotalNumberOfLevels() {
		BTNode[] tree = getPreOrder(this.getRoot());
		int level = 0;
		for (int i = 0; i < tree.length; i++) {
			int nodeLevel = nodeLevel(this.getRoot(), tree[i]);
			if (nodeLevel > level)
				level = nodeLevel;
		}
		return level;
	}

	public BinarySearchTree toFullBinarySearchTree() {
		this.tempIndex = 0;
		tempTree = new BinarySearchTree();
		tempTree = this.clone();
		tempTree.addEmptyNodes(tempTree.getRoot(), 1, tempTree.getTotalNumberOfLevels());
		return tempTree;
	}

	public void addEmptyNodes(BTNode root, int level, int levels) {

		if (level < levels) {
			if (root.getLeft() == null) {
				root.setLeft(new BTNode("e" + this.tempIndex++, ""));
				addEmptyNodes(root.getLeft(), (level + 1), levels);
			} else
				addEmptyNodes(root.getLeft(), (level + 1), levels);

			if (root.getRight() == null) {
				root.setRight(new BTNode("e" + this.tempIndex++, ""));
				addEmptyNodes(root.getRight(), (level + 1), levels);
			} else
				addEmptyNodes(root.getRight(), (level + 1), levels);
		}
	}
}
