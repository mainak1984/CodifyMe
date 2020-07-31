package edu.codifyme.hackerrank.interviewpreparation.stack;

import java.util.Stack;

/**
 * MEDIUM: MIN MAX Riddle
 * https://www.hackerrank.com/challenges/min-max-riddle/problem
 *
 * Given an integer array of size , find the maximum of the minimum(s) of every window size in the array.
 * The window size varies from  to .
 *
 * For example, given , consider window sizes of  through . Windows of size  are .
 * The maximum value of the minimum values of these windows is . Windows of size  are  and their minima are .
 * The maximum of these values is . Continue this process through window size  to finally consider the entire array.
 * All of the answers are .
 *
 * Solution: https://www.hackerrank.com/challenges/min-max-riddle/editorial
 * Build 2 arrays one for all i
 * left[i] indicates position of min element that i. right[i] indicates position of min element position on right side
 * Use a stack, pop all elements in stack which is bigger than i, if stack not empty set stack top as left[i], push i
 * similarly build right i
 *
 * So, for a length of right[i] - left[] - 1, i is min. go through full range of i and check all such length
 * for an existing length value, store the max value for that length
 * if any length is missing, compare with next element
 */
public class MInMaxRiddle {
    public void solution(int[] a) {
        int n = a.length;

        Stack<Integer> s = new Stack<>();
        int[] left = new int[n];
        int[] right = new int[n];
        int[] ans = new int[n+1];
        int i, len;

        for(i=0;i<n;++i) {
            left[i]=-1;
            right[i]=n;
        }

        for(i=0;i<n;++i) {
            while(!s.empty() && a[s.peek()] >= a[i]) {
                s.pop();
            }
            if(!s.empty()) {
                left[i]=s.peek();
            }
            s.push(i);
        }

        while(!s.empty()) { s.pop(); }

        for(i=n-1;i>=0;--i) {
            while(!s.empty() && a[s.peek()] >= a[i]) {
                s.pop();
            }
            if(!s.empty()) {
                right[i]=s.peek();
            }
            s.push(i);
        }

        for(i=0;i<n;++i) {
            len = right[i]-left[i]-1;
            ans[len]=Math.max(ans[len], a[i]);
        }

        for(i=n-1;i>=1;--i) {
            ans[i]=Math.max(ans[i], ans[i+1]);
        }

        for(i=1;i<=n;++i) {
            System.out.print(" "+i);
        }
    }
}
