package Cruise;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KWaysMergeArray {
    class Node {
        int index;
        int[] arr;
        public Node(int index, int[] arr) {
            this.index = index;
            this.arr = arr;
        }
    }

    public void merge(int[][] arrays) {
        if (arrays == null || arrays.length == 0) return;
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.arr[o.index]));

        for (int[] arr : arrays) {
            if (arr.length > 0) pq.offer(new Node(0, arr));
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            System.out.print(cur.arr[cur.index] + " ");
            int index = cur.index;
            if (index + 1 < cur.arr.length) pq.offer(new Node(index + 1, cur.arr));
        }
    }

    public static void main(String[] args) {
        int[][] arrays = new int[][]{{1,9,10},{},{4,5,6},{1,2,3,4,11,13}};
        KWaysMergeArray kw = new KWaysMergeArray();
        kw.merge(arrays);
    }
}
