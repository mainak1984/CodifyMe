package edu.codifyme.leetcode.interview.google.others;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * HARD:
 * https://leetcode.com/problems/guess-the-word
 *
 * This problem is an interactive problem new to the LeetCode platform.
 * We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.
 * You may call master.guess(word) to guess a word.  The guessed word should have type string and must be from the
 * original list with 6 lowercase letters.
 * This function returns an integer type, representing the number of exact matches (value and position) of your guess
 * to the secret word.  Also, if your guess is not in the given wordlist, it will return -1 instead.
 * For each test case, you have 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or
 * less calls to master.guess and at least one of these guesses was the secret, you pass the testcase.
 * Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list.
 * The letters of each word in those testcases were chosen independently at random from 'a' to 'z', such that every
 * word in the given word lists is unique.
 *
 * Example 1:
 * Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]
 *
 * Explanation:
 * master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
 * master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
 * master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
 * master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
 * master.guess("abcczz") returns 4, because "abcczz" has 4 matches.
 *
 * We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
 * Note:  Any solutions that attempt to circumvent the judge will result in disqualification.
 *
 * Approach:
 * https://leetcode.com/explore/interview/card/google/66/others-4/3104/discuss/556075/How-to-explain-to-interviewer-843.-Guess-the-Word
 * How to find the secret word in as less times of calling master.guess() as possible? The answer is trying to narrow
 * the candidates after each time we call master.guess()
 */
public class GuessTheWord {
    public void findSecretWord(String[] wordlist, Master master) {
        int match = 0;
        Random rn = new Random();
        for (int loop = 0; loop < 10 && match != 6; loop++ ) {
            String guess = wordlist[rn.nextInt(wordlist.length)];
            match = master.guess(guess);

            List<String> words = new ArrayList<>();
            for (String word: wordlist) {
                if ( match == matching(guess, word) ) {
                    words.add(word);
                }
            }
            wordlist = words.toArray(new String[0]);
        }

    }

    int matching(String word1, String word2) {
        int count = 0;
        for (int loop = 0; loop < 6; loop++) {
            if (word1.charAt(loop) == word2.charAt(loop) ) {
                count += 1;
            }
        }

        return count;
    }

    interface Master {
        default int guess(String word) {return 0;};
    }
}
