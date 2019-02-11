package 设计相关;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashSet;

public class LFU {
    //key <-> value
    Map<Integer, Integer> vals;
    //key <-> count
    Map<Integer, Integer> counts;
    //count <-> key list
    Map<Integer, LinkedHashSet<Integer>> lists;
    int capacity;
    int min = -1;

    public LFU(int capacity) {
        this.capacity = capacity;
        vals = new HashMap<>();
        counts = new HashMap<>();
        lists = new HashMap<>();
        lists.put(1, new LinkedHashSet<>());
    }

    public int get(int key) {
        if(!vals.containsKey(key)) return -1;
        int count = counts.get(key);
        counts.put(key, count + 1);
        lists.get(count).remove(key);
        if(count == min && lists.get(min).size() == 0){
            min ++;
        }
        if(!lists.containsKey(count + 1)){
            lists.put(count + 1, new LinkedHashSet<>());
        }
        lists.get(count + 1).add(key);
        return vals.get(key);
    }

    public void put(int key, int value) {
        if(capacity <= 0)
            return;
        //如果已经包含，啥事情不用做，只要更新count就行
        if(vals.containsKey(key)){
            vals.put(key, value);
            get(key);
            return;
        }
        //假如要加入新的,那么就要判断有没有超过capacity
        if(vals.size() >= capacity){
            int evit = lists.get(min).iterator().next();
            lists.get(min).remove(evit);
            vals.remove(evit);
        }
        vals.put(key, value);
        counts.put(key, 1);
        min = 1;
        lists.get(1).add(key);
    }
}
