package 动态规划;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Game_Type {
	/**
	 * 博弈类的动态规划
	 */
	
	/**
	 * lintcode : 394. Coins in a Line
	 * http://www.lintcode.com/en/problem/coins-in-a-line/
	 */
	public boolean firstWillWin(int n) {
        // write your code here
        if(n == 0) return false;
        if(n <= 2) return true;

        boolean[] dp = new boolean[n + 1];
        dp[0] = false;
        dp[1] = true;
        dp[2] = true;

        for(int i = 3; i <= n; i++){
            //这个表达式的含义是：还剩下i个coin的时候，第一个玩家先取能不能赢。
			//当前玩家可以取1个或者2个。对方玩家分别剩下i-1和i-2个，如果对方玩家在这两种情况下都能赢，那当前玩家就输了。
			//只要对方玩家有一个情况赢不了，那么当前玩家就赢了。
			dp[i] = (!dp[i - 1]) || (!dp[i - 2]);
        }
        return dp[n];
    }
	
	/**
	 * lintcode : 395. Coins in a Line II
	 * coin都有价值，获得较大价值的玩家赢。
	 * 这个有种minmax的思想在里面
	 * case1:我取一个coin.
	 * 		1.对手拿一个，那我下次的子问题变成f(n-2)
	 * 		2.对手拿两个，那我下次的子问题变成f(n-3)
	 * case2:我取两个coin.
	 * 		1.对手拿一个，那我下次的子问题变成f(n-3)
	 * 		2.对手拿两个，那我下次的子问题变成f(n-4) 
	 */
	public boolean firstWillWin(int[] values) {
		int n = values.length;
		if(n <= 2) return true;
		
		int[] dp = new int[n + 1];
		Arrays.fill(dp, -1);
		int sum = 0;
		for(int v : values) {
			sum += v;
		}
		return search(n, dp, values, n) * 2 > sum;
	}
	
	public int search(int coins, int[] dp, int[] values, int n) {
		if(coins < 0) {
			return 0;
		}
		if(dp[coins] != -1) return dp[coins];
		int takeOne = values[n - coins] + Math.min(search(coins - 2, dp, values, n), search(coins - 3, dp, values, n));
		int takeTwo = values[n - coins] + values[n - coins + 1] + Math.min(search(coins - 3, dp, values, n), search(coins - 4, dp, values, n));
		int max = Math.max(takeOne, takeTwo);
		dp[coins] = max;
		return max;
	}
	
	/**
	 * 375. Guess Number Higher or Lower II
	 * 这一题和上面一题比较相似。
	 * 这里使用dp[i][j]表示k在区间[i, j]中，要确保可能赢所花的最小cost
	 * 1. k 就是答案，此时子问题的额外 cost = 0 ，当前位置总 cost = k + 0;
   	 * 2. k 过大，此时我们的有效区间缩小为 [i , k - 1] 当前操作总 cost = k + dp[i][k - 1];
   	 * 3. k 过小，此时我们的有效区间缩小为 [k + 1 , j] 当前操作总 cost = k + dp[k + 1][j];
   	 * top-down
   	 * bottom-up
	 */
	public int getMoneyAmountBottom_Up(int n) {
        if(n == 0)
            return 0;
        //int res = 0;
        int [][] dp = new int[n+2][n+2];
        for(int gap = 1; gap < n; gap ++){
            for(int i = 1; i + gap <= n; i++){
                int j = gap + i;
                dp[i][j] = Integer.MAX_VALUE;
                for(int k = i; k <= j; k++){
                    //if(i <= k-1 && k+1 <= j){
                    dp[i][j] = Math.min(dp[i][j], k + Math.max(dp[i][k-1], dp[k+1][j]));
                    //}
                }
            }
        }
        return dp[1][n];
    }
	
	public int getMoneyAmountTop_Down(int n) {
        // dp[i][j] min cost to guarantee to win from interval [i , j]
        return getMinCost(0, n - 1, new int[n][n]);
    }

    private int getMinCost(int start, int end, int[][] dp){
        if(start >= end) return 0;

        if(dp[start][end] != 0) return dp[start][end];

        int minCost = Integer.MAX_VALUE;
        for(int i = start; i < end; i++){
            minCost = Math.min(minCost,  (i + 1) + Math.max(getMinCost(start, i - 1, dp), //下标为(i)的值是i+1
                                                            getMinCost(i + 1, end, dp)));
        }
        dp[start][end] = minCost;

        return dp[start][end];
    }
	
    /**
     * 464. Can I Win
     */
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if(desiredTotal <= 0) return false;
        if((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) return false;
        boolean[] visited = new boolean[maxChoosableInteger + 1];
        Map<String, Boolean> map = new HashMap<>();
        return helper(map, desiredTotal, visited);
    }
    
    private boolean helper(Map<String, Boolean> map, int desiredTotal, boolean[] visited) {
        if(desiredTotal < 0) return false;
        String key = format(visited);
        if(map.containsKey(key)) {
            return map.get(key);
        }
        for(int i = 1; i < visited.length; i++) {
            if(visited[i] == false) {
                visited[i] = true;
                if(!helper(map, desiredTotal - i, visited)) {
                    map.put(key, true);
                    visited[i] = false;
                    return true;
                }
                visited[i] = false;
            }
        }
        map.put(key, false);
        return false;
    }
    
    private String format(boolean[] visited) {
    		StringBuilder sb = new StringBuilder();
    		for(int i = 1; i < visited.length; i++) {
    			if(visited[i] == true) {
    				sb.append("#");
    			}else {
    				sb.append(i);
    			}
    		}
    		return sb.toString();
    }

}





