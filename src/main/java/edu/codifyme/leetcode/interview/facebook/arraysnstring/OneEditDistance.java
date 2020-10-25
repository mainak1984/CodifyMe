package edu.codifyme.leetcode.interview.facebook.arraysnstring;

/**
 * 161. One Edit Distance
 * MEDIUM: https://leetcode.com/problems/one-edit-distance/
 *
 * Given two strings s and t, return true if they are both one edit distance apart, otherwise return false.
 * A string s is said to be one distance apart from a string t if you can:
 *
 * Insert exactly one character into s to get t.
 * Delete exactly one character from s to get t.
 * Replace exactly one character of s with a different character to get t.
 *
 * Example 1:
 * Input: s = "ab", t = "acb"
 * Output: true
 * Explanation: We can insert 'c' into s to get t.
 *
 * Example 2:
 * Input: s = "", t = ""
 * Output: false
 * Explanation: We cannot get t from s by only one step.
 *
 * Example 3:
 * Input: s = "a", t = ""
 * Output: true
 *
 * Example 4:
 * Input: s = "", t = "A"
 * Output: true
 *
 * Constraints:
 * 0 <= s.length <= 104
 * 0 <= t.length <= 104
 * s and t consist of lower-case letters, upper-case letters and/or digits.
 *
 * Approach:
 * First of all, let's ensure that the string lengths are not too far from each other. If the length difference is 2 or
 * more characters, then s and t couldn't be one edit away strings.
 * For the next let's assume that s is always shorter or the same length as t. If not, one could always call
 * isOneEditDistance(t, s) to inverse the string order.
 *
 * Now it's time to pass along the strings and to look for the first different character.
 * If there is no differences between the first len(s) characters, only two situations are possible :
 *      - The strings are equal.
 *      - The strings are one edit away distance.
 *
 * Now what if there is a different character so that s[i] != t[i].
 *      - If the strings are of the same length, all next characters should be equal to keep one edit away distance.
 *      To verify it, one has to compare the substrings of s and t both starting from the i + 1th character.
 *      - If t is one character longer than s, the additional character t[i] should be the only difference between
 *      both strings. To verify it, one has to compare a substring of s starting from the ith character and a substring
 *      of t starting from the i + 1th character.
 */
public class OneEditDistance {
    // Approach 1:
    public boolean isOneEditDistance(String s, String t) {
        if (Math.abs(s.length()-t.length()) > 1) {
            return false;
        }

        boolean isSameLength = (s.length() == t.length())? true: false;
        String bigger = (s.length() > t.length())? s: t;
        String smaller = (s.length() > t.length())? t: s;

        int remaining = smaller.length();
        for (int i = 0; i < remaining;  i++) {
            if (bigger.charAt(i) != smaller.charAt(i)) {
                String comp = isSameLength ? smaller.substring(i+1): smaller.substring(i);
                return bigger.substring(i+1).equals(comp);
            }
        }

        return !isSameLength;
    }

    // Approach 2: as per the algo
//     public boolean isOneEditDistance(String s, String t) {
//         int ns = s.length();
//         int nt = t.length();

//         // Ensure that s is shorter than t.
//         if (ns > nt)
//           return isOneEditDistance(t, s);

//         // The strings are NOT one edit away distance
//         // if the length diff is more than 1.
//         if (nt - ns > 1)
//           return false;

//         for (int i = 0; i < ns; i++)
//           if (s.charAt(i) != t.charAt(i))
//             // if strings have the same length
//             if (ns == nt)
//               return s.substring(i + 1).equals(t.substring(i + 1));
//             // if strings have different lengths
//             else
//               return s.substring(i).equals(t.substring(i + 1));

//         // If there is no diffs on ns distance
//         // the strings are one edit away only if
//         // t has one more character.
//         return (ns + 1 == nt);
//       }
}
