package edu.codifyme.leetcode.interview.google.recursion;

import java.util.HashSet;
import java.util.Set;

/**
 * MEDIUM:
 * https://leetcode.com/problems/android-unlock-patterns
 *
 * Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock
 * patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.
 * Rules for a valid pattern:
 * Each pattern must connect at least m keys and at most n keys.
 * All the keys must be distinct.
 * If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have
 * previously selected in the pattern. No jumps through non selected key is allowed.
 * The order of keys used matters.

 * Explanation:
 * | 1 | 2 | 3 |
 * | 4 | 5 | 6 |
 * | 7 | 8 | 9 |
 * Invalid move: 4 - 1 - 3 - 6
 * Line 1 - 3 passes through key 2 which had not been selected in the pattern.
 *
 * Invalid move: 4 - 1 - 9 - 2
 * Line 1 - 9 passes through key 5 which had not been selected in the pattern.
 *
 * Valid move: 2 - 4 - 1 - 3 - 6
 * Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern
 *
 * Valid move: 6 - 5 - 4 - 1 - 9 - 2
 * Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.
 *
 * Example:
 * Input: m = 1, n = 1
 * Output: 9
 *
 * Approach:
 * Backtracking
 * Use pruning where count for 1,3,7,9 are same and 2,4,6,8 are same, so, use one loop for each of them
 */
public class AndroidUnlockPatterns {
    public int numberOfPatterns(int m, int n) {
        int[] numbers = new int[]{1, 2, 5};
        Set<Integer> visited = new HashSet<>();
        int total = 0;

        for (int i = 0; i < 3; i++) {
            visited.add(numbers[i]);
            sum = 0;
            getCombinations(numbers[i], visited, 1, m, n);
            total += sum;
            visited.remove(numbers[i]);

            if ( i == 1 ){
                total = total * 4;
            }
        }

        return total;
    }

    int sum = 0;

    void getCombinations(int num, Set<Integer> visited, int depth, int m, int n) {
        if (depth > n) {
            return;
        } else if ( depth == n ) {
            sum += 1;
            return;
        }
        for (int i = 1; i <= 9; i++) {
            if (i == num) {
                if (depth >= m) {
                    sum += 1;
                }
                continue;
            }
            if ( visited.contains(i) ) {
                continue;
            }

            if (isReachable(num, i, visited)) {
                visited.add(i);
                getCombinations(i, visited, depth+1, m, n);
                visited.remove(i);
            }
        }

        return;
    }

    boolean isReachable(int num, int target, Set<Integer> visited) {
        int si = ((num - 1) / 3 + 1);
        int sj = ((num - 1) % 3 + 1);
        int ti = ((target - 1) / 3 + 1);
        int tj = ((target - 1) % 3 + 1);

        if (si == ti && Math.abs(sj-tj) == 2) {
            int n = (si-1)*3 + 2;
            if(!visited.contains(n)) {
                return false;
            }
        } else if (sj == tj && Math.abs(si-ti) == 2) {
            int n = 3 + sj;
            if(!visited.contains(n)) {
                return false;
            }
        } else if (Math.abs(si-ti) == 2 && Math.abs(sj-tj) == 2) {
            if(!visited.contains(5)) {
                return false;
            }
        }

        return true;
    }
}
