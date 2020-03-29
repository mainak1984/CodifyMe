package edu.codifyme.leetcode.interview.topinterviewquestions.array;

/**
 * HARD: https://www.hackerrank.com/challenges/crush/problem
 *
 * Starting with a 1-indexed array of zeros and a list of operations, for each operation add a value to each of the
 * array element between two given indices, inclusive. Once all operations have been performed, return the maximum
 * value in your array.
 *
 * For example, the length of your array of zeros . Your list of queries is as follows:
 *
 *     a b k
 *     1 5 3
 *     4 8 7
 *     6 9 1
 * Add the values of  between the indices  and  inclusive:
 *
 * index->	 1 2 3  4  5 6 7 8 9 10
 * 	[0,0,0, 0, 0,0,0,0,0, 0]
 * 	[3,3,3, 3, 3,0,0,0,0, 0]
 * 	[3,3,3,10,10,7,7,7,0, 0]
 * 	[3,3,3,10,10,8,8,8,1, 0]
 * The largest value is  after all operations are performed.
 *
 * Solution:
 * Given a range[a,b] and a value  we need to add  to all the numbers whose indices are in the range from [a,b].
 * We can do an O(1) update by adding k to index a and add -k to index b+1.
 * Doing this kind of update, the ith number in the array will be prefix sum of array from index 1 to i because we are
 * adding k to the value at index a and subtracting k from the value at index b+1 and taking prefix sum will give us the
 * actual value for each index after m operations .
 * We can calculate all prefix sums as well as maximum prefix sum in O(n) time which will execute in time.
 * Further optimization comes from not processing all n entries and process only m entries in order O(mlogm)
 *
 */
public class ArrayManipulation {
    static long arrayManipulation(int n, int[][] queries) {
        long[] derived = new long[n+2];
        long max = Long.MIN_VALUE;

        for (int loopVar = 0; loopVar < queries.length; loopVar++ ) {
            int[] abk = queries[loopVar];

            derived[abk[0]] += abk[2];
            derived[abk[1]+1] -= abk[2];
        }

        // parse thru the entire array to find max
        long currentHeight = 0;
        for (int loopVar = 0; loopVar < n+2; loopVar++ ) {
            currentHeight += derived[loopVar];

            if ( max < currentHeight ) {
                max = currentHeight;
            }
        }

        return max;
    }
}
