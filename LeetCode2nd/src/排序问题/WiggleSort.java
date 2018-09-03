package 排序问题;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class WiggleSort {
	/**
	 * 280. Wiggle Sort
	 * nums[0] <= nums[1] >= nums[2] <= nums[3]...
     * 5 2 9 0 2 1 6
     * 2 9 0 5 1 6 2
	 */
	public void wiggleSort(int[] nums) {
        if(nums == null || nums.length <= 1) return;
        
        boolean flag = true;
        for(int i = 1; i < nums.length; i++){
            if(flag && nums[i] < nums[i-1]){
                swap(nums, i, i-1);
            }else if(!flag && nums[i] > nums[i-1]){
                swap(nums, i, i-1);
            }
            flag = !flag;
        }
    }
    
    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * Google 面试题，wiggle sort变种
     * 给两个数组：
     * 数组1：0 1 组成，1表示升序，0表示降序
     * 数组2：根据数组1中的顺序进行升降排序
     * http://www.1point3acres.com/bbs/thread-146255-1-1.html
     *
     * 通过这一题理解wiggle sort的本质ß
     * wiggle sort的本质就是分段排序，第二段的排序不会影响第一段的排序。
     * wiggle sort1 假定没有联系的升序或者降序，所以交换相邻两个就行。
     */
    public static void googleWiggle(Integer[] nums1, Integer[] nums2){
        int start = 0;
        int end = 0;
        //将原来的nums1数组最后添加一个和原来最后一个相反的数字
        Integer[] tmp = new Integer[nums1.length + 1];
        for(int i = 0; i < nums1.length; i++){
            tmp[i] = nums1[i];
        }
        tmp[nums1.length] = tmp[nums1.length] == 0 ? 1 : 0;

        //开始反转
        while(end != nums1.length - 1) {
            while (end != nums1.length && nums1[start] == nums1[end]) {
                end++;
            }
            if(nums1[end] == 0)
                sort(nums2, start, end + 1, true);
            else
                sort(nums2, start, end + 1, false);
            start = end;
        }
    }

    private static void sort(Integer[] nums, int start, int end, boolean type){
        if(type)
            Arrays.sort(nums, start, end);
        else
            Arrays.sort(nums, start, end, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
    }

    public static void main(String[] args){
        Integer[] nums1 = {0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0};
        Integer[] nums2 = {10, 9, 8, 7, 6, 5, 4, 11, 12, 3, 9, 8, 7};
        googleWiggle(nums1, nums2);
        for(Integer num : nums2){
            System.out.print(num + " ");
        }
    }

    /**
     * 324. Wiggle Sort II
     * nums[0] < nums[1] > nums[2] < nums[3]...
     * 两个解法：
     * 解法1：先将数组排序，然后将大于mid的数，放在奇数index，将小于mid的数，放在偶数index。
     * 解法2：http://blog.csdn.net/qq508618087/article/details/51337187
     */
    public void wiggleSortII(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int oddStart = 1;
        int evenStart = (len % 2 == 1) ? len - 1 : len - 2;
        int mid = nums[len / 2];
        int[] tmp = new int[nums.length];
        Arrays.fill(tmp, mid);
        for(int i = len - 1; i >= 0 && nums[i] > mid; i--, oddStart += 2){
            tmp[oddStart] = nums[i];
        }
        for(int i = 0; i < len && nums[i] < mid; i++, evenStart -= 2){
            tmp[evenStart] = nums[i];
        }
        
        for(int i = 0; i < len; i++){
            nums[i] = tmp[i];
        }
        
    }
}
