package edu.codifyme.leetcode.interview.google.dp;

/**
 * 121. Best Time to Buy and Sell Stock
 * EASY: https://leetcode.com/problems/best-time-to-buy-and-sell-stock
 * Editor's choice: Frequently asked in Facebook onsite assessment
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit.
 * Note that you cannot sell a stock before you buy one.
 *
 * Example 1:
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 *              Not 7-1 = 6, as selling price needs to be larger than buying price.
 * Example 2:
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 *
 * Approach:
 * We need to find out the maximum difference (which will be the maximum profit) between two numbers in the given array.
 * Also, the second number (selling price) must be larger than the first one (buying price).
 * In formal terms, we need to find max(prices[j] - prices[i]), for every ii and jj such that j > ij>i.
 */
public class BestTimeToBuyAndSellStock {
    public int maxProfit(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }
}
