import java.util.HashMap;
import java.util.Map;

public class MaxPathSum {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        System.out.println(new MaxPathSum().new Solution().maxPathSum(root));

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
        public int maxPathSum(TreeNode root) {
            Map<TreeNode, Integer> maxDownwardsPaths = new HashMap<>();
            return maxPathSum(root, maxDownwardsPaths);
        }

        private int maxPathSum(TreeNode node, Map<TreeNode, Integer> maxDownwardsPaths) {
            if (node == null) return Integer.MIN_VALUE;
            int leftOptimal = maxPathSum(node.left, maxDownwardsPaths);
            int rightOptimal = maxPathSum(node.right, maxDownwardsPaths);
            int leftMaxDownwardsPath = getMaxDownwardsPath(node.left, maxDownwardsPaths);
            int rightMaxDownwardsPath = getMaxDownwardsPath(node.right, maxDownwardsPaths);
            maxDownwardsPaths.put(node, node.val + max(0, leftMaxDownwardsPath, rightMaxDownwardsPath));
            int newOptimal = node.val + (leftMaxDownwardsPath > 0 ? leftMaxDownwardsPath : 0) + (rightMaxDownwardsPath > 0 ? rightMaxDownwardsPath : 0);
            return max(leftOptimal, rightOptimal, newOptimal);
        }

        private int max(int first, int second, int third) {
            return Math.max(Math.max(first, second), third);
        }

        private int getMaxDownwardsPath(TreeNode node, Map<TreeNode, Integer> maxDownwardsPaths) {
            return (node == null) ? Integer.MIN_VALUE : maxDownwardsPaths.get(node);
        }
    }
}
