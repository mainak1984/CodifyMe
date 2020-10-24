package edu.codifyme.leetcode.practice.greedy;

import java.util.Arrays;

/**
 * 948. Bag of Tokens
 * MEDIUM: https://leetcode.com/problems/bag-of-tokens/
 *
 * You have an initial power of P, an initial score of 0, and a bag of tokens where tokens[i] is the value of the ith
 * token (0-indexed).
 *
 * Your goal is to maximize your total score by potentially playing each token in one of two ways:
 *
 * If your current power is at least tokens[i], you may play the ith token face up, losing tokens[i] power and gaining
 * 1 score.
 * If your current score is at least 1, you may play the ith token face down, gaining tokens[i] power and losing 1 score.
 * Each token may be played at most once and in any order. You do not have to play all the tokens.
 *
 * Return the largest possible score you can achieve after playing any number of tokens.
 *
 * Example 1:
 * Input: tokens = [100], P = 50
 * Output: 0
 * Explanation: Playing the only token in the bag is impossible because you either have too little power or too little
 * score.
 *
 * Example 2:
 * Input: tokens = [100,200], P = 150
 * Output: 1
 * Explanation: Play the 0th token (100) face up, your power becomes 50 and score becomes 1.
 * There is no need to play the 1st token since you cannot play it face up to add to your score.
 *
 * Example 3:
 * Input: tokens = [100,200,300,400], P = 200
 * Output: 2
 * Explanation: Play the tokens in this order to get a score of 2:
 * 1. Play the 0th token (100) face up, your power becomes 100 and score becomes 1.
 * 2. Play the 3rd token (400) face down, your power becomes 500 and score becomes 0.
 * 3. Play the 1st token (200) face up, your power becomes 300 and score becomes 1.
 * 4. Play the 2nd token (300) face up, your power becomes 0 and score becomes 2.
 *
 * Example 4:
 * Input: tokens = [91,4,75,70,66,71,91,64,37,54], P = 20
 * Output: 2
 *
 * Constraints:
 * 0 <= tokens.length <= 1000
 * 0 <= tokens[i], P < 104
 *
 * Approach:
 * We don't need to play anything until absolutely necessary. Let's play tokens face up until we can't, then play a
 * token face down and continue.
 * We should always play tokens face up until exhaustion, then play one token face down and continue.
 * Our final answer could be any of the intermediate answers we got after playing tokens face up (but before playing
 * them face down.)
 */
public class BagOfTokens {
    public int bagOfTokensScore(int[] tokens, int P) {
        Arrays.sort(tokens);

        int score = 0;
        int max = 0;
        int left = 0;
        int right = tokens.length-1;

        while (left <= right) {
            if (P >= tokens[left]) {
                P -= tokens[left++];
                score += 1;
                max = max < score? score: max;
            } else if (score > 0) {
                P += tokens[right--];
                score -= 1;
            } else {
                break;
            }
        }

        return max;
    }
}
