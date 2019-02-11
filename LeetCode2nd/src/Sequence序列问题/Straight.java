package Sequence序列问题;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Straight {
    /**
     * 顺子问题：
     * 几个规律，不一定对：
     * 1）需要对原数组排序
     * 2）会用到hashmap
     */
    /**
     * 659. Split Array into Consecutive Subsequences
     * 这一题也是想不到，看答案才知道怎么做。
     * Greedy方法
     * 每当遇到一个数字，首先检查这个数字之前有没有连续序列，如果有就把它append到哪个序列
     * 如果之前没有连续序列，那么就以这个数字开头，组成长度为3的连续序列
     * 如果以上两种情况都不符合，那么就返回false
     * Time: O(n)
     * Space: O(n)
     */
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<>();
        Map<Integer, Integer> end = new HashMap<>();
        for (int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }

        for (int num : nums) {
            if (counter.get(num) == 0) continue;
            if (end.containsKey(num) && end.get(num) > 0) {
                end.put(num, end.get(num) - 1);
                end.put(num + 1, end.getOrDefault(num + 1, 0) + 1);
            } else if (counter.containsKey(num + 1) &&
                    counter.containsKey(num + 2) &&
                    counter.get(num + 1) > 0 &&
                    counter.get(num + 2 ) > 0
            ) {
                counter.put(num + 1, counter.get(num + 1) - 1);
                counter.put(num + 2, counter.get(num + 2) - 1);
                end.put(num + 3, end.getOrDefault(num + 3, 0) + 1);
            } else return false;
            counter.put(num, counter.get(num) - 1);
        }
        return true;
    }

    /**
     * 846. Hand of Straights
     * [1,2,3,6,2,3,4,7,8], W = 3
     * [1,2,3],[2,3,4],[6,7,8]
     */
    public boolean isNStraightHand(int[] hand, int W) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int h : hand) {
            map.put(h, map.getOrDefault(h, 0) + 1);
        }
        Arrays.sort(hand);
        for (int h : hand) {
            if (!map.containsKey(h)) continue;
            int cnt = 1;
            int cur = h;
            while (cnt <= W) {
                if(!map.containsKey(cur)) return false;
                map.put(cur, map.get(cur) - 1);
                if (map.get(cur) == 0) map.remove(cur);
                cur ++;
                cnt ++;
            }
        }
        return map.size() == 0;
    }
}
