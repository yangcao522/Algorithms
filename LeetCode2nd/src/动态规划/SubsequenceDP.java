package 动态规划;

import java.util.HashMap;
import java.util.Map;

public class SubsequenceDP {
    /**
     * 873. Length of Longest Fibonacci Subsequence
     * 这种子序列的题目一般都是几层循环DP一下
     * 最长递增子序列，确定当前指针，通过一层内循环，在某种约束条件下，找出最大值。
     */
    public int lenLongestFibSubseq(int[] A) {
        int N = A.length;
        Map<Integer, Integer> map = new HashMap<>();
        int t = 0; for(int a : A) map.put(a, t++);
        Map<Integer, Integer> longest = new HashMap<>();
        int ret = 0;
        for(int k = 0; k < N; k++){
            for(int j = 0; j < k; j++){
                int i = map.getOrDefault(A[k] - A[j], -1);
                if(i >= 0 && i < j){
                    int cand = longest.getOrDefault(i * N + j, 2) + 1;
                    longest.put(j * N + k, cand);
                    ret = Math.max(ret, cand);
                }
            }
        }
        return ret;
    }
}
