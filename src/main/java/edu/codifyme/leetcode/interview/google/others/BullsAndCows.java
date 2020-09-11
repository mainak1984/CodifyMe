package edu.codifyme.leetcode.interview.google.others;

/**
 * 299. Bulls and Cows
 * EASY: https://leetcode.com/problems/bulls-and-cows
 *
 * You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to
 * guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in
 * said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the
 * secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to
 * eventually derive the secret number.
 * Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and
 * B to indicate the cows.
 * Please note thatMaximum Product Subarray both secret number and friend's guess may contain duplicate digits.
 *
 * Example 1:
 * Input: secret = "1807", guess = "7810"
 * Output: "1A3B"
 * Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
 *
 * Example 2:
 * Input: secret = "1123", guess = "0111"
 * Output: "1A1B"
 * Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
 * Note: You may assume that the secret number and your friend's guess only contain digits, and their lengths are always
 * equal.
 *
 * Approach 1: HashMap: Two Passes
 * Algorithm
 * Initialize the number of bulls and cows to zero.
 * Initialize the hashmap character -> its frequency for the string secret. This hashmap could be later used during the
 * iteration over the string guess to keep the available characters.
 * It's time to iterate over the string guess.
 *      - If the current character ch of the string guess is in the string secret: if ch in h, then there could be two
 *      situations.
 *          $ The corresponding characters of two strings match: ch == secret[idx].
 *          $ Then it's time to update the bulls: bulls += 1.
 *          $ The update of the cows is needed if the count for the current character in the hashmap is negative or
 *          equal to zero. That means that before it was already used for cows, and the cows counter should be
 *          decreased: cows -= int(h[ch] <= 0).
 *      - The corresponding characters of two strings don't match: ch != secret[idx]. Then increase the cows counter:
 *      cows += int(h[ch] > 0).
 *      - In both cases, one has to update hashmap, marking the current character as used: h[ch] -= 1.
 * Return the number of bulls and cows.
 *
 * Approach 2: One Pass
 * Intuition
 * Let's optimize approach 1 by building the hashmap during the strings' parsing. That would allow us to reduce the
 * number of passes to one.
 *
 * Algorithm
 * Initialize the number of bulls and cows to zero.
 * Initialize the hashmap to count characters. During the iteration, secret string gives a positive contribution, and
 * guess - negative contribution.
 * Iterate over the strings: s is the current character in the string secret and g - the current character in the
 * string guess.
 *      - If s == g, update bulls counter: bulls += 1.
 *      - Otherwise, if s != g:
 *          $ Update cows by adding 1 if so far guess contains more s characters than secret: h[s] < 0.
 *          $ Update cows by adding 1 if so far secret contains more g characters than guess: h[g] > 0.
 *          $ Update the hashmap by marking the presence of s character in the string secret: h[s] += 1.
 *          $ Update the hashmap by marking the presence of g character in the string guess: h[g] -= 1.
 *      - Return the number of bulls and cows.
 */
public class BullsAndCows {
    public String getHint(String secret, String guess) {
        int bullCount = 0;
        int cowCount = 0;
        int[] countSecret = new int[10];
        int[] countGuess = new int[10];

        for (int loop = 0; loop < secret.length(); loop++ ) {
            if (secret.charAt(loop) == guess.charAt(loop)) {
                bullCount++;
            } else {
                countSecret[secret.charAt(loop) - '0']++;
                countGuess[guess.charAt(loop) - '0']++;
            }
        }

        for (int loop = 0; loop < 10; loop++) {
            if (countSecret[loop] > 0 && countGuess[loop] > 0) {
                cowCount += Math.min(countSecret[loop], countGuess[loop]);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(bullCount);
        sb.append('A');
        sb.append(cowCount);
        sb.append('B');

        return sb.toString();
    }

    // public String getHint(String secret, String guess) {
    //     int bulls = 0;
    //     int cows = 0;
    //     int[] numbers = new int[10];
    //     for (int i = 0; i<secret.length(); i++) {
    //         int s = Character.getNumericValue(secret.charAt(i));
    //         int g = Character.getNumericValue(guess.charAt(i));
    //         if (s == g) bulls++;
    //         else {
    //             if (numbers[s] < 0) cows++;
    //             if (numbers[g] > 0) cows++;
    //             numbers[s] ++;
    //             numbers[g] --;
    //         }
    //     }
    //     return bulls + "A" + cows + "B";
    // }
}
