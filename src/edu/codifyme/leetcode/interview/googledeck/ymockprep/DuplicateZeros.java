package edu.codifyme.leetcode.interview.googledeck.ymockprep;

/**
 * EASY:
 * https://leetcode.com/problems/duplicate-zeros/
 *
 * Given a fixed length array arr of integers, duplicate each occurrence of zero, shifting the remaining elements to
 * the right.
 * Note that elements beyond the length of the original array are not written.
 * Do the above modifications to the input array in place, do not return anything from your function.
 *
 * Example 1:
 * Input: [1,0,2,3,0,4,5,0]
 * Output: null
 * Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]
 *
 * Example 2:
 * Input: [1,2,3]
 * Output: null
 * Explanation: After calling your function, the input array is modified to: [1,2,3]
 *
 * Note:
 * 1 <= arr.length <= 10000
 * 0 <= arr[i] <= 9
 */
public class DuplicateZeros {
    public void duplicateZeros(int[] arr) {

        // 1,0,2,3,0,4,5,0
        //              ,
        // 1,2,0
        // 1,0,2
        // 1,0,2,0,3

        int virtualIndex = 0;
        int loop = 0;

        for (; loop < arr.length; loop++, virtualIndex++) {
            if(virtualIndex >= arr.length ) {
                break;
            }

            if (arr[loop] == 0) {
                virtualIndex++;
            }
        }

        int actualIndex = arr.length-1;
        loop--;

        if (virtualIndex > arr.length) { // last element single zero
            arr[actualIndex--] = arr[loop--];
        }

        for (int i = loop; i >= 0; i--) {
            if (arr[i] == 0) {
                // Do something
                arr[actualIndex--] = 0;
                arr[actualIndex--] = 0;
            } else {
                arr[actualIndex--] = arr[i];
            }
        }
    }
}
