package Google面经;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TODO_GoogleSnapshot {
    /**
     * 实现三个functions， get, set, take snapshots。其实就是一个长度为N的Array。Set可以设置Index i的值，每次take snapshot， version + 1，并且记录下当前version下 Array里面的值。然后get方法可以得到某一个Version下，每一个Index的Array的值。就是非常Naive的方法，在Chromebook上写完了。写完之后有一个变量名Typo被指出。口头跑了Test case。Follow up 时空复杂度，并且要节省空间。
     * 举例
     * 初始 100001
     * take a snapshot 返回sid 1
     * 改变成 100201.
     * take a snapshot 返回sid 2
     * 这时lookup get（3，1）（ get(index, snapshotID)）应该返回0而不是2，这是version control的原理可是关键在怎么存最省空间
     *
     * 可以参考视频压缩，每隔若干帧保存一份完整的图像，期间只保存delta。重建图像的时候，从最近的full snapshot开始，然后apply 每个version的delta
     */
    class Snapshot {
        int version = 1;
        int length;
        Map<Integer, TreeMap<Integer, Integer>> map = new HashMap<>();
        public Snapshot(int length) {
            this.length = length;
            for(int i = 0 ; i < length ; i++) {
                map.put(i, new TreeMap<>());
            }
        }
        public void set(int idx, int val) {
            // sanity check and exception process
            map.get(idx).put(version, val);
        }
        public int get(int idx, int version) {
            // sanity check and exception process
            Integer key = map.get(idx).floorKey(version);
            if(key == null) return Integer.MIN_VALUE;
            return map.get(idx).get(key);
        }
        public void snapshot() {
            version++;
        }
    }
}
