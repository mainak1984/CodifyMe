package edu.codifyme.leetcode.practice.searchnsort;

import java.util.HashMap;
import java.util.Map;

/**
 * 532. K-diff Pairs in an Array
 * MEDIUM: https://leetcode.com/problems/k-diff-pairs-in-an-array/
 *
 * Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.
 * A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:
 *
 * 0 <= i, j < nums.length
 * i != j
 * a <= b
 * b - a == k
 *
 * Example 1:
 * Input: nums = [3,1,4,1,5], k = 2
 * Output: 2
 * Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
 * Although we have two 1s in the input, we should only return the number of unique pairs.
 *
 * Example 2:
 * Input: nums = [1,2,3,4,5], k = 1
 * Output: 4
 * Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
 *
 * Example 3:
 * Input: nums = [1,3,1,5,4], k = 0
 * Output: 1
 * Explanation: There is one 0-diff pair in the array, (1, 1).
 *
 * Example 4:
 * Input: nums = [1,2,4,4,3,3,0,9,2,3], k = 3
 * Output: 2
 *
 * Example 5:
 * Input: nums = [-1,-2,-3], k = 1
 * Output: 2
 *
 * Constraints:
 * 1 <= nums.length <= 104
 * -107 <= nums[i] <= 107
 * 0 <= k <= 107
 *
 * Approach: Hashmap
 * Intuition:
 * This method removes the need to sort the nums array. Rather than that, we will be building a frequency hash map. This
 * hash map will have every unique number in nums as keys and the number of times each number shows up in nums as values.
 *
 * For example:
 * nums = [2,4,1,3,5,3,1], k = 3
 * hash_map = {1: 2,
 *             2: 1,
 *             3: 2,
 *             4: 1,
 *             5: 1}
 * Next, we look at a key (let's call x) in the hash map and ask whether:
 * - There is a key in the hash map which is equal to x+k IF k > 0.
 *      - For example, if a number in nums is 1 (x=1) and k is 3, you would need to have 4 to satisfy this condition
 *      (thus, we need to look for 1+3 = 4 in the hash map). Using addition to look for a complement pair has the
 *      advantage of not double-counting the same pair, but in reverse order (i.e. if we have found a pair (1,4), we
 *      won't be counting (4,1)).
 * - There is more than one occurrence of x IF k = 0.
 *      - For example, if we have nums = [1,1,1,1] and k = 0, we have one unique (1,1) pair. In this case, our hash
 *      map will be {1: 4}, and this condition is satisfied since we have more than one occurrence of number 1.
 *
 * If we can satisfy either of the above conditions, we can increment our placeholder result variable.
 * Then we look at the next key in the hash map.
 */
public class KDiffPairsInAnArray {
    // Approach 1: O(n)
    public int findPairs(int[] nums, int k) {
        int result = 0;

        HashMap<Integer,Integer> counter = new HashMap<>();
        for (int n: nums) {
            counter.put(n, counter.getOrDefault(n, 0)+1);
        }

        for (Map.Entry <Integer, Integer> entry: counter.entrySet()) {
            int x = entry.getKey();
            int val = entry.getValue();
            if (k > 0 && counter.containsKey(x + k)) {
                result++;
            } else if (k == 0 && val > 1) {
                result++;
            }
        }
        return result;
    }

    // Approach 2: O(nlogn)
//    public int findPairs(int[] nums, int k) {
//        Arrays.sort(nums);
//        int left=0,right=1;
//        int ans=0;
//
//        while(left<nums.length && right<nums.length)
//        {
//            if(left==right || nums[right] - nums[left] < k)
//                right++;
//
//            else if(nums[right] - nums[left] > k)
//                left++;
//            else
//            {
//                left++;
//                ans++;
//                while(left<nums.length && nums[left] == nums[left-1])
//                {
//                    left++;
//                }
//            }
//
//        }
//        return ans;
//    }
}
