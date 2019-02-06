package Iterator设计;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Google 店面题目
 */
public class PalidromeIterator implements Iterator {

    String next = null;
    Iterator<String> iter;

    public PalidromeIterator(Iterator<String> iter){
        this.iter = iter;
        while(iter.hasNext()){
            String tmp = iter.next();
            if(isPalidrome(tmp)) {
                next = tmp;
                break;
            }
        }
    }

    public String next() {
        String ret = next;
        next = null;
        while(iter.hasNext()){
            String tmp = iter.next();
            if(isPalidrome(tmp)) {
                next = tmp;
                break;
            }
        }
        return ret;
    }

    public boolean hasNext() {
        return next != null;
    }

    private boolean isPalidrome(String str){
        char[] arr = str.toCharArray();
        int l = 0;
        int r = arr.length - 1;
        while(l < r){
            if(arr[l] != arr[r]) return false;
            l ++; r --;
        }
        return true;
    }

    public static void main(String[] args){
        String[] strs = new String[]{"aaa", "abc", "adfda", "bsb", "hhh", "123", "343"};
        PalidromeIterator iter = new PalidromeIterator(Arrays.asList(strs).iterator());
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
    }
}
