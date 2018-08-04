package 设计相关;

import java.util.HashMap;
import java.util.Map;

/**
 * 首先确定思想，如何实现LRU。
 * 维护一个线性结构的，最不经常使用的数据放在这个结构的最末尾，最近使用的数据放在最头部。
 * 当一个数据又被使用的时候，将这个数据从中间删除，放到头部。
 * 为了减少时间复杂度，这里的删除和放置头部所需要的复杂度不能是线性的，因此双向链表可以达到这个要求。
 * 同时，怎样凭借一个key能快速定位到这个节点呢？所以可以使用HashMap，key就是题目要求的key，value就是双向链表的node。
 */
public class LRU {
	
	class Node{
		public int key;
		public int value;
		public Node pre;
		public Node next;
		public Node(int key, int value){
			this.key = key;
			this.value = value;
		}
	}
	int count;
	int capacity;
	Map<Integer, Node> map;
	Node head;
	Node tail;
	
	public LRU(int capacity){
		count = 0;
		this.capacity = capacity;
		map = new HashMap<>();
		head = new Node(0, 0);
		tail = new Node(0, 0);
		head.next = tail;
		head.pre = null;
		tail.pre = head;
		tail.next = null;
	}
	
	/**
	 * 正是要删除节点，所以要使用双向链表。
	 */
	public void deleteNode(Node tmp){
		tmp.pre.next = tmp.next;
		tmp.next.pre = tmp.pre;
	}
	
	public void addToHead(Node tmp){
		head.next.pre = tmp;
		tmp.next = head.next;
		head.next = tmp;
		tmp.pre = head;
	}
	
	/**
	 * 只要被 get 或者 put 过, 都意味着当前cache被使用过一次。
	 */
	public int get(int key){
		Node tmp = map.get(key);
		if(tmp == null) return -1;
		deleteNode(tmp);
		addToHead(tmp);
		return tmp.value;
	}
	
	public void put(int key, int value){
		if(map.get(key) != null){
			Node tmp = map.get(key);
			tmp.value = value;
			deleteNode(tmp);
			addToHead(tmp);
		}else{
			Node tmp = new Node(key, value);
			if(count < capacity){
				count ++;
				addToHead(tmp);
			}else{
				map.remove(tail.pre.key);
				deleteNode(tail.pre);
				addToHead(tmp);
			}
		}
	}
}
