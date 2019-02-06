package Google面经;

import java.util.TreeMap;
import java.util.TreeSet;

public class StockSnapShot {
    TreeSet<Integer> set = new TreeSet<>();
    TreeMap<Integer, Integer> map = new TreeMap<>();
    public void add(int t, int p) {
        map.put(t, p);
        set.add(p);
    }

    public void update(int t, int p) {
        if(delete(t)){
            add(t, p);
        }
    }

    public boolean delete(int t) {
        if(map.containsKey(t)) {
            set.remove(map.get(t));
            map.remove(t);
            return true;
        }
        return false;
    }

    public int getCurrent() {
        return map.lastEntry().getValue();
    }

    public int getMax() {
        return set.last();
    }
}
