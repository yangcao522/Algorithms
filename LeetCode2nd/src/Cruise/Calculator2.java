package Cruise;

import java.util.Stack;

public class Calculator2 {
    public int calculate(String s) {
        int N = s.length();

        char[] arr = s.toCharArray();
        char sign = '+';
        int num = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i ++) {
            if (arr[i] >= '0' && arr[i] <= '9') {
                num = num * 10 + arr[i] - '0';
            }
            if ((arr[i] != ' ' && !Character.isDigit(arr[i])) || i == N - 1) {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                sign = arr[i];
                num = 0;
            }
        }

        int ans = 0;
        while (!stack.isEmpty()) ans += stack.pop();
        return ans;
    }
}
