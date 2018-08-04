package UnionFind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfIsland {
	private class unionfind {
		Map<Integer, Integer> parents = new HashMap<>();
		Map<Integer, Integer> size = new HashMap<>();
		int count = 0;
		public Integer find(int num) {
			if(!parents.containsKey(num)) {
				return null;
			}
			Integer root = num;
			while(root != parents.get(root)) {
				root = parents.get(root);
			}
			
			while(num != root) {
				Integer next =  parents.get(num);
				parents.put(next, root);
				num = next;
			}
			return root;
		}
		public void union(Integer a, Integer b) {
			Integer aRoot = find(a);
			Integer bRoot = find(b);
			if(aRoot == null || bRoot == null) return;
			if(aRoot.equals(bRoot)) return;
			
			int aSize = size.get(a);
			int bSize = size.get(b);
			
			if(aSize > bSize) {
				parents.put(bRoot, aRoot);
				size.put(bRoot, aSize + bSize);
			}else {
				parents.put(aRoot, bRoot);
				size.put(aRoot, aSize + bSize);
			}
			count --;
		}
		public void add(Integer num) {
			if(!parents.containsKey(num)) {
				parents.put(num, num);
				size.put(num, 1);
				count ++;
			}
		}
		public Integer getCount() {
			return this.count;
		}
	}
	public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> list = new ArrayList<Integer>();
        if(positions == null || positions.length == 0) return list;
        unionfind uf = new unionfind();

        for(int i = 0; i < positions.length; i++){
            int x = positions[i][0];
            int y = positions[i][1];
            int index = x * n + y;
            uf.add(index);

            if(x + 1 < m)   uf.union(index, (x + 1) * n + y);
            if(x > 0)       uf.union(index, (x - 1) * n + y);
            if(y + 1 < n)   uf.union(index, x * n + y + 1);
            if(y > 0)       uf.union(index, x * n + y - 1);

            list.add(uf.getCount());
        }

        return list;
    }
}
