package edu.codifyme.leetcode.practice.tree;

import javafx.util.Pair;

import java.util.*;

/**
 * MEDIUM: Vertical Order Traversal of a Binary Tree
 * https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree
 *
 * Given a binary tree, return the vertical order traversal of its nodes values.
 *
 * For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1,
 * Y-1).
 *
 * Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report
 * the values of the nodes in order from top to bottom (decreasing Y coordinates).
 *
 * If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.
 * Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.
 *
 * Example 1:
 * Input: [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * Explanation:
 * Without loss of generality, we can assume the root node is at position (0, 0):
 * Then, the node with value 9 occurs at position (-1, -1);
 * The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
 * The node with value 20 occurs at position (1, -1);
 * The node with value 7 occurs at position (2, -2).
 *
 * Example 2:
 * Input: [1,2,3,4,5,6,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * The node with value 5 and the node with value 6 have the same position according to the given scheme.
 * However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.
 *
 * Note:
 * The tree will have between 1 and 1000 nodes.
 * Each node's value will be between 0 and 1000.
 *
 * Algorithm:
 * We could implement the above intuition based on the previous approaches. Again, we could break it down into 3 steps:
 * Step 1): First of all, we create a hashmap called columnTable with the column index as key and the list of
 * <row, value> tuples as value. This hashmap is used to hold the groups of coordinates.
 * We traverse the input tree by either BFS or DFS. During the traversal, we populate the hashmap that we created above.
 * Meanwhile, we also note down the minimal and maximal column index during the traversal. The minimal and maximal column
 * index defines the range of column index. With this range, we could iterate through columns in order without the need
 * for sorting, as one will see later.
 *
 * Step 2): Once we populate the above hashmap, we then sort the value in each entry of the hashmap, i.e. we sort each
 * group of coordinates led by the column index.
 *
 * Step 3): From the sorted hashmap, we extract the results that are grouped by the column index.
 */
public class VerticalOrderTraversalOfBinaryTree {
    Map<Integer, ArrayList<Pair<Integer, Integer>>> columnTable = new HashMap();
    int minColumn = 0, maxColumn = 0;

    private void DFS(TreeNode node, Integer row, Integer column) {
        if (node == null)
            return;

        if (!columnTable.containsKey(column)) {
            this.columnTable.put(column, new ArrayList<Pair<Integer, Integer>>());
        }

        this.columnTable.get(column).add(new Pair<Integer, Integer>(row, node.val));
        this.minColumn = Math.min(minColumn, column);
        this.maxColumn = Math.max(maxColumn, column);
        // preorder DFS traversal
        this.DFS(node.left, row + 1, column - 1);
        this.DFS(node.right, row + 1, column + 1);
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> output = new ArrayList();
        if (root == null) {
            return output;
        }

        // step 1). DFS traversal
        this.DFS(root, 0, 0);

        // step 2). retrieve the value from the columnTable
        for (int i = minColumn; i < maxColumn + 1; ++i) {
            // order by both "row" and "value"
            Collections.sort(columnTable.get(i), new Comparator<Pair<Integer, Integer>>() {
                @Override
                public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                    if (p1.getKey().equals(p2.getKey()))
                        return p1.getValue() - p2.getValue();
                    else
                        return p1.getKey() - p2.getKey();
                }
            });

            List<Integer> sortedColumn = new ArrayList();
            for (Pair<Integer, Integer> p : columnTable.get(i)) {
                sortedColumn.add(p.getValue());
            }
            output.add(sortedColumn);
        }

        return output;
    }

    public class TreeNode {
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

    // Approach 2:
//    public List<List<Integer>> verticalTraversal(TreeNode root) {
//        List<List<Integer>> output = new ArrayList<>();
//        TreeMap<Integer, TreeMap<Integer, List<Integer>>> res = new TreeMap<>();
//
//        addResult(root, 0, 0, res);
//
//        for (TreeMap<Integer, List<Integer>> innerMap: res.values()) {
//            List<Integer> outputPart = new ArrayList<>();
//
//            for (List<Integer> subItems: innerMap.values()) {
//                Collections.sort(subItems);
//                outputPart.addAll(subItems);
//            }
//
//            if (outputPart.size() != 0) {
//                output.add(outputPart);
//            }
//        }
//
//        return output;
//    }
//
//    void addResult(TreeNode node, int x, int y, TreeMap<Integer, TreeMap<Integer, List<Integer>>> res) {
//        // System.out.println(node.val+", "+x+", "+y);
//
//        if (!res.containsKey(y)) {
//            res.put(y, new TreeMap<Integer, List<Integer>>());
//        }
//
//        TreeMap<Integer, List<Integer>> items = res.get(y);
//
//        if (!items.containsKey(x)) {
//            items.put(x, new ArrayList<Integer>());
//        }
//
//        List<Integer> subItem = items.get(x);
//
//        subItem.add(node.val);
//
//        if (node.left != null) {
//            addResult(node.left, x+1, y-1, res);
//        }
//
//        if (node.right != null) {
//            addResult(node.right, x+1, y+1, res);
//        }
//    }
}
