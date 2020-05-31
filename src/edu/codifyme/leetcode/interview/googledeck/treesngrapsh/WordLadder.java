package edu.codifyme.leetcode.interview.googledeck.treesngrapsh;

import javafx.util.Pair;

import java.util.*;

/**
 * MEDIUM:
 * https://leetcode.com/explore/interview/card/google/61/trees-and-graphs/3068/
 *
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list.
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 *
 * Example 1:
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * Output: 5
 *
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 *
 * Example 2:
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: 0
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 *
 * One of the most important step here is to figure out how to find adjacent nodes i.e. words which differ by one letter.
 * To efficiently find the neighboring nodes for any given word we do some pre-processing on the words of the given
 * wordList. The pre-processing involves replacing the letter of a word by a non-alphabet say, *
 * This pre-processing helps to form generic states to represent a single letter change.
 * For e.g. Dog ----> D*g <---- Dig
 * Both Dog and Dig map to the same intermediate or generic state D*g.
 *
 * The graph formed from the nodes in the dictionary might be too big. The search space considered by the breadth first
 * search algorithm depends upon the branching factor of the nodes at each level. If the branching factor remains the
 * same for all the nodes, the search space increases exponentially along with the number of levels. Consider a simple
 * example of a binary tree. With each passing level in a complete binary tree, the number of nodes increase in powers
 * of 2.
 * We can considerably cut down the search space of the standard breadth first search algorithm if we launch two
 * simultaneous BFS. One from the beginWord and one from the endWord. We progress one node at a time from both sides
 * and at any point in time if we find a common node in both the searches, we stop the search. This is known as
 * bidirectional BFS and it considerably cuts down on the search space and hence reduces the time and space complexity.
 */
public class WordLadder {

    private int L;
    private Map<String, List<String>> allComboDict;

    WordLadder() {
        this.L = 0;

        // Dictionary to hold combination of words that can be formed,
        // from any given word. By changing one letter at a time.
        this.allComboDict = new HashMap<>();
    }

    private int visitWordNode(
            Queue<Pair<String, Integer>> Q,
            Map<String, Integer> visited,
            Map<String, Integer> othersVisited) {

        Pair<String, Integer> node = Q.remove();
        String word = node.getKey();
        int level = node.getValue();

        for (int i = 0; i < this.L; i++) {

            // Intermediate words for current word
            String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

            // Next states are all the words which share the same intermediate state.
            for (String adjacentWord : this.allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                // If at any point if we find what we are looking for
                // i.e. the end word - we can return with the answer.
                if (othersVisited.containsKey(adjacentWord)) {
                    return level + othersVisited.get(adjacentWord);
                }

                if (!visited.containsKey(adjacentWord)) {

                    // Save the level as the value of the dictionary, to save number of hops.
                    visited.put(adjacentWord, level + 1);
                    Q.add(new Pair(adjacentWord, level + 1));
                }
            }
        }
        return -1;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if (!wordList.contains(endWord)) {
            return 0;
        }

        // Since all words are of same length.
        this.L = beginWord.length();

        wordList.forEach(
                word -> {
                    for (int i = 0; i < L; i++) {
                        // Key is the generic word
                        // Value is a list of words which have the same intermediate generic word.
                        String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                        List<String> transformations =
                                this.allComboDict.getOrDefault(newWord, new ArrayList<>());
                        transformations.add(word);
                        this.allComboDict.put(newWord, transformations);
                    }
                });

        // Queues for birdirectional BFS
        // BFS starting from beginWord
        Queue<Pair<String, Integer>> Q_begin = new LinkedList<>();
        // BFS starting from endWord
        Queue<Pair<String, Integer>> Q_end = new LinkedList<>();
        Q_begin.add(new Pair(beginWord, 1));
        Q_end.add(new Pair(endWord, 1));

        // Visited to make sure we don't repeat processing same word.
        Map<String, Integer> visitedBegin = new HashMap<>();
        Map<String, Integer> visitedEnd = new HashMap<>();
        visitedBegin.put(beginWord, 1);
        visitedEnd.put(endWord, 1);

        while (!Q_begin.isEmpty() && !Q_end.isEmpty()) {

            // One hop from begin word
            int ans = visitWordNode(Q_begin, visitedBegin, visitedEnd);
            if (ans > -1) {
                return ans;
            }

            // One hop from end word
            ans = visitWordNode(Q_end, visitedEnd, visitedBegin);
            if (ans > -1) {
                return ans;
            }
        }

        return 0;
    }

//    public int ladderLength(String findDistance, String endWord, List<String> wordList) {
//        Graph g = new Graph();
//
//        if (null == wordList || 0 == wordList.size()) {
//            return 0;
//        }
//
//        g.addVertex(findDistance);
//        for (String word: wordList) {
//            g.addVertex(word);
//        }
//
//        if (!g.getAllWords().contains(endWord)) {
//            return 0;
//        }
//
//        for (String source: g.getAllWords() ) {
//            for (String target: g.getAllWords() ) {
//                if (!source.equals(target) && isOneHop(source, target)) {
//                    g.addEdge(source, target);
//                }
//            }
//        }
//
//        // Do a BFS and find the shortest path
//        return g.findDistance(findDistance, endWord);
//    }
//
//    class Graph {
//        HashMap<String, List<String>> edges = new HashMap<>();
//
//        public List<String> addVertex(String src) {
//            List<String> adjList = edges.get(src);
//
//            if (null == adjList) {
//                adjList = new LinkedList<>();
//                edges.put(src, adjList);
//            }
//            return adjList;
//        }
//
//        public void addEdge(String src, String dest) {
//            List<String> adjList = addVertex(src);
//            adjList.add(dest);
//        }
//
//        public Set<String> getAllWords() {
//            return edges.keySet();
//        }
//
//        public int findDistance(String src, String dst) {
//            Queue<String> queue = new LinkedList<>();
//            Set<String> visited = new HashSet<>();
//            int level = -1;
//
//            queue.offer(src);
//            queue.offer(String.valueOf(level));
//            visited.add(src);
//
//            while ( !(queue.isEmpty() || (1 == queue.size() && queue.peek().startsWith("-"))) ) {
//                String node = queue.poll();
//                // System.out.println(node+", QSize "+queue.size());
//
//                if (node.equals(dst)) {
//                    return Math.abs(level);
//                }
//
//                if (node.startsWith("-")) {
//                    level -= 1;
//                    // System.out.println("level "+level);
//                    queue.offer(String.valueOf(level));
//                    continue;
//                }
//
//                List<String> neighbours = edges.get(node);
//                // System.out.println("Got neightbours "+neighbours.size());
//
//                for (String next: neighbours) {
//                    if (!visited.contains(next)) {
//                        // System.out.println(next);
//                        visited.add(next);
//                        queue.offer(next);
//                    } else {
//                        // System.out.println("-"+next);
//                    }
//                }
//            }
//
//            return 0;
//        }
//    }
//
//    boolean isOneHop(String src, String dest) {
//        int sum = 0;
//
//        for (int loop = 0; loop < src.length(); loop++) {
//            if ( src.charAt(loop) != dest.charAt(loop) ) {
//                sum++;
//            }
//        }
//
//        return (sum == 1)? true: false;
//    }
}
