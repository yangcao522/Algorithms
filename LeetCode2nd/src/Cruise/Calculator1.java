package Cruise;

import java.util.Stack;

public class Calculator1 {
    public int calculate(String s) {
        int N = s.length();

        char[] arr = s.toCharArray();
        int sign = 1;
        int eval = 0;
        int num = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i ++) {
            if (arr[i] == ' ') continue;
            if (arr[i] >= '0' && arr[i] <= '9') {
                num = num * 10 + arr[i] - '0';
            } else if (arr[i] == '(') {
                stack.push(eval);
                stack.push(sign);
                eval = 0;
                num = 0;
                sign = 1;
            } else if (arr[i] == ')') {
                eval += sign * num;
                eval = stack.pop() * eval + stack.pop();
                sign = 1;
                num = 0;
            } else {
                eval += sign * num;
                sign = arr[i] == '+' ? 1 : -1;
                num = 0;
            }
        }
        eval += sign * num;
        return eval;
    }
}
