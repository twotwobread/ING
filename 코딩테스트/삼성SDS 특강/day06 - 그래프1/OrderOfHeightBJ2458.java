package day06;

import java.io.*;
import java.util.*;

public class OrderOfHeightBJ2458 {
	
	static int n, m;
	static int[] indegree;
	static int[][] lowerCheck, higherCheck;
	static List<List<Integer>> list;
	static List<Integer> sequence = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		n = Integer.parseInt(tmp[0]);
		m = Integer.parseInt(tmp[1]);
		
		initialize();
		for (int i = 0; i < m; i++) {
			tmp = br.readLine().split(" ");
			int a = Integer.parseInt(tmp[0]);
			int b = Integer.parseInt(tmp[1]);
			indegree[b] += 1;
			list.get(a).add(b);
		}
		
		topological();
		int result = count();
		System.out.println(result);
	}
	
	public static int count() {
		int result = 0;
		for (int i = sequence.size() - 1; i >= 0; i--) {
			for (int edge : list.get(sequence.get(i))) {
				for (int j = 1; j <= n; j++) {
					if (higherCheck[edge][j] == 1)
						higherCheck[sequence.get(i)][j] = 1;
				}
			}
		}
		
		loop1:
		for (int i = 1; i<=n; i++) {
			for (int j = 1; j <= n; j++) {
				if (lowerCheck[i][j] != 1 && higherCheck[i][j] != 1)
					continue loop1;
			}
			result += 1;
		}
		
		return result;
	}
	
	public static boolean isRunning() {
		for (int i = 1; i <= n; i++) {
			if (indegree[i] != -1) return true;
		}
		return false;
	}
	
	public static void topological() {
		while(isRunning()) {
			for (int i = 1; i<=n; i++) {
				if (indegree[i] == 0) {
					sequence.add(i);
					indegree[i] -= 1;
					for (int edge : list.get(i)) {
						indegree[edge] -= 1;
						for (int j = 1; j<=n; j++) {
							if (lowerCheck[i][j] == 1)
								lowerCheck[edge][j] = 1;
							higherCheck[i][edge] = 1;
						}
					}
				}
			}
		}
	}
	
	public static void initialize() {
		indegree = new int[n+1];
		lowerCheck = new int[n+1][n+1];
		higherCheck = new int[n+1][n+1];
		list = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			lowerCheck[i][i] = 1;
			list.add(new ArrayList<>());
		}
	}
}
