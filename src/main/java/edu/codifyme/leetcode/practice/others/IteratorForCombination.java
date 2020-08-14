package edu.codifyme.leetcode.practice.others;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 1286. Iterator for Combination
 * MEDIUM: https://leetcode.com/problems/iterator-for-combination/
 *
 * Design an Iterator class, which has:
 *
 * A constructor that takes a string characters of sorted distinct lowercase English letters and a number
 * combinationLength as arguments.
 * A function next() that returns the next combination of length combinationLength in lexicographical order.
 * A function hasNext() that returns True if and only if there exists a next combination.
 *
 * Example:
 * CombinationIterator iterator = new CombinationIterator("abc", 2); // creates the iterator.
 *
 * iterator.next(); // returns "ab"
 * iterator.hasNext(); // returns true
 * iterator.next(); // returns "ac"
 * iterator.hasNext(); // returns true
 * iterator.next(); // returns "bc"
 * iterator.hasNext(); // returns false
 *
 * Constraints:
 * 1 <= combinationLength <= characters.length <= 15
 * There will be at most 10^4 function calls per test.
 * It's guaranteed that all calls of the function next are valid.
 *
 * Approach:
 * Generate combination using recursion
 *
 * Approach 2:
 * Print all binary number and use the binary number which has r no. of 1's set. Use that as Mask on char array
 */
public class IteratorForCombination {
    List<String> result;
    Iterator<String> itr;

    void combinationUtil(char arr[], char data[], int start, int end, int index, int r)
    {
        // Current combination is ready to be printed, print it
        if (index == r)
        {
            result.add(new String(data));
            return;
        }

        // replace index with all possible elements. The condition
        // "end-i+1 >= r-index" makes sure that including one element
        // at index will make a combination with remaining elements
        // at remaining positions
        for (int i=start; i<=end && end-i+1 >= r-index; i++)
        {
            data[index] = arr[i];
            combinationUtil(arr, data, i+1, end, index+1, r);
        }
    }

    // The main function that prints all combinations of size r
    // in arr[] of size n. This function mainly uses combinationUtil()
    void storeCombination(char arr[], int n, int r)
    {
        // A temporary array to store all combination one by one
        char data[]=new char[r];

        // Print all combination using temprary array 'data[]'
        combinationUtil(arr, data, 0, n-1, 0, r);
    }

    public IteratorForCombination(String characters, int combinationLength) {
        result = new LinkedList<>();
        storeCombination(characters.toCharArray(), characters.length(), combinationLength);
        itr = result.iterator();
    }

    public String next() {
        return itr.next();
    }

    public boolean hasNext() {
        return itr.hasNext();
    }
}

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator obj = new CombinationIterator(characters, combinationLength);
 * String param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
