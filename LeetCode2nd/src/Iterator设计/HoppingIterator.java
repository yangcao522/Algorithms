package Iterator设计;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
* Implement an iterator that hops specified number of times and then returns the next
* element after the hop. Note: the iterator always returns the first element as
* it is, and starts hopping only after the first element.
*
* Examples:
* If the original iterator returns: [1, 2, 3, 4, 5] in order, then the hopping
* iterator will return [1, 3, 5] in order when the hop value is 1.
*
* If the original iterator returns: [1, 2, 3, 4, 5] in order, then the hopping
* iterator will return [1, 4] in order when the hop value is 2.
*
* If the original iterator returns: [1, 2, 3, 4, 5] in order, then the hopping
* iterator will return [1, 5] in order when the hop value is 3.. 
* 
* Methods expected to be implemented:
* public class HoppingIterator<T> implements Iterator<T> {
*                 public HoppingIterator(Iterator<T> iterator, int numHops) {...}
*                 public boolean hasNext() {...}
*                 public T next() {...} 
* }
*/
//这一种iterator题目的思路就是判断hasNext的时候，在next中把该做的事情全部做了。
public class HoppingIterator<T> implements Iterator<T>{
    public T next = null;
    public int hop;
    public Iterator<T> buffer;
         
    public HoppingIterator(Iterator<T> iterator, int numHops) {
        this.hop = numHops;
        this.buffer = iterator;
        if (buffer.hasNext()) {
            next = buffer.next();
        }
    }
     
    public boolean hasNext() {
        return next != null;
    }
     
    public T next() {
        T rs = next;
        for (int i = 0; i <= hop; i++) {
            if (buffer.hasNext()) {
                next = buffer.next();
            } else {
                next = null;
                break;
            }
        }
        return rs;
    }
     
    public static void main(String[] args) {
        Integer[] a = {1, 2, 3, 4, 5};
        List<Integer> k = Arrays.asList(a);
        HoppingIterator<Integer> hp = new HoppingIterator<>(k.iterator(), 3);
        while (hp.hasNext()) {
            System.out.println(hp.next());
        }
    }
     
}