package BinaryIndexedTree;

public class BIT {

    int[] sum;
    public void update(int i, int delta) {
        while (i < sum.length) {
            sum[i] += delta;
            i += lowbit(i);
        }
    }

    public int query(int i) {
        int ans = 0;
        while (i > 0) {
            ans += sum[i];
            i -= lowbit(i);
        }
        return ans;
    }

    public BIT(int N) {
        sum = new int[N + 1];
    }

    private int lowbit(int x) {
        return x & -x;
    }
}
