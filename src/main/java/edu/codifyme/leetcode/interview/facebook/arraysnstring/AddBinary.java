package edu.codifyme.leetcode.interview.facebook.arraysnstring;

/**
 * 67. Add Binary
 * EASY: https://leetcode.com/problems/add-binary/
 * Editor's choice: Frequently asked in Facebook phone interview.
 *
 * Given two binary strings, return their sum (also a binary string).
 * The input strings are both non-empty and contains only characters 1 or 0.
 *
 * Example 1:
 * Input: a = "11", b = "1"
 * Output: "100"
 *
 * Example 2:
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 *
 * Constraints:
 * Each string consists only of '0' or '1' characters.
 * 1 <= a.length, b.length <= 10^4
 * Each string is either "0" or doesn't contain any leading zero.
 *
 * Approach:
 * There is a simple way using built-in functions:
 * - Convert a and b into integers.
 * - Compute the sum.
 * - Convert the sum back into binary form.
 *
 * In Java this approach is limited by the length of the input strings a and b. Once the string is long enough, the
 * result of conversion into integers will not fit into Integer, Long or BigInteger
 *
 * To fix the issue, one could use standard Bit-by-Bit Computation approach which is suitable for quite long input
 * strings.
 *
 * Approach 2: Bit Manipulation
 * Intuition
 * Here the input is more adapted to push towards Approach 1, but there is popular Facebook variation of this problem
 * when interviewer provides you two numbers and asks to sum them up without using addition operation.
 *
 * No addition? OK, bit manipulation then.
 *
 * Here XOR is a key as well, because it's a sum of two binaries without taking carry into account.
 * To find current carry is quite easy as well, it's AND of two input numbers, shifted one bit to the left.
 *
 * Now the problem is reduced: one has to find the sum of answer without carry and carry. It's the same problem - to sum
 * two numbers, and hence one could solve it in a loop with the condition statement "while carry is not equal to zero".
 *
 * Algorithm
 * - Convert a and b into integers x and y, x will be used to keep an answer, and y for the carry.
 * - While carry is nonzero: y != 0:
 *      - Current answer without carry is XOR of x and y: answer = x^y.
 *      - Current carry is left-shifted AND of x and y: carry = (x & y) << 1.
 *      - Job is done, prepare the next loop: x = answer, y = carry.
 * - Return x in the binary form.
 *
 */
public class AddBinary {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int carrier = 0;
        int m = a.length() - 1;
        int n = b.length() - 1;

        while (m >= 0 || n >= 0) {
            int ac = m >= 0 ? a.charAt(m--) - '0' : 0;
            int bc = n >= 0 ? b.charAt(n--) - '0' : 0;
            int sum = ac + bc + carrier;
            sb.append(sum % 2);
            carrier = sum / 2;
        }
        if (carrier == 1) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }

    // Best: Bitwise manipulation
//    public String addBinary(String a, String b) {
//        BigInteger x = new BigInteger(a, 2);
//        BigInteger y = new BigInteger(b, 2);
//        BigInteger zero = new BigInteger("0", 2);
//        BigInteger carry, answer;
//        while (y.compareTo(zero) != 0) {
//            answer = x.xor(y);
//            carry = x.and(y).shiftLeft(1);
//            x = answer;
//            y = carry;
//        }
//        return x.toString(2);
//    }
}
