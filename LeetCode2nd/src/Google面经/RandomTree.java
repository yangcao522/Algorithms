package Google面经;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomTree {
    class TreeNode {
        List<TreeNode> children;
        int val;
        public TreeNode(int val) {
            this.val = val;
            children = new ArrayList<>();
        }
    }

    public TreeNode getRandomTree(int N) {
        List<TreeNode> pool = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            pool.add(new TreeNode(i));
        }
        List<TreeNode> nodes = new ArrayList<>();
        Random rand = new Random();
        int rootIndex = rand.nextInt(N);
        TreeNode root = pool.get(rootIndex);
        pool.remove(rootIndex);
        while (nodes.size() < N) {
            int index = rand.nextInt(pool.size());
            int parentIdx = rand.nextInt(nodes.size());
            TreeNode cand = pool.get(index);
            TreeNode parent = nodes.get(rootIndex);
            parent.children.add(cand);
            pool.remove(index);
            nodes.add(cand);
        }
        return nodes.get(0);
    }
}
