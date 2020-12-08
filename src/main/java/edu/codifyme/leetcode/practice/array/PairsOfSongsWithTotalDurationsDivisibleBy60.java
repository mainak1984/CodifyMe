package edu.codifyme.leetcode.practice.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 1010. Pairs of Songs With Total Durations Divisible by 60
 * MEDIUM: https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/
 *
 * You are given a list of songs where the ith song has a duration of time[i] seconds.
 *
 * Return the number of pairs of songs for which their total duration in seconds is divisible by 60. Formally, we want
 * the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.
 *
 * Example 1:
 * Input: time = [30,20,150,100,40]
 * Output: 3
 * Explanation: Three pairs have a total duration divisible by 60:
 * (time[0] = 30, time[2] = 150): total duration 180
 * (time[1] = 20, time[3] = 100): total duration 120
 * (time[1] = 20, time[4] = 40): total duration 60
 *
 * Example 2:
 * Input: time = [60,60,60]
 * Output: 3
 * Explanation: All three pairs have a total duration of 120, which is divisible by 60.
 *
 * Constraints:
 * 1 <= time.length <= 6 * 104
 * 1 <= time[i] <= 500
 *
 * Approach: Hashmap
 * We would iterate through the input array time and for each element a, we want to know the number of elements b such
 * that:
 *  - b % 60=0, if a % 60=0
 *  - b % 60=60−a % 60, if a % 60 != 0
 * We can use Approach 1 to implement this logic by repeatedly examining the rest of time again and again for each
 * element a. However, we are able to improve the time complexity by consuming more space - we can store the frequencies
 * of the remainder a % 60, so that we can find the number of the complements in O(1) time.
 * We would initiate an array remainders with size 60 to record the frequencies of each remainder - as the range of
 * remainders is [0,59]. Then we can loop through the array once and for each element a we would:
 *  - if a a % 60=0, add remainders[0] to the result; else, add remainders[60 - t % 60] to the result;
 *  - update remainders[a % 60].
 */
public class PairsOfSongsWithTotalDurationsDivisibleBy60 {
    public int numPairsDivisibleBy60(int[] time) {
        int remainders[] = new int[60];
        int count = 0;
        for (int t: time) {
            if (t % 60 == 0) { // check if a%60==0 && b%60==0
                count += remainders[0];
            } else { // check if a%60+b%60==60
                count += remainders[60 - t % 60];
            }
            remainders[t % 60]++; // remember to update the remainders
        }
        return count;
    }

    // Alternate approach:
    public int numPairsDivisibleBy60_2(int[] times) {
        Map<Integer, Integer> dict = new HashMap<>();
        int count = 0;

        for (int time: times) {
            time = time % 60;
            dict.put(time, dict.getOrDefault(time, 0) + 1);
        }

        for (int time: dict.keySet()) {
            int countVal = dict.get(time);
            if ( countVal > 0) {
                if (time == 0 || time == 30) {
                    count += (countVal * (countVal - 1))/2;
                } else {
                    if (dict.containsKey(60 - time)) {
                        int count1Val = dict.get(60 - time);
                        count += (countVal * count1Val);
                    }
                }
                dict.put(time, 0);
            }
        }

        return count;
    }
}
