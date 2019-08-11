package 排列组合专题;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
	/**
	 * 78. Subsets
	 */
	/**
	 * DFS的方法
	 * helper(int start, ..) : 包含num[start]的所有组合。
	 */
	public List<List<Integer>> subsetsI(int[] nums){
		List<Integer> tmp = new ArrayList<>();
		List<List<Integer>> res = new ArrayList<>();
		helper(0, tmp, res, nums);
		return res;
	}

	private void helper(int start, List<Integer> tmp, List<List<Integer>> res, int[] nums) {
		res.add(new ArrayList<>(tmp));
		for(int i = start; i < nums.length; i++) {
			tmp.add(nums[i]);
			helper(i+1, tmp, res, nums);
			tmp.remove(tmp.size() - 1);
		}
	}

	/**
	 * iterative的方法：
	 */
	/**
	 * {1, 2, 3}
	 * step 1: {[]}
	 * step 2: {[], 1}
	 * step 3: {[], 1, 2, {1, 2}}
	 * step 3: {[], 1, 2, {1, 2}, 3, {1, 3}, {2, 3}, {1, 2, 3}}
	 * 好简洁
	 */
	public List<List<Integer>> subsetsII(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		
		for(int num : nums) {
			int size = res.size();
			for(int i = 0; i < size; i++) {
				List<Integer> subset = new ArrayList<>(res.get(i));
				subset.add(num);
				res.add(subset);
			}
		}
		
		return res;
	}
}
