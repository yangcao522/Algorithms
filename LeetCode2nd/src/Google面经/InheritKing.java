package Google面经;

import java.util.*;

public class InheritKing {
    Map<String, Set<String>> map = new HashMap<>();
    Set<String> dead = new HashSet<>();

    String king;

    public InheritKing(String king) {
        this.king = king;
        map.put(king, new HashSet<>());
    }

    public void birth(String parent, String child) {
        if(map.containsKey(parent)) {
            map.get(parent).add(child);
            map.put(child, new HashSet<>());
        }
    }

    public void death(String name) {
        dead.add(name);
    }

    public List<String> getOrder(){
        List<String> res = new ArrayList<>();
        helper(res, king);
        return res;
    }

    private void helper(List<String> res, String cur) {
        if(!dead.contains(cur)) res.add(cur);
        for (String name : map.get(cur)) {
            helper(res, name);
        }
    }
}
