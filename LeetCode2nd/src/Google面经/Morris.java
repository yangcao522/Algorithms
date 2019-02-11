package Google面经;

public class Morris {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }
    void inOrder(TreeNode root) {
        if(root == null) return;
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode predecessor = findPred(curr);
                if(predecessor.right == null) {
                    //指回根节点
                    predecessor.right = curr;
                    curr = curr.left;
                } else {
                    predecessor.right = null;
                    System.out.print(curr.val);
                    //回到根节点
                    curr = curr.right;
                }
            } else {
                System.out.println(curr.val);
                curr = curr.right;
            }
        }
    }
    TreeNode findPred(TreeNode root) {
        TreeNode curr = root.left;
        while (curr.right != null && curr.right != root) {
            curr = curr.right;
        }
        return curr;
    }
}
