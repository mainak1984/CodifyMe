package edu.codifyme.leetcode.interview.googledeck.design;

import java.util.*;

/**
 * MEDIUM:
 * https://leetcode.com/explore/interview/card/google/65/design-4/3094/
 *
 * Design a data structure that supports all following operations in average O(1) time.
 *
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements. Each element must have the same probability of
 * being returned.
 * Example:
 *
 * // Init an empty set.
 * RandomizedSet randomSet = new RandomizedSet();
 *
 * // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomSet.insert(1);
 *
 * // Returns false as 2 does not exist in the set.
 * randomSet.remove(2);
 *
 * // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomSet.insert(2);
 *
 * // getRandom should return either 1 or 2 randomly.
 * randomSet.getRandom();
 *
 * // Removes 1 from the set, returns true. Set now contains [2].
 * randomSet.remove(1);
 *
 * // 2 was already in the set, so return false.
 * randomSet.insert(2);
 *
 * // Since 2 is the only number in the set, getRandom always return 2.
 * randomSet.getRandom();
 *
 * Approach:
 * Hashmap provides Insert and Delete in average constant time, although has problems with GetRandom.
 * The idea of GetRandom is to choose a random index and then to retrieve an element with that index. There is no
 * indexes in hashmap, and hence to get true random value, one has first to convert hashmap keys in a list, that would
 * take linear time. The solution here is to build a list of keys aside and to use this list to compute GetRandom in
 * constant time.
 * Array List has indexes and could provide Insert and GetRandom in average constant time, though has problems with Delete.
 * To delete a value at arbitrary index takes linear time. The solution here is to always delete the last value:
 *      Swap the element to delete with the last one.
 *      Pop the last element out.
 * For that, one has to compute an index of each element in constant time, and hence needs a hashmap which stores
 * element -> its index dictionary.
 * Both ways converge into the same combination of data structures:
 *      * Hashmap element -> its index.
 *      * Array List of elements.
 */
public class InsertDeleteGetRandomInConstantTIme {
    Map<Integer, Integer> dict;
    List<Integer> list;
    Random rand = new Random();

    /** Initialize your data structure here. */
    public InsertDeleteGetRandomInConstantTIme() {
        dict = new HashMap();
        list = new ArrayList();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (dict.containsKey(val)) return false;

        dict.put(val, list.size());
        list.add(list.size(), val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (! dict.containsKey(val)) return false;

        // move the last element to the place idx of the element to delete
        int lastElement = list.get(list.size() - 1);
        int idx = dict.get(val);
        list.set(idx, lastElement);
        dict.put(lastElement, idx);
        // delete the last element
        list.remove(list.size() - 1);
        dict.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}
