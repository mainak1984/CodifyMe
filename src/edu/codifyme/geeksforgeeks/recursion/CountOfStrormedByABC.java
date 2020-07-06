package edu.codifyme.geeksforgeeks.recursion;

/**
 * EASY:
 * https://www.geeksforgeeks.org/count-strings-can-formed-using-b-c-given-constraints/
 *
 * Given a length n, count the number of strings of length n that can be made using ‘a’, ‘b’ and ‘c’ with at-most one
 * ‘b’ and two ‘c’s allowed.
 *
 * Examples :
 *
 * Input : n = 3
 * Output : 19
 * Below strings follow given constraints:
 * aaa aab aac aba abc aca acb acc baa
 * bac bca bcc caa cab cac cba cbc cca ccb
 *
 * Input  : n = 4
 * Output : 39
 */
public class CountOfStrormedByABC {
    // n is total number of characters. bCount and cCount are counts of 'b' and 'c' respectively.
    static int countStr(int n,
                        int bCount,
                        int cCount)
    {
        // Base cases
        if (bCount < 0 || cCount < 0) return 0;
        if (n == 0) return 1;
        if (bCount == 0 && cCount == 0) return 1;

        // Three cases, we choose, a or b or c In all three cases n decreases by 1.
        int res = countStr(n - 1, bCount, cCount);
        res += countStr(n - 1, bCount - 1, cCount);
        res += countStr(n - 1, bCount, cCount - 1);

        return res;
    }

    // + Memoization as per need
}
