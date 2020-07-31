package edu.codifyme.geeksforgeeks.stacknqueue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * MEDIUM: Median in a stream of integers (running integers)
 * https://www.geeksforgeeks.org/median-of-stream-of-integers-running-integers/
 *
 * Given that integers are read from a data stream. Find median of elements read so for in efficient way. For simplicity
 * assume there are no duplicates. For example, let us consider the stream 5, 15, 1, 3 …
 *
 * After reading 1st element of stream - 5 -> median - 5
 * After reading 2nd element of stream - 5, 15 -> median - 10
 * After reading 3rd element of stream - 5, 15, 1 -> median - 5
 * After reading 4th element of stream - 5, 15, 1, 3 -> median - 4, so on...
 * Making it clear, when the input size is odd, we take the middle element of sorted data. If the input size is even, we
 * pick average of middle two elements in sorted stream.
 *
 * Note that output is effective median of integers read from the stream so far. Such an algorithm is called online
 * algorithm. Any algorithm that can guarantee output of i-elements after processing i-th element, is said to be online
 * algorithm. Let us discuss three solutions for the above problem.
 *
 * Approach:
 * Use 2 heaps (min and max) to help the 2 centre elements in the top of heap
 */
public class MedianOfStream {
    PriorityQueue<Integer> minHeap = null;
    PriorityQueue<Integer> maxHeap = null;

    /** initialize your data structure here. */
    public MedianOfStream() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public void addNum(int num) {
        minHeap.offer(num);
        maxHeap.offer(minHeap.poll());

        if(minHeap.size()<maxHeap.size()){
            minHeap.offer(maxHeap.poll());
        }
    }

    public double findMedian() {
        if(minHeap.size() > maxHeap.size()){
            return minHeap.peek();
        }else {
            return (minHeap.peek()+maxHeap.peek())/2.0;
        }
    }
}
