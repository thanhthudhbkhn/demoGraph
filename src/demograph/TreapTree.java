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
    private static Vertex nil = new Vertex(-1);

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

    public void update(Vertex T) {
        if (T.left != nil && T.left.deg < T.deg) {
            Vertex tmp = T.left;

            Vertex T2 = new Vertex(T.vertexId);
            T2.deg = T.deg;
            T2.adjacencyList = T.adjacencyList;
            T2.left = tmp.right;
            T2.right = T.right;

            T.vertexId = tmp.vertexId;
            T.deg = tmp.deg;
            T.adjacencyList = tmp.adjacencyList;
            T.left = tmp.left;
            T.right = T2;

            update(T2);
        }

        if (T.right != nil && T.right.deg < T.deg) {
            Vertex tmp = T.right;

            Vertex T2 = new Vertex(T.vertexId);
            T2.deg = T.deg;
            T2.adjacencyList = T.adjacencyList;
            T2.right = tmp.left;
            T2.left = T.left;

            T.vertexId = tmp.vertexId;
            T.deg = tmp.deg;
            T.adjacencyList = tmp.adjacencyList;
            T.right = tmp.right;
            T.left = T2;

            update(T2);
        }
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
            if (T.left.deg < T.deg) {
                Vertex L = T.left;
                T.left = L.right;
                L.right = T;
                return L;
            }
        } else if (X.vertexId > T.vertexId) {
            T.right = insert(X, T.right);
            if (T.right.deg < T.deg) {
                Vertex R = T.right;
                T.right = R.left;
                R.left = T;
                return R;
            }
        }
        return T;
    }

    /**
     * Functions to delete data *
     */
    public void delete(Vertex X) {
        root = delete(X, root);
    }

    private Vertex delete(Vertex X, Vertex T) {
        //TREE is empty
        if (T == nil) {
            return nil;
        }
        // IF KEYS IS NOT AT ROOT THEN CONTINUE
        if (X.vertexId < T.vertexId) {
            T.left = delete(X, T.left);
        } else if (X.vertexId > T.vertexId) {
            T.right = delete(X, T.right);
        } 
        // IF KEY IS AT ROOT 
        // If left is NULL 
        else if (T.left == nil) {
            Vertex temp = T.right;
            T = temp;  // Make right child as root 
        } // If Right is NULL 
        else if (T.right == nil) {
            Vertex temp = T.left;
            T = temp;  // Make left child as root 
        } // If key is at root and both left and right are not NULL
        else if (T.left.deg < T.right.deg) {
            Vertex L = T.left;
            T.left = L.right;
            L.right = T;
            return delete(X, L);
        } else {
            Vertex R = T.right;
            T.right = R.left;
            R.left = T;
            return delete(X, R);
        }
        //------------------------------------------------------------
//        if (X.vertexId < T.vertexId) {
//            T.left = insert(X, T.left);
//            if (T.left.deg < T.deg) {
//                Vertex L = T.left;
//                T.left = L.right;
//                L.right = T;
//                return L;
//            }
//        } else if (X.vertexId > T.vertexId) {
//            T.right = insert(X, T.right);
//            if (T.right.deg < T.deg) {
//                Vertex R = T.right;
//                T.right = R.left;
//                R.left = T;
//                return R;
//            }
//        }
        return T;
    }

    public Vertex deleteNode(Vertex root, Vertex X) {
        // Base case 
        if (root == nil) {
            return root;
        }

        // IF KEYS IS NOT AT ROOT 
        if (X.vertexId < root.vertexId) {
            root.left = deleteNode(root.left, X);
        } else if (X.vertexId > root.vertexId) {
            root.right = deleteNode(root.right, X);
        } // IF KEY IS AT ROOT 
        // If left is NULL 
        else if (root.left == nil) {
            Vertex temp = root.right;
            root = temp;  // Make right child as root 
        } // If Right is NULL 
        else if (root.right == nil) {
            Vertex temp = root.left;
            root = temp;  // Make left child as root 
        } // If ksy is at root and both left and right are not NULL 
        else if (root.left.deg < root.right.deg) {
            System.out.println("con ben trai bac thap hon");
//            root = leftRotate(root);
//            root.left = deleteNode(root.left, X);
        } else {
            System.out.println("con ben phai bac thap hon");
//            root = rightRotate(root);
            Vertex R = X.right;
            X.right = R.left;
            R.left = X;
            root.right = deleteNode(root.right, X);
        }

        return root;
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
            System.out.println(r.vertexId + "-" + r.deg);// + " " + r.adjacencyList);
            System.out.print("left: ");
            preorder(r.left);
            System.out.print("right: ");
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
