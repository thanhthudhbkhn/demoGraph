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

	Vertex insert(Vertex node, int key) {

		/* 1. Perform the normal BST insertion */
		if (node == null) {
			return (new Vertex(key));
		}

		if (key < node.vertexId) {
			node.left = insert(node.left, key);
		} else if (key > node.vertexId) {
			node.right = insert(node.right, key);
		} else // Duplicate keys not allowed 
		{
			return node;
		}

		/* 2. Update height of this ancestor node */
		node.height = 1 + max(height(node.left),
				height(node.right));

		/* 3. Get the balance factor of this ancestor 
			node to check whether this node became 
			unbalanced */
		int balance = getBalance(node);

		// If this node becomes unbalanced, then there 
		// are 4 cases Left Left Case 
		if (balance > 1 && key < node.left.vertexId) {
			return rightRotate(node);
		}

		// Right Right Case 
		if (balance < -1 && key > node.right.vertexId) {
			return leftRotate(node);
		}

		// Left Right Case 
		if (balance > 1 && key > node.left.vertexId) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		// Right Left Case 
		if (balance < -1 && key < node.right.vertexId) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}

		/* return the (unchanged) node pointer */
		return node;
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
