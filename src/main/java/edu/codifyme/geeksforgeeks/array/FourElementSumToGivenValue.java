package edu.codifyme.geeksforgeeks.array;

import java.util.HashMap;

/**
 * MEDIUM: Find four elements that sum to a given value | Set 2
 * https://www.geeksforgeeks.org/find-four-elements-that-sum-to-a-given-value-set-2/
 *
 * Given an array of integers, find any one combination of four elements in the array whose sum is equal to a given
 * value X.
 * For example,
 *
 * Input: array = {10, 2, 3, 4, 5, 9, 7, 8}
 *        X = 23
 * Output: 3 5 7 8
 * Sum of output is equal to 23,
 * i.e. 3 + 5 + 7 + 8 = 23.
 *
 * Input: array = {1, 2, 3, 4, 5, 9, 7, 8}
 *        X = 16
 * Output: 1 3 5 7
 * Sum of output is equal to 16,
 * i.e. 1 + 3 + 5 + 7 = 16.
 *
 * Solution:
 * Keeping first and second element fixed, move left and right pointer based approach to find other 2. Complexity O(n^3)
 * Build all pairs and store in a Hash table. Again build all pairs and search for a complementary pair which from hash
 * table.
 *
 * Method 2: Hashing Based Solution[O(n^2)]
 * Approach:
 * Store sums of all pairs in a hash table
 * Traverse through all pairs again and search for X – (current pair sum) in the hash table.
 * If a pair is found with the required sum, then make sure that all elements are distinct array elements and an element
 * is not considered more than once.
 */
public class FourElementSumToGivenValue {
    // The function finds four elements
    // with given sum X
    static void findFourElements(int arr[], int n, int X) {
        // Store sums of all pairs in a hash table
        HashMap<Integer, pair> mp = new HashMap<Integer, pair>();
        for (int i = 0; i < n - 1; i++)
            for (int j = i + 1; j < n; j++)
                mp.put(arr[i] + arr[j],
                        new pair(i, j));

        // Traverse through all pairs and search
        // for X - (current pair sum).
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = arr[i] + arr[j];

                // If X - sum is present in hash table,
                if (mp.containsKey(X - sum)) {
                    // Making sure that all elements are
                    // distinct array elements and an element
                    // is not considered more than once.
                    pair p = mp.get(X - sum);
                    if (p.first != i && p.first != j && p.second != i && p.second != j) {
                        System.out.print(arr[i] + ", " + arr[j] + ", " + arr[p.first] + ", " + arr[p.second]);
                        return;
                    }
                }
            }
        }
    }

    static class pair {
        int first, second;
        public pair(int first, int second)
        {
            this.first = first;
            this.second = second;
        }
    }
}
