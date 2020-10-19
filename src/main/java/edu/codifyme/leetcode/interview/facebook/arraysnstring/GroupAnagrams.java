package edu.codifyme.leetcode.interview.facebook.arraysnstring;

import java.util.*;

/**
 * 49. Group Anagrams
 * MEDIUM: https://leetcode.com/problems/group-anagrams/
 *
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all
 * the original letters exactly once.
 *
 * Example 1:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * Example 2:
 * Input: strs = [""]
 * Output: [[""]]
 *
 * Example 3:
 * Input: strs = ["a"]
 * Output: [["a"]]
 *
 * Constraints:
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lower-case English letters.
 *
 * Approach:
 * 1. calculate hashcode using word frequency and use a hashmap to group strings
 * 2. sort the word to generate the hashcode and use a hashmap to group the strings
 * 3. use first 26 prime numbers and use their multiplication to generate hashcode followed by grouping
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> dict = new HashMap<>();
        List<List<String>> result = new LinkedList<>();

        for (String str: strs) {
            String rep = getSmallestAnagramCode(str);
            dict.putIfAbsent(rep, new LinkedList<String>());
            List<String> list = dict.get(rep);
            list.add(str);
        }

        for (List<String> list: dict.values()) {
            result.add(list);
        }

        return result;
    }

    String getSmallestAnagramCode(String src) {
        char[] arr = new char[26];

        for (char ch: src.toCharArray()) {
            arr[ch - 'a'] += 1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (arr[i] != 0) {
                sb.append('a'+i);
                sb.append(arr[i]);
            }
        }

        return sb.toString();
    }

    // Best: Calc hashcode using prime numbers
//    private final int[] PRIME_NUMBER = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101};
//    public List<List<String>> groupAnagrams(String[] strs) {
//        List<List<String>> list = new ArrayList<>();
//        Map<Long,Integer> map = new HashMap<>();
//        for(int i = 0; i< strs.length; i++) {
//            String str = strs[i];
//            long sum = 1;
//            for(char ch : str.toCharArray()) {
//                sum *= PRIME_NUMBER[ch - 'a'];
//            }
//            if(map.containsKey(sum)) {
//                list.get(map.get(sum)).add(str);
//            } else {
//                map.put(sum,list.size());
//                List<String> li = new ArrayList<>();
//                li.add(str);
//                list.add(li);
//            }
//        }
//
//        return list;
//    }
}
