package edu.codifyme.leetcode.interview.mocktest.google.list;

import java.util.LinkedList;
import java.util.List;

/**
 * EASY: Moving Average from Data Stream
 * https://leetcode.com/problems/moving-average-from-data-stream/
 *
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 *
 * Example:
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 */
public class MovingAveragefromDataStream {
    int size;
    int sum = 0;
    List<Integer> list = new LinkedList<>();

    /** Initialize your data structure here. */
    public MovingAveragefromDataStream(int size) {
        this.size = size;
    }

    public double next(int val) {
        list.add(val);
        sum += val;

        if (list.size() > size) {
            sum -= list.get(0);
            list.remove(0);
        }

        return (double)sum / list.size();
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */