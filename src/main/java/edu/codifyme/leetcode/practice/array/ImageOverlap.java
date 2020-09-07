package edu.codifyme.leetcode.practice.array;

/**
 * 835. Image Overlap
 * MEDIUM: https://leetcode.com/problems/image-overlap/
 *
 * Two images A and B are given, represented as binary, square matrices of the same size.  (A binary matrix has only 0s
 * and 1s as values.)
 *
 * We translate one image however we choose (sliding it left, right, up, or down any number of units), and place it on
 * top of the other image. After the overlap of this translation is the number of positions that have a 1 in both images.
 *
 * (Note also that a translation does not include any kind of rotation.)
 *
 * What is the largest possible overlap?
 *
 * Example 1:
 * Input: A = [[1,1,0],
 *             [0,1,0],
 *             [0,1,0]]
 *        B = [[0,0,0],
 *             [0,1,1],
 *             [0,0,1]]
 * Output: 3
 * Explanation: We slide A to right by 1 unit and down by 1 unit.
 * Notes:
 *
 * 1 <= A.length = A[0].length = B.length = B[0].length <= 30
 * 0 <= A[i][j], B[i][j] <= 1
 */
public class ImageOverlap {
    public int largestOverlap(int[][] a, int[][] b) {
        return Math.max(find(a, b, 0, 0, new boolean[a.length][a[0].length]), find(b, a, 0, 0, new boolean[a.length][a[0].length]));
    }

    private int find(int[][] a, int[][] b, int posi, int posj, boolean[][] mem) {
        if(posi < 0 || posi >= a.length || posj < 0 || posj >= a[0].length) {
            return 0;
        }
        if(mem[posi][posj]) {
            return 0;
        }
        mem[posi][posj] = true;
        int max = count(a, b, posi, posj);
        max = Math.max(max, find(a, b, posi + 1, posj, mem));
        max = Math.max(max, find(a, b, posi, posj + 1, mem));
        return max;
    }

    private int count(int[][] a, int[][] b, int posi, int posj) {
        int cnt = 0;
        for(int i = posi; i < a.length; i++) {
            for(int j = posj; j < a[i].length; j++) {
                cnt += (a[i][j] + b[i - posi][j - posj]) >> 1;
            }
        }
        return cnt;
    }

    // public int largestOverlap(int[][] A, int[][] B) {
    //     int N = A.length;
    //     List<Integer> LA = new ArrayList<>(),  LB = new ArrayList<>();
    //     HashMap<Integer, Integer> count = new HashMap<>();
    //     for (int i = 0; i < N * N; ++i)
    //         if (A[i / N][i % N] == 1)
    //             LA.add(i / N * 100 + i % N);
    //     for (int i = 0; i < N * N; ++i)
    //         if (B[i / N][i % N] == 1)
    //             LB.add(i / N * 100 + i % N);
    //     for (int i : LA) for (int j : LB)
    //             count.put(i - j, count.getOrDefault(i - j, 0) + 1);
    //     int res = 0;
    //     for (int i : count.values())
    //         res = Math.max(res, i);
    //     return res;
    // }
}
