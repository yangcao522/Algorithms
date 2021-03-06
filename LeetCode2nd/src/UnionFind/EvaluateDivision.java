package UnionFind;

import java.util.HashMap;
import java.util.Map;

/**
 * Google 高频
 */
public class EvaluateDivision {
    class Node {
        public String parent;
        public double ratio;
        //parent是表达式的分子 a/parent = ratio
        public Node(String parent, double ratio) {
            this.parent = parent;
            this.ratio = ratio;
        }
    }

    class UnionFindSet {
        private Map<String, Node> parents = new HashMap<>();
        /**
         * 找root的过程中，
         */
        public Node find(String s) {
            if (!parents.containsKey(s)) return null;
            Node n = parents.get(s);
            if (!n.parent.equals(s)) {
                Node p = find(n.parent);
                n.parent = p.parent;
                n.ratio *= p.ratio;
            }
            return n;
        }

        /**
         * 这里相当于初始化或者add的时候在union的时候进行
         */
        public void union(String s, String p, double ratio) {
            boolean hasS = parents.containsKey(s);
            boolean hasP = parents.containsKey(p);
            if (!hasS && !hasP) {
                parents.put(s, new Node(p, ratio));
                parents.put(p, new Node(p, 1.0));
            } else if (!hasP) {
                parents.put(p, new Node(s, 1.0 / ratio));
            } else if (!hasS) {
                parents.put(s, new Node(p, ratio));
            } else {
                Node rS = find(s);
                Node rP = find(p);
                rS.parent = rP.parent;
                rS.ratio = ratio * rP.ratio;
            }
        }
    }

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        UnionFindSet u = new UnionFindSet();

        for (int i = 0; i < equations.length; ++i)
            u.union(equations[i][0], equations[i][1], values[i]);

        double[] ans = new double[queries.length];

        for (int i = 0; i < queries.length; ++i) {
            Node rx = u.find(queries[i][0]);
            Node ry = u.find(queries[i][1]);
            if (rx == null || ry == null || !rx.parent.equals(ry.parent))
                ans[i] = -1.0;
            else
                ans[i] = rx.ratio / ry.ratio;
        }

        return ans;
    }
}
