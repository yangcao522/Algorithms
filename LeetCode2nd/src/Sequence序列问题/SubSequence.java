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
}
