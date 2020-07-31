package edu.codifyme.geeksforgeeks.hashmap;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.util.LinkedList;
import java.util.Stack;

/**
 * MEDIUM:
 * https://www.geeksforgeeks.org/given-sorted-dictionary-find-precedence-characters/
 *
 * Given a sorted dictionary (array of words) of an alien language, find order of characters in the language.
 * Examples:
 *
 * Input:  words[] = {"baa", "abcd", "abca", "cab", "cad"}
 * Output: Order of characters is 'b', 'd', 'a', 'c'
 * Note that words are sorted and in the given language "baa"
 * comes before "abcd", therefore 'b' is before 'a' in output.
 * Similarly we can find other orders.
 *
 * Input:  words[] = {"caa", "aaa", "aab"}
 * Output: Order of characters is 'c', 'a', 'b'
 *
 * Given a sorted dictionary of an alien language
 *
 * Solution:
 * Build graph with the letter relationship
 * Perform topological sort
 * Print the stack to get the order
 */
public class FindAlienLanguageOrder {
    // An array representing the graph as an adjacency list
    static LinkedList<Integer>[] adjacencyList;

    FindAlienLanguageOrder(int nVertices)
    {
        adjacencyList = new LinkedList[nVertices];
        for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++)
        {
            adjacencyList[vertexIndex] = new LinkedList<>();
        }
    }

    // function to add an edge to graph
    public static void addEdge(int startVertex, int endVertex)
    {
        adjacencyList[startVertex].add(endVertex);
    }

    private static int getNoOfVertices()
    {
        return adjacencyList.length;
    }

    // A recursive function used by topologicalSort
    public static void topologicalSortUtil(int currentVertex, boolean[] visited,
                                           Stack<Integer> stack)
    {
        // Mark the current node as visited.
        visited[currentVertex] = true;

        // Recur for all the vertices adjacent to this vertex
        for (int adjacentVertex : adjacencyList[currentVertex])
        {
            if (!visited[adjacentVertex])
            {
                topologicalSortUtil(adjacentVertex, visited, stack);
            }
        }

        // Push current vertex to stack which stores result
        stack.push(currentVertex);
    }

    // prints a Topological Sort of the complete graph
    static void topologicalSort()
    {
        Stack<Integer> stack = new Stack<>();

        // Mark all the vertices as not visited
        boolean[] visited = new boolean[getNoOfVertices()];
        for (int i = 0; i < getNoOfVertices(); i++)
        {
            visited[i] = false;
        }

        // Call the recursive helper function to store Topological
        // Sort starting from all vertices one by one
        for (int i = 0; i < getNoOfVertices(); i++)
        {
            if (!visited[i])
            {
                topologicalSortUtil(i, visited, stack);
            }
        }

        // Print contents of stack
        while (!stack.isEmpty())
        {
            System.out.print((char)('a' + stack.pop()) + " ");
        }
    }

    public static class OrderOfCharacters {
        // This function fidns and prints order
        // of characer from a sorted array of words.
        // alpha is number of possible alphabets
        // starting from 'a'. For simplicity, this
        // function is written in a way that only
        // first 'alpha' characters can be there
        // in words array. For example if alpha
        //  is 7, then words[] should contain words
        // having only 'a', 'b','c' 'd', 'e', 'f', 'g'
        private static void printOrder(String[] words, int alpha) {
            // Create a graph with 'aplha' edges
//            Graph graph = new Graph(alpha);

            for (int i = 0; i < words.length - 1; i++) {
                // Take the current two words and find the first mismatching
                // character
                String word1 = words[i];
                String word2 = words[i + 1];
                for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                    // If we find a mismatching character, then add an edge
                    // from character of word1 to that of word2
                    if (word1.charAt(j) != word2.charAt(j)) {
                        addEdge (word1.charAt(j) - 'a', word2.charAt(j) - 'a');
                        break;
                    }
                }
            }

            // Print topological sort of the above created graph
            topologicalSort();
        }

    }
}
