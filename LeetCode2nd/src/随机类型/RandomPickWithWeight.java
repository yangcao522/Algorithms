package 随机类型;

import java.util.Random;

/**
 * 528. Random Pick with Weight
 * num:     [1,3,2]
 * index:    0,1,2
 * [(1),(2,3,4),(5,6)]
 * rand.nextInt(6-1)获得0-5中的任意一个数
 * 这个数落在三个区间的概率 = 按照weight选取index的概率
 * 分别取每个区间最后一个数：
 * [1, 4, 6]
 * 哪个index对应的数是最接近且大于0~5中随机产生的这个数，就是被选中的index
 */
public class RandomPickWithWeight {
    int[] sum;
    Random rand = new Random();
    public RandomPickWithWeight(int[] w) {
        sum = new int[w.length];
        sum[0] = w[0];
        for(int i = 1; i < w.length; i++){
            sum[i] = sum[i - 1] + w[i];
        }
    }

    public int pickIndex() {
        int targ = rand.nextInt(sum[sum.length - 1]);

        int lo = 0;
        int hi = sum.length - 1;
        while (lo != hi) {
            int mid = (lo + hi) / 2;
            if (targ >= sum[mid]) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}
