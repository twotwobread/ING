package day07;

import java.io.*;
import java.util.*;

public class FloydBJ11404 {
	
	static final int INF = 1000001;
	
	static int n, m;
	static int[][] edges;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		initialize();
		for (int i = 0 ; i < m; i++) {
			String[] tmp = br.readLine().split(" ");
			int a = Integer.parseInt(tmp[0]);
			int b = Integer.parseInt(tmp[1]);
			int c = Integer.parseInt(tmp[2]);
	        edges[a][b] = Math.min(edges[a][b], c);
		}
		
		floyd();
		printAllCities();
	}
	
	private static void printAllCities() {
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=n; j++) {
				if (edges[i][j] == INF)
					edges[i][j] = 0;
			}
		}
		
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=n; j++) {
				System.out.print(edges[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private static void floyd() {
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=n; j++) {
				for (int k=1; k<=n; k++) {
					edges[j][k] = Math.min(edges[j][k], edges[j][i] + edges[i][k]);
				}
			}
		}
	}
	
	private static void initialize() {
		edges = new int[n+1][n+1];
		for (int i = 1; i<=n; i++) {
			for (int j=1; j<=n; j++) {
				edges[i][j] = INF;	
				if (i == j)
					edges[i][j] = 0;
			}
		}
	}
}
