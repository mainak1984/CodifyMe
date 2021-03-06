package edu.codifyme.leetcode.interview.google.arraynstring;

/**
 * 849. Maximize Distance to Closest Person
 * EASY: https://leetcode.com/problems/maximize-distance-to-closest-person
 *
 * In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty.
 *
 * There is at least one empty seat, and at least one person sitting.
 *
 * Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
 *
 * Return that maximum distance to closest person.
 *
 * Example 1:
 *
 * Input: [1,0,0,0,1,0,1]
 * Output: 2
 * Explanation:
 * If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
 * If Alex sits in any other open seat, the closest person has distance 1.
 * Thus, the maximum distance to the closest person is 2.
 * Example 2:
 *
 * Input: [1,0,0,0]
 * Output: 3
 * Explanation:
 * If Alex sits in the last seat, the closest person is 3 seats away.
 * This is the maximum distance possible, so the answer is 3.
 * Note:
 *
 * 1 <= seats.length <= 20000
 * seats contains only 0s or 1s, at least one 0, and at least one 1.
 *
 * Approach #1: Next Array [Accepted]
 * Let left[i] be the distance from seat i to the closest person sitting to the left of i. Similarly, let right[i] be the
 * distance to the closest person sitting to the right of i. This is motivated by the idea that the closest person in seat
 * i sits a distance min(left[i], right[i]) away.
 */
public class MaximizeDistanceToClosestPerson {
    public int maxDistToClosest(int[] seats) {
        int dist = 0;
        int maxDistance = 0;
        boolean edge = false;

        if (seats[0] == 0) {
            edge = true;
        }

        for (int seat: seats) {
            if ( 0 == seat) {
                dist++;
            } else {
                if (edge) {
                    maxDistance = Math.max(maxDistance, dist);
                    edge = false;
                } else {
                    maxDistance = Math.max(maxDistance, (dist+1)/2);
                }
                dist = 0;
            }
        }

        if(0 != dist) {
            maxDistance = Math.max(maxDistance, dist);
        }

        return maxDistance;
    }
}
