package 图和拓扑排序;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

public class MatrixBFS {
	/**
	 * 286. Walls and Gates
	 */
	public void wallsAndGates(int[][] rooms) {
		if(rooms == null || rooms.length == 0) return;
		Queue<int[]> q = new LinkedList<>();
		for(int i = 0; i < rooms.length; i++) {
			for(int j = 0; j < rooms[0].length; j++) {
				if(rooms[i][j] == 0) {
					q.offer(new int[] {i, j});
				}
			}
		}
		int[] dir = new int[] {-1, 0, 1, 0, -1};
		while(!q.isEmpty()) {
			for(int i = 0; i < q.size(); i++) {
				int[] cur = q.poll();
				for(int j = 0; j < 4; j++) {
					int x = cur[0] + dir[j];
					int y = cur[1] + dir[j + 1];
					if(x < 0 || x >= rooms.length || y < 0 || y >= rooms[0].length || rooms[x][y] == -1) continue;
					if(rooms[x][y] == Integer.MAX_VALUE) {
						rooms[x][y] = rooms[cur[0]][cur[1]] + 1;
						q.offer(new int[] {x, y});
					}else {
						continue;
					}
				}
			}
		}
	}

	/**
	 * 296. Best Meeting Point
	 * 1. 这题首先应该想到的是BFS，因为曼哈顿距离的问题是可以转换成BFS的问题的。
	 * 	  从矩阵中的每个点出发为起始点，进行BFS，可以算出最短距离。
	 * 2. 在1的基础上，可以发现不需要BFS。|ax - bx| + |ay - by|就是最短距离。
	 * 3. 找规律：在一维空间上，只要这个点的左边点的个数和右边点的个数相同，那么这就是一个meet point。
	 */
	public int minTotalDistance(int[][] grid) {
		List<Integer> rows = collectRows(grid);
		List<Integer> cols = collectCols(grid);
		return getMinDist(rows) + getMinDist(cols);
	}

	private List<Integer> collectRows(int[][] grid){
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				if(grid[i][j] == 1){
					list.add(i);
				}
			}
		}
		return list;
	}

	private List<Integer> collectCols(int[][] grid){
		List<Integer> list = new ArrayList<>();
		for(int j = 0; j < grid[0].length; j++){
			for(int i = 0; i < grid.length; i++){
				if(grid[i][j] == 1){
					list.add(j);
				}
			}
		}
		return list;
	}

	private int getMinDist(List<Integer> list){
		int i = 0;
		int j = list.size() - 1;
		int dist = 0;
		while(i < j){
			dist += (list.get(j) - list.get(i));
			i ++;
			j --;
		}
		return dist;
	}
}