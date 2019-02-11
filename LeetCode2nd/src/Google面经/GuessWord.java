package Google面经;
import java.util.*;

public class GuessWord {
    /**
     * https://leetcode.com/problems/guess-the-word/submissions/
     */
    class Master { public int guess(String s){return 0;} }

    class Node {
        String s;
        int freq;
        public Node(String s, int freq) {
            this.s = s;
            this.freq = freq;
        }
    }

    public void findSecretWord(String[] wordList, Master master) {
        for (int i = 0; i < 10; i++) {
            Map<String, Integer> map = new HashMap<>();
            for (String w1 : wordList) {
                for (String w2 : wordList) {
                    if (match(w1, w2) == 0) map.put(w1, map.getOrDefault(w1, 0) + 1);
                }
            }

            Node cand = new Node("", 101);
            for (String key : wordList) {
                if (map.getOrDefault(key, 0) < cand.freq) {
                    cand = new Node(key, map.getOrDefault(key, 0));
                }
            }

            int matchNum = master.guess(cand.s);
            if (matchNum == 6) return;

            List<String> tmp = new ArrayList<>();
            for (String str : wordList) {
                if (match(str, cand.s) == matchNum) {
                    tmp.add(str);
                }
            }
            wordList = tmp.toArray(new String[tmp.size()]);
        }
    }

    private int match(String a, String b) {
        int ans = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == b.charAt(i)) {
                ans ++;
            }
        }
        return ans;
    }
}
