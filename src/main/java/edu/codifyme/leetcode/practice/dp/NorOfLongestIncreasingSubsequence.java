package edu.codifyme.leetcode.practice.dp;

import java.util.Arrays;

/**
 * 673. Number of Longest Increasing Subsequence
 * https://leetcode.com/problems/number-of-longest-increasing-subsequence/
 *
 * Given an integer array nums, return the number of longest increasing subsequences.
 *
 * Example 1:
 * Input: nums = [1,3,5,4,7]
 * Output: 2
 * Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
 *
 * Example 2:
 * Input: nums = [2,2,2,2,2]
 * Output: 5
 * Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1,
 * so output 5.
 *
 * Constraints:
 * 0 <= nums.length <= 2000
 * -106 <= nums[i] <= 106
 *
 * Approach 1: DP, using an additional count array - O(n^2)
 *
 * Suppose for sequences ending at nums[i], we knew the length length[i] of the longest sequence, and the number
 * count[i] of such sequences with that length.
 * For every i < j with A[i] < A[j], we might append A[j] to a longest subsequence ending at A[i]. It means that we have
 * demonstrated count[i] subsequences of length length[i] + 1.
 * Now, if those sequences are longer than length[j], then we know we have count[i] sequences of this length. If these
 * sequences are equal in length to length[j], then we know that there are now count[i] additional sequences to be
 * counted of that length (ie. count[j] += count[i]).
 *
 * Approach 2: Segment Tree - O(nlogn)
 * Intuition:
 * Suppose we knew for each length L, the number of sequences with length L ending in x. Then when considering the next
 * element of nums, updating our knowledge hinges on knowing the number of sequences with length L-1 ending in y < x.
 * This type of query over an interval is a natural fit for using some sort of tree.
 *
 * We could try using Fenwick trees, but we would have to store NN of them, which naively might be O(N^2) space. Here,
 * we focus on an implementation of a Segment Tree.
 *
 * Algorithm:
 * Implementing Segment Trees is discussed in more detail here. In this approach, we will attempt a variant of segment
 * tree that doesn't use lazy propagation.
 * First, let us call the "value" of an interval, the longest length of an increasing subsequence, and the number of
 * such subsequences in that interval.
 * Each node knows about the interval of nums values it is considering [node.range_left, node.range_right], and it knows
 * about node.val, which contains information on the value of interval. Nodes also have node.left and node.right children
 * that represents the left and right half of the interval node considers. These child nodes are created on demand as
 * appropriate.
 * Now, query(node, key) will tell us the value of the interval specified by node, except we'll exclude anything above
 * key. When key is outside the interval specified by node, we return the answer. Otherwise, we'll divide the interval
 * into two and query both intervals, then merge the result.
 * What does merge(v1, v2) do? Suppose two nodes specify adjacent intervals, and have corresponding values
 * v1 = node1.val, v2 = node2.val. What should the aggregate value, v = merge(v1, v2) be? If there are longer
 * subsequences in one node, then v will just be that. If both nodes have longest subsequences of equal length, then we
 * should count subsequences in both nodes. Note that we did not have to consider cases where larger subsequences were
 * made, since these were handled by insert.
 * What does insert(node, key, val) do? We repeatedly insert the key into the correct half of the interval that node
 * specifies (possibly a point), and after such insertion this node's value could change, so we merge the values
 * together again.
 * Finally, in our main algorithm, for each num in nums we query for the value length, count of the interval below num,
 * and we know it will lead to count additional sequences of length length + 1. We then update our tree with that
 * knowledge.
 */
public class NorOfLongestIncreasingSubsequence {
    // Approach 1:
    public int findNumberOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int[] cnt = new int[nums.length];

        Arrays.fill(dp,1);

        int ret =0;
        int max =0;

        for(int i=0; i<nums.length; i++) {
            int count = 1;

            for(int j=0; j<i; j++){
                if(nums[i] > nums[j]){
                    if(1+dp[j] > dp[i]){
                        count = cnt[j];
                        dp[i] = 1 + dp[j];
                    } else if(1+dp[j] == dp[i]){
                        count += cnt[j];
                    }
                }
            }

            cnt[i] = count;

            if(dp[i] > max ){
                ret = count;
                max = dp[i];
            } else if(max == dp[i]){
                ret += count;
            }
        }

        return ret;
    }

    // Approach 2:
//    class Solution {
//        public Value merge(Value v1, Value v2) {
//            if (v1.length == v2.length) {
//                if (v1.length == 0) return new Value(0, 1);
//                return new Value(v1.length, v1.count + v2.count);
//            }
//            return v1.length > v2.length ? v1 : v2;
//        }
//
//        public void insert(Node node, int key, Value val) {
//            if (node.range_left == node.range_right) {
//                node.val = merge(val, node.val);
//                return;
//            } else if (key <= node.getRangeMid()) {
//                insert(node.getLeft(), key, val);
//            } else {
//                insert(node.getRight(), key, val);
//            }
//            node.val = merge(node.getLeft().val, node.getRight().val);
//        }
//
//        public Value query(Node node, int key) {
//            if (node.range_right <= key) return node.val;
//            else if (node.range_left > key) return new Value(0, 1);
//            else return merge(query(node.getLeft(), key), query(node.getRight(), key));
//        }
//
//        public int findNumberOfLIS(int[] nums) {
//            if (nums.length == 0) return 0;
//            int min = nums[0], max = nums[0];
//            for (int num: nums) {
//                min = Math.min(min, num);
//                max = Math.max(max, num);
//            }
//            Node root = new Node(min, max);
//            for (int num: nums) {
//                Value v = query(root, num-1);
//                insert(root, num, new Value(v.length + 1, v.count));
//            }
//            return root.val.count;
//        }
//    }
//
//    class Node {
//        int range_left, range_right;
//        Node left, right;
//        Value val;
//        public Node(int start, int end) {
//            range_left = start;
//            range_right = end;
//            left = null;
//            right = null;
//            val = new Value(0, 1);
//        }
//        public int getRangeMid() {
//            return range_left + (range_right - range_left) / 2;
//        }
//        public Node getLeft() {
//            if (left == null) left = new Node(range_left, getRangeMid());
//            return left;
//        }
//        public Node getRight() {
//            if (right == null) right = new Node(getRangeMid() + 1, range_right);
//            return right;
//        }
//    }
//
//    class Value {
//        int length;
//        int count;
//        public Value(int len, int ct) {
//            length = len;
//            count = ct;
//        }
//    }
}
