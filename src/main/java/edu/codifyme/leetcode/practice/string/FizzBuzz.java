package edu.codifyme.leetcode.practice.string;

import java.util.LinkedList;
import java.util.List;

/**
 * 412: Fizz Buzz
 * EASY: https://leetcode.com/problems/fizz-buzz/
 *
 * Write a program that outputs the string representation of numbers from 1 to n.
 *
 * But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”.
 * For numbers which are multiples of both three and five output “FizzBuzz”.
 *
 * Example: n = 15,
 *
 * Return:
 * [
 *     "1",
 *     "2",
 *     "Fizz",
 *     "4",
 *     "Buzz",
 *     "Fizz",
 *     "7",
 *     "8",
 *     "Fizz",
 *     "Buzz",
 *     "11",
 *     "Fizz",
 *     "13",
 *     "14",
 *     "FizzBuzz"
 * ]
 */
public class FizzBuzz {
    public List<String> fizzBuzz(int n) {
        List<String> result = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            if (i % 3 == 0) {
                sb.append("Fizz");
            }
            if (i % 5 == 0) {
                sb.append("Buzz");
            }
            if (sb.length() == 0) {
                result.add(String.valueOf(i));
            } else {
                result.add(sb.toString());
            }
        }

        return result;
    }
}
