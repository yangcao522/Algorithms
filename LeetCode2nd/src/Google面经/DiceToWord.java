package Google面经;

import java.util.*;

public class DiceToWord {
    /**
     * Dice: n个 index: 0 ~ n-1
     * Word: String
     * 哪些骰子可以拼成这个word
     */
    static List<Integer> res = new ArrayList<>();
    public static List<Integer> getIndexes (String word, char[][] dices) {
        int N = dices.length;
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (int i = 0; i < N; i ++) {
            for (int j = 0; j < dices.length; j ++) {
                if (map.containsKey(dices[i][j] - 'a')) {
                    map.get(dices[i][j] - 'a').add(i);
                } else {
                    Set<Integer> set = new HashSet<>();
                    set.add(i);
                    map.put(dices[i][j] - 'a', set);
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        helper(ans, 0, word, map, new HashSet<>());
        return ans;
    }

    private static void helper(List<Integer> ans, int cur, String word, Map<Integer, Set<Integer>> map, Set<Integer> visited) {
        if(cur == word.length()){
            res.clear();
            res.addAll(ans);
            return ;
        }

        //Set<Integer> cand = map.get(word.charAt(cur) - 'a');
        for (Integer i : map.get(word.charAt(cur) - 'a')) {
            if (visited.contains(i)) continue;
            ans.add(i);
            visited.add(i);
            helper(ans,cur + 1, word, map, visited);
            ans.remove(ans.size() - 1);
            visited.remove(i);
        }
    }

    public static void main(String[] args) {
        char[][] dices = new char[][]{{'a','b','c', 'd', 'e', 'f'}, {'a', 't', 'm', 'c', 'd', 'e'}, {'d', 'o', 't', 'a', 'x', 'z'}, {'u', 'v', 'w', 'x', 'y', 'z'}};
        List<Integer> ans = getIndexes("bwm", dices);
        for(Integer i : res) System.out.println(i);
    }

}
