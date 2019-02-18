package 双指针.窗口类型;

import java.util.HashMap;
import java.util.Map;

public class SubstringProblem {
    /**
     * 76. Minimum Window Substring
     * 这个是找最短，当当前窗口包含所有的目标字符时，开始移动start，这样必然会导致出现unmatched，
     * 当出现unmatched之后，又开始移动end，这样就能找到最短的。
     *
     * 3. Longest Substring Without Repeating Characters
     * 159. Longest Substring with At Most Two Distinct Characters
     * 340. Longest Substring with At Most K Distinct Characters
     * 992. Subarrays with K Different Integers
     */

	/**
	 * 先来一题热身，理解这个方法：
	 * 76. Minimum Window Substring
	 * S = "ADOBECODEBANC"
	 * T = "ABC"
	 * Minimum window is "BANC".
	 */
	public String minWindow(String s, String t) {
        int[] map = new int[128];
        //character -> number
        //A:1 B:1 C:1
        for(int i = 0; i < t.length(); i++){
            map[t.charAt(i) - 'A'] ++;
        }
        int start = 0; int end = 0; int dis = Integer.MAX_VALUE;
        int head = 0;
        int count = t.length();//unmatched number
        while(end < s.length()){
            //说明当前的窗口包含了目标字符
            if(map[s.charAt(end) - 'A'] > 0){
                count --;
            }
            //每遇到一个新的字符，不管是不是目标字符，都对其计数-1。
            //由于不管是不是目标字符，那么不是目标字符就一定会变成负数，那么在41行的时候，就能确保只有==0的情况下，才可以将unmatched计数增加1。
            map[s.charAt(end) - 'A'] --;
            end ++;
            //count == 0，说明当前窗口已经包含了所有目标字符
            while(count == 0){
                if(end - start < dis){
                    dis = end - start;
                    head = start;
                }
                if(map[s.charAt(start) - 'A'] == 0){
                    count ++;
                }
                //每脱离一个旧的字符，都对其计数+1
                map[s.charAt(start) - 'A'] ++;
                start ++;
            }
        }
        return dis == Integer.MAX_VALUE ? "" : s.substring(head, head + dis);
    }
	
	/**
	 * 3. Longest Substring Without Repeating Characters
	 */
	public int lengthOfLongestSubstring(String s) {
		if(s == null || s.length() == 0) return 0;
	    Map<Character, Integer> map = new HashMap<>();
	    int end = 0; int start = 0; int dis = Integer.MIN_VALUE;
	    int count = 0;//重复的个数
	    while(end < s.length()){
            if(map.containsKey(s.charAt(end))){
                if(map.get(s.charAt(end)) > 0) count ++;
            }else
                map.put(s.charAt(end), 0);

            map.put(s.charAt(end), map.get(s.charAt(end)) + 1);
            end ++;

            while(count > 0){
                //说明这个字符在当前窗口有重复
                if(map.get(s.charAt(start)) > 1){
                    count --;
                }
                map.put(s.charAt(start), map.get(s.charAt(start)) - 1);
                start ++;
            }
            dis = Math.max(dis, end - start);
	    }
	    return dis;
	}

	//这题还有个简单的方法：
	public int lengthOfLongestSubstringI(String s) {
		if(s == null || s.length() == 0) return 0;
		int res = Integer.MIN_VALUE;
		Map<Character, Integer> map = new HashMap<>();
		int j = 0;
		for(int i = 0; i < s.length(); i++) {
			if(map.containsKey(s.charAt(i))) {
				j = Math.max(j, map.get(s.charAt(i)) + 1);//A,B,C,D,B,A 这种情况
			}
			map.put(s.charAt(i), i);
			res = Math.max(res, i - j + 1);
		}
		return res;
	}
	
	/**
	 * 159. Longest Substring with At Most Two Distinct Characters
	 */
	public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s == null || s.length() == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        int start = 0, end = 0, count = 0;//count表示一共有多少char是distinct
        int dis = Integer.MIN_VALUE;
        while(end < s.length()){
            if(map.containsKey(s.charAt(end))){
                if(map.get(s.charAt(end)) == 0) count++;
            }else{
                count ++;
                map.put(s.charAt(end), 0);
            }
            map.put(s.charAt(end), map.get(s.charAt(end)) + 1);
            end ++;
            while(count > 2){
                if(map.get(s.charAt(start)) == 1){
                    count--; 
                }
                map.put(s.charAt(start), map.get(s.charAt(start)) - 1);
                start ++;
            }
            dis = Math.max(dis, end - start);
        }
        return dis;
    }

	/**
	 * 340. Longest Substring with At Most K Distinct Characters
	 */
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(s == null || s.length() == 0 || k == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        int start = 0; int end = 0; int dis = Integer.MIN_VALUE;
        int count = 0;
        while(end < s.length()){
            if(map.containsKey(s.charAt(end))){
                if(map.get(s.charAt(end)) == 0) {
                    count++;
                }
            }else{
                count ++;
                map.put(s.charAt(end), 0);
            }
            map.put(s.charAt(end), map.get(s.charAt(end)) + 1);
            end ++;
            while(count > k){
                if(map.get(s.charAt(start)) == 1){
                    count --;
                }
                map.put(s.charAt(start), map.get(s.charAt(start)) - 1);
                start ++;
            }
            dis = Math.max(dis, end - start);
        }
        return dis;
    }
}
