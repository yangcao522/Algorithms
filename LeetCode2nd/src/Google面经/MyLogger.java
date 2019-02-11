package Google面经;

import java.util.HashMap;
import java.util.Map;

public class MyLogger {
    private class Node {
        String id;
        long start;
        long end;
        Node prev;
        Node next;
        public Node(String id, long start) {
            this.id = id;
            this.start = start;
            end = -1;
        }
    }
    Node head, tail;
    Map<String, Node> map;
    public MyLogger() {
        head = new Node("", -1);
        tail = new Node("", -1);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
    }
    public void started(String id, long time) {
        Node curr = new Node(id, time);
        map.put(id, curr);
        add(curr);
    }

    public void finished(String id, long time) {
        Node curr = map.get(id);
        if(curr != null && curr.end == -1) {
            curr.end = time;
            map.remove(id);
        }
    }
    public void print() {
        Node curr = head.next;
        while(curr != tail) {
            if(curr.end != -1) {
                System.out.println(curr.id + "start at" + curr.start +  "end at" + curr.end);
            }
            curr = curr.next;
        }
    }
    private void add(Node curr) {
        if(curr == null) return ;
        curr.next = tail;
        curr.prev = tail.prev;
        tail.prev.next = curr;
        tail.prev = curr;
    }
}


