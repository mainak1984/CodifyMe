package edu.codifyme.leetcode.mocktest.google.string;

import java.util.Arrays;

/**
 * MEDIUM: Add Bold Tag in String
 * https://leetcode.com/problems/add-bold-tag-in-string/
 *
 * Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the
 * substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together by only one pair
 * of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.
 *
 * Example 1:
 * Input:
 * s = "abcxyz123"
 * dict = ["abc","123"]
 * Output:
 * "<b>abc</b>xyz<b>123</b>"
 *
 * Example 2:
 * Input:
 * s = "aaabbcc"
 * dict = ["aaa","aab","bc"]
 * Output:
 * "<b>aaabbc</b>c"
 *
 * Constraints:
 * The given dict won't contain duplicates, and its length won't exceed 100.
 * All the strings in input have length in range [1, 1000].
 * Note: This question is the same as 758: https://leetcode.com/problems/bold-words-in-string/
 */
public class AddBoldTaginString {
    public String addBoldTag(String s, String[] dict) {
        int[] marker = new int[s.length()];

        for (String str: dict) {
            int index = 0;

            while (index != -1) {
                index = s.indexOf(str, index);

                for (int i = 0; index != -1 && i < str.length(); i++) {
                    marker[index + i] = 1;
                }

                if (index != -1)
                    index++;
            }
        }

        System.out.println(Arrays.toString(marker));

        StringBuilder sb = new StringBuilder();

        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (marker[i] == 1) {
                sb.append("<b>");
                while (i < arr.length && marker[i] == 1) {
                    sb.append(arr[i]);
                    i++;
                }
                sb.append("</b>");
                i--;
            } else {
                sb.append(arr[i]);
            }
        }

        return sb.toString();
    }
}
