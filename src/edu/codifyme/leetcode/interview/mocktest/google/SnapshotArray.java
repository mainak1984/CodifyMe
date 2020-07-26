package edu.codifyme.leetcode.interview.mocktest.google;

import java.util.*;

/**
 * MEDIUM:
 * https://leetcode.com/problems/snapshot-array
 *
 * Implement a SnapshotArray that supports the following interface:
 *
 * SnapshotArray(int length) initializes an array-like data structure with the given length.  Initially, each element
 * equals 0.
 * void set(index, val) sets the element at the given index to be equal to val.
 * int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
 * int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 *
 * Example 1:
 * Input: ["SnapshotArray","set","snap","set","get"]
 * [[3],[0,5],[],[0,6],[0,0]]
 * Output: [null,null,0,null,5]
 * Explanation:
 * SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
 * snapshotArr.set(0,5);  // Set array[0] = 5
 * snapshotArr.snap();  // Take a snapshot, return snap_id = 0
 * snapshotArr.set(0,6);
 * snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
 *
 * Constraints:
 * 1 <= length <= 50000
 * At most 50000 calls will be made to set, snap, and get.
 * 0 <= index < length
 * 0 <= snap_id < (the total number of times we call snap())
 * 0 <= val <= 10^9
 *
 * Approach 1:
 * Use a HashMap for every snapshot
 *
 * Approach 2:
 * Use a treemap for all the snapshot
 */
public class SnapshotArray {
    // Approach 1:
    private List<Map<Integer, Integer>> shot;
    private Map<Integer, Integer> diff;

    public SnapshotArray(int length) {
        shot  = new ArrayList<>(length);
        diff  = new HashMap<>(length);
    }

    public void set(int index, int val) {
        diff.put(index, val);
    }

    public int snap() {
        shot.add(diff);
        diff = new HashMap<>();
        return shot.size() - 1;
    }

    public int get(int index, int snap_id) {
        for (int i = snap_id; i >= 0; --i)
            if (shot.get(i).containsKey(index))
                return shot.get(i).get(index);
        return 0;
    }

    // Approach 2: using TreeMap
//    TreeMap<Integer, Integer>[] A;
//    int snap_id = 0;
//    public SnapshotArray(int length) {
//        A = new TreeMap[length];
//        for (int i = 0; i < length; i++) {
//            A[i] = new TreeMap<Integer, Integer>();
//            A[i].put(0, 0);
//        }
//    }
//
//    public void set(int index, int val) {
//        A[index].put(snap_id, val);
//    }
//
//    public int snap() {
//        return snap_id++;
//    }
//
//    public int get(int index, int snap_id) {
//        return A[index].floorEntry(snap_id).getValue();
//    }
}
