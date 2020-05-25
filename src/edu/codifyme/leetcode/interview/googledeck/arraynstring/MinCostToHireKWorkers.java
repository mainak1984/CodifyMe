package edu.codifyme.leetcode.interview.googledeck.arraynstring;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * HARD:
 * https://leetcode.com/explore/interview/card/google/59/array-and-strings/3061/
 *
 * There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].
 * Now we want to hire exactly K workers to form a paid group.  When hiring a group of K workers, we must pay them according to the following rules:
 * Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
 * Every worker in the paid group must be paid at least their minimum wage expectation.
 * Return the least amount of money needed to form a paid group satisfying the above conditions.
 *
 * Example 1:
 * Input: quality = [10,20,5], wage = [70,50,30], K = 2
 * Output: 105.00000
 * Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
 *
 * Example 2:
 * Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
 * Output: 30.66667
 * Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately.
 *
 * Note:
 * 1 <= K <= N <= 10000, where N = quality.length = wage.length
 * 1 <= quality[i] <= 10000
 * 1 <= wage[i] <= 10000
 * Answers within 10^-5 of the correct answer will be considered correct.
 *
 * Approach 2: Heap
 * As in Approach #1, at least one worker is paid their minimum wage expectation.
 * Additionally, every worker has some minimum ratio of dollars to quality that they demand. For example,
 * if wage[0] = 100 and quality[0] = 20, then the ratio for worker 0 is 5.0.
 * The key insight is to iterate over the ratio. Let's say we hire workers with a ratio R or lower. Then, we would want
 * to know the K workers with the lowest quality, and the sum of that quality. We can use a heap to maintain these
 * variables.
 */
public class MinCostToHireKWorkers {
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        Worker[] workers = new Worker[quality.length];

        if ( 0 == quality.length || 0 == wage.length || 0 == K) {
            return (double)0;
        }

        for (int loop=0; loop < quality.length; loop++) {
            workers[loop] = new Worker(quality[loop], wage[loop]);
        }
        Arrays.sort(workers);

        PriorityQueue<Integer> workerQ = new PriorityQueue<>(K);
        int sumQ = 0;
        double result = Double.MAX_VALUE;

        for (Worker w: workers) {
            workerQ.add(-w.quality);
            sumQ += w.quality;

            if (workerQ.size() > K) {
                sumQ += workerQ.poll();
            }
            if (workerQ.size() == K) {
                result = Math.min(result, sumQ * w.ratio);
            }
        }

        return result;
    }

    class Worker implements Comparable<Worker> {
        int quality;
        int wage;
        double ratio;

        public Worker(int quality, int wage) {
            this.quality = quality;
            this.wage = wage;
            this.ratio = (double) wage/quality;
        }

        public int compareTo(Worker w) {
            return Double.compare(this.ratio, w.ratio);
        }
    }
}
