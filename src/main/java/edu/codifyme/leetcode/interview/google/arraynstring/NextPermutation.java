package edu.codifyme.leetcode.interview.google.arraynstring;

/**
 * 31. Next Permutation
 * MEDIUM: https://leetcode.com/problems/next-permutation
 *
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * The replacement must be in-place and use only constant extra memory.
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 *
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 * Approach 2: Single Pass Approach
 *
 * Find A[i] > A[i-1], starting from left; if found reverse A[i] to end; find a number just greater than A[i-1] and Swap
 *
 * Algorithm
 * First, we observe that for any given sequence that is in descending order, no next larger permutation is possible.
 * For example, no next permutation is possible for the following array:
 * [9, 5, 4, 3, 1]
 * We need to find the first pair of two successive numbers a[i]a[i] and a[i-1]a[i−1], from the right, which satisfy
 * a[i] > a[i-1]a[i]>a[i−1]. Now, no rearrangements to the right of a[i-1]a[i−1] can create a larger permutation since
 * that subarray consists of numbers in descending order. Thus, we need to rearrange the numbers to the right of
 * a[i-1]a[i−1] including itself.
 * Now, what kind of rearrangement will produce the next larger number? We want to create the permutation just larger
 * than the current one. Therefore, we need to replace the number a[i-1]a[i−1] with the number which is just larger than
 * itself among the numbers lying to its right section, say a[j]a[j].
 *  Next Permutation
 * We swap the numbers a[i-1]a[i−1] and a[j]a[j]. We now have the correct number at index i-1i−1. But still the current
 * permutation isn't the permutation that we are looking for. We need the smallest permutation that can be formed by
 * using the numbers only to the right of a[i-1]a[i−1]. Therefore, we need to place those numbers in ascending order to
 * get their smallest permutation.
 * But, recall that while scanning the numbers from the right, we simply kept decrementing the index until we found the
 * pair a[i]a[i] and a[i-1]a[i−1] where, a[i] > a[i-1]a[i]>a[i−1]. Thus, all numbers to the right of a[i-1]a[i−1] were
 * already sorted in descending order. Furthermore, swapping a[i-1]a[i−1] and a[j]a[j] didn't change that order.
 * Therefore, we simply need to reverse the numbers following a[i-1]a[i−1] to get the next smallest lexicographic
 * permutation.
 *
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        for (; i > 0; i--) {
            if (nums[i] > nums[i-1]) {
                reverse(nums, i, nums.length-1);
                int nextBigIndex = nextBiggerElement(nums, i, nums[i-1]);
                int temp = nums[nextBigIndex];
                nums[nextBigIndex] = nums[i-1];
                nums[i-1] = temp;

                break;
            }
        }

        if ( i == 0 ) { // Array is sorted in descending order
            reverse(nums, 0, nums.length-1);
        }
    }

    public void reverse(int[] nums, int start, int end) {
        while ( start < end ) {
            int temp = nums[end];
            nums[end] = nums[start];
            nums[start] = temp;
            start++;
            end--;
        }
    }

    public int nextBiggerElement(int[] nums, int startingIdx, int target) {
        for (int loop = startingIdx; loop < nums.length; loop++ ) {
            if ( nums[loop] > target ) {
                return loop;
            }
        }

        return -1;
    }
}
