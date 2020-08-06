package edu.codifyme.leetcode.interview.google.ztopfifty;

import java.util.Map;
import java.util.TreeMap;

/**
 * MEDIUM:
 * https://leetcode.com/problems/hand-of-straights/
 *
 * Alice has a hand of cards, given as an array of integers.
 * Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.
 * Return true if and only if she can.
 *
 * Example 1:
 * Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
 * Output: true
 * Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].
 * Example 2:
 *
 * Input: hand = [1,2,3,4,5], W = 4
 * Output: false
 * Explanation: Alice's hand can't be rearranged into groups of 4.
 *
 *
 * Constraints:
 * 1 <= hand.length <= 10000
 * 0 <= hand[i] <= 10^9
 * 1 <= W <= hand.length
 * Note: This question is the same as 1296: https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
 */
public class HandOfStraights {
    public boolean isNStraightHand(int[] hand, int W) {
        Map<Integer, Integer> freqMap = new TreeMap<>();

        // Put all the elements in a sorted frequency map
        for (int element: hand) {
            freqMap.put(element, freqMap.getOrDefault(element, 0)+1);
        }

        // iterate the map and try to assign in each element of group of W
        // reduce frequency when taken
        while (freqMap.size() != 0) {

            if (freqMap.size() < W) {
                return false;
            }

            int startingKey = freqMap.keySet().iterator().next();

            for (int loop = 0; loop < W; loop++) {
                if ( freqMap.getOrDefault(startingKey, 0) > 0) {
                    freqMap.put(startingKey, freqMap.getOrDefault(startingKey, 0)-1);

                    if (freqMap.get(startingKey) == 0) {
                        freqMap.remove(startingKey);
                    }
                } else {
                    return false;
                }
                startingKey++;
            }
        }

        return true;
    }
}
