package edu.codifyme.leetcode.practice.dp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 139. Word Break
 * MEDIUM: https://leetcode.com/problems/word-break/
 *
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be
 * segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note:
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 *
 * Example 1:
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 *
 * Example 2:
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 *              Note that you are allowed to reuse a dictionary word.
 *
 * Example 3:
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 *
 * Approach:
 * The naive approach to solve this problem is to use recursion and backtracking. For finding the solution, we check
 * every possible prefix of that string in the dictionary of words, if it is found in the dictionary, then the recursive
 * function is called for the remaining portion of that string. And, if in some function call it is found that the
 * complete string is in dictionary, then it will return true.
 * In the previous approach we can see that many subproblems were redundant, i.e we were calling the recursive function
 * multiple times for a particular string. To avoid this we can use memoization method, where an array memo is used
 * to store the result of the subproblems. Now, when the function is called again for a particular string, value will be
 * fetched and returned using the memo array, if its value has been already evaluated.
 *
 * With memoization many redundant subproblems are avoided and recursion tree is pruned and thus it reduces the time
 * complexity by a large factor.
 */
public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        return word_Break(s, new HashSet(wordDict), 0, new Boolean[s.length()]);
    }

    public boolean word_Break(String s, Set<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && word_Break(s, wordDict, end, memo)) {
                return memo[start] = true;
            }
        }
        return memo[start] = false;
    }

//     public boolean wordBreak(String s, List<String> wordDict) {
//         for (String word: wordDict) {
//             buildTrie(word);
//         }

//         return find (s.toCharArray(), 0, new Boolean[s.length()]);
//     }

//     boolean find(char[] arr, int startingPos, Boolean[] visited) {
//         if (startingPos == arr.length) {
//             return true;
//         }
//         if (visited[startingPos] != null) {
//             return visited[startingPos];
//         }
//         TrieNode node = root;
//         for (int i = startingPos; i < arr.length; i++) {
//             char ch = arr[i];
//             if (node.child[ch - 'a'] == null) {
//                 break;
//             } else {
//                 node = node.child[ch - 'a'];
//                 if ( node.isWord && find (arr, i+1, visited)) {
//                     return true;
//                 }
//             }
//         }

//         visited[startingPos] = false;
//         return false;
//     }

//     TrieNode root = new TrieNode();
//     void buildTrie(String word) {
//         TrieNode node = root;
//         for (char ch: word.toCharArray()) {
//             if (node.child[ch - 'a'] == null) {
//                 node.child[ch - 'a'] = new TrieNode();
//             }
//             node = node.child[ch - 'a'];
//         }
//         node.isWord = true;
//     }

//     class TrieNode {
//         TrieNode[] child = new TrieNode[26];
//         boolean isWord = false;
//     }
}
