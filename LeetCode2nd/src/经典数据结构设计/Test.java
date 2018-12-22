package 经典数据结构设计;

import java.util.*;

public class Test {

    public static void main(String[] args){
    int[][] input = new int[][]{{1,1},{1,3},{3,1},{3,3},{2,2}};
        //System.out.println(minAreaRect(input));
        String[] grid = new String[]{"@..aA","..B#.","....b"};
        System.out.println(shortestPathAllKeys(grid));
    }

    public static int shortestPathAllKeys(String[] grid) {
        int M = grid.length;
        int L = 30;
        int N = grid[0].length();
        Set<Character> target = new HashSet<>();
        char[][] matrix = new char[M][N];
        int start = -1;
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                if(grid[i].charAt(j) >= 'a' && grid[i].charAt(j) <= 'z'){
                    target.add(grid[i].charAt(j));
                }
                if(grid[i].charAt(j) == '@') start = i * L + j;
                matrix[i][j] = grid[i].charAt(j);
            }
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        int[] dir = new int[]{-1, 0, 1, 0, -1};
        Set<Character> keys = new HashSet<>();
        Set<Integer> seen = new HashSet<>();
        int level = 0;
        while(!q.isEmpty() && !target.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                int cur = q.poll();
                int x = cur / L;
                int y = cur % L;
                for(int j = 0; j < dir.length - 1; j++){
                    int xx = x + dir[j];
                    int yy = y + dir[j + 1];
                    if(xx >= 0 && xx < M && yy >= 0 && yy < N && !seen.contains(xx * L + yy)){
                        if(matrix[xx][yy] == '.'){
                            q.offer(xx * L + yy);
                            seen.add(xx * L + yy);
                        }else if(matrix[xx][yy] >= 'A' && matrix[xx][yy] <= 'Z'
                                && keys.contains(Character.toLowerCase(matrix[xx][yy]))){
                            q.offer(xx * L + yy);
                            seen.add(xx * L + yy);
                        }else if(matrix[xx][yy] >= 'a' && matrix[xx][yy] <= 'z'){
                            target.remove(matrix[xx][yy]);
                            keys.add(matrix[xx][yy]);
                            q.offer(xx * L + yy);
                            seen.add(xx * L + yy);
                        }
                    }
                }
                level ++;
            }
        }

        return target.isEmpty() ? level : -1;
    }
}
