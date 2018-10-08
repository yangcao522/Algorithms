package 数学符号;

import java.util.ArrayList;
import java.util.List;

public class Operators {
    /**
     *  282. Expression Add Operators
     *  Input: num = "123", target = 6
     *  Output: ["1+2+3", "1*2*3"]
     *
     *  Input: num = "232", target = 8
     *  Output: ["2*3+2", "2+3*2"]
     */
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if(num == null || num.length() == 0) return res;
        helper(res, 0, num, "", 0, 0, target);
        return res;
    }

    private void helper(List<String> res, int start, String num, String tmp, long eval, long prev, int target){
        if(eval == target && start == num.length()){
            res.add(tmp);
            return;
        }
        for(int i = start; i < num.length(); i++){
            if(i != start && num.charAt(start) == '0') break;
            long cur = Long.parseLong(num.substring(start, i+1));
            if(start == 0){
                helper(res, i + 1, num, tmp + cur, cur, cur, target);
            }else{
                // +
                helper(res, i + 1, num, tmp + '+' + cur, eval + cur, cur, target);
                // -
                helper(res, i + 1, num, tmp + '-' + cur, eval - cur, -cur, target);
                // *
                helper(res, i + 1, num, tmp + '*' + cur, (eval - prev) + (prev * cur), prev * cur, target);
            }
        }
    }
}
