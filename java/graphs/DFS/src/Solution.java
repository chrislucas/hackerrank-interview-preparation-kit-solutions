import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/ctci-connected-cell-in-a-grid/problem?h_l=interview&playlist_slugs%5B%5D%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D%5B%5D=graphs
 * */

public class Solution {

    static final int [][] moves = {
        {-1, -1}      // diagonal superior esquerda *
        ,{-1, 0}     // cima *
        ,{-1, 1}     // dsd *
        ,{0, -1}     // esq *
        ,{0, 1}     // dir *
        ,{1, -1}     // dis *
        ,{1, 0}     // baixo *
        ,{1, 1}     // did *
    };

    // Complete the maxRegion function below.
    static int maxRegion(int[][] grid) {
        int ll = grid.length, lc = grid[0].length;
        boolean [][] v = new boolean[ll][lc];
        //return count(grid, v, ll, lc);
        return dfs(grid, v, ll, lc, 0, 0, 0);
    }

    static int count(int [][] grid,  boolean [][] visited, int ll, int lc) {
        int max = 0;
        for (int i = 0; i < ll ; i++) {
            for (int j = 0; j < lc ; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    max = Math.max(max, count(grid, visited, i, j, ll, lc));
                }
            }
        }
        return max;
    }

    static int count(int [][] matrix, boolean [][] visited, int i, int j, int ll, int lc) {
        int acc = 1;
        visited[i][j] = true;
        for (int[] move : moves) {
            int ii = move[0] + i;
            int jj = move[1] + j;
            if (ii > -1 && ii < ll && jj > -1 && jj < lc && ! visited[ii][jj] && matrix[ii][jj] == 1) {
                acc += count(matrix, visited, ii, jj, ll, lc);
            }
        }
        return acc;
    }

    static int dfs(int [][] grid, boolean [][] visited, int ll, int lc, int si, int sj, int max) {
        if (si == ll || sj == lc)
            return 0;
        else if (grid[si][sj] == 0 || visited[si][sj]) {
            int a = dfs(grid, visited, ll, lc,  si, sj+1, max);
            int b = dfs(grid, visited, ll, lc,  si+1, sj, max);
            return a + b;
        }
        else {
            int acc = 1;
            visited[si][sj] = true;
            for (int[] move : moves) {
                int ii = move[0] + si;
                int jj = move[1] + sj;
                // limit of graph and not visited and connected
                if (ii > -1 && ii < ll && jj > -1 && jj < lc && ! visited[ii][jj] && grid[ii][jj] == 1) {
                    acc += dfs(grid, visited, ll, lc,  ii, jj, max);
                }
            }
            return Math.max(acc, max);
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] gridRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int j = 0; j < m; j++) {
                int gridItem = Integer.parseInt(gridRowItems[j]);
                grid[i][j] = gridItem;
            }
        }
        int res = maxRegion(grid);
        //bufferedWriter.write(String.valueOf(res));
        //bufferedWriter.newLine();
        //bufferedWriter.close();
        System.out.println(res);
        scanner.close();
    }
}
