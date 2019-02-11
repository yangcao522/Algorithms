package 复杂BFS;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathVisitingAllNodes {
    class State{
        int path;
        int head;
        public State(int path, int head) {
            this.path = path;
            this.head = head;
        }

        public int shortestPathLength(int[][] graph) {
            int N = graph.length;
            boolean[][] visited = new boolean[1 << N][N];
            Queue<State> q = new LinkedList<>();
            for (int i = 0; i < N; i++) {
                q.offer(new State(1 << i, i));
                visited[1 << i][i] = true;
            }
            int ans = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    State curState = q.poll();
                    if (curState.path == ((1 << N) - 1)) return ans;
                    for (int e : graph[curState.head]) {
                        if (visited[curState.path | (1 << e)][e]) continue;
                        visited[curState.path | (1 << e)][e] = true;
                        q.offer(new State(curState.path | (1 << e), e));
                    }
                }
                ans ++;
            }
            return -1;
        }
    }
}
