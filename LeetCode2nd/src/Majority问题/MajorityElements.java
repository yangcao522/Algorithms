package Majority问题;

public class MajorityElements {
    public static int majorityElement(int[] nums) {
        int cand = 0;
        int count = 0;
        for (int num : nums) {
            if (cand == num) {
                count ++;
            } else if (count == 0){
                cand = num;
                count = 1;
            } else {
                count --;
            }
        }
        return cand;
    }

    public static void main(String[] args){
        int[] num = new int[]{1,2,2,2,1,3,3};
        System.out.println(majorityElement(num));
    }

}
