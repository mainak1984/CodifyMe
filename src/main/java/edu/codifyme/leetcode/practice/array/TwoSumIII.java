package edu.codifyme.leetcode.practice.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 170. Two Sum III
 * https://leetcode.com/problems/two-sum-iii-data-structure-design/
 *
 * Design a data structure that accepts a stream of integers and checks if it has a pair of integers that sum up to a
 * particular value.
 *
 * Implement the TwoSum class:
 *
 * TwoSum() Initializes the TwoSum object, with an empty array initially.
 * void add(int number) Adds number to the data structure.
 * boolean find(int value) Returns true if there exists any pair of numbers whose sum is equal to value, otherwise, it
 * returns false.
 *
 * Example 1:
 * Input
 * ["TwoSum", "add", "add", "add", "find", "find"]
 * [[], [1], [3], [5], [4], [7]]
 * Output
 * [null, null, null, null, true, false]
 *
 * Explanation
 * TwoSum twoSum = new TwoSum();
 * twoSum.add(1);   // [] --> [1]
 * twoSum.add(3);   // [1] --> [1,3]
 * twoSum.add(5);   // [1,3] --> [1,3,5]
 * twoSum.find(4);  // 1 + 3 = 4, return true
 * twoSum.find(7);  // No two integers sum up to 7, return false
 *
 * Constraints:
 * -105 <= number <= 105
 * -231 <= value <= 231 - 1
 * At most 5 * 104 calls will be made to add and find.
 *
 * Approach:
 * Build a HashMap and search for the pair number from map for finding a given sum
 */
public class TwoSumIII {
    Map<Integer, Integer> numbers;

    /** Initialize your data structure here. */
    public TwoSumIII() {
        numbers = new HashMap<>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        numbers.put(number, numbers.getOrDefault(number, 0)+1);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for (int number: numbers.keySet() ) {
            if (numbers.containsKey(value - number)) {
                if (number == (value - number) && numbers.get(number) < 2) {
                    continue;
                }
                return true;
            }
        }

        return false;
    }
}
