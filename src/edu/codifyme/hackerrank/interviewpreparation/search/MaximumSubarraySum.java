package edu.codifyme.hackerrank.interviewpreparation.search;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * HARD:
 * https://www.hackerrank.com/challenges/maximum-subarray-sum/problem
 *
 * We define the following:
 * * A subarray of array a of length n is a contiguous segment from a[i] through a[j] where 0 <= i <= j < n.
 * * The sum of an array is the sum of its elements.
 * Given an n element array of integers, a, and an integer, m, determine the maximum value of the sum of any of its
 * subarrays modulo m. For example, Assume a=[1, 2, 3] and m=2. The following table lists all subarrays and their
 * moduli:
 *
 * 		       sum	%2
 * [1]		    1	1
 * [2]		    2	0
 * [3]		    3	1
 * [1,2]		3	1
 * [2,3]		5	1
 * [1,2,3]		6	0
 * The maximum modulus is 1.
 *
 * Function Description:
 * Complete the maximumSum function in the editor below. It should return a long integer that represents the maximum
 * value of subarray sum % m.
 *
 * maximumSum has the following parameter(s):
 * a: an array of long integers, the array to analyze
 * m: a long integer, the modulo divisor
 *
 * Approach:
 * To solve it in a better way, the problem requires some knowledge of modular arithmetic.
 * Here are some basic formula for this problem:
 * (a+b)%M=(a%M+b%M)%M  --- 1
 * (a−b)%M=(a%M−b%M)%M  --- 2
 *
 * This link: Modular arithmetic, explains 1, 2 in a straightforward way.
 * Now, let's solve the problem with the simple math formula!
 *
 * Usually, a great many problems related to "subarray computation" could be solved with prefix array, which saves time
 * for repeating computation.
 * Define:
 * prefix[n]=(a[0]+a[1]+...+a[n])%M
 *
 * To construct a prefix table, we could use the following code. (It is a generalization of the 1, 2 formula I mentioned
 * before)
 *
 * int curr = 0;
 * for(int i = 0; i < n; i ++) {
 *   curr = (arr[i] % M + curr) % M;
 *   prefix[i] = curr;
 * }
 *
 * The we have to find a subarray. Any subarray can be expressed in the following way:
 * sumModular[i,j]=(prefix[j]−prefix[i−1]+M)%M  --3
 *
 * which is very simple and efficient.
 * The correctness of 3 require some math based on 1 and 2. And 3 is the key to the efficient solution!
 *
 * Let's write the solution of the BF algorithm first:
 *
 * int ret = 0;
 * for(int i = 0; i < n; i ++) {
 *   for(int j = i-1; j >= 0; j --) {
 *     ret = max(ret, (prefix[i] - prefix[j] + M) % M)
 *   }
 *   ret = max(ret, prefix[i]); // Don't forget sum from beginning.
 * }
 *
 * Okie! This code is simple, however, it did some non-sense job! Why?
 * if prefix[j] is smaller than prefix[i], in the previous code, there is no need to calculate, because for
 * prefix[j] < prefix[i], we have:
 * (prefix[i]−prefix[j]+M)%M=prefix[i]−prefix[j]≤prefix[i]
 *
 * So we just need to find those j, which prefix[j] > prefix[i]. Ok, let's just linear scan all j before i and find the
 * ones that is smaller than prefix[i]. But that makes no difference in terms of time complexity.
 *
 * The only way I could think of is to use a data structure to keep the array sorted. Moreover, the data structure has
 * to support the insert operation. Red black tree can be a great DS in this situation. If you are using C++, you can
 * use set. Java user could use TreeSet.
 *
 * So every time you get a prefix[i], all previous elements: prefix[0...i-1] are in the red black tree, sorted. You need
 * to find the upper_bound of the prefix[i], which is the first element that is larger than the prefix[i]. Why we just
 * need the first larger element? The reason is very simple!
 *
 * The key-logic is, if the larger index(say j) has smaller prefix_sum than any smaller index (say i), then that
 * sub-array (from i to j) can be a contender for our solution provided it's sum%m is maximum.
 */
public class MaximumSubarraySum {
    private static long getMaxSum(int n, long m, long[] arr) {
        long maxSum=0;

        TreeSet<Long> prefix = new TreeSet<Long>();
        long currentSum=0;
        for(int i=0;i<n;i++){
            currentSum=(currentSum+arr[i]%m)%m;
            SortedSet<Long> set = prefix.tailSet(currentSum+1);
            Iterator<Long> itr = set.iterator();
            if(itr.hasNext()){

                maxSum= Math.max(maxSum, (currentSum-itr.next()+m)%m);
            }

            maxSum=Math.max(maxSum, currentSum);
            prefix.add(currentSum);
        }

        return maxSum;
    }
}
