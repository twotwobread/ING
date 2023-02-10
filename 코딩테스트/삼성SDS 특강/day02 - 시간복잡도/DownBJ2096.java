package day02;

import java.io.*;

public class DownBJ2096 {
	
	static int n;
	static int[][] arr;
	static int[] result = new int[3];
	static int[] newResult = new int[3];
	
	public static int max(int a, int b, int c) {
		int tmp1 = Math.max(a, b);
		int tmp2 = Math.max(b,  c);
		return Math.max(tmp1, tmp2);
	}
	
	public static int min(int a, int b, int c) {
		int tmp1 = Math.min(a, b);
		int tmp2 = Math.min(b,  c);
		return Math.min(tmp1, tmp2);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n][3];
		for (int i = 0; i < n; i++) {
			String[] tmp = br.readLine().split(" ");
			for (int j = 0; j < tmp.length; j++) {
				arr[i][j] = Integer.parseInt(tmp[j]);
			}
		}
		
		result[0] = arr[0][0];
		result[1] = arr[0][1];
		result[2] = arr[0][2];
		for (int i = 1; i< n; i++) {
			newResult[0] = Math.max(arr[i][0] + result[0], arr[i][0] + result[1]);
			newResult[1] = max(arr[i][1] + result[0], arr[i][1] + result[1], arr[i][1] + result[2]);
			newResult[2] = Math.max(arr[i][2] + result[1], arr[i][2] + result[2]);
			
			result[0] = newResult[0];
			result[1] = newResult[1];
			result[2] = newResult[2];
		}
		int maxValue = max(result[0], result[1], result[2]);
		
		result[0] = arr[0][0];
		result[1] = arr[0][1];
		result[2] = arr[0][2];
		for (int i = 1; i< n; i++) {
			newResult[0] = Math.min(arr[i][0] + result[0], arr[i][0] + result[1]);
			newResult[1] = min(arr[i][1] + result[0], arr[i][1] + result[1], arr[i][1] + result[2]);
			newResult[2] = Math.min(arr[i][2] + result[1], arr[i][2] + result[2]);
			
			result[0] = newResult[0];
			result[1] = newResult[1];
			result[2] = newResult[2];
		}
		int minValue = min(result[0], result[1], result[2]);
		
		System.out.println(maxValue + " " + minValue);
	}

}
