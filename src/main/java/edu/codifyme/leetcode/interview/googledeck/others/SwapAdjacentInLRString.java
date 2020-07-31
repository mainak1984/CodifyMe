package edu.codifyme.leetcode.interview.googledeck.others;

/**
 * MEDIUM:
 * https://leetcode.com/problems/swap-adjacent-in-lr-string
 *
 * In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one
 * occurrence of "XL" with "LX", or replacing one occurrence of "RX" with "XR". Given the starting string start and the
 * ending string end, return True if and only if there exists a sequence of moves to transform one string to the other.
 *
 * Example:
 * Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
 * Output: True
 * Explanation:
 * We can transform start to end following these steps:
 * RXXLRXRXL ->
 * XRXLRXRXL ->
 * XRLXRXRXL ->
 * XRLXXRRXL ->
 * XRLXXRRLX
 *
 * Constraints:
 * 1 <= len(start) == len(end) <= 10000.
 * Both start and end will only consist of characters in {'L', 'R', 'X'}.
 */
public class SwapAdjacentInLRString {
    public boolean canTransform(String start, String end) {
        int len = start.length();
        int src = 0, dst = 0;

        while (src < len && dst < len) {
            while (src < len && start.charAt(src) == 'X') {
                src++;
            }
            while (dst < len && end.charAt(dst) == 'X' ) {
                dst++;
            }

            if ( src < len && dst >= len || src >= len && dst < len ) {
                return false; // one has reached end and another has not
            }

            if ( src < len && dst < len ) {
                if ( start.charAt(src) != end.charAt(dst) || start.charAt(src) == 'L' &&
                        src < dst || start.charAt(src) == 'R' && src > dst ) {
                    return false;
                }
            }
            src++;
            dst++;
        }

        if ( src < dst ) {
            while (src < len && start.charAt(src) == 'X') {
                src++;
            }
            if ( src < len ) {
                return false; // some more element remaining
            }
        } else if ( dst < src ) {
            while (dst < len && end.charAt(dst) == 'X' ) {
                dst++;
            }
            if ( dst < len ) {
                return false; // some more element remaining
            }
        }
        return true;
    }
}
