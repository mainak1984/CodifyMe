package edu.codifyme.leetcode.mocktest.google.dp;

import java.util.Arrays;

/**
 * 279. Perfect Squares
 * MEDIUM: https://leetcode.com/problems/perfect-squares/
 *
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum
 * to n.
 *
 * Example 1:
 *
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * Example 2:
 *
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 *
 * Approach 2: Dynamic Programming
 * Intuition
 *
 * The reason why it failed with the brute-force approach is simply because we re-calculate the sub-solutions over and
 * over again. However, the formula that we derived before is still valuable. All we need is a better way to implement
 * the formula.
 *
 * numSquares(n)=min(numSquares(n-k) + 1)∀k∈{square numbers}
 *
 * One might notice that, the problem is similar to the Fibonacci number problem, judging from the formula. And like
 * Fibonacci number, we have several more efficient ways to calculate the solution, other than the simple recursion.
 * One of the ideas to solve the stack overflow issue in recursion is to apply the Dynamic Programming (DP) technique,
 * which is built upon the idea of reusing the results of intermediate sub-solutions to calculate the final solution.
 * To calculate the value of numSquares(n), first we need to calculate all the values before nn, i.e. {numSquares}(n-k)
 * forall{k} in{square numbers}numSquares(n−k)∀k∈{square numbers}. If we have already kept the solution for the number
 * n-k in somewhere, we then would not need to resort to the recursive calculation which prevents the stack overflow.
 *
 * Algorithm
 * Based on the above intuition, we could implement the DP solution in the following steps.
 *
 * As for almost all DP solutions, we first create an array dp of one or multiple dimensions to hold the values of
 * intermediate sub-solutions, as well as the final solution which is usually the last element in the array. Note that,
 * we create a fictional element dp[0]=0 to simplify the logic, which helps in the case that the remainder (n-k) happens
 * to be a square number. As an additional preparation step, we pre-calculate a list of square numbers (i.e.
 * square_nums) that is less than the given number n. As the main step, we then loop from the number 1 to n, to calculate
 * the solution for each number i (i.e. numSquares(i)). At each iteration, we keep the result of numSquares(i) in dp[i],
 * while reusing the previous results stored in the array.
 * At the end of the loop, we then return the last element in the array as the result of the solution.
 * In the graph below, we illustrate how to calculate the results of numSquares(4) and numSquares(5) which correspond to
 * the values in dp[4] and dp[5].
 *
 * Here are some sample implementations. In particular, the Python solution took ~3500 ms, which was faster than ~50%
 * submissions at the time.
 *
 * Note: the following python solution works for Python2 only. For some unknown reason, it takes significantly longer
 * time for Python3 to run the same code.
 *
 * Complexity
 * Time complexity: O(n * sqrt{n}). In main step, we have a nested loop, where the outer loop is of nn iterations and in
 * the inner loop it takes at maximum sqrt{n} iterations.
 *
 * Space Complexity: O(n). We keep all the intermediate sub-solutions in the array dp[].
 *
 *
 * Approach 4: Greedy + BFS (Breadth-First Search)
 * Intuition
 *
 * As we mentioned in the complexity analysis in the above Greedy approach, the trace of the call stack forms a N-ary
 * tree where each node represents a call to the is_divided_by(n, count) function. Based on the above intuition, again
 * we could reformulate the original problem as follows:
 *
 * Given a N-ary tree, where each node represents a remainder of the number n subtracting a combination of square numbers,
 * our task is to find a node in the tree, which should meet two conditions: 1). the value of the node (i.e. the remainder)
 * should be a square number as well. 2). the distance between the node and the root should be minimal among all nodes
 * that meet the condition (1).
 *
 * Here is an example how the tree would look like.
 *
 * In the previous Approach #3, due to the Greedy strategy that we perform the calls, we were actually constructing the
 * N-ary tree level-by-level from top to down. And the we were traversing it in a BFS (Breadth-First Search) manner. At
 * each level of the N-ary tree, we were enumerating the combinations that are of the same size.
 *
 * The order of traversing is of BFS, rather than DFS (Depth-First Search), is due to the fact that before exhausting all
 * the possibilities of decomposing a number n with a fixed amount of squares, we would not explore any potential
 * combination that needs more elements.
 *
 * Algorithm
 * Again, first of all, we prepare a list of square numbers (named square_nums) that are less than the given number n.
 * We then create a queue variable which would keep all the remainders to enumerate at each level.
 * In the main loop, we iterate over the queue variable. At each iteration, we check if the remainder is one of the
 * square numbers. If the remainder is not a square number, we subtract it with one of the square numbers to obtain a new
 * remainder and then add the new remainder to the next_queue for the iteration of the next level. We break out of the
 * loop once we encounter a remainder that is of a square number, which also means that we find the solution.
 * Note: in a typical BFS algorithm, the queue variable usually would be of array or list type. However, here we use the
 * set type, in order to eliminate the redundancy of remainders within the same level. As it turns out, this tiny trick
 * could even provide a 5 times speedup on running time.
 *
 * In the following graph, we illustrate the layout of the queue, on the example of numSquares(7).
 *
 * Complexity
 *
 * Time complexity: O(sqrt{n}^{h+1} - 1}{sqrt{n} - 1} ) = {O}(n^{2}})O(n) where h is the height of the N-ary tree.
 * One can see the detailed explanation on the previous Approach #3.
 *
 * Space complexity: Big(sqrt{n})^hn), which is also the maximal number of nodes that can appear at the level h. As
 * one can see, though we keep a list of square_nums, the main consumption of the space is the queue variable, which
 * keep track of the remainders to visit for a given level of N-ary tree.
 *
 * Approach 5: Mathematics
 * Intuition
 *
 * The problem can be solved with the mathematical theorems that have been proposed and proved over time. We will break
 * down the problem into several cases in this section.
 *
 * In 1770, Joseph Louis Lagrange proved a theorem, called Lagrange's four-square theorem, also known as Bachet's
 * conjecture, which states that every natural number can be represented as the sum of four integer squares:
 * p=a_{0}^{2}+a_{1}^{2}+a_{2}^{2}+a_{3}^{2}
 * where the four numbers a_{0},a_{1},a_{2},a_{3} are integers.
 *
 * For example, 3, 31 can be represented as the sum of four squares as follows:
 * 3=1^{2}+1^{2}+1^{2}+0^{2} \qquad 31=5^{2}+2^{2}+1^{2}+1^{2}
 *
 * Case 1). The Lagrange's four-square theorem sets the upper bound for the results of the problem, i.e. if the number n
 * cannot be decomposed into a fewer number of squares, at least it can be decomposed into the sum of 4 square numbers,
 * i.e. \text{numSquares}(n) \le 4numSquares(n)≤4.
 *
 * As one might notice in the above example, the number zero is also considered as a square number, so we can consider
 * that the number 3 can either be decomposed into 3 or 4 square numbers.
 *
 * However, Lagrange's four-square theorem does not tell us directly the least numbers of square to decompose a natural
 * number.
 *
 * Later, in 1797, Adrien-Marie Legendre completed the four-square theorem with his three-square theorem, by proving a
 * particular condition that a positive integer can be expressed as the sum of three squares:
 * n \ne 4^{k}(8m+7) \iff n = a_{0}^{2}+a_{1}^{2}+a_{2}^{2}
 *
 * where kk and mm are integers.
 *
 * Case 2). Unlike the four-square theorem, Adrien-Marie Legendre's three-square theorem gives us a necessary and
 * sufficient condition to check if the number can ONLY be decomposed into 4 squares, not fewer.
 *
 * It might be tricky to see the conclusion that we made in the case (2) from the three-square theorem. Let us elaborate
 * the deduction procedure a bit. First of all, the three-square theorem tells us that if the number n is of the form
 * n = 4^{k}(8m+7)n=4
 * k
 *  (8m+7), then the number n cannot be decomposed into the sum of 3 numbers of squares. Further, we can also assert that
 *  the number n cannot be decomposed into the sum of two squares, neither the number itself is a square. Because
 *  suppose the number n can be decomposed as n = a_{0}^{2}+a_{1}^{2}n=a
 * ​
 *  , then by adding the square number zero into the expression, i.e. n = a_{0}^{2}+a_{1}^{2} + 0^2n=a
 *
 *  , we obtain the conclusion that the number n can be decomposed into 3 squares, which is contradicted to the
 *  three-square theorem. Therefore, together with the four-square theorem, we can assert that if the number does not
 *  meet the condition of the three-square theorem, it can ONLY be decomposed into the sum of 4 squares, not fewer.
 *
 * If the number meets the condition of the three-square theorem, we know that if can be decomposed into 3 squares. But
 * what we don't know is that whether the number can be decomposed into fewer squares, i.e. one or two squares. So before
 * we attribute the number to the bottom case (three-square theorem), here are the two cases remained to check, namely:
 *
 * Case 3.1). if the number is a square number itself, which is easy to check e.g. n == int(sqrt(n)) ^ 2.
 * Case 3.2). if the number can be decomposed into the sum of two squares. Unfortunately, there is no mathematical weapon
 * that can help us to check this case in one shot. We need to resort to the enumeration approach.
 *
 * Algorithm
 * One can literally follow the above cases to implement the solution.
 * First, we check if the number n is of the form n = 4^{k}(8m+7)n=4k
 *  (8m+7), if so we return 4 directly.
 * Otherwise, we further check if the number is of a square number itself or the number can be decomposed the sum of two
 * squares. In the bottom case, the number can be decomposed into the sum of 3 squares, though we can also consider
 * it decomposable by 4 squares by adding zero according to the four-square theorem. But we are asked to find the least
 * number of squares. We give some sample implementations here. The solution is inspired from the posts of TCarmic and
 * StefanPochmann in the Discussion forum.
 *
 */
public class PerfectSquares {
    public int numSquares(int n) {
        int dp[] = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        // bottom case
        dp[0] = 0;

        // pre-calculate the square numbers.
        int max_square_index = (int) Math.sqrt(n) + 1;
        int square_nums[] = new int[max_square_index];
        for (int i = 1; i < max_square_index; ++i) {
            square_nums[i] = i * i;
        }

        for (int i = 1; i <= n; ++i) {
            for (int s = 1; s < max_square_index; ++s) {
                if (i < square_nums[s])
                    break;
                dp[i] = Math.min(dp[i], dp[i - square_nums[s]] + 1);
            }
        }
        return dp[n];
    }
}
