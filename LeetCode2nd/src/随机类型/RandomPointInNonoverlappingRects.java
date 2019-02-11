package 随机类型;

import java.util.Random;

public class RandomPointInNonoverlappingRects {
    private int[] pSum;
    private int[][] r;
    Random rand = new Random();
    public RandomPointInNonoverlappingRects(int[][] rects) {
        r = rects;
        pSum = new int[rects.length];
        pSum[0] = getArea(rects[0]);
        for (int i = 1; i < rects.length; i++) {
            pSum[i] = pSum[i - 1] + getArea(rects[i]);
        }
    }

    public int[] pick() {
        int cand = rand.nextInt(pSum[pSum.length - 1]);
        int lo = 0;
        int hi = pSum.length - 1;
        while (lo != hi) {
            int m = (lo + hi) / 2;
            if (pSum[m] > cand) {
                hi = m;
            } else {
                lo = m + 1;
            }
        }
        int[] ans = r[lo];
        int w = ans[2] - ans[0] + 1;
        int h = ans[3] - ans[1] + 1;
        int base = pSum[lo] - w * h;
        return new int[]{ans[0] + (cand - base) % w, ans[1] + (cand - base) / w};
    }

    private int getArea(int[] rect) {
        int a = Math.abs(rect[0] - rect[2]);
        int b = Math.abs(rect[1] - rect[3]);
        return a * b;
    }
}
