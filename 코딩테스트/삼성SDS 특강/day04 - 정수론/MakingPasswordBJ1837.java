package day04;

import java.io.*;
import java.util.*;

public class MakingPasswordBJ1837 {
	
	static char[] P;
	static int K;
	static boolean[] isPrime;
	static List<Integer> primeNumbers = new ArrayList<>();
	
	public static void initialize(int num) {
		for (int i = 2; i < num; i++) {
			isPrime[i] = true;
		}
	}
	
	public static void checkPrimeNumber(int num) {
		for(int i = 2; i < num; i++) {
			if (isPrime[i]) {
				primeNumbers.add(i);
				if (i <= 1000) {
					for (int j = i * i; j <= num; j+=i) {
						isPrime[j] = false;
					}
				}
			}
		}
	}
	
	public static boolean mod(int num) {
		int sum = 0;
		for (int i = 0; i < P.length; i++) {
			sum *= 10;
			sum += P[i] - '0';
			if (sum < num) {
				continue;
			} else {
				sum %= num;
			}
		}
		return sum == 0;
	}
	
	public static void checkGoodOrBadPassword() {
		for (int primeNumber : primeNumbers) {
			if (mod(primeNumber)) {
				System.out.println("BAD " + primeNumber);
				return;
			}
		}
		System.out.println("GOOD");
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		P = tmp[0].toCharArray();
		K = Integer.parseInt(tmp[1]);
		
		isPrime = new boolean[K + 1];
		initialize(K);
		checkPrimeNumber(K);
		checkGoodOrBadPassword();
	}

}
