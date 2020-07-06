package LeetcodeAlgorithm.N401_500.N460LFU缓存;

import java.util.HashMap;
import java.util.Map;

class LFUCacheRedo{
    Map<Integer, Node> cacheMap;
    Map<Integer, LinkedFreqList> freqMap;
    int capacity, minFreq, size = 0;

    public LFUCacheRedo(int capacity) {
        freqMap = new HashMap<>();
        this.capacity = capacity;
        cacheMap = new HashMap<>(capacity);
    }

    public int get(int key) {
        if (!cacheMap.containsKey(key)) return -1;

        Node node = cacheMap.get(key);
        helper(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (cacheMap.containsKey(key)) { //在里面
            Node node = cacheMap.get(key);  //更新值
            node.value = value;
            helper(node);
        } else {    //不在里面
            if (size == capacity) {     //容量满了----删除freq最小的---,cache值也删除
                LinkedFreqList linkedFreqList = freqMap.get(minFreq);

                cacheMap.remove(linkedFreqList.tail.pre.key);   //必须先在cache中remove
                linkedFreqList.deleteNode(linkedFreqList.tail.pre); //然后在链表中删除
                //先在链表中删除会导致找不到对应Node
                size--;
            }
            //容量未满
            Node newNode = new Node(key, value);
            LinkedFreqList linkedFreqList = freqMap.get(1);
            if (linkedFreqList == null) {
                linkedFreqList = new LinkedFreqList();
                freqMap.put(1, linkedFreqList);
            }
            linkedFreqList.addNode(newNode);
            cacheMap.put(key, newNode);
            size++;
            minFreq = 1;
        }
    }

    private void helper(Node node) { //put 和 get 都会进行更新一个操作，从一个freqlist删除更新到另外一个freqlist中
        int freq = node.freq;
        LinkedFreqList linkedFreqList = freqMap.get(freq);
        linkedFreqList.deleteNode(node);
        if (freq == minFreq && linkedFreqList.head.next == linkedFreqList.tail) {
            minFreq++;
        }
        node.freq++;
        LinkedFreqList list = freqMap.get(freq + 1);
        if (list == null) {
            list = new LinkedFreqList();
            freqMap.put(freq + 1, list);
        }
        list.addNode(node);
    }

    class Node {
        Node pre, next;
        int key, value, freq = 1;

        public Node() {
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    class LinkedFreqList {
        Node head = new Node(), tail = new Node();

        public LinkedFreqList() {
            head.next = tail;
            tail.pre = head;
        }

        public void deleteNode(Node n) {
            n.next.pre = n.pre;
            n.pre.next = n.next;
        }

        public void addNode(Node n) {
            n.next = head.next;
            head.next.pre = n;
            n.pre = head;
            head.next = n;
        }

    }

}
