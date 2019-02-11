package Google面经;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CokeSelection {
    static class Soda{
        int s;
        int e;
        public Soda(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }

    public static boolean cokeSelect(List<Soda> sodas, int curlo, int curhi, int targlo, int targhi, Map<String, Boolean> memo) {
        Boolean ans = memo.get(curlo + ',' + curhi);
        if (ans != null) return ans;
        if (curlo >= targlo && curhi <= targhi) return true;
        if (curhi > targhi || curlo > targhi) return false;

        for (Soda soda : sodas) {
            if (cokeSelect(sodas, curlo + soda.s, curhi + soda.e, targlo, targhi, memo)) {
                memo.put(curlo + "," + curhi, true);
                return true;
            }
        }

        memo.put(curlo + "," + curhi, false);
        return false;
    }

    //https://1o24bbs.com/t/topic/239/12
    public static boolean cokeSelect(List<Soda> sodas, int targlo, int targhi) {
        int[] dp = new int[9999 + 1];
        int N = dp.length;
        //以i开头
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (Soda soda : sodas) {
            dp[soda.s] = soda.e;
        }
        for (Soda soda : sodas) {
            for (int i = soda.s; i < N; i++) {
                if (dp[i - soda.s] == Integer.MAX_VALUE) continue;
                dp[i] = Math.min(dp[i - soda.s] + soda.e, dp[i]);
            }
        }
        for (int i = 0; i < targhi; i++) {
            if (i >= targlo && dp[i] <= targhi && dp[i] > i) {
                return true;
            }
        }
        return false;
    }

    public static void main (String[] args) {
        Map<String, Boolean> memo = new HashMap<>();
        List<Soda> sodas = Arrays.asList(new Soda[]{new Soda(100, 120), new Soda(200, 240), new Soda(400, 410)});
        System.out.println(cokeSelect(sodas, 0, 0, 300, 360, memo));
        System.out.println(cokeSelect(sodas, 300, 360));
        System.out.println(cokeSelect(sodas, 0, 0, 90, 120, memo));
        System.out.println(cokeSelect(sodas,  90, 120));
        System.out.println(cokeSelect(sodas, 0, 0, 100, 110, memo));
        System.out.println(cokeSelect(sodas,  100, 110));
        System.out.println(cokeSelect(sodas, 0, 0, 310, 360, memo));
        System.out.println(cokeSelect(sodas, 310, 360));
    }
}
