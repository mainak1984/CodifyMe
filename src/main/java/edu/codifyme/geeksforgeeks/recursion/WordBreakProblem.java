package edu.codifyme.geeksforgeeks.recursion;

/**
 * MEDIUM: Word Break Problem using Backtracking
 * https://www.geeksforgeeks.org/word-break-problem-using-backtracking/
 *
 * Given a valid sentence without any spaces between the words and a dictionary of valid English words, find all
 * possible ways to break the sentence in individual dictionary words.
 *
 * Example
 *
 * Consider the following dictionary
 * { i, like, sam, sung, samsung, mobile, ice,
 *   cream, icecream, man, go, mango}
 *
 * Input: "ilikesamsungmobile"
 * Output: i like sam sung mobile
 *         i like samsung mobile
 *
 * Input: "ilikeicecreamandmango"
 * Output: i like ice cream and man go
 *         i like ice cream and mango
 *         i like icecream and man go
 *         i like icecream and mango
 *
 * Solution:
 * We start scanning the sentence from left. As we find a valid word, we need to check whether the rest of the sentence
 * can make valid words or not. Because in some situations the first found word from left side can leave a remaining
 * portion which is not further separable. So in that case we should come back and leave the current found word and keep
 * on searching for the next word. And this process is recursive because to find out whether the right portion is
 * separable or not, we need the same logic. So we will use recursion and backtracking to solve this problem. To keep
 * track of the found words we will use a stack. Whenever the right portion of the string does not make valid words, we
 * pop the top string from stack and continue finding.
 */
public class WordBreakProblem {
    // Prints all possible word breaks of given string
    void wordBreak(String str) {
        // last argument is prefix
        wordBreakUtil(str, str.length(), "");
    }

    // result store the current prefix with spaces between words
    void wordBreakUtil(String str, int n, String result) {
        //Process all prefixes one by one
        for (int i=1; i<=n; i++) {
            //extract substring from 0 to i in prefix
            String prefix = str.substring(0, i);

            // if dictionary conatins this prefix, then we check for remaining string. Otherwise we ignore this prefix
            // (there is no else for this if) and try next
            if (dictionaryContains(prefix)) {
                // if no more elements are there, print it
                if (i == n) {
                    // add this element to previous prefix
                    result += prefix;
                    System.out.println(result); //print result
                    return;
                }
                wordBreakUtil(str.substring(i, n), n-i,result + prefix + " ");
            }
        }      //end for
    }//end function

    /* A utility function to check whether a word is present in dictionary or not.  An array of strings is used for
    dictionary. Using array of strings for dictionary is definitely not a good idea. We have used for simplicity of the
    program*/
    boolean dictionaryContains(String word)
    {
        String dictionary[] = {"mobile","samsung","sam","sung", "man","mango", "icecream","and", "go","i","love","ice",
                "cream"};
        int n = dictionary.length;
        for (int i = 0; i < n; i++)
            if (dictionary[i].equals(word))
                return true;
        return false;
    }
}
