package edu.codifyme.leetcode.practice.array;

/**
 * 484. Find Permutation
 * MEDIUM: https://leetcode.com/problems/find-permutation/
 *
 * By now, you are given a secret signature consisting of character 'D' and 'I'. 'D' represents a decreasing relationship
 * between two numbers, 'I' represents an increasing relationship between two numbers. And our secret signature was
 * constructed by a special integer array, which contains uniquely all the different number from 1 to n (n is the length
 * of the secret signature plus 1). For example, the secret signature "DI" can be constructed by array [2,1,3] or [3,1,2],
 * but won't be constructed by array [3,2,4] or [2,1,3,4], which are both illegal constructing special string that can't
 * represent the "DI" secret signature.
 *
 * On the other hand, now your job is to find the lexicographically smallest permutation of [1, 2, ... n] could refer to
 * the given secret signature in the input.
 *
 * Example 1:
 * Input: "I"
 * Output: [1,2]
 * Explanation: [1,2] is the only legal initial special string can construct secret signature "I", where the number 1
 * and 2 construct an increasing relationship.
 *
 * Example 2:
 * Input: "DI"
 * Output: [2,1,3]
 * Explanation: Both [2,1,3] and [3,1,2] can construct the secret signature "DI",
 * but since we want to find the one with the smallest lexicographical permutation, you need to output [2,1,3]
 *
 * Note:
 * The input string will only contain the character 'D' and 'I'.
 * The length of input string is a positive integer and will not exceed 10,000
 *
 * Approach:
 * Intuition:
 * Say, the given pattern ss is "DDIIIID". This corresponds to n=8. Thus, the min permutation possible will be
 * [1, 2, 3, 4, 5, 6, 7, 8]. Now, to satisfy the first two "DD" pattern, we can observe that the best course of action
 * to generate the smallest arrangement will be to rearrange only 1, 2, 3, because these are the smallest numbers that
 * can be placed at the three most significant positions to generate the smallest arrangement satisfying the given
 * pattern till now, leading to the formation of [3, 2, 1, 4, 5, 6, 7, 8] till now. We can note that placing any number
 * larger than 3 at any of these positions will lead to the generation of a lexicographically larger arrangement as
 * discussed above.
 *
 * Approach 1:
 * To perform the operations discussed above, we can make use of a stack. We can start considering the numbers i from
 * 1 to n. For every current number, whenever we find a D in the pattern s, we just push that number onto the stack.
 * This is done so that, later on, when we find the next I, we can pop off these numbers from the stack leading to the
 * formation of a reversed (descending) subpattern of those numbers corresponding to the D's in ss(as discussed above).
 *
 * Approach 2:
 * In order to reverse the subsections of the min array, as discussed in the last approach, we can also start by
 * initializing the resultant arrangement res with the min array i.e. by filling with elements (1,n) in ascending order.
 * Then, while traversing the pattern ss, we can keep a track of the starting and ending indices in res corresponding to
 * the D's in the pattern ss, and reverse the portions of the sub-arrays in res corresponding to these indices. The
 * reasoning behind this remains the same as discussed in the last approach.
 *
 * Approach 3:
 * Instead of initializing the res array once with ascending numbers, we can save one iteration over res if we fill it
 * on the fly. To do this, we can keep on filling the numbers in ascending order in res for I's found in the pattern s.
 * Whenever we find a D in the pattern ss, we can store the current position(counting from 1) being filled in the res
 * array in a pointer j. Now, whenever we find the first I following this last consecutive set of D's, say at the ith
 * position(counting from 1) in res, we know, that the elements from jth position to the ith position in res need to be
 * filled with the numbers from j to i in reverse order. Thus, we can fill the numbers in res array starting from the
 * jth position, with i being the number filled at that position and continue the filling in descending order till
 * reaching the ith position. In this way, we can generate the required arrangement without initializing res.
 */
public class FindPermutation {
    public int[] findPermutation(String s) {
        int[] res = new int[s.length() + 1];

        int start = 0;
        int end = 0;
        int count = 1;

        while (end < s.length()) {
            if (s.charAt(end) == 'I') {
                res[start] = count;
                start++;
            } else {
                start = end;
                while (end < s.length() && s.charAt(end) == 'D') {
                    end++;
                    count++;
                }
                int tempCount = count;

                while (start <= end) {
                    res[start++] = count--;
                }

                if (end == s.length()) {
                    return res;
                }
                count = tempCount;
            }
            end++;
            count++;
        }

        res[s.length()] = count;
        return res;
    }

    // Stack:
//    public int[] findPermutation(String s) {
//        int[] res = new int[s.length() + 1];
//        Stack< Integer > stack = new Stack < > ();
//        int j = 0;
//        for (int i = 1; i <= s.length(); i++) {
//            if (s.charAt(i - 1) == 'I') {
//                stack.push(i);
//                while (!stack.isEmpty())
//                    res[j++] = stack.pop();
//            } else
//                stack.push(i);
//        }
//        stack.push(s.length() + 1);
//        while (!stack.isEmpty())
//            res[j++] = stack.pop();
//        return res;
//    }

    // Reverse:
//    public int[] findPermutation(String s) {
//        int[] res = new int[s.length() + 1];
//        for (int i = 0; i < res.length; i++)
//            res[i] = i + 1;
//        int i = 1;
//        while (i <= s.length()) {
//            int j = i;
//            while (i <= s.length() && s.charAt(i - 1) == 'D')
//                i++;
//            reverse(res, j - 1, i);
//            i++;
//        }
//        return res;
//    }
//    public void reverse(int[] a, int start, int end) {
//        for (int i = 0; i < (end - start) / 2; i++) {
//            int temp = a[i + start];
//            a[i + start] = a[end - i - 1];
//            a[end - i - 1] = temp;
//        }
//    }

    // TWO Pointer
//    public int[] findPermutation(String s) {
//        int[] res = new int[s.length() + 1];
//        res[0]=1;
//        int i = 1;
//        while (i <= s.length()) {
//            res[i]=i+1;
//            int j = i;
//            if(s.charAt(i-1)=='D')
//            {
//                while (i <= s.length() && s.charAt(i - 1) == 'D')
//                    i++;
//                for (int k = j - 1, c = i; k <= i - 1; k++, c--) {
//                    res[k] = c;
//                }
//            }
//            else
//                i++;
//        }
//        return res;
//    }
}
