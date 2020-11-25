package edu.codifyme.leetcode.practice.others;

import java.util.HashSet;
import java.util.Set;

/**
 * 1015. Smallest Integer Divisible by K
 * MEDIUM: https://leetcode.com/problems/smallest-integer-divisible-by-k/
 *
 * Given a positive integer K, you need to find the length of the smallest positive integer N such that N is divisible
 * by K, and N only contains the digit 1.
 *
 * Return the length of N. If there is no such N, return -1.
 * Note: N may not fit in a 64-bit signed integer.
 *
 * Example 1:
 * Input: K = 1
 * Output: 1
 * Explanation: The smallest answer is N = 1, which has length 1.
 *
 * Example 2:
 * Input: K = 2
 * Output: -1
 * Explanation: There is no such positive integer N divisible by 2.
 *
 * Example 3:
 * Input: K = 3
 * Output: 3
 * Explanation: The smallest answer is N = 111, which has length 3.
 *
 * Constraints:
 * 1 <= K <= 105
 *
 * Approach 1: Checking Loop
 * We need to do two things:
 * - check if the required number N exists.
 * - find out length(N).
 * The second one is easy: we only need to keep multiplying N by 10 and adding 1 until N%K==0. Since the remainder and N
 * have the same remainder of K, it OK to use remainder instead of N.
 *
 * Notice that if N does not exist, this while-loop will continue endlessly. However, the possible values of remainder
 * are limited -- ranging from 0 to K-1. Therefore, if the while-loop continues forever, the remainder repeats. Also, if
 * remainder repeats, then it gets into a loop. Hence, the while-loop is endless if and only if the remainder repeats.
 *
 * In this case, we can check if the remainder repeats to check if the while-loop is endless:
 *
 * Approach 2: Using pigeonhole principle
 * Recall that the number of possible values of remainder (ranging from 0 to K-1) is limited, and in fact, the number is
 * K. As a result, if the while-loop continues more than K times, and haven't stopped, then we can conclude that
 * remainder repeats -- you can not have more than K different remainder.
 * Hence, if N exists, the while-loop must return length_N in the first K loops. Otherwise, it goes into an infinite loop.
 * Therefore, we can just run the while-loop K times, and return -1 if not stopped.
 */
public class SmallestIntegerDivisiblebyK {
    //Approach 1:
    public int smallestRepunitDivByK(int K) {
        int num = 1;
        int size = 1;

        Set<Integer> rem = new HashSet<>();
        int mod = num % K;

        while (mod != 0) {
            if (rem.contains(mod)) {
                return -1;
            } else {
                rem.add(mod);
            }

            num = mod * 10 + 1;
            mod = num % K;
            size++;
        }

        return size;
    }

    // Approach 2:
    public int smallestRepunitDivByK2(int K) {
        int remainder = 0;
        for (int length_N = 1; length_N <= K; length_N++) {
            remainder = (remainder * 10 + 1) % K;
            if (remainder == 0) {
                return length_N;
            }
        }
        return -1;
    }
}
