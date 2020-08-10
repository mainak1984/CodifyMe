package edu.codifyme.leetcode.interview.google.ztopfifty;

import java.util.HashMap;
import java.util.Map;

/**
 * 1088. Confusing Number II
 * HARD: https://leetcode.com/problems/confusing-number-ii/
 *
 * We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become
 * 0, 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid.
 * A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.
 * (Note that the rotated number can be greater than the original number.)
 *
 * Given a positive integer N, return the number of confusing numbers between 1 and N inclusive.
 *
 * Example 1:
 * Input: 20
 * Output: 6
 * Explanation:
 * The confusing numbers are [6,9,10,16,18,19].
 * 6 converts to 9.
 * 9 converts to 6.
 * 10 converts to 01 which is just 1.
 * 16 converts to 91.
 * 18 converts to 81.
 * 19 converts to 61.
 *
 * Example 2:
 * Input: 100
 * Output: 19
 * Explanation:
 * The confusing numbers are [6,9,10,16,18,19,60,61,66,68,80,81,86,89,90,91,98,99,100].
 *
 * Approach: DFS limit check
 */
public class ConfusingNumberII {
    Map<Integer, Integer> map = new HashMap<Integer, Integer>() {{
        put(0, 0);
        put(1, 1);
        put(6, 9);
        put(8, 8);
        put(9, 6);
    }};
    int count = 0;

    public int confusingNumberII(int N) {
        dfs(N, 0L);

        return count;
    }

    void dfs(int N, long startingNum) {
        if ( isValid(startingNum, N) ) {
            count++;
        }

        for (int num: map.keySet()) {
            long candidateNum = startingNum * 10 + num;
            if ( candidateNum <= N && candidateNum != 0) {
                dfs(N, candidateNum);
            }
        }
    }

    boolean isValid(long src, long N) {
        long rotated = 0;
        long source = src;

        while( source > 0 ) {
            int lsb = (int)source % 10;
            rotated = rotated * 10 + map.get(lsb);
            source /= 10;
        }

        return src != rotated; //&& src > 0 && src <= N;
    }
}
