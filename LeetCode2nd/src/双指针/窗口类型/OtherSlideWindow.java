package 双指针.窗口类型;

import java.util.HashMap;

public class OtherSlideWindow {
    /**
     * 904. Fruit Into Baskets
     * 问题转化为：只包含小于等于两个不同元素的最长窗口
     */
    class Counter extends HashMap<Integer, Integer> {
        public int get(int k){
            return containsKey(k) ? super.get(k) : 0;
        }
        public void add(int k, int a){
            put(k, get(k) + a);
        }
    }

    public int totalFruit(int[] tree) {
        int i = 0;
        int j = 0;
        int res = 0;
        Counter num = new Counter();
        while(j < tree.length){
            num.add(tree[j], 1);
            while(num.size() == 3){
                num.add(tree[i], -1);
                if(num.get(tree[i]) == 0)
                    num.remove(tree[i]);
                i++;
            }
            res = Math.max(res, j - i + 1);
            j ++;
        }
        return res;
    }
}
