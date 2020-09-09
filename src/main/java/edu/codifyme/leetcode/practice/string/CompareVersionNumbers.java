package edu.codifyme.leetcode.practice.string;

/**
 * 165. Compare Version Numbers
 * MEDIUM: https://leetcode.com/problems/compare-version-numbers/
 *
 * Compare two version numbers version1 and version2.
 * If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.
 * You may assume that the version strings are non-empty and contain only digits and the . character.
 * The . character does not represent a decimal point and is used to separate number sequences.
 * For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of
 * the second first-level revision.
 * You may assume the default revision number for each level of a version number to be 0. For example, version number
 * 3.4 has a revision number of 3 and 4 for its first and second level revision number. Its third and fourth level
 * revision number are both 0.
 *
 * Example 1:
 * Input: version1 = "0.1", version2 = "1.1"
 * Output: -1
 *
 * Example 2:
 * Input: version1 = "1.0.1", version2 = "1"
 * Output: 1
 *
 * Example 3:
 * Input: version1 = "7.5.2.4", version2 = "7.5.3"
 * Output: -1
 *
 * Example 4:
 * Input: version1 = "1.01", version2 = "1.001"
 * Output: 0
 * Explanation: Ignoring leading zeroes, both “01” and “001" represent the same number “1”
 *
 * Example 5:
 * Input: version1 = "1.0", version2 = "1.0.0"
 * Output: 0
 * Explanation: The first version number does not have a third level revision number, which means its third level
 * revision number is default to "0"
 *
 * Note:
 * Version strings are composed of numeric strings separated by dots . and this numeric strings may have leading zeroes.
 * Version strings do not start or end with dots, and they will not be two consecutive dots.
 *
 * Approach 1: Split + Parse, Two Pass
 *
 * Split both strings by dot character into two arrays.
 * Iterate over the longest array and compare chunks one by one. If one of the arrays is over, virtually add as many
 * zeros as needed to continue the comparison with the longer array.
 * If two chunks are not equal, return 1 or -1.
 * If we're here, the versions are equal. Return 0.
 *
 *
 * Approach 2: Two Pointers, One Pass
 *
 * Rather than splitting the string all at once with the split() function, we could also split the string on the fly,
 * through which we only need to iterate through the revisions once.
 * The idea is that we split the string chunk by chunk, i.e. each trunk represents a revision in the version number.
 * The moment we retrieve a trunk from each string, we then compare them.
 * In this way, one could move along both strings in parallel, retrieve and compare corresponding chunks. Once both
 * strings are parsed, the comparison is done as well.
 *
 * As a result, the process can be done in a single pass.
 */
public class CompareVersionNumbers {
    public int compareVersion(String version1, String version2) {
        String[] v1parts = version1.split("\\.");
        int v1Len = v1parts.length;
        String[] v2parts = version2.split("\\.");
        int v2Len = v2parts.length;

        int effectiveLen = v1Len > v2Len? v1Len: v2Len;
        int v1, v2;

        for (int i = 0; i < effectiveLen; i++) {
            v1 = i < v1Len ? Integer.parseInt(v1parts[i]): 0;
            v2 = i < v2Len? Integer.parseInt(v2parts[i]): 0;

            if (v1 > v2) {
                return 1;
            } else if (v1 < v2) {
                return -1;
            }
        }

        return 0;
    }

    // Approach 2:
//    public Pair<Integer, Integer> getNextChunk(String version, int n, int p) {
//        // if pointer is set to the end of string
//        // return 0
//        if (p > n - 1) {
//            return new Pair(0, p);
//        }
//        // find the end of chunk
//        int i, pEnd = p;
//        while (pEnd < n && version.charAt(pEnd) != '.') {
//            ++pEnd;
//        }
//        // retrieve the chunk
//        if (pEnd != n - 1) {
//            i = Integer.parseInt(version.substring(p, pEnd));
//        } else {
//            i = Integer.parseInt(version.substring(p, n));
//        }
//        // find the beginning of next chunk
//        p = pEnd + 1;
//
//        return new Pair(i, p);
//    }
//
//    public int compareVersion(String version1, String version2) {
//        int p1 = 0, p2 = 0;
//        int n1 = version1.length(), n2 = version2.length();
//
//        // compare versions
//        int i1, i2;
//        Pair<Integer, Integer> pair;
//        while (p1 < n1 || p2 < n2) {
//            pair = getNextChunk(version1, n1, p1);
//            i1 = pair.getKey();
//            p1 = pair.getValue();
//
//            pair = getNextChunk(version2, n2, p2);
//            i2 = pair.getKey();
//            p2 = pair.getValue();
//            if (i1 != i2) {
//                return i1 > i2 ? 1 : -1;
//            }
//        }
//        // the versions are equal
//        return 0;
//    }
}
