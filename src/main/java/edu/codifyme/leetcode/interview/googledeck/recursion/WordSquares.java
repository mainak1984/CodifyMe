package edu.codifyme.leetcode.interview.googledeck.recursion;

import java.util.*;

/**
 * HARD:
 * https://leetcode.com/problems/word-squares
 *
 * Given a set of words (without duplicates), find all word squares you can build from them.
 * A sequence of words forms a valid word square if the kth row and column read the exact same string,
 * where 0 ≤ k < max(numRows, numColumns).
 * For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same
 * both horizontally and vertically.
 *
 * b a l l
 * a r e a
 * l e a d
 * l a d y
 * Note:
 * There are at least 1 and at most 1000 words.
 * All words will have the exact same length.
 * Word length is at least 1 and at most 5.
 * Each word contains only lowercase English alphabet a-z.
 *
 * Example 1:
 * Input:
 * ["area","lead","wall","lady","ball"]
 *
 * Output:
 * [
 *   [ "wall",
 *     "area",
 *     "lead",
 *     "lady"
 *   ],
 *   [ "ball",
 *     "area",
 *     "lead",
 *     "lady"
 *   ]
 * ]
 *
 * Explanation:
 * The output consists of two word squares. The order of output does not matter (just the order of words in each word
 * square matters).
 *
 * Example 2:
 * Input:
 * ["abat","baba","atan","atal"]
 *
 * Output:
 * [
 *   [ "baba",
 *     "abat",
 *     "baba",
 *     "atan"
 *   ],
 *   [ "baba",
 *     "abat",
 *     "baba",
 *     "atal"
 *   ]
 * ]
 *
 * Explanation:
 * The output consists of two word squares. The order of output does not matter (just the order of words in each word
 * square matters).
 *
 * Algorithm: Backtracking
 * Given a list of words, we are asked to find a combination of words upon with we could construct a word square. The
 * backbone of the algorithm to solve the above problem could be surprisingly simple.
 * The idea is that we construct the word square row by row from top to down. At each row, we simply do trial and error,
 * i.e. we try with one word, if it does not meet the constraint then we try another one.
 * As one might notice, the above idea of the algorithm is actually known as backtracking, which is often associated with
 * recursion and DFS (Depth-First Search) as well.
 *
 * Approach 1: Backtracking with HashTable
 * One of the ideas to optimize the getWordsWithPrefix() function would be to process the words beforehand and to build
 * a data structure that could speed up the lookup procedure later.
 *
 * Approach 2: Backtracking with Trie
 * Intuition
 *
 * Speaking about prefix, there is another data structure called Trie (also known as prefix tree), which could find its
 * use in this problem.
 *
 * In the above approach, we have reduce the time complexity of retrieving a list of words with a given prefix from the
 * linear O(N) to the constant time \mathcal{O}(1)O(1). In exchange, we have to spend some extra space to store all the
 * prefixes of each words.
 */
public class WordSquares {
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> result = new LinkedList<>();
        buildMap(words);

        String[] temp = new String[words[0].length()];

        for(String word: words) {
            temp[0] = word;
            getSquare(words, temp, 1, words[0].length(), result);
        }

        return result;
    }

    void getSquare(String[] words, String[] temp, int depth, int n, List<List<String>> result) {
        if (depth == n) {
            // result.add(Arrays.asList(temp));
            List<String> output = new LinkedList<>();
            for (int i = 0; i < n; i++ ) {
                output.add(temp[i]);
            }
            result.add(0, output);
            return;
        }

        String startingStr = getStartingStr(temp, depth);
        List<String> startsWith = getStartsWith(startingStr);

        if ( null != startsWith ) {
            for(String nextStr: startsWith) {
                temp[depth] = nextStr;
                getSquare(words, temp, depth+1, n, result);
            }
        }
    }

    String getStartingStr(String[] temp, int depth) {
        StringBuilder sb = new StringBuilder();

        for (int i=0; i < depth; i++) {
            sb.append(temp[i].charAt(depth));
        }

        return sb.toString();
    }

    Map<String, List<String>> startsWith = new HashMap<>();

    public void buildMap(String[] words) {
        for (String word: words) {
            for(int i = 1; i <= word.length(); i++) {
                String str = word.substring(0,i);
                List<String> list = startsWith.get(str);
                if (list == null) {
                    list = new LinkedList<>();
                    startsWith.put(str,list);
                }
                list.add(word);
            }
        }
    }

    public List<String> getStartsWith(String starts) {
        return startsWith.get(starts);
    }

    class TrieNode {
        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        List<Integer> wordList = new ArrayList<Integer>();

        public TrieNode() {}
    }

// APPROACH 2: Trie
//    int N = 0;
//    String[] words = null;
//    TrieNode trie = null;
//
//    public List<List<String>> wordSquares(String[] words) {
//        this.words = words;
//        this.N = words[0].length();
//
//        List<List<String>> results = new ArrayList<List<String>>();
//        this.buildTrie(words);
//
//        for (String word : words) {
//            LinkedList<String> wordSquares = new LinkedList<String>();
//            wordSquares.addLast(word);
//            this.backtracking(1, wordSquares, results);
//        }
//        return results;
//    }
//
//    protected void backtracking(int step, LinkedList<String> wordSquares,
//                                List<List<String>> results) {
//        if (step == N) {
//            results.add((List<String>) wordSquares.clone());
//            return;
//        }
//
//        StringBuilder prefix = new StringBuilder();
//        for (String word : wordSquares) {
//            prefix.append(word.charAt(step));
//        }
//
//        for (Integer wordIndex : this.getWordsWithPrefix(prefix.toString())) {
//            wordSquares.addLast(this.words[wordIndex]);
//            this.backtracking(step + 1, wordSquares, results);
//            wordSquares.removeLast();
//        }
//    }
//
//    protected void buildTrie(String[] words) {
//        this.trie = new TrieNode();
//
//        for (int wordIndex = 0; wordIndex < words.length; ++wordIndex) {
//            String word = words[wordIndex];
//
//            TrieNode node = this.trie;
//            for (Character letter : word.toCharArray()) {
//                if (node.children.containsKey(letter)) {
//                    node = node.children.get(letter);
//                } else {
//                    TrieNode newNode = new TrieNode();
//                    node.children.put(letter, newNode);
//                    node = newNode;
//                }
//                node.wordList.add(wordIndex);
//            }
//        }
//    }
//
//    protected List<Integer> getWordsWithPrefix(String prefix) {
//        TrieNode node = this.trie;
//        for (Character letter : prefix.toCharArray()) {
//            if (node.children.containsKey(letter)) {
//                node = node.children.get(letter);
//            } else {
//                // return an empty list.
//                return new ArrayList<Integer>();
//            }
//        }
//        return node.wordList;
//    }
}
