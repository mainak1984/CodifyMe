package edu.codifyme.leetcode.interview.google.others;

import java.util.Arrays;

/**
 * HARD:
 * https://leetcode.com/problems/candy
 *
 * There are N children standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following requirements:
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 *
 * Example 1:
 * Input: [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 *
 * Example 2:
 * Input: [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 *              The third child gets 1 candy because it satisfies the above two conditions.
 *
 * Solutions:
 * Brute force, double array, single array, one pass
 */
public class Candy {
    public int candy(int[] ratings) {
         int[] candies = new int[ratings.length];
         Arrays.fill(candies, 1);
         for (int i = 1; i < ratings.length; i++) {
             if (ratings[i] > ratings[i - 1]) {
                 candies[i] = candies[i - 1] + 1;
             }
         }
         int sum = candies[ratings.length - 1];
         for (int i = ratings.length - 2; i >= 0; i--) {
             if (ratings[i] > ratings[i + 1]) {
                 candies[i] = Math.max(candies[i], candies[i + 1] + 1);
             }
             sum += candies[i];
         }
         return sum;
        }

//    public int candy(int[] ratings) {
//        int[] candies = new int[ratings.length];
//        int[] candiesR = new int[ratings.length];
//
//        if (null == ratings || 0 == ratings.length) {
//            return 0;
//        }
//
//        candies[0] = 1;
//        for ( int loop = 1; loop < ratings.length; loop++) {
//            if (ratings[loop-1] < ratings[loop]) {
//                candies[loop] = candies[loop-1] + 1;
//            } else {
//                candies[loop] = 1;
//            }
//        }
//
//        int temp = 1;
//        int sum = candies[ratings.length - 1];
//        for ( int loop = ratings.length - 2; loop >= 0; loop--) {
//            if (ratings[loop] > ratings[loop+1]) {
//                temp = temp + 1;
//            } else {
//                temp = 1;
//            }
//            sum += Math.max(temp, candies[loop]);
//        }
//
//        return sum;
//    }
}
