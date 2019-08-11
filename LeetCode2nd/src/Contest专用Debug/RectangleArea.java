package Contest专用Debug;

import java.util.*;

class RectangleArea {
    public int rectangleArea(int[][] rectangles) {
        int OPEN = 0, CLOSE = 1;
        int[][] events = new int[rectangles.length * 2][];
        int t = 0;
        for (int[] r : rectangles) {
            events[t ++] = new int[]{r[1], OPEN, r[0], r[3]};
            events[t ++] = new int[]{r[1], CLOSE, r[0], r[3]};
        }

        Arrays.sort(events, (a, b) -> (a[0] - b[0]));

        List<int[]> active = new ArrayList<>();
        int prev_y = events[0][0];
        int ans = 0;

        for (int[] e : events) {
            int cur_y = e[0], x1 = e[2], x2 = e[3], eventType = e[1];

            //merge
            int mergedRes = 0;
            int cur = -1;
            for (int[] a : active) {
                cur = Math.max(cur, a[0]);
                mergedRes += (a[1] - cur);
                cur = Math.max(cur, a[1]);
            }

            ans += mergedRes * (cur_y - prev_y);

            if (eventType == OPEN) {
                active.add(new int[]{x1, x2});
                Collections.sort(active, (a, b) -> (a[0] - b[0]));
            } else {
                for (int[] a : active) {
                    if (x1 == a[0] && x2 == a[1]) {
                        active.remove(a);
                        break;
                    }
                }
            }

            prev_y = cur_y;
        }

        ans %= 1_000_000_007;
        return (int) ans;
    }
}
