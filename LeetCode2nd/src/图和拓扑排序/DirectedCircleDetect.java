package 图和拓扑排序;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DirectedCircleDetect {
	/**
	 * 有向图检测有没有环
	 */
	/**
	 * 207. Course Schedule
	 * https://leetcode.com/problems/course-schedule/discuss/58509/18-22-lines-C++-BFSDFS-Solutions
	 **/
	/**
	 * 1.DFS
     * 版本1
	 */
	public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        int[] visited = new int[numCourses];

        for(int i = 0; i < numCourses; i++){
            graph[i] = new ArrayList<Integer>();
        }

        for(int[] num : prerequisites){
            int parent = num[1];
            int child = num[0];
            graph[parent].add(child);
        }

        for(int i = 0; i < numCourses; i++){
            if(visited[i] == 0 && hasCycle(i, visited, graph)) return false;
        }

        return true;
    }
    private boolean hasCycle(int cur, int[] visited, ArrayList[] graph){
        visited[cur] = 1;
        boolean hasCycle = false;
        for(int i = 0; i < graph[cur].size(); i++){
            int next = (int) graph[cur].get(i);
            if(visited[next] == 1) 
                return true;
            else if(visited[next] == 0){
                hasCycle = hasCycle || hasCycle(next, visited, graph);
                if(hasCycle){
                    return true;
                }
            }
        }
        visited[cur] = 2;
        return hasCycle;
    }

    /**
     * 版本2
     * 击败了99.8% 这个速度贼快
     * 状态1： 正在访问这个点，意思是还在dfs这个点周围的点
     * 状态0： 这个点还没有被访问
     * 状态2： 这个点周围的点全部dfs完毕
     * 每次遇到检测circle这种题目，都用这种状态方法搞定。
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        int[] visited = new int[numCourses];

        for(int i = 0; i < numCourses; i++){
            graph[i] = new ArrayList<Integer>();
        }

        for(int[] num : prerequisites){
            int parent = num[1];
            int child = num[0];
            graph[parent].add(child);
        }

        for(int i = 0; i < numCourses; i++){
            if(hasCycle(i, visited, graph)) return false;
        }

        return true;
    }

    private boolean hasCycle2(int cur, int[] visited, ArrayList[] graph){
        if(visited[cur] != 0)
            return visited[cur] == 1;
        visited[cur] = 1;
        for(int i = 0; i < graph[cur].size(); i++){
            int next = (int) graph[cur].get(i);
            boolean hasCycle = hasCycle2(next, visited, graph);
            if(hasCycle){
                return true;
            }
        }
        visited[cur] = 2;
        return false;
    }

    /**
     * 2.BFS
     * 扫一遍所有 edge，记录每个节点的 indegree.
     * 在有向无环图中，一定会存在至少一个 indegree 为 0 的起点，将所有这样的点加入queue。
     * 依次处理queue里的节点，把每次poll出来的节点的 children indegree -1. 减完之后如果 child 的 indegree = 0 了，就也放入队列。
     * 如果图真的没有环，可以顺利访问完所有节点，如果还有剩的，说明图中有环，因为环上节点的 indegree 没法归 0.
     * 
     * 此方法可以用于：
     * 1. 检测是否有环
     * 2. 拓扑排序
     */
    public boolean canFinishBFS(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        int[] indegree = new int[numCourses];

        for(int i = 0; i < numCourses; i++){
            graph[i] = new ArrayList<Integer>();
        }

        for(int[] num : prerequisites){
            int parent = num[1];
            int child = num[0];
            graph[parent].add(child);
            indegree[child] ++; 
        }

        Queue<Integer> queue = new LinkedList<>();
        
        for(int i = 0; i < indegree.length; i++) {
        		if(indegree[i] == 0) {
        			queue.offer(i);
        		}
        }
        
        int visitCount = 0;
        
        while(!queue.isEmpty()) {
        		int cur = queue.poll();
        		visitCount ++;
        		for(int i = 0; i < graph[cur].size(); i++) {
        			 int next = (int) graph[cur].get(i);
        			 indegree[next] --;
        			 if(indegree[next] == 0) {
        				 queue.offer(next);
        			 }
        		}
        }

        return visitCount == numCourses;
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
