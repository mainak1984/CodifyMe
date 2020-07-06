package edu.codifyme.geeksforgeeks.recursion;

/**
 * EASY:
 * https://www.geeksforgeeks.org/generate-all-binary-strings-from-given-pattern/
 *
 * Given a string containing of ‘0’, ‘1’ and ‘?’ wildcard characters, generate all binary strings that can be formed by
 * replacing each wildcard character by ‘0’ or ‘1’.
 * Example :
 *
 * Input str = "1??0?101"
 * Output:
 *         10000101
 *         10001101
 *         10100101
 *         10101101
 *         11000101
 *         11001101
 *         11100101
 *         11101101
 */
public class GenerateAllBinaryStrFromGivenPattern {
    public static void print(char str[], int index) {
        if (index == str.length) {
            System.out.println(str);
            return;
        }

        if (str[index] == '?') {
            // replace '?' by '0' and recurse
            str[index] = '0';
            print(str, index + 1);

            // replace '?' by '1' and recurse
            str[index] = '1';
            print(str, index + 1);

            // NOTE: Need to backtrack as string is passed by reference to the function
            str[index] = '?';
        } else {
            print(str, index + 1);
        }
    }
}
