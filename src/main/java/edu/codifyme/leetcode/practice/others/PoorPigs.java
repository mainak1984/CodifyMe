package edu.codifyme.leetcode.practice.others;

/**
 * 458. Poor Pigs
 * HARD: https://leetcode.com/problems/poor-pigs/
 *
 * There are 1000 buckets, one and only one of them is poisonous, while the rest are filled with water. They all look
 * identical. If a pig drinks the poison it will die within 15 minutes. What is the minimum amount of pigs you need to
 * figure out which bucket is poisonous within one hour?
 *
 * Answer this question, and write an algorithm for the general case.
 *
 * General case:
 * If there are n buckets and a pig drinking poison will die within m minutes, how many pigs (x) you need to figure out
 * the poisonous bucket within p minutes? There is exactly one bucket with poison.
 *
 * Note:
 * A pig can be allowed to drink simultaneously on as many buckets as one would like, and the feeding takes no time.
 * After a pig has instantly finished drinking buckets, there has to be a cool down time of m minutes. During this time,
 * only observation is allowed and no feedings at all.
 * Any given bucket can be sampled an infinite number of times (by an unlimited number of pigs).
 *
 * Approach:
 * 1. if a pig has only 1 round, it can represent 2 states - alive or dead, so, cover 2 buckets atleast
 * 2. If a pig has 2 round, it can represent 3 states - dead in 1st, dead in 2nd or alive in 2nd
 * similarly, any pig can represent (round + 1) no. of states. So, combining multiple pig is an encoding problem -
 * more like binary representation of number, but it may be n-ary depending on rounds.
 * So, total no. of states = log(buckets) / log(rounds+1). Depending on states double value, we have to ceiling it
 *
 * https://leetcode.com/problems/poor-pigs/discuss/94273/Solution-with-detailed-explanation
 */
public class PoorPigs {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int states = minutesToTest / minutesToDie + 1;
        return (int) Math.ceil(Math.log(buckets) / Math.log(states));
    }
}
