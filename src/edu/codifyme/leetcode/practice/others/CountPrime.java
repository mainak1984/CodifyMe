package edu.codifyme.leetcode.practice.others;

/**
 * 204. Count Primes
 * EASY:https://leetcode.com/problems/count-primes/
 *
 * Count the number of prime numbers less than a non-negative number, n.
 *
 * Example:
 *
 * Input: 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 */

public class CountPrime {
    public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int count = 0;

        for (int i = 2; i * i < n; i++) {
            if ( notPrime[i] == false ) {
                // count += 1;

                for ( int j = 2; j * i < n; j++ ) {
                    notPrime[j * i] = true;
                }
            }
        }

        for (int k = 2; k < n; k++) {
            if (!notPrime[k]) {
                count++;
            }
        }

        return count;
    }
}
