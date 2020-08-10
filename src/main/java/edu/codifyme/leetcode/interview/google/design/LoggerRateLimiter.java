package edu.codifyme.leetcode.interview.google.design;

import java.util.HashMap;
import java.util.Map;

/**
 * 359. Logger Rate Limiter
 * EASY: https://leetcode.com/problems/logger-rate-limiter
 *
 * Design a logger system that receive stream of messages along with its timestamps, each message should be printed if
 * and only if it is not printed in the last 10 seconds.
 * Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given
 * timestamp, otherwise returns false.
 * It is possible that several messages arrive roughly at the same time.
 *
 * Example:
 * Logger logger = new Logger();
 *
 * // logging string "foo" at timestamp 1
 * logger.shouldPrintMessage(1, "foo"); returns true;
 *
 * // logging string "bar" at timestamp 2
 * logger.shouldPrintMessage(2,"bar"); returns true;
 *
 * // logging string "foo" at timestamp 3
 * logger.shouldPrintMessage(3,"foo"); returns false;
 *
 * // logging string "bar" at timestamp 8
 * logger.shouldPrintMessage(8,"bar"); returns false;
 *
 * // logging string "foo" at timestamp 10
 * logger.shouldPrintMessage(10,"foo"); returns false;
 *
 * // logging string "foo" at timestamp 11
 * logger.shouldPrintMessage(11,"foo"); returns true;
 *
 * Alternate Approach: Helpful for generic Rate limitter
 * We could interpret that the input messages are in chronological order, i.e. the timestamps of the messages are
 * monotonically increasing, though not strictly. This constraint is critical, since it would simplify the task, as
 * one will see in the following solutions.
 * As a first solution, let us build a solution intuitively following the tasks described in the problem.
 * We keep the incoming messages in a queue. In addition, to accelerate the check of duplicates, we use a set data
 * structure to index the messages.
 * As one see can from the above example where the number indicates the timestamp of each message, the arrival of the
 * message with the timestamp 18 would invalidate both the messages with the timestamp of 5 and 7 which go beyond the
 * time window of 10 seconds.
 *
 * Algorithm
 * First of all, we use a queue as a sort of sliding window to keep all the printable messages in certain time frame
 * (10 seconds).
 * At the arrival of each incoming message, it comes with a timestamp. This timestamp implies the evolution of the
 * sliding windows. Therefore, we should first invalidate those expired messages in our queue.
 * Since the queue and set data structures should be in sync with each other, we would also remove those expired
 * messages from our message set.
 * After the updates of our message queue and set, we then simply check if there is any duplicate for the new incoming
 * message. If not, we add the message to the queue as well as the set.
 */
public class LoggerRateLimiter {
    Map<String, Integer> store;

    /** Initialize your data structure here. */
    public LoggerRateLimiter() {
        store = new HashMap<>();
    }

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        Integer old = store.get(message);

        if (null == old || timestamp - old >= 10) {
            store.put(message, timestamp);
            return true;
        } else {
            return false;
        }
    }
}
