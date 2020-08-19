package edu.codifyme.leetcode.practice.string;

/**
 * 824. Goat Latin
 * https://leetcode.com/problems/goat-latin/
 *
 * A sentence S is given, composed of words separated by spaces. Each word consists of lowercase and uppercase letters
 * only.
 *
 * We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)
 *
 * The rules of Goat Latin are as follows:
 *
 * If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
 * For example, the word 'apple' becomes 'applema'.
 *
 * If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the end, then add "ma".
 * For example, the word "goat" becomes "oatgma".
 *
 * Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
 * For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.
 * Return the final sentence representing the conversion from S to Goat Latin.
 *
 * Example 1:
 *
 * Input: "I speak Goat Latin"
 * Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
 * Example 2:
 *
 * Input: "The quick brown fox jumped over the lazy dog"
 * Output: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
 */
public class GoatLatin {
    public String toGoatLatin(String S) {
        if (S == null || S.length() == 0) {
            return S;
        }

        StringBuilder sb = new StringBuilder();
        StringBuilder a = new StringBuilder("maa");

        for (String part: S.split(" ")) {
            if (!isVowel(part.charAt(0))) {
                sb.append(part.substring(1));
                sb.append(part.charAt(0));
            } else {
                sb.append(part);
            }

            sb.append(a);
            sb.append(' ');
            a.append('a');
        }

        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    boolean isVowel(char ch) {
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' ||ch == 'u' ||
                ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
            return true;
        }
        return false;
    }
}
