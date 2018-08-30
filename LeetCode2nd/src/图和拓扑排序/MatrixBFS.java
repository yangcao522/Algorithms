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


	/**
	 * 317. Shortest Distance from All Buildings
	 * 1.因为有障碍物，所以296题的方法失效
	 * 2.转而思考BFS的方法，从每个建筑物出发，计算建筑物到每一块空地的最短距离。
	 * 每个点的距离每次进行叠加，即这一点到所有建筑物的最短距离之和。
	 * 3.如何避免访问已经访问过的点？如何确保只考虑可以到达所有建筑物的点？这一题的mark技巧值得研究。
	 * 刚开始空地是0，从第一个建筑出发，访问到的全部置-1，这样确保在访问的过程中不会再去访问-1的点。
	 * 从第二个建筑出发的时候，访问的全部置-2，只能访问-1的点。
	 * 从第三个建筑出发的时候，访问的全部置-3，只能访问-2的点。
	 * 。。。
	 * 最后只有当那些位置的标记和最终的标记(code里的mark)相等时，才能代表这一点可以到达所有建筑。
	 */
	public int shortestDistance(int[][] grid) {
		if(grid == null || grid.length == 0) return 0;
		int[][] dist = new int[grid.length][grid[0].length];
		int mark = 0;

		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				if(grid[i][j] == 1){
					bfs(grid, dist, mark, i, j);
					mark --;
				}
			}
		}
		int res = Integer.MAX_VALUE;
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				if(mark == grid[i][j]){
					res = Math.min(res, dist[i][j]);
				}
			}
		}

		return res == Integer.MAX_VALUE ? -1 : res;
	}

	private void bfs(int[][] grid, int[][] dist, int mark, int i, int j){
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[]{i, j});
		int[] dirs = new int[]{-1, 0, 1, 0, -1};
		int distance = 1;
		while(!q.isEmpty()){
			int size = q.size();
			for(int l = 0; l < size; l++){
				int[] cur = q.poll();
				for(int k = 0; k < 4; k++){
					int next_i = cur[0] + dirs[k];
					int next_j = cur[1] + dirs[k + 1];
					if(next_i >= 0 && next_i < grid.length && next_j >= 0
							&& next_j < grid[0].length && grid[next_i][next_j] == mark){
						grid[next_i][next_j] = mark - 1;
						System.out.println(grid[i][j]);
						dist[next_i][next_j] += distance;
						q.offer(new int[]{next_i, next_j});

					}
				}
			}
			distance ++;
		}

	}
}