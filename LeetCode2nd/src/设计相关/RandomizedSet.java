package 设计相关;

import java.util.*;

public class RandomizedSet {
	/** Initialize your data structure here. */
	/**
	 * 这里的组合是map + list
	 */
	private Map<Integer, Integer> map;//<value, index>
	private List<Integer> list; //<index, value>
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val)) return false;
        map.put(val, list.size() - 1);
        list.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)) return false;
        int pos = map.get(val);
        int lastValue = list.get(list.size() - 1);
        list.set(pos, lastValue);
        list.remove(list.size()-1);
        map.put(lastValue, pos);
        map.remove(val);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        Random rand = new Random();
        int index = rand.nextInt(list.size());
        return list.get(index);
    }
}
