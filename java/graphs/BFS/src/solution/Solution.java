package solution;

import java.io.*;
import java.util.*;

public class Solution {

    static class Edge {
        private int u, v, w;
        Edge(int u, int v,  int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    private static ArrayList<ArrayList<Edge>> graph;
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out), true);

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

    private static String readLine() throws IOException {
        return reader.readLine().trim();
    }

    private static void run() {
        try {
            int queries = Integer.parseInt(readLine());
            while (queries>0) {
                StringTokenizer tk = new StringTokenizer(readLine(), " ");
                int v = Integer.parseInt(tk.nextToken());
                int e = Integer.parseInt(tk.nextToken());
                graph = new ArrayList<>();
                for (int i = 0; i<v+1; i++) {
                    graph.add(new ArrayList<>());
                }
                int [] distances = new int[v+1];
                Arrays.fill(distances, -1);
                for (int i = 0; i < e ; i++) {
                    tk = new StringTokenizer(readLine(), " ");
                    int p = Integer.parseInt(tk.nextToken());
                    int q = Integer.parseInt(tk.nextToken());
                    graph.get(p).add(new Edge(p, q, 6));
                    graph.get(q).add(new Edge(q, p, 6));
                }
                int source = Integer.parseInt(readLine());
                distances[source] = 0;
                bfs(distances, source);
                boolean flag = true;
                for (int i = 1; i < v + 1 ; i++) {
                    if (i == source)
                        continue;
                    writer.printf(flag ? "%d" : " %d", distances[i]);
                    flag = false;
                }
                writer.printf("\n");
                queries--;
            }
        } catch (IOException ignore) {}
    }

    public static void main(String[] args) {
        run();
    }
}
