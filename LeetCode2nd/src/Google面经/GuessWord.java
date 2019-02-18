package Google面经;
import java.util.*;

public class GuessWord {
    /**
     * https://leetcode.com/problems/guess-the-word/submissions/
     * 如果我们选择的candidate和其余所有单词中的大部分单词都match0次，那么这个candidate很有可能和target也match0次
     * 如果我们选择的candidate和其余所有单词中的少部分单词都match0次，那么这个candidate很有可能和target match不止0次
     * 所以即使这个candidate不是最终的target，那么这个candidate和target match了num次，我们通过num次就可以找到接下来的candidate pool
     * 这个candidate pool很有可能减少了很多。如果num是0的话，这个candidate pool可能会很大。因此我们要选match 0次最少的candidate，来不断的
     * 缩小candidate pool。
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
