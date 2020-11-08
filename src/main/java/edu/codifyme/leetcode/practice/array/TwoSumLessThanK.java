package edu.codifyme.leetcode.practice.array;

import java.util.Arrays;

/**
 * 1099. Two Sum Less Than K
 * EASY: https://leetcode.com/problems/two-sum-less-than-k/
 *
 * Given an array A of integers and integer K, return the maximum S such that there exists i < j with A[i] + A[j] = S
 * and S < K. If no i, j exist satisfying this equation, return -1.
 *
 * Example 1:
 * Input: A = [34,23,1,24,75,33,54,8], K = 60
 * Output: 58
 * Explanation: We can use 34 and 24 to sum 58 which is less than 60.
 *
 * Example 2:
 * Input: A = [10,20,30], K = 15
 * Output: -1
 * Explanation: In this case it is not possible to get a pair sum less that 15.
 *
 * Constraints:
 * 1 <= A.length <= 100
 * 1 <= A[i] <= 1000
 * 1 <= K <= 2000
 *
 * Approach:
 * 1. Sort array; pick one element, search for its pair using binary search
 * Time Complexity: \mathcal{O}(n\log{n})O(nlogn) to sort the array and do the binary search for each element.
 * Space Complexity: from \mathcal{O}(\log{n})O(logn) to \mathcal{O}(n)O(n), depending on the implementation of the
 * sorting algorithm.
 *
 * 2. Sort array; use two pointer to find the possible pairs
 * Time Complexity: O(nlogn) to sort the array. The two pointers approach itself is O(n), so the time complexity would
 * be linear if the input is sorted.
 * Space Complexity: from O(logn) to O(n), depending on the implementation of the sorting algorithm.
 *
 * 3. Use counting sort to reduce sorting complexity
 * Time Complexity: \mathcal{O}(n + m)O(n+m), where mm corresponds to the range of values in the input array.
 * Space Complexity: \mathcal{O}(m)O(m) to count each value.
 */
public class TwoSumLessThanK {
    // 2 pointer
    public int twoSumLessThanK(int[] A, int K) {
        int S = -1;
        Arrays.sort(A);
        int lo = 0, hi = A.length - 1;
        while (lo < hi) {
            if (A[lo] + A[hi] < K) {
                S = Math.max(S, A[lo] + A[hi]);
                ++lo;
            }
            else
                --hi;
        }
        return S;
    }

    // Binary search
//    public int twoSumLessThanK(int[] A, int K) {
//        int S = -1;
//        Arrays.sort(A);
//        for (int i = 0; i < A.length; ++i) {
//            var idx = Arrays.binarySearch(A, i + 1, A.length, K - A[i] - 1);
//            int j = (idx >= 0 ? idx : ~idx);
//            if (j == A.length || A[j] > K - A[i] - 1)
//                --j;
//            if (j > i) {
//                S = Math.max(S, A[i] + A[j]);
//            }
//        }
//        return S;
//    }

    // Counting sort
//    public int twoSumLessThanK(int[] A, int K) {
//        int S = -1;
//        int[] count = new int[1001];
//        for (int i: A)
//            ++count[i];
//        int lo = 1, hi = 1000;
//        while (lo <= hi) {
//            if (lo + hi >= K || count[hi] == 0)
//                --hi;
//            else {
//                if (count[lo] > (lo < hi ? 0 : 1))
//                    S = Math.max(S, lo + hi);
//                ++lo;
//            }
//        }
//        return S;
//    }

    // Inefficient implementation
//    public int twoSumLessThanK(int[] A, int K) {
//        Arrays.sort(A);
//        // System.out.println(Arrays.toString(A));
//
//        int closest = -1;
//        for (int i = 0; i < A.length; i++) {
//            int b = lowerMatch(A, i+1, A.length - 1, K-A[i]);
//            // System.out.println(A[i] +","+ b +" = "+(A[i]+b));
//
//            if (b == -1) {
//                break;
//            }
//
//            if (closest == -1 || (A[i] + b) > closest) {
//                closest = A[i] + b;
//            }
//        }
//
//        return closest;
//    }
//
//    int lowerMatch(int[] A, int low, int high, int target) {
//        if (low > high || A[low] >= target) {
//            return -1;
//        }
//        int least = A[low];
//
//        while (low <= high) {
//            int mid = low + (high - low)/2;
//
//            if (A[mid] < target) {
//                least = A[mid];
//                low = mid + 1;
//            } else {
//                high = mid - 1;
//            }
//        }
//
//        return least;
//    }
}
