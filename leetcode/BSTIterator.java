//https://leetcode.com/problems/binary-search-tree-iterator/
import java.util.Deque;
import java.util.LinkedList;

class BSTIterator {

    Deque<TreeNode> stack = new LinkedList<>();

    public BSTIterator(TreeNode root) {
        traverseLeft(root);
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode current = stack.pop();
        traverseLeft(current.right);
        return current.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    private void traverseLeft(TreeNode node) {
        if (node != null) {
            stack.push(node);
            traverseLeft(node.left);
        }
    }
}

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}


/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */