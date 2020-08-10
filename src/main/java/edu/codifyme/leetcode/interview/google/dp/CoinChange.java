package edu.codifyme.leetcode.interview.google.dp;

import java.util.Arrays;

/**
 * 322. Coin Change
 * MEDIUM: https://leetcode.com/problems/coin-change
 *
 * You are given coins of different denominations and a total amount of money amount. Write a function to compute the
 * fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any
 * combination of the coins, return -1.
 *
 * Example 1:
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 *
 * Example 2:
 * Input: coins = [2], amount = 3
 * Output: -1
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 *
 * Algorithm: Bottom UP DP
 * For the iterative solution, we think in bottom-up manner. Before calculating F(i)F(i), we have to compute all minimum
 * counts for amounts up to ii. On each iteration ii of the algorithm F(i)F(i) is computed as min_{j=0 .. n-1}F(i -c_j)+1
 *
 * Algorithm: Top Down DP
 * The idea of the algorithm is to build the solution of the problem from top to bottom. It applies the idea described
 * above. It use backtracking and cut the partial solutions in the recursive tree, which doesn't lead to a viable
 * solution. Тhis happens when we try to make a change of a coin with a value greater than the amount SS. To improve
 * time complexity we should store the solutions of the already calculated subproblems in a table.
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
         int[] minCoins = new int[amount+1];
         Arrays.fill(minCoins, Integer.MAX_VALUE);
         minCoins[0] = 0;

         for (int loop = 1; loop <= amount; loop++) {
             int coinCount = Integer.MAX_VALUE;
             for (int coin: coins) {
                 if (loop >= coin) {
                     if ( minCoins[loop - coin] != Integer.MAX_VALUE ) {
                         coinCount = minCoins[loop - coin]+1;
                     }
                 }
                 minCoins[loop] = Math.min(minCoins[loop], coinCount);
             }
         }

         return (minCoins[amount] == Integer.MAX_VALUE)? -1: minCoins[amount];
    }

    // Approach 2: Top down DP
//    public int coinChange(int[] coins, int amount) {
//        if (amount < 1) return 0;
//        return coinChange(coins, amount, new int[amount]);
//    }
//
//    private int coinChange(int[] coins, int rem, int[] count) {
//        if (rem < 0) return -1;
//        if (rem == 0) return 0;
//        if (count[rem - 1] != 0) return count[rem - 1];
//        int min = Integer.MAX_VALUE;
//        for (int coin : coins) {
//            int res = coinChange(coins, rem - coin, count);
//            if (res >= 0 && res < min)
//                min = 1 + res;
//        }
//        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
//        return count[rem - 1];
//    }
}
