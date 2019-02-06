package 单调栈或队列;

import java.util.Stack;

public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stk = new Stack<>();
        int[] ans = new int[temperatures.length];
        for(int i = temperatures.length - 1; i >= 0; i--){
            int cur = temperatures[i];
            ans[i] = 0;
            while(!stk.isEmpty() && cur >= temperatures[stk.peek()]){
                stk.pop();
            }
            if(!stk.isEmpty())
                ans[i] = stk.peek() - i;
            stk.push(i);
        }
        return ans;
    }
}
