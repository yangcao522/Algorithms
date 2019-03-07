package Google面经;

import java.util.Stack;

public class DeleteChar {
    /**
     * 双指针模拟栈
     * @param str
     * @return
     */
    public String delete(String str) {
        char[] s = str.toCharArray();
        int slow = -1;
        for (char c : str.toCharArray()) {
            if (slow != -1 && isValid(s[slow], c)) {
                slow --;
            } else {
                slow ++;
                s[slow] = c;
            }
        }
        return new String(s, 0, slow + 1);
    }

    public static void main(String[] args) {
        DeleteChar dc = new DeleteChar();
        System.out.println(dc.delete("abBBaAba"));
    }

    public String delete_stack(String str) {
        Stack<Character> stk = new Stack<>();
        for (char c : str.toCharArray()) {
            if (!stk.isEmpty() && isValid(stk.peek(), c)) {
                stk.pop();
            } else {
                stk.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stk.isEmpty()) {
            sb.insert(0, stk.pop());
        }
        return sb.toString();
    }

    private boolean isValid(char a, char b) {
        if (a >= 'a' && a <= 'z' && b >= 'A' && b <= 'Z' && (a - 'a' == b - 'A')) return true;
        if (a >= 'A' && a <= 'Z' && b >= 'a' && b <= 'z' && (a - 'A' == b - 'a')) return true;
        return false;
    }
}
