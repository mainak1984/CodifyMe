package edu.codifyme.leetcode.interview.unordered;

/**
 * 11. Container With Most Water
 * EASY: https://leetcode.com/problems/container-with-most-water/
 *
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 *
 * Note: You may not slant the container and n is at least 2.
 *
 * The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
 *
 * Example:
 *
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 *
 * Hint: Start with the maximum width container and go to a shorter width container if there is a vertical line longer than the current containers shorter line.
 * This way we are compromising on the width but we are looking forward to a longer length container.
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int max = 0;
        if (height.length == 0) {
            return max;
        }
        int start = 0;
        int end = height.length - 1;
        while (start < end) {
            if (height[start] < height[end]) {
                max = Math.max(max, height[start] * (end - start));
                start++;
            }
            else {
                max = Math.max(max, height[end] * (end - start));
                end--;
            }
        }
        return max;
    }
}
