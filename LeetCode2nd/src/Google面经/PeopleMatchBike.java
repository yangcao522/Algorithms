package Google面经;

import java.util.*;

public class PeopleMatchBike {
    final int N = 10000;
    class Node {
        int bikePos;
        int peoplePos;
        int distance;
        public Node(int x1, int y1, int x2, int y2) {
            bikePos = x1 * N + y1;
            peoplePos = x2 * N + y2;
            distance = getDistance(x1, y1, x2, y2);
        }
        private int getDistance(int x1, int y1, int x2, int y2) {
            return Math.abs(x1 - x2) + Math.abs(y1 - y2);
        }
    }
    Set<Integer> bikes = new HashSet<>();
    Set<Integer> peoples = new HashSet<>();
    Queue<Node> q = new PriorityQueue<>((a, b) -> a.distance - b.distance);

    /**
     * O(m * n * log(m * n))
     * @param matrix
     */
    public void init(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 'B') bikes.add(i * N + j);
                if (matrix[i][j] == 'P') peoples.add(i * N + j);
            }
        }
        for (int b : bikes) {
            for (int p : peoples) {
                q.offer(new Node(b / N, b % N, p / N, p % N));
            }
        }
    }

    /**
     * O(m * n)
     */
    public List<Node> getPostions() {
        List<Node> ans = new ArrayList<>();
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (bikes.contains(cur.bikePos) && peoples.contains(cur.peoplePos)) {
                ans.add(cur);
                bikes.remove(cur.bikePos);
                peoples.remove(cur.peoplePos);
            }
        }
        return ans;
    }

    public void printNode(List<Node> nodes) {
        for (Node node : nodes) {
            System.out.println(node.bikePos / N + ":"+ node.bikePos % N + "-" + node.peoplePos / N + ":" + node.peoplePos % N);
        }
    }

    public static void main(String[] args) {
        PeopleMatchBike pmb = new PeopleMatchBike();
        pmb.init(new char[][]{
                {'O', 'P', 'O', 'B', 'O', 'O', 'P'},
                {'O', 'O', 'O', 'B', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'B', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'B', 'O', 'O', 'O'},
                {'O', 'P', 'O', 'B', 'O', 'O', 'P'},
                {'O', 'O', 'O', 'B', 'O', 'O', 'P'},
                {'O', 'P', 'O', 'B', 'O', 'O', 'P'}
        });
        pmb.printNode(pmb.getPostions());
    }
}
