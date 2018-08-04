package 二分搜索;

public class Search2DMatrix {
	/**
	 * 74. Search a 2D Matrix
	 * 将一维转换成2维 -> 二分搜索
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) return false;
        int n = matrix.length;
        int m = matrix[0].length;
        int[] nums = new int[n * m];
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                nums[i*m + j] = matrix[i][j];
            }
        }
        
        int start = 0;
        int end = nums.length - 1;
        
        while(start <= end){
            int mid = (end - start)/2 + start;
            if(nums[mid] == target){
                return true;
            }else if(nums[mid] < target){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return false;
    }
	
	/**
	 * 240. Search a 2D Matrix II
	 * 从第一行的最右端开始搜索，要是target比当前小，则往左搜索（右半边完全不需要搜索了，一定比它大）
	 * 要是target大于当前的就往下搜索（左上角的完全不用搜索了，一定比它小）
	 */
	public boolean searchMatrixII(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) return false;
        int row = 0;
        int col = matrix[0].length - 1;
        
        while(row < matrix.length && col >= 0){
            if(matrix[row][col] == target){
                return true;
            }else if(matrix[row][col] > target){
                col--;
            }else{
                row++;
            }
        }
        return false;
    }

}
