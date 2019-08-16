package Cruise;

import java.util.*;

public class KWaysMerge {
    class Node {
        Iterator<Integer> iter;
        int next;
        public Node(Iterator<Integer> iter) {
            this.next = iter.next();
            this.iter = iter;
        }
    }

    public List<Integer> merge(List<List<Integer>> lists) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.next - o2.next;
            }
        });

        for (List<Integer> l : lists) {
            Iterator<Integer> iter = l.iterator();
            if (iter.hasNext()) pq.offer(new Node(iter));
        }

        List<Integer> ans = new ArrayList<>();
        int prev = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (prev == Integer.MAX_VALUE || cur.next != prev) {
                ans.add(cur.next);
                prev = cur.next;
            }
            if (cur.iter.hasNext()) pq.offer(new Node(cur.iter));
        }
        return ans;
    }

    public static void main(String[] args) {
        KWaysMerge kw = new KWaysMerge();
        List<List<Integer>> lists = Arrays.asList(Arrays.asList(1, 3, 5), Arrays.asList(1, 1, 1, 1, 1, 3, 7), Arrays.asList(3, 5, 7), Arrays.asList(9, 9, 9));
        for (int i : kw.merge(lists)) {
            System.out.print(i + " ");
        }
    }
}
