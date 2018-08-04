package 二分搜索;

public class LISProblem {
	/**
	 * 300. Longest Increasing Subsequence
	 * 这是比较暴力的DP算法
	 * O(N^2)
	 */
	public int lengthOfLIS(int[] nums) {
        int[] L = new int[nums.length];
        int max = 0;
        for(int i = 0; i < nums.length; i++){
            L[i] = 1;
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    L[i] = Math.max(L[i], L[j] + 1);
                }
            }
            max = Math.max(max, L[i]);
        }
        return max;
    }
	/**
	 * follow up:
	 * 通过binary search 进行优化
	 * 实际上，在对于i往前查找的过程中，只要将nums[i]和之前LIS的最小结尾就可以。
	 * 原来是将nums[i]与之前每一个小于他的数进行比较，从而更新当前的最大长度。
	 * 通过一个tail数组，这个数组存放sequence长度为i+1的最小结尾，通过二分搜索，可以确定当前的数可以贴到哪个结尾后面：
	 * tails is an array storing the smallest tail of all increasing subsequences with length i+1 in tails[i]
	 * (1) if x is larger than all tails, append it, increase the size by 1
	 * (2) if tails[i-1] < x <= tails[i], update tails[i]
	 */
	public int lengthOfLISBS(int[] nums) {
	    int[] tails = new int[nums.length];
	    int size = 0;
	    for (int x : nums) {
	        int i = 0, j = size;
	        while (i != j) {
	            int m = (i + j) / 2;
	            if (tails[m] < x) 
	            		i = m + 1;
	            else 
	            		j = m;
	        }
	        tails[i] = x;
	        if (i == size) size++;
	    }
	    return size;
	}
}
