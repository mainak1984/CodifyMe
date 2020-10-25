package edu.codifyme.leetcode.practice.dp;

/**
 * 1510. Stone Game IV
 * HARD: https://leetcode.com/problems/stone-game-iv/
 *
 * Alice and Bob take turns playing a game, with Alice starting first.
 *
 * Initially, there are n stones in a pile.  On each player's turn, that player makes a move consisting of removing any
 * non-zero square number of stones in the pile.
 *
 * Also, if a player cannot make a move, he/she loses the game.
 *
 * Given a positive integer n. Return True if and only if Alice wins the game otherwise return False, assuming both
 * players play optimally.
 *
 * Example 1:
 * Input: n = 1
 * Output: true
 * Explanation: Alice can remove 1 stone winning the game because Bob doesn't have any moves.
 *
 * Example 2:
 * Input: n = 2
 * Output: false
 * Explanation: Alice can only remove 1 stone, after that Bob removes the last one winning the game (2 -> 1 -> 0).
 *
 * Example 3:
 * Input: n = 4
 * Output: true
 * Explanation: n is already a perfect square, Alice can win with one move, removing 4 stones (4 -> 0).
 *
 * Example 4:
 * Input: n = 7
 * Output: false
 * Explanation: Alice can't win the game if Bob plays optimally.
 * If Alice starts removing 4 stones, Bob will remove 1 stone then Alice should remove only 1 stone and finally Bob
 * removes the last one (7 -> 3 -> 2 -> 1 -> 0).
 * If Alice starts removing 1 stone, Bob will remove 4 stones then Alice only can remove 1 stone and finally Bob removes
 * the last one (7 -> 6 -> 2 -> 1 -> 0).
 *
 * Example 5:
 * Input: n = 17
 * Output: false
 * Explanation: Alice can't win the game if Bob plays optimally.
 *
 * Constraints:
 * 1 <= n <= 10^5
 *
 * Approach: DFS with Memoization
 * According to Zermelo's_theorem, given n stones, either Alice has a must-win strategy, or Bob has one. Therefore, for
 * Alice, the current state is either "must-win" or "must-lose". But how to determine which one it is?
 *
 * For convenience, in the following context, "the current player" refers to the player now removing the stones, and
 * "state i" refers to when there is i stones remaining.
 *
 * Now the problem asks whether the current player will win in state n.
 *
 * If we can go to a known state where Bob must lose, then we know Alice must win in the current state. All Alice has to
 * do is to move the corresponding number of stones to go to that state. Therefore we need to find out which state Bob
 * must lose.
 *
 * Note that after going to the next state, Bob becomes the player removing the stones, which is the position of Alice
 * in the current state. Therefore, to find out whether Bob will lose in the next state, we just need to check whether
 * our function gives False for remaining stones.
 *
 * Algorithm:
 * Let function dfs(remain) represents whether the current player must win with remain stones remaining.
 *
 * To find out the result of dfs(n), we need to iterate k from 0 to check whether there exits dfs(remain - k*k)==False.
 * To prevent redundant calculate, use a map to store the result of dfs function.
 *
 * Don't forget the base case dfs(0)==False and dfs(1)==True.
 *
 * Approach: DP
 * Intuition:
 * DFS with memorization is very similar to dp. We can also use dp to solve this problem.
 * We can just use a single dp[i] array to store whether the player now removing stones wins with i stones remaining.
 *
 * Algorithm:
 * Let dp[i] represents the result of the game with i stones. dp[i]==True means the current player must win, and
 * dp[i]==False means the current player must lose, when both players play optimally.
 * The next step is to find out how to calculate dp[i].
 * We can iterate all possible movements, and check if we can move to a False state. If we can, then we found a must-win
 * strategy, otherwise, we must lose since the opponent has a must-win strategy in this case.
 * More clearly, we can iterate k from 0 and check if there exists dp[i - k*k]==False. Of course, i - k*k >= 0.
 * Finally, we only need to return dp[n].
 */
public class StoneGameIV {
    // Approach 1: Memoization
//    public boolean winnerSquareGame(int n) {
//        HashMap<Integer, Boolean> cache = new HashMap<>();
//        cache.put(0, false);
//        return winner(cache, n);
//    }
//
//    boolean winner(HashMap<Integer, Boolean> cache, int remaining) {
//        if (cache.containsKey(remaining)) {
//            return cache.get(remaining);
//        }
//
//        int sqrt = (int)Math.sqrt(remaining);
//
//        for (int i = 1; i <= sqrt; i++) {
//            if (!winner(cache, remaining - i*i)) {
//                cache.put(remaining, true);
//                return true;
//            }
//        }
//
//        cache.put(remaining, false);
//        return false;
//    }

    // Approach 2: DP
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j*j <= i; j++) {
                if (!dp[i - j*j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}
