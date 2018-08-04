package 排序问题;

public class BucketSort {
	/**
	 * 164. Maximum Gap
	 * 题目：求一个无序数组排完序之后相邻的最大间隔。
	 * 1.最naive的方法就是nlogn sort。
	 * 2.怎样才能在O(n)时间里做出来？
	 * 第一个想到的就是桶排序。
	 * 思路：使用桶排序首先确定桶的大小，多少个桶，怎样映射？
	 * 桶的大小bucketSize：有序元素间的平均间隔（所以相邻间隔最大的两个元素不可能出现在同一个桶里）
	 * 桶的数量len：(max - min)/bucketSize + 1
	 * 映射index：(cur - min)/bucketSize
	 */
	class Bucket{
        private boolean isUsed;
        private int maxValue;
        private int minValue;
        public Bucket(){
            isUsed = false;
            maxValue = Integer.MIN_VALUE;
            minValue = Integer.MAX_VALUE;
        }
    }
    
    public int maximumGap(int[] nums) {
        if(nums == null || nums.length < 2) return 0;
        int[] res = getMax_Min(nums);
        int A = res[1]; int B = res[0];
        int bucketSize = Math.max((B - A)/(nums.length - 1), 1);
        int len = (B - A)/bucketSize + 1;
        if(len <= 1) return B-A;
        Bucket[] buckets = new Bucket[len];
        for(int i = 0; i < buckets.length; i++){
            buckets[i] = new Bucket();         
        }
        for(int num : nums){
            int index = (num-A)/bucketSize;
            buckets[index].isUsed = true;
            buckets[index].maxValue = Math.max(buckets[index].maxValue, num);
            buckets[index].minValue = Math.min(buckets[index].minValue, num);
        }
        int maxGap = 0;
        int prevBucketMax = A;
        for (Bucket bucket : buckets) {
            if (bucket.isUsed == false) continue;
            maxGap = Math.max(maxGap, bucket.minValue - prevBucketMax);
            prevBucketMax = bucket.maxValue;
        }
        return maxGap;
    }
    
    private int[] getMax_Min(int[] nums){
        int[] res = new int[2];
        int max = Integer.MIN_VALUE; int min = Integer.MAX_VALUE; 
        for(int num : nums){
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        res[0] = max; res[1] = min;
        return res;
    }
    
    /**
     * 41. First Missing Positive
     * 最关键的就是：
     * nums[i] != nums[nums[i] - 1]
     */
    public int firstMissingPositive(int[] nums) {
        
        if(nums == null || nums.length == 0) 
            return 1;
        for(int i = 0; i < nums.length; i++){
            while(nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]){
                int tmp = nums[i];
                nums[i] = nums[tmp - 1];
                nums[tmp - 1] = tmp;
            }
        }
        
        for(int i = 0; i < nums.length; i++){
            if(i+1 != nums[i]){
                return i+1;
            }
        }
        return nums.length + 1;
    }
}
