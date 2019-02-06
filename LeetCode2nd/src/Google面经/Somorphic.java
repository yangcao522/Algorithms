package Google面经;

import java.util.HashMap;
import java.util.Map;

public class Somorphic {
    public boolean isSomorphic(String a, String p) {
        if (a == null) return p == null;
        if (p == null) return a == null;
        char[] str = a.toCharArray();
        char[] pat = p.toCharArray();
        Map<Character, Character> map = new HashMap<>();
        int candidate = 26;
        // a里面相同的p里面也要相同，不符合这个条件肯定失败
        for (int i = 0; i < str.length; i++) {
            if (map.containsKey(str[i])) {
                if(map.get(str[i]) != pat[i]) return false;
            } else {
                map.put(str[i], pat[i]);
                candidate -- ;
            }
        }
        //不管有没有环，只要还剩下其他字母可以作为替代，一定可以成功
        if (candidate > 0) return true;

        //如果没有可以作为中间替代的字母，就看有没有环，有环则失败，无环则成功
        /**
         *  a -> b -> c -> d  h i j k
         *   \            /
         *    g <- f <- e
         */
        boolean[] visited = new boolean[26];
        for (Character c : map.keySet()) {
            if (visited[c - 'a']) continue;
            Character cur = c;
            while (!visited[cur - 'a']) {
                visited[cur - 'a'] = true;
                cur = map.get(c);
                if (cur == null) break;
            }
            if (cur != null) return false;
        }
        return true;
    }
}
