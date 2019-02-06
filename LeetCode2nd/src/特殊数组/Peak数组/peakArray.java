package 特殊数组.Peak数组;

public class peakArray {
    /**
     * 判断一个数组是不是mountain array
     * 941. Valid Mountain Array
     */
    public boolean validMountainArray(int[] A) {
        int N = A.length;
        int i = 0;
        while(i + 1 < N && A[i] < A[i + 1]) i++;
        if(i == 0 || i == N - 1) return false;
        while(i + 1 < N && A[i] > A[i + 1]) i++;
        return i == N - 1;
    }

    /**
     * 找mountain array的山顶
     * 852. Peak Index in a Mountain Array
     */
    public int peakIndexInMountainArray(int[] A){
        int l = 0;
        int r = A.length - 1;
        while(l < r){
            int m = (r - l) / 2 + l;
            if(A[m] < A[m + 1]) l = m + 1;
            else r = m;
        }
        return l;
    }

    /**
     * 找一个array里最长的mountain
     * 845. Longest Mountain in Array
     */
    public int longestMountain(int[] A) {
        int base = 0;
        int N = A.length;
        int ans = 0;

        while(base < N){
            int end = base;
            if(end + 1 < N && A[end] < A[end + 1]){
                //walk up
                while(end + 1 < N && A[end] < A[end + 1]) end ++;
                if(end + 1 < N && A[end] > A[end + 1]){
                    //walk down
                    while(end + 1 < N && A[end] > A[end + 1]) end ++;
                    ans = Math.max(ans, end - base + 1);
                }
            }
            base = Math.max(end, base + 1);
        }

        return ans;
    }
}
