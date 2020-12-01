package edu.codifyme.leetcode.practice.array;

/**
 * 243. Shortest Word Distance
 * EASY: https://leetcode.com/problems/shortest-word-distance/
 *
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
 *
 * Example:
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *
 * Input: word1 = “coding”, word2 = “practice”
 * Output: 3
 *
 * Input: word1 = "makes", word2 = "coding"
 * Output: 1
 *
 * Note:
 * You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 *
 * Approach:
 * We can greatly improve on the brute-force approach by keeping two indices i1 and i2 where we store the most recent
 * locations of word1 and word2. Each time we find a new occurrence of one of the words, we do not need to search the
 * entire array for the other word, since we already have the index of its most recent occurrence.
 */
public class ShortestWordDistance {
    public int shortestDistance(String[] words, String word1, String word2) {
        int first = -1, second = -1;
        int minDiff = Integer.MAX_VALUE;

        for (int i = 0; i < words.length; i++) {
            if (word1.equals(words[i])) {
                first = i;

                if (second != -1) {
                    minDiff = Math.min(minDiff, first - second);
                }
            } else if (word2.equals(words[i])) {
                second = i;

                if (first != -1) {
                    minDiff = Math.min(minDiff, second - first);
                }
            }
        }

        return minDiff;
    }
}
