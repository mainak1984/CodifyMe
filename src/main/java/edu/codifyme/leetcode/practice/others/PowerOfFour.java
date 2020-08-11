package edu.codifyme.leetcode.practice.others;

/**
 * 342. Power of Four
 * EASY: https://leetcode.com/problems/power-of-four/
 *
 * Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
 *
 * Example 1:
 * Input: 16
 * Output: true
 *
 * Example 2:
 * Input: 5
 * Output: false
 * Follow up: Could you solve it without loops/recursion?
 *
 * Approach 3: Bit Manipulation
 * Let's first check if num is a power of two: x > 0 and x & (x - 1) == 0.
 * Now the problem is to distinguish between even powers of two (when x is a power of four) and odd powers of two
 * (when x is not a power of four). In binary representation both cases are single 1-bit followed by zeros.
 *
 * What is the difference? In the first case (power of four), 1-bit is at even position: bit 0, bit 2, bit 4, etc.
 * In the second case, at odd position.
 *
 * Hence power of four would make a zero in a bitwise AND with number (101010...10)2: 4^a ∧ (101010...10)2 == 0
 * How long should be (101010...10)2 if x is a signed integer? 32 bits. To write shorter, in 8 charaters instead of
 * 32, it's common to use hexadecimal representation: (101010...10)2 = (aaaaaaaa)16
 * x∧(aaaaaaaa)16 ==0
 *
 * Pattern of 4 power is 1, 100, 10100, 1010100 - so we need to look for alternate 1s in addition to power of 2 check
 */
public class PowerOfFour {
    public boolean isPowerOfFour(int num) {
        return (num > 0) && (num & (num - 1)) == 0 && (num & 0xaaaaaaaa) == 0;
    }
}
