package edu.codifyme.geeksforgeeks.recursion;

import java.util.LinkedList;
import java.util.Queue;

/**
 * MEDIUM: Print all Jumping Numbers smaller than or equal to a given value:
 * https://www.geeksforgeeks.org/print-all-jumping-numbers-smaller-than-or-equal-to-a-given-value/
 *
 * A number is called as a Jumping Number if all adjacent digits in it differ by 1. The difference between ‘9’ and ‘0’ is not considered as 1.
 * All single digit numbers are considered as Jumping Numbers. For example 7, 8987 and 4343456 are Jumping numbers but 796 and 89098 are not.
 *
 * Given a positive number x, print all Jumping Numbers smaller than or equal to x. The numbers can be printed in any order.
 *
 * Example:
 *
 * Input: x = 20
 * Output:  0 1 2 3 4 5 6 7 8 9 10 12
 *
 * Input: x = 105
 * Output:  0 1 2 3 4 5 6 7 8 9 10 12
 *          21 23 32 34 43 45 54 56 65
 *          67 76 78 87 89 98 101
 *
 * Note: Order of output doesn't matter,
 * i.e. numbers can be printed in any order
 *
 * Solution:
 * Use a Graph where 1->2->3->...->9 are first set of numbers. From each number move to next by adding
 * 10*num + (1 plus minus). Check limit at every step
 */
public class JumpingNumbers {
    // Prints all jumping numbers smaller than or equal to x starting
    // with 'num'. It mainly does BFS starting from 'num'.
    public void bfs(int x, int num)
    {
        // Create a queue and enqueue 'i' to it
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(num);

        // Do BFS starting from i
        while (!q.isEmpty()) {
            num = q.peek();
            q.poll();
            if (num <= x) {
                System.out.print(num + " ");
                int last_digit = num % 10;

                // If last digit is 0, append next digit only
                if (last_digit == 0) {
                    q.add((num * 10) + (last_digit + 1));
                }

                // If last digit is 9, append previous digit only
                else if (last_digit == 9) {
                    q.add((num * 10) + (last_digit - 1));
                }

                // If last digit is neighter 0 nor 9, append both
                // previous and next digits
                else {
                    q.add((num * 10) + (last_digit - 1));
                    q.add((num * 10) + (last_digit + 1));
                }
            }
        }
    }

    // Prints all jumping numbers smaller than or equal to
    // a positive number x
    public void printJumping(int x)
    {
        System.out.print("0 ");

        for (int i = 1; i <= 9 && i <= x; i++) {
            this.bfs(x, i);
        }
    }
}
