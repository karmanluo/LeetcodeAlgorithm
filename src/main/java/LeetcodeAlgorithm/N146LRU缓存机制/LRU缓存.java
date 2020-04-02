package LeetcodeAlgorithm.N146LRU缓存机制;

import java.util.HashMap;
import java.util.Map;

public class LRU缓存 {

    //head 与 tail 都不能存值，方便检索，新来的值放在头，老的自然就在尾巴侧
    final Node head = new Node(0, 0);
    final Node tail = new Node(0, 0);
    final Map<Integer, Node> map; //map存的是键值与节点信息
    final int capacity;

    public LRU缓存(int capacity) {
        this.capacity = capacity;
        map = new HashMap(capacity);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        int res = -1;
        if (map.containsKey(key)) {
            Node n = map.get(key);
            remove(n);
            insertToHead(n);
            res = n.value;
        }
        return res;
    }

    //之前出现过，插入到链表头
    //之前未出现过，但是容量已满，map删尾巴前面一个对应的最久未使用的节点
    //将节点放入双向链表，并且放进hashmap
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            remove(n);
            n.value = value;
            insertToHead(n);
        } else {

            if (map.size() == capacity) {
                map.remove(tail.prev.key);
                remove(tail.prev);
            }

            Node n = new Node(key, value);
            insertToHead(n);
            map.put(key, n);
        }
    }

    //将双向链表中当前节点删除
    private void remove(Node n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    //将n节点插在头结点的后面
    private void insertToHead(Node n) {
        Node headNext = head.next;
        head.next = n;
        headNext.prev = n;
        n.prev = head;
        n.next = headNext;
    }

    //子类定义出Node的格式
    class Node {
        Node prev, next;
        int key, value;

        Node(int k, int v) {
            key = k;
            value = v;
        }
    }
}