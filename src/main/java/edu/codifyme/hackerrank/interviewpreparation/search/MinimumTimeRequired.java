package edu.codifyme.hackerrank.interviewpreparation.search;

import java.util.Arrays;

/**
 * MEDIUM:
 * https://www.hackerrank.com/challenges/minimum-time-required/problem
 *
 * You are planning production for an order. You have a number of machines that each have a fixed number of days to
 * produce an item. Given that all the machines operate simultaneously, determine the minimum number of days to produce
 * the required order.
 *
 * For example, you have to produce goal=10 items. You have three machines that take machines = [2,3,2] days to produce
 * an item. The following is a schedule of items produced:
 *
 * Day Production  Count
 * 2   2               2
 * 3   1               3
 * 4   2               5
 * 6   3               8
 * 8   2              10
 * It takes  days to produce  items using these machines.
 *
 * Function Description:
 * Complete the minimumTime function in the editor below. It should return an integer representing the minimum number of
 * days required to complete the order.
 *
 * minimumTime has the following parameter(s):
 *
 * machines: an array of integers representing days to produce one item per machine
 * goal: an integer, the number of items required to complete the order
 *
 * Approach:
 * Find min time taking machine, which can give the max days it can take
 * Do a binary search on date, if feasible between lowest(day) -> max day
 */
public class MinimumTimeRequired {
    // Complete the minTime function below.
    static long minTime(long[] machines, long goal) {
        Arrays.sort(machines);

        // find the shortest time taken by a single machine to satisfy quantity
        long targetMaxDays = goal * machines[0];

        return getMinDays(machines, goal, 1, targetMaxDays);
    }

    static long getMinDays(long[] machines, long goal, long minDays, long maxDays) {
        if ( minDays >= maxDays ){
            return minDays;
        }

        int ret = computeDays(machines, goal, (minDays+maxDays)/2 );

        if ( ret < 0 ) {
            // target not met; double the days
            return getMinDays(machines, goal, (minDays + maxDays)/2+1, maxDays);
        } else {
            // target met with more
            return getMinDays(machines, goal, minDays, (minDays + maxDays)/2);
        }
    }

    static int computeDays(long[] machines, long goal, long longMinDays) {
        long unitsProduced = 0;

        for ( long i: machines ) {
            unitsProduced += longMinDays / i;

            // optimization to cut the loop quickly
            if ( goal < unitsProduced ) {
                return 1;
            }
        }

        if (goal == unitsProduced) {
            return 0;
        } else if ( goal > unitsProduced ) {
            return -1;
        } else {
            return 1;
        }
    }
}
