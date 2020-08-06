package edu.codifyme.leetcode.mocktest.google.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * HARD: Stream of Characters
 * https://leetcode.com/problems/stream-of-characters/
 *
 * Implement the StreamChecker class as follows:
 *
 * StreamChecker(words): Constructor, init the data structure with the given words.
 * query(letter): returns true if and only if for some k >= 1, the last k characters queried (in order from oldest to
 * newest, including this letter just queried) spell one of the words in the given list.
 *
 * Example:
 *
 * StreamChecker streamChecker = new StreamChecker(["cd","f","kl"]); // init the dictionary.
 * streamChecker.query('a');          // return false
 * streamChecker.query('b');          // return false
 * streamChecker.query('c');          // return false
 * streamChecker.query('d');          // return true, because 'cd' is in the wordlist
 * streamChecker.query('e');          // return false
 * streamChecker.query('f');          // return true, because 'f' is in the wordlist
 * streamChecker.query('g');          // return false
 * streamChecker.query('h');          // return false
 * streamChecker.query('i');          // return false
 * streamChecker.query('j');          // return false
 * streamChecker.query('k');          // return false
 * streamChecker.query('l');          // return true, because 'kl' is in the wordlist
 *
 * Note:
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 2000
 * Words will only consist of lowercase English letters.
 * Queries will only consist of lowercase English letters.
 * The number of queries is at most 40000.
 *
 * Approach 1: Trie
 * Trie is widely used in real life: autocomplete search, spell checker, T9 predictive text, IP routing (longest prefix
 * matching), some GCC containers.
 * Trie is something to think about if you're asked to design a structure to dynamically add and search strings.
 *
 * Intuition:
 * The first idea is to add all input words in the trie and then implement a standard search.
 * The problem is we don't know how many characters to match. On the example above, should we try to match the last three
 * stream characters "jkl", the last two "kl", or the last one "l"?
 * The way to solve the problem is to notice that we always know the last character to match. That gives us an idea to
 * build a trie of reversed words, and try to match the reversed stream of characters.
 * This way, instead of multiple choices to match, we always have one path: to match character by character starting
 * from the end of the stream. We could stop once we meet the "end of word" label, which means success. If we couldn't
 * match a character before we meet that label, that means fail.
 */
public class StreamOfCharacters {
    TrieNode trie = new TrieNode();
    Deque<Character> stream = new ArrayDeque();

    public StreamOfCharacters(String[] words) {
        for (String word : words) {
            TrieNode node = trie;
            String reversedWord = new StringBuilder(word).reverse().toString();
            for (char ch : reversedWord.toCharArray()) {
                if (!node.children.containsKey(ch)) {
                    node.children.put(ch, new TrieNode());
                }
                node = node.children.get(ch);
            }
            node.word = true;
        }
    }

    public boolean query(char letter) {
        stream.addFirst(letter);

        TrieNode node = trie;
        for (char ch : stream) {
            if (node.word) {
                return true;
            }
            if (!node.children.containsKey(ch)) {
                return false;
            }
            node = node.children.get(ch);
        }
        return node.word;
    }

    class TrieNode {
        Map<Character, TrieNode> children = new HashMap();
        boolean word = false;
    }
}
