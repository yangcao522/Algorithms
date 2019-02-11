package Google面经;

public class DominondAndTrominoTiling {
    /**
     * 中国大哥
     * 给一个2*n的board，每个格子有两种情况，为空或者被block了。现在有大小为1*2的多米诺骨牌，问这个board最多能放多少个骨牌
     *
     * 思路：
     * 动态规划
     * dp[i] 表示[0, i] 的最多骨牌
     * case 1: i列上没有block
     * 	dp[i] = dp[i - 1] + 1;
     * 	case 1.1: 如果dp[i-1]也没有block
     * 		dp[i] = max(dp[i], dp[i-2] + 2)
     * case 2: i列上有一个block
     * 	dp[i] = dp[i-1]
     * 	case 1.1: 如果i-1列上没有block or i-1上有一个block在同侧
     * 		dp[i] = max(dp[i], dp[i-2] + 1)
     * case 3: i列上有两个block
     * 	dp[i] = dp[i - 1]
     */

}
