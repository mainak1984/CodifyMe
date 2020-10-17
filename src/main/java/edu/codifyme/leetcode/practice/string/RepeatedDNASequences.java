package edu.codifyme.leetcode.practice.string;

import java.util.*;

/**
 * 187. Repeated DNA Sequences
 * MEDIUM: https://leetcode.com/problems/repeated-dna-sequences/
 *
 * All DNA is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T', for example: "ACGAATTCCG".
 * When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 *
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 *
 * Example 1:
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * Output: ["AAAAACCCCC","CCCCCAAAAA"]
 *
 * Example 2:
 * Input: s = "AAAAAAAAAAAAA"
 * Output: ["AAAAAAAAAA"]
 *
 * Constraints:
 * 0 <= s.length <= 105
 * s[i] is 'A', 'C', 'G', or 'T'.
 *
 * Approach:
 * compute rolling hash from the previous hash value. Dump results in a set to maintain unique list
 *
 * Approach 2:
 * The idea is to slice over the string and to compute the bitmask of the sequence in the sliding window, both in a
 * constant time.
 *
 * As for Rabin-Karp, let's start from conversion of string to 2-bits integer array:
 * A -> 0 = 00_2, C -> 1 = 01_2, G -> 2 = 10_2, T -> 3 = 11_2
 * GAAAAACCCCCAAAAACCCCCCAAAAAGGGTTT -> 200000111110000011111100000222333.
 * Time to compute bitmask for the first sequence of length L: 2000001111. Each digit in the sequence (0, 1, 2 or 3)
 * takes not more than 2 bits:
 * 0 = 00_2, 1 = 01_2, 2 = 10_2, 3 = 11_20=00
 *
 * Hence the bitmask could be computed in the loop:
 *  - Do left shift to free the last two bits: bitmask <<= 2
 *  - Save current digit from 2000001111 in these last two bits: bitmask |= nums[i]
 *
 * Now let's consider the slice GAAAAACCCCC -> AAAAACCCCC. For int arrays that means 20000011111 -> 0000011111, to remove
 * leading 2 and to add trailing 1.
 *
 * To add trailing 1 is simple, the same idea as just above:
 *  - Do left shift to free the last two bits: bitmask <<= 2
 *  - Save 1 into these last two bits: bitmask |= 1
 *
 * Now the problem is to remove two leading bits, which contain 2. In other words, the problem is to set 2L-bit and
 * (2L + 1)-bit to zero.
 * Let's use bitwise trick to unset n-th bit: bitmask &= ~(1 << n).
 *
 * This trick is very simple:
 *  - 1 << n is to set n-th bit equal to 1.
 *  - ~(1 << n) is to set n-th bit equal to 0, and all lower bits to 1.
 *  - bitmask &= ~(1 << n) is to set n-th bit of bitmask equal to 0.
 *
 * Straightforward trick usage is to unset first 2L-bit and then (2L + 1)-bit:
 * bitmask &= ~(1 << 2 * L) & ~(1 << (2 * L + 1). That could be simplified as bitmask &= ~(3 << 2 * L):
 *  - 3 = (11)_2, and hence 3 << 2 * L would set 2L-bit and (2L + 1)-bit equal to 1.
 *  - ~(3 << 2 * L) would set 2L-bit and (2L + 1)-bit equal to 0, and all lower bits to 1.
 *  - bitmask &= ~(3 << 2 * L) would set 2L-bit and (2L + 1)-bit of bitmask equal to 0.
 *
 * Voila, window slice and bitmask recomputation are both done in a constant time.
 */
public class RepeatedDNASequences {
    //Approach 1:
     public List<String> findRepeatedDnaSequences(String s) {
         Set<String> result = new HashSet<>();
         Set<String> allWords = new HashSet<>();

         if (s == null || s.length() < 10) {
             return new LinkedList<String>();
         }

         StringBuilder sb = new StringBuilder(s.substring(0, 10));
         allWords.add(sb.toString());

         for (int i = 10; i < s.length(); i++) {
             String newWord = sb.append(s.charAt(i)).deleteCharAt(0).toString();
             if (!allWords.add(newWord)) {
                 result.add(newWord);
             }
         }

         return new LinkedList<String>(result);
     }

     // Approach 2: Bitwise mask
//    public List<String> findRepeatedDnaSequences(String s) {
//        int L = 10, n = s.length();
//        if (n <= L) return new ArrayList();
//
//        // rolling hash parameters: base a
//        int a = 4, aL = (int)Math.pow(a, L);
//
//        // convert string to array of integers
//        Map<Character, Integer> toInt = new
//                HashMap() {{put('A', 0); put('C', 1); put('G', 2); put('T', 3); }};
//        int[] nums = new int[n];
//        for(int i = 0; i < n; ++i) nums[i] = toInt.get(s.charAt(i));
//
//        int bitmask = 0;
//        Set<Integer> seen = new HashSet();
//        Set<String> output = new HashSet();
//        // iterate over all sequences of length L
//        for (int start = 0; start < n - L + 1; ++start) {
//            // compute bitmask of the current sequence in O(1) time
//            if (start != 0) {
//                // left shift to free the last 2 bit
//                bitmask <<= 2;
//                // add a new 2-bits number in the last two bits
//                bitmask |= nums[start + L - 1];
//                // unset first two bits: 2L-bit and (2L + 1)-bit
//                bitmask &= ~(3 << 2 * L);
//            }
//            // compute hash of the first sequence in O(L) time
//            else {
//                for(int i = 0; i < L; ++i) {
//                    bitmask <<= 2;
//                    bitmask |= nums[i];
//                }
//            }
//            // update output and hashset of seen sequences
//            if (seen.contains(bitmask)) output.add(s.substring(start, start + L));
//            seen.add(bitmask);
//        }
//        return new ArrayList<String>(output);
//    }
}
