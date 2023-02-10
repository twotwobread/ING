package day02;

import java.io.*;
import java.util.*;

public class Fibo {
	
	static int n;
	static long[] dp = new long[91];
	
	public static long fibo(int num) {
		if (num <= 2)
			return 1;
		
		if (dp[num] != 0)
			return dp[num];
		
		dp[num] = fibo(num - 1) + fibo(num - 2);
		return dp[num];
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		dp[1] = 1;
		dp[2] = 1;
		System.out.println(fibo(n));
	}
}
