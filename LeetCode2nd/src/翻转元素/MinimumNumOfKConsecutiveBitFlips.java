package 翻转元素;

public class MinimumNumOfKConsecutiveBitFlips {
    /**
     * 995. Minimum Number of K Consecutive Bit Flips
     */
    public int minKBitFlips(int[] A, int K) {
        //首先，这题的本质是贪婪算法
        //从左往右，遇到K-Size窗口的开头是0的，一定要翻，所以暴力解释O(N*K)
        int[] fliped = new int[A.length];
        //这是一种lazy flip, 也就是说先不着急去真正的翻这一位，而是记录这一位在当前窗口里被翻的次数
        //假如是偶数次，就相当于没有翻
        //假如是技术次，相当于翻了一次
        //于是，假如当前元素是0并且被翻了偶数次，那么这一位需要被翻
        //假如当前元素是1并且被翻了奇数次，那么这一位还是要被翻
        int flipedCnt = 0;
        int ans = 0;
        for (int i = 0; i < A.length; i++) {
            //超出了窗口,需要把之前一个flip去掉
            if (i >= K) {
                flipedCnt ^= fliped[i - K];
            }
            //需要被翻
            if ((A[i] ^ flipedCnt) == 0) {
                //接近尾声，不能再翻了
                if (i + K > A.length) return -1;
                ans ++;
                //当前被翻，记录下来
                flipedCnt ^= 1;
                fliped[i] = 1;
            }
        }
        return ans;
    }
}
