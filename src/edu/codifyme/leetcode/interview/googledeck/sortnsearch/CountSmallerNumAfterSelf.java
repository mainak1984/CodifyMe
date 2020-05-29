package edu.codifyme.leetcode.interview.googledeck.sortnsearch;

import java.util.LinkedList;
import java.util.List;

/**
 * HARD:
 * https://leetcode.com/explore/interview/card/google/63/sorting-and-searching-4/3083/
 *
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 *
 * Example:
 *
 * Input: [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 */
public class CountSmallerNumAfterSelf {
    Node head;

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new LinkedList<>();

        if ( null == nums || 0 == nums.length ) {
            return result;
        }

        // add first node
        head = new Node(nums[nums.length-1]);
        result.add(0);

        for(int loop = nums.length - 2; loop >= 0 ; loop--) {
            int noOfElem = getLessElementNo(head, nums[loop], head.leftSize);
            result.add(0, noOfElem);
        }

        return result;
    }

    int getLessElementNo(Node node, int num, int count) {
        // System.out.println("num "+num+", count "+count);
        if (num < node.val ) {
            if ( null == node.left ) {
                // insert left; increase count and leave
                Node newNode = new Node(num);
                node.left = newNode;
                node.leftSize += 1;
                // System.out.println(node.val+" leftsize ->"+node.leftSize);
                return count;
            } else {
                return getLessElementNo(node.left, num, count);
            }
        } else if (num > node.val ) {
            if ( null == node.right ) {
                // insert right; increase count and leave
                Node newNode = new Node(num);
                node.right = newNode;
                return count + 1;
            } else {
                // System.out.println("calling right from "+node.val+" to "+node.right.val+"with count "+(count + 1 + node.leftSize));
                return getLessElementNo(node.right, num, count + 1 + node.leftSize);
            }
        } else {
            Node newNode = new Node(num);
            node.left = newNode;
            node.leftSize += 1;
            return count;
        }
    }

    class Node {
        int val;
        Node left;
        Node right;
        int leftSize;

        public Node(int val) {
            this.val = val;
            left = null;
            right = null;
            leftSize = 0;
        }
    }
}
