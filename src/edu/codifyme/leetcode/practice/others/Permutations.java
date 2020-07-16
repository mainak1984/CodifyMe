package edu.codifyme.leetcode.practice.others;

import java.util.ArrayList;
import java.util.List;

/**
 * MEDIUM: Permutations
 * https://leetcode.com/problems/permutations/
 *
 * Given a collection of distinct integers, return all possible permutations.
 *
 * Example:
 * Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        traverse(nums, 0, result);

        return result;
    }

    void traverse(int[] nums, int pos, List<List<Integer>> result) {
        // check end condition and print
        if ( pos == nums.length - 1 ) {
            List<Integer> list = new ArrayList<>();
            // add it to list
            for (int num: nums) {
                list.add(num);
            }
            result.add(list);
        }

        // loop for all items from current pos onwards
        // exchange current with that pos and call recur
        for (int loop = pos; loop < nums.length; loop++) {

            int temp = nums[loop];
            nums[loop] = nums[pos];
            nums[pos] = temp;

            traverse(nums, pos+1, result);

            temp = nums[loop];
            nums[loop] = nums[pos];
            nums[pos] = temp;
        }

        // Efficient Implementation: Heap's algorithm
//        public static <T> void printAllRecursive(int n, T[] elements, char delimiter) {
//
//            if(n == 1) {
//                printArray(elements, delimiter);
//            } else {
//                for(int i = 0; i < n-1; i++) {
//                    printAllRecursive(n - 1, elements, delimiter);
//                    if(n % 2 == 0) {
//                        swap(elements, i, n-1);
//                    } else {
//                        swap(elements, 0, n-1);
//                    }
//                }
//                printAllRecursive(n - 1, elements, delimiter);
//            }
//        }
    }
