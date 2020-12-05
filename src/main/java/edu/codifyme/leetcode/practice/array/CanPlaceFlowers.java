package edu.codifyme.leetcode.practice.array;

/**
 * 605. Can Place Flowers
 * EASY: https://leetcode.com/problems/can-place-flowers/
 *
 * You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be
 * planted in adjacent plots.
 *
 * Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n,
 * return if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule.
 *
 * Example 1:
 * Input: flowerbed = [1,0,0,0,1], n = 1
 * Output: true
 *
 * Example 2:
 * Input: flowerbed = [1,0,0,0,1], n = 2
 * Output: false
 *
 * Constraints:
 * 1 <= flowerbed.length <= 2 * 104
 * flowerbed[i] is 0 or 1.
 * There are no two adjacent flowers in flowerbed.
 * 0 <= n <= flowerbed.length
 *
 * Approach:
 * The solution is very simple. We can find out the extra maximum number of flowers, count, that can be planted for the
 * given flowerbed arrangement. To do so, we can traverse over all the elements of the flowerbed and find out those
 * elements which are 0(implying an empty position). For every such element, we check if its both adjacent positions are
 * also empty. If so, we can plant a flower at the current position without violating the no-adjacent-flowers-rule. For
 * the first and last elements, we need not check the previous and the next adjacent positions respectively.
 *
 * If the count obtained is greater than or equal to n, the required number of flowers to be planted, we can plant n
 * flowers in the empty spaces, otherwise not.
 *
 * Instead of finding the maximum value of count that can be obtained, as done in the last approach, we can stop the
 * process of checking the positions for planting the flowers as soon as count becomes equal to n. Doing this leads to
 * an optimization of the first approach. If count never becomes equal to n, nn flowers can't be planted at the empty
 * positions.
 */
public class CanPlaceFlowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int i = 0, count = 0;
        while (i < flowerbed.length) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) &&
                    (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i++] = 1;
                count++;
            }
            if(count>=n)
                return true;
            i++;
        }
        return false;
    }
}
