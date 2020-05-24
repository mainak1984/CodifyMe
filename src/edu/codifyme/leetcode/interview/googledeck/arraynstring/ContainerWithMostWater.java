package edu.codifyme.leetcode.interview.googledeck.arraynstring;

/**
 * MEDIUM:
 * https://leetcode.com/explore/interview/card/google/59/array-and-strings/3048/
 *
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * Note: You may not slant the container and n is at least 2.
 * The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.

 * Example:
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 *
 * Approach 2: Two Pointer Approach
 * Algorithm
 * The intuition behind this approach is that the area formed between the lines will always be limited by the height of the shorter line. Further, the farther the lines, the more will be the area obtained.
 * We take two pointers, one at the beginning and one at the end of the array constituting the length of the lines. Futher, we maintain a variable \text{maxarea}maxarea to store the maximum area obtained till now. At every step, we find out the area formed between them, update \text{maxarea}maxarea and move the pointer pointing to the shorter line towards the other end by one step.
 *
 * Complexity Analysis
 * Time complexity : O(n) Single pass.
 * Space complexity : O(1) Constant space is used.
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        if ( null == height || height.length < 2 ) {
            return 0;
        }

        int start = 0;
        int end = height.length - 1;
        int max = 0;

        while (start < end) {
            if ( height[start] < height[end] ) {
                max = Math.max(max, height[start] * (end - start));

                // Boosting
                int startHeight = height[start];
                while ( start < height.length - 1 && height[start] <= startHeight ) {
                    start++;
                }
            } else {
                max = Math.max(max, height[end] * (end - start));

                // Boosting
                int endHeight = height[end];
                while ( end > 0 && height[end] <= endHeight ) {
                    end--;
                }
            }
        }

        return max;
    }
}
