package edu.codifyme.hackerrank.interviewpreparation.hashmap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MEDIUM:
 * https://www.hackerrank.com/challenges/count-triplets-1/problem
 *
 * You are given an array and you need to find number of tripets of indices (i, j, k) such that the elements at those
 * indices are in geometric progression for a given common ratio r and i < j < k.
 * For example, arr[1, 4, 16, 64]. If r=4, we have [1, 4, 16] and [4, 16, 64] at indices (0,1,2) and (1,2,3).
 *
 * Function Description:
 * Complete the countTriplets function in the editor below. It should return the number of triplets forming a
 * geometric progression for a given r as an integer.
 * countTriplets has the following parameter(s):
 *
 * arr: an array of integers
 * r: an integer, the common ratio
 * Input Format
 *
 * The first line contains two space-separated integers n and r, the size of arr and the common ratio.
 * The next line contains n space-seperated integers arr[i].
 *
 * Constraints:
 * 1 <= n <= 10^5
 * 1 <= r <= 10^9
 * 1 <= arr[i] <= 10^9
 *
 * Output Format:
 * Return the count of triplets that form a geometric progression.
 *
 * Sample Input 0
 * 4 2
 * 1 2 2 4
 * Sample Output 0
 * 2
 * Explanation 0
 * There are  triplets in satisfying our criteria, whose indices are (0,1,3) and (0,2,3)
 *
 * Sample Input 1
 * 6 3
 * 1 3 9 9 27 81
 * Sample Output 1
 * 6
 * Explanation 1
 * The triplets satisfying are index (0,1,2), (0,1,3), (1,2,4), (1,3,4), (2,4,5) and (3,4,5).
 *
 * Sample Input 2
 * 5 5
 * 1 5 5 25 125
 * Sample Output 2
 * 4
 * Explanation 2
 * The triplets satisfying are index (0,1,3), (0,2,3), (1,3,4), (2,3,4).
 *
 * Input:
 * 5 2
 * 1 2 1 2 4
 * Output:
 * 3
 *
 * An elegent solution will be to represent our triplets by (aj/r, aj, aj*r), where r is the common ratio and 0 <= j
 * <= n. So, we need to find ai = aj/r and ak = ai*r where i < j < k.
 * We use two maps. Let's call them left and right. Initially, in the right map we store the frequency of all the
 * elements. Now, as we traverse the array elements from left side, we first decrement it's count from right map by 1,
 * then we check the count of aj*r in the right map (say, c1)and the count of check aj/r in the left map (say, c2). We,
 * increment our answer by c1*c2. At last we increment the count of aj in the left map by .
 * The intuition behind this is the left map will always hold elements which have indices less than the current index
 * and the right map will hold elements which have indices greater than the current index.
 */
public class CountTriplets {
    // Complete the countTriplets function below.
    private static long countTriplets(List<Long> arr, long r) {
        Map<Long, Long> potential = new HashMap<>();
        Map<Long, Long> counter = new HashMap<>();
        long count = 0;
        for (int i = 0; i < arr.size(); i++) {
            long a = arr.get(i);
            long key = a / r;

            if (counter.containsKey(key) && a % r == 0) {
                count += counter.get(key);
            }

            if (potential.containsKey(key) && a % r == 0) {
                long c = potential.get(key);
                counter.put(a, counter.getOrDefault(a, 0L) + c);
            }

            potential.put(a, potential.getOrDefault(a, 0L) + 1); // Every number can be the start of a triplet.
        }
        return count;
    }
}
