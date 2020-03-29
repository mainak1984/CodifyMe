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
 * Solution:
 * Start from end and check if the person is moved from original position
 * If moved, bring this person back to its position and swap others up
 * total no. of swaps are no. of bribes
 */
public class NewYearChaos {
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
