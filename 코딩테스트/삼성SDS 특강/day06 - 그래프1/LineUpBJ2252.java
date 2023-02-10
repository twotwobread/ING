package day06;

import java.io.*;
import java.util.*;

public class LineUpBJ2252 {
	
	static int n, m;
	static int[] come;
	static List<List<Integer>> list;
	static StringBuilder sb;
	
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
			list.get(a).add(b);
			come[b] += 1;
		}
		
		topological();
		System.out.println(sb.toString());
	}
	
	public static boolean isRunnig() {
		for (int i = 1; i <= n; i++) {
			if (come[i] != -1)
				return true;
		}
		return false;
	}
	
	public static void topological() {
		while(isRunnig()) {
			for (int i = 1; i <= n; i++) {
				if (come[i] == 0) {
					sb.append(i + " ");
					come[i] -= 1;
					for (int edge : list.get(i)) {
						come[edge] -= 1;
					}
				}
			}
		}
	}
	
	public static void initialize() {
		sb = new StringBuilder();
		come = new int[n+1];
		list = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			list.add(new ArrayList<>());
		}
	}
}
