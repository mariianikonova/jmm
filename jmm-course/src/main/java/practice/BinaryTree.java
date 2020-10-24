package practice;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Stack;

public class BinaryTree {

	Node root;

	private static BinaryTree createBinaryTree() {
		BinaryTree bt = new BinaryTree();

		bt.add(10);
		bt.add(5);
		bt.add(15);
		bt.add(7);
		bt.add(3);
		bt.add(8);
		bt.add(1);
		bt.add(4);
		bt.add(6);
		bt.add(9);
		bt.add(13);
		bt.add(19);
		bt.add(11);
		bt.add(14);
		bt.add(17);
		bt.add(21);

		System.out.println(bt.toString());
		return bt;
	}

	//todo dfi
	//todo equivalent case
	public static void main(String[] args) {

		BinaryTree tree = createBinaryTree();
		tree.add(2);
		tree.search(7);
		tree.search(21);
		tree.df();
//        tree.delete(8);
//        tree.delete(1);
//        tree.df();
		tree.dfi();
		tree.bf();
	}

	void add(Integer value) {
		System.out.println("=========================== Add ======================");
		root = addNode(value, root);
	}

	void delete(Integer value) {
		System.out.println("=========================== Delete ======================");
		deleteNode(value, root, null);
	}

	Integer search(Integer value) {
		System.out.println("=========================== Search ======================");
		Node node = searchNode(value, root);
		if (Objects.isNull(node)) {
			return -1;
		}
		return node.value;
	}

	void df() {
		System.out.println("=========================== Depth Read ======================");
		dfNode(root);
	}

	void dfi() {
		System.out.println("=========================== Depth Read Iterative ======================");
		dfNodeI(root);
	}

	void bf() {
		System.out.println("=========================== Broad Read ======================");
		bfNode(root);
	}

	private void bfNode(Node current) {
		LinkedList<Node> queue = new LinkedList<>();
		queue.add(current);
		while (!queue.isEmpty()) {
			Node node = queue.pop();
			System.out.println(node.value);
			if (Objects.nonNull(node.left)) queue.add(node.left);
			if (Objects.nonNull(node.right)) queue.add(node.right);
		}
	}

	private void dfNodeI(Node current) {
		Stack<Node> stack = new Stack<>();
		stack.add(current);
		while (!stack.isEmpty()) {
			Node node = stack.pop();
			System.out.println(node.value);
			if (Objects.nonNull(node.left)) stack.add(node.left);
			if (Objects.nonNull(node.right)) stack.add(node.right);
		}
	}

	Node addNode(Integer value, Node current) {

		System.out.println("Value: " + value);
		if (Objects.isNull(current)) {
			System.out.println("Current NULL");
			System.out.println("New node");
			return new Node(null, null, value);
		}

		if (value <= current.value) {
			System.out.println("Current: " + current.value);
			System.out.println("Current LEFT: " + current.left);
			System.out.println("Move left");
			current.left = addNode(value, current.left);
		}
		if (current.value < value) {
			System.out.println("Current: " + current.value);
			System.out.println("Current RIGHT: " + current.left);
			System.out.println("Move right");
			current.right = addNode(value, current.right);
		}
		return current;
	}

	Node searchNode(Integer value, Node current) {

		if (Objects.isNull(current)) {
			System.out.println(value + " : current == null");
			return null;
		}

		if (value.equals(current.value)) {
			System.out.println("Found: " + value + "==" + current.value);
			return current;
		}

		if (value <= current.value) {
			System.out.println(value + "<" + current.value);
			return searchNode(value, current.left);
		}

		if (value > current.value) {
			System.out.println(value + ">" + current.value);
			return searchNode(value, current.right);
		}
		return current;
	}

	void dfNode(Node current) {

		if (Objects.isNull(current)) {
			return;
		}
		System.out.println(current.value);
		dfNode(current.left);
		dfNode(current.right);
	}

	void deleteNode(Integer value, Node current, Node parent) {
		System.out.println("value to delete: " + value);
		if (Objects.isNull(current)) {
			System.out.println("current is null");
		}
		System.out.println("current value: " + current.value);
		if (value < current.value) {
			deleteNode(value, current.left, current);
		}
		if (value > current.value) {
			deleteNode(value, current.right, current);
		}

		if (value == current.value) {
			if (Objects.isNull(current.right) && Objects.isNull(current.left)) {
				if (parent.left.equals(current)) parent.left = null;
				else parent.right = null;
				return;
			}

			if (Objects.isNull(current.right)) {
				if (parent.left.equals(current)) parent.left = current.left;
				else parent.right = current.left;
				current.left = null;
				return;
			}

			if (Objects.isNull(current.left)) {
				if (parent.left.equals(current)) parent.left = current.right;
				else parent.right = current.right;
				current.right = null;
				return;
			}

			Node leftMax = leftTreeMax(current.left, current);
			System.out.println("deleted node: " + current.value);
			current.value = leftMax.value;
			System.out.println("delete left tree max node: " + leftMax.value);
			leftMax = null;
			return;
		}
	}

	private Node leftTreeMax(Node currentToMax, Node parent) {
		if (currentToMax.right == null) {
			Node tmp = currentToMax;
			if (parent.left.equals(currentToMax)) parent.left = null;
			else parent.right = null;

			return tmp;
		} else
			return leftTreeMax(currentToMax.right, currentToMax);
	}

	class Node {

		Node left;
		Node right;
		Integer value;

		public Node(Node left, Node right, Integer value) {
			this.left = left;
			this.right = right;
			this.value = value;
		}
	}
}
