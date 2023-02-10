package day04;

import java.io.*;
import java.util.*;

public class FactorizationIntoPrimesBJ11653 {

	static int n;
	static boolean[] isPrime;
	static List<Integer> primeNumbers = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		isPrime = new boolean[n + 1];
		initialize();
		getPrimeNumbers(n);
		
		factorizationIntoPrimes(n);
	}
	
	public static void factorizationIntoPrimes(int num) {
		for (int primeNumber : primeNumbers) {
			if (num == 1)
				return;
			
			while(num % primeNumber == 0) {
				num /= primeNumber;
				System.out.println(primeNumber);
			}
		}
	}
	
	public static void getPrimeNumbers(int num) {
		for (int i = 2; i <= num; i++) {
			if (isPrime[i]) {
				primeNumbers.add(i);
				if (i <= 10000) {
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
