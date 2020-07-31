package edu.codifyme.hackerrank.interviewpreparation.stack;

import java.util.Stack;

/**
 * MEDIUM:
 * https://www.hackerrank.com/challenges/largest-rectangle/problem
 *
 * Skyline Real Estate Developers is planning to demolish a number of old, unoccupied buildings and construct a shopping
 * mall in their place. Your task is to find the largest solid area in which the mall can be constructed.
 * There are a number of buildings in a certain two-dimensional landscape. Each building has a height, given by h[i]
 * where i belongs to [1,n]. If you join k adjacent buildings, they will form a solid rectangle of area k * min(h[i],
 * h[i+1], h[i+2], ..., h[i+k-1]).
 * For example, the heights array h=[3, 2, 3]. A rectangle of height h=2 and length k=3 can be constructed within the
 * boundaries. The area formed is h*k = 2*3 = 6.
 *
 * Function Description:
 * Complete the function largestRectangle int the editor below. It should return an integer representing the largest
 * rectangle that can be formed within the bounds of consecutive buildings.
 * largestRectangle has the following parameter(s):
 * h: an array of integers representing building heights
 *
 * Sample Input
 * 5
 * 1 2 3 4 5
 * Sample Output
 * 9
 */
public class LargestRectangle {
    static long largestRectangle(int hist[])
    {
        int n = hist.length;
        // Create an empty stack. The stack holds indexes of hist[] array
        // The bars stored in stack are always in increasing order of their
        // heights.
        Stack<Integer> s = new Stack<>();

        long max_area = 0; // Initialize max area
        int tp;  // To store top of stack
        long area_with_top; // To store area with top bar as the smallest bar

        // Run through all bars of given histogram
        int i = 0;
        while (i < n)
        {
            // If this bar is higher than the bar on top stack, push it to stack
            if (s.empty() || hist[s.peek()] <= hist[i])  {
                s.push(i++);
            }

            // If this bar is lower than top of stack, then calculate area of rectangle
            // with stack top as the smallest (or minimum height) bar. 'i' is
            // 'right index' for the top and element before top in stack is 'left index'
            else
            {
                tp = s.peek();  // store the top index
                s.pop();  // pop the top

                // Calculate the area with hist[tp] stack as smallest bar
                area_with_top = hist[tp] * (s.empty() ? i : i - s.peek() - 1);

                // System.out.println("tempArea: "+area_with_top+", maxArea "+max_area);

                // update max area, if needed
                if (max_area < area_with_top)
                    max_area = area_with_top;
            }
        }

        // Now pop the remaining bars from stack and calculate area with every
        // popped bar as the smallest bar
        while (s.empty() == false)
        {
            tp = s.peek();
            s.pop();
            area_with_top = hist[tp] * (s.empty() ? i : i - s.peek() - 1);

            // System.out.println("Finally tempArea: "+area_with_top+", maxArea "+max_area);
            if (max_area < area_with_top)
                max_area = area_with_top;
        }

        return max_area;

    }
}
