class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public static void main(String[] args) {
        new ConstructBinaryTreeFromPreorderAndInorderTraversal()
//            .buildTree(new int[] {3,9,20,15,7}, new int[] {9,3,15,20,7});
            .buildTree(new int[] {1,2,3}, new int[] {3,2,1});
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private static TreeNode build(int[] preorder, int preFrom, int preTo, int[] inorder, int inFrom, int inTo) {
        if (preFrom > preTo || inFrom > inTo) {
            return null;
        }
        TreeNode n = new TreeNode(preorder[preFrom]);
        int idx = indexOf(inorder, preorder[preFrom], inFrom, inTo);
        n.left = build(preorder, preFrom + 1, preFrom + idx - inFrom, inorder, inFrom, idx - 1);
        n.right = build(preorder, preFrom + idx - inFrom + 1, preTo, inorder, idx + 1, inTo);
        return n;
    }

    private static int indexOf(int[] a, int val, int from, int to) {
        for(int i = from; i <= to; i++) {
            if (a[i] == val) return i;
        }
        return -1;
    }
}