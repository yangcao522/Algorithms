package 图和拓扑排序;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class UnDirectedCircleDetect {
	/**
	 * 无向图与有向图一个重要的区别就是：
	 * 无向图会出现“原路返回”的情况。
	 */
	/**
	 * 类型题目
	 * 261. Graph Valid Tree
	 * 802. Find Eventual Safe States
	 */
	/**
	 * DFS
	 * 与有向图相比，加上一个prev，确保不会往回访问。
	 * 261. Graph Valid Tree
	 */
	public boolean validTree(int n, int[][] edges) {
        ArrayList[] graph = new ArrayList[n];
        int[] states = new int[n];

        for(int i = 0; i < n; i++) {
        	graph[i] = new ArrayList<Integer>();
        }
        	
		for(int i = 0; i < edges.length; i++) {
			graph[edges[i][0]].add(edges[i][1]);
			graph[edges[i][1]].add(edges[i][0]);
		}
        	
		//从0开始就应该访问到所有点
		//要是有环，那就不是tree
		if(dfs(-1, 0, graph, states)) return false;

		//还有没访问到的点，那说明是森林，不是tree
        for(int state : states){
            if(state == 0) return false;
        }
        
		return true;
    }

    //
	private boolean dfs(int prev, int cur, ArrayList[] graph, int[] states) {
		if (states[cur] == 1) return true;
		states[cur] = 1;

		for(int i = 0; i < graph[cur].size(); i++) {
			int next = (int)graph[cur].get(i);
			if(next != prev) {
				if (dfs(cur, next, graph, states)) {
					return true;
				}
			}
		}

		states[cur] = 2;
		return false;
	}

	/**
	 * BFS
	 * 310. Minimum Height Trees
	 */
	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        int[] degrees = new int[n];
        ArrayList[] graph = new ArrayList[n];
        
        for(int i = 0; i < n; i++){
            graph[i] = new ArrayList<Integer>();    
        }
        
        for(int i = 0; i < edges.length; i++) {
			graph[edges[i][0]].add(edges[i][1]);
			graph[edges[i][1]].add(edges[i][0]);
			degrees[edges[i][0]] ++;
			degrees[edges[i][1]] ++;
        }
        
        Queue<Integer> q = new LinkedList<Integer>();
        
        for(int i = 0; i < n; i++) {
			if(degrees[i] == 1) {
				q.offer(i);
			}
        }

        List<Integer> res = new ArrayList<>();
        while(!q.isEmpty()) {
			int size = q.size();
			res.clear();
			for(int i = 0; i < size; i++) {
				int cur = q.poll();
				res.add(cur);
				for(int j = 0; j < graph[cur].size(); j++) {
					int next = (int) graph[cur].get(j);
					degrees[next] --;
					if(degrees[next] == 1) {
						q.offer(next);
					}
				}
			}
        }
        return res;  
    }
}
