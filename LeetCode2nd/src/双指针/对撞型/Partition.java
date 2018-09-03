package 双指针.对撞型;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 类似于QuickSort的思想
 */
public class Partition {
    /**
     * 215. Kth Largest Element in an Array
     * QuickSelect 在1D上的应用
     * 平均时间复杂度 O(logn)
     */
    public int findKthLargest(int[] nums, int k) {
        QuickSelect(nums, k, 0, nums.length - 1);
        return nums[k - 1];
    }

    private void QuickSelect(int[] nums, int k, int i, int j){
        int left = i;
        int right = j - 1;
        int povit = nums[right];
        int tmp;
        while(left <= right){
            while(left <= right && nums[left] > povit){
                left ++;
            }
            while(left <= right && nums[right] < povit){
                right --;
            }
            if(left <= right){
                tmp = nums[left];
                nums[left] = nums[j];
                nums[j] = tmp;
                left ++;
                right --;
            }
        }
        tmp = nums[left];
        nums[left] = nums[j];
        nums[j] = tmp;
        if(left == k - 1) return;
        else if(left < k - 1){
            QuickSelect(nums, k, left + 1, j);
        }else{
            QuickSelect(nums, k, i, left - 1);
        }
    }

    /**
     * 378. Kth Smallest Element in a Sorted Matrix
     * 这一题和215放在一起的目的是，这一题是第k大元素的2D版本
     */
    /**
     * 解法一: heap O(n + klog(n))
     */
    public int kthSmallest(int[][] matrix, int k) {
        class Triplet{
            int x;
            int y;
            int val;
            public Triplet(int x, int y, int val){
                this.x = x;
                this.y = y;
                this.val = val;
            }
            public int compareTo(Triplet t){
                return this.val - t.val;
            }
        }
        int n = matrix.length;
        Queue<Triplet> q = new PriorityQueue<Triplet>();
        for(int i = 0; i < n; i++){
            q.offer(new Triplet(0, i, matrix[0][i]));
        }
        Triplet res = new Triplet(-1,-1,-1);
        for(int i = 0; i < k; i++){
            res = q.poll();
            if(res.x < n - 1){
                q.offer(new Triplet(res.x + 1, res.y, matrix[res.x + 1][res.y]));
            }
        }
        return res.val;
    }
    /**
     * 关联题目：373. Find K Pairs with Smallest Sums
     * 相似的heap解法。
     */

    /**
     * 快排的本质是2-way partition
     * 这里是3-way partition 的例子
     */
    public static void threewayPartition(int[] arr, int low, int high){
        int len = arr.length;
        int left = 0;
        int right = len - 1;
        int start = left;
        while(start <= right){
            if(arr[start] < low){
                swap(arr, start ++, left ++);
            }else if(arr[start] > high){
                swap(arr, start, right --);
            }else{
                start ++;
            }
        }
    }

    private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        return;
    }

    /**
     * 75. Sort Colors
     * 3-way partition O(n)
     */
    public void sortColors(int[] nums) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        int start = left;
        while(start <= right){
            if(nums[start] == 0){
                swap(nums, start ++, left ++);
            }else if(nums[start] == 2){
                swap(nums, start, right --);
            }else{
                start ++;
            }
        }
    }

    /**
     * https://www.lintcode.com/problem/sort-colors-ii/description
     * 143. Sort Colors II
     * Given an array of n objects with k different colors (numbered from 1 to k), sort them so that objects of the same color are adjacent, with the colors in the order 1, 2, ... k.
     * Given colors=[3, 2, 2, 1, 4], k=4, your code should sort colors in-place to [1, 2, 2, 3, 4].
     */
    public void sortColors2(int[] nums, int k){
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        int cur;
        int start = 1;
        int end = k;
        while(start < end){
            cur = left;
            while(cur <= right){
                if(nums[cur] == start){
                    swap(nums, start ++, left ++);
                }else if(nums[cur] == end){
                    swap(nums, start, right --);
                }else{
                    start ++;
                }
            }
            start ++;
            end --;
        }
    }

    /**
     * https://www.lintcode.com/problem/sort-letters-by-case/description
     * 49. Sort Letters by Case
     * Given a string which contains only letters. Sort it by lower case first and upper case second.
     * 这里使用的是2-way partition
     */
    public static void sortLetters(char[] chars) {
        int left = 0;
        int right = chars.length - 1;

        while(left <= right){
            while(left <= right && chars[left] < 'a'){
                left ++;
            }
            while(left <= right && chars[right] >= 'a'){
                right --;
            }
            if(left <= right)
                swap(chars, left, right);
        }
    }

    private static void swap(char[] chars, int a, int b){
        char temp = chars[a];
        chars[a] = chars[b];
        chars[b] = temp;
        return;
    }

    public static void main(String[] args){
        char[] arr = new char[]{'a', 'B', 'c', 'D', 'e', 'F', 'g'};
        sortLetters(arr);
        for(char num : arr){
            System.out.print(num + " ");
        }
    }


}































