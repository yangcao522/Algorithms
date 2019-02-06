package Iterator设计;

import java.util.Arrays;
import java.util.Iterator;

public class PairIterator implements Iterator {
    class Pair {
        String str;
        int num;
        public Pair(String str, int num){
            this.str = str;
            this.num = num;
        }
    }

    Iterator<String> iter;
    String pre;
    String cur;
    int cnt;

    //Iterator: { alice, alice, bob, bob, bob, alice }
    public PairIterator(Iterator<String> iter){
        this.iter = iter;
        pre = null;
        cur = null;
        cnt = 0;
        if(iter.hasNext()) {
            pre = iter.next();
            cnt = 1;
            while(iter.hasNext()){
                String tmp = iter.next();
                if(!tmp.equals(pre)){
                    cur = tmp;
                    break;
                }
                cnt ++;
            }
        }
    }

    public boolean hasNext(){
        return pre != null;
    }

    public Pair next(){
        Pair ans = new Pair(pre, cnt);
        pre = cur;
        cur = null;
        cnt = 1;
        while(iter.hasNext()){
            String tmp = iter.next();
            if(!tmp.equals(pre)){
                cur = tmp;
                break;
            }
            cnt ++;
        }
        return ans;
    }

    public static void main(String[] args){
        String[] strs = new String[]{"alice", "alice", "alice", "bob", "bob", "alice", "yang", "yang", "yang", "yang" };
        PairIterator iter = new PairIterator(Arrays.asList(strs).iterator());
        while(iter.hasNext()){
            Pair item = iter.next();
            System.out.println("<" + item.str + ", " + item.num + ">");
        }
    }
}
