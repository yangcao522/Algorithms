package 排序或优先队列问题;

import java.util.*;

public class Skyline {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> sides = new ArrayList<>();
        for(int[] b: buildings) {
            // open side
            sides.add(new int[]{b[0], b[2]});
            // close side
            sides.add(new int[]{b[1], -b[2]});
        }

        Collections.sort(sides, (a, b) -> {
            if(a[0] != b[0]) return a[0] - b[0];
            else return b[1] - a[1];
        });
        List<int[]> res = new ArrayList<>();
        // sweeping line, the height of points on the line
        Queue<Integer> line = new PriorityQueue<>(Collections.reverseOrder());

        for(int[] side: sides) {
            if (side[1] > 0) {
                if (line.isEmpty() || side[1] > line.peek())
                    res.add(new int[]{side[0], side[1]});
                line.offer(side[1]);
            } else {
                line.remove(-side[1]);
                if (side[0] > line.peek()) {
                    res.add(new int[]{side[0], line.peek()});
                }
            }
        }
        return res;
    }

}
