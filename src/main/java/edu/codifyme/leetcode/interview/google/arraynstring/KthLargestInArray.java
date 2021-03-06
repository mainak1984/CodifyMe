package edu.codifyme.leetcode.interview.google.arraynstring;

import java.util.PriorityQueue;

/**
 * 215. Kth Largest Element in an Array
 * MEDIUM: https://leetcode.com/problems/kth-largest-element-in-an-array
 *
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not
 * the kth distinct element.
 *
 * Example 1:
 *
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * Example 2:
 *
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class KthLargestInArray {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int num: nums) {
            if ( queue.size() < k || queue.peek() < num ) {
                queue.add(num);
            }
            if ( queue.size() > k ) {
                queue.poll();
            }
        }

        return queue.peek();
    }
}
