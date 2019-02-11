package 复杂BFS;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathGetAllKeys {
    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length;
        int n = grid[0].length();
        int all_keys = 0;
        Queue<Integer> q = new LinkedList<>();
        int[][][] seen = new int[m][n][64];
        // Init
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j) {
                char c = grid[i].charAt(j);
                if (c == '@') {
                    q.offer((j << 16) | (i << 8));
                    seen[i][j][0] = 1;
                } else if (c >= 'a' && c <= 'f') {
                    all_keys |= (1 << (c - 'a'));
                }
            }
        int[] dirs = new int[]{-1, 0, 1, 0, -1};
        int steps = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int s = q.poll();
                int x = s >> 16;
                int y = (s >> 8) & 0xff;
                int keys = s & 0xff;
                if (keys == all_keys) return steps;
                for (int i = 0; i < 4; ++i) {
                    int nkeys = keys;
                    int nx = x + dirs[i];
                    int ny = y + dirs[i + 1];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                    char c = grid[ny].charAt(nx);
                    if (c == '#') continue; // Wall
                    // Do not have the key.
                    if (c >= 'A' && c <= 'F' && (keys & (1 << (c - 'A'))) == 0) continue;
                    // Update the keys we have.
                    if (c >= 'a' && c <= 'f') nkeys |= (1 << (c - 'a'));
                    if (seen[ny][nx][nkeys] == 1) continue;
                    q.offer((nx << 16) | (ny << 8) | nkeys);
                    seen[ny][nx][nkeys] = 1;
                }
            }
            ++steps;
        }
        return -1;
    }
}
