package day09;

import java.io.*;
import java.util.*;

public class LCS2BJ9252 {
	
	static int n, m;
	static char[] str1, str2;
	static int[][] dp;
	
	static List<Character> stack = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		str1 = br.readLine().toCharArray();
		str2 = br.readLine().toCharArray();
		n = str1.length;
		m = str2.length;
		dp = new int[n+1][m+1];
		
		int maxValue = 0;
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=m; j++) {
				if (str1[i-1] == str2[j-1]) {
					dp[i][j] = dp[i-1][j-1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		System.out.println(dp[n][m]);
		
		int r = n, c = m;
		while (r != 0 && c != 0) {
			if (dp[r][c] == dp[r-1][c]) {
				r--;
			} else if (dp[r][c] == dp[r][c-1]) {
				c--;
			} else {
				stack.add(str1[r-1]);
				r--;
				c--;
			}
		}
		while (!stack.isEmpty() ) {
			System.out.print(stack.remove(stack.size() - 1));
		}
	}
}
