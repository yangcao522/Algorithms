package 动态规划;

public class Interval_Type {
	/**
	 * 1. 求一段区间的解 min/max/count
	 * 2. 相比划分类 DP，区间类 DP 为连续相连的 subproblem，中间不留空，更有 divide & conquer 的味道。
	 * 3. 转移方程通过区间更新
	 * 4. 从大到小的更新
	 * 对于任意 size (i , j) 的向量区间，我们都可以遍历所有合理 k 的切分点，实现记忆化的 divide & conquer，当前区间的最优解一定由其最优子区间拼接而成。
	 */
	
	/**
	 * 132. Palindrome Partitioning II
	 */
	public int minCut(String s) {
        int n = s.length();
        int [] dp = new int[n+1];
        // initialize
        //长度为i的substring最多需要i-1划分
        for(int i = 0; i <= n; i++){
            dp[i] = i - 1;
        }
        for(int i = 0; i <= n; i++){
            //odd
            for(int j = 0; i - j >= 0 && i + j < n && s.charAt(i - j) == s.charAt(i + j); j++)
                dp[i + j + 1] = Math.min(dp[i + j + 1], 1 + dp[i - j]);
            //even
            for(int j = 1; i - j + 1 >= 0 && i + j < n && s.charAt(i - j + 1) == s.charAt(i + j); j++)
                dp[i + j + 1] = Math.min(dp[i + j + 1], 1 + dp[i- j + 1]);
        }
        return dp[n];
    }
	
	/**
	 * 312. Burst Balloons
	 *  nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
   	 *  coins =  3*1*5    +   3*5*8    +   1*3*8     + 1*8*1  = 167
	 */
	public int maxCoins(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;
        int n = nums.length;
        //重新初始化，两头加上一个1
        int[] ballon = new int[n+2];
        ballon[0] = 1;
        int k = 1;
        for(int num : nums) 
        		if(num > 0) 
        			ballon[k++] = num;
        ballon[k++] = 1;
        
        int[][] dp = new int[k][k];
        
        for(int j = 2; j < k; j++){//the diff between right and left
            for(int left = 0; left < k - j; left ++){//the left boundary & right boundary
                int right = left + j;
                for(int index = left + 1; index < right; index ++){
                    dp[left][right] = Math.max(dp[left][right], ballon[left] * ballon[index] * ballon[right] + dp[left][index] + dp[index][right]);
                }
            }
        }
        return dp[0][k-1];
    }
	
	/**
	 * 87. Scramble String
	 */
	public boolean isScramble(String s1, String s2) {
        if(s1.equals(s2)) return true;
        int[] count = new int[26];
        for(int i = 0; i < s1.length(); i++){
            count[s1.charAt(i) - 'a'] ++;
            count[s2.charAt(i) - 'a'] --;
        }
        //这边用来剪枝，降低递归深度。
        for(int i = 0; i < 26; i++){
            if(count[i] != 0) return false;
        }
        
        for(int i = 1; i <= s1.length() - 1; i++){
            if(isScramble(s1.substring(0, i), s2.substring(0, i)) 
               && isScramble(s1.substring(i), s2.substring(i)))
                return true;
            if(isScramble(s1.substring(0, i), s2.substring(s1.length() - i)) 
               && isScramble(s1.substring(i), s2.substring(0, s1.length() - i)))
                return true;
        }
        return false;
    }
}
