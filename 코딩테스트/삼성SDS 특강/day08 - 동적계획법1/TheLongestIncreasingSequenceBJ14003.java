package day09;

import java.io.*;
import java.util.*;

public class TheLongestIncreasingSequenceBJ14003 {

	static int n;
	static int[] sequence;
	static List<Integer> lis = new ArrayList<>();
	static List<Integer> orders = new ArrayList<>();
	static List<Integer> result = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		String[] tmp = br.readLine().split(" ");
		sequence = new int[n];
		for (int i = 0; i < n; i++) {
			sequence[i] = Integer.parseInt(tmp[i]);
		}	
		
		lis.add(sequence[0]);
		orders.add(0);
		for (int i = 1; i < n; i++) {
			if (lis.get(lis.size() - 1) < sequence[i]) {
				lis.add(sequence[i]);
				orders.add(lis.size() - 1);
			} else {
				int index = binarySearch(sequence[i]);
				lis.set(index, sequence[i]);
				orders.add(index);
			}
		}
		
		System.out.println(lis.size());
		int length = lis.size() - 1;
		for (int i = n-1; i >= 0; i--) {
			if (length == orders.get(i)) {
				result.add(sequence[i]);
				length--;
			}
		}
		
		for (int i = lis.size() - 1; i >= 0; i--) {
			System.out.print(result.get(i) + " ");
		}
	}
	
	private static int binarySearch(int goal) {
		int start = 0, end = lis.size() - 1;
		while(start <= end) {
			int mid = (start + end) / 2;
			
			if (lis.get(mid) == goal) {
				return mid;
			} else if (lis.get(mid) > goal) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return start;
	}
}
