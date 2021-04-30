package edu.codifyme.leetcode.practice.searchnsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 1229. Meeting Scheduler
 * MEDIUM: https://leetcode.com/problems/meeting-scheduler/
 *
 * Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration, return the
 * earliest time slot that works for both of them and is of duration duration.
 *
 * If there is no common time slot that satisfies the requirements, return an empty array.
 *
 * The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to
 * end.
 *
 * It is guaranteed that no two availability slots of the same person intersect with each other. That is, for any two
 * time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.
 *
 * Example 1:
 * Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
 * Output: [60,68]
 *
 * Example 2:
 * Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
 * Output: []
 *
 * Constraints:
 * 1 <= slots1.length, slots2.length <= 104
 * slots1[i].length, slots2[i].length == 2
 * slots1[i][0] < slots1[i][1]
 * slots2[i][0] < slots2[i][1]
 * 0 <= slots1[i][j], slots2[i][j] <= 109
 * 1 <= duration <= 106
 *
 * Approach:
 * Put both the slots together, sort by increasing order and find conflicting slots (they are bound to be from
 * different person as single person has non overlapping slots). Find the first conflict which is more than duration
 *
 * Algorithm:
 * Initialize a heap timeslots and push time slots that last longer than duration into it.
 * Iterate until there's only one time slot remaining in timeslots:
 *     Pop the first time slot [start1, end1] from timeslots.
 *     Retrieve the next time slot [start2, end2], which is the current top element in timeslots.
 *     If we find end1 >= start2 + duration, because start1 > start2, the common slot is longer than duration and we can
 *  return it.
 * If we don't find the common slot that is longer than duration, return an empty array.
 *
 * Complexity:
 * Time complexity: O((M+N)log(M+N)), when MM is the length of slots1 and NN is the length of slots2.
 * Space complexity: O(M+N). This is because we store all M+NM+N time slots in a heap.
 */
public class MeetingScheduler {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        PriorityQueue<int[]> timeslots = new PriorityQueue<>((slot1, slot2) -> slot1[0] - slot2[0]);

        for (int[] slot: slots1) {
            if (slot[1] - slot[0] >= duration) timeslots.offer(slot);
        }
        for (int[] slot: slots2) {
            if (slot[1] - slot[0] >= duration) timeslots.offer(slot);
        }

        while (timeslots.size() > 1) {
            int[] slot1 = timeslots.poll();
            int[] slot2 = timeslots.peek();
            if (slot1[1] >= slot2[0] + duration) {
                return new ArrayList<Integer>(Arrays.asList(slot2[0], slot2[0] + duration));
            }
        }
        return new ArrayList<Integer>();
    }
}
