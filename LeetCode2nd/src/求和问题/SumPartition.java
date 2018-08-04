package 求和问题;

public class SumPartition {
	/**
	 * 698. Partition to K Equal Sum Subsets
	 */
	public boolean canPartitionKSubsets(int[] nums, int k) {
		if(nums == null || nums.length == 0) return false;
		int total = 0;
		for(int num : nums) {
			total += num;
		}
		if(total % k != 0) return false;
		int subSum = total / k;
		boolean[] visited = new boolean[nums.length];
		return helper(0, k, 0, 0, subSum, nums, visited);
	}
	
	private boolean helper(int index, int k, int curNum, int curSum, int subSum, int[] num, boolean[] visited) {
		if(k == 1) return true;
		if(curSum == subSum && curNum > 0) {
			return helper(0, k-1, 0, 0, subSum, num, visited);
		}
		for(int i = index; i < num.length; i++) {
			if(visited[i] == false) {
				visited[i] = true;
				if(helper(i + 1, k, curNum + 1, curSum + num[i], subSum, num, visited)) {
					return true;
				}
				visited[i] = false;
			}
		}
		return false;
	}
}
