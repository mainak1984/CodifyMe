package edu.codifyme.leetcode.interview.googledeck.treesngrapsh;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * MEDIUM:
 * https://leetcode.com/explore/interview/card/google/61/trees-and-graphs/3070/
 *
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed
 * as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to
 * finish all courses.
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses,
 * return an empty array.
 *
 * Example 1:
 * Input: 2, [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
 *              course 0. So the correct course order is [0,1] .
 *
 * Example 2:
 * Input: 4, [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,1,2,3] or [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
 *              courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 *              So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
 * Note:
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 *
 * Approach:
 * Topological sort
 * Use array instead of Map for adj list if elements are 0->n-1
 */
public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[numCourses];
        int[] result = new int[numCourses];

        for (int loop = 0; loop < prerequisites.length; loop++) {
            List<Integer> srcHead = graph.getOrDefault(prerequisites[loop][0], new LinkedList<Integer>());
            srcHead.add(prerequisites[loop][1]);
            graph.put(prerequisites[loop][0], srcHead);

            inDegree[prerequisites[loop][1]]++;
        }

        LinkedList<Integer> queue = new LinkedList<>();
        int[] visited = new int[numCourses];
        boolean isPossible = false;

        for(int loop = 0; loop < inDegree.length; loop++) {
            if (0 == inDegree[loop]) {
                if (!dfs(graph, visited, queue, loop)) {
                    isPossible = false;
                    break;
                } else {
                    isPossible = true;
                }
            }
        }

        if (isPossible && allNodesVisited(visited)) {
            for(int loop = 0; loop < queue.size(); loop++) {
                result[loop] = queue.get(loop);
            }
            return result;
        } else {
            return new int[0];
        }
    }

    boolean allNodesVisited(int[] visited) {
        for(int visit: visited) {
            if (0 == visit) {
                return false;
            }
        }
        return true;
    }

    boolean dfs(Map<Integer, List<Integer>> graph, int[] visited, LinkedList<Integer> queue, int node) {
        visited[node] = 1;

        List<Integer> childs = graph.getOrDefault(node, new LinkedList<>());
        for(Integer child: childs) {
            if (0 == visited[child]) {
                if(!dfs(graph, visited, queue, child)) {
                    return false;
                }
            } else if (1 == visited[child]) {
                // visiting my own parent
                return false;
            }
        }

        visited[node] = 2;
        queue.add(node);
        return true;
    }
}
