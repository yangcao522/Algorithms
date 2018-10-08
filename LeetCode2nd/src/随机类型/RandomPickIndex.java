package 随机类型;

import java.util.Random;

public class RandomPickIndex {
    Random rand;
    int [] nums;
    public RandomPickIndex(int[] nums) {
        this.nums = nums;
        rand = new Random();
    }
    public int pick(int target) {
        int res = 0;
        int total = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == target){
                int tmp = rand.nextInt(++total); //第一个3选中的概率是1, 第二个3选中的概率是1/2，第三个3选中的概率是1/3
                res = tmp == 0 ? i : res;        //如果最后保留的结果是第二个3，那么这个概率为1/2*2/3 = 1/3(第二个3被选中且第三个3没被选中) 仍然是1/3
                                                 //如果最后保留的结果是第一个3，那么这个概率为1*1/2*2/3 = 1/3(第一个3被选中且第二个3没被选中且第三个3没被选中) 仍然是1/3
            }
        }
        return res;
    }
}
