package edu.codifyme.leetcode.interview.googledeck.design;

import java.util.Stack;

/**
 * Min Stack
 * EASY:
 * https://leetcode.com/problems/min-stack/
 *
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 *
 *
 * Example:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 */

class MinStack {

    Stack<Integer> numberStack; // = new Stack<>();
    Stack<Integer> minStack; // = new Stack<>();
    int min = Integer.MAX_VALUE;

    /** initialize your data structure here. */
    public MinStack() {
        numberStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        // System.out.println("Pushing in numberStack "+x);
        numberStack.push(x);

        if (min >= x) {
            // System.out.println("Pushing in minStack "+x);
            minStack.push(x);
            min = x;
        }
    }

    public void pop() {
        int number = numberStack.pop();
        // System.out.println("Popping from numberStack "+number);

        if ( number == minStack.peek()) {
            // System.out.println("Popping from minStack "+minStack.pop());
            minStack.pop();
            if (minStack.size() == 0) {
                min = Integer.MAX_VALUE;
            } else {
                min = minStack.peek();
            }
        }
    }

    public int top() {
        // System.out.println("top "+numberStack.peek());
        return numberStack.peek();
    }

    public int getMin() {
        // System.out.println("getMIn "+minStack.peek());
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */