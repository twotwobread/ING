package day02;

import java.io.*;

public class PartSumBJ1806 {
	
	static int n, m;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		n = Integer.parseInt(tmp[0]);
		m = Integer.parseInt(tmp[1]);
		
		arr = new int[n + 1];
		tmp = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(tmp[i]);
		}
		
		int i, j, length = Integer.MAX_VALUE;
		int sum = arr[0];
		i = j = 0;
		while(j < n) {
			if (sum == m) {
				length = Math.min(length, j - i + 1);
				sum -= arr[i++];
			} else if (sum < m) {
				sum += arr[++j];
			} else { // sum > m
				length = Math.min(length, j - i + 1);
				sum -= arr[i++];
			}
		}
		
		System.out.println(length == Integer.MAX_VALUE ? 0 : length);
	}

}
