package edu.codifyme.leetcode.practice.stacknqueue;

import java.util.Stack;

/**
 * 316. Remove Duplicate Letters
 * 1081. Smallest Subsequence of Distinct Characters
 * MEDIUM: https://leetcode.com/problems/remove-duplicate-letters/
 *
 * Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your
 * result is the smallest in lexicographical order among all possible results.
 *
 * Note: This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
 *
 * Example 1:
 * Input: s = "bcabc"
 * Output: "abc"
 *
 * Example 2:
 * Input: s = "cbacdcbc"
 * Output: "acdb"
 *
 * Constraints:
 * 1 <= s.length <= 104
 * s consists of lowercase English letters.
 *
 * Approach:
 * Find the index of last occurrence for each character.
 * Use a stack to keep the characters for result.
 * Loop on each character in the input string S,
 * if the current character is smaller than the last character in the stack,
 * and the last character exists in the following stream,
 * we can pop the last character to get a smaller result.
 *
 * Time Complexity:
 * Time O(N)
 * Space O(26)
 */
public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        Stack<Character> st = new Stack<>();
        int[] last = new int[26];
        int[] seen = new int[26];

        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            last[arr[i] - 'a'] = i;
        }

        for (int i = 0; i < arr.length; i++) {
            char ch = arr[i];
            seen[ch - 'a'] += 1;

            if (seen[ch - 'a'] > 1) {
                continue;
            }

            while (!st.empty() &&  st.peek() > ch && last[st.peek() - 'a'] > i) {
                char pop = st.pop();
                seen[pop - 'a'] = 0;
            }

            st.push(ch);
        }

        StringBuilder sb = new StringBuilder();
        for (char ch: st) {
            sb.append(ch);
        }
        return sb.toString();
    }

    // public String removeDuplicateLetters(String s) {
    //     StringBuilder sb = new StringBuilder();
    //     int[] count = new int[26];
    //     boolean[] used = new boolean[26];
    //     char[] chs = s.toCharArray();
    //     for(char ch:chs){
    //         count[ch-'a']++;
    //     }
    //     for(char c : chs){
    //         count[c-'a']--;
    //         if(used[c-'a']){
    //             continue;
    //         }
    //         while(sb.length()>0 && sb.charAt(sb.length()-1)>c && count[sb.charAt(sb.length()-1)-'a']>0){
    //             used[sb.charAt(sb.length()-1) - 'a'] = false;
    //             sb.deleteCharAt(sb.length()-1);
    //         }
    //         sb.append(c);
    //         used[c-'a']=true;
    //     }
    //     return sb.toString();
    // }
}
