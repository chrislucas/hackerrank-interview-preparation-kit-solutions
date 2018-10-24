import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
        boolean [][] v = new boolean[grid.length][grid[0].length];
        return dfs(grid, v, 0, 0);
    }

    static int dfs(int [][] grid, boolean [][] visited, int si, int sj) {
        int ll = grid.length;
        int lc = grid[0].length;
        int acc = 0, accAux = 1;
        for (int i = si; i < ll ; i++) {
            for (int j = sj; j < lc ; j++) {
                if (grid[i][j] == 1 && ! visited[i][j]) {
                    visited[i][j] = true;
                    for (int[] move : moves) {
                        int ii = move[0] + i;
                        int jj = move[1] + j;
                        // limit of graph and
                        if (ii > -1 && ii < ll && jj > -1 && jj < lc
                                && ! visited[ii][jj] && grid[ii][jj] == 1) {
                            accAux += dfs(grid, visited, ii, jj);
                        }
                    }
                }
            }
            acc = Math.max(acc, accAux);
        }
        return acc;
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
