package edu.codifyme.leetcode.interview.topinterviewquestions.stack;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * EASY:
 * 703. https://leetcode.com/problems/kth-largest-element-in-a-stream/
 *
 * Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted
 * order, not the kth distinct element.
 *
 * Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, which contains
 * initial elements from the stream. For each call to the method KthLargest.add, return the element representing the kth
 * largest element in the stream.
 *
 * Example:
 *
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 * Note:
 * You may assume that nums' length ≥ k-1 and k ≥ 1.
 */
public class KthLargestInStream {
    final PriorityQueue<Integer> q;
    final int k;

    public KthLargestInStream(int k, int[] a) {
        this.k = k;
        q = new PriorityQueue<>(k);
        for (int n : a)
            add(n);
    }

    public int add(int n) {
        if (q.size() < k)
            q.offer(n);
        else if (q.peek() < n) {
            q.poll();
            q.offer(n);
        }
        return q.peek();
    }

//    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
//    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
//    int kVal;
//
//    public KthLargestInStream(int k, int[] nums) {
//        kVal = k;
//        int i = 0;
//
//        // Arrays.sort(nums, Collections.reverseOrder());
//
//        for (; i < nums.length; i++) {
//            if (i < k) {
//                // insert into minHeap
//                minHeap.add(nums[i]);
//            } else {
//                maxHeap.add(nums[i]);
//
//                if (maxHeap.peek() > minHeap.peek()) {
//                    // change their positions
//                    int top = maxHeap.poll();
//                    minHeap.add(top);
//                    top = minHeap.poll();
//                    maxHeap.add(top);
//                }
//            }
//        }
//    }
//
//    public int add(int val) {
//        minHeap.add(val);
//
//        if ( minHeap.size() > kVal ) {
//            int top = minHeap.poll();
//            maxHeap.add(top);
//        }
//
//        return minHeap.peek();
//    }
}
