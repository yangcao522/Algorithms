package Bucket使用;

import java.util.*;

/**
 * 220. Contains Duplicate III
 * 这种题目要首先要确定好以哪个为bucket size
 */
public class ContainsDuplicateIII {
    private long getId(long value, long width) {
        return value < 0 ? (value + 1) / width - 1 : value / width;
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0) return false;
        Map<Long, Long> map = new HashMap<>();
        long w = (long)t + 1;
        for (int i = 0; i < nums.length; i++) {
            long id = getId(nums[i], w);
            //一个bucket中只能存在一个数，要是存在两个数就返回true了
            if (map.containsKey(id)) return true;
            if (map.containsKey(id - 1) && Math.abs(nums[i] - map.get(id - 1)) < w) return true;
            if (map.containsKey(id + 1) && Math.abs(nums[i] - map.get(id + 1)) < w) return true;
            map.put(id, (long)nums[i]);
            if (i >= k) map.remove(getId(nums[i - k], w));
        }
        return false;
    }
}
