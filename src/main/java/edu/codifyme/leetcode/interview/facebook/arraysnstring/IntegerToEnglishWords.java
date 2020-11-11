package edu.codifyme.leetcode.interview.facebook.arraysnstring;

import java.util.HashMap;
import java.util.Map;

/**
 * 273. Integer to English Words
 * HARD: https://leetcode.com/problems/integer-to-english-words/
 * Editor's choice: Frequently asked in Facebook phone interview.
 *
 * Convert a non-negative integer num to its English words representation.
 *
 * Example 1:
 * Input: num = 123
 * Output: "One Hundred Twenty Three"
 *
 * Example 2:
 * Input: num = 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 *
 * Example 3:
 * Input: num = 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 *
 * Example 4:
 * Input: num = 1234567891
 * Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 *
 * Constraints:
 * 0 <= num <= 231 - 1
 *
 * Approach:
 * Let's simplify the problem by representing it as a set of simple sub-problems. One could split the initial integer
 * 1234567890 on the groups containing not more than three digits 1.234.567.890. That results in representation 1 Billion
 * 234 Million 567 Thousand 890 and reduces the initial problem to how to convert 3-digit integer to English word. One
 * could split further 234 -> 2 Hundred 34 into two sub-problems : convert 1-digit integer and convert 2-digit integer.
 * The first one is trivial. The second one could be reduced to the first one for all 2-digit integers but the ones from
 * 10 to 19 which should be considered separately.
 */
public class IntegerToEnglishWords {
    Map<Integer, String> lessThanTeenDict = new HashMap<>();
    Map<Integer, String> moreThanTeenDict = Map.of(2, "Twenty",
            3, "Thirty",
            4, "Forty",
            5, "Fifty",
            6, "Sixty",
            7, "Seventy",
            8, "Eighty",
            9, "Ninety");
    Map<Integer, String> formatter = Map.of(1, "",
            1000, " Thousand ",
            1000000, " Million ",
            1000000000, " Billion ");
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        initialize();

        StringBuilder sb = new StringBuilder();
        int divisor = 1000000000;

        while (divisor != 0) {
            int val = num / divisor;
            if (val != 0) {
                sb.append(getWord(val));
                sb.append(formatter.get(divisor));
            }

            num = num % divisor;
            divisor /= 1000;
        }
        return sb.toString().trim();
    }

    String getWord(int num) {
        if (num == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        if (num > 99) {
            sb.append(lessThanTeenDict.get(num/100));
            sb.append(" Hundred ");
        }

        num %= 100;

        if (num > 19) {
            sb.append(moreThanTeenDict.get(num/10));
            sb.append(" ");
            sb.append(lessThanTeenDict.get(num%10));
        } else {
            sb.append(lessThanTeenDict.get(num));
        }

        return sb.toString().trim();
    }

    void initialize() {
        lessThanTeenDict.put(0, "");
        lessThanTeenDict.put(1, "One");
        lessThanTeenDict.put(2, "Two");
        lessThanTeenDict.put(3, "Three");
        lessThanTeenDict.put(4, "Four");
        lessThanTeenDict.put(5, "Five");
        lessThanTeenDict.put(6, "Six");
        lessThanTeenDict.put(7, "Seven");
        lessThanTeenDict.put(8, "Eight");
        lessThanTeenDict.put(9, "Nine");
        lessThanTeenDict.put(10, "Ten");
        lessThanTeenDict.put(11, "Eleven");
        lessThanTeenDict.put(12, "Twelve");
        lessThanTeenDict.put(13, "Thirteen");
        lessThanTeenDict.put(14, "Fourteen");
        lessThanTeenDict.put(15, "Fifteen");
        lessThanTeenDict.put(16, "Sixteen");
        lessThanTeenDict.put(17, "Seventeen");
        lessThanTeenDict.put(18, "Eighteen");
        lessThanTeenDict.put(19, "Nineteen");
    }
}
