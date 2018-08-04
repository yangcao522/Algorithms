package 技巧性题目;

public class SparseMatrixMulti {
	/**
	 * 1 2 3     1 2 3
	 * 4 5 6     4 5 6
	 *           7 8 9
	 *  2*3       3*3
	 *  i*k       k*j
	 */
	
	
	//暴力方法
	public int[][] multiply(int[][] A, int[][] B) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int colsB = B[0].length;

        int[][] rst = new int[rowsA][colsB];

        for(int i = 0; i < rowsA; i++){
            for(int j = 0; j < colsB; j++){
                for(int k = 0; k < colsA; k++){
                    if(A[i][k] == 0 || B[k][j] == 0) continue;
                    rst[i][j] += A[i][k] * B[k][j];
                }

            }
        }

        return rst;
    }
	
	//我们可以交换j k的顺序,可以减少最内层循环的次数
	public int[][] multiplyII(int[][] A, int[][] B) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int colsB = B[0].length;

        int[][] rst = new int[rowsA][colsB];

        for(int i = 0; i < rowsA; i++){
            for(int k = 0; k < colsA; k++) {
            		if(A[i][k] == 0) continue;
            		for(int j = 0; j < colsB; j++) {
            			if(B[k][j] == 0) continue;
            			rst[i][j] += A[i][k] * B[k][j];
            		}
            }
        }
        return rst;
    }
}
