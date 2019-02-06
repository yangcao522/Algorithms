package Google面经;

import java.util.Arrays;

public class SumOfMatrix {
    /**
     * 題目有點難敘述，簡單說，
     * 有一個x-list(0~n)和一個y-list(0~m)
     * 每個(x_i,y_i)座標的數值必須是min(x_i, y_i)，
     * 求所有座標mxn個點的sum是多少
     * 比如說xlist = [3,2,3], ylist = [1,2,3]
     * 那最大的3x3的matrix就是
     * [[1,1,1],
     * [2,2,2],
     * [3,2,3]]
     */
    public static int getSum(int[] x, int[] y){
        Arrays.sort(x);
        Arrays.sort(y);
        int M = x.length;
        int N = y.length;
        int i = 0; int j = 0;
        int sum = 0;
        while (i < M && j < N) {
            if (x[i] >= y[j]) {
                sum += y[j] * (M - i);
                j++;
            } else {
                sum += x[i] * (N - j);
                i++;
            }
        }
        return sum;
    }

    public static void main(String[] args){
        System.out.println(getSum(new int[]{3,2,3} , new int[]{1,2,3}));
    }
}
