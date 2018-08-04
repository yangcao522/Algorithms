package 二分搜索;

public class MedianProblem {
	
	public double findMedianSortedArrays(int A[], int B[]) {
        int m = A.length;
        int n = B.length;
		if((m + n) % 2 == 1)
            return helper(A, B, 0, m - 1, 0, n - 1, (m + n) / 2 + 1);
        else
            return (helper(A, B, 0, m - 1, 0, n - 1, (m + n) / 2)  
                   +helper(A, B, 0, m - 1, 0, n - 1, (m + n) / 2 + 1)) / 2.0;
    }
	
    private int helper(int A[], int B[], int i, int i2, int j, int j2, int k){
        int m = i2 - i + 1;
        int n = j2 - j + 1;
        if(m > n)
            return helper(B, A, j, j2, i, i2, k);
        if(m == 0)
            return B[j + k - 1];
        if(k == 1)
            return Math.min(A[i], B[j]);
        int posA = Math.min(k/2, m);
        int posB = k - posA;
        
        if(A[i + posA - 1] == B[j + posB - 1])
            return A[i + posA - 1];
        else if(A[i + posA - 1] < B[j + posB - 1])
            return helper(A, B, i + posA, i2, j, j2, k - posA);
        else
            return helper(A, B, i, i2, j + posB, j2, k - posB);
    }
    
    /**
     * 这个版本写的很好！
     * 复杂度 O(klogk) k = (m+n)/2 
     */
    public double findMedianSortedArraysII(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        if(len % 2 == 1){
            return findKth(nums1, 0, nums2, 0, len / 2 + 1);
        } else {
            return (findKth(nums1, 0, nums2, 0, len / 2) + 
                    findKth(nums1, 0, nums2, 0, len / 2 + 1)) / 2.0;  
        }
    }

    private int findKth(int[] A, int startA, int[] B, int startB, int k){
        if(startA == A.length) return B[startB + k - 1];
        if(startB == B.length) return A[startA + k - 1];
        if(k == 1) return Math.min(A[startA], B[startB]);

        int keyA = (startA + k / 2 - 1 < A.length)
                    ? A[startA + k / 2 - 1]
                    : Integer.MAX_VALUE;
        int keyB = (startB + k / 2 - 1 < B.length)
                    ? B[startB + k / 2 - 1]
                    : Integer.MAX_VALUE;
        if(keyA < keyB){
            return findKth(A, startA + k / 2, B, startB, k - k / 2);
        } else {
            return findKth(A, startA, B, startB + k / 2, k - k / 2);
        }
    }
}
