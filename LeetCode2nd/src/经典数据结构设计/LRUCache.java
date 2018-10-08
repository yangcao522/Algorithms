package 经典数据结构设计;

import java.util.HashMap;
import java.util.Map;

/**
 * 这一题是不是应该30分钟bug free????
 */
public class LRUCache {
    class Node{
        Node prev;
        Node next;
        int key;
        int value;
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    Node dummyHead;
    Node dummyTail;
    int capacity;
    Map<Integer, Node> map;

    public LRUCache(int capacity) {
        dummyHead = new Node(-1, -1);
        dummyTail = new Node(-1, -1);
        this.capacity = capacity;
        map = new HashMap<>();
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        Node cur = map.get(key);
        delete(key);
        addToHead(cur);
        return cur.value;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node cur = map.get(key);
            cur.value = value;
            delete(key);
            addToHead(cur);
        }else{
            Node newNode = new Node(key, value);
            if(map.size() >= capacity){
                Node toRemove = dummyTail.prev;
                delete(toRemove.key);
                map.remove(toRemove.key);
            }
            map.put(key, newNode);
            addToHead(newNode);
        }
    }

    private void delete(int key){
        Node cur = map.get(key);
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
        cur.prev = null;
        cur.next = null;
    }

    private void addToHead(Node node){
        dummyHead.next.prev = node;
        node.next = dummyHead.next;
        dummyHead.next = node;
        node.prev = dummyHead;
    }
}
