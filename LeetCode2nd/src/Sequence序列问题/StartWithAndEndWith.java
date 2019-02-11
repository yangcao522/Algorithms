package Sequence序列问题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StartWithAndEndWith {
    /**
     * End with...
     * 940. Distinct Subsequences II
     */
    public int distinctSubseqII(String S) {
        long ends[] = new long[26], mod = (long)1e9 + 7;
        Arrays.fill(ends, -1);
        int len = S.length();
        for(int i = 0; i < len; i++){
            long sum = 0;
            for(int j = 0; j < 26; j++){
                if(ends[j] != -1) sum += ends[j];
            }
            ends[S.charAt(i) - 'a'] = (sum + 1) % mod;
        }
        long res = 0;
        for(int i = 0; i < 26; i++){
            if(ends[i] != -1) res += ends[i];
        }
        return (int)(res % mod);
    }

    /**
     * Start with...
     * 792. Number of Matching Subsequences
     * Input:
     * S = "abcde"
     * words = ["a", "bb", "acd", "ace"]
     * Output: 3
     * 解释看这里：https://leetcode.com/problems/number-of-matching-subsequences/discuss/117634/Efficient-and-simple-go-through-words-in-parallel-with-explanation/
     * 曾经面试面过这一题？
     * 其实392一样的道理，需要优化，避免words中的每个word都需要做同样的判断。
     */
    public int numMatchingSubseq(String S, String[] words) {
        List<StringBuilder>[] waiting = new List[26];
        for(int i = 0; i < 26; i++){
            waiting[i] = new ArrayList<>();
        }

        for(int i = 0; i < words.length; i++){
            waiting[words[i].charAt(0) - 'a'].add(new StringBuilder(words[i]));
        }
        int res = 0;
        for(int i = 0; i < S.length(); i++){
            char c = S.charAt(i);
            List<StringBuilder> cand = waiting[c - 'a'];
            waiting[c - 'a'] = new ArrayList<>();
            for(StringBuilder sb : cand){
                sb.deleteCharAt(0);
                if(sb.length() == 0)
                    res ++;
                else
                    waiting[sb.charAt(0) - 'a'].add(sb);
            }
        }
        return res;
    }
}
