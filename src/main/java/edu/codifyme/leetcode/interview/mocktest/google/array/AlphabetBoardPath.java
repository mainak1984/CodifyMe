package edu.codifyme.leetcode.interview.mocktest.google.array;

/**
 * MEDIUM: Alphabet Board Path
 * https://leetcode.com/problems/alphabet-board-path/
 *
 * On an alphabet board, we start at position (0, 0), corresponding to character board[0][0].
 *
 * Here, board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"], as shown in the diagram below.
 *
 * We may make the following moves:
 *
 * 'U' moves our position up one row, if the position exists on the board;
 * 'D' moves our position down one row, if the position exists on the board;
 * 'L' moves our position left one column, if the position exists on the board;
 * 'R' moves our position right one column, if the position exists on the board;
 * '!' adds the character board[r][c] at our current position (r, c) to the answer.
 * (Here, the only positions that exist on the board are positions with letters on them.)
 *
 * Return a sequence of moves that makes our answer equal to target in the minimum number of moves.  You may return any
 * path that does so.
 *
 * Example 1:
 * Input: target = "leet"
 * Output: "DDR!UURRR!!DDD!"
 *
 * Example 2:
 * Input: target = "code"
 * Output: "RR!DDRR!UUL!R!"
 *
 * Constraints:
 * 1 <= target.length <= 100
 * target consists only of English lowercase letters.
 */
public class AlphabetBoardPath {
    public String alphabetBoardPath(String target) {
        int x = 0;
        int y = 0;

        StringBuilder sb = new StringBuilder();

        for (char ch: target.toCharArray()) {
            int tx = getCharX(ch);
            int ty = getCharY(ch);

            while (x != tx || y != ty) {
                if (x < tx) {
                    if (x < 4 || y == 0) {
                        sb.append("D");
                        x++;
                    }
                } else if (x > tx) {
                    sb.append("U");
                    x--;
                }
                if (y < ty) {
                    if ( x < 5 ) {
                        sb.append("R");
                        y++;
                    }
                } else if (y > ty) {
                    sb.append("L");
                    y--;
                }
            }

            sb.append("!");
        }

        return sb.toString();
    }

    int getCharX(char ch) {
        return (ch - 'a') / 5;
    }

    int getCharY(char ch) {
        return (ch - 'a') % 5;
    }
}
