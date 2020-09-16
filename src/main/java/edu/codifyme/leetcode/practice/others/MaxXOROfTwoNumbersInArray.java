package edu.codifyme.leetcode.practice.others;

import java.util.HashMap;

/**
 * 421. Maximum XOR of Two Numbers in an Array
 * MEDIUM: https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/
 *
 * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.
 * Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
 * Could you do this in O(n) runtime?
 *
 * Example:
 * Input: [3, 10, 5, 25, 2, 8]
 * Output: 28
 * Explanation: The maximum result is 5 ^ 25 = 28.
 *
 * Approach:
 * Bitwise Trie: What is it and How to Construct
 *
 * The standard way is to use Bitwise Trie. It's a special type of Trie, which is used to store binary prefixes in an
 * efficient way. There are plenty of real-life examples of bitwise trie usage
 *
 * Maximum XOR of a Given Number with All Numbers in Trie:
 * Now the Trie is constructed, so let's find the maximum XOR of a given number with all numbers that have been already
 * inserted into Bitwise Trie.
 * To maximize XOR, the strategy is to choose the opposite bit at each step whenever it's possible.
 * The implementation is also pretty simple:
 * Try to go down to the opposite bit at each step if it's possible. Add 1-bit at the end of current XOR.
 * If not, just go down to the same bit. Add 0-bit at the end of current XOR.
 *
 * Algorithm:
 * To summarise, now one could
 * - Insert a number into Bitwise Trie.
 * - Find maximum XOR of a given number with all numbers that have been inserted so far.
 *
 * That's all one needs to solve the initial problem:
 * - Convert all numbers to the binary form.
 * - Add the numbers into Trie one by one and compute the maximum XOR of a number to add with all previously inserted.
 * Update maximum XOR at each step.
 * - Return max_xor.
 */
public class MaxXOROfTwoNumbersInArray {
    public int findMaximumXOR(int[] nums) {
        // Compute length L of max number in a binary representation
        int maxNum = nums[0];
        for(int num : nums) maxNum = Math.max(maxNum, num);
        int L = (Integer.toBinaryString(maxNum)).length();

        // zero left-padding to ensure L bits for each number
        int n = nums.length, bitmask = 1 << L;
        String [] strNums = new String[n];
        for(int i = 0; i < n; ++i) {
            strNums[i] = Integer.toBinaryString(bitmask | nums[i]).substring(1);
        }

        TrieNode trie = new TrieNode();
        int maxXor = 0;
        for (String num : strNums) {
            TrieNode node = trie, xorNode = trie;
            int currXor = 0;
            for (Character bit : num.toCharArray()) {
                // insert new number in trie
                if (node.children.containsKey(bit)) {
                    node = node.children.get(bit);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(bit, newNode);
                    node = newNode;
                }

                // compute max xor of that new number
                // with all previously inserted
                Character toggledBit = bit == '1' ? '0' : '1';
                if (xorNode.children.containsKey(toggledBit)) {
                    currXor = (currXor << 1) | 1;
                    xorNode = xorNode.children.get(toggledBit);
                } else {
                    currXor = currXor << 1;
                    xorNode = xorNode.children.get(bit);
                }
            }
            maxXor = Math.max(maxXor, currXor);
        }

        return maxXor;
    }

    class TrieNode {
        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        public TrieNode() {}
    }
}
