package edu.codifyme.leetcode.interview.google.arraynstring;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * MEDIUM:
 * https://leetcode.com/problems/meeting-rooms-ii
 *
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the
 * minimum number of conference rooms required.
 *
 * Example 1:
 * Input: [[0, 30],[5, 10],[15, 20]]
 * Output: 2
 *
 * Example 2:
 * Input: [[7,10],[2,4]]
 * Output: 1
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method
 * signature.
 *
 * Approach 1: Priority Queues
 * We can't really process the given meetings in any random order. The most basic way of processing the meetings is in
 * increasing order of their start times and this is the order we will follow. After all if you're an IT guy, you should
 * allocate a room to the meeting that is scheduled for 9 a.m. in the morning before you worry about the 5 p.m. meeting,
 * right?
 * Let's do a dry run of an example problem with sample meeting times and see what our algorithm should be able to do
 * efficiently.
 * We will consider the following meeting times for our example (1, 10), (2, 7), (3, 19), (8, 12), (10, 20), (11, 30).
 * The first part of the tuple is the start time for the meeting and the second value represents the ending time. We are
 * considering the meetings in a sorted order of their start times. The first diagram depicts the first three meetings
 * where each of them requires a new room because of collisions.
 *
 * Algorithm
 * Sort the given meetings by their start time.
 * Initialize a new min-heap and add the first meeting's ending time to the heap. We simply need to keep track of the
 * ending times as that tells us when a meeting room will get free.
 * For every meeting room check if the minimum element of the heap i.e. the room at the top of the heap is free or not.
 * If the room is free, then we extract the topmost element and add it back with the ending time of the current meeting
 * we are processing.
 * If not, then we allocate a new room and add it to the heap.
 * After processing all the meetings, the size of the heap will tell us the number of rooms allocated. This will be the
 * minimum number of rooms needed to accommodate all the meetings.
 *
 * Complexity Analysis
 * Time Complexity: O(NlogN).
 * Space Complexity: O(N)
 *
 * Approach 2: Chronological Ordering
 * Intuition
 *
 * The meeting timings given to us define a chronological order of events throughout the day. We are given the start and
 * end timings for the meetings which can help us define this ordering.
 * Arranging the meetings according to their start times helps us know the natural order of meetings throughout the day.
 * However, simply knowing when a meeting starts doesn't tell us much about its duration.
 * We also need the meetings sorted by their ending times because an ending event essentially tells us that there must
 * have been a corresponding starting event and more importantly, an ending event tell us that a previously occupied room
 * has now become free.
 * A meeting is defined by its start and end times. However, for this specific algorithm, we need to treat the start and
 * end times individually. This might not make sense right away because a meeting is defined by its start and end times.
 * If we separate the two and treat them individually, then the identity of a meeting goes away. This is fine because:
 * When we encounter an ending event, that means that some meeting that started earlier has ended now. We are not really
 * concerned with which meeting has ended. All we need is that some meeting ended thus making a room available.
 * Let us consider the same example as we did in the last approach. We have the following meetings to be scheduled:
 * (1, 10), (2, 7), (3, 19), (8, 12), (10, 20), (11, 30). As before, the first diagram show us that the first three
 * meetings are colliding with each other and they have to be allocated separate rooms.
 */
public class MeetingRoomsII {
    public int minMeetingRooms(int[][] intervals) {

        // Check for the base case. If there are no intervals, return 0
        if (intervals.length == 0) {
            return 0;
        }

        // Min heap
        PriorityQueue<Integer> allocator =
                new PriorityQueue<Integer>(
                        intervals.length,
                        new Comparator<Integer>() {
                            public int compare(Integer a, Integer b) {
                                return a - b;
                            }
                        });

        // Sort the intervals by start time
        Arrays.sort(
                intervals,
                new Comparator<int[]>() {
                    public int compare(final int[] a, final int[] b) {
                        return a[0] - b[0];
                    }
                });

        // Add the first meeting
        allocator.add(intervals[0][1]);

        // Iterate over remaining intervals
        for (int i = 1; i < intervals.length; i++) {

            // If the room due to free up the earliest is free, assign that room to this meeting.
            if (intervals[i][0] >= allocator.peek()) {
                allocator.poll();
            }

            // If a new room is to be assigned, then also we add to the heap,
            // If an old room is allocated, then also we have to add to the heap with updated end time.
            allocator.add(intervals[i][1]);
        }

        // The size of the heap tells us the minimum rooms required for all the meetings.
        return allocator.size();
    }
}
