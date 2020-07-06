package LeetcodeAlgorithm.N401_500.N460LFU缓存;

import java.util.HashMap;
import java.util.Map;

class LFUCache {
    Map<Integer, Node> cache;
    Map<Integer, DoublelyLinkedList> freqMap;
    int size;
    int capacity;
    int min;

    public LFUCache(int capacity) {
        cache = new HashMap<>(capacity);
        freqMap = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) return -1;
        freqInc(node);
        return node.val;
    }

    private void freqInc(Node node) {
        //从原freq里面移除，并更新min
        int freq = node.freq;
        DoublelyLinkedList linkedList = freqMap.get(freq);
        linkedList.removeNode(node);
        if (freq == min && linkedList.head.next == linkedList.tail) {
            min = freq + 1;
        }

        //加入fre对应的链表
        node.freq++;
        linkedList = freqMap.get(freq + 1);
        if (linkedList == null){
            linkedList = new DoublelyLinkedList();
            freqMap.put(freq + 1, linkedList);
        }
        linkedList.addNode(node);
    }

    public void put(int key, int value) {
        if (capacity == 0) return;
        Node node = cache.get(key);
        if (node != null) {
            node.val = value;
            freqInc(node);
        } else {
            if (size == capacity) {
                DoublelyLinkedList minFreqLinkedList = freqMap.get(min);
                cache.remove(minFreqLinkedList.tail.pre.key);
                minFreqLinkedList.removeNode(minFreqLinkedList.tail.pre);
                size--;
            }
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            DoublelyLinkedList linkedList = freqMap.get(1);
            if (linkedList == null) {
                linkedList = new DoublelyLinkedList();
                freqMap.put(1, linkedList);
            }
            linkedList.addNode(newNode);
            size++;
            min = 1;
        }
    }

    class Node {
        int key, val;
        int freq = 1;
        Node pre, next;

        public Node() {}
        public Node(int key, int value) {
            this.key = key;
            this.val = value;
        }
    }

    class DoublelyLinkedList {
        Node head;
        Node tail;
        public DoublelyLinkedList (){
            head = new Node();
            tail = new Node();
            head.next= tail;
            tail.pre = head;
        }

        public void removeNode(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        public void addNode(Node node) {
            node.next = head.next;
            head.next.pre = node;
            head.next = node;
            node.pre = head;
        }

    }

}

