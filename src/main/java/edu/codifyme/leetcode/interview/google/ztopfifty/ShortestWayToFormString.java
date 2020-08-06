package edu.codifyme.leetcode.interview.google.ztopfifty;

/**
 * MEDIUM:
 * https://leetcode.com/problems/shortest-way-to-form-string/
 *
 * From any string, we can form a subsequence of that string by deleting some number of characters (possibly no
 * deletions).
 * Given two strings source and target, return the minimum number of subsequences of source such that their concatenation
 * equals target. If the task is impossible, return -1.
 *
 * Example 1:
 * Input: source = "abc", target = "abcbc"
 * Output: 2
 * Explanation: The target "abcbc" can be formed by "abc" and "bc", which are subsequences of source "abc".
 *
 * Example 2:
 * Input: source = "abc", target = "acdbc"
 * Output: -1
 * Explanation: The target string cannot be constructed from the subsequences of source string due to the character "d"
 * in target string.
 *
 * Example 3:
 * Input: source = "xyz", target = "xzyxz"
 * Output: 3
 * Explanation: The target string can be constructed as follows "xz" + "y" + "xz".
 *
 * Constraints:
 *
 * Both the source and target strings consist of only lowercase English letters from "a"-"z".
 * The lengths of source and target string are between 1 and 1000.
 *
 * Approach:
 * Find character from target into source.
 * Using String APIs, complexity is O(M * N).
 *
 * Using binary search makes in O(N long M)
 *
 * Using a prefilled dictionary to store the position of a character after this index in src, makes it O(N)
 */
public class ShortestWayToFormString {
    // Approach - Brute force
    public int shortestWay(String source, String target) {
        int currPos = 0;
        int noOfParts = 1;

        for (char ch: target.toCharArray()) {
            currPos = source.indexOf(ch, currPos);
            if ( -1 == currPos ) {
                // reset currPos and check again in the source array; not found Initially
                noOfParts += 1;
                currPos = source.indexOf(ch);
                if (-1 == currPos) {
                    return -1;
                } else {
                    currPos += 1;
                }
            } else {
                currPos += 1;
            }
        }

        return noOfParts;
    }

    // Approach 2; building an index
//    public int shortestWay(String source, String target) {
//        int[][] map = new int[26][source.length()];
//
//        for (int i = 0; i < 26; i++) {
//            Arrays.fill(map[i], -1);
//        }
//
//        buildMap(map, source);
//
//        int index = -1;
//        int count = 0;
//
//        for (char ch: target.toCharArray()) {
//            int val = map[ch-'a'][++index];
//
//            if (val == -1) {
//                index = source.indexOf(ch);
//
//                if ( index == -1) {
//                    return -1;
//                }
//
//                count++;
//            } else {
//                index = val;
//
//                if (index == source.length() - 1) {
//                    count++;
//                    index = -1;
//                }
//            }
//        }
//
//        if (index != -1) {
//            count++;
//        }
//
//        return count;
//    }
//
//    void buildMap(int[][] map, String source) {
//        for (int i = source.length() - 1; i >= 0; i--) {
//            int ch = source.charAt(i);
//            int index = -1;
//
//            if ( map[ch - 'a'][0] == -1) {
//                for (int j = source.length() - 1; j >= 0; j--) {
//                    if ( source.charAt(j) == ch ) {
//                        index = j;
//                    }
//                    map[ch - 'a'][j] = index;
//                }
//            }
//        }
//    }

    // Approach 2: Using pre-filled array
    // https://leetcode.com/problems/shortest-way-to-form-string/discuss/332419/O(M-%2B-N)-Java-solution-with-commented-code-and-detailed-explanation-(Beats-98)
//    public int shortestWay(String source, String target) {
//        char[] s = source.toCharArray();
//        char[] t = target.toCharArray();
//
//        int M = s.length;
//        int N = t.length;
//
//        // Build inverted index for source
//        int[][] dict = new int[M][26];
//
//        Arrays.fill(dict[M-1], -1); // -1 represents no occurrence of the character
//
//        dict[M-1][s[M-1] - 'a'] = M-1;
//        for(int x = M - 2; x >= 0; --x) {
//            dict[x] = Arrays.copyOf(dict[x+1], 26);
//            dict[x][s[x] - 'a'] = x;
//        }
//
//        int ans = 0;
//        int idx = 0;
//        // Go through target and account for each character
//        for(char c: t) {
//            // If there are no occurrences of c with index greater than 0
//            // then it doesn't occur at all. Hence, we cannot get that letter from
//            // a subsequence of source.
//            if(dict[0][c - 'a'] == -1) return -1;
//
//            // If there are no c's left in source that occur >= idx
//            // but there are c's from earlier in the subsequence
//            // add 1 to subsequence count and reset idx of source to 0.
//            if(dict[idx][c - 'a'] == -1) {
//                ++ans;
//                idx = 0;
//            }
//
//            // Then continue taking letters from the subsequence
//            idx = dict[idx][c-'a'] + 1;
//
//            if(idx == M) {
//                ++ans;
//                idx = 0;
//            }
//        }
//
//        return ans + (idx == 0? 0: 1);
//    }
}
