/**
 * Created by jbon on 4/24/14.
 */
public class BinaryTreeDepth {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        System.out.println(new BinaryTreeDepth().new Solution().maxDepth(root));

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
        public int maxDepth(TreeNode root) {
            return depth(root, 0);
        }

        private int depth(TreeNode node, int depth) {
            if (node == null) {
                return depth;
            }
            int left = depth(node.left, depth + 1);
            int right = depth(node.right, depth + 1);
            return left > right ? left : right;
        }
    }
}
