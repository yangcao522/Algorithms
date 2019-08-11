package 动态规划;

public class BestTimeToBuyStock {
	/**
	 * 121. Best Time to Buy and Sell Stock
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
	 * 122. Best Time to Buy and Sell Stock II
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
	 * 123. Best Time to Buy and Sell Stock III
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
			localMax = Math.max(localMax, prices[i]);
		}
		
		//假设交易两次
		int res = 0;
		for(int i = 0; i < prices.length - 1; i++) {
			res = Math.max(res, left[i] + right[i + 1]);
		}
		//有可能只交易一次利润更大
		res = Math.max(res, left[prices.length-1]);
		
		return res;
	}
	
	/**
	 * Best Time to Buy and Sell Stock IV
	 * 最多交易K次
	 */
	public int maxProfitIV(int K, int[] prices) {
		if (prices == null || prices.length == 0) return 0;
		int N = prices.length;
		if (K > N / 2) {
			int profit = 0;
			for (int i = 1; i < N; i++) {
				profit += Math.max(0, prices[i] - prices[i - 1]);
			}
			return profit;
		}

		//在第i天卖出股票，最多交易了k次，所获得的最大收益
		int[][] localMax = new int[N][K + 1];
		//在前i天卖出股票，最多交易了k次，所获得的最大收益
		int[][] globalMax = new int[N][K + 1];
		for (int i = 1; i < N; i++) {
			int diff = prices[i] - prices[i - 1];
			for (int j = 1; j < K + 1 && j * 2 <= i + 1; j ++) {
				//在i点，进行最后一次卖出，所获得的最大收益
				localMax[i][j] = Math.max(localMax[i - 1][j], globalMax[i - 1][j - 1]) + diff;
				//在i点或i点之前的交易中，所获得的最大收益
				globalMax[i][j] = Math.max(localMax[i][j], globalMax[i - 1][j]);
			}
		}

		return globalMax[N - 1][K];
	}

	/**
	 * 309. Best Time to Buy and Sell Stock with Cooldown
	 * 卖完之后有一天冷冻期
	 */
	public int maxProfitCoolDown(int[] prices) {
		if(prices == null || prices.length <= 1) return 0;

		int N = prices.length;

		// max money in hand after each action
		int[] sell = new int[N];
		int[] donothing = new int[N];

		sell[1] = prices[1] - prices[0];

		for(int i = 2; i < N; i++){
			donothing[i] = Math.max(donothing[i - 1], sell[i - 1]);
			sell[i] = prices[i] - prices[i - 1] + Math.max(sell[i - 1], donothing[i - 2]);
		}

		return Math.max(sell[N - 1], donothing[N - 1]);
	}
}
