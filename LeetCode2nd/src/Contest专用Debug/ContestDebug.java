package Contest专用Debug;

import java.util.*;

public class ContestDebug {



    public static void main(String[] args) {
        double[] ans = medianSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3);
    }

    public static double[] medianSlidingWindow(int[] nums, int k) {
        initial(nums, k);
        double[] ans = new double[nums.length - k + 1];
        for (int i = k - 1; i < nums.length; i ++) {
            ans[i - k + 1] = getMedian(nums, i, k);
        }
        return ans;
    }

    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    static PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    private static void initial(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            add(nums[i]);
        }
    }

    private static double getMedian(int[] nums, int i, int k) {
        if (i >= k) {
            int toAdd = nums[i];
            int toRemove = nums[i - k];
            System.out.println(toRemove + "-" + toAdd);
            // if (toRemove <= maxHeap.peek()) maxHeap.remove(toRemove);
            // else minHeap.remove(toRemove);
            if (!maxHeap.remove(toRemove)) minHeap.remove(toRemove);
            add(toAdd);
        }
        if (maxHeap.size() == minHeap.size()) return (double)(maxHeap.peek() + minHeap.peek()) / 2.0;
        else if (maxHeap.size() > minHeap.size()) return maxHeap.peek();
        else return minHeap.peek();
    }

    private static void add(int num) {
        if (maxHeap.size() != 0 && num > maxHeap.peek()) {
            minHeap.offer(num);
        } else if (minHeap.size() != 0 && num < minHeap.peek()) {
            minHeap.offer(num);
        } else {
            maxHeap.offer(num);
        }
        while (Math.abs(maxHeap.size() - minHeap.size()) > 1) {
            if (maxHeap.size() > minHeap.size()) {
                minHeap.offer(maxHeap.poll());
            } else {
                maxHeap.offer(minHeap.poll());
            }
        }
    }
}
