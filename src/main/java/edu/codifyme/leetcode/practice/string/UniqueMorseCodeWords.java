package edu.codifyme.leetcode.practice.string;

import java.util.HashSet;
import java.util.Set;

/**
 * 804. Unique Morse Code Words
 * EASY: https://leetcode.com/problems/unique-morse-code-words/
 *
 * International Morse Code defines a standard encoding where each letter is mapped to a series of dots and dashes, as
 * follows: "a" maps to ".-", "b" maps to "-...", "c" maps to "-.-.", and so on.
 *
 * For convenience, the full table for the 26 letters of the English alphabet is given below:
 *
 * [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...",
 * "-","..-","...-",".--","-..-","-.--","--.."]
 * Now, given a list of words, each word can be written as a concatenation of the Morse code of each letter. For example,
 * "cab" can be written as "-.-..--...", (which is the concatenation "-.-." + ".-" + "-..."). We'll call such a
 * concatenation, the transformation of a word.
 *
 * Return the number of different transformations among all words we have.
 *
 * Example:
 * Input: words = ["gin", "zen", "gig", "msg"]
 * Output: 2
 * Explanation:
 * The transformation of each word is:
 * "gin" -> "--...-."
 * "zen" -> "--...-."
 * "gig" -> "--...--."
 * "msg" -> "--...--."
 * There are 2 different transformations, "--...-." and "--...--.".
 *
 * Note:
 * The length of words will be at most 100.
 * Each words[i] will have length in range [1, 12].
 * words[i] will only consist of lowercase letters.
 *
 * Approach:
 * We can transform each word into it's Morse Code representation.
 * After, we put all transformations into a set seen, and return the size of the set.
 */
public class UniqueMorseCodeWords {
    public int uniqueMorseRepresentations(String[] words) {
        Set<String> result = new HashSet<>();
        String[] codes = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--",
                "-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

        for (String word: words) {
            StringBuilder sb = new StringBuilder();
            for (char ch: word.toCharArray()) {
                sb.append(codes[ch - 'a']);
            }
            result.add(sb.toString());
        }

        return result.size();
    }
}
