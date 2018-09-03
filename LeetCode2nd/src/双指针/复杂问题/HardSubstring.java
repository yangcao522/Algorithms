package 双指针.复杂问题;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HardSubstring {
    /**
     * 828. Unique Letter String
     * https://leetcode.com/problems/unique-letter-string/solution/
     * 1.转化问题：所有的substring的unique()之和 <-> 26个letter，只包含某一个letter的substring的个数，26种情况的和 (这是关键的一步)
     * 2.解决问题：比如 A 在字符串中的位置 10 14 20，那么只包含位置10的所有substring数量为10*4，同理只包含14的为4*6，只包含20的为4*1
     */
    public int uniqueLetterString(String S){
        Map<Character, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < S.length(); i++){
            map.computeIfAbsent(S.charAt(i), x-> new ArrayList<>()).add(i);
        }

        long res = 0;

        for(List<Integer> list : map.values()){
            for(int i = 0; i < list.size(); i++){
                int prev = i > 0 ? list.get(i-1) : -1;
                int next = i < list.size() ? list.get(i+1) : S.length();
                res += (list.get(i) - prev) * (next - list.get(i));
            }
        }

        return (int)res % 1_000_000_007;
    }
}
