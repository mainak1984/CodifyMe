package edu.codifyme.leetcode.practice.others;

/**
 * 1009. Complement of Base 10 Integer
 * 476. Number Complement
 * EASY: https://leetcode.com/problems/complement-of-base-10-integer/
 *
 * Every non-negative integer N has a binary representation.  For example, 5 can be represented as "101" in binary, 11
 * as "1011" in binary, and so on.  Note that except for N = 0, there are no leading zeroes in any binary representation.
 *
 * The complement of a binary representation is the number in binary you get when changing every 1 to a 0 and 0 to a 1.
 * For example, the complement of "101" in binary is "010" in binary.
 * For a given number N in base-10, return the complement of it's binary representation as a base-10 integer.
 *
 * Example 1:
 * Input: 5
 * Output: 2
 * Explanation: 5 is "101" in binary, with complement "010" in binary, which is 2 in base-10.
 *
 * Example 2:
 * Input: 7
 * Output: 0
 * Explanation: 7 is "111" in binary, with complement "000" in binary, which is 0 in base-10.
 *
 * Example 3:
 * Input: 10
 * Output: 5
 * Explanation: 10 is "1010" in binary, with complement "0101" in binary, which is 5 in base-10.
 *
 * Note:
 * 0 <= N < 10^9
 * This question is the same as 476: https://leetcode.com/problems/number-complement/
 *
 * Approach:
 * XOR
 * XOR of zero and a bit results in that bit
 * 0 ⊕ x=x
 * XOR of one and a bit flips that bit
 * 1 ⊕ x=1−x
 *
 * construct 1-bits bitmask which has the same length as the input number, and to get the answer as bitmask - num or
 * bitmask ^ num
 *
 * There are many ways to do it, let's start from the simplest one:
 *  - Compute bit length of the input number l = [log2 num]+1.
 *  - Compute 1-bits bitmask of length l: bitmask=(1<<l)−1.
 *  - Return num ^ bitmask.
 */
public class ComplementOfBase10Integer {
    public int bitwiseComplement(int N) {
        String binary = Integer.toBinaryString(N);
        StringBuilder sb = new StringBuilder();
        for(char ch: binary.toCharArray()) {
            if (ch == '1') {
                sb.append(0);
            } else {
                sb.append(1);
            }
        }
        return Integer.parseInt(sb.toString(), 2);
    }

    // public int bitwiseComplement(int N) {
    //     // l is a length of N in binary representation
    //     int l = (int)( Math.log(N) / Math.log(2) ) + 1;
    //     // bitmask has the same length as num and contains only ones 1...1
    //     int bitmask = (1 << l) - 1;
    //     // flip all bits
    //     return bitmask ^ N;
    //   }
}
