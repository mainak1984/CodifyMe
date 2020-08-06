package edu.codifyme.leetcode.interview.google.others;

import java.util.Map;
import java.util.TreeMap;

/**
 * MEDIUM:
 * https://leetcode.com/problems/my-calendar-ii
 *
 * Implement a MyCalendarTwo class to store your events. A new event can be added if adding the event will not cause a
 * triple booking. Your class will have one method, book(int start, int end). Formally, this represents a booking on the
 * half open interval [start, end), the range of real numbers x such that start <= x < end.
 * A triple booking happens when three events have some non-empty intersection (ie., there is some time that is common
 * to all 3 events.)
 * For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully
 * without causing a triple booking. Otherwise, return false and do not add the event to the calendar.
 * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 *
 * Example 1:
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(50, 60); // returns true
 * MyCalendar.book(10, 40); // returns true
 * MyCalendar.book(5, 15); // returns false
 * MyCalendar.book(5, 10); // returns true
 * MyCalendar.book(25, 55); // returns true
 * Explanation:
 * The first two events can be booked.  The third event can be double booked.
 * The fourth event (5, 15) can't be booked, because it would result in a triple booking.
 * The fifth event (5, 10) can be booked, as it does not use time 10 which is already double booked.
 * The sixth event (25, 55) can be booked, as the time in [25, 40) will be double booked with the third event;
 * the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.
 *
 * Note:
 * The number of calls to MyCalendar.book per test case will be at most 1000.
 * In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].
 *
 * Approach 1: Boundary Count
 * When booking a new event [start, end), count delta[start]++ and delta[end]--. When processing the values of delta in
 * sorted order of their keys, the running sum active is the number of events open at that time. If the sum is 3 or more,
 * that time is (at least) triple booked.
 * Time Complexity: O(N^2), where NN is the number of events booked. For each new event, we traverse delta in O(N) time.
 * Space Complexity: O(N), the size of delta.
 *
 * Approach 2: (Better)
 * Maintain a list of bookings and a list of double bookings. When booking a new event [start, end), if it conflicts
 * with a double booking, it will have a triple booking and be invalid. Otherwise, parts that overlap the calendar will
 * be a double booking. Use TreeMap for landing to specific overlap element.
 */
public class MyCalendarII {

    // APPROACH: 1
    //     TreeMap<Integer, Integer> overlap;

//     public MyCalendarII() {
//         overlap = new TreeMap<>();
//     }

//     public boolean book(int start, int end) {
//         overlap.put(start, overlap.getOrDefault(start, 0)+1);
//         overlap.put(end, overlap.getOrDefault(end, 0)-1);
//         int runningValue = 0;

//         for (Integer value: overlap.values()) {
//             runningValue += value;

//             if (runningValue > 2) {
//                 overlap.put(start, overlap.getOrDefault(start, 0)-1);
//                 overlap.put(end, overlap.getOrDefault(end, 0)+1);

//                 if(overlap.get(start) == 0) {
//                     overlap.remove(start);
//                 }

//                 return false;
//             }
//         }
//         return true;
//     }


    // APPROACH 2:
    TreeMap<Integer, Integer> overlap;
    TreeMap<Integer, Integer> intervals;
    public MyCalendarII() {
        overlap = new TreeMap<>();
        intervals = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Map.Entry<Integer, Integer> entry = overlap.lowerEntry(end);
        if(entry != null && entry.getValue() > start){
            return false;
        }

        entry = intervals.lowerEntry(end);
        while(entry != null){
            if(entry.getValue() <= start){
                break;
            }
            intervals.remove(entry.getKey());
            overlap.put(Math.max(start, entry.getKey()), Math.min(end, entry.getValue()));
            start = Math.min(start, entry.getKey());
            end = Math.max(end, entry.getValue());

            entry = intervals.lowerEntry(end);
        }

        intervals.put(start, end);
        return true;
    }
}
