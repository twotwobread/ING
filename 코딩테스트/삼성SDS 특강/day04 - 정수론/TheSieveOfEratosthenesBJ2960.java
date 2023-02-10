package day04;

import java.io.*;

public class TheSieveOfEratosthenesBJ2960 {
	
	static int n, k;
	static boolean[] isPrime;
	
	public static int isPrime(int n) {
		int cnt = 0;
		for (int i = 2; i <= n; i++) {
			if (isPrime[i] == true) {
				cnt += 1;
				if (cnt == k)
					return i;
				for (int j = i * i; j <= n; j+=i) {
					if (isPrime[j] == true) {
						cnt += 1;
						if (cnt == k)
							return j;
						isPrime[j] = false;
					}
				}
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		n = Integer.parseInt(tmp[0]);
		k = Integer.parseInt(tmp[1]);
		
		isPrime = new boolean[n + 1];
		initialize();
		System.out.println(isPrime(n));
	}
	
	public static void initialize() {
		for (int i = 2; i <= n; i++) {
			isPrime[i] = true;
		}
	}
}
