package edu.codifyme.leetcode.practice.searchnsort;

import java.util.Random;
import java.util.TreeMap;

/**
 * 497. Random Point in Non-overlapping Rectangles
 * https://leetcode.com/problems/random-point-in-non-overlapping-rectangles/
 *
 * Given a list of non-overlapping axis-aligned rectangles rects, write a function pick which randomly and uniformily
 * picks an integer point in the space covered by the rectangles.
 *
 * Note:
 *
 * An integer point is a point that has integer coordinates.
 * A point on the perimeter of a rectangle is included in the space covered by the rectangles.
 * ith rectangle = rects[i] = [x1,y1,x2,y2], where [x1, y1] are the integer coordinates of the bottom-left corner,
 * and [x2, y2] are the integer coordinates of the top-right corner.
 * length and width of each rectangle does not exceed 2000.
 * 1 <= rects.length <= 100
 * pick return a point as an array of integer coordinates [p_x, p_y]
 * pick is called at most 10000 times.
 * Example 1:
 *
 * Input:
 * ["Solution","pick","pick","pick"]
 * [[[[1,1,5,5]]],[],[],[]]
 * Output:
 * [null,[4,1],[4,1],[3,3]]
 *
 * Example 2:
 * Input:
 * ["Solution","pick","pick","pick","pick","pick"]
 * [[[[-2,-2,-1,-1],[1,0,3,0]]],[],[],[],[],[]]
 * Output:
 * [null,[-1,-2],[2,0],[-2,-1],[3,0],[-2,-2]]
 *
 * Explanation of Input Syntax:
 * The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the
 * array of rectangles rects. pick has no arguments. Arguments are always wrapped with a list, even if there aren't any.
 *
 * Approach:
 * Intuition
 *
 * Some rectangles may be more likely to be sampled from than others, since some may contain more points than others,
 * and each point has an equal chance of being sampled. Is there a way to select a rectangle to sample from, such that
 * the probabilities are proportional to the number of points contained in each rectangle? Is there a way to do this
 * using less than O(total number of points) space?
 *
 * Algo 1:
 * Find a random point from total no. of points and use TreeMap to find the rectangle it maps to
 *
 * Algo 2:
 * Find a random point from total no. and use binary search to locate right rectangle in which it contains
 */
public class RandomPointInRect {
    TreeMap<Integer, Integer> map;
    int[][] arrays;
    int sum;
    Random rnd= new Random();

    public RandomPointInRect(int[][] rects) {
        arrays = rects;
        map = new TreeMap<>();
        sum = 0;

        for(int i = 0; i < rects.length; i++) {
            int[] rect = rects[i];

            // the right part means the number of points can be picked in this rectangle
            sum += (rect[2] - rect[0] + 1) * (rect[3] - rect[1] + 1);

            map.put(sum, i);
        }
    }

    public int[] pick() {
        // nextInt(sum) returns a num in [0, sum -1]. After added by 1, it becomes [1, sum]
        int c = map.ceilingKey( rnd.nextInt(sum) + 1);

        return pickInRect(arrays[map.get(c)]);
    }

    private int[] pickInRect(int[] rect) {
        int left = rect[0], right = rect[2], bot = rect[1], top = rect[3];

        return new int[]{left + rnd.nextInt(right - left + 1), bot + rnd.nextInt(top - bot + 1) };
    }

    // Approach 2: Using Binary search
//     private final int[] sums;
//     private final int[][] rects;
//     private final Random rand = new Random();

//     public Solution(int[][] rects)
//     {
//         this.rects = rects;
//         sums = new int[rects.length];
//         int sum = 0, index;
//         for (int i = 0; i < rects.length; i++)
//         {
//             int[] rect = rects[i];
//             sum += (rect[2] - rect[0] + 1) * (rect[3] - rect[1] + 1);
//             sums[i] = sum;
//         }
//     }

//     public int[] pick()
//     {
//         int lo = 0, hi = sums.length - 1;
//         int target = rand.nextInt(sums[sums.length - 1]);

//         while (lo < hi)
//         {
//             int mid = lo + (hi - lo) / 2;

//             if (target < sums[mid]) hi = mid;
//             else lo = mid + 1;
//         }

//         int[] rect = rects[lo];
//         int width = rect[2] - rect[0] + 1;
//         int height = rect[3] - rect[1] + 1;
//         int base = sums[lo] - width * height;
//         return new int[]{rect[0] + (target - base) % width, rect[1] + (target - base) / width};
//     }
}
