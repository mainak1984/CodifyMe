package edu.codifyme.leetcode.practice.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * 763. Partition Labels
 * MEDIUM: https://leetcode.com/problems/partition-labels/
 *
 * A string S of lowercase English letters is given. We want to partition this string into as many parts as possible so
 * that each letter appears in at most one part, and return a list of integers representing the size of these parts.
 *
 * Example 1:
 *
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 *
 * Note:
 * S will have length in range [1, 500].
 * S will consist of lowercase English letters ('a' to 'z') only.
 *
 * Approach:
 * Let's try to repeatedly choose the smallest left-justified partition. Consider the first label, say it's 'a'. The
 * first partition must include it, and also the last occurrence of 'a'. However, between those two occurrences of 'a',
 * there could be other labels that make the minimum size of this partition bigger. For example, in "abccaddbeffe", the
 * minimum first partition is "abccaddb". This gives us the idea for the algorithm: For each letter encountered, process
 * the last occurrence of that letter, extending the current partition [anchor, j] appropriately.
 */
public class PartitionLabels {
    // Best solution (compact)
    public List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        for (int i = 0; i < S.length(); ++i)
            last[S.charAt(i) - 'a'] = i;

        int j = 0, anchor = 0;
        List<Integer> ans = new ArrayList();
        for (int i = 0; i < S.length(); ++i) {
            j = Math.max(j, last[S.charAt(i) - 'a']);
            if (i == j) {
                ans.add(i - anchor + 1);
                anchor = i + 1;
            }
        }
        return ans;
    }

    // Good solution, easy to understand code
    // public List<Integer> partitionLabels(String s) {
    //     List<Integer> res = new ArrayList<>();
    //     if (s == null || s.length() == 0) {
    //         return res;
    //     }
    //     int[] dict = new int[26];
    //     for (char c : s.toCharArray()) {
    //         dict[c-'a'] ++;
    //     }
    //     int[] curr = new int[26];
    //     int total = 0;
    //     int len = 0;
    //     for (char c : s.toCharArray()) {
    //         len ++;
    //         if (curr[c-'a'] == 0) {
    //             total ++;
    //         }
    //         curr[c-'a']++;
    //         if (curr[c-'a'] == dict[c-'a']) {
    //             total --;
    //         }
    //         if (total == 0) {
    //             res.add(len);
    //             len = 0;
    //         }
    //     }
    //     return res;
    // }

    // Alternate approach: easiest to understand
//    public List<Integer> partitionLabels(String S) {
//        List<Integer> result = new ArrayList<>();
//
//        int[] counter = new int[S.length()];
//        boolean[] visited = new boolean[26];
//        char[] chArr = S.toCharArray();
//        int sum = 0;
//
//        for (int i = chArr.length - 1; i >= 0; i--) {
//            if (!visited[chArr[i] - 'a']) {
//                visited[chArr[i] - 'a'] = true;
//                counter[i] += -1;
//            }
//        }
//
//        for (int i = 0, j = 1; i < chArr.length; i++, j++) {
//            if (visited[chArr[i] - 'a']) {
//                visited[chArr[i] - 'a'] = false;
//                counter[i] += 1;
//            }
//
//            sum += counter[i];
//
//            if (sum == 0) {
//                result.add(j);
//                j = 0;
//            }
//        }
//
//        return result;
//    }
}
