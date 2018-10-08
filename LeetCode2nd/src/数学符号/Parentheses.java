package 数学符号;

import java.util.*;

public class Parentheses {
    /**
     * 241. Different Ways to Add Parentheses
     * Time: O(N ^ H) = O(N ^ N)
     * 使用记忆化搜索可以降低时间复杂度
     */
    Map<String, List<Integer>> map = new HashMap<>();
    public List<Integer> diffWaysToCompute(String input) {
        if(map.containsKey(input)) return map.get(input);
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < input.length(); i++){
            if(!Character.isDigit(input.charAt(i))){
                char c = input.charAt(i);
                List<Integer> res1 = diffWaysToCompute(input.substring(0, i));
                List<Integer> res2 = diffWaysToCompute(input.substring(i + 1));
                for(int j = 0; j < res1.size(); j++){
                    for(int k = 0; k < res2.size(); k++){
                        if(c == '+') res.add(res1.get(j) + res2.get(k));
                        if(c == '-') res.add(res1.get(j) - res2.get(k));
                        if(c == '*') res.add(res1.get(j) * res2.get(k));
                    }
                }
            }
        }
        if(res.size() == 0) res.add(Integer.parseInt(input));
        map.put(input, res);
        return res;
    }

    /**
     * 32. Longest Valid Parentheses
     * 方法一：stack
     * 方法二：一维DP
     * DP的方法比较难想
     */

    /**
     * (FB) 简化版，Remove Invalid Parenthese
     * 方法一：stack方法
     * 方法二：左右扫描
     */
    public static String removeInvalidI(String str){
        Stack<Integer> stack = new Stack<>();
        Set<Integer> invalid = new HashSet<>();
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(c == '('){
                stack.push(i);
            }else if(c == ')'){
                if(!stack.isEmpty()){
                    stack.pop();
                }else{
                    invalid.add(i);
                }
            }
        }
        while(!stack.isEmpty()){
            invalid.add(stack.pop());
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length(); i++){
           if(!invalid.contains(i)){
               sb.append(str.charAt(i));
           }
        }
        return sb.toString();
    }

    public static String removeInvalidII(String str){
        int val = 0;
        char[] array = str.toCharArray();
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '('){
                val ++;
            }else if(str.charAt(i) == ')'){
                val --;
            }
            if(val < 0){
                val = 0;
                array[i] = '#';
            }
        }
        val = 0;
        for(int i = str.length() - 1; i >= 0; i--){
            if(str.charAt(i) == ')'){
                val ++;
            }else if(str.charAt(i) == '('){
                val --;
            }
            if(val < 0){
                val = 0;
                array[i] = '#';
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < array.length; i++){
            if(array[i] != '#'){
                sb.append(array[i]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args){
        String[] testcases = new String[]{"()()()()((()()())()(",
                "()AD(S)A()W)(S))()ASD()",
                "))ASDAS()()()Q(((())QWE)()",
                "S()((A)()D))W)Q())(EQ())()W(",
                "))ASD)QW(()S(Q)(WE)(Q()(AS)D("};
        for(int i = 0; i < testcases.length; i++){
            System.out.println("Input  : " + testcases[i]);
            System.out.println("OutputI : " + removeInvalidI(testcases[i]));
            System.out.println("OutputII : " + removeInvalidII(testcases[i]));
            System.out.println();
        }
    }

    /**
     * 301. Remove Invalid Parentheses
     * 这一题并不是要求最优解，因为按照正常方法来说的话(FB简化版)提供的就是最优解的一个可能情况。
     * 所以这题真正需要做的是给出所有可能->DFS/BFS
     * 一个符合条件的解是咋样的呢？
     * 1.remove所有不符合条件的')'
     * 2.remove所有不符合条件的'('
     * 怎样remove呢？
     * 每个括号都可以存在或不存在，我们可以通过三个变量来确保枚举的某一种可能是符合条件的。
     * 1.leftCount == 0 2.rightCount == 0 3.openCount == 0(这是确保合理性的)
     */





}
