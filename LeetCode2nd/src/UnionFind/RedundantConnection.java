package UnionFind;

import java.util.HashMap;
import java.util.Map;

public class RedundantConnection {
    /**
     * 684. Redundant Connection
     * 直接union find, union之前检查是不是已经union
     */

    /**
     * 685. Redundant Connection II
     */
    /**
     * https://www.youtube.com/watch?v=lnmJT5b4NlM
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int N = edges.length;
        int[] parents = new int[N + 1];
        int[] cand1 = new int[]{-1, -1};
        int[] cand2 = new int[]{-1, -1};
        boolean hasDupP = false;

        for (int i = 0; i < N; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            if (parents[v] > 0) {
                cand1 = new int[]{parents[v], v};
                cand2 = new int[]{u, v};
                edges[i][0] = -1;
                edges[i][1] = -1;
                hasDupP = true;
            } else {
                parents[v] = u;
            }
        }

        parents = new int[N + 1];

        for (int[] e : edges) {
            if (e[0] == -1 || e[1] == -1) continue;

            parents[e[1]] = e[0];
            //由于已经删除了第二条边，所以如果有环，肯定是第一个cand造成的。
            //case1 && case2.2
            if (cycle(e[1], parents)) {
                return hasDupP ? cand1 : e;
            }
        }
        //case2.1 && cand2构成环
        return cand2;
    }

    private boolean cycle(int u, int[] parents) {
        int end = u;
        while(parents[end] > 0){
            end = parents[end];
            if(end == u) return true;
        }
        return false;
    }
}


