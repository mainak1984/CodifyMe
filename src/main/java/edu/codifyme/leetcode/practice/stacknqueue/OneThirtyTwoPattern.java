package edu.codifyme.leetcode.practice.stacknqueue;

import java.util.Stack;

/**
 * 456. 132 Pattern
 * MEDIUM: https://leetcode.com/problems/132-pattern/
 *
 * Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k] such
 * that i < j < k and nums[i] < nums[k] < nums[j].
 * Return true if there is a 132 pattern in nums, otherwise, return false.
 * Follow up: The O(n^2) is trivial, could you come up with the O(n logn) or the O(n) solution?
 *
 * Example 1:
 * Input: nums = [1,2,3,4]
 * Output: false
 * Explanation: There is no 132 pattern in the sequence.
 *
 * Example 2:
 * Input: nums = [3,1,4,2]
 * Output: true
 * Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
 *
 * Example 3:
 * Input: nums = [-1,3,2,0]
 * Output: true
 * Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
 *
 * Constraints:
 * n == nums.length
 * 1 <= n <= 104
 * -109 <= nums[i] <= 109
 *
 * Approach: Stack
 * We may find out nums[i] corresponding to a particular nums[j] directly without having to consider every pair possible
 * in nums to find this nums[i],nums[j] pair. If we do some preprocessing, we can make the process of finding a
 * nums[k] corresponding to this nums[i],nums[j] pair also easy.
 *
 * The preprocessing required is to just find the best nums[i] value corresponding to every nums[j] value. This is done
 * in the same manner as in the second approach i.e. we find the minimum element found till the jth element which acts
 * as the nums[i] for the current nums[j]. We maintain this values in a min array. Thus, min[j] now refers to the
 * best nums[i] value for a particular nums[j].
 *
 * Now, we traverse back from the end of the nums array to find the nums[k]'s. Suppose, we keep a track of the nums[k]
 * values which can potentially satisfy the 132 criteria for the current nums[j]. We know, one of the conditions to be
 * satisfied by such a nums[k] is that it must be greater than nums[i]. Or in other words, we can also say that it must
 * be greater than min[j] for a particular nums[j] chosen.
 *
 * Once it is ensured that the elements left for competing for the nums[k] are all greater than min[j](or nums[i]), our
 * only task is to ensure that it should be lesser than nums[j]. Now, the best element from among the competitors, for
 * satisfying this condition will be the minimum one from out of these elements.
 *
 * If this element, nums[min] satisfies nums[min]<nums[j], we've found a 132 pattern. If not, no other element will
 * satisfy this criteria, since they are all greater than or equal to nums[min] and thus greater than or equal to
 * nums[j] as well.
 *
 * To keep a track of these potential nums[k] values for a particular nums[i],nums[j] considered currently, we maintain
 * a stack on which these potential nums[k]'s satisfying the 132 criteria lie in a descending order(minimum element on
 * the top). We need not sort these elements on the stack, but they'll be sorted automatically as we'll discuss along
 * with the process.
 *
 * After creating a min array, we start traversing the nums[j] array in a backward manner. Let's say, we are currently
 * at the jth element and let's also assume that the stack is sorted right now. Now, firstly, we check if nums[j]>min[j].
 * If not, we continue with the (j−1)th element and the stack remains sorted. If not, we keep on popping the elements
 * from the top of the stack till we find an element, stack[top] such that, stack[top]>min[j](or stack[top]>nums[i]).
 *
 * Once the popping is done, we're sure that all the elements pending on the stack are greater than nums[i] and are thus,
 * the potential candidates for nums[k] satisfying the 132 criteria. We can also note that the elements which have been
 * popped from the stack, all satisfy stack[top] ≤ min[j].
 *
 * Since, in the min array, min[p] ≤ min[q], for every p>q, these popped elements also satisfy stack[top] ≤ min[k],
 * for all 0 ≤ k < j. Thus, they are not the potential nums[k] candidates for even the preceding elements. Even after
 * doing the popping, the stack remains sorted.
 *
 * After the popping is done, we've got the minimum element from amongst all the potential nums[k]'s on the top of the
 * stack(as per the assumption). We can check if it is less than or equal to nums[j] to satisfy the 132 criteria(we've
 * already checked stack[top]>nums[i]). If this element satisfies the 132 criteria, we can return a True value. If not,
 * we know that for the current jj, nums[j]>min[j]. Thus, the element nums[j] could be a potential nums[k] value, for
 * the preceding nums[i]′s.
 *
 * Thus, we push it over the stack. We can note that, we need to push this element nums[j] on the stack only when it
 * didn't satisfy stack[top]<nums[j]. Thus, nums[j] ≤ stack[top]. Thus, even after pushing this element on the stack,
 * the stack remains sorted. Thus, we've seen by induction, that the stack always remains sorted.
 *
 * Also, note that in case nums[j] ≤ min[j], we don't push nums[j] onto the stack. This is because this nums[j] isn't
 * greater than even the minimum element lying towards its left and thus can't act as nums[k] in the future.
 *
 * If no element is found satisfying the 132 criteria till reaching the first element, we return a False value.
 */
public class OneThirtyTwoPattern {
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3) {
            return false;
        }

        int[] mins = new int[nums.length];
        mins[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            mins[i] = Math.min(mins[i-1], nums[i]);
        }

        Stack<Integer> stack = new Stack<>();
        for (int j = nums.length - 1; j > 0; j--) {
            if (nums[j] > mins[j]) {
                while (!stack.isEmpty() && stack.peek() <= mins[j]) {
                    stack.pop();
                }

                if (!stack.isEmpty() && stack.peek() < nums[j]) {
                    return true;
                }

                stack.push(nums[j]);
            }
        }

        return false;
    }

    // public boolean find132pattern(int[] nums) {
    //     if (nums.length < 3)
    //         return false;
    //     int[] min = new int[nums.length];
    //     min[0] = nums[0];
    //     for (int i = 1; i < nums.length; i++)
    //         min[i] = Math.min(min[i - 1], nums[i]);
    //     for (int j = nums.length - 1, k = nums.length; j >= 0; j--) {
    //         if (nums[j] > min[j]) {
    //             while (k < nums.length && nums[k] <= min[j])
    //                 k++;
    //             if (k < nums.length && nums[k] < nums[j])
    //                 return true;
    //             nums[--k] = nums[j];
    //         }
    //     }
    //     return false;
    // }
}
