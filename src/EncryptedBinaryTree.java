public class EncryptedBinaryTree {
    public static void main(String[] args) {

        int[] bt = {-2, -2, -1, -2, -1};
        int t = 1;

        EncryptedBinaryTree solution = new EncryptedBinaryTree();

        boolean result = solution.findTarget(bt, t);

        System.out.println(result);
    }

    public boolean findTarget(int[] bt, int target) {
        // Start DFS at
        // index = 0 (root of array tree)
        // value = 1 (root value)
        return dfs(bt, 0, 1, target);
    }

    private boolean dfs(int[] bt, int i, int value, int target) {

        // Base cases:
        // If index out of bounds (no node exists) or
        // node is marked -1 (empty node)
        if (i >= bt.length || bt[i] == -1) {
            return false;
        }

        // Check if the current value matches target
        if (value == target) {
            return true;
        }

        // Compute left and right values:
        // left = 3x + 1; right = 2x + 5
        int leftValue = 3 * value + 1;
        int rightValue = 2 * value + 5;

        // Recursively search:
        // left child is at index 2 * i + 1
        // right child is at index 2 * i + 2
        return dfs(bt, 2 * i + 1, leftValue, target)
                || dfs(bt, 2 * i + 2, rightValue, target);
    }
}

/*
Sources:
https://www.geeksforgeeks.org/java/implementing-a-binary-tree-in-java/
https://stackoverflow.com/questions/20756417/recursive-tree-encryption-method-using-dfs
 */