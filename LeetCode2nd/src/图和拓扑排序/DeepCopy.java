package 图和拓扑排序;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeepCopy {

    class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>(); }
    }

    /**
     * 无向图deep copy
     * 这边的hashmap用得很好，起到两个作用：
     * 1）无向图访问中充当visit[]的作用
     * 2) 取到对应的node，设置其临接node(非主要作用)
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null)
            return null;
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        map.put(node, newNode);

        DFS(map, node);
        return newNode;
    }

    private void DFS(Map<UndirectedGraphNode, UndirectedGraphNode> map, UndirectedGraphNode node){
        if(null == node)
            return;
        for(UndirectedGraphNode x : node.neighbors){
            if(map.get(x) == null){
                map.put(x, new UndirectedGraphNode(x.label));
                DFS(map, x);
            }
            map.get(node).neighbors.add(map.get(x));
        }
    }

    /**
     * deep copy一个无向图成有向图，方向是从value小的node指向value大的
     */
    public UndirectedGraphNode cloneGraph2Directed(UndirectedGraphNode node) {
        if(node == null)
            return null;
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        map.put(node, newNode);

        DFS(map, node);
        return newNode;
    }

    private void DFS2Directed(Map<UndirectedGraphNode, UndirectedGraphNode> map, UndirectedGraphNode node){
        if(null == node)
            return;
        for(UndirectedGraphNode x : node.neighbors){
            if(map.get(x) == null){
                map.put(x, new UndirectedGraphNode(x.label));
                DFS(map, x);
            }
            if(node.label < x.label) {
                map.get(node).neighbors.add(map.get(x));
            }
        }
    }

}
