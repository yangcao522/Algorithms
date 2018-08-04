package 图和拓扑排序;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class TopologicalSort {
	/**
	 * 210. Course Schedule II
	 * 这题相当于两个要求：
	 * 1.检测是否有环
	 * 2.输出拓扑排序
	 */
	/**
	 * 1.DFS 
	 */
	public int[] findOrder(int numCourses, int[][] prerequisites) {
        //将Edge lists 转化为临接表
		ArrayList<Integer> [] graph = new ArrayList[numCourses];
        for(int i = 0 ; i < numCourses ; i++){
        	graph[i] = new ArrayList<Integer>();
        }
		
        for(int i = 0; i < prerequisites.length ; i++){
        	graph[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        boolean [] visited = new boolean[numCourses];
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0; i < graph.length; i++){
            if(false == visited[i])    
                helper(visited, graph, stack, i);
        }
        
        int [] res = new int[numCourses];
        int [] check = new int[numCourses];
       
        for(int j = 0; j < numCourses; j++){
            int tmp = stack.pop();
            check[tmp] = j;
            res[j] = tmp;
        }
        for(int k = 0; k < prerequisites.length; k++){
            if(check[prerequisites[k][0]] < check[prerequisites[k][1]]){
                return new int[0];
            }
                
        }
        return res;
    }
    
    private void helper(boolean [] visited, ArrayList<Integer> [] graph, Stack<Integer> stack, int v){
        visited[v] = true;
        for(int i = 0; i < graph[v].size(); i++){
            int tmp = graph[v].get(i);
            if(false == visited[tmp]){    
                helper(visited, graph, stack, tmp);
            }
        }
        stack.push(v);
    }
    
    /**
     * 2.BFS
     */
    public int[] findOrderBFS(int numCourses, int[][] prerequisites) {
        int[] rst = new int[numCourses];
        int[] indegree = new int[numCourses];
        ArrayList[] graph = new ArrayList[numCourses];

        for(int i = 0; i < numCourses; i++){
            graph[i] = new ArrayList();
        }

        for(int[] edge : prerequisites){
            int parent = edge[1];
            int child = edge[0];
            graph[parent].add(child);
            indegree[child] ++;
        }

        Queue<Integer> queue = new LinkedList<Integer>();

        for(int i = 0; i < numCourses; i++){
            if(indegree[i] == 0) queue.offer(i);
        }

        int index = 0;
        int visitedCount = 0;
        while(!queue.isEmpty()){
            int cur = queue.poll();
            rst[index ++] = cur;
            visitedCount ++;

            for(int i = 0; i < graph[cur].size(); i++){
                int next = (int) graph[cur].get(i);
                indegree[next] --;
                if(indegree[next] == 0){
                    queue.offer(next);
                }
            }
        }

        return (visitedCount == numCourses) ? rst : new int[0];
    }
    
    /**
     * 269. Alien Dictionary
     * [
  	 *  "wrt",
  	 *	"wrf",
  	 *	"er",
  	 *	"ett",
  	 *	"rftt"
	 *	]
	 * t->f
	 * w->e
	 * r->t
	 * e->r
	 * 这题本质上还是拓扑排序
     */
    private class Node {
		char val;
		int state;
		Set<Character> children = new HashSet<>();
		public Node(char val) {
			this.val = val;
			this.state = 0;
		}
    }
    
    public String alienOrder(String[] words) {
    	HashMap<Character, Node> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        if(words.length == 1) return words[0];

        buildGraph(words, map);
       
        for(char i : map.keySet()){
            if(map.get(i).state == 0 && dfs(i, sb, map)) return "";
        }

        return sb.reverse().toString();
    }
    
    private boolean dfs(char cur, StringBuilder sb, HashMap<Character, Node> map) {
        Node node = map.get(cur);

        node.state = 1;
        boolean hasCycle = false;
        
        for(char next : node.children) {
        	if(map.get(next).state == 1) 
        		hasCycle = true;
            else if (map.get(next).state == 0) {
                hasCycle = hasCycle || dfs(next, sb, map);
            }
        }

        node.state = 2;
        sb.append(node.val);

        return hasCycle;
    }
    
    public void buildGraph(String[] words, Map<Character, Node> map) {
        for(int i = 0; i < words.length - 1; i++) {
            String first = words[i];
            String second = words[i + 1];
            int ptr1 = 0;
            int ptr2 = 0;
            while(ptr1 < first.length() && ptr2 < second.length()) {
                char a = first.charAt(ptr1 ++);
                char b = second.charAt(ptr2 ++);
                if(!map.containsKey(a)) {
                    map.put(a, new Node(a));
                }
                if(!map.containsKey(b)) {
                    map.put(b, new Node(b));
                }
                if(a != b) {
                    Node p = map.get(a);
                    Node c = map.get(b);
                    if(!p.children.contains(c.val)) {
                        p.children.add(c.val);
                    }
                    break;
                }
            }
            while(ptr1 < first.length()){
                char c = first.charAt(ptr1++);
                if(!map.containsKey(c)) {
                    map.put(c, new Node(c));
                }
            }   
            while(ptr2 < second.length()){
                char c = second.charAt(ptr2++);
                if(!map.containsKey(c)) {
                    map.put(c, new Node(c));
                }
            }
        }
    } 
}











