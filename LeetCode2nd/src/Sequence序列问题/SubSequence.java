package Sequence序列问题;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubSequence {
    /**
     * 判断一个字符串是不是另一个字符串的subsequence
     */

    /**
     * 392. Is Subsequence
     * 不是substring, 不用担心重复出现的字符需要重新判断
     * 【定理】
     * 同时遍历s和t
     * 如果顺利遍历t的过程中，能够把s中所有字符都匹配到，那么s一定是t的subsequence
     */
    public boolean isSubsequence(String s, String t) {
        if(s.length() == 0)
            return true;
        if(t.length() == 0)
            return false;
        int index1 = 0;
        int index2 = 0;
        while(index2 < t.length()){
            if(s.charAt(index1) == t.charAt(index2)){
                index1 ++;
                if(index1 == s.length())
                    return true;
            }
            index2++;
        }
        return false;
    }

    /**
     * 792. Number of Matching Subsequences
     * 解释看这里：https://leetcode.com/problems/number-of-matching-subsequences/discuss/117634/Efficient-and-simple-go-through-words-in-parallel-with-explanation/
     * 曾经面试面过这一题？
     * 其实392一样的道理，需要优化，避免words中的每个word都需要做同样的判断。
     *
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

    /**
     * 659. Split Array into Consecutive Subsequences
     * 这一题也是想不到，看答案才知道怎么做。
     * Greedy方法
     * 每当遇到一个数字，首先检查这个数字之前有没有连续序列，如果有就把它append到哪个序列
     * 如果之前没有连续序列，那么就以这个数字开头，组成长度为3的连续序列
     * 如果以上两种情况都不符合，那么就返回false
     * Time: O(n)
     * Space: O(n)
     */
    class Counter extends HashMap<Integer, Integer> {
        public int get(int k) {
            return containsKey(k) ? super.get(k) : 0;
        }

        public void add(int k, int v) {
            put(k, get(k) + v);
        }
    }

    public boolean isPossible(int[] nums) {
        //nums的key-value pairs
        Counter num = new Counter();
        //tail[i]: i之前的序列个数
        Counter tail = new Counter();
        for(int i : nums){
            num.add(i, 1);
        }
        for(int i : nums){
            if(num.get(i) == 0) continue;
            //如果i之前存在序列，那么把i append到之前的序列上去
            else if(tail.get(i) > 0){
                tail.add(i, -1);
                tail.add(i + 1, 1);
            }
            //如果i之前不存在序列，那么就把i作为新的序列的开头
            else if(num.get(i + 1) > 0 && num.get(i + 2) > 0){
                num.add(i + 1, -1);
                num.add(i + 2, -1);
                tail.add(i + 3, 1);
            }
            //如果即不存在以i结尾的序列，又不存在以i开头的序列
            else return false;
            num.add(i, -1);
        }
        return true;
    }
}
