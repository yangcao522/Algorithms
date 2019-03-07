package Google面经;

import java.util.*;

public class MergeKSortedIterators {
    class Wrapper {
        int next;
        Iterator<Integer> iter;
        public Wrapper(Iterator<Integer> iter) {
            this.iter = iter;
            this.next = iter.next();
        }
    }

    public Iterable<Integer> mergeKSortedIterators(Iterator<Integer>[] iters) {
        Queue<Wrapper> minHeap = new PriorityQueue<>(new Comparator<Wrapper>() {
            @Override
            public int compare(Wrapper o1, Wrapper o2) {
                return o1.next - o2.next;
            }
        });
        for (Iterator<Integer> iter : iters) {
            if (iter.hasNext()) {
                minHeap.offer(new Wrapper(iter));
            }
        }

        List<Integer> ans = new ArrayList<>();

        while (!minHeap.isEmpty()) {
            Wrapper cur = minHeap.poll();
            ans.add(cur.next);
            if(cur.iter.hasNext()) minHeap.offer(new Wrapper(cur.iter));
        }

        return ans;
    }

    public static void main(String[] args) {
        MergeKSortedIterators test = new MergeKSortedIterators();
        List<Integer> l1 = Arrays.asList(1,3,5,7,9);
        List<Integer> l2 = Arrays.asList(2,4,6,8,10);
        List<Integer> l3 = Arrays.asList(0,13);
        Iterator<Integer> [] iterators = new Iterator[]{l1.iterator(), l2.iterator(), l3.iterator()};
        Iterable<Integer> ans = test.mergeKSortedIterators(iterators);
    }

}
