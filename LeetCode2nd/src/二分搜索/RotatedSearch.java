package 二分搜索;

public class RotatedSearch {
	/**
	 * search in rotated sorted array
	 * search in rotated sorted array II(duplicate allowed)
	 * find min in rotated sorted array
	 * find min in rotated sorted array II(duplicate allowed)
	 */
	/**
	 * 33. Search in Rotated Sorted Array
	 * 数组里 没有 重复的元素
	 */
	public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;
        int start = 0;
        int end = nums.length - 1;
        while(start + 1 < end){
            int mid = (end - start)/2 + start;
            if(nums[mid] == target){
                return mid;
            }
            if(nums[start] < nums[mid]){//左边有序
                if(nums[start] <= target && target < nums[mid]){
                    end = mid;
                }else{
                    start = mid;
                }
            }else{//右边有序
                if(nums[mid] < target && target <= nums[end]){
                    start = mid;
                }else{
                    end = mid;
                }
            }
        }
        if(nums[start] == target){
            return start;
        }else if(nums[end] == target){
            return end;
        }
        return -1;
    }
	
	/**
	 * 81. Search in Rotated Sorted Array II
	 * 数组里 有 重复的元素
	 */
	public boolean searchII(int[] nums, int target) {
        if(nums == null || nums.length == 0) return false;
        int start = 0;
        int end = nums.length - 1;
        while(start + 1 < end){
            int mid = (end - start)/2 + start;
            if(nums[mid] == target){
                return true;
            }
            if(nums[start] < nums[mid]){
                if(nums[start] <= target && target < nums[mid]){
                    end = mid;
                }else{
                    start = mid;
                }
            }else if(nums[start] == nums[mid]){ //这里是改进的方法，nums[start] == nums[mid] 的时候我们并不好去判断，只好继续往右挪一步，再做判断。这样会影响时间复杂度，变为O(n).
            		start += 1;
            }else{
                if(nums[mid] < target && target <= nums[end]){
                    start = mid;
                }else{
                    end = mid;
                }
            }
        }
        if(nums[start] == target){
            return true;
        }else if(nums[end] == target){
            return true;
        }
        return false;
    }
	
	/**
	 * 153. Find Minimum in Rotated Sorted Array
	 */
	public int findMin(int[] nums) {
        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.max(nums[0], nums[1]);
        int start = 0;
        int end = nums.length - 1;
        while(start + 1 < end) {
        		if(nums[start] < nums[end]) return nums[start];
        		int mid = (end - start)/2 + start;
        		if(nums[mid - 1] > nums[mid] && nums[mid + 1] > nums[mid]) {
        			return nums[mid];
        		}
        		if(nums[start] < nums[mid]) {
        			start = mid;
        		}else {
        			end = mid;
        		}
        }
        if(nums[start] < nums[end])
        		return nums[start];
        else
        		return nums[end];
    }
	
	/**
	 * find min in rotated sorted array II(duplicate allowed)
	 */
	public int findMinII(int[] nums) {
        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.max(nums[0], nums[1]);
        int start = 0;
        int end = nums.length - 1;
        while(start + 1 < end) {
        		if(nums[start] < nums[end]) return nums[start];
        		int mid = (end - start)/2 + start;
        		if(nums[mid - 1] > nums[mid] && nums[mid + 1] > nums[mid]) {
        			return nums[mid];
        		}
        		if(nums[start] < nums[mid]) {
        			start = mid;
        		}else if(nums[start] == nums[mid]){
        			start += 1;
        		}else {
        			end = mid;
        		}
        }
        if(nums[start] < nums[end])
        		return nums[start];
        else
        		return nums[end];
    }
}
