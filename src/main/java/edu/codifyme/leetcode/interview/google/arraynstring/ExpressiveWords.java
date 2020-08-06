package edu.codifyme.leetcode.interview.google.arraynstring;

import java.util.LinkedList;
import java.util.List;

/**
 * MEDIUM:
 * https://leetcode.com/problems/expressive-words
 *
 * Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  In
 * these strings like "heeellooo", we have groups of adjacent letters that are all the same:  "h", "eee", "ll", "ooo".
 *
 * For some given string S, a query word is stretchy if it can be made to be equal to S by any number of applications
 * of the following extension operation: choose a group consisting of characters c, and add some number of characters
 * c to the group so that the size of the group is 3 or more.
 *
 * For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get
 * "helloo" since the group "oo" has size less than 3.  Also, we could do another extension like "ll" -> "lllll" to get
 * "helllllooo".  If S = "helllllooo", then the query word "hello" would be stretchy because of these two extension
 * operations: query = "hello" -> "hellooo" -> "helllllooo" = S.
 *
 * Given a list of query words, return the number of words that are stretchy.
 *
 *
 *
 * Example:
 * Input:
 * S = "heeellooo"
 * words = ["hello", "hi", "helo"]
 * Output: 1
 * Explanation:
 * We can extend "e" and "o" in the word "hello" to get "heeellooo".
 * We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.
 *
 *
 * Notes:
 *
 * 0 <= len(S) <= 100.
 * 0 <= len(words) <= 100.
 * 0 <= len(words[i]) <= 100.
 * S and all words in words consist only of lowercase letters
 *
 * Approach #1: Run Length Encoding [Accepted]
 * For some word, write the head character of every group, and the count of that group. For example, for
 * "abbcccddddaaaaa", we'll write the "key" of "abcda", and the "count" [1,2,3,4,5].
 * Let's see if a word is stretchy. Evidently, it needs to have the same key as S.
 * Now, let's say we have individual counts c1 = S.count[i] and c2 = word.count[i].
 * If c1 < c2, then we can't make the ith group of word equal to the ith word of S by adding characters.
 * If c1 >= 3, then we can add letters to the ith group of word to match the ith group of S, as the latter is extended.
 * Else, if c1 < 3, then we must have c2 == c1 for the ith groups of word and S to match.
 */
public class ExpressiveWords {
    public int expressiveWords(String S, String[] words) {
        RLE orig = new RLE(S);
        String origKey = orig.key;
        List<Integer> origList = orig.values;
        int count = 0;

        for( String word: words) {
            RLE wordRLE = new RLE(word);
            boolean isStretch = true;

            if ( !origKey.equals(wordRLE.key) ) {
                continue;
            }

            for (int loop=0; loop < wordRLE.values.size(); loop++) {
                int c1 = origList.get(loop);
                int c2 = wordRLE.values.get(loop);

                if ( c1 < c2 || (c1 < 3 && c1 != c2) ) {
                    isStretch = false;
                    break;
                }
            }

            if (isStretch) {
                count += 1;
            }
        }

        return count;
    }

    class RLE {
        String key;
        List<Integer> values = new LinkedList<>();

        public RLE(String str) {
            int count = 0;
            char pChar = 0;
            StringBuilder sb = new StringBuilder();

            for(char ch: str.toCharArray()) {
                if (ch == pChar) {
                    count += 1;
                } else {
                    sb.append(ch);
                    if(count != 0) {
                        values.add(count);
                    }
                    count = 1;
                }
                pChar = ch;
            }

            if(count != 0) {
                values.add(count);
            }

            key = sb.toString();
        }
    }
}
