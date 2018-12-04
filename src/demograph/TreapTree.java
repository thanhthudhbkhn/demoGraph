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
public class TreapTree {

	Vertex root;
	private static Vertex nil = new Vertex(0);

	/**
	 * Constructor *
	 */
	public TreapTree() {
		root = nil;
	}

	/**
	 * Function to check if tree is empty *
	 */
	public boolean isEmpty() {
		return root == nil;
	}

	/**
	 * Make the tree logically empty *
	 */
	public void makeEmpty() {
		root = nil;
	}

	/**
	 * Functions to insert data *
	 */
	public void insert(Vertex X) {
		root = insert(X, root);
	}

	private Vertex insert(Vertex X, Vertex T) {
		if (T == nil) {
			return new Vertex(X.vertexId, nil, nil);
		} else if (X.vertexId < T.vertexId) {
			T.left = insert(X, T.left);
			if (T.left.priority < T.priority) {
				Vertex L = T.left;
				T.left = L.right;
				L.right = T;
				return L;
			}
		} else if (X.vertexId > T.vertexId) {
			T.right = insert(X, T.right);
			if (T.right.priority < T.priority) {
				Vertex R = T.right;
				T.right = R.left;
				R.left = T;
				return R;
			}
		}
		return T;
	}

	/**
	 * Functions to count number of nodes *
	 */
	public int countNodes() {
		return countNodes(root);
	}

	private int countNodes(Vertex r) {
		if (r == nil) {
			return 0;
		} else {
			int l = 1;
			l += countNodes(r.left);
			l += countNodes(r.right);
			return l;
		}
	}

	/**
	 * Functions to search for an vertexId *
	 */
	public Vertex get(int val) {
		return get(root, val);
	}

	private Vertex get(Vertex r, int val) {
		if (r == nil) {
			return null;
		}
		int rval = r.vertexId;
		if (val < rval) {
			return get(r.left, val);
		} else if (val > rval) {
			return get(r.right, val);
		} else {
			return r;
		}
	}

	/**
	 * Function for inorder traversal *
	 */
	public void inorder() {
		inorder(root);
	}

	private void inorder(Vertex r) {
		if (r != nil) {
			inorder(r.left);
			System.out.print(r.vertexId + " ");
			inorder(r.right);
		}
	}

	/**
	 * Function for preorder traversal *
	 */
	public void preorder() {
		preorder(root);
	}

	private void preorder(Vertex r) {
		if (r != nil) {
			System.out.print(r.vertexId + " " + r.adjacencyList);
			preorder(r.left);
			preorder(r.right);
		}
	}

	/**
	 * Function for postorder traversal *
	 */
	public void postorder() {
		postorder(root);
	}

	private void postorder(Vertex r) {
		if (r != nil) {
			postorder(r.left);
			postorder(r.right);
			System.out.print(r.vertexId + " ");
		}
	}
}
