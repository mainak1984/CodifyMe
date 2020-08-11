package edu.codifyme.leetcode.practice.string;

/**
 * 125. Valid Palindrome
 * EASY: https://leetcode.com/problems/valid-palindrome/
 *
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 *
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 *
 * Example 1:
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 *
 * Example 2:
 * Input: "race a car"
 * Output: false
 *
 * Constraints:
 * s consists only of printable ASCII characters.
 */
public class ValidPalindrome {
    // Approach 1: Two pointer
    public boolean isPalindrome(String str) {
        if(str==null || str.isEmpty())
            return true;

        char[] s = str.toCharArray();

        int low=0, high =s.length-1;

        while(low<high){
            while(low<high && !Character.isLetterOrDigit(s[low]))
                low++;

            while(low<high && !Character.isLetterOrDigit(s[high]))
                high--;

            if(Character.toLowerCase(s[low]) != Character.toLowerCase(s[high]))
                return false;

            low++;
            high--;
        }

        return true;
    }

    // Approach 2: Compare with reverse
//     public boolean isPalindrome(String s) {
//         if (null == s || s.length() == 0) {
//             return true;
//         }
//         s = s.toLowerCase();
//         char[] arr = s.toCharArray();
//         StringBuilder sb = new StringBuilder();

//         for (char ch: arr) {
//             if ((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')) {
//                 sb.append(ch);
//             }
//         }

//         String first = sb.toString();
//         String second = sb.reverse().toString();

//         return first.equals(second);
//     }
}
