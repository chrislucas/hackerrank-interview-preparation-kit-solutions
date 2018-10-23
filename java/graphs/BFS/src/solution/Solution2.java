package solution;

import java.util.*;
public class Solution2 {


    static class Edge {
        private int u, v, w;
        Edge(int u, int v,  int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
    public static class Graph {

        private static ArrayList<ArrayList<Edge>> graph;
        private static int size;
        public Graph(int size) {
            graph = new ArrayList<>();
            for (int i = 0; i<size; i++) {
                graph.add(new ArrayList<>());
            }
            this.size = size;
        }

        public void addEdge(int p, int q) {
            graph.get(p).add(new Edge(p, q, 6));
            graph.get(q).add(new Edge(q, p, 6));
        }

        public int[] shortestReach(int startId) { // 0 indexed
            int [] distances = new int[size];
            Arrays.fill(distances, -1);
            distances[startId] = 0;
            bfs(distances, startId);
            return distances;
        }

        private static void bfs(int [] distances, int source) {
            Queue<Edge> queue = new LinkedList<>();
            Edge e = graph.get(source).get(0);
            queue.add(e);
            boolean [] visited = new boolean[distances.length];
            Edge [] parent = new Edge[distances.length];
            visited[source] = true;
            while (!queue.isEmpty()) {
                Edge u = queue.poll();
                for (Edge v : graph.get(u.u)) {
                    if (!visited[v.v]) {
                        visited[v.v]    = true;
                        parent[v.v]     = u;
                        distances[v.v]  =  distances[parent[v.v].u] + v.w;
                        queue.add(new Edge(v.v, v.v, 0));
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