/* 
 * Name: Yaacoub Yaacoub
 * Date Last Modified: 6/5/2020
 * Project Description: Creating a dictionary implemented using a Binary search trees, each node of the tree
 * 						will have a word and its description, we can add words and its description to the dictionary
 * 						remove words and its description from the dictionary, display the definitions in present
 * 						in the dictionary alphabetically and search for a definition. You can display the tree as a
 * 						tree. 
 * Other Files: BinarySearchTree.java, Dictionary.java.
 */
package assignment6;

public class BTNode {

	private String word;
	private String description;
	private BTNode left;
	private BTNode right;

	public BTNode(String word, String description) {
		this.word = word;
		this.description = description;
		this.left = null;
		this.right = null;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BTNode getLeft() {
		return left;
	}

	public void setLeft(BTNode left) {
		this.left = left;
	}

	public BTNode getRight() {
		return right;
	}

	public void setRight(BTNode right) {
		this.right = right;
	}

	public boolean equals(BTNode n) {
		return (this.getWord().equalsIgnoreCase(n.getWord()))
				&& (this.getDescription().equalsIgnoreCase(n.getDescription()));
	}

	public boolean isLeaf() {
		if (left == null && right == null)
			return true;
		else
			return false;
	}

	public BTNode clone() {
		BTNode n = new BTNode(this.getWord(), this.getDescription());
		return n;
	}
}
