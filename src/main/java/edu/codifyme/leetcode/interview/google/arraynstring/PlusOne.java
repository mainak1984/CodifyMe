package edu.codifyme.leetcode.interview.google.arraynstring;

import java.util.ArrayList;
import java.util.List;

/**
 * EASY:
 * https://leetcode.com/problems/plus-one
 *
 * Included in Mock set
 *
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 * The digits are stored such that the most significant digit is at the head of the list, and each element in the array
 * contain a single digit.
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 *
 * Example 1:
 * Input: [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 *
 * Example 2:
 * Input: [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
 *
 * Algorithm:
 * Move along the input array starting from the end of array.
 * Set all the nines at the end of array to zero.
 * If we meet a not-nine digit, we would increase it by one. The job is done - return digits.
 * We're here because all the digits were equal to nine. Now they have all been set to zero. We then append the digit
 * 1 in front of the other digits and return the result.
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        int carry = 1;
        List<Integer> list = new ArrayList<Integer>();

        for (int i = digits.length; i > 0; i--) {
            int digit = digits[i-1];
            list.add(0, ((carry + digit) % 10) );
            carry = (((carry + digit) - 9) <= 0) ? 0: ((carry + digit) - 9);
        }

        if (carry > 0) {
            list.add(0, carry);
        }

        int[] arr = new int[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }

//     public int[] plusOne(int[] digits) {
//         int n = digits.length;

//         // move along the input array starting from the end
//         for (int idx = n - 1; idx >= 0; --idx) {
//           // set all the nines at the end of array to zeros
//           if (digits[idx] == 9) {
//             digits[idx] = 0;
//           }
//           // here we have the rightmost not-nine
//           else {
//             // increase this rightmost not-nine by 1
//             digits[idx]++;
//             // and the job is done
//             return digits;
//           }
//         }
//         // we're here because all the digits are nines
//         digits = new int[n + 1];
//         digits[0] = 1;
//         return digits;
//       }
}
