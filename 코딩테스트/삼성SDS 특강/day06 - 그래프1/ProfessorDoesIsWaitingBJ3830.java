package day07;

import java.io.*;
import java.util.*;

public class ProfessorDoesIsWaitingBJ3830 {

	static int n, m;
	static int[] parents;
	static long[] weightDiff;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String[] tmp = br.readLine().split(" ");
			n = Integer.parseInt(tmp[0]);
			m = Integer.parseInt(tmp[1]);
			
			if (isExit()) break;
			
			initialize();
			for (int i=0; i<m; i++) {
				tmp = br.readLine().split(" ");
				int a = Integer.parseInt(tmp[1]);
				int b = Integer.parseInt(tmp[2]);
				if (tmp.length == 3) { // 교수님의 질문
					if (find(a) != find(b)) {
						System.out.println("UNKNOWN");
					} else {
						System.out.println(weightDiff[b] - weightDiff[a]);
					}
				} else { // 무게를 쟀음.
					int w = Integer.parseInt(tmp[3]);
					union(a, b, w);
				}
			}
		}
	}
	
	private static void union(int a, int b, int weight) {
		int parentA = find(a);
		int parentB = find(b);
		
		if (parentA == parentB) return;
		
//		if (parentA < parentB) {
			weightDiff[parentB] = weightDiff[a] - weightDiff[b] + weight;
			parents[parentB] = parentA;
//		} else { // parentB < parentA
//			weightDiff[parentA] = weightDiff[b] - weightDiff[a] - weight;
//			parents[parentA] = parentB;
//		}
	}
	
	private static int find(int n) {
		if (parents[n] != n) {
			int parentIndex = find(parents[n]);
			weightDiff[n] += weightDiff[parents[n]];
			parents[n] = parentIndex;
		}
		return parents[n];
	}
	
	private static void initialize() {
		weightDiff = new long[n+1];
		parents = new int[n+1];
		for (int i=1; i<=n; i++) {
			parents[i] = i;
		}
	}
	
	private static boolean isExit() {
		if (n == 0 && m == 0)
			return true;
		return false;
	}
}
