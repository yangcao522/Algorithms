package 排序问题;

import java.util.TreeMap;

public class CalendarProblem {
	/**
	 * 729. My Calendar I
	 * 本质上是一个overlap的问题。
	 * 1.brute force：
	 * 如果没有按照start进行排序，如何判断两个interval之间是否有overlap？
	 * base ：	-------------
	 * case1：   	--------------
	 * case2: -----------
	 * case3:      --------
	 * case4:-------------------------
	 * 所以判断的标准是：
	 * newInterval.start < baseInterval.end && newInterval.end > baseInterval.start
	 * 
	 * 2.实际上我们只要比较当前interval前后的interval就行（以start确定顺序）
	 * 使用 TreeMap 的 ceilingKey(K key) 和 floorKey(K key)
	 */
	class MyCalendar {
	    TreeMap<Integer, Integer> map;
	    public MyCalendar() {
	        map = new TreeMap<Integer, Integer>();
	    }
	    
	    public boolean book(int start, int end) {
	        Integer pre = map.floorKey(start);
	        Integer next = map.ceilingKey(start);
	        if((pre == null || start >= map.get(pre)) && (next == null || end <= next)){
	            map.put(start, end);
	            return true;
	        }
	        return false;
	    }
	}
	
	/**
	 * 731. My Calendar II
	 * 这一题允许两个interval overlap 但是不允许三个interval同时overlap
	 * 两种方法：
	 * 1. 用两个list，其中一个list存放overlaps，另一个list存放已经添加进去的intervals
	 * 2. 使用treemap存放start和end的个数，当从小往大遍历时，累计和大于等于3的时候，说明此时出现3个interval同时overlap
	 */
	
	/** 方法 2 **/
	class MyCalendarTwo {
		TreeMap<Integer, Integer> map;
		public MyCalendarTwo() {
			map = new TreeMap<>();
		}
		public boolean book(int start, int end) {
			map.put(start, map.getOrDefault(start, 0) + 1);
			map.put(end, map.getOrDefault(end, 0) - 1);
			int res = 0;
			for(int v : map.values()) {
				res += v;
				if(res >= 3) {
					//不能放入，需要复原
					map.put(start, map.getOrDefault(start, 0) - 1);
					map.put(end, map.getOrDefault(end, 0) + 1);
					if(map.get(start) == 0) map.remove(start);
					if(map.get(end) == 0) map.remove(end);
					return false;
				}
			}
			return true;
		}
	}
}
