package 单调栈或队列;

import java.util.Stack;

public class LargestRectangle {
	/**
	 * 84. Largest Rectangle in Histogram
	 * 1维：维护一个单调递增的stack
	 */
	public static int largestRectangleArea(int[] heights) {
		Stack<Integer> s = new Stack<>();
        int[] height = new int[heights.length + 1];
        //为了方便处理，在末尾追加高度为0的bar
        for (int i = 0; i < height.length - 1; i++){
        	height[i] = heights[i];
        }
        int sum = 0;
        int i = 0;
        while (i < height.length) {
            if (s.isEmpty() || height[i] > height[s.peek()]) {
                s.push(i);
                i++;
            } else {
                int t = s.pop();
                //这里还需要考虑stack为空的情况
                sum = Math.max(sum, height[t] * (s.isEmpty() ? i : i - s.peek() - 1));
            }
        }
        return sum;
    }
	
	/**
	 * 85. Maximal Rectangle
	 */
	public static int maximalRectangle(char[][] matrix){
		int res = 0;
		int[] height = new int[matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < height.length; j++) {
				if (matrix[i][j] == 1) {
					height[j] += 1;
				} else {
					height[j] = 0;
				}
			}
			res = Math.max(res, largestRectangleArea(height));//在这个地方可以记录矩形的下沿
		}
		return res;
	}
}
