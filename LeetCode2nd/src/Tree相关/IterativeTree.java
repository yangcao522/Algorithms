package Tree相关;

import java.util.*;

public class IterativeTree {
    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * Post Order遍历
     * 145. Binary Tree Postorder Traversal
     * https://www.youtube.com/watch?v=A6iCX_5xiU4
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<Integer> q = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            q.addFirst(node.val);
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }
        return q;
    }
}
