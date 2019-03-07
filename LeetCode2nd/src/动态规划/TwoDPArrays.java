package 动态规划;

public class TwoDPArrays {
    /**
     * 801. Minimum Swaps To Make Sequences Increasing
     */
    public int minSwap(int[] A, int[] B) {
        int k_p = 0;
        int k_c = Integer.MAX_VALUE/2;
        int s_p = 1;
        int s_c = Integer.MAX_VALUE/2;

        for(int i = 1; i < A.length; i++){
            if(A[i] > A[i-1] && B[i] > B[i-1]){
                k_c = k_p;
                s_c = s_p + 1;
            }

            if(A[i] > B[i-1] && B[i] > A[i-1]){
                k_c = Math.min(k_c, s_p);
                s_c = Math.min(s_c, k_p + 1);
            }
            k_p = k_c;
            s_p = s_c;
            k_c = Integer.MAX_VALUE/2;
            s_c = Integer.MAX_VALUE/2;
        }
        return Math.min(k_p, s_p);
    }

    /**
     * 790. Domino and Tromino Tiling
     * 3种形态->3个DP数组相互作用
     */
    public int numTilings(int N) {
        int MOD = 1000000007;
        //注意：这边需要使用long型
        if (N == 0) return 0;
        if (N == 1) return 1;
        long[][] dp = new long[N][2];
        dp[0][0] = 1;
        dp[0][1] = 0;
        dp[1][0] = 2;
        dp[1][1] = 1;
        for (int i = 2; i < N; i++) {
            dp[i][0] = (dp[i-1][0] + dp[i-2][0] + 2 * dp[i-1][1]) % MOD;
            dp[i][1] = (dp[i-2][0] + dp[i-1][1]) % MOD;
        }
        return (int)dp[N - 1][0] % MOD;
    }
}
