import java.util.HashMap;
import java.util.Map;

class LRUCache {

    private final int capacity;
    private final Map<Integer, Node> map = new HashMap<>();
    private Node head = null;
    private Node tail = null;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public static void main(String[] args) {
        LRUCache obj = new LRUCache(2);
        //[[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
        obj.get(2);
        obj.put(2, 6);
        System.out.println(obj.get(1));
        obj.put(1,5);
        obj.put(1,2);
        System.out.println(obj.get(1));
        System.out.println(obj.get(2));
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            // update recency
            Node n = map.get(key);
            removeNode(n);
            addNodeToHead(n);

            return n.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.size() == capacity) {
            Node toRemove = map.getOrDefault(key, tail);

            // remove LRU from map
            map.remove(toRemove.key);

            // update recency
            removeNode(toRemove);
        }
        Node n = new Node(key, value);
        map.put(key, n);
        addNodeToHead(n);
    }

    class Node {

        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private void removeNode(Node n) {
        if (n.prev != null) {
            n.prev.next = n.next;
        }
        if (n.next != null) {
            n.next.prev = n.prev;
        }
        if (n == head) {
            head = n.next;
        }
        if (n == tail) {
            tail = n.prev;
        }
    }

    private void addNodeToHead(Node n) {
        if (head != null) {
            head.prev = n;
        }
        n.next = head;
        head = n;
        head.prev = null;
        if (tail == null) {
            tail = head;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such: LRUCache obj = new LRUCache(capacity); int param_1 =
 * obj.get(key); obj.put(key,value);
 */

