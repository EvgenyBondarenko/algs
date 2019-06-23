import java.util.*;

/** https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/ */
public class LowestCommonAncestorOfBinaryTree {

    public static void main(String[] args) {
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        one.left = two;
        one.right = three;
        two.left = four;
        two.right = five;
        System.out.println(new Solution().lowestCommonAncestor(one, two, five));
    }

    // O(n) time, O(1) space
    static class Solution {

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) return null;
            if (root.val == p.val || root.val == q.val) return root;
            TreeNode leftLca = lowestCommonAncestor(root.left, p, q);
            TreeNode rightLca = lowestCommonAncestor(root.right, p, q);
            if (leftLca != null && rightLca != null) return root;
            return lowestCommonAncestor(leftLca == null ? rightLca : leftLca, p, q);
        }
    }

    // O(n) time, 0(logn) space. Much slower, presumably because the two lists and operations on them
    static class Solution1 {

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) return null;
            List<TreeNode> pPath = path(root, p);
            List<TreeNode> qPath = path(root, q);
            int i = 0;
            for (; i < pPath.size() && i < qPath.size(); i++) {
                if (pPath.get(i).val != qPath.get(i).val) break;
            }
            return pPath.get(i-1);
        }

        public List<TreeNode> path(TreeNode root, TreeNode n) {
            LinkedList<TreeNode> path = new LinkedList<>();
            path(root, n, path);
            return path;
        }

        private boolean path(TreeNode root, TreeNode n, LinkedList<TreeNode> path) {
            if (root == null) return false;
            path.add(root);
            if (root.val == n.val) return true;
            if (path(root.left, n, path) || path(root.right, n, path)) return true;
            path.removeLast();
            return false;
        }
    }

    static public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }
}
