package 数学相关;

import java.util.Stack;

public class EnCodeAndDeCode {
    /**
     * 394. Decode String
     * s = "3[a]2[bc]", return "aaabcbc".
     * s = "3[a2[c]]", return "accaccacc".
     * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
     */
    public String decodeString(String s) {
        String res = "";
        Stack<String> tmp = new Stack<String>();
        Stack<Integer> num = new Stack<Integer>();
        int index = 0;
        while(index < s.length()){
            if(s.charAt(index) >= '0' && s.charAt(index) <= '9'){
                int count = 0;
                while(s.charAt(index) >= '0' && s.charAt(index) <= '9'){//计算前面的系数
                    count = count * 10 + (s.charAt(index) - '0');
                    index ++;
                }
                num.push(count);//将系数入栈
            }else if(s.charAt(index) == '['){//遇到左括号将res入栈
                tmp.push(res);
                res = ""; //重新初始化res为空
                index ++;
            }else if(s.charAt(index) == ']'){//组装字符串
                int times = num.pop();
                StringBuilder sb = new StringBuilder(tmp.pop());
                while(times > 0){
                    sb.append(res);
                    times --;
                }
                res = sb.toString();
                index ++;
            }else {//记录中间的字符串
                res += s.charAt(index);
                index ++;
            }
        }
        return res;
    }


}
