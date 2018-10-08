package inSpace的Matrix操作;

public class Image {
    /**
     * 这一类题主要还是记住答案
     */

    /**
     * 48. Rotate Image
     * 图片内的相对位置
     * 代码简洁高效
     */
    public void rotate(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return;

        int n = matrix.length;
        int a = 0;
        int b = n - 1;

        while(a < b){
            for(int i = 0; i < (b - a); i++){
                swap(matrix, a, a + i, a + i, b);
                swap(matrix, a, a + i, b, b - i);
                swap(matrix, a, a + i, b - i, a);
            }
            ++a;
            --b;
        }
    }

    private void swap(int[][] matrix, int x1, int y1, int x2, int y2){
        int tmp = matrix[x1][y1];
        matrix[x1][y1] = matrix[x2][y2];
        matrix[x2][y2] = tmp;
    }

    /**
     * 835. Image Overlap
     * 图片间的相对位置：两个相同像素的图片，错开位置放置，对应像素之间的位移差都是相同的
     *
     * 这一题是计算两个相同像素图像之间的最大重复点，也就是两个图片重合点都是1的最大个数
     * 尝试着把图片A中的每一个像素放到图片B的每一个像素上去.
     * A的(0, 0)可以放在B的(0,0)~(N, N) ; //(-N, -N) 最大位移差
     * A的(0, 1)可以放在B的(0,0)~(N, N) ;
     * ......
     * A的(N, N)可以放在B的(0,0)~(N, N) ; //(N, N) 最大位移差
     * 所以位移差矩阵就是一个2N * 2N的矩阵
     * 矩阵的每个entry代表着独一无二的位移差delta
     * 每一个entry都是基于这种位移差(实际上也就代表着覆盖方式),A和B都是像素1的累计和。
     */
    public int largestOverlap(int[][] A, int[][] B) {
        int N = A.length;
        int[][] count  = new int[2*N][2*N];
        int ans = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(A[i][j] == 1){
                    for(int a = 0; a < N; a++){
                        for(int b = 0; b < N; b++){
                            if(B[a][b] == 1){
                                count[N + a - i][N + b - j] += 1;
                                ans = Math.max(ans, count[N + a - i][N + b - j]);
                            }
                        }
                    }
                }
            }
        }
        return ans;
    }
}
