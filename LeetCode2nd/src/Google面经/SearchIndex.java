package Google面经;

import java.util.*;
import java.util.HashMap;

public class SearchIndex {
    /**
     * google : [doc1 : 100, doc2 : 50, doc2 : 60]
     * is : [doc1 : 101, doc1 : 150]
     * hard : [doc1 : 102, doc1 : 160]
     *
     * google is hard : true
     * google hard : false
     */
    public static boolean canFind(String s, Map<String, Set<String>> map) {
        String[] str = s.split(" ");
        String start = str[0];
        for (String first : map.get(start)) {
            String target = first.split(":")[0] + ":" + (Integer.valueOf(first.split(":")[1]) + 1);
            System.out.println(target);
            int i = 1;
            for (; i < str.length; i++) {
                String next = str[i];
                boolean found = false;
                for (String cand : map.get(next)) {
                    if (cand.equals(target)) {
                        found = true;
                        break;
                    }
                }
                if (!found) break;
                target = target.split(":")[0] + ":" + (Integer.valueOf(target.split(":")[1]) + 1);
                System.out.println(target);
            }
            if (i == str.length) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Map<String, Set<String>> map = new HashMap<>();
        Set<String> set1 = new HashSet<>(Arrays.asList("doc1:100", "doc2:50", "doc2:60"));
        Set<String> set2 = new HashSet<>(Arrays.asList("doc1:101", "doc1:150"));
        Set<String> set3 = new HashSet<>(Arrays.asList("doc1:102", "doc1:160"));
        map.put("google", set1);
        map.put("is", set2);
        map.put("hard", set3);
        System.out.println(canFind("is hard", map));
    }
}
