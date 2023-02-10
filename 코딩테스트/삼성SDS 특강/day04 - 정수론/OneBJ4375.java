package day04;

import java.io.*;
import java.util.*;

public class OneBJ4375 {
	
	static String n;
	static char[] oneNumber; 
	
	public static boolean checkOneNumber(int num, int size) {
		int sum = 0;
		for (int i = 0; i < size; i++){
			sum *= 10;
			sum += oneNumber[0] - '0';
			
			if (sum / num <= 0) continue;
			else {
				sum %= num;
			}
		}
		return sum == 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while((n = br.readLine()) != null) {
			int input = Integer.parseInt(n);
			int size = 1, cnt = 1;
			while (size < input) { 
				size*=10;
				cnt += 1;
			}
			while (true) {
				oneNumber = new char[cnt];
				for (int i = 0; i < cnt; i++) {
					oneNumber[i] = '1';
				}
				
				if (checkOneNumber(input, cnt)) {
					System.out.println(cnt);
					break;
				}
				cnt += 1;
			}
		}
	}
}
