package edu.codifyme.leetcode.practice.others;

import java.util.Arrays;

/**
 * 949. Largest Time for Given Digits
 * EASY: https://leetcode.com/problems/largest-time-for-given-digits/
 *
 * Given an array of 4 digits, return the largest 24 hour time that can be made.
 *
 * The smallest 24 hour time is 00:00, and the largest is 23:59.  Starting from 00:00, a time is larger if more time has
 * elapsed since midnight.
 *
 * Return the answer as a string of length 5.  If no valid time can be made, return an empty string.
 *
 * Example 1:
 * Input: [1,2,3,4]
 * Output: "23:41"
 *
 * Example 2:
 * Input: [5,5,5,5]
 * Output: ""
 *
 * Note:
 * A.length == 4
 * 0 <= A[i] <= 9
 *
 * Approach:
 * Permute and check limit, find the max.
 *
 * Alternate approach: sort the array and try permuting from max allowed for each position. Break when found the
 * first valid combination
 */
public class LargestTimeForGivenDigits {
    public String largestTimeFromDigits(int[] A) {
        int[] limit = new int[]{2,9,5,9};
        boolean[] taken = new boolean[A.length];
        StringBuilder sb = new StringBuilder();

        Arrays.sort(A);

        if ( getTime(A, taken, 0, limit, sb)) {
            sb.insert(2, ":");
            return sb.toString();
        } else {
            return "";
        }
    }

    boolean getTime(int[] available, boolean[] taken, int index, int[] limit, StringBuilder sb) {
        if (index == available.length) {
            return true;
        }

        int i = available.length - 1;
        for (; i >= 0 ; i--) {
            int lim = limit[index];
            if (index == 1 && sb.charAt(0) == '2') {
                lim = 3;
            }
            if (!taken[i] && available[i] <= lim) {
                sb.append(available[i]);
                taken[i] = true;

                if (getTime(available, taken, index+1, limit, sb)) {
                    return true;
                }

                sb.setLength(sb.length() - 1);
                taken[i] = false;
            }
        }

        return false;
    }
}
