package 双指针.对撞型;

public class ContainerWithMOstWater {
    //lint code
    public static int maxArea(int[] heights) {
        // write your code here
        if(heights == null || heights.length == 0) return 0;

        int left = 0;
        int right = heights.length - 1;
        int max = Integer.MIN_VALUE;
        while(left < right){
            if(heights[left] < heights[right]){
                max = Math.max(max, (right - left) * heights[left]);
                left ++;
            }else{
                max = Math.max(max, (right - left) * heights[right]);
                right --;
            }
        }
        return max;
    }

    //42. Trapping Rain Water
    public int trap(int[] height) {
        if(height == null || height.length == 0) return 0;
        int i = 0;
        int j = height.length - 1;
        int leftMax = height[i];
        int rightMax = height[j];
        int trapped = 0;
        while(i < j){
            if(height[i] < height[j]){ //和上面一题一样的思路，假如左边的板的高度小于右边的板，那么左边到右边的水都能续住。
                if(height[i] > leftMax){
                    leftMax = height[i];
                }else{
                    trapped += (leftMax - height[i]);
                }
                i++;
            }else{
                if(height[j] > rightMax){
                    rightMax = height[j];
                }else{
                    trapped += (rightMax - height[j]);
                }
                j--;
            }
        }
        return trapped;
    }


    public static void main(String[] args){
//        System.out.println(maxArea(new int[]{1,3,2,4,6,9,0,3}));
    }
}
