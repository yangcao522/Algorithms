package 单调栈或队列;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> q = new LinkedList<Integer>();
        int N = nums.length;
        if (N == 0) return new int[]{};

        int[] ans = new int[N - k + 1];
        int index = 0;

        for (int i = 0; i < N; i++) {
            //把超出当前窗口的移出
            if (i >= k && q.getFirst() == i - k) q.removeFirst();

            //单调递减窗口，第一个元素是最大值
            while (q.size() != 0 && nums[q.getLast()] < nums[i]) {
                q.removeLast();
            }

            //当前元素添加到窗口
            q.addLast(i);

            //组织最终结果
            if (i >= k - 1) ans[index ++] = nums[q.getFirst()];
        }
        return ans;
    }
}
