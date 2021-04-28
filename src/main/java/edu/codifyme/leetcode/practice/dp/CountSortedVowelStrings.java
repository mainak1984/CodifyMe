package edu.codifyme.leetcode.practice.dp;

import java.util.Arrays;

/**
 * 1641. Count Sorted Vowel Strings
 * MEDIUM: https://leetcode.com/problems/count-sorted-vowel-strings/
 *
 * Given an integer n, return the number of strings of length n that consist only of vowels (a, e, i, o, u) and are
 * lexicographically sorted.
 *
 * A string s is lexicographically sorted if for all valid i, s[i] is the same as or comes before s[i+1] in the alphabet.
 *
 * Example 1:
 * Input: n = 1
 * Output: 5
 * Explanation: The 5 sorted strings that consist of vowels only are ["a","e","i","o","u"].
 *
 * Example 2:
 * Input: n = 2
 * Output: 15
 * Explanation: The 15 sorted strings that consist of vowels only are
 * ["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"].
 * Note that "ea" is not a valid string since 'e' comes after 'a' in the alphabet.
 *
 * Example 3:
 * Input: n = 33
 * Output: 66045
 *
 * Constraints:
 * 1 <= n <= 50
 *
 * Approach:
 * We use the iterative approach and store the result of subproblems in bottom-up fashion also known as Tabulation. We
 * store the results of previous computations in tabular format and use those results for the next computations.
 *
 * Algorithm
 * We maintain a 2D array , dp of size n⋅5 where, dp[n][vowels] denotes the total number of combinations for given nn and
 * number of vowels. Using the recurrence relation established in Approach 2, we could iteratively calculate the value
 * for dp[n][vowels] as,
 * dp[n][vowels] = dp[n - 1][vowels] + dp[n][vowels - 1]
 * As this is the Bottom Up approach to solve the problem, we must initialize the table for the base cases. The base
 * cases are the same as in Approach 2.
 * If n=1, the number of combinations are always equal to number of vowels. Hence, we initialize all the values of
 * dp[1][vowels] with vowels.
 * If vowels=1, the number of combinations are always equal to 1. Hence, we initialize all the values of dp[n][1] with 1.
 */
public class CountSortedVowelStrings {
    public int countVowelStrings(int n) {
        int[] arr = new int[5];

        Arrays.fill(arr, 1);

        for (int i = 2; i <= n; i++) {
            for (int j = 3; j >= 0; j--) {
                arr[j] = arr[j] + arr[j+1];
            }
        }

        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum += arr[i];
        }

        return sum;
    }
}
