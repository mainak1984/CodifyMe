package edu.codifyme.geeksforgeeks.array;

import java.util.Arrays;

/**
 * MEDIUM:
 * https://www.geeksforgeeks.org/find-triplets-array-whose-sum-equal-zero/
 *
 * Given an array of distinct elements. The task is to find triplets in the array whose sum is zero.
 *
 * Examples :
 * Input : arr[] = {0, -1, 2, -3, 1}
 * Output : (0 -1 1), (2 -3 1)
 *
 * Explanation : The triplets with zero sum are
 * 0 + -1 + 1 = 0 and 2 + -3 + 1 = 0
 *
 * Input : arr[] = {1, -2, 1, 0, 5}
 * Output : 1 -2  1
 * Explanation : The triplets with zero sum is
 * 1 + -2 + 1 = 0
 *
 * Solution:
 * Keep the first element separate, run left and right pointer from second to end and find -(first) together.
 * Like first, do for all elements
 *
 * Time Complexity : O(n^2)
 * Auxiliary Space : O(1)
 */
public class FindAllTripletsWithZeroSum {
    static void findTriplets(int arr[], int n)
    {
        boolean found = false;

        // sort array elements
        Arrays.sort(arr);

        for (int i=0; i<n-1; i++)
        {
            // initialize left and right
            int l = i + 1;
            int r = n - 1;
            int x = arr[i];
            while (l < r)
            {
                if (x + arr[l] + arr[r] == 0)
                {
                    // print elements if it's sum is zero
                    System.out.print(x + " ");
                    System.out.print(arr[l]+ " ");
                    System.out.println(arr[r]+ " ");

                    l++;
                    r--;
                    found = true;
                }

                // If sum of three elements is less
                // than zero then increment in left
                else if (x + arr[l] + arr[r] < 0)
                    l++;

                    // if sum is greater than zero than
                    // decrement in right side
                else
                    r--;
            }
        }

        if (found == false)
            System.out.println(" No Triplet Found");
    }
}
