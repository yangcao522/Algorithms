package 动态规划;

public class Matrix {
	/**
	 * 63. Unique Paths II
	 * 这一题之前的思路一直是2维数组来解决
	 * 实际上可以通过一维数组来解决
	 * dp[j]表示走到当前行，第j列一共的不同路径。
	 */
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(obstacleGrid[i][j] == 1){
                    dp[j] = 0;
                }else if(j > 0){
                    dp[j] = dp[j] + dp[j-1];//这里的j实际上就是上一行的j，这里的j-1就是当前这一行的j-1
                }
            }
        }
        return dp[n-1];
    }
	
	/**
	 * 221. Maximal Square
	 * 注意，这里是maximal square不是rectangle。
	 * 这里同上面的方法一样，可以通过一个一维矩阵进行优化。
	 */
	public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int max = 0;
        for(int i = 0; i < n; i++){
            if(matrix[0][i] == '1') {
                dp[0][i] = 1;  
                max = 1;
            }
        }
        for(int i = 0; i < m; i++){
            if(matrix[i][0] == '1') {
                dp[i][0] = 1;
                max = 1;
            }
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][j] == '1')
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                max = Math.max(max, dp[i][j]);
            }
        }
        return max*max;
    }
}
