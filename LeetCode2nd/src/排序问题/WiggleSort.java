package 排序问题;

import java.util.Arrays;

public class WiggleSort {
	/**
	 * 280. Wiggle Sort
	 * nums[0] <= nums[1] >= nums[2] <= nums[3]...
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
