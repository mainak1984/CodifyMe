package edu.codifyme.leetcode.interview.googledeck.recursion;

import java.util.LinkedList;
import java.util.List;

/**
 * MEDIUM:
 * https://leetcode.com/explore/interview/card/google/62/recursion-4/462/
 *
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
 * horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 *
 * Example:
 * Input:
 * board = [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 * words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 *
 * Note:
 * All inputs are consist of lowercase letters a-z.
 * The values of words are distinct.
 */
public class WordSearchII {
    public static final Character VISITED = '#';

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new LinkedList<>();
        TrieNode trie = buildTrie(words);

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                dfs(board, row, col, trie, result);
            }
        }
        return result;
    }

    void dfs(char[][] board, int row, int col, TrieNode trie, List<String> result) {
        // Validity
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length ) {
            return;
        }
        char ch = board[row][col];

        // Pruning
        if ( ch == VISITED || trie.next[ch - 'a'] == null ) {
            return;
        }

        trie = trie.next[ch - 'a'];

        if ( trie.val != null ) {
            result.add(trie.val);
            trie.val = null;
        }

        board[row][col] = VISITED;

        // branching
        dfs(board, row, col+1, trie, result);
        dfs(board, row+1, col, trie, result);
        dfs(board, row, col-1, trie, result);
        dfs(board, row-1, col, trie, result);

        board[row][col] = ch;

    }

    TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();

        for (String word: words) {
            TrieNode r = root;

            for (Character ch: word.toCharArray()) {
                if ( r.next[ch - 'a'] == null ) {
                    r.next[ch - 'a'] = new TrieNode();
                }
                r = r.next[ch - 'a'];
            }
            r.val = word;
        }
        return root;
    }

    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String val;
    }
}
