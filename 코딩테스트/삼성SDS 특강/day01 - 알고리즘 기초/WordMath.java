import java.io.*;
import java.util.*;

public class WordMath {
	static final int ALPHABET_LEN = 26, MAX_LEN = 8, A_VALUE = 65;
	
	
	static int n;
	static String[] word;
	static Integer[] prefixSum = new Integer[ALPHABET_LEN];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < ALPHABET_LEN; i++) {
			prefixSum[i] = 0;
		}
		
		for (int i = 0; i < n; i++) {
			char[] word = br.readLine().toCharArray();
			for (int j = 0; j < word.length; j++) {
				prefixSum[word[j] - 'A'] += (int) Math.pow(10, word.length - j - 1);
			}
		}
		
		Arrays.sort(prefixSum, Collections.reverseOrder());
		
		int value = 9;
		int result = 0;
		for (int i = 0; i < ALPHABET_LEN; i++) {
			if (prefixSum[i] == 0)
				break;
			result += (prefixSum[i] * value);
			value -= 1;
		}
		
		System.out.println(result);
	}
}
