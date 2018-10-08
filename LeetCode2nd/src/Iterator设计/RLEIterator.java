package Iterator设计;

public class RLEIterator {
    /**
     * 非递归解法
     * A[index] >= n -> A[index] = A[index] - n, return A[index + 1]
     * A[index] < n ->循环递减
     */
//    int[] A ;
//    int index = 0 ;
//
//    public RLEIterator(int[] A) {
//        this.A = A ;
//        index = 0 ;
//    }
//
//    public int next(int n) {
//        while ( index < A.length && A[index] < n ) {
//            n -= A[index] ;
//            A[index] = 0 ;
//            index += 2 ;
//        }
//        if ( index >= A.length ) return -1 ;
//        A[index] -= n ;
//        return A[index+1] ;
//    }

    /**
     * 递归解法
     */
    int[] B;
    int index;
    public RLEIterator(int[] A) {
        B = A;
        index = 0;
    }

    public int next(int n) {
        if(index > B.length - 2) return -1;
        if(n == B[index]){
            int res = B[index + 1];
            index += 2;
            return res;
        }else if(n < B[index]){
            B[index] -= n;
            return B[index + 1];
        }else{
            index += 2;
            return next(n - B[index-2]);
        }
    }
}
