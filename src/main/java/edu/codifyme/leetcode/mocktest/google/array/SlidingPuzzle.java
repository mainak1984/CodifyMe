package edu.codifyme.leetcode.mocktest.google.array;

import java.util.*;

/**
 * HARD: Sliding Puzzle
 * https://leetcode.com/problems/sliding-puzzle/
 *
 * On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.
 *
 * A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
 *
 * The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
 *
 * Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is
 * impossible for the state of the board to be solved, return -1.
 *
 * Examples:
 *
 * Input: board = [[1,2,3],[4,0,5]]
 * Output: 1
 * Explanation: Swap the 0 and the 5 in one move.
 *
 * Input: board = [[1,2,3],[5,4,0]]
 * Output: -1
 * Explanation: No number of moves will make the board solved.
 *
 * Input: board = [[4,1,2],[5,0,3]]
 * Output: 5
 * Explanation: 5 is the smallest number of moves that solves the board.
 *
 * An example path:
 * After move 0: [[4,1,2],[5,0,3]]
 * After move 1: [[4,1,2],[0,5,3]]
 * After move 2: [[0,1,2],[4,5,3]]
 * After move 3: [[1,0,2],[4,5,3]]
 * After move 4: [[1,2,0],[4,5,3]]
 * After move 5: [[1,2,3],[4,5,0]]
 * Input: board = [[3,2,4],[1,5,0]]
 * Output: 14
 *
 * Note:
 * board will be a 2 x 3 array as described above.
 * board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].
 *
 *
 * Approach #1: Breadth-First Search [Accepted]
 * Intuition
 * We can think of this problem as a shortest path problem on a graph. Each node is a different board state, and we
 * connect two boards by an edge if they can be transformed into one another in one move. We can solve shortest path
 * problems with breadth first search.
 *
 * Algorithm
 * For our breadth first search, we will need to be able to represent the nodes as something hashable, and we'll need to
 * enumerate the neighbors of a board. Afterwards, we can use a typical template for breadth-first search as shown below:
 *
 * Approach #2: A* Search [Accepted]
 * Intuition
 * As in Approach #1, this is a problem about searching on a graph.
 * We can use the "A* Star Search Algorithm" to search promising nodes in our graph first, with guarantees that it will
 * find the best answer.
 * For every node, we have some estimated cost node.priority = node.depth + node.heuristic, where node.depth is the
 * actual distance travelled, and node.heuristic is our heuristic (guess) of the remaining distance to travel. If the
 * heuristic is admissible (it never overestimates the distance to the goal), then the following algorithm is guaranteed
 * to terminate at the best answer.
 * For solvers familiar with Dijkstra's Algorithm, A* Search is a special case of Dijkstra's with node.heuristic = 0
 * always. On certain types of graphs and with good heuristics, this approach is substantially faster then a breadth-first
 * search.
 *
 * Algorithm
 * Let's keep a priority queue that sorts by node.depth + node.heuristic. As before, each node represents a puzzle board.
 * The heuristic we use is the sum of the taxicab distance of each (non-zero) number to it's final destination. This
 * heuristic is admissible as we need at least this many moves.
 * To speed up our algorithm, we use targetWrong, which has a near zero heuristic distance to the target (meaning our
 * search will aim for it quickly). If it finds it, we don't have to search all the boards.
 * We could prove that the set of boards can be split in half, with one half transformable to target, and the other half
 * transformable to targetWrong. One way to convince yourself of this is to see that every piece except the last 2 can be
 * placed in the correct position, but a formal proof analyzing the parity of inversions of the underlying permutation is
 * outside the scope of this article.
 */
public class SlidingPuzzle {
    public int slidingPuzzle(int[][] board) {
        String target = "123450";
        String start = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                start += board[i][j];
            }
        }
        HashSet<String> visited = new HashSet<>();
        // all the positions 0 can be swapped to
        int[][] dirs = new int[][] { { 1, 3 }, { 0, 2, 4 },
                { 1, 5 }, { 0, 4 }, { 1, 3, 5 }, { 2, 4 } };
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);
        int res = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String curr = queue.poll();

                if (curr.equals(target)) {
                    return res;
                }

                int zero = curr.indexOf("0");

                for (int pos: dirs[zero]) {
                    String next = swap(curr, zero, pos);

                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.add(next);
                    }
                }
            }

            res++;
        }

        return -1;
    }

    private String swap(String str, int i, int j) {
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(i, str.charAt(j));
        sb.setCharAt(j, str.charAt(i));
        return sb.toString();
    }

    // Approach 2: A* Search
//    public int slidingPuzzle(int[][] board) {
//        int R = board.length, C = board[0].length;
//        int sr = 0, sc = 0;
//
//        //Find sr, sc
//        search:
//        for (sr = 0; sr < R; sr++)
//            for (sc = 0; sc < C; sc++)
//                if (board[sr][sc] == 0)
//                    break search;
//
//        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
//        PriorityQueue<Node> heap = new PriorityQueue<Node>((a, b) ->
//                (a.heuristic + a.depth) - (b.heuristic + b.depth));
//        Node start = new Node(board, sr, sc, 0);
//        heap.add(start);
//
//        Map<String, Integer> cost = new HashMap();
//        cost.put(start.boardstring, 9999999);
//
//        String target = Arrays.deepToString(new int[][]{{1,2,3}, {4,5,0}});
//        String targetWrong = Arrays.deepToString(new int[][]{{1,2,3}, {5,4,0}});
//
//        while (!heap.isEmpty()) {
//            Node node = heap.poll();
//            if (node.boardstring.equals(target))
//                return node.depth;
//            if (node.boardstring.equals(targetWrong))
//                return -1;
//            if (node.depth + node.heuristic > cost.get(node.boardstring))
//                continue;
//
//            for (int[] di: directions) {
//                int nei_r = di[0] + node.zero_r;
//                int nei_c = di[1] + node.zero_c;
//
//                // If the neighbor is not on the board or wraps incorrectly around rows/cols
//                if ((Math.abs(nei_r - node.zero_r) + Math.abs(nei_c - node.zero_c) != 1) ||
//                        nei_r < 0 || nei_r >= R || nei_c < 0 || nei_c >= C)
//                    continue;
//
//                int[][] newboard = new int[R][C];
//                int t = 0;
//                for (int[] row: node.board)
//                    newboard[t++] = row.clone();
//
//                // Swap the elements on the new board
//                newboard[node.zero_r][node.zero_c] = newboard[nei_r][nei_c];
//                newboard[nei_r][nei_c] = 0;
//
//                Node nei = new Node(newboard, nei_r, nei_c, node.depth+1);
//                if (nei.depth + nei.heuristic >= cost.getOrDefault(nei.boardstring, 9999999))
//                    continue;
//                heap.add(nei);
//                cost.put(nei.boardstring, nei.depth + nei.heuristic);
//            }
//        }
//
//        return -1;
//    }
//
//    class Node {
//        int[][] board;
//        String boardstring;
//        int heuristic;
//        int zero_r;
//        int zero_c;
//        int depth;
//        Node(int[][] B, int zr, int zc, int d) {
//            board = B;
//            boardstring = Arrays.deepToString(board);
//
//            //Calculate heuristic
//            heuristic = 0;
//            int R = B.length, C = B[0].length;
//            for (int r = 0; r < R; ++r)
//                for (int c = 0; c < C; ++c) {
//                    if (board[r][c] == 0) continue;
//                    int v = (board[r][c] + R*C - 1) % (R*C);
//                    // v/C, v%C: where board[r][c] should go in a solved puzzle
//                    heuristic += Math.abs(r - v/C) + Math.abs(c - v%C);
//                }
//            heuristic /= 2;
//            zero_r = zr;
//            zero_c = zc;
//            depth = d;
//        }
//    }
}
