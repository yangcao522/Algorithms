package Google面经;

import java.util.*;

public class SkipIterator {
    Map<Integer, Integer> map = new HashMap<>();
    Iterator<Integer> iter;
    Integer next;
    public SkipIterator(Iterator<Integer> iter) {
        this.iter = iter;
        next = getNext();
    }

    public boolean hasNext() {
        return next != null;
    }

    public Integer next() {
        Integer ans = next;
        next = getNext();
        return ans;
    }

    public void skip(Integer num) {
        if (num.equals(next)) {
            next = getNext();
        } else {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
    }

    private Integer getNext() {
        while (iter.hasNext()) {
            Integer cur = iter.next();
            if (map.containsKey(cur) && map.get(cur) > 0) {
                map.put(cur, map.get(cur) - 1);
            } else {
                return cur;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        List<Integer> test = Arrays.asList(1,2,2,3,3,4,5,6);
        SkipIterator skipIterator = new SkipIterator(test.iterator());
        if (skipIterator.hasNext()) System.out.println(skipIterator.next());
        skipIterator.skip(2);
        skipIterator.skip(2);
        if (skipIterator.hasNext()) System.out.println(skipIterator.next());
        skipIterator.skip(3);
        if (skipIterator.hasNext()) System.out.println(skipIterator.next());
        skipIterator.skip(5);
    }
}
