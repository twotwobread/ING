package day05;

import java.io.*;

// - 현재 값이 몇인지?
// - 올바른 등식의 수 -> 배열에 들어갈 값.
// - 어디까지 계산했는지?

public class FirstGradeBJ5557 {
	
	static final int MAX_VALUE = 20;
	
	static int n;
	static int[] numbers;
	
	static long[][] equationCount;
	
	public static void main(String[] args) throws IOException {
		inputValue();
		initialize();
		getPossibleEquation();
		System.out.println(countTotalPossibleEquation());
	}
	
	public static long countTotalPossibleEquation() {
		long sum = 0;
		for (int i = 0; i <= MAX_VALUE; i++) {
			if (i + numbers[n-2] == numbers[n-1]) {
				sum += equationCount[n-3][i]; 
			}
			if (i - numbers[n-2] == numbers[n-1]) {
				sum += equationCount[n-3][i]; 
			}
		}
		return sum;
	}
	
	public static void getPossibleEquation() {
		for (int i = 1; i < n - 2; i++) {
			for (int j = 0; j <= MAX_VALUE; j++) {
				if (j - numbers[i] >= 0 && equationCount[i - 1][j - numbers[i]] > 0) {
					equationCount[i][j] += equationCount[i - 1][j - numbers[i]];
				}
				
				if (j + numbers[i] <= MAX_VALUE && equationCount[i - 1][j + numbers[i]] > 0) {
					equationCount[i][j] += equationCount[i - 1][j + numbers[i]];
				}
			}
		}
	}
	
	public static void initialize() {
		equationCount = new long[n][MAX_VALUE + 1];
		equationCount[0][numbers[0]] = 1;
	}
	
	public static void inputValue() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		numbers = new int[n];
		String[] tmp = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(tmp[i]);
		}
	}

}
