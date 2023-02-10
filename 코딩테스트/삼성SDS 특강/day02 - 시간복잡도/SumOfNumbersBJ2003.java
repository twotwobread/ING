package day02;

import java.io.*;

public class SumOfNumbersBJ2003 {
	
	static final int MAX_SIZE = 10000;
	
	static int n, m, result = 0;
	static int[] arr = new int[MAX_SIZE + 1];
	static int[] prefixSum = new int[MAX_SIZE + 1];
	
	public static void simulate() {
		for (int i = 1; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (prefixSum[j] - prefixSum[i-1] == m)
					result += 1;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		n = Integer.parseInt(tmp[0]);
		m = Integer.parseInt(tmp[1]);
		
		tmp = br.readLine().split(" ");
		for (int i = 0; i < tmp.length; i++) {
			arr[i] = Integer.parseInt(tmp[i]);
			if (arr[i] == m)
				result += 1;
		}
		
		prefixSum[0] = arr[0];
		for (int i = 1; i < n; i++) {
			prefixSum[i] = arr[i] + prefixSum[i - 1];
			if (prefixSum[i] == m)
				result += 1;
		}
		
		simulate();
		System.out.println(result);
	}
}
