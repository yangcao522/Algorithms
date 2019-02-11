package 排序或优先队列问题;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxHeap {
    class Node {
        int index;
        double ratio;
        public Node(int index, double ratio) {
            this.index = index;
            this.ratio = ratio;
        }
    }
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        int N = quality.length;
        Node[] arr = new Node[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new Node(i, (double)wage[i]/quality[i]);
        }
        Arrays.sort(arr, new Comparator<Node>(){
            public int compare(Node a, Node b) {
                if (a.ratio < b.ratio) return -1;
                else if (a.ratio == b.ratio) return 0;
                else return 1;
            }
        });

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((x, y) -> y - x);
        double ans = Double.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            int curQua = quality[arr[i].index];
            maxHeap.offer(curQua);
            sum += curQua;
            if (maxHeap.size() > K) {
                sum -= maxHeap.poll();
            }
            if (maxHeap.size() == K) {
                ans = Math.min(ans, sum * arr[i].ratio);
            }
        }
        return ans;
    }
}
