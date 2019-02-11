package Google面经;

public class EvenGreaterThanOdd {
    public void reform(int[] nums){
        if (nums == null || nums.length == 0) return;
        int N = nums.length;
        int l = 0;
        int r = nums.length - 1;
        int mid = (l + r) / 2;
        reverse(nums, mid, r);
        if (N % 2 == 0) shuffle(nums, l, r);
        else shuffle(nums, l, r - 1);

    }

    private void shuffle(int[] nums, int left, int right) {
        if (left >= right) return;
        if (left + 1 == right) {
            swap(nums, left, right);
            return;
        }
        int len = right - left + 1;
        int mid = left + len / 2;
        int lMid = left + len / 4;
        int rMid = mid + len / 4;
        reverse(nums, lMid, mid - 1);
        reverse(nums, mid, rMid);
        reverse(nums, lMid, rMid);
        shuffle(nums, left, left + (len / 4) * 2 - 1);
        shuffle(nums, left + (len / 4) * 2, right);
    }

    private void reverse(int[] nums, int l, int r) {
        while (l < r) {
            swap(nums, l, r);
            l ++;
            r --;
        }
    }

    private void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }
}
