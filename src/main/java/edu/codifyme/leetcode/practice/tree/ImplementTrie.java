package edu.codifyme.leetcode.practice.tree;

/**
 * 208. Implement Trie (Prefix Tree)
 * MEDIUM: https://leetcode.com/problems/implement-trie-prefix-tree/
 *
 * Implement a trie with insert, search, and startsWith methods.
 *
 * Example:
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // returns true
 * trie.search("app");     // returns false
 * trie.startsWith("app"); // returns true
 * trie.insert("app");
 * trie.search("app");     // returns true
 * Note:
 *
 * You may assume that all inputs are consist of lowercase letters a-z.
 * All inputs are guaranteed to be non-empty strings.
 *
 */
public class ImplementTrie {
    TrieNode head = null;

    /** Initialize your data structure here. */
    public ImplementTrie() {
        head = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = head;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if ( null == node.index[ch - 'a'] ) {
                node.index[ch - 'a'] = new TrieNode();
            }
            node = node.index[ch - 'a'];
        }
        node.isPresent = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = head;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if( node.index[ch - 'a'] != null) {
                node = node.index[ch - 'a'];
            } else {
                return false;
            }
        }

        return node.isPresent;

    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = head;

        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if( node.index[ch - 'a'] != null) {
                node = node.index[ch - 'a'];
            } else {
                return false;
            }
        }

        return true;
    }

    class TrieNode {
        TrieNode[] index = new TrieNode[26];
        boolean isPresent = false;
    }
}
