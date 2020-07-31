package edu.codifyme.hackerrank.interviewpreparation.array;

/**
 * MEDIUM: https://www.hackerrank.com/challenges/minimum-swaps-2/problem
 *
 * You are given an unordered array consisting of consecutive integers  [1, 2, 3, ..., n] without any duplicates.
 * You are allowed to swap any two elements. You need to find the minimum number of swaps required to sort the array
 * in ascending order.
 * For example, given the array arr=[7,1,3,2,4,5,6] we perform the following steps:
 * i   arr                         swap (indices)
 * 0   [7, 1, 3, 2, 4, 5, 6]   swap (0,3)
 * 1   [2, 1, 3, 7, 4, 5, 6]   swap (0,1)
 * 2   [1, 2, 3, 7, 4, 5, 6]   swap (3,4)
 * 3   [1, 2, 3, 4, 7, 5, 6]   swap (4,5)
 * 4   [1, 2, 3, 4, 5, 7, 6]   swap (5,6)
 * 5   [1, 2, 3, 4, 5, 6, 7]
 *
 * It took 5 swaps to sort the array.
 *
 * Sample Input 0
 * 4
 * 4 3 1 2
 * Sample Output 0
 * 3
 *
 * Sample Input 1
 * 5
 * 2 3 4 1 5
 * Sample Output 1
 * 3
 *
 * Sample Input 2
 * 7
 * 1 3 5 2 4 6 7
 * Sample Output 2
 * 3
 *
 * Solution:
 * The idea is that if  occupies  position,  occupies  position and so on, then there will be some integer  which will
 * occupy  position. So, this forms a cycle.
 * So, if any element  is not at its correct position, we shift it to its correct position , then shift  to its correct
 * position  and so on. So, if  is the length of the cycle (number of elements in the cycle), then it will require a
 * minimum of  swaps to rearrange the elements of the cycle to their correct positions.
 * We find all such cycles and compute our answer.
 */
public class MinSwapsOfPair {
    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {
        // Alternate:
//        int swap=0;
//        for(int i=0;i<a.length;i++){
//            if(i+1!=a[i]){
//                int t=i;
//                while(a[t]!=i+1){
//                    t++;
//                }
//                int temp=a[t];
//                a[t]=a[i];
//                a[i]=temp;
//                swap++;
//            }
//        }
//        return swap;

        // Keep an array to bookkeep if all the elements are touched
        //int[] fixed = new int[arr.length];
        int swap = 0;

        for ( int loopVar=0; loopVar < arr.length; loopVar++ ) {
            while( loopVar < arr.length && arr[loopVar] == loopVar + 1 ) {
                //fixed[loopVar] = 1;
                loopVar += 1;
            }

            if ( loopVar == arr.length ) {
                return swap;
            }

            // Form the DAG and mark the fixed array
            int tempLoop = loopVar;
            while ( loopVar + 1 != arr[tempLoop] ) {
                int tmp = arr[tempLoop] - 1;
                swap += 1;
                arr[tempLoop] = tempLoop + 1;
                tempLoop = tmp;
                //fixed[tempLoop] = 1;
            }

            arr[tempLoop] = tempLoop + 1;
            //fixed[tempLoop] = 1;
        }

        return swap;
    }

}
