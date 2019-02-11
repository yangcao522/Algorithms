package Google面经;

import java.util.HashSet;
import java.util.Set;

public class Rythme {
    public static Set<String> getResult(int n) {
        Set<String> ans = new HashSet<>();
        helper(n, 0, 0, ans, new StringBuilder());
        return ans;
    }

    private static void helper(int n, int i, int max, Set<String> set, StringBuilder sb) {
        if (n <= i) {
            set.add(sb.toString());
            return ;
        }
        //max之前已经有的
        for (int j = 0; j < max; j++) {
            sb.append((char)('A' + j));
            helper(n, i + 1, max, set, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
        if ((char)max > 'Z') return;
        sb.append((char)('A' + max));
        helper(n, i + 1, max + 1, set, sb);
        sb.deleteCharAt(sb.length() - 1);
    }

    public static void main(String[] args) {
        for (String s : getResult(3)) {
            System.out.println(s);
        }
    }
}
