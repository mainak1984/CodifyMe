package edu.codifyme.hackerrank.interviewpreparation.array;

/**
 * MEDIUM: https://www.hackerrank.com/challenges/new-year-chaos/problem
 *
 * It's New Year's Day and everyone's in line for the Wonderland rollercoaster ride! There are a number of people
 * queued up, and each person wears a sticker indicating their initial position in the queue. Initial positions increment
 * by  from  at the front of the line to  at the back.
 * Any person in the queue can bribe the person directly in front of them to swap positions. If two people swap positions,
 * they still wear the same sticker denoting their original places in line. One person can bribe at most two others.
 * For example, if  and  bribes , the queue will look like this: .
 * Fascinated by this chaotic queue, you decide you must know the minimum number of bribes that took place to get the
 * queue into its current state!
 *
 * Function Description:
 * Complete the function minimumBribes in the editor below. It must print an integer representing the minimum number of
 * bribes necessary, or Too chaotic if the line configuration is not possible.
 * minimumBribes has the following parameter(s):
 * q: an array of integers
 *
 * Input Format:
 * The first line contains an integer , the number of test cases.
 * Each of the next  pairs of lines are as follows:
 * - The first line contains an integer , the number of people in the queue
 * - The second line has  space-separated integers describing the final state of the queue.
 *
 * Output Format
 * Print an integer denoting the minimum number of bribes needed to get the queue into its final state. Print Too chaotic if the state is invalid, i.e. it requires a person to have bribed more than  people.
 *
 * Sample Input
 * 2
 * 5
 * 2 1 5 3 4
 * 5
 * 2 5 1 3 4
 * Sample Output
 * 3
 * Too chaotic
 *
 * Solution:
 * Start from end and check if the person is moved from original position
 * If moved, bring this person back to its position and swap others up
 * total no. of swaps are no. of bribes
 */
public class NewYearChaos {
//    void calc(vector<int> q)
//    {
//        int ans = 0;
//        for (int i = q.size() - 1; i >= 0; i--) {
//            if (q[i] - (i + 1) > 2) {
//                cout << "Too chaotic" << endl;
//                return;
//            }
//            for (int j = max(0, q[i] - 2); j < i; j++)
//                if (q[j] > q[i]) ans++;
//        }
//        cout << ans << endl;
//    }

    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {
        int[] bribe = new int[q.length];

        // run for all the elements until q.length and rebalance / upbubble when a bribe is found
        for ( int loopVar = q.length; loopVar > 0; loopVar-- ) {
            if ( q[loopVar - 1] == loopVar ) {
                continue;
            }

            // one step away
            if ( loopVar > 1 && q[loopVar-2] == loopVar ) {
                // swipe the current element
                int temp = q[loopVar-1];
                q[loopVar-1] = q[loopVar-2];
                q[loopVar-2] = temp;
                bribe[q[loopVar-1] - 1] += 1;
                continue;
            }

            // two step away
            if ( loopVar > 2 && q[loopVar-3] == loopVar ) {
                // swipe the current element by 2 step
                int temp = q[loopVar-1];
                q[loopVar-1] = q[loopVar-3];
                q[loopVar-3] = q[loopVar-2];
                q[loopVar-2] = temp;
                bribe[q[loopVar-1] - 1] += 2;
                continue;
            }

            // error case
            System.out.println("Too chaotic");
            return;
        }

        // Go over the bribe array and get the total count
        int sum = 0;
        for ( int i: bribe) {
            if ( i > 2 ) {
                System.out.println("Too chaotic");
                return;
            } else {
                sum += i;
            }
        }

        System.out.println(sum);
    }
}
