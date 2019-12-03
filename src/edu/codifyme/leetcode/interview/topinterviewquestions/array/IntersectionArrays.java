package edu.codifyme.leetcode.interview.topinterviewquestions.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntersectionArrays {
/** EASY Collection **/
/** https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/674/ **/
/**
 Given two arrays, write a function to compute their intersection.

 Example 1:

 Input: nums1 = [1,2,2,1], nums2 = [2,2]
 Output: [2,2]

 Example 2:
 Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 Output: [4,9]

 Example 3:
 Input: nums1 = [1,2], nums2 = [1,1]
 Output: [1]

 Note:
 Each element in the result should appear as many times as it shows in both arrays.
 The result can be in any order.
 Follow up:

 What if the given array is already sorted? How would you optimize your algorithm?
 What if nums1's size is small compared to nums2's size? Which algorithm is better?
 What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */

    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> maps1 = new HashMap<>();
        List<Integer> list = new ArrayList<Integer>();
        int[] short1;
        int[] long1;

        if ( nums1.length < nums2.length ) {
            short1 = nums1;
            long1 = nums2;
        } else {
            short1 = nums2;
            long1 = nums1;
        }

        for (int num: short1) {
            int count = maps1.getOrDefault(num, 0);
            maps1.put( num, count+1 );
        }

        for (int num: long1) {
            int count = maps1.getOrDefault(num, 0);
            if ( count != 0 ) {
                list.add(num);
                if ( count == 1 ){
                    maps1.remove( num );
                } else {
                    maps1.put( num, count - 1 );
                }
            }

            if ( maps1.size() == 0 ) {
                break;
            }
        }

        int[] output = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            output[i] = list.get(i);
        }
        return output;
    }
}
