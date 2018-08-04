package UnionFind;
import java.util.HashMap;

public class WeightedUnionFind {
	private HashMap<Integer, Integer> parent;
    private HashMap<Integer, Integer> size;
    private int maxSize;

    public WeightedUnionFind(){}
    public WeightedUnionFind(int[] nums){
        int N = nums.length;
        parent = new HashMap<Integer, Integer>();
        size = new HashMap<Integer, Integer>();
        maxSize = 1;

        for(int i = 0; i < N; i++){
            parent.put(nums[i], nums[i]);
            size.put(nums[i], 1);
        }
    }

    private int getMaxSize(){
        return this.maxSize;
    }

    // With path compression
    //worst o(n)
    public Integer find(Integer num){
        if(!parent.containsKey(num)) return null;
        //root 就是当前 num 的根节点
        Integer root = num;
        while(root != parent.get(root)){
            root = parent.get(root);
        }
        //num 可能是通过一些 parent 节点到达 root 的，因此将他所有的 parent 节点的根节点全部更新为 root
        while(num != root){
            Integer next = parent.get(num);
            parent.put(num, root);
            num = next;
        }

        return root;
    }
    //O(1)
    public void union(int p, int q){
        Integer pRoot = find(p);
        Integer qRoot = find(q);

        if(pRoot == null || qRoot == null) return;
        if(pRoot == qRoot) return;

        int pSize = size.get(pRoot);
        int qSize = size.get(qRoot);

        if(pSize > qSize){
            parent.put(qRoot, pRoot);
            size.put(pRoot, pSize + qSize);
            maxSize = Math.max(maxSize, pSize + qSize);
        } else {
            parent.put(pRoot, qRoot);
            size.put(qRoot, pSize + qSize);
            maxSize = Math.max(maxSize, pSize + qSize);
        }
    }
}
