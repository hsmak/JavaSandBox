package org.hsmak.letit;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache {

    LinkedList<Integer> LRUOrder;
    Map<Integer, Integer> LRUCache;

    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        LRUOrder = new LinkedList<>();
        LRUCache = new HashMap<>(capacity);

    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2, 6);
        lruCache.put(1, 5);
        lruCache.put(1, 2);
        System.out.println(lruCache.get(2));
    }

    public int get(int key) {
        if (LRUCache.containsKey(key)) {
            LRUOrder.remove((Integer) key);
            LRUOrder.addFirst(key);
            return LRUCache.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if (LRUCache.containsKey(key)) {
            LRUOrder.remove((Integer) key);
            LRUOrder.addFirst(key);
            LRUCache.put(key, value);
            return;
        }
        if (capacity == LRUOrder.size()) {
            Integer removedKey = LRUOrder.removeLast();
            LRUCache.remove(removedKey);
        }

        LRUOrder.addFirst(key);
        LRUCache.put(key, value);
    }
}
