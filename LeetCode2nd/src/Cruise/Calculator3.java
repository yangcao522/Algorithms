package Cruise;

import java.util.Stack;

public class Calculator3 {
    public int calculate(String s) {
        int N = s.length();
        char[] arr = s.toCharArray();

        Stack<Integer> vals = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < N; i++) {
            if (arr[i] == ' ') continue;
            if (Character.isDigit(arr[i])) {
                int num = 0;
                int index = i;
                while (index < N && Character.isDigit(arr[index])) {
                    num = num * 10 + arr[index] - '0';
                    index ++;
                }
                vals.push(num);
                i = index - 1;
            } else if (arr[i] == '(') {
                ops.push('(');
            } else if (arr[i] == ')') {
                while (ops.peek() != '(') {
                    vals.push(binOps(ops.pop(), vals.pop(), vals.pop()));
                }
                ops.pop();
            } else {
                while (!ops.isEmpty() && hasPre(arr[i], ops.peek())) {
                    vals.push(binOps(ops.pop(), vals.pop(), vals.pop()));
                }
                ops.push(arr[i]);
            }
        }

        int ans = 0;
        while (!ops.isEmpty()) {
            ans += binOps(ops.pop(), vals.pop(), vals.pop());
        }
        return ans;
    }

    private int binOps(char c, int a, int b) {
        if (c == '+') return a + b;
        else if (c == '-') return b - a;
        else if (c == '*') return a * b;
        else return b / a;
    }

    private boolean hasPre(char a, char b) {
        if (b == '(' || b == ')') return false;
        if ((b == '*' || b == '/') && (a == '+' || a == '-')) return true;
        return false;
    }
}
