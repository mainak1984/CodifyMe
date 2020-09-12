package edu.codifyme.leetcode.practice.recursion;

import java.util.LinkedList;
import java.util.List;

/**
 * 216. Combination Sum III
 * https://leetcode.com/problems/combination-sum-iii/
 *
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be
 * used and each combination should be a unique set of numbers.
 *
 * Note:
 * All numbers will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Example 2:
 *
 * Input: k = 3, n = 9
 * Output: [[1,2,6], [1,3,5], [2,3,4]]
 *
 * Approach:
 * Backtrack for the length of K and generate new combinations. Check sum once resultset reaches K length
 */
public class CombinationSumIII {
    int k;
    int n;
    List<List<Integer>> result;

    public List<List<Integer>> combinationSum3(int k, int n) {
        this.k = k;
        this.n = n;
        result = new LinkedList<>();

        getCombination(1, 1, new int[k], 0);

        return result;
    }

    void getCombination(int startingIndex, int depth, int[] resCandidate, int resItemCount) {
        if (resItemCount == k) {
            if( checkIfMatches(resCandidate) ) {
                List<Integer> res = new LinkedList<>();
                for (int elem: resCandidate) {
                    res.add(elem);
                }
                result.add(res);
            }
            return;
        }

        int lim = n > 9? 9: n;

        for (int i = startingIndex; i <= lim-k+depth; i++) {
            resCandidate[resItemCount] = i;
            getCombination(i+1, depth+1, resCandidate, resItemCount+1);
        }
    }

    boolean checkIfMatches(int[] resCandidate) {
        int sum = 0;
        for (int item: resCandidate) {
            sum += item;
        }

        return sum == n;
    }
}
