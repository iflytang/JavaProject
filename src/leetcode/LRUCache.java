package src.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    /** define double ended linked list.
    * */
    class DeLinkedList {
        int key;
        int val;

        DeLinkedList pre;
        DeLinkedList next;

        public DeLinkedList() {
            // nothing
        }

        public DeLinkedList(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    /**
     * parameters
     */
    int size;       // realtime size
    int capacity;   // setting size

    DeLinkedList head;
    DeLinkedList tail;

    Map<Integer, DeLinkedList> cache = new HashMap<>();

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;

        // dummy nodes
        this.head = new DeLinkedList();
        this.tail = new DeLinkedList();

        this.head.next = tail;
        this.tail.pre = head;

    }

    public int get(int key) {
        DeLinkedList node = cache.getOrDefault(key, null);

        if (node == null) {  // not found
            return -1;
        }

        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);

        return node.val;
    }

    public void put(int key, int value) {
        DeLinkedList node = cache.getOrDefault(key, null);

        if (node == null) {
            // 如果 key 不存在，创建一个新的节点
            DeLinkedList newNode = new DeLinkedList(key, value);

            // 添加进哈希表
            cache.put(key, newNode);

            // 添加至双向链表的头部
            addToHead(newNode);
            size++;

            if (size > capacity) {
                // 如果超出容量，删除双向链表的尾部节点
                DeLinkedList removedNode = removeTail();

                // 删除哈希表中对应的项
                cache.remove(removedNode.key);
                size--;
            }

        } else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.val = value;
            moveToHead(node);
        }
    }

    public void addToHead(DeLinkedList node) {
        // latter link
        head.next.pre = node;
        node.next = head.next;

        // font link
        head.next = node;
        node.pre = head;
    }

    public void removeNode(DeLinkedList node) {
        node.next.pre = node.pre;
        node.pre.next = node.next;
    }

    public void moveToHead(DeLinkedList node) {
        removeNode(node);
        addToHead(node);
    }

    public DeLinkedList removeTail() {
        DeLinkedList res = tail.pre;
        removeNode(res);

        return res;
    }

    public static void main(String [] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        lRUCache.get(1);    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.get(2);    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lRUCache.get(1);    // 返回 -1 (未找到)
        lRUCache.get(3);    // 返回 3
        lRUCache.get(4);    // 返回 4
    }
}
