package Google面经;

import java.util.*;

/**
 * 排列组合题：
 * https://docs.google.com/document/d/1hOJt18Hm0UC5CKXZ4fxjYTmWzO3dhOwhmnbXO2sKSoM/edit
 * 学会画递归栈
 */
public class Rythme {
    public static Set<String> getResult(int n) {
        Set<String> ans = new HashSet<>();
        helper(n, 0, ans, new StringBuilder());
        return ans;
    }

    private static void helper(int n, int max, Set<String> set, StringBuilder sb) {
        if (n == sb.length()) {
            set.add(sb.toString());
            System.out.println(sb);
            return ;
        }
        //max之前已经有的
        for (int j = 0; j < max; j++) { //当j = max 时，本来还想着append的，结果到头了，转而到29行去append。
            sb.append((char)('A' + j)); //
            helper(n, max, set, sb); //
            sb.deleteCharAt(sb.length() - 1);
        }
        //System.out.println(sb);
        if ((char)max == 'Z') return;
        sb.append((char)('A' + max));
        helper(n, max + 1, set, sb);
        sb.deleteCharAt(sb.length() - 1);
    }

    public static void main(String[] args) {
        Set<String> set = getResult(3);
        List<String> ans = new ArrayList<>(set);
//        Collections.sort(ans);
//        for (String s : ans) {
//            System.out.println(s);
//        }
    }
}
