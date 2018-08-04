package Iterator设计;

import java.util.Stack;

class TreeNode{
	TreeNode left;
	TreeNode right;
	int val;
	public TreeNode(int val) {
		this.val = val;
	}
}
public class BSTIterator {
	Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        while(root != null) {
        		stack.push(root);
        		root = root.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
    		TreeNode next = stack.pop();
    		int res = next.val;
    		TreeNode cur = next.right;
    		while(cur != null) {
    			stack.push(cur);
    			cur = cur.left;
    		}
        return res;
    }
}
