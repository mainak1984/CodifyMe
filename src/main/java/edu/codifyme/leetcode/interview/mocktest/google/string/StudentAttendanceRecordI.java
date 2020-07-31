package edu.codifyme.leetcode.interview.mocktest.google.string;

/**
 * EASY: Student Attendance Record I
 * https://leetcode.com/problems/student-attendance-record-i/
 *
 * You are given a string representing an attendance record for a student. The record only contains the following three
 * characters:
 * 'A' : Absent.
 * 'L' : Late.
 * 'P' : Present.
 * A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two
 * continuous 'L' (late).
 *
 * You need to return whether the student could be rewarded according to his attendance record.
 *
 * Example 1:
 * Input: "PPALLP"
 * Output: True
 * Example 2:
 * Input: "PPALLL"
 * Output: False
 */
public class StudentAttendanceRecordI {
    public boolean checkRecord(String s) {
        if (null == s || 0 == s.length()) {
            return false;
        }

        char[] chars = s.toCharArray();
        boolean isPunished = false;
        boolean isAlreadyAbsent = 'A' == chars[0];

        for (int loop = 1; loop < chars.length; loop++) {
            if ('L' == chars[loop] && 'L' == chars[loop - 1]) {
                if (loop < chars.length - 1 && 'L' == chars[loop + 1]) {
                    isPunished = true;
                    break;
                }
            }
            if ('A' == chars[loop]) {
                if (isAlreadyAbsent) {
                    isPunished = true;
                    break;
                } else {
                    isAlreadyAbsent = true;
                }

            }
        }

        return !isPunished;
    }
}
