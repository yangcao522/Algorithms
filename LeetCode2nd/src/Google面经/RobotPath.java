package Google面经;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RobotPath {
    /**
     * Rules：
     * 1. 从左上角走到右上角
     * 2. 机器人只能走右上，右和右下
     * 思路：
     * 按照列dp, dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1] + dp[i + 1][j - 1]， 注意i-1，i+1需要存在
     */
    public int uniquePaths(int M, int N) {
        int[] prev = new int[M];
        int[] cur = new int[M];
        prev[0] = 1;
        for (int j = 1; j < N; j++) {
            for (int i = 0; i < M; i++) {
                int a = i - 1 >= 0 ? prev[i - 1] : 0;
                int b = prev[i];
                int c = i + 1 < M ? prev[i + 1] : 0;
                cur[i] = a + b + c;
            }
            System.arraycopy(cur, 0, prev, 0, M);
        }
        return cur[0];
    }

    /**
     * followup 1
     * 给定矩形里的三个点，判断是否存在遍历这三个点的路径
     */
    public boolean canReach(int[][] points) {
        List<int[]> list = new ArrayList<>();
        list.add(new int[] {0, 0});
        for(int[] point : points) list.add(point);
        Collections.sort(list, (a, b) -> (a[1] - b[1]));
        for(int i = 1 ; i < list.size() ; i++) {
            int[] curr = list.get(i);
            int[] prev = list.get(i-1);
            if(curr[1] == prev[1]) return false;
            int len = curr[1] - prev[1];
            int upper = prev[0] - len;
            int lower = prev[0] + len;
            if(curr[0] <= lower && curr[0] >= upper) continue;
            else return false;
        }
        return true;
    }

    /**
     * followup 2
     * 给定一个下界(x == H)，找到能经过给定下界的所有从左上到右上的路径数量 (x >= H)
     * 1.先dp一遍，得到所有到右上的路径数量
     * 2.然后在 0<=x<=H, 0<=y<=cols 这个小矩形中再DP一遍得到不经过下界的所有路径数量
     * 3.两个结果相减得到最终路径数量
     */
    public int uniquePaths(int rows, int cols, int H) {
        return uniquePaths(rows, cols) - uniquePaths(H, cols);
    }


}
