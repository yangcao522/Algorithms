package 图和拓扑排序;

import java.util.*;

public class Hierholzer {
    /**
     * 332. Reconstruct Itinerary
     */
    public List<String> findItinerary(String[][] tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for(int i = 0; i < tickets.length; i++){
            if(!map.containsKey(tickets[i][0])){
                map.put(tickets[i][0], new PriorityQueue<String>());
            }
            map.get(tickets[i][0]).add(tickets[i][1]);
        }
        List<String> res = new LinkedList<>();
        dfs(res, map, "JFK");
        return res;
    }

    private void dfs(List<String> res, Map<String, PriorityQueue<String>> map, String str){
        //res.add(str);
        while(map.containsKey(str) && !map.get(str).isEmpty()){
            dfs(res, map, map.get(str).poll());//这里把它poll掉就不会重复访问到了
        }
        res.add(0, str);
    }
}
