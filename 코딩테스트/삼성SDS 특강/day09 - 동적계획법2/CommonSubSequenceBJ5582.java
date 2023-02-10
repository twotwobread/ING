package day09;

import java.io.*;
import java.util.*;

public class CommonSubSequenceBJ5582 {
	
	static int n, m;
	static char[] str1, str2;
	static int[][] dp;
	
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
				}
				maxValue = Math.max(maxValue, dp[i][j]);
			}
		}
		System.out.println(maxValue);
	}
}

