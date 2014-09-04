/**
 * Created by jbon on 4/24/14.
 */
public class BinaryTreeEquality {

    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        a.right = new TreeNode(2);
        TreeNode b = new TreeNode(1);
        b.right = new TreeNode(2);
        System.out.println(new BinaryTreeEquality().new Solution().isSameTree(a, b));

    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Solution {
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == q) return true;
            if ((p != null && q == null) || (p == null && q != null)) return false;
            if (p.val != q.val) return false;
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }
}
