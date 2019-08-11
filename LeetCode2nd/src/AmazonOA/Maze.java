package AmazonOA;

import java.util.*;

public class Maze {
    static int removeObstacle(
            int numRows,
            int numColumns,
            List<List<Integer>> lot
    ) {
        boolean[][] v = new boolean[numRows][numColumns];
        int[] dir = new int[]{-1, 0, 1, 0, -1};
        v[0][0] = true;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});

        int dist = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            dist ++;
            for (int i = 0; i < size; i ++) {
                int[] cur = q.poll();
                int x = cur[0];
                int y = cur[1];
                for (int d = 0; d < 4; d ++) {
                    int cx = dir[d] + x;
                    int cy = dir[d + 1] + y;
                    if (!isValid(cx, cy, numRows, numColumns)) continue;
                    if (lot.get(cx).get(cy) == 0) continue;
                    if (lot.get(cx).get(cy) == 9) return dist;
                    if (v[cx][cy]) continue;
                    v[cx][cy] = true;
                    q.offer(new int[]{cx, cy});
                }
            }
        }
        return -1;
    }

    private static boolean isValid(int x, int y, int m, int n) {
        if (x < 0 || x >= m || y < 0 || y >= n) return false;
        return true;
    }

    public static void main(String[] args) {
        List<List<Integer>> lot = Arrays.asList(Arrays.asList(1, 0, 0), Arrays.asList(1, 0, 0), Arrays.asList(1, 9, 1));
        System.out.println(removeObstacle(3, 3, lot));
    }
}
