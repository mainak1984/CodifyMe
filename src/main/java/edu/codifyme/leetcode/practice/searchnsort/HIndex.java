package edu.codifyme.leetcode.practice.searchnsort;

import java.util.Arrays;

/**
 * 274. H-Index
 * Medium: https://leetcode.com/problems/h-index/
 *
 * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute
 * the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least
 * h citations each, and the other N − h papers have no more than h citations each."
 *
 * Example:
 * Input: citations = [3,0,6,1,5]
 * Output: 3
 * Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had
 *              received 3, 0, 6, 1, 5 citations respectively.
 *              Since the researcher has 3 papers with at least 3 citations each and the remaining
 *              two with no more than 3 citations each, her h-index is 3.
 * Note: If there are several possible values for h, the maximum one is taken as the h-index.
 *
 * Approach: 1
 * Sort the citations and traverse from lowest to find the cut off. Time complexity O(nlogn)
 *
 * Approach 2:
 * Sort using count sort to make the complexity O(n).
 * However, in our problem, the keys are the citations of each paper which can be much larger than the number of papers
 * n. It seems that we cannot use counting sort. The trick here is the following observation:
 * Any citation larger than nn can be replaced by nn and the h-index will not change after the replacement
 * The reason is that h-index is upper bounded by total number of papers nn, i.e. h ≤ n
 *
 * But we can do even better. The idea is that we don't even need to get sorted citations. We can find the hh-index by
 * using the paper counts directly.
 *
 * To explain this, let's look at the following example: citations=[1,3,2,3,100]
 *
 * The counting results are:
 * kk	    0	1	2	3	4	5
 * count	0	1	1	2	0	1
 * sk  	    5	5	4	3	1	1
 *
 * The value sk is defined as "the sum of all counts with citation ≥k" or "the number of papers having, at
 * least, k citations". By definition of the h-index, the largest k with k ≤ s is our answer.
 *
 * After replacing 100100 with n=5, we have citations=[1,3,2,3,5]. Now, we count the number of papers for each citation
 * number 0 to 5. The counts are [0,1,1,2,0,1]. The first k from right to left (5 down to 0) that have k ≤ s is the
 * h-index 3.
 *
 * Since we can calculate sk on the fly when traverse the count array, we only need one pass through the count array
 * which only costs O(n)O(n) time.
 */
public class HIndex {
    // aPPROACH 1: Time complexity O9nlogn)
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int N = citations.length;

        for (int i = 0; i < N; i++) {
            if ( citations[i] >= (N - i)) {
                return N - i;
            }
        }

        return 0;
    }

    // Approach 2: Time complexity O(n)
//    public int hIndex(int[] citations) {
//        int n = citations.length;
//        int[] papers = new int[n + 1];
//        // counting papers for each citation number
//        for (int c: citations)
//            papers[Math.min(n, c)]++;
//        // finding the h-index
//        int k = n;
//        for (int s = papers[n]; k > s; s += papers[k])
//            k--;
//        return k;
//    }
}
