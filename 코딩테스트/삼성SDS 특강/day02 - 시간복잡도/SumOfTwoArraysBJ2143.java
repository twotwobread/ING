package day02;

import java.io.*;
import java.util.*;

public class SumOfTwoArraysBJ2143 {

	static int T, N, M;
	static long result = 0;
	static int[] A, B;
	
	static List<Integer> allSumA = new ArrayList<>(), allSumB = new ArrayList<>();
	
	public static void caculate(List<Integer> all, int[] sum, int n) {
		for (int i = 0; i < n; i++) {
			int sumValue = 0;
			for (int j = i; j < n; j++) {
				sumValue += sum[j];
				all.add(sumValue);
			}
		}
	}
	
	public static void select() {
		int i = 0, j = allSumB.size() - 1;
		
		while(i < allSumA.size() && j >= 0) {
			int sum = allSumA.get(i) + allSumB.get(j);
			
			if (sum < T) {
				i++;
			} else if (sum > T) {
				j--;
			} else {
				long cntA = 0;
				int valueA = allSumA.get(i);
				while(i < allSumA.size() && valueA == allSumA.get(i)) {
					cntA++;
					i++;
				}
				
				long cntB = 0;
				int valueB = allSumB.get(j);
				while(j >= 0 && valueB == allSumB.get(j)) {
					cntB++;
					j--;
				}
				result += (cntA * cntB);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		
		A = new int[N];
		String[] tmp = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(tmp[i]);
		}
		
		M = Integer.parseInt(br.readLine());
		
		B = new int[M];
		tmp = br.readLine().split(" ");
		for (int i = 0; i < M; i++) {
			B[i] = Integer.parseInt(tmp[i]);
		}
		
		caculate(allSumA, A, N);
		caculate(allSumB, B, M);
		
		Collections.sort(allSumA);
		Collections.sort(allSumB);
		
		select();
		System.out.println(result);
	}
}
