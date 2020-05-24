package edu.codifyme.leetcode.interview.googledeck.arraynstring;

/**
 * HARD
 * https://leetcode.com/explore/interview/card/google/59/array-and-strings/341/
 *
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
 *
 * Example:
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 *
 * Approach: Using Stacks
 * Use stack to store the indices of the bars.
 * Iterate the array:
 * While stack is not empty and \text{height[current]}>\text{height[st.top()]}height[current]>height[st.top()]
 * It means that the stack element can be popped. Pop the top element as \text{top}top.
 * Find the distance between the current element and the element at top of stack, which is to be filled. \text{distance} = \text{current} - \text{st.top}() - 1distance=current−st.top()−1
 * Find the bounded height \text{bounded\_height} = \min(\text{height[current]}, \text{height[st.top()]}) - \text{height[top]}bounded_height=min(height[current],height[st.top()])−height[top]
 * Add resulting trapped water to answer \text{ans} \mathrel{+}= \text{distance} \times \text{bounded\_height}ans+=distance×bounded_height
 * Push current index to top of the stack
 * Move \text{current}current to the next position
 */
public class TrappingRainWater {
    public int trap(int[] height) {
        if ( null == height || height.length == 0 ) {
            return 0;
        }

        int[] waterHeight = new int[height.length];
        int seenSoFar = height[0];
        int sum = 0;

        for (int loop = 0; loop < height.length; loop++ ) {
            waterHeight[loop] = Math.max(seenSoFar, height[loop]);
            seenSoFar = waterHeight[loop];
        }
        seenSoFar = height[height.length-1];
        for (int loop = height.length - 1; loop >= 0; loop-- ) {
            seenSoFar = Math.max(seenSoFar, height[loop]);
            int actualHeight = Math.min(waterHeight[loop], seenSoFar);
            sum += (actualHeight - height[loop]);
        }

        return sum;
    }
}
