package Google面经;

public class majorityN4 {
    /**
     * 一個list中有n個的數字，相同的數字連在一起放著，沒有sort，求是不是有一個數字出現至少n/4次
     */
    public int morethan4(int[] nums) {
        int cnt1 = 0, cnt2 = 0, cnt3 = 0;
        int cand1 = 0, cand2 = 0, cand3 = 0;

        for (int n : nums) {
            if (cand1 == n) {
                cnt1 ++;
            } else if (cand2 == n) {
                cnt2 ++;
            } else if (cand3 == n) {
                cnt3 ++;
            } else if (cnt1 == 0) {
                cand1 = n;
                cnt1 = 1;
            } else if (cnt2 == 0) {
                cand2 = n;
                cnt2 = 1;
            } else if (cnt3 == 0) {
                cand3 = n;
                cnt3 = 1;
            } else {
                cnt1 --;
                cnt2 --;
                cnt3 --;
            }
        }

        int num1 = 0, num2 = 0, num3 = 0;
        for (int n : nums) {
            if (cand1 == n) {
                num1 ++;
                if (num1 > nums.length / 4) return num1;
            } else if (cand2 == n) {
                num2 ++;
                if (num2 > nums.length / 4) return num2;
            } else if (cand3 == n) {
                num3 ++;
                if (num3 > nums.length / 4) return num3;
            }
        }

        return -1;
    }
}
