package Cruise;

import java.util.Stack;

public class Reverse {
    public static String getReverse(String str) {
        int N = str.length();
        if (N <= 2) return str;
        char[] arr = str.toCharArray();

        String ans = "";
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            if (Character.isLetter(arr[i])) {
                ans += arr[i];
            } else if (arr[i] == '(') {
                stack.push(ans);
                ans = "";
            } else if (arr[i] == ')') {
                StringBuilder sb = new StringBuilder(stack.pop());
                sb.append(rev(ans));
                ans = sb.toString();
            }
        }

        return ans;
    }

    private static String rev(String str) {
        char[] arr = str.toCharArray();
        int i = 0, j = arr.length - 1;
        while (i < j) {
            char tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i ++;
            j --;
        }
        return new String(arr);
    }

    public static void main(String[] args) {
        System.out.println(getReverse("ab(cd(ef)(gh))"));
    }

}
