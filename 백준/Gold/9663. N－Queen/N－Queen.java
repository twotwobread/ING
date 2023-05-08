import java.io.*;
import java.util.*;

public class Main {
    private static int n, result = 0;
    private static boolean[] row, col;
    private static boolean[] lDiagonal, rDiagonal;

    public static void main(String args[]) throws IOException {
        initialize();
        getNumberOfCasesNQueen(0);
        System.out.println(result);
    }
    
    private static void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        row = new boolean[n];
        col = new boolean[n];
        lDiagonal = new boolean[n * n];
        rDiagonal = new boolean[n * n];
    }
    
    private static void getNumberOfCasesNQueen(int r) {
        if (r >= n) {
            result += 1;
            return;
        }
        
        for (int c = 0; c < n; c++) {
            if (col[c] || checkDiagonalQueen(r, c)) continue;
            
            col[c] = true;
            lDiagonal[r-c+(n-1)] = true;
            rDiagonal[r+c+(n-1)] = true;
            getNumberOfCasesNQueen(r + 1);
            lDiagonal[r-c+(n-1)] = false;
            rDiagonal[r+c+(n-1)] = false;
            col[c] = false;
        }
    }
    
    private static boolean checkDiagonalQueen(int r, int c) {
        if (lDiagonal[r-c+(n-1)]) return true;
        if (rDiagonal[r+c+(n-1)]) return true;
        return false;
    }
}