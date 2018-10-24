package solution;

import java.util.*;

/**
 * https://www.hackerrank.com/challenges/ctci-bfs-shortest-reach/problem?h_l=interview&playlist_slugs%5B%5D%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D%5B%5D=graphs
 * DONE
 * */

public class Solution2 {

    public static class Graph {
        private int [][] graph;
        private int size;
        Graph(int size) {
            this.graph = new int [size][size];
            this.size = size;
        }

        void addEdge(int p, int q) {
            graph[p][q] = 6;
            graph[q][p] = 6;
        }

        int[] shortestReach(int startId) { // 0 indexed
            int [] distances = new int[size];
            Arrays.fill(distances, -1);
            distances[startId] = 0;
            bfs(distances, startId);
            return distances;
        }

        private void bfs(int [] distances, int source) {
            Queue<Integer> queue = new LinkedList<>();
            queue.add(source);
            boolean [] visited = new boolean[distances.length];
            int [] parent = new int[distances.length];
            visited[source] = true;
            while (!queue.isEmpty()) {
                int u = queue.poll();
                for (int v = 1; v < this.size; v++) {
                    if (graph[u][v] != 0 && ! visited[v]) {
                        visited[v]    = true;
                        parent[v]     = u;
                        distances[v]  =  distances[parent[v]] + graph[u][v];
                        queue.add(v);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int queries = scanner.nextInt();
        for (int t = 0; t < queries; t++) {
            // Create a graph of size n where each edge weight is 6:
            Graph graph = new Graph(scanner.nextInt() + 1);
            int m = scanner.nextInt();
            // read and set edges
            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                // add each edge to the graph
                graph.addEdge(u, v);
            }
            // Find shortest reach from node s
            int startId = scanner.nextInt();
            int[] distances = graph.shortestReach(startId);
            for (int i = 1; i < distances.length; i++) {
                if (i != startId) {
                    System.out.print(distances[i]);
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        scanner.close();
    }
}