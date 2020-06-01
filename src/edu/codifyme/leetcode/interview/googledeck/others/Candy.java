package edu.codifyme.leetcode.interview.googledeck.others;

public class Candy {
    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        int[] candiesR = new int[ratings.length];

        if (null == ratings || 0 == ratings.length) {
            return 0;
        }

        candies[0] = 1;
        for ( int loop = 1; loop < ratings.length; loop++) {
            if (ratings[loop-1] < ratings[loop]) {
                candies[loop] = candies[loop-1] + 1;
            } else if (ratings[loop-1] > ratings[loop]) {
                candies[loop] = 1;
            } else {
                candies[loop] = 1;
            }
        }

        candiesR[ratings.length - 1] = 1;
        for ( int loop = ratings.length - 2; loop >= 0; loop--) {
            if (ratings[loop] > ratings[loop+1]) {
                candiesR[loop] = candiesR[loop+1] + 1;
            } else if (ratings[loop] < ratings[loop+1]) {
                candiesR[loop] = 1;
            } else {
                candiesR[loop] = 1;
            }
        }

        int sum = 0;
        for(int loop = 0; loop < ratings.length; loop++) {
            // System.out.println("LeftPass "+candies[loop]+", rightPass "+candiesR[loop]);
            sum += Math.max(candies[loop], candiesR[loop]);;
        }

        return sum;
    }
}
