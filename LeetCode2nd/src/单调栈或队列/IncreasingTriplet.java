package 单调栈或队列;

public class IncreasingTriplet {
	/**
	 * 334. Increasing Triplet Subsequence
     * 本质：每次都尽量更新更小的数
	 * 这一题是一个比较隐蔽的单调栈问题
	 * 实际上就是维护一个size为3的单调队列
	 * 比如：2 4 1 3 5
	 * 
	 * step1 : 2
	 * step2 : 2 4
	 * step3 : 1 4
	 * step4 : 1 3
	 * step5 : 1 3 5 (只要这个单调递增的栈大小到达3，那么就证明存在Triplet)
	 */
	public boolean increasingTriplet(int[] nums) {
        int min_1 = Integer.MAX_VALUE;
        int min_2 = Integer.MAX_VALUE;
        for(int num : nums){
            if(num <= min_1){
                min_1 = num;
            }else if(num <= min_2){
                min_2 = num;
            }else{
                return true;
            }
        }
        return false;
    } 
}
