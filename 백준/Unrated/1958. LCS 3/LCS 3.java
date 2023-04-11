import java.io.*;
import java.util.*;

class Main {
    
    private static String s1, s2, s3;
    private static int l, m, n;
    private static int[][][] dp;
    
    public static void main(String args[]) throws IOException {
        initialize();
        lcs();
        System.out.println(dp[l][m][n]);
    }
    
    private static int lcs() {
        int result = 0;
        for (int i = 1; i <= l; i++) {
            for (int j = 1; j <= m; j++) {
                for (int k = 1; k <= n; k++) {
                    if (s1.charAt(i-1) == s2.charAt(j-1) && s2.charAt(j-1) == s3.charAt(k-1)) {
                        dp[i][j][k] = dp[i-1][j-1][k-1] + 1;
                    } else {
                        int tmp = Math.max(dp[i-1][j][k], dp[i][j-1][k]);
                        dp[i][j][k] = Math.max(tmp, dp[i][j][k-1]);
                    }
                    result = Math.max(result, dp[i][j][k]);
                }
            }
        }
        return result;
    }
    
    private static void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s1 = br.readLine();
        s2 = br.readLine();
        s3 = br.readLine();
        
        l = s1.length();
        m = s2.length();
        n = s3.length();
        
        dp = new int[l + 1][m + 1][n + 1];
    }
    
    private static void printDP() {
        for (int i = 1; i < l; i++) {
            for (int j = 1; j < m; j++) {
                for (int k = 1; k < n; k++) {
                    System.out.print(String.format("%s ", dp[i][j][k]));
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
        }
    }
}