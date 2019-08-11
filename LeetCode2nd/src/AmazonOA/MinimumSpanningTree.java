package AmazonOA;

import java.util.*;

public class MinimumSpanningTree {
    public static int getMinimumCostToConstruct(
            int numTotalAvailableCities,
            int numTotalAvailableRoads,
            List<List<Integer>> roadsAvailable,
            int numberOfRoadsConstruct,
            List<List<Integer>> costsNewRoadsContruct
    ) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        for (List<Integer> edge : costsNewRoadsContruct) {
            pq.offer(new Edge(edge.get(0), edge.get(1), edge.get(2)));
        }

        UnionFind uf = new UnionFind(numTotalAvailableCities);
        int curNumOfRoads = 0;
        for (List<Integer> edge : roadsAvailable) {
            if (uf.union(edge.get(0), edge.get(1))) curNumOfRoads ++;
        }

        if (curNumOfRoads >= numTotalAvailableCities - 1) return 0;

        int minimumCost = 0;
        while (!pq.isEmpty() &&  curNumOfRoads < numTotalAvailableCities - 1) {
            Edge cur = pq.poll();
            if (!uf.union(cur.start, cur.end)) continue;
            minimumCost += cur.cost;
            curNumOfRoads ++;
        }
        if (curNumOfRoads < numTotalAvailableCities - 1) return -1;
        return minimumCost;
    }

    static class Edge {
        int start;
        int end;
        int cost;
        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    static class UnionFind {
        Map<Integer, Integer> parents;
        Map<Integer, Integer> size;

        public UnionFind(int n) {
            parents = new HashMap<>();
            size = new HashMap<>();
            for (int i = 1; i <= n; i++) {
                parents.put(i, i);
                size.put(i, 1);
            }
        }

        public Integer find(int num) {
            if (!parents.containsKey(num)) return null;
            //find root
            Integer root = num;
            while (!parents.get(root).equals(root)) {
                root = parents.get(root);
            }
            //compress
            while (num != root) {
                Integer next = parents.get(num);
                parents.put(num, root);
                num = next;
            }
            return root;
        }

        public boolean union(int a, int b) {
            Integer rootA = find(a);
            Integer rootB = find(b);
            if (rootA == null || rootB == null) return false;
            if (rootA == rootB) return false;
            int sizeA = size.get(rootA);
            int sizeB = size.get(rootB);

            if (sizeA > sizeB) {
                parents.put(rootB, rootA);
                size.put(rootA, sizeA + sizeB);
            } else {
                parents.put(rootA, rootB);
                size.put(rootB, sizeA + sizeB);
            }
            return true;
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> a = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(1, 3), Arrays.asList(2, 3));
        List<List<Integer>> b = Arrays.asList(Arrays.asList(1, 2, 5), Arrays.asList(1, 3, 10), Arrays.asList(2, 3, 2), Arrays.asList(1, 4, 10));
        //[[1,2], [1,3],[2,3]], [[1,2,5],[1,3,10],[2,3,2], [1,4,10]]

        System.out.println(getMinimumCostToConstruct(4,3, a, 4, b));
    }
}
