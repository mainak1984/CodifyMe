package edu.codifyme.leetcode.practice.array;

/**
 * 905. Sort Array By Parity
 * EASY: https://leetcode.com/problems/sort-array-by-parity/
 *
 * Given an array A of non-negative integers, return an array consisting of all the even elements of A, followed by all
 * the odd elements of A.
 *
 * You may return any answer array that satisfies this condition.
 *
 * Example 1:
 * Input: [3,1,2,4]
 * Output: [2,4,3,1]
 * The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
 *
 * Note:
 * 1 <= A.length <= 5000
 * 0 <= A[i] <= 5000
 *
 * Approach:
 * Use another array and keep filling from both end as the main array is traversed
 *
 * Better Approach: Inplace
 * We'll maintain two pointers i and j. The loop invariant is everything below i has parity 0
 * (ie. A[k] % 2 == 0 when k < i), and everything above j has parity 1.
 *
 * Then, there are 4 cases for (A[i] % 2, A[j] % 2):
 * If it is (0, 1), then everything is correct: i++ and j--.
 * If it is (1, 0), we swap them so they are correct, then continue.
 * If it is (0, 0), only the i place is correct, so we i++ and continue.
 * If it is (1, 1), only the j place is correct, so we j-- and continue.
 *
 * Throughout all 4 cases, the loop invariant is maintained, and j-i is getting smaller. So eventually we will be done
 * with the array sorted as desired.
 */
public class SortArrayByParity {
    // Extra space
    public int[] sortArrayByParity(int[] A) {
        int[] result = new int[A.length];
        int front = 0;
        int rear = A.length - 1;

        for (int elem: A) {
            if (elem %2 == 0) {
                result[front++] = elem;
            } else {
                result[rear--] = elem;
            }
        }

        return result;
    }

    // IN-Place
//     public int[] sortArrayByParity(int[] A) {
//         int i = 0, j = A.length - 1;
//         while (i < j) {
//             if (A[i] % 2 > A[j] % 2) {
//                 int tmp = A[i];
//                 A[i] = A[j];
//                 A[j] = tmp;
//             }

//             if (A[i] % 2 == 0) i++;
//             if (A[j] % 2 == 1) j--;
//         }

//         return A;
//     }

//     public int[] sortArrayByParity(int[] A) {
//         int right = A.length - 1;

//         for(int i = 0; i < A.length; i++){
//             if(A[i]%2 == 1){
//                 while(right > i && A[right]%2 == 1) right--;
//                 if(right > i){
//                     int temp = A[right];
//                     A[right] = A[i];
//                     A[i] = temp;
//                 }
//             }
//         }

//         return A;
//     }
}
