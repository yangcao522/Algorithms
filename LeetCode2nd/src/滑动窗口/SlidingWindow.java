package 滑动窗口;

import java.util.LinkedList;

public class SlidingWindow {
    //sorted dequeue
    //每个元素只会被add和remove一次 因此每次操作还是O(1)的时间
    public int[] maxSlidingWindow(int[] nums, int k){
        if(nums == null || nums.length == 0){
            return new int[0];
        }
        LinkedList<Integer> dequeue = new LinkedList<>();
        int[] res = new int[nums.length + 1 - k];
        for(int i = 0; i < nums.length; i++){
            //假如当前队列的最大元素已经不在窗口，那么剔除出去
            if(!dequeue.isEmpty() && dequeue.peekFirst() == (i - k)){
                dequeue.removeFirst();
            }
            //剔除窗口中那些比新加入元素小的
            while(!dequeue.isEmpty() && nums[dequeue.peekLast()] < nums[i]){
                dequeue.removeLast();
            }

            dequeue.offerLast(i);

            if(i - k + 1 >= 0){
                res[i + 1 -k] = nums[dequeue.peekFirst()];
            }
        }
        return res;
    }
}
