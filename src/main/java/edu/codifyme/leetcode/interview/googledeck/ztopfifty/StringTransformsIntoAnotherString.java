package edu.codifyme.leetcode.interview.googledeck.ztopfifty;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * HARD:
 * https://leetcode.com/problems/string-transforms-into-another-string/
 *
 * Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing zero
 * or more conversions.
 * In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.
 * Return true if and only if you can transform str1 into str2.
 *
 * Example 1:
 * Input: str1 = "aabcc", str2 = "ccdee"
 * Output: true
 * Explanation: Convert 'c' to 'e' then 'b' to 'd' then 'a' to 'c'. Note that the order of conversions matter.
 *
 * Example 2:
 * Input: str1 = "leetcode", str2 = "codeleet"
 * Output: false
 * Explanation: There is no way to transform str1 to str2.
 *
 * Note:
 * 1 <= str1.length == str2.length <= 10^4
 * Both str1 and str2 contain only lowercase English letters.
 *
 * Approach:
 * How to model this problem?
 *
 * Model it as a graph would be the most intuitive way. The edges in the graph represent the mapping relationships.
 * Realize that the out degree of a node can only be smaller or equal to 1, otherwise we should just return false
 * immediately! So the graph is just linked lists and using a hashmap will be sufficient to keep track of the edges.
 * Use a HashMap<Character, Character> to keep track of the edges.
 * Why only check if str2 has unused character?
 * See point 8 and 9 below
 *
 * Logical Thinking Step by Step:
 *
 * Model it as a graph
 * Realize that the out degree of a node can only be smaller or equal to 1, so the graph is just a linked list and using
 * a hashmap will be sufficient to keep track of the edges.
 * Note that size(key set) >= size(value set) because of point 2 above.
 * Realize that if there is no cycle in the graph, we can process the linked list from the end to the beginning, so just
 * return true if no cycle exists.
 * It would be tempting for us to conclude that result=hasCycle(graph) but this is incorrect!
 * Realize that having cycle doesn't necessarily mean the result is false. This is because cycles can be break by using
 * a temp node (temp node has zero out degree, in other words, temp node doesn't show up in the keyset of the hashmap or
 * temp character doesn't show up in str1)
 * It would be tempting for us to conclude that ressult = size(key set)<26 but this is incorrect!
 * Realize that size(key set)=26 doesn't necessarily mean the result is false. we still need to pay attention to a
 * spacial case when size(key set)=26 but size(value set)<26. In such case, multiple characters are mapped to the same
 * character. Given this, we can create an unused character
 * How to create an unused characte in str1r: let's say c1, c2 are both mapped to c3. we transform c1 to c2 first. After
 * this transformation, c1 disappears from the str1, so c1 is an unused character now!
 * We reach the conclusion that "if size(value set)<26, return true". As the final step, Let's prove that "if size(value
 * set)=26, return false". When size(value set)=26, it implies that size(key set)=26 too, so we have 26 one-to-one
 * mappings. If str1 is not the same as str2, cycle must exist, but there is no unused character to break the cycle.
 * So return false.
 *
 * Actual Algorithm:
 * Loop through str1 and str2, update hashmap with the mapping str1[i] -> str2[i]
 * If one-to-more mapping are found, return flase immediately
 * return size(value set) < 26 at the end
 */
public class StringTransformsIntoAnotherString {
    public boolean canConvert(String str1, String str2) {
        if (str1.equals(str2)) {
            return true;
        }
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < str1.length(); i++) {
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);
            if (map.containsKey(c1) && map.get(c1) != c2) {
                return false;
            }
            map.put(c1, c2);
        }
        return new HashSet<Character>(map.values()).size() < 26;
    }
}
