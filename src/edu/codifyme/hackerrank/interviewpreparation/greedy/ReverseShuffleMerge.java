package edu.codifyme.hackerrank.interviewpreparation.greedy;

/**
 * HARD:
 * https://www.hackerrank.com/challenges/reverse-shuffle-merge/problem
 *
 * The problem states that you are given a string s which is the result of the following operations on some string A.
 * s = merge(reverse(A) ), shuffle(A))
 * The operations are defined like this:
 * Merge(A, B): merges two strings into one while keeping the relative order of the characters of each string.
 * Reverse(A): Reverses the string A.
 * Shuffle(A): Shuffles the characters of A, or leaves them intact.
 * We need to find the lexicographically smallest A. Which means A with the lowest alphabetical order.
 * Solution
 * The key to this is that since Merge keeps the relative order of both strings. Our string A is hidden in-between the
 * repeating characters in reverse order.
 *
 * Sample Input 0
 * eggegg
 * Sample Output 0
 * egg
 *
 * Sample Input 1
 * abcdefgabcdefg
 * Sample Output 1
 * agfedcb
 *
 * Sample Input 2
 * aeiouuoiea
 * Sample Output 2
 * aeiou
 *
 * Pseudocode:
 * First we need to count the occurrences of the characters.
 * Then we make a copy of the occurrences this call it the usable characters.
 * Then we half the occurrences and call this the required characters
 * Then we make an empty count for our solution.
 * And and empty string for our solution
 * From right to left in the string s
 * — If the character is required
 * — — we backtrack if we can and improve our solution
 * — — we add the character to the solution
 * — — we add it to the solution count
 * — — we reduce form the usable count
 * — else
 * — — we ignore and reduce form the usable count
 * return our solution
 */
public class ReverseShuffleMerge {
    public static void main(String entry) {
        int[] frequency = new int[26];
        int uniqueElement = 0;
        for(int i =0; i < entry.length(); i++){
            char currentChar = entry.charAt(i);
            if(frequency[currentChar - 'a'] == 0){
                uniqueElement++;
            }
            frequency[currentChar - 'a']++;
        }
        //Halve the frequency of each character to find the frequency the characters in the answer
        for(int i =0; i<26; i++){
            frequency[i] = frequency[i]/2;
        }
        int lastIndex = entry.length();
        StringBuilder result = new StringBuilder(lastIndex/2);
        while(uniqueElement > 0){
            //find the smallest window which have elemnents of frequency as subsequence
            int[] tempFrequency = frequency.clone();
            int tempUniqueElement = uniqueElement;
            int windowSize = 0;

            for(int i=0; i < entry.length(); i++){
                char currentChar = entry.charAt(i);
                tempFrequency[currentChar - 'a']--;
                if(tempFrequency[currentChar - 'a'] == 0) tempUniqueElement--;
                if(tempUniqueElement == 0) break;
                windowSize++;
            }
            Character minCharacter = null;
            for(int i= lastIndex - 1; i >= windowSize; i--){
                if(frequency[entry.charAt(i) - 'a'] == 0){
                    continue;
                }
                if(minCharacter == null || minCharacter > entry.charAt(i)){
                    minCharacter = entry.charAt(i);
                    lastIndex = i;
                }
            }
            result.append(minCharacter);
            frequency[minCharacter - 'a']--;
            if(frequency[minCharacter - 'a'] == 0){
                uniqueElement--;
            }
        }
        System.out.println(result.toString());
    }
}
