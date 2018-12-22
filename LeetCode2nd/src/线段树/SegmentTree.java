package 线段树;

class SegmentTreeNode {
    public int start, end, max;
    public SegmentTreeNode left, right;
    public SegmentTreeNode(int start, int end, int max) {
        this.start = start;
        this.end = end;
        this.max = max;
        this.left = this.right = null;
    }
}
public class SegmentTree {
    /**
     * build
     */
    public SegmentTreeNode build(int[] A) {
        return buildTree(0, A.length - 1, A);
    }

    public SegmentTreeNode buildTree(int start, int end, int[] A) {
        if (start > end)
            return null;
        if (start == end) {
            return new SegmentTreeNode(start, end, A[start]);
        }
        SegmentTreeNode node = new SegmentTreeNode(start, end, A[start]);
        int mid = (start + end) / 2;
        node.left = this.buildTree(start, mid, A);
        node.right = this.buildTree(mid + 1, end, A);
        if (node.left != null && node.left.max > node.max)
            node.max = node.left.max;
        if (node.right != null && node.right.max > node.max)
            node.max = node.right.max;
        return node;
    }

    /**
     * query
     */
    public int query(SegmentTreeNode root, int start, int end) {
        if(start == root.start && root.end == end) { // 相等
            return root.max;
        }
        int mid = (root.start + root.end)/2;
        int leftmax = Integer.MIN_VALUE, rightmax = Integer.MIN_VALUE;
        // 左子区
        if(start <= mid) {
            if( mid < end) { // 分裂
                leftmax =  query(root.left, start, mid);
            } else { // 包含
                leftmax = query(root.left, start, end);
            }
        }
        // 右子区
        if(mid < end) { // 分裂 3
            if(start <= mid) {
                rightmax = query(root.right, mid+1, end);
            } else { //  包含
                rightmax = query(root.right, start, end);
            }
        }
        // else 就是不相交
        return Math.max(leftmax, rightmax);
    }

    /**
     * update
     */
    public void modify(SegmentTreeNode root, int index, int value) {
        if(root.start == index && root.end == index) { // 查找到
            root.max = value;
            return;
        }
        // 查询
        int mid = (root.start + root.end) / 2;
        if(root.start <= index && index <= mid) {
            modify(root.left, index, value);
        }
        if(mid < index && index <= root.end) {
            modify(root.right, index, value);
        }
        //更新
        root.max = Math.max(root.left.max, root.right.max);
    }
}
