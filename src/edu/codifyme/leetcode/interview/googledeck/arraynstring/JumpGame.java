package edu.codifyme.leetcode.interview.googledeck.arraynstring;

/**
 * https://leetcode.com/explore/interview/card/google/59/array-and-strings/3053/
 *
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * Example 2:
 *
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 10^4
 * 0 <= nums[i][j] <= 10^5
 *
 * Approach 3: Dynamic Programming Bottom-up
 * Top-down to bottom-up conversion is done by eliminating recursion. In practice, this achieves better performance as
 * we no longer have the method stack overhead and might even benefit from some caching. More importantly, this step opens
 * up possibilities for future optimization. The recursion is usually eliminated by trying to reverse the order of the
 * steps from the top-down approach.
 * The observation to make here is that we only ever jump to the right. This means that if we start from the right of the
 * array, every time we will query a position to our right, that position has already be determined as being GOOD or BAD.
 * This means we don't need to recurse anymore, as we will always hit the memo table.
 *
 * Approach 4: Greedy
 * Once we have our code in the bottom-up state, we can make one final, important observation. From a given position,
 * when we try to see if we can jump to a GOOD position, we only ever use one - the first one (see the break statement).
 * In other words, the left-most one. If we keep track of this left-most GOOD position as a separate variable, we can avoid
 * searching for it in the array.
 */
public class JumpGame {
    public boolean canJump(int[] nums) {
        if ( null == nums || 0 == nums.length ) return false;

        int lastValidPos = nums.length - 1;

        for (int loop = nums.length - 2; loop >= 0; loop-- ) {
            if ( loop + nums[loop] >= lastValidPos ) {
                lastValidPos = loop;
            }
        }

        return (0 == lastValidPos)? true: false;
    }
}
