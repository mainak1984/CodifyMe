package edu.codifyme.leetcode.practice.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 967. Numbers With Same Consecutive Differences
 * MEDIUM: https://leetcode.com/problems/numbers-with-same-consecutive-differences/
 *
 * Return all non-negative integers of length N such that the absolute difference between every two consecutive digits
 * is K.
 * Note that every number in the answer must not have leading zeros except for the number 0 itself. For example, 01 has
 * one leading zero and is invalid, but 0 is valid.
 * You may return the answer in any order.
 *
 * Example 1:
 * Input: N = 3, K = 7
 * Output: [181,292,707,818,929]
 * Explanation: Note that 070 is not a valid number, because it has leading zeroes.
 *
 * Example 2:
 * Input: N = 2, K = 1
 * Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 *
 * Note:
 * 1 <= N <= 9
 * 0 <= K <= 9
 *
 * Approach:
 * DFS or BFS by adding K to last digit and checking boundary
 */
public class NumbersWithSameConsecutiveDifferences {
    public int[] numsSameConsecDiff(int N, int K) {
        List<Integer> list = new ArrayList<>();

        int i = (N == 1)? 0 : 1;
        for (; i < 10; i++) {
            findNums(N, K, i, 1, list);
        }

        int[] result = new int[list.size()];
        for (int j = 0; j < list.size(); j++) {
            result[j] = list.get(j);
        }

        return result;
    }

    void findNums(int N, int K, int number, int index, List<Integer> result) {

        if (index == N) {
            result.add(number);
            return;
        }

        int lastDigit = number % 10;

        if (lastDigit - K >= 0) {
            findNums(N, K, (10 * number) + (lastDigit - K), index+1, result);
        }

        if (K != 0 && lastDigit + K < 10) {
            findNums(N, K, (10 * number) + (lastDigit + K), index+1, result);
        }
    }
}
