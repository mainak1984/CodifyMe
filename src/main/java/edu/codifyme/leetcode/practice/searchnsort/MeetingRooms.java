package edu.codifyme.leetcode.practice.searchnsort;

import java.util.Arrays;

/**
 * 252. Meeting Rooms
 * EASY: https://leetcode.com/problems/meeting-rooms/
 *
 * Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all
 * meetings.
 *
 * Example 1:
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: false
 *
 * Example 2:
 * Input: intervals = [[7,10],[2,4]]
 * Output: true
 *
 * Example 3:
 * Input: intervals = [[10,16],[5,10],[15,20]]
 * Output: false
 *
 * Constraints:
 * 0 <= intervals.length <= 104
 * intervals.length == 2
 * 0 <= starti < endi <= 106
 *
 * Approach: Sort
 * The idea here is to sort the meetings by starting time. Then, go through the meetings one by one and make sure that
 * each meeting ends before the next one starts.
 */
public class MeetingRooms {
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return true;
        }

        Arrays.sort(intervals, (a, b) -> a[0] == b[0]? a[1]-b[1]: a[0]-b[0]);

        int end = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            if (end > intervals[i][0]) {
                return false;
            }
            end = intervals[i][1];
        }

        return true;
    }
}
