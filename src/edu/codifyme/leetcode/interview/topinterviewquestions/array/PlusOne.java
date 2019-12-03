package edu.codifyme.leetcode.interview.topinterviewquestions.array;

import java.util.ArrayList;
import java.util.List;

public class PlusOne {
/** EASY Collection **/
/** https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/559/ **/
/**
 Given a non-empty array of digits representing a non-negative integer, plus one to the integer.

 The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.

 You may assume the integer does not contain any leading zero, except the number 0 itself.

 Example 1:

 Input: [1,2,3]
 Output: [1,2,4]
 Explanation: The array represents the integer 123.
 Example 2:

 Input: [4,3,2,1]
 Output: [4,3,2,2]
 Explanation: The array represents the integer 4321.

 */
    public int[] plusOne(int[] digits) {
        int carry = 1;
        List<Integer> list = new ArrayList<Integer>();

        for (int i = digits.length; i > 0; i--) {
            int digit = digits[i-1];
            list.add(0, ((carry + digit) % 10) );
            carry = (((carry + digit) - 10) < 0) ? 0: ((carry + digit) - 9);
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
}
