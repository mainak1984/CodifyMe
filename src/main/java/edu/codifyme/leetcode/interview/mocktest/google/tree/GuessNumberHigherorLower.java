package edu.codifyme.leetcode.interview.mocktest.google.tree;

import java.util.Random;

/**
 * EASY: Guess Number Higher or Lower
 * https://leetcode.com/problems/guess-number-higher-or-lower/
 *
 * We are playing the Guess Game. The game is as follows:
 * I pick a number from 1 to n. You have to guess which number I picked.
 * Every time you guess wrong, I'll tell you whether the number is higher or lower.
 * You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
 *
 * -1 : My number is lower
 *  1 : My number is higher
 *  0 : Congrats! You got it!
 * Example :
 *
 * Input: n = 10, pick = 6
 * Output: 6
 */
public class GuessNumberHigherorLower {
    int guess(int num) {
        return new Random().nextInt();
    }

    public int guessNumber(int n) {
        int low = 0;
        int high = n;

        while (low <= high) {
            int mid = low + (high - low)/2;

            int guess = guess(mid);

            if (guess == 0) {
                return mid;
            } else if (guess < 0) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }

        return -1;
    }
}
