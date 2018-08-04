package 单调栈或队列;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreater {
	/**
	 * Next Greater Element III
	 * 这个和单调栈无关。
	 */
	
	/**
	 * 496. Next Greater Element I
	 * 维护一个单调栈，栈顶到栈底的元素依次递增。
	 */
	public int[] nextGreaterElement(int[] findNums, int[] nums) {
		Stack<Integer> stack = new Stack<>();
		Map<Integer, Integer> map = new HashMap<>();
		for(int num : nums) {
			while(!stack.isEmpty() && num > stack.peek()) {
				map.put(stack.pop(), num);
			}
			stack.push(num);
		}
		
		int[] res = new int[findNums.length];
		for(int i = 0; i < findNums.length; i++) {
			res[i] = map.containsKey(findNums[i]) ? map.get(findNums[i]) : -1;
		}
		return res;
	}
	
	/**
	 * 503. Next Greater Element II
	 * Input: [1,2,1]
	 * Output: [2,-1,2]
	 * 要求能够循环。
	 * 那么解决办法就是在原来数组后面追加一个同样的数组
	 */
	public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[nums.length];
        for(int i = 0; i < nums.length * 2; i++) {
        		int num = nums[i % nums.length];
        		while(!stack.isEmpty() && nums[stack.peek()] < num) {
        			res[stack.pop()] = num;
        		}
        		if(i < nums.length) {
        			stack.push(i);
        		}
        }
        return res;
    }
	
	/**
	 * 739. Daily Temperatures
	 * 和Next Greater Element I一个意思
	 */
	public int[] dailyTemperatures(int[] temperatures) {
        if(temperatures == null || temperatures.length == 0) return new int[0];
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < temperatures.length; i++){
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                map.put(stack.pop(), i);
            }
            stack.push(i);
        }
        int[] res = new int[temperatures.length];
        for(int i = 0; i < temperatures.length; i++){
            if(map.containsKey(i)){
                res[i] = map.get(i) - i;
            }
        }
        return res;
    }
}
