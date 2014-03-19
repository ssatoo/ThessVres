package com.atei.thessvres.BSTree;

import java.util.ArrayList;

import com.atei.thessvres.interfaces.ObjectToTree;

public class BSTree {

	private TreeNode root;

	public BSTree() {
		root = null;
	}

	public void insertElement(ObjectToTree data) {
		if (isEmpty())
			root = new TreeNode(data);
		else
			insertNode(data, root);
	}

	/**
	 * <p>
	 * inOrderTraversal method
	 * </p>
	 */
	public void inOrderTraversal() {
		System.out.println("INORDER TRAVERSAL");
		// inOrder(root);
		System.out.println();
	}

	public ArrayList<ObjectToTree> inOrderTraversalList() {
		ArrayList<ObjectToTree> sl = new ArrayList<ObjectToTree>();
		// System.out.println("INORDER TRAVERSAL");
		inOrder(root, sl);
		// System.out.println();

		return sl;
	}

	/**
	 * <p>
	 * preOrderTraversal method
	 * </p>
	 */
	public void preOrderTraversal() {
		System.out.println("PREORDER TRAVERSAL");
		preOrder(root);
		System.out.println();
	}

	/**
	 * <p>
	 * postOrderTraversal method
	 * </p>
	 */
	public void postOrderTraversal() {
		System.out.println("POSTORDER TRAVERSAL");
		postOrder(root);
		System.out.println();
	}

	/**
	 * <p>
	 * CheckS whether the BS Tree is empty
	 * 
	 * @return boolean
	 *         </p>
	 */
	public boolean isEmpty() {
		return (root == null);
	}

	// RECURSIVE PRIVATE METHODS

	// Recursive insert node
	private void insertNode(ObjectToTree data, TreeNode node) {

		ObjectToTree temp = (ObjectToTree) node.getNodeData();
		if (data.getCompareItem() < temp.getCompareItem()) {
			if (node.getLeftNode() == null)
				node.setLeftNode(new TreeNode(data));
			else
				insertNode(data, node.getLeftNode());
		} else // move right
		{
			if (node.getRightNode() == null)
				node.setRightNode(new TreeNode(data));
			else
				insertNode(data, node.getRightNode());
		}
	}

	// Recursive inOrder
	private void inOrder(TreeNode node, ArrayList<ObjectToTree> sl) {
		// if the current node is null return one level up
		if (node == null)
			return;

		inOrder(node.getLeftNode(), sl);

		// System.out.print(node.getNodeData() + ", ");
		sl.add(node.getNodeData());
		inOrder(node.getRightNode(), sl);
	}

	// Recursive preOrder
	private void preOrder(TreeNode node) {
		// if the current node is null return one level up
		if (node == null)
			return;

		System.out.print(node.getNodeData() + ", ");
		preOrder(node.getLeftNode());
		preOrder(node.getRightNode());
	}

	// Recursive postOrder
	private void postOrder(TreeNode node) {
		// if the current node is null return one level up
		if (node == null)
			return;

		postOrder(node.getLeftNode());
		postOrder(node.getRightNode());
		System.out.print(node.getNodeData() + ", ");
	}

}