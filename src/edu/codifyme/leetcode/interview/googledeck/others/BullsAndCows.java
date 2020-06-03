package edu.codifyme.leetcode.interview.googledeck.others;

/**
 * EASY:
 * https://leetcode.com/explore/interview/card/google/66/others-4/3100/
 *
 * You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to
 * guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in
 * said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the
 * secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to
 * eventually derive the secret number.
 * Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and
 * B to indicate the cows.
 * Please note that both secret number and friend's guess may contain duplicate digits.
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
 * Note: You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
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
