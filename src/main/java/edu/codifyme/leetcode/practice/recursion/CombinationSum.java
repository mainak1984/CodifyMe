package edu.codifyme.leetcode.practice.recursion;

import java.util.LinkedList;
import java.util.List;

/**
 * 39. Combination Sum
 * MEDIUM: https://leetcode.com/problems/combination-sum/
 *
 * Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations
 * of candidates where the chosen numbers sum to target. You may return the combinations in any order.
 *
 * The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the
 * frequency of at least one of the chosen numbers is different.
 *
 * Example 1:
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation:
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 *
 * Example 2:
 * Input: candidates = [2,3,5], target = 8
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 *
 * Example 3:
 * Input: candidates = [2], target = 1
 * Output: []
 *
 * Example 4:
 * Input: candidates = [1], target = 1
 * Output: [[1]]
 *
 * Example 5:
 * Input: candidates = [1], target = 2
 * Output: [[1,1]]
 *
 * Constraints:
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * All elements of candidates are distinct.
 * 1 <= target <= 500
 *
 * Approach:
 * As one can see, the above backtracking algorithm is unfolded as a DFS (Depth-First Search) tree traversal, which is
 * often implemented with recursion.
 *
 * Here we define a recursive function of backtrack(remain, comb, start) (in Python), which populates the combinations,
 * starting from the current combination (comb), the remaining sum to fulfill (remain) and the current cursor (start) to
 * the list of candidates. Note that, the signature of the recursive function is slightly different in Java. But the
 * idea remains the same.
 *
 *  - For the first base case of the recursive function, if the remain==0, i.e. we fulfill the desired target sum,
 *  therefore we can add the current combination to the final list.
 *  - As another base case, if remain < 0, i.e. we exceed the target value, we will cease the exploration here.
 *  - Other than the above two base cases, we would then continue to explore the sublist of candidates as [start ... n]
 *  . For each of the candidate, we invoke the recursive function itself with updated parameters.
 *      - Specifically, we add the current candidate into the combination.
 *      - With the added candidate, we now have less sum to fulfill, i.e. remain - candidate.
 *      - For the next exploration, still we start from the current cursor start.
 *      - At the end of each exploration, we backtrack by popping out the candidate out of the combination.
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new LinkedList<>();
        backtrack(candidates, target, 0, new LinkedList<Integer>(), result);
        return result;
    }

    void backtrack(int[] candidates, int remaining, int start, LinkedList<Integer> currList, List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new LinkedList<>(currList));
            return;
        } else if (remaining < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            currList.add(candidates[i]);
            backtrack(candidates, remaining - candidates[i], i, currList, result);
            currList.removeLast();
        }
    }
}
