package 数学符号;

import java.util.ArrayList;
import java.util.List;

public class Operators {

    /**
     * 494. Target Sum
     * Input: nums is [1, 1, 1, 1, 1], S is 3.
     * Output: 5
     * Explanation:
     *
     * -1+1+1+1+1 = 3
     * +1-1+1+1+1 = 3
     * +1+1-1+1+1 = 3
     * +1+1+1-1+1 = 3
     * +1+1+1+1-1 = 3
     *
     * There are 5 ways to assign symbols to make the sum of nums be target 3.
     */
    int res = 0;
    public int findTargetSumWaysRecrusive(int[] nums, int S) {
        helper(0, S, nums, 0);
        return res;
    }

    private void helper(int i, int S, int[] nums, int cur){
        if(i == nums.length){
            if(S == cur)
                res ++;
        }else{
            helper(i + 1, S, nums, cur + nums[i]);
            helper(i + 1, S, nums, cur - nums[i]);
        }
    }

    /**
     * DP 方法
     * ways[i][j] 使用到第i个元素时，当前和是j，可以使用的方法
     * ways[i][j] = ways[i-1][j-nums[i]] + ways[i-1][j+nums[i]]
     */
    public int findTargetSumWaysDP(int[] nums, int S){
        int sum = 0;
        for(int num : nums) sum += num;
        if(sum < S) return 0;

        int[][] ways = new int[nums.length + 1][2 * sum + 1];
        //由于不能从负的下标开始，定义偏移下标为sum
        ways[0][sum] = 1;
        for(int i = 1; i < nums.length + 1; i++){
            for(int j = 0; j <= 2 * sum; j++){
                if(j >= nums[i-1]) //防止越界
                    ways[i][j] += ways[i-1][j - nums[i-1]];
                if(j + nums[i-1] <= 2*sum) //防止越界
                    ways[i][j] += ways[i-1][j + nums[i-1]];
            }
        }
        //S加上偏移sum
        return ways[nums.length][S + sum];
    }


    /**
     *  282. Expression Add Operators
     *  Input: num = "123", target = 6
     *  Output: ["1+2+3", "1*2*3"]
     *
     *  Input: num = "232", target = 8
     *  Output: ["2*3+2", "2+3*2"]
     */
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if(num == null || num.length() == 0) return res;
        helper(res, 0, num, "", 0, 0, target);
        return res;
    }

    private void helper(List<String> res, int start, String num, String tmp, long eval, long prev, int target){
        if(eval == target && start == num.length()){
            res.add(tmp);
            return;
        }
        for(int i = start; i < num.length(); i++){
            if(i != start && num.charAt(start) == '0') break;
            long cur = Long.parseLong(num.substring(start, i+1));
            if(start == 0){
                helper(res, i + 1, num, tmp + cur, cur, cur, target);
            }else{
                // +
                helper(res, i + 1, num, tmp + '+' + cur, eval + cur, cur, target);
                // -
                helper(res, i + 1, num, tmp + '-' + cur, eval - cur, -cur, target);
                // *
                helper(res, i + 1, num, tmp + '*' + cur, (eval - prev) + (prev * cur), prev * cur, target);
            }
        }
    }
}
