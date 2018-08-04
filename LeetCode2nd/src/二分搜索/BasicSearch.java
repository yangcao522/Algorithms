package 二分搜索;

public class BasicSearch {
	/**
	 * search insert position
	 * search for a range
	 * isBadVersion
	 * Find Peak Element
	 * Median of Two Sorted Arrays
	 */
	
	/**
	 * 35. Search Insert Position
	 */
	public int searchInsert(int[] nums, int target) {
        if(nums == null || nums.length == 0) return 0;
        int left = 0;
        int right = nums.length - 1;
        while(left + 1 < right){
            int mid = (right - left)/2 + left;
            if(nums[mid] >= target){
                right = mid; 
            }else{
                left = mid;
            }
        }
        
        if(nums[left] >= target){
            return left;
        }
        if(nums[right] >= target){
            return right;
        }
        return nums.length;
    }
	
	/**
	 * 34. Search for a Range
	 * 找到target的前后界
	 */
	public int[] searchRange(int[] nums, int target) {
        int left = -1;
        int right = -1;
        if(nums == null || nums.length == 0){
            return new int[]{left, right};
        }
        
        int start = 0;
        int end = nums.length - 1;
        while(start + 1 < end){
            int mid = (end - start)/2 + start;
            if(nums[mid] >= target){
                end = mid;
            }else{
                start = mid;
            }
        }
        if(nums[start] == target)
            left = start;
        else if(nums[end] == target){
            left = end;
        }else return new int[]{-1, -1};
        
        start = 0; end = nums.length - 1;
        while(start + 1 < end){
            int mid = (end - start)/2 + start;
            if(nums[mid] <= target){
                start = mid;
            }else{
                end = mid;
            }
        }
        if(nums[end] == target)
            right = end;
        else if(nums[start] == target){
            right = start;
        }else
            return new int[]{-1, -1};
        return new int[]{left, right};
    }
	
	/**
	 * 278. First Bad Version
	 * 找到第一个bad version
	 */
	private boolean isBadVersion(int n) {
		//fake function;
		return true;
	}
	public int firstBadVersion(int n) {
        int start = 1;
        int end = n;
        while(start + 1 < end){
            int mid = (end - start)/2 + start;
            if(isBadVersion(mid)){
                end = mid;
            }else{
                start = mid;
            }
        }
        if(isBadVersion(start)){
            return start;
        }else if(isBadVersion(end)){
            return end;
        }
        return -1;
    }
}
