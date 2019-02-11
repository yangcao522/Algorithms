package 动态规划;

public class TwoDPArrays {
    /**
     * 801. Minimum Swaps To Make Sequences Increasing
     */
    public int minSwap(int[] A, int[] B) {
        int k_p = 0;
        int k_c = Integer.MAX_VALUE/2;
        int s_p = 1;
        int s_c = Integer.MAX_VALUE/2;

        for(int i = 1; i < A.length; i++){
            if(A[i] > A[i-1] && B[i] > B[i-1]){
                k_c = k_p;
                s_c = s_p + 1;
            }

            if(A[i] > B[i-1] && B[i] > A[i-1]){
                k_c = Math.min(k_c, s_p);
                s_c = Math.min(s_c, k_p + 1);
            }
            k_p = k_c;
            s_p = s_c;
            k_c = Integer.MAX_VALUE/2;
            s_c = Integer.MAX_VALUE/2;
        }
        return Math.min(k_p, s_p);
    }
}
