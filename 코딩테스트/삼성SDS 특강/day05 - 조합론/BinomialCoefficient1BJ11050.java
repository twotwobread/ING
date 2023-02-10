package day05;

import java.io.*;

public class BinomialCoefficient1BJ11050 {
	
	static int n, m;
	static int[][] dp = new int[1001][1001];
	
	
	public static int paskal(int n, int k) {
		for (int i = 0; i <= n; i++) {
			dp[i][0] = 1;
			for (int j = 1; j <= i; j++) {
				dp[i][j] = (dp[i - 1][j] + dp[i - 1][j - 1]) % 10007;
			}
		}
		return dp[n][k];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		n = Integer.parseInt(tmp[0]);
		m = Integer.parseInt(tmp[1]);
		
		System.out.println(paskal(m, n));
	}
}
