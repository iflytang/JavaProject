package src.interview;

import java.util.HashMap;

public class LRUCache { // 使用HashMap和双向链表

    int capacity;  // 配置大小
    int size;  // 实际大小

    DeLinkedList head;
    DeLinkedList tail;

    HashMap<Integer, DeLinkedList> cache = new HashMap<>();  // <key, {key, val}>

    public LRUCache (int capacity) {
        this.capacity = capacity;
        this.size = 0;

        head = new DeLinkedList();
        tail = new DeLinkedList();

        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        DeLinkedList node = cache.get(key);

        if (node == null) {
            return -1;
        }

        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);

        return node.val;
    }

    public void put(int key, int value) {
        DeLinkedList node = cache.get(key);

        if (node == null) { // 节点不存在，需要新建并判断size
            DeLinkedList newNode = new DeLinkedList(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
            size++;

            if (size > capacity) { // 判断size
                int delKey = removeTail().key;
                cache.remove(delKey);
                size--;
            }

        } else {
            node.val = value;
            moveToHead(node);
        }
    }

    public void addToHead(DeLinkedList node) {
        head.next.pre = node;
        node.next = head.next;

        head.next = node;
        node.pre = head;
    }

    public void removeNode(DeLinkedList node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public void moveToHead(DeLinkedList node) {
        removeNode(node);
        addToHead(node);
    }

    public DeLinkedList removeTail() {
        DeLinkedList ret = tail.pre;
        removeNode(ret);

        return ret;
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

class DeLinkedList {
    int key;
    int val;

    DeLinkedList pre;
    DeLinkedList next;

    DeLinkedList() {

    }

    DeLinkedList(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
