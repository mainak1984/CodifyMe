package edu.codifyme.leetcode.practice.dp;

/**
 * 123. Best Time to Buy and Sell Stock III
 * HARD: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * Note: You may not engage in multiple transactions at the same time (you must sell the stock before you buy again).
 *
 * Example 1:
 * Input: [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 *              Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 *
 * Example 2:
 * Input: [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 *              Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 *              engaging multiple transactions at the same time. You must sell before buying again.
 *
 * Example 3:
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 *
 * Approach 1: Bidirectional Dynamic Programming
 * We could interpret this constraint as that there would be no overlapping in the sequence of transactions.
 *
 * In other words, the two transactions that we should make would situate in two different subsequences of the stock
 * prices, without any overlapping, which we illustrate in the above graph.
 *
 * The total profits would be the sum of profits from each subsequence. If we enumerate all possible divisions (or we
 * could consider them as combinations of subsequences), we could find the maximum total profits among them, which is
 * also the desired result of the problem.
 *
 * Approach 2: One-pass Simulation
 * Algorithm
 * Overall, we run an iteration over the sequence of prices.
 *
 * Over the iteration, we calculate 4 variables which correspond to the costs or the profits of each action respectively,
 * as follows:
 *
 * t1_cost: the minimal cost of buying the stock in transaction #1. The minimal cost to acquire a stock would be the
 * minimal price value that we have seen so far at each step.
 *
 * t1_profit: the maximal profit of selling the stock in transaction #1. Actually, at the end of the iteration, this
 * value would be the answer for the first problem in the series, i.e. Best Time to Buy and Sell Stock.
 *
 * t2_cost: the minimal cost of buying the stock in transaction #2, while taking into account the profit gained from the
 * previous transaction #1. One can consider this as the cost of reinvestment. Similar with t1_cost, we try to find the
 * lowest price so far, which in addition would be partially compensated by the profits gained from the first transaction.
 *
 * t2_profit: the maximal profit of selling the stock in transaction #2. With the help of t2_cost as we prepared so far,
 * we would find out the maximal profits with at most two transactions at each step.
 */
public class BestTimeToBuyAndSellStockIII {
    // Approach 1:
    //     public int maxProfit(int[] prices) {
//         int length = prices.length;
//         if (length <= 1) return 0;

//         int leftMin = prices[0];
//         int rightMax = prices[length - 1];

//         int[] leftProfits = new int[length];
//         // pad the right DP array with an additional zero for convenience.
//         int[] rightProfits = new int[length + 1];

//         // construct the bidirectional DP array
//         for (int l = 1; l < length; ++l) {
//             leftProfits[l] = Math.max(leftProfits[l - 1], prices[l] - leftMin);
//             leftMin = Math.min(leftMin, prices[l]);

//             int r = length - 1 - l;
//             rightProfits[r] = Math.max(rightProfits[r + 1], rightMax - prices[r]);
//             rightMax = Math.max(rightMax, prices[r]);
//         }

//         int maxProfit = 0;
//         for (int i = 0; i < length; ++i) {
//             maxProfit = Math.max(maxProfit, leftProfits[i] + rightProfits[i + 1]);
//         }
//         return maxProfit;
//     }

    // Approach: 2
    public int maxProfit(int[] prices) {
        int oneBuy = Integer.MIN_VALUE;
        int oneBuyOneSell = 0;
        int twoBuy = Integer.MIN_VALUE;
        int twoBuyTwoSell = 0;
        for(int i = 0; i < prices.length; i++){
            oneBuy = Math.max(oneBuy, -prices[i]);//we set prices to negative, so the calculation of profit will be convenient
            oneBuyOneSell = Math.max(oneBuyOneSell, prices[i] + oneBuy);
            twoBuy = Math.max(twoBuy, oneBuyOneSell - prices[i]);//we can buy the second only after first is sold
            twoBuyTwoSell = Math.max(twoBuyTwoSell, twoBuy + prices[i]);
        }

        return Math.max(oneBuyOneSell, twoBuyTwoSell);
    }
}
