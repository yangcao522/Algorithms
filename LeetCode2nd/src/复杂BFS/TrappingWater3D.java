package 复杂BFS;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TrappingWater3D {
    public class Cell{
        public int row;
        public int col;
        public int height;
        public Cell(int row, int col, int height){
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }

    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0)
            return 0;
        PriorityQueue<Cell> queue = new PriorityQueue<>(1, new Comparator<Cell>(){
            public int compare(Cell a, Cell b) {
                return a.height - b.height;
            }
        });
        int m = heightMap.length; int n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];
        for(int i = 0; i < m; i++){
            visited[i][0] = true;
            queue.add(new Cell(i, 0, heightMap[i][0]));
            visited[i][n-1] = true;
            queue.add(new Cell(i, n-1, heightMap[i][n-1]));
        }
        for(int j = 1; j < n-1; j++){
            visited[0][j] = true;
            queue.add(new Cell(0, j, heightMap[0][j]));
            visited[m-1][j] = true;
            queue.add(new Cell(m-1, j, heightMap[m-1][j]));
        }
        int res = 0;
        int[] dirs = new int[]{0, -1, 0, 1, 0};
        while(!queue.isEmpty()){
            Cell cell = queue.poll();
            for(int k = 0; k <4; k++){
                int row = cell.row + dirs[k];
                int col = cell.col + dirs[k+1];
                if(row >= 0 && row < m && col >= 0 && col < n && !visited[row][col]){
                    visited[row][col] = true;
                    if(cell.height > heightMap[row][col])
                        res += (cell.height - heightMap[row][col]);
                    queue.offer(new Cell(row, col, Math.max(heightMap[row][col], cell.height)));
                }
            }
        }
        return res;
    }
}
