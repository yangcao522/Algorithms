package Cruise;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class KWMergeInterator implements Iterator {
    class Node {
        int index;
        int[] arr;
        public Node(int index, int[] arr) {
            this.index = index;
            this.arr = arr;
        }
    }


    private PriorityQueue<Node> pq;

    public KWMergeInterator(int[][] arrays) {
        if (arrays == null || arrays.length == 0) return;
        this.pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.arr[o.index]));

        for (int[] arr : arrays) {
            if (arr.length > 0) pq.offer(new Node(0, arr));
        }
    }

    @Override
    public boolean hasNext() {
        return !pq.isEmpty();
    }

    @Override
    public Integer next() {
        Node cur = pq.poll();
        int val = cur.arr[cur.index];
        if (cur.index + 1 < cur.arr.length) {
            pq.offer(new Node(cur.index + 1, cur.arr));
        }
        return val;
    }

    public static void main(String[] args) {
        KWMergeInterator iter = new KWMergeInterator(new int[][]{{1, 4, 45}, {}, {2, 3, 4}, {11, 23, 33}});
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
    }
}
