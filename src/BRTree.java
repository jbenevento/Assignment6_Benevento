public class BRTree {

    public static void main(String[] args) {

        BRTree brt = new BRTree();

        int[] values = {10, 19, 20, 30, 42, 55, 77};

        for (int v : values) {
            brt.insert(v);
        }

        System.out.println("Original:");
        brt.inorder();

        brt.inRange(15, 20);
        System.out.println("inRange(15,20):");
        brt.inorder(); // 10 30 42 55 77

        brt.inRange(25, 60);
        System.out.println("inRange(25,60):");
        brt.inorder(); // 10 77
    }

    class Node {
        int key;
        Node left, right, parent;
        boolean color; // true = red, false = black

        Node(int key) {
            this.key = key;
        }
    }

    Node root;

    public void insert(int key) {
        root = insertNode(root, key, null);
    }

    private Node insertNode(Node root, int key, Node parent) {

        // Check for an empty spot, create new node
        if (root == null) {
            Node node = new Node(key);
            node.parent = parent;
            node.color = true; //red by default
            return node;
        }


        if (key < root.key) {   // If key less than root, go left
            root.left = insertNode(root.left, key, root);
        } else if (key > root.key) {    // If key greater than root, go right
            root.right = insertNode(root.right, key, root);
        }

        return root; // Return unchanged node
    }

    public void delete(int key) {
        root = deleteBST(root, key);
    }

    private Node deleteBST(Node node, int key) {

        // Base Case: Key not found
        if (node == null) return null;

        if (key < node.key) {   // If key less than root, go left
            node.left = deleteBST(node.left, key);
        } else if (key > node.key) {    // If key greater than root, go right
            node.right = deleteBST(node.right, key);
        } else { // Node found

            if (node.left == null) return node.right; // No left child
            if (node.right == null) return node.left; // No right child

            // Left & right children
            Node successor = node.right; // Right subtree
            while (successor.left != null) { // Find smallest value in the right subtree
                successor = successor.left;
            }

            node.key = successor.key; // Replace cuurent node with successor value
            node.right = deleteBST(node.right, successor.key); // Delete successor
        }

        return node; // Return updated subtree
    }

    public void inRange(int a, int b) {
        inRangeHelper(root, a, b);
    }

    private void inRangeHelper(Node node, int a, int b) {

        //Base Case: empty subtree
        if (node == null) return;

        inRangeHelper(node.left, a, b); // Visit left subtree
        inRangeHelper(node.right, a, b); // Visit right subtree

        // Check if node is in range
        if (node.key >= a && node.key <= b) {
            delete(node.key); // Delete the node
        }
    }

    public void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(Node node) {

        // Base Case: empty node
        if (node == null) return;

        inorder(node.left); // Visit left subtree
        System.out.print(node.key + " "); // Print current node
        inorder(node.right); // Visit right subtree
    }

}

/*
Sources:
ChatGPT
https://www.geeksforgeeks.org/dsa/introduction-to-red-black-tree/
https://www.geeksforgeeks.org/java/red-black-tree-java/
https://github.com/Arsenalist/Red-Black-Tree-Java-Implementation/blob/master/src/RedBlackTree.java
https://zarif98sjs.github.io/blog/blog/redblacktree/
https://medium.com/@YodgorbekKomilo/red-black-tree-algorithm-in-java-balanced-search-with-a-touch-of-color-%EF%B8%8F-f6c1d0132b57
 */