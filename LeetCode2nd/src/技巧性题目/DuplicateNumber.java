package 技巧性题目;

public class DuplicateNumber {
	//这个链表找环的解释非常清晰：http://blog.csdn.net/wuzhekai1985/article/details/6725263
	public int findDuplicate(int[] nums) {
        if(nums.length <= 1) return -1;
        int slow = nums[0];
        int fast = nums[nums[0]];
        while(slow != fast) {
        		slow = nums[slow];
        		fast = nums[nums[fast]];
        }
        fast = 0;
        while(fast != slow) {
        		fast = nums[fast];
        		slow = nums[slow];
        }
        return slow;
    }
}
