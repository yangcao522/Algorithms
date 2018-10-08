package Iterator设计.Peeking类型;

import java.util.Iterator;
import java.util.Stack;

class TreeNode{
    int val;
    TreeNode left, right;
    public TreeNode(int val){
        this.val = val;
    }
}

/**
 * 这是一道FB面经题，主要是考察二叉树的PostOrder遍历
 */
public class PostOrderIterator implements Iterator<TreeNode> {
    Stack<TreeNode> s1;
    Stack<TreeNode> s2;

    public PostOrderIterator(TreeNode root){
        s1 = new Stack<>();
        s2 = new Stack<>();
        if(null != root){
            s1.push(root);
        }
        while(!s1.isEmpty()){
            TreeNode node = s1.pop();
            s2.push(node);
            if(node.left != null){
                s1.push(node.left);
            }
            if(node.right != null){
                s1.push(node.right);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return !s2.isEmpty();
    }

    @Override
    public TreeNode next() {
        return s2.pop();
    }

    public static void main(String[] args){
        /*
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        node7.left = node3;
        node7.right = node6;
        node3.left = node1;
        node3.right = node2;
        node6.left = node5;
        node5.right = node4;
        */

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        TreeNode node10 = new TreeNode(10);

        node10.left = node5;
        node10.right = node9;
        node5.left = node2;
        node5.right = node4;
        node2.left = node1;
        node4.left = node3;
        node9.left = node6;
        node9.right = node8;
        node8.left = node7;

        PostOrderIterator iter = new PostOrderIterator(node10);

        while(iter.hasNext()){
            System.out.println(iter.next().val);
        }

    }
}
