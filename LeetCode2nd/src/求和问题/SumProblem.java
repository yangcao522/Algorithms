package 求和问题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SumProblem {
	/**
	 * 1. two sum
	 * a. sorted : two pointers 
	 * 167	Two Sum II - Input array is sorted   
	 * b. unsorted : hashtable 
	 * 1	 Two Sum  
	 */
	
	/**
	 * 2. two sum - data structure
	 * 70 Two Sum III - Data structure design 
	 * a. add 频繁 find 不频繁
	 * b. add 不频繁 find 频繁
	 */
	
	/**
	 * 3. 3 Sum - target | closet | smaller
	 * 15 3Sum 
	 * 16 3Sum Closest  
	 * 259 3Sum Smaller      
	 * 双指针
	 */
	public static List<List<Integer>> threeSum(int[] num, int target){
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(num);
		for(int i = 0; i < num.length - 2; i ++){
			if(i != 0 && num[i] == num[i-1]) continue;
			int newTarget = target - num[i];
			int left = i + 1;
			int right = num.length - 1;
			while(left < right){
				if(num[left] + num[right] == newTarget){
					List<Integer> tmp = new ArrayList<>();
					tmp.add(num[i]); tmp.add(num[left]); tmp.add(num[right]);
					list.add(tmp);
					while(left < right && num[right] == num[right-1]) right --;
					while(left < right && num[left] == num[left + 1])	 left ++;
					left++; right--;
				}else if(num[left] + num[right] < newTarget){
					left++;
				}else{
					right--;
				}
			}
		}
		return list;
	}
	
	public static int threeSumcloset(int[] num, int target) {
		Arrays.sort(num);
		int min = Integer.MAX_VALUE;
		int res = 0;
		for(int i = 0; i < num.length - 2; i++) {
			int left = i + 1;
			int right = num.length - 1;
			while(left < right) {
				int sum = num[i] + num[left] + num[right];
				if(Math.abs(sum - target) < Math.abs(min)) {
					min = Math.abs(sum - target);
					res = sum;
				}
				if(sum > target) {
					right --;
				}else {
					left ++;
				}
			}
		}
		return res;
	}
	
	public static int threeSumsmaller(int[] num, int target) {
		Arrays.sort(num);
		int res = 0;
		for(int i = 0; i < num.length; i++) {
			int left = i + 1;
			int right = num.length - 1;
			while(left < right) {
				int sum = num[i] + num[left] + num[right];
				if(sum < target) {
					res += (right - left);
					left ++;
				}else {
					right --;
				}
			}
		}
		return res;
	}
	
	/**
	 * 4. 4 sum 
	 * reduce to 3 sum problem
	 * 18 4Sum  
	 */
	
}
