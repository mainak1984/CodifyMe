package edu.codifyme.leetcode.practice.others;

/**
 * 470. Implement Rand10() Using Rand7()
 * https://leetcode.com/problems/implement-rand10-using-rand7/
 *
 * Given a function rand7 which generates a uniform random integer in the range 1 to 7, write a function rand10 which
 * generates a uniform random integer in the range 1 to 10.
 *
 * Do NOT use system's Math.random().
 *
 * Example 1:
 * Input: 1
 * Output: [7]
 * Example 2:
 *
 * Input: 2
 * Output: [8,4]
 * Example 3:
 *
 * Input: 3
 * Output: [8,1,10]
 *
 * Note:
 * rand7 is predefined.
 * Each testcase has one argument: n, the number of times that rand10 is called.
 *
 * Follow up:
 * What is the expected value for the number of calls to rand7() function?
 * Could you minimize the number of calls to rand7()?
 *
 * Approach 1: Rejection Sampling
 * Intuition
 *
 * What if you could generate a random integer in the range 1 to 49? How would you generate a random integer in the
 * range of 1 to 10? What would you do if the generated number is in the desired range? What if it is not?
 *
 * Algorithm
 * This solution is based upon Rejection Sampling. The main idea is when you generate a number in the desired range,
 * output that number immediately. If the number is out of the desired range, reject it and re-sample again. As each
 * number in the desired range has the same probability of being chosen, a uniform distribution is produced.
 */
public class Rand10UsingRand7 {
    public int rand10() {
        int rand40 = 40;
        while (rand40 >= 40) {
            rand40 = (rand7() - 1) * 7 + rand7() - 1;
        }
        return rand40 % 10 + 1;
    }

    public int rand7() {
        return (int)(Math.random() % 7) + 1;
    }
}
