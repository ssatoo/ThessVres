package com.atei.thessvres.BSTree;

import com.atei.thessvres.interfaces.ObjectToTree;

public class TreeNode {
	private TreeNode left;
	private TreeNode right;
	private ObjectToTree data;

	public TreeNode(ObjectToTree d) {
		data = d;
		left = right = null;
	}

	// ACCESSOR METHODS

	/**
	 * <p>
	 * Returns the data that the node contains
	 * 
	 * @return Object
	 *         </p>
	 */
	public ObjectToTree getNodeData() {
		return data;
	}

	/**
	 * <p>
	 * Return left tree node
	 * 
	 * @return TreeNode
	 *         </p>
	 */
	public TreeNode getLeftNode() {
		return left;
	}

	/**
	 * <p>
	 * Return right tree node
	 * 
	 * @return TreeNode
	 *         </p>
	 */
	public TreeNode getRightNode() {
		return right;
	}

	/**
	 * <p>
	 * Return true if the node is leaf
	 * 
	 * @return boolean
	 *         </p>
	 */
	public boolean isLeaf() {
		return ((left == null) && (right == null));
	}

	// MUTATOR METHODS

	/**
	 * <p>
	 * Set left tree node to the given node
	 * 
	 * @param TreeNode
	 *            </p>
	 */
	public void setLeftNode(TreeNode node) {
		left = node;
	}

	/**
	 * <p>
	 * Set right tree node to the given node
	 * 
	 * @param TreeNode
	 *            </p>
	 */
	public void setRightNode(TreeNode node) {
		right = node;
	}

}