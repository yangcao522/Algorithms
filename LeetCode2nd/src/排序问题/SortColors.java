package 排序问题;

public class SortColors {
	/**
	 * 75. Sort Colors
	 * 三种颜色排序，O(n)方法
	 * 维护两个index，一个index指向低位，也就是存放颜色0的index，另一个index指向高位，也就是存放颜色2的index。
	 * 只要遇到0，就将当前位置元素与index0指向的元素交换
	 * 只要遇到2，就将当前位置元素与index2指向的元素交换
	 * 其余剩下的就是1，处于中间位置。
	 */
	public static void sort3Colors(int[] nums){
		int index0 = 0;
		int index2 = nums.length - 1;
		for(int i = 0; i <= index2; i++){
			if(nums[i] == 0){
				swap(index0++, i, nums);
			}else if(nums[i] == 3){
				swap(i--, index2--, nums);
			}
		}
	}
	
	/**
	 * follow up, sort 4 colors
	 */
	public static void sort4Colors(int[] nums) {
		int index0 = 0;
		int index2 = nums.length - 1;
		for(int i = 0; i < nums.length; i++) {
			if(nums[i] == 0) {
				swap(index0++, i, nums);
			}else if(nums[i] == 2) {
				swap(index2, i--, nums);
			}
		}
		
		for(int i = index0; i <= index2; i++) {
			if(nums[i] == 1) {
				swap(i, index0++, nums);
			}
		}
	}
	
	private static void swap(int left, int right, int[] nums){
		int tmp = nums[left];
		nums[left] = nums[right];
		nums[right] = tmp;
	}
	
	
}
