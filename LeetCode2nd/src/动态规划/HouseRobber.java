package 动态规划;

public class HouseRobber {
	/**
	 * 入门级动态规划
	 */
	
	/**
	 * 198. House Robber I
	 * 当前的抢劫最大profit(n)只和profit(n-1)和profit(n-2)有关。
	 */
	public int robI_1(int[] nums) {
        if(nums.length == 0)
            return 0;
        int pre = 0;//只用两个变量交替更新
        int cur = 0;
        for(int i = 0; i < nums.length ; i++){
        		int tmp = cur;
        		cur = Math.max(pre + nums[i], cur);
        		pre = tmp;
        }
        return cur;
    }
	/**
	 * 198. House Robber I
	 * 这个方法对这一题并无实际意义。主要是circular buffer的应用。
	 */
	public int robI_2(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.max(nums[0], nums[1]);
        
        int[] dp = new int[2];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        
        //这里就是circular buffer的使用
        for(int i = 2; i < nums.length; i++){
            dp[i % 2] = Math.max(dp[(i - 2) % 2] + nums[i], dp[(i - 1) % 2]);
        }
        return dp[(nums.length - 1) % 2];
    }
	/**
	 * 213. House Robber II
	 * 首位相连，那么0和n-1是相连的两个house，所以整个问题要切换成两个问题：
	 * 1）0 ~ n-2 最大profit
	 * 2）1 ~ n-1 最大profit
	 * 最后在这两个里面选一个最大的
	 */
	public int robII(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        if(nums.length == 1)
            return nums[0];
        int pre1 = 0, pre2 = 0, cur1 = 0, cur2 = 0;
        for(int i = 0; i < nums.length - 1; i++){
            int tmp1 = cur1;
            cur1 = Math.max(cur1, pre1 + nums[i]);
            pre1 = tmp1;
            
            int tmp2 = cur2;
            cur2 = Math.max(cur2, pre2 + nums[i+1]);
            pre2 = tmp2;
        }
        return Math.max(cur1, cur2);
    }
	/**
	 * 337. House Robber III
	 * 没有什么特殊技巧
	 */
	class  TreeNode{
		TreeNode left;
		TreeNode right;
		int val;
		public TreeNode(int val) {
			this.val = val;
		}
	}
	public int robIII(TreeNode root) {
        if(root == null)
            return 0;
        int res = root.val; 
        int left = 0;
        int right = 0;
        if(root.left != null){
            left = robIII(root.left);
            res += robIII(root.left.left);
            res += robIII(root.left.right);
        }
        if(root.right != null){
            right = robIII(root.right);
            res += robIII(root.right.left);
            res += robIII(root.right.right);
        }
        return Math.max(res, left + right);
    }
}
