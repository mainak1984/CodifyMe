package edu.codifyme.leetcode.practice.others;

import java.util.ArrayList;
import java.util.List;

/**
 * 119. Pascal's Triangle II
 * EASY: https://leetcode.com/problems/pascals-triangle-ii/
 *
 * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
 *
 * Note that the row index starts from 0.
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *
 * Example:
 * Input: 3
 * Output: [1,3,3,1]
 * Follow up:
 *
 * Could you optimize your algorithm to use only O(k) extra space?
 *
 * Approach:
 * A single row can be calculated as follows:
 *
 * First compute 1.               -> N choose 0
 * Then N/1                       -> N choose 1
 * Then N*(N-1)/1*2               -> N choose 2
 * Then N*(N-1)*(N-2)/1*2*3       -> N choose 3
 */
public class PascalsTriangleII {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        res.add(1);
        for(int i = 1; i <= rowIndex; i++){
            long num = ((long)res.get(i-1)*(long)(rowIndex-i+1))/i;
            res.add((int)num);
        }
        return res;
    }

//     public List<Integer> getRow(int rowIndex) {
//         Integer[] result = new Integer[rowIndex+1];

//         int left = 0;
//         int right = rowIndex;

//         result[left++] = 1;
//         result[right--] = 1;

//         long numerator = rowIndex;
//         long denominator = 1;
//         long value = 1;

//         while (left <= right) {
//             value = (value * numerator--) / denominator++;
//             result[left++] = (int)value;
//             result[right--] = (int)value;
//         }

//         return Arrays.asList(result);
//     }
}
