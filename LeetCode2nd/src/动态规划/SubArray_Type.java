package 动态规划;

public class SubArray_Type {
	/**
	 * Maximum Subarray
	 * 方法一：prefix sum解法
	 * 方法二：kadane's algorithm
	 */
	public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int cur = 0, pre = 0, max = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++) {
        		cur = Math.max(nums[i], pre + nums[i]);
        		pre = cur;
        		max = Math.max(cur, max);
        }
        return max;
    }
	/**
	 * Maximum Subarray II
	 * Given an array of integers, find two non-overlapping subarrays which have the largest sum.
	 * The number in each subarray should be contiguous.
	 * Return the largest sum.
	 * 这一题使用prefix sum逻辑比较清晰。
	 */
	public int maxSubArrayII(int[] nums) {
		if(nums == null || nums.length == 0) return 0;
		int[] left = new int[nums.length];
		int[] right = new int[nums.length];
		
		int prefixSum = 0;
		int minPre = 0;
		int maxLeft = Integer.MIN_VALUE;
		for(int i = 0; i < nums.length; i++) {
			prefixSum += nums[i];
			maxLeft = Math.max(maxLeft, prefixSum - minPre);
			left[i] = maxLeft;
			minPre = Math.min(minPre, prefixSum);
		}
		
		int suffixSum = 0;
		minPre = 0;
		int maxRight = Integer.MIN_VALUE;
		for(int i = nums.length - 1; i >= 0; i--) {
			suffixSum += nums[i];
			maxRight = Math.max(maxRight, suffixSum - minPre);
			minPre = Math.min(minPre, suffixSum);
		}
		int res = Integer.MIN_VALUE;
		for(int i = 0; i < nums.length - 1; i++) {
			res = Math.max(res, left[i] + right[i + 1]);
		}
		return res;
	}
	/**
	 * Maximum Subarray III
	 * 上一题是两个，这一题是K个subarray
	 * 状态：
	 * localMax[i][j] 表示前i个元素，分成j个数组，同时第i个元素必须取，的最大值。
	 * globalMax[i][j] 表示前i个元素，分成j个数组，第i个元素可以取也可以不取。
	 * 方程：
	 * localMax[i][j] = Max(localMax[i-1][j](i与i-1个元素连在一起组成一个subarray), globalMax[i-1][j-1](i自己单独组成一个subarray)) + nums[i];
	 * globalMax[i][j] = Max(localMax[i][j], globalMax[i-1][j])
	 * 初始化：
	 * localMax[j-1][j] = Integer.MAX_VALUE;
	 * 因为j最大和i相等，那么localMax[i][j]的极端情况就是localMax[j][j]，而方程中会出现localMax[j-1][j],????
	 * 
	 */
	public int maxSubArrayIII(int[] nums, int k) {
		if(nums == null || nums.length == 0) return 0;
		int n = nums.length;
		int[][] localMax = new int[n+1][k+1];
		int[][] globalMax = new int[n+1][k+1];
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= i && j <= k; j++) {
				localMax[j-1][j] = Integer.MIN_VALUE;
				localMax[i][j] = Math.max(localMax[i-1][j], globalMax[i-1][j-1]) + nums[i];
				if(i == j)
					globalMax[i][j] = localMax[i][j];
				else
					globalMax[i][j] = Math.max(localMax[i][j], globalMax[i-1][j]);
			}
		}
		return globalMax[n][k];
	}
	
}
















