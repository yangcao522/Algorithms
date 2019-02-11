package 拼凑数字类型;

public class CreateMaximumNumber {
    /**
     * 321. Create Maximum Number
     * Input:
     * nums1 = [3, 4, 6, 5]
     * nums2 = [9, 1, 2, 5, 8, 3]
     * k = 5
     * Output:
     * [9, 8, 6, 5, 3]
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] cand = new int[k];
        for(int i = Math.max(0, k - len2); i <= k && i <= len1; i++){
            int[] cand1 = getMaxNum(nums1, i);
            int[] cand2 = getMaxNum(nums2, k - i);
            int[] res = merge(cand1, cand2);
            if(isGreater(res, cand, 0, 0)){
                cand = res;
            }
        }
        return cand;
    }

    /**
     * 在一串数组中找出K位组成的最大数
     */
    private int[] getMaxNum(int[] num, int k){
        int[] ans = new int[k];
        int len = num.length;
        for(int i = 0, j = 0; i < num.length; i++){
            while(len - i > k - j && j > 0 && ans[j - 1] < num[i]){
                j --;
            }
            if(j < k) ans[j++] = num[i];
        }
        return ans;
    }

    private boolean isGreater(int[] nums1, int[] nums2, int i, int j){
        int len1 = nums1.length;
        int len2 = nums2.length;
        while(i < len1 && j < len2 && nums1[i] == nums2[j]){i++; j++;}
        return j == len2 || (i < len1 && nums1[i] > nums2[j]);
    }

    /**
     * merge两个数组，拼成最大整数
     */
    private int[] merge(int[] num1, int[] num2){
        int len1 = num1.length;
        int len2 = num2.length;
        int[] ans = new int[len1 + len2];
        int i = 0, j = 0;
        for(int index = 0; index < len1 + len2; index++){
            if(isGreater(num1, num2, i, j)){
                ans[index] = num1[i++];
            }else{
                ans[index] = num2[j++];
            }
        }
        return ans;
    }
}
