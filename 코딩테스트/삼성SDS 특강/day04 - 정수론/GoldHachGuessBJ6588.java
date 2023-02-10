package day04;

import java.io.*;
import java.util.*;

public class GoldHachGuessBJ6588 {
	
	static final int MAX_SIZE = 1000000;
	
	static int n;
	static boolean[] isPrime = new boolean[MAX_SIZE + 1];
	static List<Integer> primeNumbers = new ArrayList<>();
	
	public static void checkPrime() {
		for (int i = 2; i <= MAX_SIZE; i++) {
			if (isPrime[i] == true) {
				primeNumbers.add(i);
				if (i <= 1000) {
					for (int j = i * i; j <= MAX_SIZE; j+=i) {
						isPrime[j] = false;
					}
				}
			}
		}
	}
	
	public static void verify(int num) {
		for (int now : primeNumbers) {
			if (num - now < 2) break;
			if (isPrime[num - now]) {
				System.out.println(num + " = " + now + " + " + (num - now));
				return;
			}
		}
		System.out.println("GoldBach's conjecture is wrong.");
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		initialize();
		checkPrime();
		while((n = Integer.parseInt(br.readLine())) != 0) {
			verify(n);
		}
	}
	
	public static void initialize() {
		for (int i = 2; i <= MAX_SIZE; i++) {
			isPrime[i] = true;
		}
	}

}
