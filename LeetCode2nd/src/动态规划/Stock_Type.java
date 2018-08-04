package 动态规划;

public class Stock_Type {
	/**
	 * Best Time to Buy and Sell Stock
	 */
	public int maxProfitI(int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        int preMin = Integer.MAX_VALUE;
        int res = 0;
        for(int i = 0; i < prices.length; i++){
            res = Math.max(res, prices[i] - preMin);
            preMin = Math.min(preMin, prices[i]);
        }
        return res;
    }
	
	/**
	 * Best Time to Buy and Sell Stock II
	 * 把每次incerease的add在一起
	 */
	public int maxProfitII(int[] prices) {
		if(prices == null || prices.length == 0) return 0;
		int preMin = Integer.MAX_VALUE;
		int res = 0;
		for(int i = 0; i < prices.length; i++) {
			if(prices[i] > preMin) {
				res += (prices[i] - preMin);
			}
			preMin = prices[i];
		}
		return res;
	}
	
	/**
	 * Best Time to Buy and Sell Stock III
	 * 最多可以交易两次
	 * 实际上就是max subarray II 的改编
	 */
	public int maxProfitIII(int[] prices) {
		if(prices == null || prices.length == 0) return 0;
		//第i天卖的最大收益
		int[] left = new int[prices.length];
		//第i天买的最大收益
		int[] right = new int[prices.length];
		
		int localMin = Integer.MAX_VALUE;
		int globalMax = 0;
		for(int i = 0; i < prices.length; i++) {
			globalMax = Math.max(globalMax, Math.max(0, prices[i] - localMin));
			left[i] = globalMax;
			localMin = Math.min(localMin, prices[i]);
		}
		
		int localMax = 0;
		globalMax = 0;
		for(int i = prices.length - 1; i >= 0; i++) {
			globalMax = Math.max(globalMax, Math.max(0, localMax - prices[i]));
			right[i] = globalMax;
			localMin = Math.max(localMax, prices[i]);
		}
		
		//假设交易两次
		int res = 0;
		for(int i = 0; i < prices.length - 1; i++) {
			res = Math.max(res, left[i] + right[i + 1]);
		}
		res = Math.max(res, left[prices.length-1]);
		
		return res;
	}
	
	/**
	 * Best Time to Buy and Sell Stock IV
	 * 
	 */
	
}
