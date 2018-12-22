package 贪心算法;

import java.util.Arrays;
import java.util.Collections;

public class Test {
    private static int[] dir = new int[]{-1, 0, 1, 0, -1};
    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        helper(image, sr, sc, newColor, image[sr][sc]);
        return image;
    }

    private static  void helper(int[][] image, int i, int j, int newColor, int oldColor){
        if(image[i][j] != oldColor) return;
        image[i][j] = newColor;
        for(int d = 0; d < 4; d++){
            int x = dir[d] + i;
            int y = dir[d + 1] + j;
            if(x < 0 || x >= image.length || y < 0 || y >= image[0].length) continue;
            helper(image, x, y, newColor, oldColor);
        }
    }
	
	public static void main(String[] args) {

        floodFill(new int[][]{{0,0,0}, {0,1,1}}, 1, 1, 1);
	}
}
