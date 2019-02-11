package Google面经;

import java.util.Iterator;
import java.util.List;

public class BlueRedTree {
    class Node {
        List<Node> children;
        boolean isRed;
        public Node(boolean isRed) {
            this.isRed = isRed;
        }
    }

    public void dfsHelp (Node root, List<Node> res) {
        if (root == null) {
            return;
        }
        if (root.isRed) {
            for (Node child : root.children) {
                if (!child.isRed) {
                    res.add(child);
                }
                dfsHelp(child, res);
            }
        } else {
            Iterator<Node> it = root.children.iterator();
            while (it.hasNext()) {
                Node child = it.next();
                if (child.isRed) {
                    root.children.remove(child);
                }
                dfsHelp(child, res);
            }
        }
    }

    public Node helper(Node root, List<Node> ans) {
        if (root == null) return null;
        if (root.isRed) {
            for (Node node : root.children) {
                Node cur = helper(node, ans);
                if (cur != null) ans.add(cur);
            }
            return null;
        } else {
            for (Node node : root.children) {
                if (helper(node, ans) == null) {
                    root.children.remove(node);
                }
            }
            return root;
        }
    }

}
