package edu.codifyme.leetcode.interview.facebook.treesngraphs;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * 314. Binary Tree Vertical Order Traversal
 * MEDIUM: https://leetcode.com/problems/binary-tree-vertical-order-traversal/
 *
 * Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom,
 * column by column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 *
 * Example 2:
 * Input: root = [3,9,8,4,0,1,7]
 * Output: [[4],[9],[3,0,1],[8],[7]]
 *
 * Example 3:
 * Input: root = [3,9,8,4,0,1,7,null,null,null,2,5]
 * Output: [[4],[9,5],[3,0,1],[8,2],[7]]
 *
 * Example 4:
 * Input: root = []
 * Output: []
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 *
 * Approach:
 * Perform BFS or DFS, keep track of the co-ordinates and add nodes
 * Track min and max nodes for running a loop at the end adding to results
 */
public class BinaryTreeVerticalOrderTraversal {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> output = new ArrayList<>();
        TreeMap<Integer, TreeMap<Integer, List<Integer>>> res = new TreeMap<>();

        if (root != null) {
            addResult(root, 0, 0, res);
        }

        for (TreeMap<Integer, List<Integer>> innerMap: res.values()) {
            List<Integer> outputPart = new ArrayList<>();

            for (List<Integer> subItems: innerMap.values()) {
                // Collections.sort(subItems);
                outputPart.addAll(subItems);
            }

            if (outputPart.size() != 0) {
                output.add(outputPart);
            }
        }

        return output;
    }

    void addResult(TreeNode node, int x, int y, TreeMap<Integer, TreeMap<Integer, List<Integer>>> res) {
        // System.out.println(node.val+", "+x+", "+y);

        if (!res.containsKey(y)) {
            res.put(y, new TreeMap<Integer, List<Integer>>());
        }

        TreeMap<Integer, List<Integer>> items = res.get(y);

        if (!items.containsKey(x)) {
            items.put(x, new ArrayList<Integer>());
        }

        List<Integer> subItem = items.get(x);

        subItem.add(node.val);

        if (node.left != null) {
            addResult(node.left, x+1, y-1, res);
        }

        if (node.right != null) {
            addResult(node.right, x+1, y+1, res);
        }
    }

    // Approach 2
//    Map<Integer, ArrayList<Pair<Integer, Integer>>> columnTable = new HashMap();
//    int minColumn = 0, maxColumn = 0;
//
//    private void DFS(TreeNode node, Integer row, Integer column) {
//        if (node == null)
//            return;
//
//        if (!columnTable.containsKey(column)) {
//            this.columnTable.put(column, new ArrayList<Pair<Integer, Integer>>());
//        }
//
//        this.columnTable.get(column).add(new Pair<Integer, Integer>(row, node.val));
//        this.minColumn = Math.min(minColumn, column);
//        this.maxColumn = Math.max(maxColumn, column);
//        // preorder DFS traversal
//        this.DFS(node.left, row + 1, column - 1);
//        this.DFS(node.right, row + 1, column + 1);
//    }
//
//    public List<List<Integer>> verticalOrder(TreeNode root) {
//        List<List<Integer>> output = new ArrayList();
//        if (root == null) {
//            return output;
//        }
//
//        this.DFS(root, 0, 0);
//
//        // Retrieve the resuts, by ordering by column and sorting by row
//        for (int i = minColumn; i < maxColumn + 1; ++i) {
//
//            Collections.sort(columnTable.get(i), new Comparator<Pair<Integer, Integer>>() {
//                @Override
//                public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
//                    return p1.getKey() - p2.getKey();
//                }
//            });
//
//            List<Integer> sortedColumn = new ArrayList();
//            for (Pair<Integer, Integer> p : columnTable.get(i)) {
//                sortedColumn.add(p.getValue());
//            }
//            output.add(sortedColumn);
//        }
//
//        return output;
//    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
