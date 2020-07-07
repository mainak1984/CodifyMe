package edu.codifyme.geeksforgeeks.treengraph;

/**
 * MEDIUM: The Celebrity Problem
 * https://www.geeksforgeeks.org/the-celebrity-problem/
 *
 * In a party of N people, only one person is known to everyone. Such a person may be present in the party, if yes, (s)he doesn’t know anyone in the party. We can only ask questions like “does A know B? “. Find the stranger (celebrity) in the minimum number of questions.
 *
 * We can describe the problem input as an array of numbers/characters representing persons in the party. We also have a hypothetical function HaveAcquaintance(A, B) which returns true if A knows B, false otherwise. How can we solve the problem.
 * Examples:
 *
 * Input:
 * MATRIX = { {0, 0, 1, 0},
 *            {0, 0, 1, 0},
 *            {0, 0, 0, 0},
 *            {0, 0, 1, 0} }
 * Output:id = 2
 * Explanation: The person with ID 2 does not
 * know anyone but everyone knows him
 *
 * Input:
 * MATRIX = { {0, 0, 1, 0},
 *            {0, 0, 1, 0},
 *            {0, 1, 0, 0},
 *            {0, 0, 1, 0} }
 * Output: No celebrity
 * Explanation: There is no celebrity.
 *
 * Approach:
 * Use method of elimination when any of A->B knows another person
 * Run thru double loop to explore every relation
 *
 * Complexity: O(n) as in every comparison we are eliminating one possibility
 *
 * Details:
 * The idea is to use two pointers, one from start and one from the end. Assume the start person is A, and the end person
 * is B. If A knows B, then A must not be the celebrity. Else, B must not be the celebrity. At the end of the loop, only
 * one index will be left as a celebrity. Go through each person again and check whether this is the celebrity.
 * The Two Pointer approach can be used where two pointers can be assigned, one at the start and other at the end and the
 * elements can be compared and the search space can be reduced.
 * Algorithm :
 * Create two indices a and b, where a = 0 and b = n-1
 * Run a loop until a is less than b.
 * Check if a knows b, then a can’t be celebrity. so increment a, i.e. a++
 * Else b cannot be celebrity, so decrement b, i.e. b–
 * Assign a as the celebrity
 * Run a loop from 0 to n-1 and find the count of persons who knows the celebrity and the number of people whom the
 * celebrity knows. if the count of persons who knows the celebrity is n-1 and the count of people whom the celebrity
 * knows is 0 then return the id of celebrity else return -1.
 */
public class CelebrityProblem {
    // Person with 2 is celebrity
    static int MATRIX[][] = { { 0, 0, 1, 0 },
            { 0, 0, 1, 0 },
            { 0, 0, 0, 0 },
            { 0, 0, 1, 0 } };

    // Returns true if a knows
    // b, false otherwise
    static boolean knows(int a, int b)
    {
        boolean res = (MATRIX[a][b] == 1) ?
                true :
                false;
        return res;
    }

    // Returns -1 if celebrity
    // is not present. If present,
    // returns id (value from 0 to n-1).
    static int findCelebrity(int n)
    {
        // Initialize two pointers
        // as two corners
        int a = 0;
        int b = n - 1;

        // Keep moving while
        // the two pointers
        // don't become same.
        while (a < b)
        {
            if (knows(a, b))
                a++;
            else
                b--;
        }

        // Check if a is actually
        // a celebrity or not
        for (int i = 0; i < n; i++)
        {
            // If any person doesn't
            // know 'a' or 'a' doesn't
            // know any person, return -1
            if (i != a && (knows(a, i) ||
                    !knows(i, a)))
                return -1;
        }
        return a;
    }
}
