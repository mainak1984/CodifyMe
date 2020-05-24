package edu.codifyme.leetcode.interview.googledeck.arraynstring;

/**
 * MEDIUM:
 * https://leetcode.com/explore/interview/card/google/59/array-and-strings/3051/
 *
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 *
 * Example 1:
 *
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * Example 2:
 *
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 * Note:
 *
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contain only digits 0-9.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 *
 * Approach 1:
 * Start from right to left, perform multiplication on every pair of digits, and add them together. Let's draw the process! From the following draft, we can immediately conclude:
 *  `num1[i] * num2[j]` will be placed at indices `[i + j`, `i + j + 1]`
 */
public class MultiplyString {
    // Approach 1: num1[i]*num2[j] is placed at result[i+j],result[i+j+1]
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m + n];

        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j, p2 = i + j + 1;
                int sum = mul + pos[p2];

                pos[p1] += sum / 10;
                pos[p2] = (sum) % 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int p : pos) if(!(sb.length() == 0 && p == 0)) sb.append(p);
        return sb.length() == 0 ? "0" : sb.toString();
    }

    // Approach 2: Just multiply and put in i+j index, treat carry in another loop
//     public String multiply(String num1, String num2) {
//         if(num1.equals("0") || num2.equals("0")) return "0";
//         int len1 = num1.length();
//         int len2 = num2.length();
//         char[] sc1 = num1.toCharArray();
//         char[] sc2 = num2.toCharArray();
//         int[] resArray = new int[len1 + len2 - 1];
//         for(int i = 0; i < len1; i++) {
//             for(int j = 0; j < len2; j++) {
//                 resArray[i + j] += (sc1[i] - '0') * (sc2[j] - '0');
//             }
//         }

//         int carry = 0;
//         for(int i = len1 + len2 - 2; i >= 0; i--) {
//             int tmp = resArray[i];
//             tmp += carry;
//             resArray[i] = tmp % 10;
//             carry = tmp / 10;
//         }

//         StringBuilder sb = new StringBuilder();
//         if(carry != 0) {
//             sb.append(carry);
//         }

//         for(int i = 0; i < len1 + len2 - 1; i++) {
//             sb.append(resArray[i]);
//         }

//         return sb.toString();
//     }
}
