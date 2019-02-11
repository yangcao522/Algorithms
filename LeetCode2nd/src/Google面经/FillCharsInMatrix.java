package Google面经;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class FillCharsInMatrix {
    /**
     * 一个矩阵，里面有空字符和abc等character，用最近的字符填满矩阵。
     * A      ‘’       ‘’        ‘’        ‘’
     * ‘’       ‘’       ‘’        ‘’        ‘’
     * ‘’       ‘’       ‘’        B       ‘’
     * 空字符填上最近字符，有tie的话随便选一个
     * follow up：char比空字符多
     */
    class Node {
        int i;
        int j;
        char c;
        int dist;
        public Node(int i, int j, char c, int dist) {
            this.i = i;
            this.j = j;
            this.c = c;
            this.dist = dist;
        }
    }

    public void fillMatrix(char[][] matrix) {
        Queue<Node> q = new PriorityQueue<>(new Comparator<Node>(){
            public int compare(Node a, Node b) {
                return a.dist - b.dist;
            }
        });
        int M = matrix.length;
        int N = matrix[0].length;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] != ' ') {
                    q.offer(new Node(i, j, matrix[i][j], 0));
                }
            }
        }

        int[] dir = new int[]{-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (matrix[cur.i][cur.j] == ' ') matrix[cur.i][cur.j] = cur.c;
            for (int i = 0; i < dir.length; i++) {
                int x = cur.i + dir[i];
                int y = cur.j + dir[i + 1];
                if (x < 0 || x >= M || y < 0 || y >= N) continue;
                if (matrix[x][y] != ' ') continue;
                q.offer(new Node(x, y, cur.c, cur.dist + 1));
            }
        }
    }
}
