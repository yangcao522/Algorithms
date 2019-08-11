package 任务调度;

import java.util.*;

public class RearrangeStringKDistApart {
    /**
     * 358. Rearrange String k Distance Apart
     * s = "aabbcc", k = 3
     * "abcabc"
     */
    public String rearrangeString(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int N = s.length();
        for(char c : s.toCharArray()){
            if(!map.containsKey(c)) map.put(c, 0);
            map.put(c, map.get(c) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> maxQueue = new PriorityQueue<>(
                (entry1, entry2) -> entry2.getValue() - entry1.getValue()
        );
        Queue<Map.Entry<Character, Integer>> waitQueue = new LinkedList<>();
        maxQueue.addAll(map.entrySet());
        StringBuilder res = new StringBuilder();
        while(!maxQueue.isEmpty()){
            Map.Entry<Character, Integer> cur = maxQueue.poll();
            cur.setValue(cur.getValue() - 1);
            waitQueue.add(cur);
            res.append(cur.getKey());
            if(waitQueue.size() < k){
                continue;
            }
            Map.Entry<Character, Integer> front = waitQueue.poll();
            if(front.getValue() > 0) maxQueue.add(front);
        }
        return res.length() == N ? res.toString() : "";
    }
}
