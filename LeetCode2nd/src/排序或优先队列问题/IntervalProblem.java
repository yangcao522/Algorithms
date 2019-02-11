package 排序或优先队列问题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class IntervalProblem {
	/*************************** meeting room 问题 ************************/
	/**
	 * 252. Meeting Rooms 
	 * 就是检查有没有overlap，这是interval问题的基本操作！
	 * 检查overlap的方法就是，首先按照start进行排序，判断相邻的两个interval的start和end是否有交叉
	 */
	
	/**
	 * 253. 
	 */
	public int minMeetingRooms(Interval[] intervals) {
        Map<Integer, Integer> map = new TreeMap<>();
        
        for(int i = 0; i < intervals.length; i++){
            map.put(intervals[i].start, map.getOrDefault(intervals[i].start, 0) + 1);
            map.put(intervals[i].end, map.getOrDefault(intervals[i].end, 0) - 1);
        }
        
        int res = 0;
        int rooms = 0;
        for(int v : map.values()){
            res = Math.max(res, rooms += v);
        }
        
        return res;
    }
	
	/*************************** interval 问题 ****************************/
	class  Interval{
		int start;
		int end;
		public Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	/**
	 * 56. Merge Intervals
	 * 本质上还检查overlap，有overlap就更新end，没有overlap就把当前更新过的interval加入结果。
	 */
	public List<Interval> merge(List<Interval> intervals) {
        if(intervals == null || intervals.size() == 0) return new ArrayList<Interval>();
        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval a, Interval b){
                return a.start - b.start;
            }
        });
        
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        List<Interval> res = new ArrayList<>();
        
        for(int i = 1; i < intervals.size(); i++){
            if(intervals.get(i).start <= end){
                end = Math.max(end, intervals.get(i).end);
            }else{
                res.add(new Interval(start, end));
                end = intervals.get(i).end;
                start = intervals.get(i).start;
            }
        }
        res.add(new Interval(start, end));
        
        return res;
    }
	/**
	 * 57. Insert Interval
	 *  1. 先找出第一个和newinterval overlap 的 interval。
	 *  2. 将newinterval 和 从当前开始的 interval merge 起来
	 *  3. 再将最后的那些interval加进去
	 *  【好像那种按start得排序 都是为了解决overlap的问题？】
	 */
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        if(intervals.size() == 0){
            res.add(newInterval);
            return res;
        }
        int i = 0;
        while(i < intervals.size() && intervals.get(i).end < newInterval.start){
            res.add(intervals.get(i++));
        }
        
        while(i < intervals.size() && intervals.get(i).start <= newInterval.end){
            newInterval = new Interval(
                Math.min(intervals.get(i).start, newInterval.start),
                Math.max(intervals.get(i).end, newInterval.end)
            );
            i++;
        }
        res.add(newInterval);
        
        while(i < intervals.size()){
            res.add(intervals.get(i++));
        }
        
        return res;
    }
	
	/**
	 * 435. Non-overlapping Intervals
	 * 最少 remove 哪些会使得其他的都没有overlap
	 * 解题思路：
	 * 1. 首先，其他的都没有overlap，这一题很有可能是按照start排序的。
	 * 2. 排序之后，当对整个list进行遍历的时候，当发现当前的interval和之前的interval出现overlap，这其中必须remove掉一个interval，所以主要的算法就是决定要remove哪一个。
	 * 3. 怎样做决定？
	 * 	-1、——————————
	 * 		  ——————
	 *  这种情况保留短的
	 * 	-2、——————————
	 * 		 ————————————
	 *  这种情况保留之前的
	 *  -3、——————  ————————
	 *  这种情况不要需要删除任何interval，只需要更新pre为当前的interval
	 */
	public int eraseOverlapIntervals(Interval[] intervals) {
        if(intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval a, Interval b){
                return a.start - b.start;
            }
        });
        Interval pre = intervals[0];
        int res = 0;
        for(int i = 1; i < intervals.length; i++){
            if(intervals[i].start < pre.end){
                if(intervals[i].end <= pre.end){
                    res ++;
                    pre = intervals[i];
                }else{
                    res ++;
                }
            }else{
                pre = intervals[i];
            }
        }
        return res;
    }
}
















