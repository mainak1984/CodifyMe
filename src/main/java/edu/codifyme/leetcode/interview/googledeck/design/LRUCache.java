package edu.codifyme.leetcode.interview.googledeck.design;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * MEDIUM:
 * https://leetcode.com/problems/lru-cache
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
 * it should invalidate the least recently used item before inserting a new item.
 * The cache is initialized with a positive capacity.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 * Example:
 * LRUCache cache = new LRUCache( 2 ); // capacity
 *
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // returns 1
 * cache.put(3, 3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4, 4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 *
 * Approach 2: Hashmap + DoubleLinkedList
 * This Java solution is an extended version of the the article published on the Discuss forum.
 * The problem can be solved with a hashmap that keeps track of the keys and its values in the double linked list.
 * That results in \mathcal{O}(1)O(1) time for put and get operations and allows to remove the first added node in
 * O(1) time as well.
 * One advantage of double linked list is that the node can remove itself without other reference. In addition, it takes
 * constant time to add and remove nodes from the head or tail.
 * One particularity about the double linked list implemented here is that there are pseudo head and pseudo tail to mark
 * the boundary, so that we don't need to check the null node during the update.
 */
public class LRUCache {
    LinkedHashMap<Integer, Integer> cache;
    int capacity = 0;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }
}
