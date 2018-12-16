/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demograph;

/**
 *
 * @author Computer
 */
public class AVLTree {

	Vertex root;

	// A utility function to get the height of the tree 
	int height(Vertex N) {
		if (N == null) {
			return 0;
		}
		return N.height;
	}

	// A utility function to get maximum of two integers 
	int max(int a, int b) {
		return (a > b) ? a : b;
	}

	// A utility function to right rotate subtree rooted with y
	Vertex rightRotate(Vertex y) {
		Vertex x = y.left;
		Vertex T2 = x.right;

		// Perform rotation 
		x.right = y;
		y.left = T2;

		// Update heights 
		y.height = max(height(y.left), height(y.right)) + 1;
		x.height = max(height(x.left), height(x.right)) + 1;

		// Return new root 
		return x;
	}

	// A utility function to left rotate subtree rooted with x 
	Vertex leftRotate(Vertex x) {
		Vertex y = x.right;
		Vertex T2 = y.left;

		// Perform rotation 
		y.left = x;
		x.right = T2;

		// Update heights 
		x.height = max(height(x.left), height(x.right)) + 1;
		y.height = max(height(y.left), height(y.right)) + 1;

		// Return new root 
		return y;
	}

	// Get Balance factor of node N 
	int getBalance(Vertex N) {
		if (N == null) {
			return 0;
		}

		return height(N.left) - height(N.right);
	}
	
	//add a node to a tree have root
	Vertex insert(Vertex root, Vertex node) {

		/* 1. Perform the normal BST insertion */
		if (root == null) {
			return node;
		}

		if (node.vertexId < root.vertexId) {
			root.left = insert(root.left, node);
		} else if (node.vertexId > root.vertexId) {
			root.right = insert(root.right, node);
		} else // Duplicate keys not allowed 
		{
			return root;
		}

		/* 2. Update height of this ancestor root */
		root.height = 1 + max(height(root.left),
				height(root.right));

		/* 3. Get the balance factor of this ancestor 
			root to check whether this root became 
			unbalanced */
		int balance = getBalance(root);

		// If this root becomes unbalanced, then there 
		// Left Left Case 
		if (balance > 1 && node.vertexId < root.left.vertexId) {
			return rightRotate(root);
		}

		// Right Right Case 
		if (balance < -1 && node.vertexId > root.right.vertexId) {
			return leftRotate(root);
		}

		// Left Right Case 
		if (balance > 1 && node.vertexId > root.left.vertexId) {
			root.left = leftRotate(root.left);
			return rightRotate(root);
		}

		// Right Left Case 
		if (balance < -1 && node.vertexId < root.right.vertexId) {
			root.right = rightRotate(root.right);
			return leftRotate(root);
		}

		/* return the (unchanged) root pointer */
		return root;
	}
	
	Vertex insert(Vertex root, int node) {

		/* 1. Perform the normal BST insertion */
		if (root == null) {
			return new Vertex(node);
		}

		if (node < root.vertexId) {
			root.left = insert(root.left, node);
		} else if (node > root.vertexId) {
			root.right = insert(root.right, node);
		} else // Duplicate keys not allowed 
		{
			return root;
		}

		/* 2. Update height of this ancestor root */
		root.height = 1 + max(height(root.left),
				height(root.right));

		/* 3. Get the balance factor of this ancestor 
			root to check whether this root became 
			unbalanced */
		int balance = getBalance(root);

		// If this root becomes unbalanced, then there 
		// Left Left Case 
		if (balance > 1 && node < root.left.vertexId) {
			return rightRotate(root);
		}

		// Right Right Case 
		if (balance < -1 && node > root.right.vertexId) {
			return leftRotate(root);
		}

		// Left Right Case 
		if (balance > 1 && node > root.left.vertexId) {
			root.left = leftRotate(root.left);
			return rightRotate(root);
		}

		// Right Left Case 
		if (balance < -1 && node < root.right.vertexId) {
			root.right = rightRotate(root.right);
			return leftRotate(root);
		}

		/* return the (unchanged) root pointer */
		return root;
	}

	// A utility function to print preorder traversal of the tree. 
	// The function also prints height of every node 
	void preOrder(Vertex node) {
		if (node != null) {
			System.out.print(node.vertexId + " ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}
	
	//get (find) the Vertex by key
	Vertex getVertex(Vertex root, int key){
		if (root == null) {
			return null;
		}
		if (key < root.vertexId) {
			return getVertex(root.left, key);
		} else if (key > root.vertexId) {
			return getVertex(root.right, key);
		} else {
			return root;
		}
	}
}
