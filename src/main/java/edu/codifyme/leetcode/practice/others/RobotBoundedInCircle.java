package edu.codifyme.leetcode.practice.others;

/**
 * 1041. Robot Bounded In Circle
 * MEDIUM: https://leetcode.com/problems/robot-bounded-in-circle/
 *
 * On an infinite plane, a robot initially stands at (0, 0) and faces north.  The robot can receive one of three
 * instructions:
 *
 * "G": go straight 1 unit;
 * "L": turn 90 degrees to the left;
 * "R": turn 90 degress to the right.
 * The robot performs the instructions given in order, and repeats them forever.
 *
 * Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
 *
 * Example 1:
 * Input: "GGLLGG"
 * Output: true
 * Explanation:
 * The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
 * When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.
 *
 * Example 2:
 * Input: "GG"
 * Output: false
 * Explanation:
 * The robot moves north indefinitely.
 *
 * Example 3:
 * Input: "GL"
 * Output: true
 * Explanation:
 * The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ...
 *
 * Note:
 * 1 <= instructions.length <= 100
 * instructions[i] is in {'G', 'L', 'R'}
 *
 * Approach:
 * This solution is based on two facts about the limit cycle trajectory.
 *
 * After at most 4 cycles, the limit cycle trajectory returns to the initial point x = 0, y = 0. That is related to the
 * fact that 4 directions (north, east, south, west) define the repeated cycles' plane symmetry
 *
 * We do not need to run 4 cycles to identify the limit cycle trajectory. One cycle is enough. There could be two
 * situations here.
 *
 * - First, if the robot returns to the initial point after one cycle, that's the limit cycle trajectory.
 * - Second, if the robot doesn't face north at the end of the first cycle, that's the limit cycle trajectory. Once
 * again, that's the consequence of the plane symmetry for the repeated cycles
 *
 * Algorithm
 * - The initial robot position is in the center x = y = 0, facing north idx = 0.
 * - Now everything is ready to iterate over the instructions.
 * - After one cycle we have everything to decide. It's a limit cycle trajectory if the robot is back to the center:
 * x = y = 0 or if the robot doesn't face north: idx != 0.
 */
public class RobotBoundedInCircle {
    public boolean isRobotBounded(String instructions) {
        int d[][] = {{0, 1}, {1, 0}, {0, -1}, { -1, 0}};
        int x = 0, y = 0;
        int dir = 0;

        for (char ch: instructions.toCharArray()) {
            if (ch == 'R') {
                dir = (dir + 1) % 4;
            } else if (ch == 'L') {
                dir = (dir + 3) % 4;
            } else {
                x += d[dir][0];
                y += d[dir][1];
            }
        }

        return (x == 0 && y == 0) || dir > 0;
    }
}
