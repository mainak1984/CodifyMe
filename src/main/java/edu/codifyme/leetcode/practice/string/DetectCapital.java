package edu.codifyme.leetcode.practice.string;

/**
 * 520. Detect Capital
 * EASY: https://leetcode.com/problems/detect-capital/
 *
 * Given a word, you need to judge whether the usage of capitals in it is right or not.
 *
 * We define the usage of capitals in a word to be right when one of the following cases holds:
 *
 * All letters in this word are capitals, like "USA".
 * All letters in this word are not capitals, like "leetcode".
 * Only the first letter in this word is capital, like "Google".
 * Otherwise, we define that this word doesn't use capitals in a right way.
 *
 * Example 1:
 * Input: "USA"
 * Output: True
 *
 * Example 2:
 * Input: "FlaG"
 * Output: False
 */
public class DetectCapital {
    public boolean detectCapitalUse(String word) {
        char[] chars = word.toCharArray();

        int i = 1;
        if (chars[0] <= 'Z') {
            if (i < chars.length && chars[i] >= 'a') {
                while (i < chars.length && chars[i] >= 'a') {
                    i++;
                }
            } else {
                while (i < chars.length && chars[i] <= 'Z') {
                    i++;
                }
            }
        } else {
            while (i < chars.length && chars[i] >= 'a') {
                i++;
            }
        }

        if (i < chars.length) {
            return false;
        }

        return true;
    }

    // public boolean detectCapitalUse(String word) {
    //     int latest=-1;
    //     int count=0;
    //     for(int i=0;i<word.length();i++){
    //         if(word.charAt(i)-'Z'<=0){
    //             count++;
    //             if(i-latest!=1){
    //                 return false;
    //             }
    //             else{
    //                 latest=i;
    //             }
    //         }
    //         //System.out.println(latest);
    //     }
    //     if(count>1&&latest==word.length()-1)
    //         return true;
    //     else if(count==1||count==0)
    //         return true;
    //     else return false;
    // }
}
