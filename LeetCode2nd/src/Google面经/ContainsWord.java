package Google面经;

import java.util.HashSet;
import java.util.Set;

public class ContainsWord {
    Set<String> set = new HashSet<>();
    public boolean containsWord(String[] dict, String word) {
        for (String s : dict) {
            set.add(s);
        }
        return helper(set, new StringBuilder(word));
    }

    private boolean helper(Set<String> set, StringBuilder word) {
        if (set.contains(word.toString())) return true;
        for (int i = 0; i < word.length(); i++) {
            StringBuilder sb = new StringBuilder(word);
            sb.deleteCharAt(i);
            if(helper(set, sb)) {
                return true;
            }
            sb.insert(i, word.charAt(i));
        }
        return false;
    }

    public static void main(String[] args) {
        ContainsWord test = new ContainsWord();
        System.out.println(test.containsWord(new String[]{"cat", "google"}, "gooooooogle"));
    }
}
