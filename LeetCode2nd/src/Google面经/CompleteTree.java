package Google面经;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CompleteTree {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
    }
    private TreeNode buildCompleteTree(int N){
        Queue<TreeNode> q = new LinkedList<>();
        int[] nums = new int[N];
        for(int i = 0; i < N; i++) {
            nums[i] = i + 1;
        }
        TreeNode node = new TreeNode(nums[0]);
        TreeNode root = node;
        q.offer(node);
        while(!q.isEmpty()){
            TreeNode cur = q.poll();
            int index = cur.val - 1;
            int left = index * 2 + 1;
            if(left < N) {
                TreeNode leftNode = new TreeNode(nums[left]);
                cur.left = leftNode;
                q.offer(leftNode);
            }
            int right = index * 2 + 2;
            if(right < N){
                TreeNode rightNode = new TreeNode(nums[right]);
                cur.right = rightNode;
                q.offer(rightNode);
            }
        }
        return root;
    }

    public boolean isExist(TreeNode root, int val){
        List<Integer> list = new ArrayList<>();
        while(val != 1){
            list.add(val);
            val /= 2;
        }
        int index = list.size() - 1;
        TreeNode node = root;
        for(; index >= 0; index --){
            if(node == null) return false;
            int num = list.get(index);
            if(node.left != null && num == node.left.val) {
                node = node.left;
            } else if (node.right != null && num == node.right.val){
                node = node.right;
            } else {
                return false;
            }
        }
        return true;
    }

    public int getNum(TreeNode node){
        if(node == null) return 0;
        int left = getHeight(node.left);
        int right = getHeight(node.right);
        if(left == right){
            return (1 << left) + getNum(node.right);
        } else {
            return (1 << right) + getNum(node.left);
        }
    }

    private int getHeight(TreeNode node){
        if(node == null) return 0;
        return 1 + getHeight(node.left);
    }

    public static void main(String[] args){
        CompleteTree ct = new CompleteTree();
        TreeNode root = ct.buildCompleteTree(8);
        System.out.println(ct.isExist(root, 9));
        System.out.println(ct.isExist(root, 10));
        System.out.println(ct.getNum(root));
    }
}
