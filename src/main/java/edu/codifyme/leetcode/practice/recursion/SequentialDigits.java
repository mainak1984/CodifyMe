package edu.codifyme.leetcode.practice.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 1291. Sequential Digits
 * MEDIUM: https://leetcode.com/problems/sequential-digits/
 *
 * An integer has sequential digits if and only if each digit in the number is one more than the previous digit.
 * Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.
 *
 * Example 1:
 * Input: low = 100, high = 300
 * Output: [123,234]
 *
 * Example 2:
 * Input: low = 1000, high = 13000
 * Output: [1234,2345,3456,4567,5678,6789,12345]
 *
 * Constraints:
 * 10 <= low <= high <= 10^9
 *
 * Approach:
 *
 * Approach 1: Sliding Window
 * One might notice that all integers that have sequential digits are substrings of string "123456789". Hence to
 * generate all such integers of a given length, just move the window of that length along "123456789" string.
 * The advantage of this method is that it will generate the integers that are already in the sorted order.
 *
 * Complexity Analysis
 * Time complexity: O(1). Length of sample string is 9, and lengths of low and high are between 2 and 9. Hence the
 * nested loops are executed no more than 8 \times 8 = 648×8=64 times.
 *
 * Space complexity: O(1) to keep not more than 36 integers with sequential digits.
 *
 * Approach 2:
 * Generate numbers with a starting digit, say 1 or 2. Keep on appending numbers at the back until they are in range.
 * Move to next starting number.
 * Sort it finally.
 *
 * Complexity:
 * Time: O(nlogn) as sorting is involved; however as there are only 36 possibilities, this also works fast.
 *
 * Approach 3:
 * Use a seed number of length of lowest number. Keep on generating numbers of that range.
 * Move to one more higher length of numbers until highest number
 *
 * Complexity: O(1)
 */
public class SequentialDigits {
    // Approach 1:
    public List<Integer> sequentialDigits(int low, int high) {
        String sample = "123456789";
        int n = 10;
        List<Integer> nums = new ArrayList();

        int lowLen = String.valueOf(low).length();
        int highLen = String.valueOf(high).length();
        for (int length = lowLen; length < highLen + 1; ++length) {
            for (int start = 0; start < n - length; ++start) {
                int num = Integer.parseInt(sample.substring(start, start + length));
                if (num >= low && num <= high) nums.add(num);
            }
        }
        return nums;
    }

    // Approach 2:
//    public List<Integer> sequentialDigits(int low, int high) {
//         List<Integer> output = new ArrayList<Integer>();
//         for(int i=1;i<10;i++){
//             int num=0, j =i;
//             while(num<=high && j<10){
//                  num = num*10+(j);
//                 if(num>=low && num<=high){
//                     output.add(num);
//                 }
//                 j++;
//             }
//         }
//         Collections.sort(output);
//        return output;
//     }

    // Approach 3:
//     public List<Integer> sequentialDigits(int low, int high) {
//         List<Integer> results = new LinkedList<>();

//         int lowbits = (int)Math.floor(Math.log10(low) + 1);
//         int highbits = (int)Math.floor(Math.log10(high) + 1);

//         for (int i = lowbits; i <= highbits; i++) {
//             generateNums(low, high, i, results);
//         }

//         return results;
//     }

//     void generateNums(int low, int high, int depth, List<Integer> results) {
//         int num = 1;
//         for (int i = 0, j = 2; i < depth - 1; i++, j++) {
//             num = num*10 + j;
//         }

//         int base = (int)Math.pow(10, depth - 1);
//         int offset = base;

//         if (num >= low && num <= high) {
//             results.add(num);
//         }

//         for (int i = depth+1; i <= 9 && num <= high; i++) {
//             num -= offset;
//             offset += base;
//             num = num*10 + i;

//             if (num >= low && num <= high) {
//                 results.add(num);
//             }
//         }
//     }
}
