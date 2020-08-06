package edu.codifyme.leetcode.interview.google.intro;

import java.util.TreeMap;

/**
 * HARD:
 * https://leetcode.com/problems/odd-even-jump
 *
 * You are given an integer array A.  From some starting index, you can make a series of jumps.  The (1st, 3rd, 5th, ...)
 * jumps in the series are called odd numbered jumps, and the (2nd, 4th, 6th, ...) jumps in the series are called even
 * numbered jumps.
 *
 * You may from index i jump forward to index j (with i < j) in the following way:
 *
 * During odd numbered jumps (ie. jumps 1, 3, 5, ...), you jump to the index j such that A[i] <= A[j] and A[j] is the
 * smallest possible value.  If there are multiple such indexes j, you can only jump to the smallest such index j.
 * During even numbered jumps (ie. jumps 2, 4, 6, ...), you jump to the index j such that A[i] >= A[j] and A[j] is the
 * largest possible value.  If there are multiple such indexes j, you can only jump to the smallest such index j.
 * (It may be the case that for some index i, there are no legal jumps.)
 * A starting index is good if, starting from that index, you can reach the end of the array (index A.length - 1) by
 * jumping some number of times (possibly 0 or more than once.)
 *
 * Return the number of good starting indexes.
 *
 * Example 1:
 *
 * Input: [10,13,12,14,15]
 * Output: 2
 * Explanation:
 * From starting index i = 0, we can jump to i = 2 (since A[2] is the smallest among A[1], A[2], A[3], A[4] that is
 * greater or equal to A[0]), then we can't jump any more.
 * From starting index i = 1 and i = 2, we can jump to i = 3, then we can't jump any more.
 * From starting index i = 3, we can jump to i = 4, so we've reached the end.
 * From starting index i = 4, we've reached the end already.
 * In total, there are 2 different starting indexes (i = 3, i = 4) where we can reach the end with some
 *
 * Example 2:
 * Input: [2,3,1,1,4]
 * Output: 3
 *
 * Example 3:
 * Input: [5,1,3,4,2]
 * Output: 3
 *
 * Explanation:
 * Your first jump from nums[i] is an odd jump, and you must land at the first element closest to nums[i] in the positive
 * direction. Let's call it nums[j]. And your second jump from nums[j] is an even jump and must end up at the first
 * element closest to nums[j] in the negative direction. Then your third jump is an odd jump, fourth jump is an even jump,
 * so on so forth.
 *
 * Approach 2: Tree Map
 * Intuition
 * As in Approach 1, the problem reduces to solving this question: for some index i during an odd numbered jump, what
 * index do we jump to (if any)?
 *
 * Algorithm
 * We can use a TreeMap, which is an excellent structure for maintaining sorted data. Our map vals will map values
 * v = A[i] to indices i. Iterating from i = N-2 to i = 0, we have some value v = A[i] and we want to know what the next
 * largest or next smallest value is. The TreeMap.lowerKey and TreeMap.higherKey functions do this for us.
 * With this in mind, the rest of the solution is straightforward: we use dynamic programming to maintain odd[i] and
 * even[i]: whether the state of being at index i on an odd or even numbered jump is possible to reach.
 *
 * Complexity Analysis
 * Time Complexity: O(NlogN), where N is the length of A.
 * Space Complexity: O(N).
 */
public class OddEvenJump {
    public int oddEvenJumps(int[] A) {
        int N = A.length;
        if (N <= 1) return N;
        boolean[] odd = new boolean[N];
        boolean[] even = new boolean[N];
        odd[N-1] = even[N-1] = true;

        TreeMap<Integer, Integer> vals = new TreeMap();
        vals.put(A[N-1], N-1);
        for (int i = N-2; i >= 0; --i) {
            int v = A[i];
            if (vals.containsKey(v)) {
                odd[i] = even[vals.get(v)];
                even[i] = odd[vals.get(v)];
            } else {
                Integer lower = vals.lowerKey(v);
                Integer higher = vals.higherKey(v);

                if (lower != null)
                    even[i] = odd[vals.get(lower)];
                if (higher != null) {
                    odd[i] = even[vals.get(higher)];
                }
            }
            vals.put(v, i);
        }

        int ans = 0;
        for (boolean b: odd)
            if (b) ans++;
        return ans;
    }
}
