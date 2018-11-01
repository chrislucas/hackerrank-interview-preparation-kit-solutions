package solution;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;


/**
 * https://www.hackerrank.com/challenges/torque-and-development/problem?h_l=interview&playlist_slugs%5B%5D%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D%5B%5D=graphs
 * DONE
 * */

public class Solution {

    // Weigthed quick Union
    static class WQU {
        private int [] parent, weight;
        private int comp;
        WQU(int n) {
            this.comp = n;
            parent = new int[n];
            weight = new int[n];
            for (int i = 0; i < n ; i++) {
                parent[i] = i;
                weight[i] = 1;
            }
        }

        private void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) // conectados ?
                return;
            if (weight[rootP] < weight[rootQ]) {
                parent[rootP] = rootQ;
                weight[rootQ] += weight[rootP];
            }
            else {
                parent[rootQ] = rootP;
                weight[rootP] += weight[rootQ];
            }
            comp--;
        }

        private boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        private int find(int p) {
            while (parent[p] != p) {
                p = parent[p];
            }
            return p;
        }
    }


    // Complete the roadsAndLibraries function below.
    static long roadsAndLibraries(int n, long c_lib, long c_road, int[][] cities) {
        WQU wqu = new WQU(n+1);
        if (c_road > c_lib)
            return (long)n * c_lib;
        long sumEdges = 0;
        for (int city[] : cities) {
            int i = city[0], j = city[1];
            if ( ! wqu.connected(i, j) ) {
                sumEdges++;
                wqu.union(i, j);
            }
        }
        return ((long)(wqu.comp-1) * c_lib) + (sumEdges * c_road);
    }

    private static void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String skip = "(\r\n|[\n\r\u2028\u2029\u0085])?";
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out), true);
        try {
            int queries = Integer.parseInt(reader.readLine().trim());
            while (queries>0) {
                StringTokenizer tk = new StringTokenizer(reader.readLine().trim(), " ");
                int v = Integer.parseInt(tk.nextToken().replaceAll(skip, ""));
                int e = Integer.parseInt(tk.nextToken().replaceAll(skip, ""));
                long costLib = Long.parseLong(tk.nextToken().replaceAll(skip, ""));
                long costRoad = Long.parseLong(tk.nextToken().replaceAll(skip, ""));
                int [][] graph = new int[e+1][2]; //new int[v+1][v+1];
                for (int i = 0; i < e ; i++) {
                    tk = new StringTokenizer(reader.readLine().trim(), " ");
                    int p = Integer.parseInt(tk.nextToken().replaceAll(skip, ""));
                    int q = Integer.parseInt(tk.nextToken().replaceAll(skip, ""));
                    graph[i][0] = p;
                    graph[i][1] = q;
                }
                writer.printf("%d\n", roadsAndLibraries(v, costLib, costRoad, graph));
                queries--;
            }
        } catch (IOException ignore) {}
    }

    private static void run2() {
        Scanner scanner = new Scanner(System.in);
        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        for (int qItr = 0; qItr < q; qItr++) {
            String[] nmC_libC_road = scanner.nextLine().split(" ");
            int n = Integer.parseInt(nmC_libC_road[0]);
            int m = Integer.parseInt(nmC_libC_road[1]);
            long c_lib = Long.parseLong(nmC_libC_road[2]);
            long c_road = Long.parseLong(nmC_libC_road[3]);
            int[][] cities = new int[m][2];
            for (int i = 0; i < m; i++) {
                String[] citiesRowItems = scanner.nextLine().split(" ");
                //scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                for (int j = 0; j < 2; j++) {
                    int citiesItem = Integer.parseInt(citiesRowItems[j]);
                    cities[i][j] = citiesItem;
                }
            }
            long result = roadsAndLibraries(n, c_lib, c_road, cities);
            System.out.println(result);
        }
    }

    public static void main(String[] args) {
        run2();
    }
}
