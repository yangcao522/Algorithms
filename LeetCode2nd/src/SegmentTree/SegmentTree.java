package SegmentTree;

/**
 * 讲义链接：https://zxi.mytechroad.com/blog/category/sp/
 * API Interface:
 * 1) build(start, end) -> T(N) = 2 * T(N / 2) = O(N)
 * 2) update(root, index, val) -> T(N) = T(N / 2) + O(1) = log(N)
 * 3) query(start, end) -> log(N)
 * 以区间求和为🌰
 */
public class SegmentTree {
    class Node {
        Node left;
        Node right;
        int sum;
        int s;
        int e;
        public Node(int s, int e, int sum) {
            this.s = s;
            this.e = e;
            this.sum = sum;
        }
    }
    int[] nums;
    Node root;
    public SegmentTree(int[] nums) {
        this.nums = nums;
        root = build(0, nums.length);
    }

    public Node build(int s, int e) {
        if (s == e) return new Node(s, e, nums[s]);
        int mid = (e - s) / 2 + s;
        Node leftNode = build(s, mid);
        Node rightNode = build(mid + 1, e);
        Node root = new Node(s, e, leftNode.sum + rightNode.sum);
        root.left = leftNode;
        root.right = rightNode;
        return root;
    }

    //update 一个 index
    public void update(Node root, int index, int val) {
        if (root == null) return;
        if (root.s == root.e && root.s == index) {
            root.sum = val;
            return;
        }
        int mid = (root.e - root.s) / 2 + root.s;
        if (index <= mid) {
            update(root.left, index, val);
        } else {
            update(root.right, index, val);
        }
        int leftSum = root.left == null ? 0 : root.left.sum;
        int rightSum = root.right == null ? 0 : root.right.sum;
        root.sum = leftSum + rightSum;
    }

    public int query(Node root, int s, int e) {
        if (root == null) return 0;
        if (root.s == s && root.e == e) {
            return root.sum;
        }
        int mid = (root.e - root.s) / 2 + root.s;
        if (e <= mid) {
            return query(root.left, s, e);
        } else if (s > mid) {
            return query(root.right, s, e);
        } else {
            return query(root.left, s, mid) + query(root.right, mid + 1, e);
        }
    }
}
