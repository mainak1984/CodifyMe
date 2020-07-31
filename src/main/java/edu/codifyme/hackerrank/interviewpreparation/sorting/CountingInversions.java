package edu.codifyme.hackerrank.interviewpreparation.sorting;

import java.util.Arrays;

/**
 * MEDIUM: https://www.hackerrank.com/challenges/ctci-merge-sort/problem
 *
 * In an array, , the elements at indices  and  (where ) form an inversion if . In other words, inverted elements  and
 * are considered to be "out of order". To correct an inversion, we can swap adjacent elements.
 * For example, consider the dataset . It has two inversions:  and . To sort the array, we must perform the following
 * two swaps to correct the inversions:
 * Given  datasets, print the number of inversions that must be swapped to sort each dataset on a new line.
 *
 * Solution:
 * When we merge two sorted arrays,  and , we must put the element from  into the new, sorted array if it is smaller
 * than the element in  (i.e., out of order). This effectively indirectly shifts the element to the left by the number
 * of elements remaining in the first array, but does it with a complexity of . Thus, we simply need to implement Merge
 * Sort and add the shift whenever the element in the  array is less than the element in the  array.
 */
public class CountingInversions {
    // Merge sort function
    private static int mergeSortAndCount(int[] arr, int l, int r)
    {
        // Keeps track of the inversion count at a
        // particular node of the recursion tree
        int count = 0;

        if (l < r) {
            int m = (l + r) / 2;

            // Total inversion count = left subarray count
            // + right subarray count + merge count
            // Left subarray count
            count += mergeSortAndCount(arr, l, m);

            // Right subarray count
            count += mergeSortAndCount(arr, m + 1, r);

            // Merge count
            count += mergeAndCount(arr, l, m, r);
        }

        return count;
    }

    // Function to count the number of inversions
    // during the merge process
    private static int mergeAndCount(int[] arr, int l, int m, int r)
    {
        // Left subarray
        int[] left = Arrays.copyOfRange(arr, l, m + 1);

        // Right subarray
        int[] right = Arrays.copyOfRange(arr, m + 1, r + 1);

        int i = 0, j = 0, k = l, swaps = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j])
                arr[k++] = left[i++];
            else {
                arr[k++] = right[j++];
                swaps += (m + 1) - (l + i);
            }
        }

        // Fill from the rest of the left subarray
        while (i < left.length)
            arr[k++] = left[i++];

        // Fill from the rest of the right subarray
        while (j < right.length)
            arr[k++] = right[j++];

        return swaps;
    }


    // Self implemented
//    static long noOfSwap = 0;
//
//    // Complete the countInversions function below.
//    static long countInversions(int[] arr) {
//        noOfSwap = 0;
//
//        mergesort(arr, 0, arr.length - 1);
//
//        return noOfSwap;
//    }
//
//    static void mergesort(int[] arr, int start, int end) {
//        if ( start < end ) {
//            // divide
//            int middle = (start + end) / 2;
//
//            mergesort(arr, start, middle);
//            mergesort(arr, middle+1, end);
//
//            merge(arr, start, middle, end);
//        }
//    }
//
//    static void merge(int[] arr, int left, int mid, int right) {
//        int leftSize = mid - left + 1;
//        int rightSize = right - mid;
//
//        // create temp array and copy the values
//        int[] leftArr = new int[leftSize];
//        int[] rightArr = new int[rightSize];
//
//        System.arraycopy(arr, left, leftArr, 0, leftSize);
//        System.arraycopy(arr, mid+1, rightArr, 0, rightSize);
//
//        int leftIndex = 0;
//        int rightIndex = 0;
//        int arrayIndex = left;
//
//        while ( leftIndex < leftSize && rightIndex < rightSize ) {
//            if ( leftArr[leftIndex] <= rightArr[rightIndex] ) {
//                // make a copy from left array to main array
//                arr[arrayIndex] = leftArr[leftIndex];
//                arrayIndex += 1;
//                leftIndex += 1;
//            } else {
//                // make a copy from right array
//                arr[arrayIndex] = rightArr[rightIndex];
//                arrayIndex += 1;
//                rightIndex += 1;
//
//                // add noOfSwaps by remaining elements of leftArray
//                noOfSwap += (leftSize - leftIndex);
//            }
//        }
//
//        while ( leftIndex < leftSize ) {
//            // copy rest of the left elements
//            arr[arrayIndex] = leftArr[leftIndex];
//            arrayIndex += 1;
//            leftIndex += 1;
//        }
//
//        while (rightIndex < rightSize) {
//            // copy rest of the right elements
//            arr[arrayIndex] = rightArr[rightIndex];
//            arrayIndex += 1;
//            rightIndex += 1;
//        }
//    }
}
