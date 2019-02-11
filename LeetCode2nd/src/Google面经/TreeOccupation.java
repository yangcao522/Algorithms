package Google面经;

public class TreeOccupation {
    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    private TreeNode redParent = null;
    private TreeNode red = null;
    public TreeNode findNode(TreeNode root, TreeNode redNode) {
        if (root == null) return null;
        this.red =  redNode;
        int left = count(redNode.left);
        int right = count(redNode.right);
        int parent = count(root);
        int max = Math.max(Math.max(left, right), parent);
        if (max == left) return redNode.left;
        else if (max == right) return redNode.right;
        else return redParent;
    }

    private int count(TreeNode node) {
        if (node.left == red || node.right == red) redParent = node;
        if (node == null || node == red) return 0;
        return 1 + count(node.left) + count(node.right);
    }
}
