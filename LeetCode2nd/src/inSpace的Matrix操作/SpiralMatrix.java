package inSpace的Matrix操作;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if(matrix == null || matrix.length == 0) return list;

        int rowStart = 0;
        int rowEnd = matrix.length - 1;
        int colStart = 0;
        int colEnd = matrix[0].length - 1;

        while(rowStart <= rowEnd && colStart <= colEnd){
            // add top frame
            for(int i = colStart; i <= colEnd; i++) list.add(matrix[rowStart][i]);
            rowStart ++;
            // add right frame
            for(int i = rowStart; i <= rowEnd; i++) list.add(matrix[i][colEnd]);
            colEnd --;
            // add bot frame
            if(rowStart <= rowEnd) for(int i = colEnd; i >= colStart; i--) list.add(matrix[rowEnd][i]);
            rowEnd --;
            // add left frame
            if(colStart <= colEnd) for(int i = rowEnd; i >= rowStart; i--) list.add(matrix[i][colStart]);
            colStart ++;
        }

        return list;
    }

    public int[][] generateMatrix(int n) {
        if(n <= 0) return new int[0][0];

        int[][] matrix = new int[n][n];

        int rowStart = 0;
        int rowEnd = n - 1;
        int colStart = 0;
        int colEnd = n - 1;

        int curNum = 1;

        while(rowStart <= rowEnd && colStart <= colEnd){
            for(int i = colStart; i <= colEnd; i++) matrix[rowStart][i] = curNum++;
            rowStart ++;

            for(int i = rowStart; i <= rowEnd; i++) matrix[i][colEnd] = curNum++;
            colEnd --;

            if(rowStart <= rowEnd) for(int i = colEnd; i >= colStart; i--) matrix[rowEnd][i] = curNum++;
            rowEnd --;

            if(colStart <= colEnd) for(int i = rowEnd; i >= rowStart; i--) matrix[i][colStart] = curNum++;
            colStart ++;
        }

        return matrix;
    }
}
