package 动态规划;

public class Paint {
	/**
	 * 276. Paint Fence
	 * 两种情况：
	 * 1）当前n要paint的颜色和之前的相同
	 * 若之前两个同色： 不可能
	 * 若之前两个异色： * k-1
	 * 2）当前n要paint的颜色和之前的不同
	 * 若之前两个同色： * k-1
	 * 若之前两个异色： * k-1
	 */
	public int numWays(int n, int k) {
        if(n == 0) return 0;
        if(n == 1) return k;
        int caseSame = k;
        int caseDiff = k*(k-1);
        for(int i = 3; i <= n; i++){
            int tmp = caseDiff;
            caseDiff = caseSame * (k - 1) + caseDiff * (k - 1);
            caseSame = tmp;
        }
        return caseSame + caseDiff;
    }
	
	/**
	 * 256. Paint House I 
	 * 一共有3种颜色
	 * 
	 */
	public int minCost(int[][] costs) {
        if(costs == null || costs.length == 0)
            return 0;
        int [] pre = new int[]{costs[0][0], costs[0][1], costs[0][2]};
        int [] cur = new int[3];
        for(int i = 1; i < costs.length; i++){
            cur[0] = Math.min(pre[1], pre[2]) + costs[i][0];
            cur[1] = Math.min(pre[0], pre[2]) + costs[i][1];
            cur[2] = Math.min(pre[0], pre[1]) + costs[i][2];
            for(int j = 0; j < 3; j++){
                pre[j] = cur[j];
            }
        }
        return Math.min(Math.min(pre[0], pre[1]), pre[2]);
    }
	/**
	 * 265. Paint House II
	 * 这里一共costs[0].length种颜色
	 * 时间复杂度 O(nk) ，空间复杂度 O(1)
	 */
	public int minCostII(int[][] costs) {
        if(costs == null || costs.length == 0) return 0;
        int premin = 0;
        int presecMin = 0;
        int preminIndex = -1;
        for(int i = 0; i < costs.length; i++){
            int min = Integer.MAX_VALUE;
            int secMin = Integer.MAX_VALUE;
            int minIndex = -1;
            for(int j = 0; j < costs[0].length; j++){
                int val = costs[i][j] + (j == preminIndex ? presecMin : premin);
                //这里相当于初始化
                if(minIndex < 0){
                    min = val;
                    minIndex = j;
                }else if(val < min){
                    secMin = min;
                    min = val;
                    minIndex = j;
                }else if(val < secMin){
                    secMin = val;
                }
            }
            premin = min;
            presecMin = secMin;
            preminIndex = minIndex;
        }
        return premin;
    }
}
