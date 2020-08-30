package edu.codifyme.leetcode.practice.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * 952. Largest Component Size by Common Factor
 * https://leetcode.com/problems/largest-component-size-by-common-factor/
 *
 * Given a non-empty array of unique positive integers A, consider the following graph:
 *
 * There are A.length nodes, labelled A[0] to A[A.length - 1];
 * There is an edge between A[i] and A[j] if and only if A[i] and A[j] share a common factor greater than 1.
 * Return the size of the largest connected component in the graph.
 *
 * Example 1:
 * Input: [4,6,15,35]
 * Output: 4
 *
 * Example 2:
 * Input: [20,50,9,63]
 * Output: 2
 *
 * Example 3:
 * Input: [2,3,6,7,4,12,21,39]
 * Output: 8
 *
 * Note:
 * 1 <= A.length <= 20000
 * 1 <= A[i] <= 100000
 *
 * Approach:
 * One naive idea would be that we enumerate all pairs of nodes, in order to partition the nodes into groups, with the
 * help of Union-Find data structure. A more efficient idea would be that we build groups led by each of the common
 * factors of the numbers. This can be done in a single iteration over each of the number.
 * For each number, we enumerate all factors that can divide the number, and then we attribute the number to each group
 * led by the factor, i.e. Union(num, factor).
 */
public class LargestComponentSizeByCommonFactor {
    class UF {
        int[] parent;
        int[] size;
        int max;
        public UF (int N){
            parent = new int[N];
            size = new int[N];
            max = 1;
            for (int i = 0; i < N; i++){
                parent[i] = i;
                size[i] = 1;
            }
        }
        public int find(int x){
            if (x == parent[x]){
                return x;
            }
            return parent[x] = find(parent[x]);
        }
        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY){
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
                max = Math.max(max, size[rootY]);
            }
        }
    }
    public int largestComponentSize(int[] A) {
        int N = A.length;
        Map<Integer, Integer> map = new HashMap<>();// key is the factor, val is the node index
        UF uf = new UF(N);
        for (int i = 0; i < N; i++){
            int a = A[i];
            for (int j = 2; j * j <= a; j++){
                if (a % j == 0){
                    if (!map.containsKey(j)){//this means that no index has claimed the factor yet
                        map.put(j, i);
                    }else{//this means that one index already claimed, so union that one with current
                        uf.union(i, map.get(j));
                    }
                    if (!map.containsKey(a/j)){
                        map.put(a/j, i);
                    }else{
                        uf.union(i, map.get(a/j));
                    }
                }
            }
            if (!map.containsKey(a)){//a could be factor too. Don't miss this
                map.put(a, i);
            }else{
                uf.union(i, map.get(a));
            }
        }
        return uf.max;
    }
}
