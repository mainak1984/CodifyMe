package edu.codifyme.leetcode.practice.stacknqueue;

/**
 * 346. Moving Average from Data Stream
 * EASY: https://leetcode.com/problems/moving-average-from-data-stream/
 *
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 *
 * Example:
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 *
 * Approach 1: Circular Queue with Array
 * The major advantage of circular queue is that by adding a new element to a full circular queue, it automatically
 * discards the oldest element. Unlike deque, we do not need to explicitly remove the oldest element.
 * Another advantage of circular queue is that a single index suffices to keep track of both ends of the queue, unlike
 * deque where we have to keep a pointer for each end.
 *
 * Approach 2: Double-ended Queue
 * Add element to end of queue and remove it from front when size is reached
 */
public class MovingAverageFromDataStream {
    // Approach 1:
    /** Initialize your data structure here. */
    int count = 0;
    int sum = 0;
    int[] arr;
    int size;

    /** Initialize your data structure here. */
    public MovingAverageFromDataStream(int size) {
        arr = new int[size];
        this.size = size;
    }

    public double next(int val) {
        int index = count++ % size;
        sum += val - arr[index];
        arr[index] = val;
        int divisor = count >= size ? size: count;
        return (double)sum / divisor;
    }

    // Approach: 2
//    int size;
//    int sum = 0;
//    List<Integer> list = new LinkedList<>();
//
//    /** Initialize your data structure here. */
//    public MovingAverageFromDataStream(int size) {
//        this.size = size;
//    }
//
//    public double next(int val) {
//        list.add(val);
//        sum += val;
//
//        if (list.size() > size) {
//            sum -= list.get(0);
//            list.remove(0);
//        }
//
//        return (double)sum / list.size();
//    }
}
