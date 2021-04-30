package edu.codifyme.leetcode.practice.others;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 970. Powerful Integers
 * MEDIUM: https://leetcode.com/problems/powerful-integers/
 *
 * Given three integers x, y, and bound, return a list of all the powerful integers that have a value less than or equal
 * to bound.
 * An integer is powerful if it can be represented as xi + yj for some integers i >= 0 and j >= 0.
 * You may return the answer in any order. In your answer, each value should occur at most once.
 *
 * Example 1:
 * Input: x = 2, y = 3, bound = 10
 * Output: [2,3,4,5,7,9,10]
 * Explanation:
 * 2 = 20 + 30
 * 3 = 21 + 30
 * 4 = 20 + 31
 * 5 = 21 + 31
 * 7 = 22 + 31
 * 9 = 23 + 30
 * 10 = 20 + 32
 *
 * Example 2:
 * Input: x = 3, y = 5, bound = 15
 * Output: [2,4,6,8,10,14]
 *
 * Constraints:
 * 1 <= x, y <= 100
 * 0 <= bound <= 106
 *
 * Approach:
 * There is a way to find a much smaller bound for the powers.
 * m^n <= bound
 * This formula implies that
 * n<=log m (bound)
 * We can use the log function to determine the bounds for the powers of "x" and "y".
 *
 * Run a loop for mxn to find all combination and save them in HashSet to get only unique ones as a result.
 *
 * Complexity:
 * Time Complexity: Let N be log_x (bound) and M be log_y (bound). Then the overall time complexity is O(N×M)
 * Space Complexity: O(N×M) because we use a set to omit duplicates
 */
public class PowerfulIntegers {
    public List<Integer> powerfulIntegers(int x, int y, int bound) {

        int a = x == 1 ? bound : (int) (Math.log(bound) / Math.log(x));
        int b = y == 1 ? bound : (int) (Math.log(bound) / Math.log(y));

        HashSet<Integer> powerfulIntegers = new HashSet<Integer>();

        for (int i = 0; i <= a; i++) {
            for (int j = 0; j <= b; j++) {

                int value = (int) Math.pow(x, i) + (int) Math.pow(y, j);

                if (value <= bound) {
                    powerfulIntegers.add(value);
                }

                // No point in considering other powers of "1".
                if (y == 1) {
                    break;
                }
            }

            if (x == 1) {
                break;
            }
        }

        return new ArrayList<Integer>(powerfulIntegers);
    }
}
