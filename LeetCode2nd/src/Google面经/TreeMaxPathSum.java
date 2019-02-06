package Google面经;

import java.util.ArrayList;
import java.util.List;

public class TreeMaxPathSum {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static int maxPathSum(TreeNode root) {
        int[] ans = new int[]{Integer.MIN_VALUE};
        List<Integer> res = new ArrayList<>();
        getMaxPath(root, ans, res, new ArrayList<>());
        for(Integer i : res){
            System.out.println(i);
        }
        return ans[0];
    }

    private static int getMaxPath(TreeNode root, int[] ans, List<Integer> res, List<Integer> tmp){
        if(root == null) return 0;
        List<Integer> path = new ArrayList<>();
        path.add(root.val);
        int l = getMaxPath(root.left, ans, res, tmp);
        int r = getMaxPath(root.right, ans, res, tmp);
        int left = Math.max(0, l);
        int right = Math.max(0, r);
        if(left > right) {
            path.addAll(tmp);
        } else {
            path.addAll(tmp);
        }
        tmp.addAll(path);
        if(left + right + root.val > ans[0]) {
            ans[0] = left + right + root.val;
            res.clear();
            res.add(root.val);
            res.addAll(path);
            tmp.clear();
        }

        return Math.max(left, right) + root.val;
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        root.right.right.right = new TreeNode(6);
        maxPathSum(root);
    }
}
