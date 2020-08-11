package edu.codifyme.leetcode.practice.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 211. Add and Search Word - Data structure design
 * MEDIUM: https://leetcode.com/problems/add-and-search-word-data-structure-design/
 *
 * Design a data structure that supports the following two operations:
 *
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it
 * can represent any one letter.
 *
 * Example:
 *
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
 *
 * Approach:
 * Build a Trie. For searching of '.', search in all characters of that Trie node
 */
public class AddAndSearchWord {
    TrieNode root;

    /** Initialize your data structure here. */
    public AddAndSearchWord() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode node = root;

        for (char ch: word.toCharArray()) {
            if (!node.child.containsKey(ch)) {
                node.child.put(ch, new TrieNode());
            }
            node = node.child.get(ch);
        }
        node.word = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(word, root);
    }

    public boolean search(String word, TrieNode node) {

        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);

            if (node.child.containsKey(ch)) {
                node = node.child.get(ch);
            } else {
                if (ch == '.') {
                    for (TrieNode child: node.child.values()) {
                        if (search(word.substring(i + 1), child)) {
                            return true;
                        }
                    }
                }
                return false;
            }
        }

        return node.word;
    }

    class TrieNode {
        Map<Character, TrieNode> child = new HashMap<>();
        boolean word = false;
    }
}
