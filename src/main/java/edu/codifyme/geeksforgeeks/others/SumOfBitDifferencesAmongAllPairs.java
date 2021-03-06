package edu.codifyme.geeksforgeeks.others;

/** MEDIUM: Sum of bit differences among all pairs
 * https://www.geeksforgeeks.org/sum-of-bit-differences-among-all-pairs/
 *
 * Given an integer array of n integers, find sum of bit differences in all pairs that can be formed from array elements.
 * Bit difference of a pair (x, y) is count of different bits at same positions in binary representations of x and y.
 * For example, bit difference for 2 and 7 is 2. Binary representation of 2 is 010 and 7 is 111 ( first and last bits
 * differ in two numbers).
 *
 * Examples :
 *
 * Input: arr[] = {1, 2}
 * Output: 4
 * All pairs in array are (1, 1), (1, 2)
 *                        (2, 1), (2, 2)
 * Sum of bit differences = 0 + 2 +
 *                          2 + 0
 *                       = 4
 *
 * Input:  arr[] = {1, 3, 5}
 * Output: 8
 * All pairs in array are (1, 1), (1, 3), (1, 5)
 *                        (3, 1), (3, 3) (3, 5),
 *                        (5, 1), (5, 3), (5, 5)
 * Sum of bit differences =  0 + 1 + 1 +
 *                           1 + 0 + 2 +
 *                           1 + 2 + 0
 *                        = 8
 * Source: Google Interview Question
 *
 * Solution:
 * An Efficient Solution can solve this problem in O(n) time using the fact that all numbers are represented using 32
 * bits (or some fixed number of bits). The idea is to count differences at individual bit positions. We traverse from
 * 0 to 31 and count numbers with i’th bit set. Let this count be ‘count’. There would be “n-count” numbers with i’th
 * bit not set. So count of differences at i’th bit would be “count * (n-count) * 2”.
 */
public class SumOfBitDifferencesAmongAllPairs {
    static int sumBitDifferences(int arr[], int n)
    {
        int ans = 0; // Initialize result

        // traverse over all bits
        for (int i = 0; i < 32; i++) {

            // count number of elements
            // with i'th bit set
            int count = 0;

            for (int j = 0; j < n; j++)
                if ((arr[j] & (1 << i)) == 0)
                    count++;

            // Add "count * (n - count) * 2"
            // to the answer
            ans += (count * (n - count) * 2);
        }

        return ans;
    }
}
