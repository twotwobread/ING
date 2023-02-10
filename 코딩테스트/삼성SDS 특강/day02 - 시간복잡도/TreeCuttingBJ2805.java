package day02;

import java.io.*;
import java.util.*;

public class TreeCuttingBJ2805 {
	
	static int n, m;
	static long result = 0;
	static int[] tree;
	
	public static void binarySearch(int start, int end) {
		if (start > end) return;
		
		int mid = (start + end) / 2;
		
		long sum = 0;
		for (int i = 0; i < n; i++) {
			long tmp = tree[i] - mid;
			if (tmp > 0) sum += tmp;
		}
		
		if (sum >= m) {
			result = Math.max(result, mid);
			binarySearch(mid + 1, end);
		} else {
			binarySearch(start, mid - 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		n = Integer.parseInt(tmp[0]);
		m = Integer.parseInt(tmp[1]);
		
		tree = new int[n];
		tmp = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			tree[i] = Integer.parseInt(tmp[i]);
		}
		
		Arrays.sort(tree);
		binarySearch(0, tree[n-1]);
		
		System.out.println(result);
	}
}
