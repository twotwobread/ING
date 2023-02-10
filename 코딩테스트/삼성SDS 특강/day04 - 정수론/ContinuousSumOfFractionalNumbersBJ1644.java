package day04;

import java.io.*;
import java.util.*;

public class ContinuousSumOfFractionalNumbersBJ1644 {
	
	static int n;
	static boolean[] isPrime;
	static List<Integer> primeNumbers = new ArrayList<>();
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		isPrime = new boolean[n + 1];
		
		initialize();
		getPrimeNumbers(n);
		
		System.out.println(getContinousSumOfFractionalNumber(n));
	}
	
	public static int getContinousSumOfFractionalNumber(int num) {
		int cnt = 0;
		int start = 0, end = 0;
		int sum = primeNumbers.size() > 0 ? primeNumbers.get(start) : 0;
		while (start < primeNumbers.size() && end < primeNumbers.size()) {
			if (sum < num) {
				end += 1;
				if (end < primeNumbers.size() ) {
					sum += primeNumbers.get(end);
				}
			} else if (sum > num) {
				sum -= primeNumbers.get(start++);
			} else {
				cnt += 1;
				end += 1;
				if (end < primeNumbers.size() ) {
					sum += primeNumbers.get(end);
				}
			}
		}
		
		return cnt;
	}
	
	public static void getPrimeNumbers(int num) {
		for (int i = 2; i <= num; i++) {
			if (isPrime[i]) {
				primeNumbers.add(i);
				if (i <= 2001) {
					for (int j = i * i; j <= num; j += i) {
						isPrime[j] = false;
					}
				}
			}
		}
	}
	
	public static void initialize() {
		for (int i = 2; i <= n; i++) {
			isPrime[i] = true;
		}
	}
}
