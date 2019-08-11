package AmazonOA;

import java.util.*;

public class ClosestKPoints {
    static class Node {
        List<Integer> node;
        int dist;
        public Node(List<Integer> node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }
    static List<List<Integer>> ClosestXDestinations(
            int numDestinations,
            List<List<Integer>> allocations,
            int numDeliveries
    ) {
        if (numDestinations == 0 || allocations.size() == 0 || numDestinations < numDeliveries) return new ArrayList<>();

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o2.dist - o1.dist;
            }
        });

        for (List<Integer> a : allocations) {
            int dist = a.get(0) * a.get(0) + a.get(1) * a.get(1);
            Node toAdd = new Node(a, dist);
            pq.offer(toAdd);
            if (pq.size() == numDeliveries + 1) {
                pq.poll();
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        while (!pq.isEmpty()) {
            ans.add(pq.poll().node);
        }
        return ans;
    }

    public static void main(String[] args) {
        List<List<Integer>> a = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4), Arrays.asList(1, -1));
        List<List<Integer>> ans = ClosestXDestinations(3, a, 2);
    }
}
