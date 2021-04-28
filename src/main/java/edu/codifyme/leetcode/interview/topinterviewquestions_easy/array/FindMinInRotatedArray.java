package edu.codifyme.leetcode.interview.topinterviewquestions.array;

/**
 * 154. Find Minimum in Rotated Sorted Array II
 * HARD: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 *
 * Find the minimum element.
 *
 * The array may contain duplicates.
 *
 * Example 1:
 *
 * Input: [1,3,5]
 * Output: 1
 * Example 2:
 *
 * Input: [2,2,2,0,1]
 * Output: 0
 * Note:
 *
 * This is a follow up problem to Find Minimum in Rotated Sorted Array.
 * Would allow duplicates affect the run-time complexity? How and why?
 *
 * Algorithm
 * In the classical binary search algorithm, we would compare the pivot element (i.e. nums[pivot]) with the value that
 * we would like to locate. In our case, however, we would compare the pivot element to the element pointed by the upper
 * bound pointer (i.e. nums[high]).
 *
 * Following the structure of the binary search algorithm, the essential part remained is to design the cases on how to
 * update the two pointers.
 *
 * Here we give one example on how we can break it down concisely into three cases. Note that given the array, we
 * consider the element pointed by the low index to be on the left-hand side of the array, and the element pointed by
 * the high index to be on the right-hand side.
 *
 * Case 1). nums[pivot] < nums[high]
 *
 * The pivot element resides in the same half as the upper bound element.
 * Therefore, the desired minimum element should reside to the left-hand side of pivot element. As a result, we then
 * move the upper bound down to the pivot index, i.e. high = pivot.
 *
 * Case 2). nums[pivot] > nums[high]
 *
 * The pivot element resides in the different half of array as the upper bound element.
 * Therefore, the desired minium element should reside to the right-hand side of the pivot element. As a result, we then
 * move the lower bound up next to the pivot index, i.e. low = pivot + 1.
 *
 * Case 3). nums[pivot] == nums[high]
 *
 * In this case, we are not sure which side of the pivot that the desired minimum element would reside.
 * To further reduce the search scope, a safe measure would be to reduce the upper bound by one (i.e. high = high - 1),
 * rather than moving aggressively to the pivot point.
 * The above strategy would prevent the algorithm from stagnating (i.e. endless loop). More importantly, it maintains
 * the correctness of the procedure, i.e. we would not end up with skipping the desired element.
 *
 * To summarize, this algorithm differs to the classical binary search algorithm in two parts:
 *
 * We use the upper bound of search scope as the reference for the comparison with the pivot element, while in the
 * classical binary search the reference would be the desired value.
 * When the result of comparison is equal (i.e. Case #3), we further move the upper bound, while in the classical binary
 * search normally we would return the value immediately.
 */
public class FindMinInRotatedArray {
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;

        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (nums[pivot] < nums[high])
                high = pivot;
            else if (nums[pivot] > nums[high])
                low = pivot + 1;
            else
                high -= 1;
        }
        return nums[low];
    }
}
