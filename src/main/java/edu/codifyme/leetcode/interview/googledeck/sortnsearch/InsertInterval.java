package edu.codifyme.leetcode.interview.googledeck.sortnsearch;

import java.util.ArrayList;
import java.util.List;

/**
 * MEDIUM:
 * https://leetcode.com/problems/insert-interval
 *
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Example 1:
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 *
 * Example 2:
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 */
public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> resultList = new ArrayList<>();

        for(int[] interval : intervals) {
            if(newInterval[0] > interval[1]) {
                resultList.add(interval);
            }
            else if(newInterval[1] < interval[0]) {
                resultList.add(newInterval);
                newInterval = interval;
            }
            else {
                int[] merged = new int[] {Math.min(newInterval[0], interval[0]), Math.max(newInterval[1], interval[1])};
                newInterval = merged;
            }
        }
        resultList.add(newInterval);

        return resultList.toArray(new int[resultList.size()][2]);
    }

//     public int[][] insert(int[][] intervals, int[] newInterval) {
//         List<int[]> listIntervals = Arrays.asList(intervals);

//         int sId = Arrays.binarySearch(intervals, newInterval, (a,b) -> Integer.compare(a[0], b[0]));
//         int eId = Arrays.binarySearch(intervals, newInterval, (a,b) -> Integer.compare(a[1], b[1]));

//         int startId = (sId < 0)? Math.abs((sId+1)): sId;
//         int endId = (eId < 0)? Math.abs((eId+1)): eId;

//         System.out.println("startId "+startId+", endId "+endId);

//         List<int[]> listInterval = new LinkedList<>();

//         for (int[] interval: intervals) {
//             listInterval.add(interval);
//         }

//         // Insert the newElement based on search position
//         if ( sId < 0 ) {
//             listInterval.add(startId, newInterval);
//         } else {
//             int[] temp = intervals[startId];
//             temp[1] = Math.max(temp[1], newInterval[1]);
//             intervals[startId] = temp;
//             listInterval.set(startId, newInterval);
//         }

//         for (int[] interval: listInterval) {
//             System.out.println(Arrays.toString(interval));
//         }

//         int[] elem = listInterval.get(startId);
//         int currentStart = elem[0];
//         int currentEnd = elem[1];
//         int currentIndex = startId;

//         for (int loop = startId > 0? startId-1: 0; (loop <= endId + 1) && (loop < intervals.length); loop++) {
//             System.out.println("Checking "+Arrays.toString(intervals[loop])+" with
//                              "+Arrays.toString(listInterval.get(startId)));
//             if ( intervals[loop][0] <= currentEnd ) {
//                 currentStart = Math
//                 currentEnd = Math.max(currentEnd, intervals[loop][1]);
//                 listInterval.remove(startId + 1);

//                 if (listInterval.size() == (startId + 1)) {
//                     int[] temp = new int[2];
//                     temp[0] = currentStart;
//                     temp[1] = currentEnd;
//                     listInterval.set(currentIndex, temp);
//                 }
//                 continue;
//             } else {
//                 int[] temp = new int[2];
//                 temp[0] = currentStart;
//                 temp[1] = currentEnd;
//                 listInterval.set(currentIndex, temp);
//                 break;
//             }
//         }

//         int[][] items = new int[listInterval.size()][2];

//         for ( int k = 0; k < listInterval.size(); k++ ) {
//             int[] temp = listInterval.get(k);
//             items[k][0] = temp[0];
//             items[k][1] = temp[1];
//         }

//         return items;
//     }
}
