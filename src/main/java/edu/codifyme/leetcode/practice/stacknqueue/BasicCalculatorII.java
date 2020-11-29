package edu.codifyme.leetcode.practice.stacknqueue;

import java.util.Stack;

/**
 * 22. Basic Calculator II
 * MEDIUM: https://leetcode.com/problems/basic-calculator-ii/
 *
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer
 * division should truncate toward zero.
 *
 * Example 1:
 *
 * Input: "3+2*2"
 * Output: 7
 *
 * Example 2:
 * Input: " 3/2 "
 * Output: 1
 *
 * Example 3:
 * Input: " 3+5 / 2 "
 * Output: 5
 * Note:
 *
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 *
 * Approach 1: Using Stack
 * Scan the input string s from left to right and evaluate the expressions based on the following rules
 *  - If the current character is a digit 0-9 ( operand ), add it to the number currentNumber.
 *  - Otherwise, the current character must be an operation (+,-,*, /). Evaluate the expression based on the type of
 *  operation.
 *  - Addition (+) or Subtraction (-): We must evaluate the expression later based on the next operation. So, we must
 *  store the currentNumber to be used later. Let's push the currentNumber in the Stack.
 *  Important trick: For A - B operations, push (-B) to stack so that all operations are + only while retrieving
 *
 * Approach 2: Optimised Approach without the stack
 * Intuition
 *
 * In the previous approach, we used a stack to track the values of the evaluated expressions. In the end, we pop all
 * the values from the stack and add to the result. Instead of that, we could add the values to the result beforehand
 * and keep track of the last calculated number, thus eliminating the need for the stack. Let's understand the algorithm in detail.
 *
 * Algorithm:
 * The approach works similar to Approach 1 with the following differences :
 *  - Instead of using a stack, we use a variable lastNumber to track the value of the last evaluated expression.
 *  - If the operation is Addition (+) or Subtraction (-), add the lastNumber to the result instead of pushing it to
 *  the stack. The currentNumber would be updated to lastNumber for the next iteration.
 *  - If the operation is Multiplication (*) or Division (/), we must evaluate the expression lastNumber *
 *  currentNumber and update the lastNumber with the result of the expression. This would be added to the result after
 *  the entire string is scanned.
 */
public class BasicCalculatorII {
    // Approach 1:
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        Stack<Integer> stack = new Stack<Integer>();
        int currentNumber = 0;
        char operation = '+';
        for (int i = 0; i < len; i++) {
            char currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
            }
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == len - 1) {
                if (operation == '-') {
                    stack.push(-currentNumber);
                }
                else if (operation == '+') {
                    stack.push(currentNumber);
                }
                else if (operation == '*') {
                    stack.push(stack.pop() * currentNumber);
                }
                else if (operation == '/') {
                    stack.push(stack.pop() / currentNumber);
                }
                operation = currentChar;
                currentNumber = 0;
            }
        }
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }

    // Approach 2
    public int calculateApp2(String s) {
        if (s == null || s.length() == 0) return 0;
        int length = s.length();
        int currentNumber = 0, lastNumber = 0, result = 0;
        char operation = '+';
        for (int i = 0; i < length; i++) {
            char currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
            }
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == length - 1) {
                if (operation == '+' || operation == '-') {
                    result += lastNumber;
                    lastNumber = (operation == '+') ? currentNumber : -currentNumber;
                } else if (operation == '*') {
                    lastNumber = lastNumber * currentNumber;
                } else if (operation == '/') {
                    lastNumber = lastNumber / currentNumber;
                }
                operation = currentChar;
                currentNumber = 0;
            }
        }
        result += lastNumber;
        return result;
    }
}
