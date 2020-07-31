package edu.codifyme.hackerrank.interviewpreparation.search;

/**
 * HARD:
 * https://www.hackerrank.com/challenges/making-candies/problem
 *
 * Karl loves playing games on social networking sites. His current favorite is CandyMaker, where the goal is to make
 * 0candies.
 *
 * Karl just started a level in which he must accumulate n candies starting with m machines and w workers. In a single
 * pass, he can make m*w candies. After each pass, he can decide whether to spend some of his candies to buy more
 * machines or hire more workers. Buying a machine or hiring a worker costs p units, and there is no limit to the
 * number of machines he can own or workers he can employ.
 *
 * Karl wants to minimize the number of passes to obtain the required number of candies at the end of a day. Determine
 * that number of passes.
 *
 * For example, Karl starts with m=1 machine and w=2 workers. The cost to purchase or hire, p=1 and he needs to
 * accumulate 60 candies. He executes the following strategy:
 *
 * Make m*w = 1*2 = 2 candies. Purchase two machines.
 * Make 3*2 = 6 candies. Purchase 3 machines and hire 3 workers.
 * Make 6*5 = 30 candies. Retain all 30 candies.
 * Make 6*5 = 30 candies. With yesterday's production, Karl has 60 candies.
 * It took 4 passes to make enough candies.
 *
 * Function Description:
 * Complete the minimumPasses function in the editor below. The function must return a long integer representing the
 * minimum number of passes required.
 *
 * minimumPasses has the following parameter(s):
 *
 * m: long integer, the starting number of machines
 * w: long integer, the starting number of workers
 * p: long integer, the cost of a new hire or a new machine
 * n: long integer, the number of candies to produce
 *
 * Approach:
 * Two important observations to make when solving this problem are:
 * > Always buy machines and hire workers as early as possible. If we do so, then the subsequent operations will lead
 * to a larger product everytime and hence more candies.
 * > To maximize the number of candies made during each round, the numbers of workers and machines should be either
 * equal or as close to each other as possible. Hence, we must always invest in whichever resource we have less of.
 *
 * if we have money, go for purchase
 */
public class MakingCandies {
    static long minimumPasses(long m, long w, long p, long n) {
        long passes = 0;
        long candy = 0;
        long run = Long.MAX_VALUE;
        long step;

        while (candy < n) {
            step = (m > Long.MAX_VALUE / w) ? 0 : (p - candy) / (m * w);

            if (step <= 0) {
                long mw = candy / p;

                if (m >= w + mw) {
                    w += mw;
                } else if (w >= m + mw) {
                    m += mw;
                } else {
                    long total = m + w + mw;
                    m = total / 2;
                    w = total - m;
                }

                candy %= p;
                step = 1;
            }

            passes += step;

            if (step * m > Long.MAX_VALUE / w) {
                candy = Long.MAX_VALUE;
            } else {
                candy += step * m * w;
                run = Math.min(run, (long)(passes + Math.ceil((n - candy) / (m * w * 1.0))));
            }
        }

        return Math.min(passes, run);
    }

//    def minimumPasses(m, w, p, n):
//        days = 0
//        candies = 0
//        answer = math.ceil(n / (m * w))
//
//        while days < answer:
//            if p > candies:
//                daysNeeded = math.ceil((p - candies) / (m * w))
//                candies += daysNeeded * m * w
//                days += daysNeeded
//
//            diff = abs(m - w)
//            available = candies // p
//            purchased = min(diff, available)
//
//            if m < w:
//                m += purchased
//            else:
//                w += purchased
//
//            rest = available - purchased
//            m += rest // 2
//            w += rest - rest // 2
//            candies -= available * p
//
//            candies += m * w
//            days += 1
//
//            remainingCandies = max(n - candies, 0)
//            answer = min(answer, days + math.ceil(remainingCandies / (m * w)))
//
//    return answer
}
