package day09;

import java.io.*;
import java.util.*;

public class TheBiggestSquareBJ1915 {
	
	static int n, m;
	static int[][] grid, dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		n = Integer.parseInt(tmp[0]);
		m = Integer.parseInt(tmp[1]);
		
		grid = new int[n][m];
		for (int i=0; i<n; i++) {
			char[] value = br.readLine().toCharArray();
			for (int j=0; j<m; j++) {
				grid[i][j] = Character.getNumericValue(value[j]);
			}
		}
		
		initialize();
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				if (grid[i][j] == 1) {
					int minValue = Math.min(dp[i - 1][j - 1], dp[i - 1][j]);
					minValue = Math.min(minValue, dp[i][j - 1]);
					dp[i][j] = minValue + 1;
				}
			}
		}
		
		int maxValue = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				maxValue = Math.max(maxValue, dp[i][j]);
			}
		}
		System.out.println(maxValue * maxValue);
	}
	
	public static void initialize() {
		dp = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				dp[i][j] = grid[i][j];
			}
		}
	}
}
