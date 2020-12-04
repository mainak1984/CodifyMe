package edu.codifyme.leetcode.practice.others;

import java.util.ArrayList;
import java.util.List;

/**
 * 1492. The kth Factor of n
 * MEDIUM: https://leetcode.com/problems/the-kth-factor-of-n/
 *
 * Given two positive integers n and k.
 *
 * A factor of an integer n is defined as an integer i where n % i == 0.
 *
 * Consider a list of all factors of n sorted in ascending order, return the kth factor in this list or return -1 if n
 * has less than k factors.
 *
 * Example 1:
 * Input: n = 12, k = 3
 * Output: 3
 * Explanation: Factors list is [1, 2, 3, 4, 6, 12], the 3rd factor is 3.
 *
 * Example 2:
 * Input: n = 7, k = 2
 * Output: 7
 * Explanation: Factors list is [1, 7], the 2nd factor is 7.
 *
 * Example 3:
 * Input: n = 4, k = 4
 * Output: -1
 * Explanation: Factors list is [1, 2, 4], there is only 3 factors. We should return -1.
 *
 * Example 4:
 * Input: n = 1, k = 1
 * Output: 1
 * Explanation: Factors list is [1], the 1st factor is 1.
 *
 * Example 5:
 * Input: n = 1000, k = 3
 * Output: 4
 * Explanation: Factors list is [1, 2, 4, 5, 8, 10, 20, 25, 40, 50, 100, 125, 200, 250, 500, 1000].
 *
 * Constraints:
 * 1 <= k <= n <= 1000
 *
 * Approach 1: Brute force
 * Algorithm:
 * - Iterate by x from 11 to N/2:
 *      - If x is a divisor of N, decrease k by one. Return x if k == 0.
 * -  Return N if k == 1 and -1 otherwise.
 *
 * Approach 2: Math, √O(N) time
 * Algorithm:
 * - Initialize a list divisors to store the divisors.
 * - Iterate by x from 11 to √N:
 *      - If x is a divisor of N, decrease k by one. Return xx if k == 0.
 *  - We're here because the kth divisor is not yet found. Although divisors already contains all "independent"
 *    divisors. All other divisors are "paired" ones, i.e, the kth divisor could be computed as
 *    N / divisors[len(divisors) - k].
 *    But before that, we need a small correction for the case when NN is a perfect square. In that case, the divisor
 *    list contains a duplicate because √N appears two times. To skip it, we have to increase k by one.
 *  - Return N / divisors[len(divisors) - k] if k <= len(divisors) and -1 otherwise.
 */
public class ThekthFactorofn {
    // Efficient: √O(N) time
    public int kthFactor(int n, int k) {
        List<Integer> divisors = new ArrayList();
        int sqrtN = (int) Math.sqrt(n);
        for (int x = 1; x < sqrtN + 1; ++x) {
            if (n % x == 0) {
                --k;
                divisors.add(x);
                if (k == 0) {
                    return x;
                }
            }
        }

        // If n is a perfect square
        // we have to skip the duplicate
        // in the divisor list
        if (sqrtN * sqrtN == n) {
            ++k;
        }

        int nDiv = divisors.size();
        return (k <= nDiv) ? n / divisors.get(nDiv - k) : -1;
    }

    // Inefficient: O(N) time
    public int kthFactor1(int n, int k) {
        for (int x = 1; x < n / 2 + 1; ++x) {
            if (n % x == 0) {
                --k;
                if (k == 0) {
                    return x;
                }
            }
        }

        return k == 1 ? n : -1;
    }
}
