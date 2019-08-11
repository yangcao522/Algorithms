package AmazonOA;
import java.util.*;

public class MergeFils {
    public static int minimumTime(int numOfSubfiles, List<Integer> files) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.addAll(files);
        int ans = 0;
        while (minHeap.size() > 1) {
            int cost = minHeap.poll() + minHeap.poll();
            ans += cost;
            minHeap.offer(cost);
        }
        return ans;
    }

    public static void main(String[] args) {
        List<Integer> files = Arrays.asList(8, 4, 6, 12);
        System.out.println(minimumTime(4, files));
    }
}
