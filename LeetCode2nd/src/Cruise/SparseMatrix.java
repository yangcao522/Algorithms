package Cruise;

import java.util.HashMap;
import java.util.Map;

public class SparseMatrix {
    private Map<Integer, Map<Integer, Integer>> map;
    private int M;
    private int N;
    public SparseMatrix(int[][] matrix) {
        this.M = matrix.length;
        this.N = matrix[0].length;
        this.map = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j ++) {
                if (matrix[i][j] != 0) {
                    if (!map.containsKey(i)) map.put(i, new HashMap<>());
                    map.get(i).put(j, matrix[i][j]);
                }
            }
        }
    }

    public void set(int row, int col, int val) {
        if (val == 0) return;
        if (!map.containsKey(row)) map.put(row, new HashMap<>());
        map.get(row).put(col, val);
    }

    public int get(int row, int col) {
        if (!map.containsKey(row)) return 0;
        if (!map.get(row).containsKey(col)) return 0;
        return map.get(row).get(col);
    }

    public SparseMatrix add(SparseMatrix matrix) {
        if (this.M != matrix.M || this.N != matrix.N) return null;
        for (int i = 0; i < this.M; i ++) {
            for (int j = 0; j < this.N; j ++) {
                int val = this.get(i, j) + matrix.get(i, j);
                if (val == 0 && map.containsKey(i) && map.get(i).containsKey(j)) map.remove(i);
                else this.set(i, j, val);
            }
        }
        return this;
    }

    public void print() {
        for (int i = 0; i < this.M; i ++) {
            for (int j = 0; j < this.N; j ++) {
                System.out.print(this.get(i, j) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SparseMatrix m1 = new SparseMatrix(new int[][]{{5, 0, 0, 0}, {0, -2, 0, 0}, {0, 0, 0, 8}});
        m1.print();
        SparseMatrix m2 = new SparseMatrix(new int[][]{{0, 5, 0, 5}, {0, 2, 0, 1}, {1, -3, 0, 9}});
        m2.print();
        m1.add(m2).print();
    }
}
